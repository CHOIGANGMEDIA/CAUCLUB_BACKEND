package CHOIGANGMEDIA.CAUCLUB.controller;

import CHOIGANGMEDIA.CAUCLUB.service.FindService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FindController {

    private final FindService findService;

    public FindController(FindService findService) {
        this.findService = findService;
    }

    /**
     * 아이디 찾기, 비밀번호 찾기 이메일 인증 API
     * 해당 이메일이 데이터베이스에 있으면 true 반환
     * 해당 이메일이 데이터베이스에 없으면 false 반환
     */

    @ResponseBody
    @RequestMapping(value="/club/validEmail", method = RequestMethod.POST)
    public boolean idDuplicateCheck(@RequestParam String email) throws Exception{
        if(findService.emailCheckService(email)){
            return true;
        }
        else{
            return false;
        }
    }
}
