package com.ganaan.missionary.service;

import com.ganaan.missionary.ect.GoogleMapApiInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GoogleMapApiService {
    @Value("${google.maps.api.key}")
    private String apiKey;
    @Value("${google.maps.map.id}")
    private String mapId;
    public GoogleMapApiInfo getGoogleMapApiInfo() {
        return new GoogleMapApiInfo(apiKey, mapId);
    }
}
