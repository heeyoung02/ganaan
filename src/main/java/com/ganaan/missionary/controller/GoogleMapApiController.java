package com.ganaan.missionary.controller;

import com.ganaan.missionary.ect.GoogleMapApiInfo;
import com.ganaan.missionary.service.GoogleMapApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoogleMapApiController {
    @Autowired
    private GoogleMapApiService googleMapApiService;
    @GetMapping("/getGoogleMapApiInfo")
    public GoogleMapApiInfo getGoogleMapApiInfo() {
        return googleMapApiService.getGoogleMapApiInfo();
    }
}
