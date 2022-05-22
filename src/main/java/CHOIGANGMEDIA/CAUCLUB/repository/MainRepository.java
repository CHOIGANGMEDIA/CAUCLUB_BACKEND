package CHOIGANGMEDIA.CAUCLUB.repository;

import java.util.ArrayList;

public interface MainRepository {
    public ArrayList<Integer> showJoinedClub(String id) throws Exception;
    public ArrayList<Integer> showManagingClub(String id) throws Exception;
}
