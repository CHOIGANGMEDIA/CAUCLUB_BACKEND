package CHOIGANGMEDIA.CAUCLUB.controller;

import CHOIGANGMEDIA.CAUCLUB.domain.Club;
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
    @RequestMapping(value="/club/idDuplicateCheck", method = RequestMethod.POST)
    public boolean idDuplicateCheck(@RequestParam String id) throws Exception{
        if(loginService.idDuplicateCheckService(id)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 회원가입 API, 위에서 아이디는 이미 중복체크가 된걸로 가정
     * <회원가입 시에 프론트->백엔드로 념겨주는 데이터 리스트>
     * department   부서
     * email    이메일
     * id   아이디
     * leader   동아리장
     * name 동아리명
     * password 비밀번호
     * type 동아리 유형 -> 학술(1) / 예체능(2) / 기타(3)
     */

    @ResponseBody
    @RequestMapping(value="/club/newClub", method= RequestMethod.POST)
    public boolean joinNewClub(@RequestParam String department, @RequestParam String email, @RequestParam String id,
                               @RequestParam String leader, @RequestParam String name, @RequestParam String password,
                               @RequestParam int type) throws Exception{

        Club club = new Club();
        club.setDepartment(department);
        club.setEmail(email);
        club.setId(id);
        club.setLeader(leader);
        club.setName(name);
        club.setPassword(password);
        club.setType(type);
        /////////////////// 프론트에서 넘겨준 데이터를 바탕으로 club 객체 각 필드에 저장
        loginService.registerNewClub(club);
        System.out.println("회원가입이 성공적으로 완료되었습니다."); // 테스트를 위한 회원가입 성공 문구 출력
        return true;
    }
}