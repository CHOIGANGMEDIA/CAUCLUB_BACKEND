package CHOIGANGMEDIA.CAUCLUB.service;

import CHOIGANGMEDIA.CAUCLUB.domain.Post;
import CHOIGANGMEDIA.CAUCLUB.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void registerNewPost(Post post) throws Exception{
        postRepository.registerNewPost(post);
    }

    public int getClubIdByMemberId(String memberId) throws Exception{
        return postRepository.getClubId(memberId);
    }

    public boolean deletePost(String postId) throws Exception {
        return postRepository.deletePost(postId);
    }

    public Post getPostObject(int postId) throws Exception{
        return postRepository.getPostObject(postId);
    }

    public String getClubName(int clubId) throws Exception{
        return postRepository.getClubName(clubId);
    }

    public boolean modifyPost(String title, String contents, int postId) throws Exception{
        return postRepository.modifyPost(title,contents,postId);
    }

    public List<HashMap<String, Object>> viewAllPost() throws Exception{
        return postRepository.viewAllPost();
    }

}
