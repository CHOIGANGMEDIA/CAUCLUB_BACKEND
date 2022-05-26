package CHOIGANGMEDIA.CAUCLUB.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Archive {

    private int archiveId;
    private static int staticArchiveId = 0;
    private int clubId;
    private String contents;
    private String createdDate;
    private String modifiedDate;
    private String title;
    private int like;
    private ArrayList<String> pictureUrls;

    public ArrayList<String> getLikeMember() {
        return likeMember;
    }

    public void setLikeMember(ArrayList<String> likeMember) {
        this.likeMember = likeMember;
    }

    private ArrayList<String> likeMember;

    public Archive(){
        staticArchiveId++;
        archiveId = staticArchiveId;
    }

    public int getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(int archiveId) {
        this.archiveId = archiveId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public ArrayList<String> getPictureUrls() {
        return pictureUrls;
    }

    public void setPictureUrls(ArrayList<String> pictureUrls) {
        this.pictureUrls = pictureUrls;
    }
}
