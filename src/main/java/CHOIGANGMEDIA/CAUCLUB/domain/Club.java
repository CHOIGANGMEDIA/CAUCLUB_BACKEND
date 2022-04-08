package CHOIGANGMEDIA.CAUCLUB.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * id : 동아리장 아이디
 * department : 소속
 * introduction : 동아리 소개
 * name : 동아리 이름
 * picture : 동아리 사진
 * type : 동아리 유형 (1->학술동아리, 2->예체능동아리, 3->기타동아리)
 * score : 동아리 활동 점수
 */

public class Club {

    private String id;
    private String department;
    private String introduction;
    private String name;
    private String picture;
    private int type;
    private int score;

}