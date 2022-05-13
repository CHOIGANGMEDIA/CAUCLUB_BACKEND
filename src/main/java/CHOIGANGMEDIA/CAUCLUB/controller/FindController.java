package CHOIGANGMEDIA.CAUCLUB.controller;

import CHOIGANGMEDIA.CAUCLUB.domain.Member;
import CHOIGANGMEDIA.CAUCLUB.service.AuthenticationService;
import CHOIGANGMEDIA.CAUCLUB.service.FindService;
import CHOIGANGMEDIA.CAUCLUB.service.JavaMainSenderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value="/member/validIdEmail", method = RequestMethod.POST)
    public boolean idDuplicateCheck(@RequestParam String email, HttpServletRequest request) throws Exception{
        if(findService.emailCheckService(email)){
            ////////////////// 여기서 해당 이메일로 인증번호 발송하기..!! & 인증번호 세션에 저장하기..
            String validationNumber = authenticationService.generateNumber();   // 6자리 랜덤 인증번호 생성
//            System.out.println(validationNumber);
            HttpSession session = request.getSession();
            session.setAttribute("validation",validationNumber);    // validation 세션에 인증번호 저장
            session.setAttribute("email",email);
            javaMainSenderService.javaMailSender(email,validationNumber);
            System.out.println("해당 이메일로 인증번호를 발송했습니다. 확인부탁드립니다.");
            return true;
        }
        else{
            System.out.println("해당 이메일이 존재하지 않습니다.");
            return false;
        }
    }

    /**
     * 아이디를 아는 경우 비밀번호 재설정
     */

    @ResponseBody
    @RequestMapping(value="/member/resetPassword", method = RequestMethod.POST)
    public boolean resetPassword(@RequestBody String information, HttpServletRequest request) throws Exception{
        String password = information.substring(13,information.length()-2);
        HttpSession session = request.getSession();
        String memberId = (String) session.getAttribute("member");
        session.invalidate();
        findService.resetPasswordService(memberId, password);
        System.out.println("성공적으로 비밀번호가 재설정되었습니다!");
        return true;
    }

    /**
     * 아이디를 모르는 경우 비밀번호 재설정
     */

    @ResponseBody
    @RequestMapping(value="/member/changePassword", method = RequestMethod.POST)
    public boolean changePassword(@RequestBody String information, HttpServletRequest request) throws Exception{
        String password = information.substring(13,information.length()-2);
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        session.invalidate();
        findService.resetPasswordByEmailService(email,password);
        System.out.println("성공적으로 비밀번호가 재설정되었습니다!");
        return true;
    }

    @ResponseBody
    @RequestMapping(value="member/validIdCertification", method = RequestMethod.POST)
    public String checkIdCertification(@RequestParam String certification, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        String validationNumber = (String)session.getAttribute("validation");
        String email = (String)session.getAttribute("email");
        session.invalidate();
//        System.out.println(validationNumber);
        if(validationNumber.equals(certification)){
            System.out.println("인증번호가 일치합니다!");
            String userId = findService.getIdByEmailService(email);
            System.out.println("회원님의 아이디는 "+userId+" 입니다!");
            return userId;
        }
        System.out.println("인증번호가 일치하지 않습니다!");
        return null;
    }

    @ResponseBody
    @RequestMapping(value="member/validPasswordCertification", method = RequestMethod.POST)
    public boolean checkPasswordCertification(@RequestParam String certification, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        String validationNumber = (String)session.getAttribute("validation");
//        System.out.println(validationNumber);
        if(validationNumber.equals(certification)){
            System.out.println("인증번호가 일치합니다!");
            return true;
        }
        System.out.println("인증번호가 일치하지 않습니다!");
        return false;
    }
}
