package CHOIGANGMEDIA.CAUCLUB.controller;

import CHOIGANGMEDIA.CAUCLUB.service.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    /*
    * 1. 자기가 회장인 동아리 다 뜨게
    * 2. 동아리 생성
    * 3. 자기 과의 동아리 다 뜨게
    * 4. 상세 동아리 조회
    * 5. 동아리 정보 수정
    * 6. 동아리 삭제
    * 7. 자신이 속한 동아리 다 뜨게
    * */

    @ResponseBody
    @RequestMapping(value = "/{memberId}/managingClub", method = RequestMethod.GET)
    public List<Integer> viewManagingClub(@PathVariable String memberId) throws Exception {
        return clubService.viewManagingClub(memberId);
    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/newClub", method = RequestMethod.POST)
    public void api2(){

    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/club", method = RequestMethod.GET)
    public void api3(){

    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/{clubId}", method = RequestMethod.GET)
    public void api4(){

    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/{clubId}", method = RequestMethod.PATCH)
    public void api5(){

    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/{clubId}", method = RequestMethod.DELETE)
    public void api6(){

    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/joinedClub", method = RequestMethod.GET)
    public List<Integer> viewJoinedClub(@PathVariable String memberId) throws Exception {
        return clubService.viewJoinedClub(memberId);
    }
}
