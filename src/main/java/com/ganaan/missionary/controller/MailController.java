package com.ganaan.missionary.controller;

import com.ganaan.missionary.dto.MailDTO;
import com.ganaan.missionary.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class MailController {
    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/")
    public String mailSend() {
        return "ganaan";
    }

    @GetMapping("/result")
    public String sendSuccess() {
        return "result";
    }

    @PostMapping("/mail/send")
    @ResponseBody
    public String sendMail(MailDTO mailDto, MultipartFile[] files) throws MessagingException, IOException {
        if(files == null) files = new MultipartFile[0];
        try {
            mailService.sendMultipleMessage(mailDto, files);
            System.out.println("메일 전송 완료");
            return "success";
        } catch (Exception e) {
            System.err.println("메일 전송 실패: " + e.getMessage());
            return "error";
        }
    }
}
