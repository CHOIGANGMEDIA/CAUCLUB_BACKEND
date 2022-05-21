package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Post;

import java.util.HashMap;
import java.util.List;

public interface PostRepository {
    String registerNewPost(Post post) throws Exception;
    int getClubId(String memberId) throws Exception;
    Boolean deletePost(String postId) throws Exception;
    String getClubName(int clubId) throws Exception;
    Post getPostObject(int postId) throws Exception;
    Boolean modifyPost(String title, String contents, int postId) throws Exception;
    List<HashMap<String,Object>> viewAllPost() throws Exception;

}
