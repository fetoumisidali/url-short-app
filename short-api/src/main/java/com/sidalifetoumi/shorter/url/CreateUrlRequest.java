package com.sidalifetoumi.shorter.url;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

/*
    @Author Fetoumi Sidali
    @Created 11/23/2025
*/
public record CreateUrlRequest
        (
                @NotBlank(message = "url cannot be empty")
                @URL(message = "please enter valid url")
                String url
        ) {
}
