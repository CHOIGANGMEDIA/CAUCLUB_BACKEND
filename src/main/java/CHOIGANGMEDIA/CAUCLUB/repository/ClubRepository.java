package CHOIGANGMEDIA.CAUCLUB.repository;

import java.util.ArrayList;
import java.util.List;

public interface ClubRepository {
    List<Integer> viewManagingClub(String memberId) throws Exception;
}
