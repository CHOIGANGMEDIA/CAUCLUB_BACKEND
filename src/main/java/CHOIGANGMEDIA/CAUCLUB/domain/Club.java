package CHOIGANGMEDIA.CAUCLUB.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Club {

    private int clubId;
    private String leaderId;
    private String department;
    private String introduction;
    private String name;
    private String picture;
    private int type;
    private int score;
    private ArrayList<String> keyword;
    private ArrayList<String> members;
    private int reportCount;
}