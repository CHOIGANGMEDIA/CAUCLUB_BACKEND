package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Archive;
import CHOIGANGMEDIA.CAUCLUB.domain.Club;
import CHOIGANGMEDIA.CAUCLUB.domain.Post;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Objects;

@Repository
public class MemoryReportRepository implements ReportRepository {

    private final ArchiveRepository archiveRepository;
    private final PostRepository postRepository;

    public MemoryReportRepository(ArchiveRepository archiveRepository, PostRepository postRepository) {
        this.archiveRepository = archiveRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Boolean reportArchive(String memberId, int archiveId, int clubId) throws Exception {
        ArrayList<String> reportMemberList = new ArrayList<>();
        int reportCount;
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("Archive").document(String.valueOf(archiveId));
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        reportMemberList = Objects.requireNonNull(document.toObject(Archive.class)).getReportMemberList();
        reportCount = Objects.requireNonNull(document.toObject(Archive.class)).getReportCount();
        if(reportMemberList.contains(memberId)){
            return false;
        }
        else{
            reportMemberList.add(memberId);
            reportCount += 1;
            if(reportCount>=3){
                archiveRepository.deleteArchive(archiveId);
                System.out.println("신고 횟수가 3회를 달성해 아카이브가 삭제됩니다.");
            }
            ApiFuture<WriteResult> future1 = documentReference.update("reportMemberList",reportMemberList);
            ApiFuture<WriteResult> future2 = documentReference.update("reportCount",reportCount);
            reportClub(clubId);
            return true;
        }
    }

    @Override
    public Boolean reportPost(String memberId, int postId, int clubId) throws Exception {
        ArrayList<String> reportMemberList = new ArrayList<>();
        int reportCount;
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("Post").document(String.valueOf(postId));
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        reportMemberList = Objects.requireNonNull(document.toObject(Post.class)).getReportMemberList();
        reportCount = Objects.requireNonNull(document.toObject(Post.class)).getReportCount();
        if(reportMemberList.contains(memberId)){
            return false;
        }
        else{
            reportMemberList.add(memberId);
            reportCount += 1;
            if(reportCount>=3){
                postRepository.deletePost(postId);
                System.out.println("신고 횟수가 3회를 달성해 게시판이 삭제됩니다.");
            }
            ApiFuture<WriteResult> future1 = documentReference.update("reportMemberList",reportMemberList);
            ApiFuture<WriteResult> future2 = documentReference.update("reportCount",reportCount);
            reportClub(clubId);
            return true;
        }
    }

    @Override
    public void reportClub(int clubId) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("Club").document(String.valueOf(clubId));
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        int reportCount = Objects.requireNonNull(document.toObject(Club.class)).getReportCount();
        reportCount += 1;
        if(reportCount >= 5){
            int clubScore = Objects.requireNonNull(document.toObject(Club.class)).getScore();
            clubScore -= 30;
            ApiFuture<WriteResult> future1 = documentReference.update("score",clubScore);
            ApiFuture<WriteResult> future2 = documentReference.update("reportCount",0);
            System.out.println("동아리 신고 횟수가 5회를 달성해 동아리 점수 30점을 차감합니다. 신고횟수는 다시 0회로 리셋됩니다.");
        }
        else{
            ApiFuture<WriteResult> future1 = documentReference.update("reportCount",reportCount);
        }
    }
}
