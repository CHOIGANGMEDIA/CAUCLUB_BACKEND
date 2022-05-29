package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Member;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryMyPageRepository implements MyPageRepository{

    @Override
    public Member findById(String id) throws Exception{
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection("Member").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for(QueryDocumentSnapshot document : documents) {
            if (document.toObject(Member.class).getId().equals(id)) {
                return document.toObject(Member.class);
            }
        }
        return null;
    }

    @Override
    public Boolean modifyMyInformation(String id, String name, String email, ArrayList<String> keyword) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("Member").document(id);
        ApiFuture<WriteResult> future = documentReference.update("name",name);
        ApiFuture<WriteResult> future1 = documentReference.update("email",email);
        ApiFuture<WriteResult> future2 = documentReference.update("keyword",keyword);
        return true;
    }

}
