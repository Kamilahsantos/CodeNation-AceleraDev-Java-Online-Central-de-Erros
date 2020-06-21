package br.com.aceleradev.java.centraldeerros.auth;

import java.util.Date;

import org.springframework.util.StringUtils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;

public class TokenAuthenticationService {
  private static final String SECRET = "";
  private static final long EXPIRATION_TIME = 86400000;
  private static final String TOKEN_PREFIX = "Bearer ";

  public static String create(String username) {
    return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(SignatureAlgorithm.HS512, SECRET).compact();
  }

  static boolean hasJwtToken(String token) {
    return (!StringUtils.isEmpty(token) && token.startsWith(TOKEN_PREFIX));
  }

  static String validateToken(String token) {
    return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
      .getSubject();
  }


}
