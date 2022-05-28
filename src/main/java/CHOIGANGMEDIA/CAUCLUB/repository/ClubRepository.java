package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Club;

import java.util.ArrayList;
import java.util.List;

public interface ClubRepository {
    List<Integer> viewManagingClub(String memberId) throws Exception;
    List<Integer> viewJoinedClub(String memberId) throws Exception;
    String registerNewClub(Club club) throws Exception;
    Boolean deleteClub(int clubId) throws Exception;
    Club getClubObject(int clubId) throws Exception;
    String getDepartmentByMemberId(String memberId) throws Exception;
    ArrayList<String> viewDepartmentClubList(String memberId, String department) throws Exception;
    Boolean modifyClubInformation(String picture, String leaderId, String name, int type, String introduction, int clubId) throws Exception;
    List<Club> recommendClubList(String memberId) throws Exception;
}
