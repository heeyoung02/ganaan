package com.ganaan.missionary.service;

import com.ganaan.missionary.dto.MailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender emailSender;

    public void sendMultipleMessage(MailDTO mailDto, MultipartFile[] files) throws MessagingException, IOException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // 메일 제목 설정
        helper.setSubject(mailDto.getTitle());

        // 발신자 설정
        if (StringUtils.hasText(mailDto.getFrom())) {
            helper.setFrom(mailDto.getFrom());
        } else {
            helper.setFrom("ccganaan@gmail.com"); // 발신자가 없는 경우 기본 주소로 설정
        }

        // 수신자 설정
        if (mailDto.getAddress().length < 1) {
            mailDto.setAddress(new String[]{"ccganaan@gmail.com"}); // 수신자가 없는 경우 기본 주소로 설정
//            mailDto.setCcAddress(new String[]{"ohohyesgogo@gmail.com"}); // 참조 수신인 설정
//            helper.setCc(mailDto.getCcAddress());
        }
        helper.setTo(mailDto.getAddress());

        // 본문 설정
        helper.setText(mailDto.getContent(), false);

        // 첨부 파일 추가
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                helper.addAttachment(MimeUtility.encodeText(fileName, "UTF-8", "B"), new ByteArrayResource(file.getBytes()));
            }
        }

        // 메일 전송
        emailSender.send(message);
        log.info("Mail multiple send complete.");
    }
}
