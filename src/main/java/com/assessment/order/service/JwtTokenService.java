package com.assessment.order.service;

import com.assessment.order.config.properties.AuthenticationProperties;
import io.jsonwebtoken.Jwts;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtTokenService {

  private final AuthenticationProperties authenticationProperties;

  public String generateJwtRS256(String subject) {
    long expirationTimeInMs = 1000 * 60 * 60;

    try {
      PrivateKey privateKey = RSAKeyBouncyCastleHandler.getRSAPrivateKeyFromString(authenticationProperties.getJwtEncKey());
      return Jwts.builder()
          .subject(subject)
          .issuedAt(new Date())
          .expiration(new Date(System.currentTimeMillis() + expirationTimeInMs))
          .signWith(privateKey)
          .compact();
    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
      log.error("message=\"Failed to generate JWT token\"");
      return null;
    }
  }
}
