package CHOIGANGMEDIA.CAUCLUB.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthenticationService {
    public String generateNumber(){
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d",number);
    }
}
