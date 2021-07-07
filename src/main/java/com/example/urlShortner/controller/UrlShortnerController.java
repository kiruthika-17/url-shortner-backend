package com.example.urlShortner.controller;

import com.example.urlShortner.service.UrlShortnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortnerController {

  @Autowired
  private UrlShortnerService urlShortnerService;

  @PostMapping("/short-url")
  public ResponseEntity<String> shortUrl(@RequestBody String url){
    String shortUrl = urlShortnerService.shortUrl(url);
    return new ResponseEntity<>(shortUrl, HttpStatus.OK);
  }

  @GetMapping("/get/short-url/{url}")
  public ResponseEntity<String> getOriginalUrl(@PathVariable("url") String url){
    String originalUrl = urlShortnerService.getOriginalUrl(url);
    return new ResponseEntity<>(originalUrl, HttpStatus.OK);
  }
}
