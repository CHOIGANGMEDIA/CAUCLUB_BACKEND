package CHOIGANGMEDIA.CAUCLUB.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Archive {

    private int archiveId;
    private int clubId;
    private String contents;
    private String createdDate;
    private String modifiedDate;
    private String title;
    private int like;
    private ArrayList<String> pictureUrls;
    private ArrayList<String> likeMember;
    private int reportCount;
    private ArrayList<String> reportMemberList;
    private int isMutual;
}
