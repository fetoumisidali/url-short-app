package com.sidalifetoumi.shorter.url;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.security.SecureRandom;
import java.util.List;

/*
    @Author Fetoumi Sidali
    @Created 11/23/2025
*/
@Service
public class UrlServiceImpl implements UrlService{

    private static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final static int SHORT_ID_LENGTH = 4;
    private final String APP_URL;
    private static final String SHORT_URL_SUFFIX = "/api/v1/shorter/";
    private static final SecureRandom RANDOM = new SecureRandom();

    private static final Logger log = LoggerFactory.getLogger(UrlServiceImpl.class);

    private final UrlRepository urlRepository;

    public UrlServiceImpl(@Value("${app.url}") String appUrl, UrlRepository urlRepository) {
        APP_URL = appUrl;
        this.urlRepository = urlRepository;
    }

    @Override
    public String getUrl(String shortId) {
        Url url = urlRepository
                .findByShortId(shortId).orElseThrow(() -> new UrlNotFoundException("url not found"));
        return url.getLongUrl();
    }

    @Override
    public ShortUrlResponse saveUrl(CreateUrlRequest createUrlRequest) {

        String longUrl = createUrlRequest.url();
        String shortUrlBase = APP_URL + SHORT_URL_SUFFIX;

        Url url = urlRepository.findByLongUrl(longUrl)
                .orElseGet(() -> {
                    String shortId = generateUniqueId();
                    Url newUrl = new Url();
                    newUrl.setLongUrl(longUrl);
                    newUrl.setShortId(shortId);
                    return urlRepository.save(newUrl);
                });

        String shortUrl = shortUrlBase + url.getShortId();

        return new ShortUrlResponse(shortUrl);
    }

    @Override
    public List<Url> getAll() {
        return urlRepository.findAll();
    }

    private String generateUniqueId(){

        String generatedId;

        do {
            StringBuilder sb = new StringBuilder(SHORT_ID_LENGTH);
            for (int i = 0; i < SHORT_ID_LENGTH; i++) {
                sb.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
            }
            generatedId = sb.toString();

        } while (urlRepository.existsByShortId(generatedId));

        return generatedId;
    }




}
