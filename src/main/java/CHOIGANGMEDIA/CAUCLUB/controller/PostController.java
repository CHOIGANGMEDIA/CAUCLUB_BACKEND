package CHOIGANGMEDIA.CAUCLUB.controller;

import CHOIGANGMEDIA.CAUCLUB.domain.Member;
import CHOIGANGMEDIA.CAUCLUB.domain.Post;
import CHOIGANGMEDIA.CAUCLUB.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ResponseBody
    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public List<HashMap<String,Object>> viewAllPost() throws Exception{
        return postService.viewAllPost();
    }

    @ResponseBody
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public boolean registerNewPost(@RequestParam String contents, @RequestParam String title, HttpServletRequest request) throws Exception{
        Post post = new Post();
        Date now = new Date();
        String createDate = now.toString();
        HttpSession session = request.getSession();
        String memberId = (String) session.getAttribute("member");
        String clubId = postService.getClubIdByMemberId(memberId);

        post.setCreatedDate(createDate);
        post.setModifiedDate(null);
        post.setContents(contents);
        post.setTitle(title);
        post.setClubId(clubId);

        postService.registerNewPost(post);
        System.out.println("게시글이 등록되었습니다.");

        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/post/{postId}", method = RequestMethod.DELETE)
    public boolean deletePost(@PathVariable("postId") String postId) throws Exception{
        postService.deletePost(postId);
        System.out.println("게시글이 삭제되었습니다.");
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
    public HashMap<String, Object> viewDetailPost(@PathVariable("postId") int postId) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        Post post = postService.getPostObject(postId);
        String clubName = postService.getClubName(post.getClubId());
        map.put("title", post.getTitle());
        map.put("contents", post.getContents());
        if(post.getModifiedDate()==null){
            map.put("time", post.getCreatedDate());
        }
        else{
            map.put("time", post.getModifiedDate());
        }
        map.put("clubName", clubName);
        return map;
    }


    @ResponseBody
    @RequestMapping(value = "/post/{postId}", method = RequestMethod.PATCH)
    public boolean modifyPost(@PathVariable("postId") int postId, @RequestParam String title, @RequestParam String contents) throws Exception{
        postService.modifyPost(title,contents,postId);
        System.out.println("게시글이 변경되었습니다.");
        return true;
    }

}
