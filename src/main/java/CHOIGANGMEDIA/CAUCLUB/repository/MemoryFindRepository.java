package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Club;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemoryFindRepository implements FindRepository{

    private static final String collectionClub = "Club";

    /**
     * 아이디찾기,비밀번호 찾기에서 사용되는 이메일 인 (프론트에서 보내준 email을 Club Collection의 모든 Document를 순회하면서 검색)
     * 찾고자하는 email이 있으면 true 반환, 없으면 false 반환
     */

    @Override
    public Boolean emailCheck(String email) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection("Club").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for(QueryDocumentSnapshot document : documents) {
            if(document.toObject(Club.class).getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
}
