package top.mao196.sms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTests {

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    JavaMailSender javaMailSender;

    @Test
    public void testThymeleaf() {

        Context context = new Context();
        context.setVariable("code", "85535");
        String email = templateEngine.process("email", context);

        System.out.println(email);
    }

    @Test
    public void testSendEmail() throws MessagingException {

        Context context = new Context();
        context.setVariable("code", "85535");
        String email = templateEngine.process("email", context);
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom("linkmao196@163.com");
        mimeMessageHelper.setTo("497593532@qq.com");
        mimeMessageHelper.setSubject("This is checkcode email");
        mimeMessageHelper.setText(email, true);
//        javaMailSender.send(mimeMailMessage);
    }

}
