package com.sidalifetoumi.shorter.url;

/*
    @Author Fetoumi Sidali
    @Created 11/23/2025
*/


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends MongoRepository<Url,String> {

    Optional<Url> findByShortId(String shortId);
    boolean existsByShortId(String shortId);

    Optional<Url> findByLongUrl(String longUrl);
    boolean existsByLongUrl(String longUrl);
}
