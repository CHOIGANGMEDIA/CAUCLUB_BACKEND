package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Club;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryRankRepository implements RankRepository{
    @Override
    public ArrayList<String> showTotalRank() throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        Query clubQuery = firestore.collection("Club")
                .orderBy("score", Query.Direction.DESCENDING);
        List<Club> totalClubList = clubQuery.get().get().toObjects(Club.class);
        ArrayList<String> totalClubRank = new ArrayList<>();
        for(Club club : totalClubList){
            totalClubRank.add(club.getName());
        }
        return totalClubRank;
    }

    @Override
    public ArrayList<String> showStudyRank() throws Exception {
        return null;
    }

    @Override
    public ArrayList<String> showArtClub() throws Exception {
        return null;
    }

    @Override
    public ArrayList<String> showEtcClub() throws Exception {
        return null;
    }
}
