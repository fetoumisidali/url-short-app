package com.sidalifetoumi.shorter.url;

/*
    @Author Fetoumi Sidali
    @Created 11/23/2025
*/

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/shorter")
public class UrlController {


    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShortUrlResponse saveUrl(@Valid @RequestBody CreateUrlRequest createUrlRequest){
        return urlService.saveUrl(createUrlRequest);
    }

    @GetMapping("/{shortId}")
    public ResponseEntity<Void> redirectToUrl(@PathVariable String shortId){
        String longUrl = urlService.getUrl(shortId);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(longUrl))
                .build();
    }

}
