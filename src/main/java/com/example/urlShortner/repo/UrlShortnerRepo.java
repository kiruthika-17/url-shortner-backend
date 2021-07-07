package com.example.urlShortner.repo;

import com.example.urlShortner.entity.UrlShortnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlShortnerRepo extends JpaRepository<UrlShortnerEntity, Long> {
}
