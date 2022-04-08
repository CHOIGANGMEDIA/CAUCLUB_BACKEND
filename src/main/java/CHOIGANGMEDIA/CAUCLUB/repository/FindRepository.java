package CHOIGANGMEDIA.CAUCLUB.repository;

public interface FindRepository {
    Boolean emailCheck(String email) throws Exception;
    Boolean resetPassword(String id, String password) throws Exception;
}
