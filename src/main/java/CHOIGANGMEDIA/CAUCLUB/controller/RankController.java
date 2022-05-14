package CHOIGANGMEDIA.CAUCLUB.controller;

import CHOIGANGMEDIA.CAUCLUB.service.RankService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class RankController {

    private final RankService rankService;

    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @ResponseBody
    @RequestMapping(value = "/rank/0", method = RequestMethod.GET)
    public ArrayList<String> viewTotalRank() throws Exception{
        return rankService.showTotalRank();
    }

    @ResponseBody
    @RequestMapping(value = "/rank/1", method = RequestMethod.GET)
    public ArrayList<String> viewStudyClub() throws Exception{
        return rankService.showStudyRank();
    }

    @ResponseBody
    @RequestMapping(value = "/rank/2", method = RequestMethod.GET)
    public ArrayList<String> viewArtClub() throws Exception{
        return rankService.showArtRank();
    }

    @ResponseBody
    @RequestMapping(value = "/rank/3", method = RequestMethod.GET)
    public ArrayList<String> viewEtcClub() throws Exception{
        return rankService.showEtcRank();
    }
}
