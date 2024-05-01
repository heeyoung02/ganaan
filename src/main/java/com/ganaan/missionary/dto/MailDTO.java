package com.ganaan.missionary.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailDTO {

    private String from;

    private String[] address;

    private String[] ccAddress;

    private String title;

    private String content;

}
