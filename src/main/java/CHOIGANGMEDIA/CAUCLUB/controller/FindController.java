package CHOIGANGMEDIA.CAUCLUB.controller;

import CHOIGANGMEDIA.CAUCLUB.domain.Member;
import CHOIGANGMEDIA.CAUCLUB.service.AuthenticationService;
import CHOIGANGMEDIA.CAUCLUB.service.FindService;
import CHOIGANGMEDIA.CAUCLUB.service.JavaMainSenderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class FindController {

    private final FindService findService;
    private final AuthenticationService authenticationService;
    private final JavaMainSenderService javaMainSenderService;

    public FindController(FindService findService, AuthenticationService authenticationService, JavaMainSenderService javaMainSenderService) {
        this.findService = findService;
        this.authenticationService = authenticationService;
        this.javaMainSenderService = javaMainSenderService;
    }

    /**
     * 아이디 찾기, 비밀번호 찾기 이메일 인증 API
     * 해당 이메일이 데이터베이스에 있으면 true 반환
     * true를 반환하면서 6자리 랜덤 인증번호를 세션에 저장하면서 이메일로 보내주기..
     * 해당 이메일이 데이터베이스에 없으면 false 반환
     */

    @ResponseBody
    @RequestMapping(value="/member/validEmail", method = RequestMethod.POST)
    public boolean idDuplicateCheck(@RequestParam String email, HttpServletRequest request) throws Exception{
        if(findService.emailCheckService(email)){
            ////////////////// 여기서 해당 이메일로 인증번호 발송하기..!! & 인증번호 세션에 저장하기..
            String validationNumber = authenticationService.generateNumber();   // 6자리 랜덤 인증번호 생성
            System.out.println(validationNumber);
            HttpSession session = request.getSession();
            session.setAttribute("validation",validationNumber);    // validation 세션에 인증번호 저장
            javaMainSenderService.javaMailSender(email,validationNumber);
            System.out.println("해당 이메일로 인증번호를 발송했습니다. 확인부탁드립니다.");
            return true;
        }
        else{
            System.out.println("해당 이메일이 존재하지 않습니다.");
            return false;
        }
    }

    @ResponseBody
    @RequestMapping(value="/member/resetPassword", method = RequestMethod.POST)
    public boolean resetPassword(@RequestParam String password, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        String memberId = (String) session.getAttribute("member");
        findService.resetPasswordService(memberId, password);
        System.out.println("성공적으로 비밀번호가 재설정되었습니다!");
        return true;
    }
}
