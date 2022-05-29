package CHOIGANGMEDIA.CAUCLUB.service;

import CHOIGANGMEDIA.CAUCLUB.domain.Member;
import CHOIGANGMEDIA.CAUCLUB.repository.MyPageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyPageService {
    private final MyPageRepository myPageRepository;

    public MyPageService(MyPageRepository myPageRepository) {
        this.myPageRepository = myPageRepository;
    }

    public Member getMemberObject(String id) throws Exception{
        return myPageRepository.findById(id);
    }

    public boolean modifyMyInformation(String id, String name, String email, ArrayList<String> keyword) throws Exception{
        myPageRepository.modifyMyInformation(id,name,email,keyword);
        return true;
    }
}
