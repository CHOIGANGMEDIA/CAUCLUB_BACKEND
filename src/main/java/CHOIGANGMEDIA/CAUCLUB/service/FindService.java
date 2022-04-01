package CHOIGANGMEDIA.CAUCLUB.service;

import CHOIGANGMEDIA.CAUCLUB.repository.FindRepository;

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
}
