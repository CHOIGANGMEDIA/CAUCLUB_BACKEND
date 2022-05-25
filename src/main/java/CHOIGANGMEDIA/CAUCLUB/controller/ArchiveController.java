package CHOIGANGMEDIA.CAUCLUB.controller;

import CHOIGANGMEDIA.CAUCLUB.domain.Archive;
import CHOIGANGMEDIA.CAUCLUB.service.ArchiveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

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
        archiveService.registerNewArchive(archive);
        System.out.println("아카이브 생성이 완료되었습니다.");
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/archive/{archiveId}", method = RequestMethod.DELETE)
    public boolean deleteArchive(@PathVariable("archiveId") int archiveId) throws Exception{
//        archiveService.deletePost(postId);
        System.out.println("아카이브가 삭제되었습니다.");
        return true;
    }
}
