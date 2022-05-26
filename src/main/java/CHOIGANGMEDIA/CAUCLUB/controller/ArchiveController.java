package CHOIGANGMEDIA.CAUCLUB.controller;

import CHOIGANGMEDIA.CAUCLUB.domain.Archive;
import CHOIGANGMEDIA.CAUCLUB.service.ArchiveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Controller
public class ArchiveController {

    private final ArchiveService archiveService;

    public ArchiveController(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/{clubId}/newArchive", method = RequestMethod.POST)
    public boolean registerNewArchive(@PathVariable int clubId, @RequestParam String contents,
                                      @RequestParam ArrayList<String> pictureUrls, @RequestParam String title) throws Exception{

        Archive archive = new Archive();
        Date now = new Date();

        String createDate = now.toString();
        archive.setCreatedDate(createDate);
        archive.setContents(contents);
        archive.setClubId(clubId);
        archive.setTitle(title);
        archive.setModifiedDate(null);
        archive.setPictureUrls(pictureUrls);
        archive.setLike(0);
        archive.setLikeMember(null);
        archiveService.registerNewArchive(archive);
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
    public boolean modifyArchive(@PathVariable("archiveId") int archiveId, @RequestParam String title,
                                 @RequestParam ArrayList<String> pictureUrls, @RequestParam String contents) throws Exception {
        archiveService.modifyArchive(title,pictureUrls,archiveId,contents);
        System.out.println("아카이브가 변경되었습니다.");
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/archive/{archiveId}", method = RequestMethod.GET)
    public HashMap<String, Object> viewDetailArchive(@PathVariable("archiveId") int archiveId) throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        Archive archive = archiveService.getArchiveObject(archiveId);
        String clubName = archiveService.getClubName(archive.getClubId());
        map.put("title", archive.getTitle());
        map.put("pictures", archive.getPictureUrls());
        if(archive.getModifiedDate()==null){
            map.put("time", archive.getCreatedDate());
        }
        else{
            map.put("time", archive.getModifiedDate());
        }
        map.put("like", archive.getLike());
        map.put("clubName", clubName);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/archive/{archiveId}/like", method = RequestMethod.POST)
    public boolean likeArchive(@PathVariable("archiveId") int archiveId, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        String memberId = (String) session.getAttribute("member");
        return archiveService.likeArchive(archiveId,memberId);
    }
}
