package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Member;

import java.util.ArrayList;

public interface MyPageRepository {
    Member findById(String id) throws Exception;
    Boolean modifyMyInformation(String id, String name, String email, ArrayList<String> keyword) throws Exception;
}
