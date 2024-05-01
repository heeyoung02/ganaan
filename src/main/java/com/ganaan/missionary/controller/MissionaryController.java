package com.ganaan.missionary.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MissionaryController {
    @Value("${google.maps.api.key}")
    private String apiKey;
    @Value("${google.maps.map.id}")
    private String mapId;
    @GetMapping("/googlemap")
    public String home(Model model) {
        model.addAttribute("apiKey", apiKey);
        model.addAttribute("mapId", mapId);
        return "home";
    }
}
