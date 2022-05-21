package CHOIGANGMEDIA.CAUCLUB.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter

public class Member {

    private String id;
    private String password;
    private String name;
    private String email;
    private String department;
    private ArrayList<Integer> joinedClub;
    private ArrayList<Integer> managingClub;
    private ArrayList<String> keyword;

}
