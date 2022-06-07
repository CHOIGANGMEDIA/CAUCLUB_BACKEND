package CHOIGANGMEDIA.CAUCLUB.repository;

public interface FindRepository {
    Boolean emailCheck(String email) throws Exception;
    Boolean resetPassword(String id, String password, String salt) throws Exception;
    String getIdByEmail(String email) throws Exception;
    Boolean resetPasswordByEmail(String email, String password) throws Exception;
}
