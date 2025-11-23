package com.sidalifetoumi.shorter.url;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

/*
    @Author Fetoumi Sidali
    @Created 11/23/2025
*/
@Service
public class UrlServiceImpl implements UrlService{

    private static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final static int SHORT_ID_LENGTH = 4;

    private final UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
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
        String shortId = generateUniqueId();
        Url url = new Url();
        url.setLongUrl(createUrlRequest.url());
        url.setShortId(shortId);
        urlRepository.save(url);
        return new ShortUrlResponse(shortId);
    }

    private String generateUniqueId(){

        SecureRandom random = new SecureRandom();
        String generatedId;

        do {
            StringBuilder sb = new StringBuilder(SHORT_ID_LENGTH);
            for (int i = 0; i < SHORT_ID_LENGTH; i++) {
                sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
            }
            generatedId = sb.toString();

        } while (urlRepository.existsByShortId(generatedId));

        return generatedId;
    }


}
