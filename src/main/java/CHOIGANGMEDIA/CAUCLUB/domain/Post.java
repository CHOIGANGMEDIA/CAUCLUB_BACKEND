package CHOIGANGMEDIA.CAUCLUB.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Post {
    private int clubId;
    private int postId;
    private String contents;
    private String title;
    private String createdDate;
    private String modifiedDate;
    private int reportCount;
    private ArrayList<String> reportMemberList;
}
