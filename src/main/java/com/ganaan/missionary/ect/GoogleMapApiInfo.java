package com.ganaan.missionary.ect;

public class GoogleMapApiInfo {
    private final String apiKey;
    private final String mapId;

    public GoogleMapApiInfo(String apiKey, String mapId) {
        this.apiKey = apiKey;
        this.mapId = mapId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getMapId() {
        return mapId;
    }
}
