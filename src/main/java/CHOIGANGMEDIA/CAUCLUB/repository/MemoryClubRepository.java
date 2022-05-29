package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Archive;
import CHOIGANGMEDIA.CAUCLUB.domain.Club;
import CHOIGANGMEDIA.CAUCLUB.domain.Member;
import CHOIGANGMEDIA.CAUCLUB.domain.Post;
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
    public String registerNewClub(Club club) throws Exception {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture=
                dbFirestore.collection("Club").document(String.valueOf(club.getClubId())).set(club);
        return collectionsApiFuture.get().getUpdateTime().toString();
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
    public Boolean modifyClubInformation(String picture, String leaderId, String name, int type, String introduction, int clubId) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("Club").document(String.valueOf(clubId));
        ApiFuture<WriteResult> future = documentReference.update("picture",picture);
        ApiFuture<WriteResult> future1 = documentReference.update("leaderId",leaderId);
        ApiFuture<WriteResult> future2 = documentReference.update("name", name);
        ApiFuture<WriteResult> future3 = documentReference.update("type", type);
        ApiFuture<WriteResult> future4 = documentReference.update("introduction", introduction);
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

//    public ArrayList<ArrayList<String>> getClubKeyword() throws Exception{
//        ArrayList<ArrayList<String>> clubKeyword = new ArrayList<>();
//        Firestore firestore = FirestoreClient.getFirestore();
//        ApiFuture<QuerySnapshot> future = firestore.collection("Club").get();
//        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
//        for(QueryDocumentSnapshot document : documents) {
//            clubKeyword.add(document.toObject(Club.class).getKeyword());
//        }
//        return clubKeyword;
//    }
}
