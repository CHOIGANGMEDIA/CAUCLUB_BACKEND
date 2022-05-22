package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Club;
import CHOIGANGMEDIA.CAUCLUB.domain.Member;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
