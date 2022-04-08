package CHOIGANGMEDIA.CAUCLUB.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/**
 * department   부서
 * email    이메일
 * id   아이디
 * name 이름
 * password 비밀번호
 * type 멤버 유형 -> 1: 동아리장 / 2: 동아리 부원
 * club 동아리
 */


public class Member {

    private String id;
    private String password;
    private String name;
    private String email;
    private String club;
    private String department;
    private int type;

}
