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
    public ArrayList<Number> showTotalRank() throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        Query clubQuery = firestore.collection("Club")
                .orderBy("score", Query.Direction.DESCENDING);
        List<Club> totalClubList = clubQuery.get().get().toObjects(Club.class);
        ArrayList<Number> totalClubRank = new ArrayList<>();
        for(Club club : totalClubList){
            totalClubRank.add(club.getClubId());
        }
        return totalClubRank;
    }

    @Override
    public ArrayList<Number> showStudyRank() throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        Query clubQuery = firestore.collection("Club")
                .orderBy("score", Query.Direction.DESCENDING);
        List<Club> totalClubList = clubQuery.get().get().toObjects(Club.class);
        ArrayList<Number> studyClubRank = new ArrayList<>();
        for(Club club : totalClubList){
            if(club.getType()==1){
                studyClubRank.add(club.getClubId());
            }
        }
        return studyClubRank;
    }

    @Override
    public ArrayList<Number> showArtClub() throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        Query clubQuery = firestore.collection("Club")
                .orderBy("score", Query.Direction.DESCENDING);
        List<Club> totalClubList = clubQuery.get().get().toObjects(Club.class);
        ArrayList<Number> artClubRank = new ArrayList<>();
        for(Club club : totalClubList){
            if(club.getType()==2){
                artClubRank.add(club.getClubId());
            }
        }
        return artClubRank;
    }

    @Override
    public ArrayList<Number> showEtcClub() throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        Query clubQuery = firestore.collection("Club")
                .orderBy("score", Query.Direction.DESCENDING);
        List<Club> totalClubList = clubQuery.get().get().toObjects(Club.class);
        ArrayList<Number> etcClubRank = new ArrayList<>();
        for(Club club : totalClubList){
            if(club.getType()==3){
                etcClubRank.add(club.getClubId());
            }
        }
        return etcClubRank;
    }
}
