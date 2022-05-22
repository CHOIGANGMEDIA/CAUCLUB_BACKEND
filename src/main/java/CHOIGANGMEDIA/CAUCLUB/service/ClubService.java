package CHOIGANGMEDIA.CAUCLUB.service;

import CHOIGANGMEDIA.CAUCLUB.domain.Club;
import CHOIGANGMEDIA.CAUCLUB.repository.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Boolean deleteClub(int clubId) throws Exception{
        return clubRepository.deleteClub(clubId);
    }

    public Club getClubObject(int clubId) throws Exception{
        return clubRepository.getClubObject(clubId);
    }

    public String getDepartmentByMemberId(String memberId) throws Exception{
        return clubRepository.getDepartmentByMemberId(memberId);
    }

    public ArrayList<String> getDepartmentAllCLubList(String memberId, String department) throws Exception{
        return clubRepository.viewDepartmentClubList(memberId,department);
    }
}
