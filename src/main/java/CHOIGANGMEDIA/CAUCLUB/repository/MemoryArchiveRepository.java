package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Archive;
import CHOIGANGMEDIA.CAUCLUB.domain.Club;
import CHOIGANGMEDIA.CAUCLUB.domain.Post;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryArchiveRepository implements ArchiveRepository{

    @Override
    public String registerNewArchive(Archive archive) throws Exception {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture=
                dbFirestore.collection("Archive").document(String.valueOf(archive.getArchiveId())).set(archive);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    @Override
    public Boolean deleteArchive(int archiveId) throws Exception {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("Archive").document(String.valueOf(archiveId)).delete();
        return true;
    }

    @Override
    public Boolean modifyArchive(String title, ArrayList<String> pictureUrls, int archiveId, String contents) throws Exception {
        Date now = new Date();
        String modifiedDate = now.toString();
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("Archive").document(String.valueOf(archiveId));
        ApiFuture<WriteResult> future = documentReference.update("title",title);
        ApiFuture<WriteResult> future1 = documentReference.update("contents",contents);
        ApiFuture<WriteResult> future2 = documentReference.update("modifiedDate", modifiedDate);
        ApiFuture<WriteResult> future3 = documentReference.update("pictureUrls", pictureUrls);
        return true;
    }

    @Override
    public Archive getArchiveObject(int archiveId) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference docRef = firestore.collection("Archive").document(String.valueOf(archiveId));
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        return document.toObject(Archive.class);
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
    public Boolean likeArchive(int archiveId, String memberId) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference docRef = firestore.collection("Archive").document(String.valueOf(archiveId));
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        ArrayList<String> likeMemberList = Objects.requireNonNull(document.toObject(Archive.class)).getLikeMember();
        int likeCount = Objects.requireNonNull(document.toObject(Archive.class)).getLike();
        if(likeMemberList.contains(memberId)){
            likeMemberList.remove(memberId);
            likeCount = likeCount-1;
            ApiFuture<WriteResult> future1 = docRef.update("likeMember",likeMemberList);
            ApiFuture<WriteResult> future2 = docRef.update("like",likeCount);
            return false;
        }
        else{
            likeMemberList.add(memberId);
            likeCount = likeCount+1;
            ApiFuture<WriteResult> future1 = docRef.update("likeMember",likeMemberList);
            ApiFuture<WriteResult> future2 = docRef.update("like",likeCount);
            return true;
        }
    }

    @Override
    public List<HashMap<String, Object>> viewAllArchive() throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        Query query = firestore.collection("Archive")
                .orderBy("createdDate", Query.Direction.ASCENDING);
        QuerySnapshot queryDocumentSnapshots = query.get().get();
        List<HashMap<String,Object>> archiveList = new ArrayList<>();
        if(queryDocumentSnapshots.size() != 0){
            for(Archive archive : queryDocumentSnapshots.toObjects(Archive.class)){
                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("title", archive.getTitle());
                hashMap.put("clubName", getClubName(archive.getClubId()));
                hashMap.put("pictures", archive.getPictureUrls());
                hashMap.put("contents", archive.getContents());
                hashMap.put("likeCount", archive.getLike());
                hashMap.put("time", archive.getCreatedDate());
                archiveList.add(hashMap);
            }
        }
        return archiveList;
    }
}
