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
     * true를 반환하면서 6자리 랜덤 인증번호를 세션에 저장하면서 이메일로 보내주기..
     * 해당 이메일이 데이터베이스에 없으면 false 반환
     */

    @ResponseBody
    @RequestMapping(value="/club/validEmail", method = RequestMethod.POST)
    public boolean idDuplicateCheck(@RequestParam String email) throws Exception{
        if(findService.emailCheckService(email)){
            ////////////////// 여기서 해당 이메일로 인증번호 발송하기..!! & 인증번호 세션에 저장하기..
            return true;
        }
        else{
            return false;
        }
    }
}
