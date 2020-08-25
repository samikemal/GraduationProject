package com.teklifver.Controller;

import com.teklifver.Services.UserService;
import com.teklifver.data.CodeCheckService;
import com.teklifver.entity.UserEntity;
import com.teklifver.form.CheckPasswordForm;
import com.teklifver.form.SendEmailForm;
import com.teklifver.form.VerificationCodeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

@Controller
public class PasswordMailController {

    @Autowired
    UserService userService;

    @Autowired
    CodeCheckService codeCheckService;

    @PostMapping("/editPassword")
    private String editPassword(@ModelAttribute("checkPasswordForm") @Valid CheckPasswordForm checkPasswordForm, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        String mail = (String) session.getAttribute("email");
        userService.editPassword(mail,checkPasswordForm);
        return "redirect:/";
    }
    @PostMapping("/checkVerificationCode")
    private String checkVerificationcode(@ModelAttribute("verificationCodeForm") @Valid VerificationCodeForm verificationCodeForm , BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request)
    {
        if (bindingResult.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.SendEmailForm.", bindingResult);
            return "pages/checkVerificationCodePage";
        }

        HttpSession session = request.getSession();
        if (codeCheckService.getOtpCode((String) session.getAttribute("email")).equals(verificationCodeForm.getVerificationCode()))
        {
            model.addAttribute("checkPasswordForm",new CheckPasswordForm());
            return "pages/checkPasswordPage";
        }
        else {
            redirectAttributes.addFlashAttribute("isFail",Boolean.TRUE);
            return "redirect:/";
        }
    }
    @PostMapping("/sendEmail")
    public String sendEmail(@ModelAttribute("sendEmailForm") @Valid SendEmailForm sendEmailForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) throws MailException, IOException, MessagingException {
        if (bindingResult.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.SendEmailForm.", bindingResult);
            return "pages/forgotPasswordPage";
        }
        UserEntity userEntity = userService.findUserEntityByEmail(sendEmailForm.getEmail());
        if (userEntity == null){
            return "redirect:/forgotPassword";
        }
        HttpSession session = request.getSession();
        session.setAttribute("email",sendEmailForm.getEmail());
        sendmaill(sendEmailForm.getEmail());
        model.addAttribute("verificationCodeForm",new VerificationCodeForm());
        return "pages/checkVerificationCodePage";
    }
    private void sendmaill(String toEmail) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("teklifverr@gmail.com", "teklifver123");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("hasan.altun@hibizz.com", false));
        String code = getRandomNumberString();
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        msg.setSubject("Şifre Değiştirme");
        msg.setContent("Şifre değiştirme için gereken doğrulama kodu :" + code , "text/plain; charset=UTF-8");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Şifre değiştirme için gereken doğrulama kodu :" + code, "text/plain; charset=UTF-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);
        Transport.send(msg);
        codeCheckService.addOtpCode(toEmail,code);
    }
    public  String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
