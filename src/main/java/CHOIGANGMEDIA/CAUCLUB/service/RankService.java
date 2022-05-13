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

    public ArrayList<String> showTotalRank() throws Exception{
        return rankRepository.showTotalRank();
    }
}
