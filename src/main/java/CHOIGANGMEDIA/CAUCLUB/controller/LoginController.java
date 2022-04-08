package CHOIGANGMEDIA.CAUCLUB.controller;

import CHOIGANGMEDIA.CAUCLUB.domain.Club;
import CHOIGANGMEDIA.CAUCLUB.domain.Member;
import CHOIGANGMEDIA.CAUCLUB.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 아이디 중복체크 API
     * 중복이면 false, 중복이 아니면 true 리턴
     */

    @ResponseBody
    @RequestMapping(value="/member/idDuplicateCheck", method = RequestMethod.POST)
    public boolean idDuplicateCheck(@RequestParam String id) throws Exception{
        if(loginService.idDuplicateCheckService(id)){
            System.out.println("사용할 수 있는 아이디입니다.");
            return true;
        }
        else{
            System.out.println("아이디가 중복되었습니다.");
            return false;
        }
    }

    @ResponseBody
    @RequestMapping(value="/member/newMember", method= RequestMethod.POST)
    public boolean joinNewClub(@RequestParam String department, @RequestParam String email, @RequestParam String id,
                               @RequestParam String name, @RequestParam int type, @RequestParam String club,
                               @RequestParam String password) throws Exception{

        Member member = new Member();
        member.setDepartment(department);
        member.setEmail(email);
        member.setId(id);
        member.setName(name);
        member.setType(type);
        member.setClub(club);
        member.setPassword(password);
        /////////////////// 프론트에서 넘겨준 데이터를 바탕으로 club 객체 각 필드에 저장
        loginService.registerNewMember(member);
        System.out.println("회원가입이 성공적으로 완료되었습니다."); // 테스트를 위한 회원가입 성공 문구 출력
        return true;
    }

    @ResponseBody
    @RequestMapping(value="/member/login", method= RequestMethod.POST)
    public boolean loginClub(@RequestParam String id, @RequestParam String password) throws Exception{
        if(loginService.loginCheckService(id,password)){
            System.out.println("아이디와 비밀번호가 일치합니다.");
            return true;
        }
        else{
            System.out.println("아이디나 비밀번호가 일치하지 않습니다.");
            return false;
        }
    }
}