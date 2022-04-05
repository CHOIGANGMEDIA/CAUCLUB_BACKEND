package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Club;

public interface LoginRepository {
    Boolean idDuplicateCheck(String id) throws Exception;
    String registerClub(Club club) throws Exception;
    Boolean loginCheck(String id, String password) throws Exception;
}