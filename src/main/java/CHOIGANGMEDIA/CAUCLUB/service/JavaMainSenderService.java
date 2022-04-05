package CHOIGANGMEDIA.CAUCLUB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public class JavaMainSenderService{

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 아이디 찾기, 비밀번호 찾기 이메일 인증 API
     * 해당 이메일이 데이터베이스에 있으면 true 반환
     * true를 반환하면서 6자리 랜덤 인증번호를 세션에 저장하면서 이메일로 보내주기..
     * 해당 이메일이 데이터베이스에 없으면 false 반환
     */

    public void javaMailSender(String email, String validationNumber) throws Exception{
        String to = email;
        String from = "kitaecoding999@gmail.com";
        String subject = "CAUCLUB 이메일 인증 관련 메일";
        StringBuilder body = new StringBuilder();
        body.append("<html><body><h3>안녕하세요. CAUCLUB 관리자입니다. 이메일 인증번호 보내드립니다.<br>");
        body.append("인증번호는 " +validationNumber+"입니다.<br>");
        body.append("CAUCLUB 어플에 가셔서 인증번호를 올바르게 입력해주시기 바랍니다.</h3></body></html>");
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");
        mimeMessageHelper.setFrom(from,"To Do Mate Administrator");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body.toString(), true);
        javaMailSender.send(message);
    }
}
