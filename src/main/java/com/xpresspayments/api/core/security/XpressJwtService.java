package com.xpresspayments.api.core.security;

import com.xpresspayments.api.model.dto.user.AuthenticationToken;
import com.xpresspayments.api.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class XpressJwtService {

    private final XpressJwtProperties xpressJwtProperties;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public AuthenticationToken generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, user.getContact().getEmailAddress());
    }

    private AuthenticationToken createToken(Map<String, Object> claims, String emailAddress) {
        Date createdAt = Date.from(Instant.now());
        Date expiresAt = Date.from(Instant.now().plus(xpressJwtProperties.getExpiration(), ChronoUnit.SECONDS));
        String accessToken = buildToken(claims, emailAddress, createdAt, expiresAt);
        String refreshToken = generateRefreshToken(emailAddress, createdAt, expiresAt);

        return AuthenticationToken.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }

    public String generateRefreshToken(String emailAddress, Date createdAt, Date expiresAt) {
        return buildToken(new HashMap<>(), emailAddress,createdAt, expiresAt);
    }

    private String buildToken(Map<String, Object> extraClaims, String subject, Date createdAt, Date expiresAt) {
        return Jwts.builder().setClaims(extraClaims).setSubject(subject).setIssuer("xpresspayments").setIssuedAt(createdAt)
                .setExpiration(expiresAt).signWith(getSignInKey(), SignatureAlgorithm.HS512).compact();
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(xpressJwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
