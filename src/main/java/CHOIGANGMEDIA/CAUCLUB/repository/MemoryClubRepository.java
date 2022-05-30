package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryClubRepository implements ClubRepository{

    @Override
    public List<Integer> viewManagingClub(String memberId) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference docRef = firestore.collection("Member").document(memberId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        Member member = null;
        if(document.exists()){
            member = document.toObject(Member.class);
            assert member != null;
            return member.getManagingClub();
        }
        return null;
    }

    @Override
    public List<Integer> viewJoinedClub(String memberId) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference docRef = firestore.collection("Member").document(memberId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        Member member = null;
        if(document.exists()){
            member = document.toObject(Member.class);
            assert member != null;
            return member.getJoinedClub();
        }
        return null;
    }

    @Override
    public Boolean deleteClub(int clubId) throws Exception {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("Club").document(String.valueOf(clubId)).delete();
        return true;
    }

    @Override
    public Club getClubObject(int clubId) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference docRef = firestore.collection("Club").document(String.valueOf(clubId));
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        return document.toObject(Club.class);
    }

    @Override
    public String getDepartmentByMemberId(String memberId) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection("Member").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for(QueryDocumentSnapshot document : documents) {
            if(document.toObject(Member.class).getId().equals(memberId)){
                return document.toObject(Member.class).getDepartment();
            }
        }
        return null;
    }

    @Override
    public ArrayList<Integer> viewDepartmentClubList(String memberId, String department) throws Exception {
        ArrayList<Integer> clubList = new ArrayList<>();
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection("Club").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for(QueryDocumentSnapshot document : documents) {
            if(document.toObject(Club.class).getDepartment().equals(department)){
                clubList.add(document.toObject(Club.class).getClubId());
            }
        }
        return clubList;
    }

    @Override
    public Boolean modifyClubInformation(String picture, String leaderId, String name, int type, String introduction, int clubId, ArrayList<String> keyword) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("Club").document(String.valueOf(clubId));
        ApiFuture<WriteResult> future = documentReference.update("picture",picture);
        ApiFuture<WriteResult> future1 = documentReference.update("leaderId",leaderId);
        ApiFuture<WriteResult> future2 = documentReference.update("name", name);
        ApiFuture<WriteResult> future3 = documentReference.update("type", type);
        ApiFuture<WriteResult> future4 = documentReference.update("introduction", introduction);
        ApiFuture<WriteResult> future5 = documentReference.update("keyword", keyword);
        return true;
    }

    @Override
    public List<Club> recommendClubList(String memberId) throws Exception {

        Map<Object,Integer> clubList = new HashMap<>();
        List<Club> clubs = new ArrayList<>();

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference docRef = firestore.collection("Member").document(memberId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        ArrayList<String> memberKeyword;
        memberKeyword = Objects.requireNonNull(document.toObject(Member.class)).getKeyword();

        ApiFuture<QuerySnapshot> future1 = firestore.collection("Club").get();
        List<QueryDocumentSnapshot> documents = future1.get().getDocuments();
        for(QueryDocumentSnapshot clubDocument : documents) {
            ArrayList<String> clubKeyword;
            clubKeyword = clubDocument.toObject(Club.class).getKeyword();
            int commonCount = 0;
            for(int i=0;i<clubKeyword.size();i++){
                for(int j=0;j<memberKeyword.size();j++){
                    if(memberKeyword.get(j).equals(clubKeyword.get(i))){
                        commonCount+=1;
                    }
                }
            }
            clubList.put(clubDocument.toObject(Club.class),commonCount);
        }
        List<Map.Entry<Object,Integer>> entryList = new LinkedList<>(clubList.entrySet());
        entryList.sort(new Comparator<Map.Entry<Object, Integer>>() {
            @Override
            public int compare(Map.Entry<Object, Integer> o1, Map.Entry<Object, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        for(Map.Entry<Object,Integer> entry : entryList){
            clubs.add((Club) entry.getKey());
        }
        return clubs;
    }

    @Override
    public String registerNewClub(Club club, String memberId) throws Exception {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture=
                dbFirestore.collection("Club").document(String.valueOf(club.getClubId())).set(club);
        plusClubPk();
        DocumentReference docRef = dbFirestore.collection("Member").document(memberId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        ArrayList<Integer> managingClubList = new ArrayList<>();
        managingClubList = Objects.requireNonNull(document.toObject(Member.class)).getManagingClub();
        int tempClubId = setClubPk();
        managingClubList.add(tempClubId-1);
        ApiFuture<WriteResult> future1 = docRef.update("managingClub",managingClubList);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    @Override
    public int setClubPk() throws Exception{
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference docRef = firestore.collection("CollectionPrimaryKey").document("club");
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        ArrayList<Integer> list = new ArrayList<>();
        list = Objects.requireNonNull(document.toObject(CollectionPrimaryKey.class)).getClubPk();
        return list.size();
    }

    @Override
    public void plusClubPk() throws Exception{
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("CollectionPrimaryKey").document("club");
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        ArrayList<Integer> originalList = new ArrayList<>();
        originalList = Objects.requireNonNull(document.toObject(CollectionPrimaryKey.class)).getClubPk();
        originalList.add(originalList.size());
        ApiFuture<WriteResult> future1 = documentReference.update("clubPk",originalList);
    }

    @Override
    public Boolean enterClub(String memberId, int clubId) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("Club").document(String.valueOf(clubId));
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        ArrayList<String> originalMemberList = new ArrayList<>();
        originalMemberList = Objects.requireNonNull(document.toObject(Club.class)).getMembers();
        originalMemberList.add(memberId);
        ApiFuture<WriteResult> future1 = documentReference.update("members",originalMemberList);
        DocumentReference documentReference1 = firestore.collection("Member").document(memberId);
        ApiFuture<DocumentSnapshot> future2 = documentReference1.get();
        DocumentSnapshot documentSnapshot = future2.get();
        DocumentSnapshot document1 = future2.get();
        ArrayList<Integer> originalJoiningClubList = new ArrayList<>();
        originalJoiningClubList = Objects.requireNonNull(document1.toObject(Member.class)).getJoinedClub();
        originalJoiningClubList.add(clubId);
        ApiFuture<WriteResult> future3 = documentReference1.update("joinedClub",originalJoiningClubList);
        return true;
    }

    @Override
    public Boolean resignClub(String memberId, int clubId) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("Club").document(String.valueOf(clubId));
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        ArrayList<String> originalMemberList = new ArrayList<>();
        originalMemberList = Objects.requireNonNull(document.toObject(Club.class)).getMembers();
        originalMemberList.remove(memberId);
        ApiFuture<WriteResult> future1 = documentReference.update("members",originalMemberList);
        DocumentReference documentReference1 = firestore.collection("Member").document(memberId);
        ApiFuture<DocumentSnapshot> future2 = documentReference1.get();
        DocumentSnapshot documentSnapshot = future2.get();
        DocumentSnapshot document1 = future2.get();
        ArrayList<Integer> originalJoiningClubList = new ArrayList<>();
        originalJoiningClubList = Objects.requireNonNull(document1.toObject(Member.class)).getJoinedClub();
        originalJoiningClubList.remove(Integer.valueOf(clubId));
        ApiFuture<WriteResult> future3 = documentReference1.update("joinedClub",originalJoiningClubList);
        return true;
    }

    @Override
    public Boolean changeLeader(String memberId, int clubId, String newLeaderId) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("Member").document(memberId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        ArrayList<Integer> managingClubList = new ArrayList<>();
        ArrayList<Integer> joinedClubList = new ArrayList<>();
        managingClubList = Objects.requireNonNull(document.toObject(Member.class)).getManagingClub();
        joinedClubList = Objects.requireNonNull(document.toObject(Member.class)).getJoinedClub();
        managingClubList.remove(Integer.valueOf(clubId));
        joinedClubList.add(clubId);
        ApiFuture<WriteResult> future1 = documentReference.update("managingClub",managingClubList);
        ApiFuture<WriteResult> future2 = documentReference.update("joinedClub",joinedClubList);

        DocumentReference documentReference1 = firestore.collection("Member").document(newLeaderId);
        ApiFuture<DocumentSnapshot> future3 = documentReference1.get();
        DocumentSnapshot document1 = future3.get();
        ArrayList<Integer> newManagingClubList = new ArrayList<>();
        ArrayList<Integer> newJoinedClubList = new ArrayList<>();
        newManagingClubList = Objects.requireNonNull(document1.toObject(Member.class)).getManagingClub();
        newJoinedClubList = Objects.requireNonNull(document1.toObject(Member.class)).getJoinedClub();
        newManagingClubList.add(clubId);
        newJoinedClubList.remove(Integer.valueOf(clubId));
        ApiFuture<WriteResult> future4 = documentReference1.update("managingClub",newManagingClubList);
        ApiFuture<WriteResult> future5 = documentReference1.update("joinedClub",newJoinedClubList);

        DocumentReference documentReference2 = firestore.collection("Club").document(String.valueOf(clubId));
        ApiFuture<DocumentSnapshot> future6 = documentReference2.get();
        DocumentSnapshot document2 = future6.get();
        ArrayList<String> memberList = new ArrayList<>();
        memberList = Objects.requireNonNull(document2.toObject(Club.class)).getMembers();
        memberList.add(memberId);
        memberList.remove(newLeaderId);
        ApiFuture<WriteResult> future7 = documentReference2.update("members",memberList);
        ApiFuture<WriteResult> future8 = documentReference2.update("leaderId",newLeaderId);


        return true;
    }
}
