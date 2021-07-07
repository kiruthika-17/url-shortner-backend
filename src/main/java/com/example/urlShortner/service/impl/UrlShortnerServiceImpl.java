package com.example.urlShortner.service.impl;

import com.example.urlShortner.entity.UrlShortnerEntity;
import com.example.urlShortner.repo.UrlShortnerRepo;
import com.example.urlShortner.service.UrlShortnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class UrlShortnerServiceImpl implements UrlShortnerService {

  private static final String BASE64STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  private static final char[] CHARSET = BASE64STRING.toCharArray();
  @Autowired
  private UrlShortnerRepo urlShortnerRepo;

  @Override
  public String shortUrl(String url) {
    UrlShortnerEntity urlShortnerEntity = generateShortnerEntity(url);
    urlShortnerEntity = urlShortnerRepo.save(urlShortnerEntity);
    return encode(urlShortnerEntity.getId());
  }

  @Override
  public String getOriginalUrl(String url) {
    Long id = decode(url);
    UrlShortnerEntity urlShortnerEntity = urlShortnerRepo.getById(id);
    return urlShortnerEntity.getLongUrl();
  }

  private Long decode(String url) {
    Long i = 0L;
    char[] chars = url.toCharArray();
    for(char c : chars){
      i = i * BASE64STRING.length() + BASE64STRING.indexOf(c);
    }
    return i;
  }

  private String encode(Long id) {
    StringBuilder shortUrl = new StringBuilder();
    Long i = id;
    while(i > 0){
      Integer rem = Math.toIntExact(i % BASE64STRING.length());
      i /=  BASE64STRING.length();
      shortUrl.append(CHARSET[rem]);
    }
    return shortUrl.reverse().toString();
  }

  private UrlShortnerEntity generateShortnerEntity(String url) {
    UrlShortnerEntity urlShortnerEntity = new UrlShortnerEntity();
    urlShortnerEntity.setLongUrl(url);
    urlShortnerEntity.setCreatedAt(new Date());
    urlShortnerEntity.setUpdatedAt(new Date());
    return urlShortnerEntity;
  }
}
