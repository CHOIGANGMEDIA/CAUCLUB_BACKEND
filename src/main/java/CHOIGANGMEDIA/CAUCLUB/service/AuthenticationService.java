package CHOIGANGMEDIA.CAUCLUB.service;

import java.util.Random;

public class AuthenticationService {
    public String generateNumber(){
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d",number);
    }
}
