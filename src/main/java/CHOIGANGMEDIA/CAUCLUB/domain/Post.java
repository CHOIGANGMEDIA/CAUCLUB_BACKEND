package CHOIGANGMEDIA.CAUCLUB.domain;


public class Post {
    private int clubId;
    public static int staticPostId = 0;
    private int postId;
    private String contents;
    private String title;
    private String createdDate;
    private String modifiedDate;

    public Post(){
        staticPostId++;
        postId = staticPostId;
    }

    public int getClubId() {
        return clubId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

}
