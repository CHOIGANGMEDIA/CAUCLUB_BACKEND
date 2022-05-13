package CHOIGANGMEDIA.CAUCLUB.service;

import CHOIGANGMEDIA.CAUCLUB.repository.MainRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MainService {

    private final MainRepository mainRepository;

    public MainService(MainRepository mainRepository) {
        this.mainRepository = mainRepository;

    }

    public ArrayList<String> showJoinedClub(String id) throws Exception{
        return mainRepository.showJoinedClub(id);
    }

    public ArrayList<String> showManagingClub(String id) throws Exception{
        return mainRepository.showManagingClub(id);
    }
}
