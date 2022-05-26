package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Archive;
import CHOIGANGMEDIA.CAUCLUB.domain.Club;
import CHOIGANGMEDIA.CAUCLUB.domain.Member;
import CHOIGANGMEDIA.CAUCLUB.domain.Post;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class MemoryPostRepository implements PostRepository{

    private static final String collectionPost = "Post";

    @Override
    public String registerNewPost(Post post) throws Exception {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture=
                dbFirestore.collection(collectionPost).document(String.valueOf(post.getPostId())).set(post);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    @Override
    public int getClubId(String memberId) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection("Club").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for(QueryDocumentSnapshot document : documents) {
            if(document.toObject(Club.class).getLeaderId().equals(memberId)){
                return document.toObject(Club.class).getClubId();
            }
        }
        return 0;
    }

    @Override
    public Boolean deletePost(int postId) throws Exception {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("Post").document(String.valueOf(postId)).delete();
        return true;
    }

    @Override
    public String getClubName(int clubId) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection("Club").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for(QueryDocumentSnapshot document : documents) {
            if (document.toObject(Club.class).getClubId() == clubId){
                return document.toObject(Club.class).getName();
            }
        }
        return null;
    }

    @Override
    public Post getPostObject(int postId) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference docRef = firestore.collection("Post").document(String.valueOf(postId));
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        return document.toObject(Post.class);
    }

    @Override
    public Boolean modifyPost(String title, String contents, int postId) throws Exception {
        Date now = new Date();
        String modifiedDate = now.toString();
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("Post").document(String.valueOf(postId));
        ApiFuture<WriteResult> future = documentReference.update("title",title);
        ApiFuture<WriteResult> future1 = documentReference.update("contents",contents);
        ApiFuture<WriteResult> future2 = documentReference.update("modifiedDate", modifiedDate);
        return true;
    }

    @Override
    public List<HashMap<String, Object>> viewAllPost() throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        Query query = firestore.collection("Post")
                .orderBy("createdDate", Query.Direction.ASCENDING);
        QuerySnapshot queryDocumentSnapshots = query.get().get();
        List<HashMap<String,Object>> postList = new ArrayList<>();
        if(queryDocumentSnapshots.size() != 0){
            for(Post post : queryDocumentSnapshots.toObjects(Post.class)){
                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("title", post.getTitle());
                hashMap.put("clubName", getClubName(post.getClubId()));
                hashMap.put("contents", post.getContents());
                hashMap.put("time", post.getCreatedDate());
                postList.add(hashMap);
            }
        }
        return postList;
    }

}
