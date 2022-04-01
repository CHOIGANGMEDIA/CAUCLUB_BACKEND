package CHOIGANGMEDIA.CAUCLUB.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * department   부서
 * email    이메일
 * id   아이디
 * introduction 동아리 소개글
 * leader   동아리장
 * name 동아리명
 * password 비밀번호
 * picture  동아리 사진
 * score    동아리 점수
 * type 동아리 유형 -> 학술(1) / 예체능(2) / 기타(3)
 */
public class Club {

    private String department;
    private String email;
    private String id;
    private String introduction;
    private String leader;
    private String name;
    private String password;
    private String picture;
    private int score;
    private int type;

}