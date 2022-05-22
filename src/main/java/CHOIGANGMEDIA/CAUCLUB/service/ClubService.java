package CHOIGANGMEDIA.CAUCLUB.service;

import CHOIGANGMEDIA.CAUCLUB.domain.Club;
import CHOIGANGMEDIA.CAUCLUB.repository.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {

    private final ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public List<Integer> viewManagingClub(String memberId) throws Exception {
        return clubRepository.viewManagingClub(memberId);
    }

    public List<Integer> viewJoinedClub(String memberId) throws Exception {
        return clubRepository.viewJoinedClub(memberId);
    }

    public String registerNewClub(Club club) throws Exception{
        return clubRepository.registerNewClub(club);
    }
}
