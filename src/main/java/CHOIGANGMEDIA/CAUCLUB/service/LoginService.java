package CHOIGANGMEDIA.CAUCLUB.service;

import CHOIGANGMEDIA.CAUCLUB.domain.Club;
import CHOIGANGMEDIA.CAUCLUB.repository.LoginRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    /**
     * 회원가입 서비스
     */

    public void registerNewClub(Club club) throws Exception{
        loginRepository.registerClub(club);
    }

    /**
     * id 중복체크 확인 서비스
     */

    public boolean idDuplicateCheckService(String id) throws Exception{
        if(loginRepository.idDuplicateCheck(id)){
            return true;
        }
        else{
            return false;
        }
    }
}