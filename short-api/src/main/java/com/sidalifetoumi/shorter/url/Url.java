package com.sidalifetoumi.shorter.url;

/*
    @Author Fetoumi Sidali
    @Created 11/23/2025
*/

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "urls")
public class Url {

    @Id
    private String id;

    private String longUrl;

    private String shortId;

    @CreatedDate
    private LocalDateTime createdAt;
}
