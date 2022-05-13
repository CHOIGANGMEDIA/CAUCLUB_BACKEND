package CHOIGANGMEDIA.CAUCLUB.controller;

import CHOIGANGMEDIA.CAUCLUB.domain.Member;
import CHOIGANGMEDIA.CAUCLUB.service.MyPageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
public class MyPageController {

    private final MyPageService myPageService;

    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @ResponseBody
    @RequestMapping(value = "/member/{memberId}", method = RequestMethod.GET)
    public HashMap<String, Object> viewMyPage(@PathVariable("memberId") String id) throws Exception {
        Member member = myPageService.getMemberObject(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", member.getName());
        map.put("email", member.getEmail());
        map.put("department", member.getDepartment());
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/member/{memberId}", method = RequestMethod.POST)
    public boolean modifyMyPage(@PathVariable("memberId") String id, @RequestParam String name, @RequestParam String email) throws Exception{
        myPageService.modifyMyInformation(id,name,email);
        return true;
    }

}
