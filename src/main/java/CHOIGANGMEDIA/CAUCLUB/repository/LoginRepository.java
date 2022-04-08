package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Club;
import CHOIGANGMEDIA.CAUCLUB.domain.Member;

public interface LoginRepository {
    Boolean idDuplicateCheck(String id) throws Exception;
    String registerMember(Member member) throws Exception;
    Boolean loginCheck(String id, String password) throws Exception;
}