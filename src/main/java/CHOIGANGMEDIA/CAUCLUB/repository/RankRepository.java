package CHOIGANGMEDIA.CAUCLUB.repository;

import java.util.ArrayList;

public interface RankRepository {
    ArrayList<Number> showTotalRank() throws Exception;
    ArrayList<Number> showStudyRank() throws Exception;
    ArrayList<Number> showArtClub() throws Exception;
    ArrayList<Number> showEtcClub() throws Exception;
}
