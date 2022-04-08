package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Club;
import CHOIGANGMEDIA.CAUCLUB.domain.Member;
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

    private static final String collectionMember = "Member";

    /**
     * 회원가입 시 id 중복체크 메소드 (Member Collection의 모든 Document를 순회하면서 id Field 값이 중복되는 것이 있는지 Check)
     * 만약에 중복된 아이디가 있다면 false 반환
     * 중복된 아이디가 없으면 true 반환
     */

    @Override
    public Boolean idDuplicateCheck(String id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection("Member").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for(QueryDocumentSnapshot document : documents) {
            if(document.toObject(Member.class).getId().equals(id)){
                return false;
            }
        }
        return true;
    }

    /**
     * 회원가입 메소드 (DB에 저장, id를 Document 이름으로 설정해서 자동으로 저장됨)
     */

    @Override
    public String registerMember(Member member) throws Exception {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture=
                dbFirestore.collection(collectionMember).document(member.getName()).set(member);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    /**
     * 로그인 메소드
     */

    @Override
    public Boolean loginCheck(String id, String password) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection("Member").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for(QueryDocumentSnapshot document : documents) {
            if(document.toObject(Member.class).getId().equals(id)){
                if(document.toObject(Member.class).getPassword().equals(password)){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        return false;
    }
}