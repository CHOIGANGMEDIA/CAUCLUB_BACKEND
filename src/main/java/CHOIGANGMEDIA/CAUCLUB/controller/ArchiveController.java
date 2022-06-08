package CHOIGANGMEDIA.CAUCLUB.controller;

import CHOIGANGMEDIA.CAUCLUB.domain.Archive;
import CHOIGANGMEDIA.CAUCLUB.service.ArchiveService;
import com.google.firebase.database.utilities.Pair;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class ArchiveController {

    private final ArchiveService archiveService;

    public ArchiveController(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

//    @ResponseBody
//    @RequestMapping(value = "/{memberId}/{clubId}/newArchive", method = RequestMethod.POST)
//    public boolean registerNewArchive(@PathVariable int clubId, @RequestParam String contents,
//                                      @RequestParam ArrayList<String> pictureUrls, @RequestParam String title, @RequestParam int isMutual) throws Exception{
//
//        Archive archive = new Archive();
//        Date now = new Date();
//        ArrayList<String> likeMemberList = new ArrayList<>();
//        ArrayList<String> reportMemberList = new ArrayList<>();
//
//        String createDate = now.toString();
//        archive.setArchiveId(archiveService.setArchivePk());
//        archive.setCreatedDate(createDate);
//        archive.setContents(contents);
//        archive.setClubId(clubId);
//        archive.setTitle(title);
//        archive.setModifiedDate(null);
//        archive.setPictureUrls(pictureUrls);
//        archive.setLike(0);
//        archive.setLikeMember(likeMemberList);
//        archive.setReportCount(0);
//        archive.setReportMemberList(reportMemberList);
//        archive.setIsMutual(isMutual);
//        archiveService.registerNewArchive(archive);
//        archiveService.getScoreByArchive(clubId, isMutual);
//        System.out.println("아카이브 생성이 완료되었습니다.");
//        return true;
//    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/{clubId}/newArchive", method = RequestMethod.POST)
    public boolean registerNewArchive(@PathVariable int clubId, @RequestBody String archiveInformation) throws Exception{

        Archive archive = new Archive();
        Date now = new Date();
        ArrayList<String> likeMemberList = new ArrayList<>();
        ArrayList<String> reportMemberList = new ArrayList<>();
        ArrayList<String> pictureUrlList = new ArrayList<>();
        String createDate = now.toString();

        JsonParser jsonParser = new JsonParser();
        Object objects = jsonParser.parse(archiveInformation);
        JsonObject jsonObject = (JsonObject) objects;

        String contents = jsonObject.get("contents").toString();
        contents = contents.substring(1,contents.length()-1);
        String title = jsonObject.get("title").toString();
        title = title.substring(1,title.length()-1);
        String isMutual = jsonObject.get("isMutual").toString();
        int tempIsMutual = Integer.parseInt(isMutual);


        String pictureUrls = jsonObject.get("pictures").toString();
        String[] info = pictureUrls.substring(1, pictureUrls.length()-1).split(",");
        for(int i=0;i<info.length;i++){
            String tempUrls = info[i].substring(1,info[i].length()-1);
            pictureUrlList.add(tempUrls);
        }
        archive.setArchiveId(archiveService.setArchivePk());
        archive.setCreatedDate(createDate);
        archive.setContents(contents);
        archive.setClubId(clubId);
        archive.setTitle(title);
        archive.setModifiedDate(null);
        archive.setPictureUrls(pictureUrlList);
        archive.setLike(0);
        archive.setLikeMember(likeMemberList);
        archive.setReportCount(0);
        archive.setReportMemberList(reportMemberList);
        archive.setIsMutual(tempIsMutual);
        archiveService.registerNewArchive(archive);
        archiveService.getScoreByArchive(clubId, tempIsMutual);
        System.out.println("아카이브 생성이 완료되었습니다.");
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/archive/{archiveId}", method = RequestMethod.DELETE)
    public boolean deleteArchive(@PathVariable("archiveId") int archiveId) throws Exception{
        archiveService.deleteArchive(archiveId);
        System.out.println("아카이브가 삭제되었습니다.");
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/archive/{archiveId}", method = RequestMethod.PATCH)
    public boolean modifyArchive(@PathVariable("archiveId") int archiveId, @RequestBody String archiveInformation) throws Exception {

        ArrayList<String> pictureUrlList = new ArrayList<>();

        JsonParser jsonParser = new JsonParser();
        Object objects = jsonParser.parse(archiveInformation);
        JsonObject jsonObject = (JsonObject) objects;

        String contents = jsonObject.get("contents").toString();
        contents = contents.substring(1,contents.length()-1);
        String title = jsonObject.get("title").toString();
        title = title.substring(1,title.length()-1);
        String isMutual = jsonObject.get("isMutual").toString();
        int tempIsMutual = Integer.parseInt(isMutual);

        String pictureUrls = jsonObject.get("pictures").toString();
        Object obj1 = jsonParser.parse(pictureUrls);
        JsonObject jsonObject1 = (JsonObject) obj1;
        String[] info = pictureUrls.split(",");
        for(int i=0;i<info.length;i++){
            String tempUrls = jsonObject1.get(Integer.toString(i)).toString();
            tempUrls = tempUrls.substring(1,tempUrls.length()-1);
            pictureUrlList.add(tempUrls);
        }
        archiveService.modifyArchive(title,pictureUrlList,archiveId,contents,tempIsMutual);
        System.out.println("아카이브가 변경되었습니다.");
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/archive/{archiveId}", method = RequestMethod.GET)
    public HashMap<String, Object> viewDetailArchive(@PathVariable("archiveId") int archiveId, HttpServletRequest request) throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        Archive archive = archiveService.getArchiveObject(archiveId);
        String clubName = archiveService.getClubName(archive.getClubId());
        map.put("title", archive.getTitle());
        map.put("contents", archive.getContents());
        map.put("pictures", archive.getPictureUrls());
        if(archive.getModifiedDate()==null){
            map.put("time", archive.getCreatedDate());
        }
        else{
            map.put("time", archive.getModifiedDate());
        }
        map.put("like", archive.getLike());
        map.put("clubName", clubName);
        map.put("clubId", archive.getClubId());
        map.put("isMutual", archive.getIsMutual());
        HttpSession session = request.getSession();
        map.put("userLiked", archive.getLikeMember().contains((String) session.getAttribute("member")));
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/archive/{archiveId}/like", method = RequestMethod.POST)
    public boolean likeArchive(@PathVariable("archiveId") int archiveId, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        String memberId = (String) session.getAttribute("member");
        return archiveService.likeArchive(archiveId,memberId);
    }

    @ResponseBody
    @RequestMapping(value = "/archive", method = RequestMethod.GET)
    public List<HashMap<String,Object>> viewAllArchive() throws Exception{
        return archiveService.viewAllArchive();
    }

    @ResponseBody
    @RequestMapping(value = "/club/{clubId}/archive", method = RequestMethod.GET)
    public List<Pair<Integer, String>> viewMyClubArchiveList(@PathVariable("clubId") int clubId) throws Exception{
        return archiveService.viewMyClubArchiveList(clubId);
    }
}
