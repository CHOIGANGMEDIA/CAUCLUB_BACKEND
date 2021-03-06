package CHOIGANGMEDIA.CAUCLUB.controller;

import CHOIGANGMEDIA.CAUCLUB.service.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @ResponseBody
    @RequestMapping(value = "/member/{memberId}/main/joinedClub", method = RequestMethod.GET)
    public ArrayList<Integer> showJoinedClub(@PathVariable("memberId") String id) throws Exception {
        ArrayList<Integer> joinedClub = mainService.showJoinedClub(id);
        return joinedClub;
    }

    @ResponseBody
    @RequestMapping(value = "/member/{memberId}/main/managingClub", method = RequestMethod.GET)
    public ArrayList<Integer> showManagingClub(@PathVariable("memberId") String id) throws Exception {
        ArrayList<Integer> managingClub = mainService.showManagingClub(id);
        return managingClub;
    }
}
