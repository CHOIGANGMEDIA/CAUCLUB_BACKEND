package CHOIGANGMEDIA.CAUCLUB.service;

import CHOIGANGMEDIA.CAUCLUB.repository.FindRepository;
import org.springframework.stereotype.Service;

@Service
public class FindService {

    private final FindRepository findRepository;

    public FindService(FindRepository findRepository) {
        this.findRepository = findRepository;
    }

    public boolean emailCheckService(String email) throws Exception{
        if(findRepository.emailCheck(email)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean resetPasswordService(String id, String password) throws Exception{
        if(findRepository.resetPassword(id,password)){
            return true;
        }
        else{
            return false;
        }
    }

    public String getIdByEmailService(String email) throws Exception{
        return findRepository.getIdByEmail(email);
    }

    public boolean resetPasswordByEmailService(String email, String password) throws Exception{
        if(findRepository.resetPasswordByEmail(email,password)){
            return true;
        }
        else{
            return false;
        }
    }
}
