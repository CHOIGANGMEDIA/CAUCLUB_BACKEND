package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Club;

import java.util.ArrayList;
import java.util.List;

public interface ClubRepository {
    List<Integer> viewManagingClub(String memberId) throws Exception;
    List<Integer> viewJoinedClub(String memberId) throws Exception;
    String registerNewClub(Club club, String memberId) throws Exception;
    Boolean deleteClub(int clubId) throws Exception;
    Club getClubObject(int clubId) throws Exception;
    String getDepartmentByMemberId(String memberId) throws Exception;
    ArrayList<Integer> viewDepartmentClubList(String memberId, String department) throws Exception;
    Boolean modifyClubInformation(String picture, String leaderId, String name, String introduction, int clubId, ArrayList<String> keyword) throws Exception;
    List<Club> recommendClubList(String memberId) throws Exception;
    int setClubPk() throws Exception;
    void plusClubPk() throws Exception;
    Boolean enterClub(String memberId, int clubId) throws Exception;
    Boolean resignClub(String memberId, int clubId) throws Exception;
    Boolean changeLeader(String memberId, int clubId, String newLeaderId) throws Exception;
    int getInformationOfEnter(String memberId, int clubId) throws Exception;
    boolean validLeader(String memberId, int clubId) throws Exception;
}
