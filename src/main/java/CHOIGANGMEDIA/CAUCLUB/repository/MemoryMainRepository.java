package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Member;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryMainRepository implements MainRepository{


    @Override
    public ArrayList<Integer> showJoinedClub(String id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection("Member").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for(QueryDocumentSnapshot document : documents) {
            if (document.toObject(Member.class).getId().equals(id)) {
                return document.toObject(Member.class).getJoinedClub();
            }
        }
        return null;
    }

    @Override
    public ArrayList<Integer> showManagingClub(String id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection("Member").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for(QueryDocumentSnapshot document : documents) {
            if (document.toObject(Member.class).getId().equals(id)) {
                return document.toObject(Member.class).getManagingClub();
            }
        }
        return null;
    }
}
