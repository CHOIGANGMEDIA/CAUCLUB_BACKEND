package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Club;

import java.util.ArrayList;
import java.util.List;

public interface ClubRepository {
    List<Integer> viewManagingClub(String memberId) throws Exception;
    List<Integer> viewJoinedClub(String memberId) throws Exception;
    String registerNewClub(Club club) throws Exception;
    Boolean deleteClub(int clubId) throws Exception;
}
