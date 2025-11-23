package com.sidalifetoumi.shorter.url;

/*
    @Author Fetoumi Sidali
    @Created 11/23/2025
*/

public record ShortUrlResponse (
        String shortUrl
)
{
    private static final String APP_BASE_URL = "http://localhost:8080/api/v1/shorter/";

    public ShortUrlResponse(String shortUrl){
        this.shortUrl = APP_BASE_URL + shortUrl;
    }
}
