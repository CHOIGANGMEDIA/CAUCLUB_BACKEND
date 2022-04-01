package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Club;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemoryLoginRepository implements LoginRepository{

    private static final String collectionClub = "Club";

    /**
     * 회원가입 시 id 중복체크 메소드 (Club Collection의 모든 Document를 순회하면서 id Field 값이 중복되는 것이 있는지 Check)
     * 만약에 중복된 아이디가 있다면 false 반환
     * 중복된 아이디가 없으면 true 반환
     */

    @Override
    public Boolean idDuplicateCheck(String id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection("Club").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for(QueryDocumentSnapshot document : documents) {
            if(document.toObject(Club.class).getId().equals(id)){
                return false;
            }
        }
        return true;
    }

    /**
     * 회원가입 메소드 (DB에 저장, 동아리명을 Document 이름으로 설정해서 자동으로 저장됨)
     */

    @Override
    public String registerClub(Club club) throws Exception {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture=
                dbFirestore.collection(collectionClub).document(club.getName()).set(club);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
}