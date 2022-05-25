package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Archive;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

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
}
