package CHOIGANGMEDIA.CAUCLUB.repository;

import java.util.ArrayList;

public interface RankRepository {
    ArrayList<String> showTotalRank() throws Exception;
    ArrayList<String> showStudyRank() throws Exception;
    ArrayList<String> showArtClub() throws Exception;
    ArrayList<String> showEtcClub() throws Exception;
}
