package com.example.urlShortner.service;

public interface UrlShortnerService {
  String shortUrl(String url);

  String getOriginalUrl(String url);
}
