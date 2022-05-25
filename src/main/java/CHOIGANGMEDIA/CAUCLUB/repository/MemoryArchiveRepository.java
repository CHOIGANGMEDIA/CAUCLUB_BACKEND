package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Archive;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

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
}
