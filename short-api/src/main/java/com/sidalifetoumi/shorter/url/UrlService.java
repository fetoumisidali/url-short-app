package com.sidalifetoumi.shorter.url;

import java.util.List;

/*
    @Author Fetoumi Sidali
    @Created 11/23/2025
*/
public interface UrlService {
    String getUrl(String shortId);
    ShortUrlResponse saveUrl(CreateUrlRequest createUrlRequest);
    List<Url> getAll();
}
