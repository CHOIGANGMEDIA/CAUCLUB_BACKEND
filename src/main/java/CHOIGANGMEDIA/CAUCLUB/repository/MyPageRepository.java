package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Member;

public interface MyPageRepository {
    public Member findById(String id) throws Exception;
    public boolean modifyMyInformation(String id, String name, String email) throws Exception;
}
