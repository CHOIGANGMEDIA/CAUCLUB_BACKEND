package CHOIGANGMEDIA.CAUCLUB.service;

import CHOIGANGMEDIA.CAUCLUB.repository.RankRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RankService {

    private final RankRepository rankRepository;

    public RankService(RankRepository rankRepository) {
        this.rankRepository = rankRepository;
    }

    public ArrayList<Number> showTotalRank() throws Exception{
        return rankRepository.showTotalRank();
    }

    public ArrayList<Number> showStudyRank() throws Exception{
        return rankRepository.showStudyRank();
    }

    public ArrayList<Number> showArtRank() throws Exception{
        return rankRepository.showArtClub();
    }

    public ArrayList<Number> showEtcRank() throws Exception{
        return rankRepository.showEtcClub();
    }
}
