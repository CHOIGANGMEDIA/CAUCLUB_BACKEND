package CHOIGANGMEDIA.CAUCLUB.controller;

import CHOIGANGMEDIA.CAUCLUB.domain.Club;
import CHOIGANGMEDIA.CAUCLUB.service.ClubService;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    * 8. 해당 id가 동아리에서 갖고있는 권한
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
        ArrayList<String> memberList = new ArrayList<>();
        club.setClubId(clubService.setClubPk());
        club.setDepartment(department);
        club.setIntroduction(introduction);
        club.setKeyword(keyword);
        club.setLeaderId(memberId);
        club.setName(name);
        club.setPicture(picture);
        club.setScore(0);
        club.setType(type);
        club.setMembers(memberList);
        club.setReportCount(0);
        System.out.println("새로운 동아리가 생성되었습니다.");
        clubService.registerNewClub(club, memberId);
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/club", method = RequestMethod.GET)
    public ArrayList<Integer> viewDepartmentAllClubList(@PathVariable String memberId) throws Exception{
        String department = clubService.getDepartmentByMemberId(memberId);
        return clubService.getDepartmentAllCLubList(memberId,department);
    }

    @ResponseBody
    @RequestMapping(value = "/club/{clubId}", method = RequestMethod.GET)
    public HashMap<String, Object> viewDetailClub(@PathVariable int clubId) throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        Club club = clubService.getClubObject(clubId);
        map.put("picture", club.getPicture());
        map.put("name", club.getName());
        map.put("department", club.getDepartment());
        map.put("introduction", club.getIntroduction());
        map.put("leaderId", club.getLeaderId());
        map.put("score", club.getScore());
        if(club.getType()==1){
            map.put("type", "학술동아리");
        }
        else if(club.getType()==2){
            map.put("type", "예체능동아리");
        }
        else{
            map.put("type", "기타동아리");
        }
        map.put("keyword", club.getKeyword());
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/{clubId}", method = RequestMethod.PATCH)
    public boolean modifyClubInformation(@PathVariable String memberId, @PathVariable int clubId, @RequestParam String name,
                                         @RequestParam String introduction, @RequestParam String leaderId, @RequestParam String picture,
                                         @RequestParam ArrayList<String> keyword) throws Exception{
        clubService.modifyClubInformation(picture, leaderId, name, introduction, clubId, keyword);
        clubService.changeLeader(memberId, clubId, leaderId);
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
        List<Integer> managing = clubService.viewManagingClub(memberId);
        managing.addAll(clubService.viewJoinedClub(memberId));
        return managing;
    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/club/clubRecommend", method = RequestMethod.GET)
    public List<Integer> recommendClub(@PathVariable("memberId") String memberId) throws Exception{
        List<Club> clubList = new ArrayList<>();
        List<Integer> clubIdList = new ArrayList<>();
        clubList = clubService.showRecommendList(memberId);
        int clubIndex = 0;
        while(clubIdList.size()<5){
            if(clubIdList.contains(clubList.get(clubIndex).getClubId())){
                clubIndex += 1;
            }
            else{
                clubIdList.add(clubList.get(clubIndex).getClubId());
            }
            if(clubIndex==clubList.size()-1){
                break;
            }
        }
        return clubIdList;
    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/{clubId}/enterClub", method = RequestMethod.POST)
    public boolean enterClub(@PathVariable("memberId") String memberId, @PathVariable("clubId") int clubId) throws Exception{
        System.out.println("동아리 가입이 완료되었습니다.");
        return clubService.enterClub(memberId,clubId);
    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/{clubId}/resignClub", method = RequestMethod.POST)
    public boolean resignClub(@PathVariable("memberId") String memberId, @PathVariable("clubId") int clubId) throws Exception{
        System.out.println("동아리 탈퇴가 완료되었습니다.");
        return clubService.resignClub(memberId, clubId);
    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/{clubId}/changeLeaderId", method = RequestMethod.PATCH)
    public boolean changeLeaderId(@PathVariable("memberId") String memberId, @PathVariable("clubId") int clubId, @RequestParam String newLeaderId) throws Exception{
        System.out.println("동아리 회장이 변경되었습니다.");
        return clubService.changeLeader(memberId, clubId, newLeaderId);
    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/{clubId}/enterValid", method = RequestMethod.GET)
    public int getValidInformationOfEnter(@PathVariable("memberId") String memberId, @PathVariable("clubId") int clubId) throws Exception{
        return clubService.getInformationOfEnter(memberId, clubId);
    }

}
