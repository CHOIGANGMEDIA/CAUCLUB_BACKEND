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

    public String registerNewClub(Club club, String memberId) throws Exception{
        return clubRepository.registerNewClub(club, memberId);
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

    public ArrayList<Integer> getDepartmentAllCLubList(String memberId, String department) throws Exception{
        return clubRepository.viewDepartmentClubList(memberId,department);
    }

    public boolean modifyClubInformation(String picture, String leaderId, String name, int type, String introduction, int clubId, ArrayList<String> keyword) throws Exception{
        return clubRepository.modifyClubInformation(picture, leaderId, name, type, introduction, clubId, keyword);
    }

    public List<Club> showRecommendList(String memberId) throws Exception{
        return(clubRepository.recommendClubList(memberId));
    }

    public int setClubPk() throws Exception{
        return clubRepository.setClubPk();
    }

    public boolean enterClub(String memberId, int clubId) throws Exception{
        return clubRepository.enterClub(memberId, clubId);
    }

    public boolean resignClub(String memberId, int clubId) throws Exception{
        return clubRepository.resignClub(memberId, clubId);
    }

    public boolean changeLeader(String memberId, int clubId, String newLeaderId) throws Exception{
        return clubRepository.changeLeader(memberId, clubId, newLeaderId);
    }
}
