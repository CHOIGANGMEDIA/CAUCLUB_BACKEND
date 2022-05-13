package CHOIGANGMEDIA.CAUCLUB.repository;

import java.util.ArrayList;

public interface MainRepository {
    public ArrayList<String> showJoinedClub(String id) throws Exception;
    public ArrayList<String> showManagingClub(String id) throws Exception;
}
