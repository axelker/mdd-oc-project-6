package com.openclassrooms.mdd.service.auth;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.model.UserEntity;

@Service
public class JWTService {

    @Value("${jwt.iss}")
    private String jwtIssuer;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private JwtEncoder jwtEncoder;

    public JWTService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(UserEntity user) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtIssuer)
                .issuedAt(now)
                .subject(user.getUsername())
                .claim("user_id", user.getId())
                .expiresAt(now.plus(jwtExpiration, ChronoUnit.MILLIS))
                .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters
                .from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }

    public Long getUserId(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        Long userIdLong = jwt.getClaim("user_id");
        return Long.valueOf(userIdLong);
    }

}
