package CHOIGANGMEDIA.CAUCLUB.controller;

import CHOIGANGMEDIA.CAUCLUB.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/{clubId}/{archiveId}/archiveReport", method = RequestMethod.POST)
    public boolean reportArchive(@PathVariable("memberId") String memberId, @PathVariable("archiveId") int archiveId, @PathVariable("clubId") int clubId) throws Exception{
        if(reportService.reportArchive(memberId, archiveId, clubId)){
            System.out.println("아카이브가 정상적으로 신고되었습니다.");
            return true;
        }
        else{
            System.out.println("이미 이 아카이브를 신고하였습니다.");
            return false;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{memberId}/{clubId}/{postId}/postReport", method = RequestMethod.POST)
    public boolean reportPost(@PathVariable("memberId") String memberId, @PathVariable("postId") int postId, @PathVariable("clubId") int clubId) throws Exception{
        if(reportService.reportPost(memberId, postId, clubId)){
            System.out.println("게시물이 정상적으로 신고되었습니다.");
            return true;
        }
        else{
            System.out.println("이미 이 게시물을 신고하였습니다.");
            return false;
        }
    }
}
