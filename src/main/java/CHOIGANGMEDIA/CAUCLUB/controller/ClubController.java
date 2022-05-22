package CHOIGANGMEDIA.CAUCLUB.controller;

import CHOIGANGMEDIA.CAUCLUB.domain.Club;
import CHOIGANGMEDIA.CAUCLUB.service.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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
    public boolean registerNewClub(@PathVariable String memberId, @RequestParam String name, @RequestParam String department, @RequestParam String introduction,
                                   @RequestParam ArrayList<String> keyword, @RequestParam String picture, @RequestParam int type) throws Exception {
        Club club = new Club();
        club.setDepartment(department);
        club.setIntroduction(introduction);
        club.setKeyword(keyword);
        club.setLeaderId(memberId);
        club.setName(name);
        club.setPicture(picture);
        club.setScore(0);
        club.setType(type);
        System.out.println("새로운 동아리가 생성되었습니다.");
        clubService.registerNewClub(club);
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/club", method = RequestMethod.GET)
    public ArrayList<String> viewDepartmentAllClubList(@PathVariable String memberId) throws Exception{
        String department = clubService.getDepartmentByMemberId(memberId);
        return clubService.getDepartmentAllCLubList(memberId,department);
    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/{clubId}", method = RequestMethod.GET)
    public HashMap<String, Object> viewDetailClub(@PathVariable String memberId, @PathVariable int clubId) throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        Club club = clubService.getClubObject(clubId);
        map.put("picture", club.getPicture());
        map.put("name", club.getName());
        map.put("department", club.getDepartment());
        map.put("introduction", club.getIntroduction());
        map.put("leaderId", club.getLeaderId());
        if(club.getType()==1){
            map.put("type", "학술동아리");
        }
        else if(club.getType()==2){
            map.put("type", "예체능동아리");
        }
        else{
            map.put("type", "기타동아리");
        }
        map.put("keyword", null);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/{clubId}", method = RequestMethod.PATCH)
    public boolean modifyClubInformation(@PathVariable String memberId, @PathVariable int clubId, @RequestParam String name,
                                         @RequestParam String introduction, @RequestParam int type, @RequestParam String leaderId, @RequestParam String picture) throws Exception{
        clubService.modifyClubInformation(picture, leaderId, name, type, introduction, clubId);
        System.out.println("동아리 정보가 수정되었습니다.");
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/{clubId}", method = RequestMethod.DELETE)
    public boolean deleteClub(@PathVariable String memberId, @PathVariable int clubId) throws Exception{
        System.out.println("동아리가 삭제되었습니다.");
        return clubService.deleteClub(clubId);
    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/joinedClub", method = RequestMethod.GET)
    public List<Integer> viewJoinedClub(@PathVariable String memberId) throws Exception {
        return clubService.viewJoinedClub(memberId);
    }
}
