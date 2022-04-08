package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Club;
import CHOIGANGMEDIA.CAUCLUB.domain.Member;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemoryFindRepository implements FindRepository{

    private static final String collectionMember = "Member";

    /**
     * 아이디찾기,비밀번호 찾기에서 사용되는 이메일 인 (프론트에서 보내준 email을 Member Collection의 모든 Document를 순회하면서 검색)
     * 찾고자하는 email이 있으면 true 반환, 없으면 false 반환
     */

    @Override
    public Boolean emailCheck(String email) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection("Member").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for(QueryDocumentSnapshot document : documents) {
            if(document.toObject(Member.class).getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean resetPassword(String id, String password) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("Member").document(id);
        ApiFuture<WriteResult> future = documentReference.update("password",password);
        return true;
    }
}
