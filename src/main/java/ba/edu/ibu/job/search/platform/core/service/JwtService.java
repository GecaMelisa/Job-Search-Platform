package ba.edu.ibu.job.search.platform.core.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
        @Value("${security.jwt.secret}")
        private String jwtSigningKey;

        public String extractUserName(String token) {
            return extractClaim(token, Claims::getSubject);
        }

        public String generateToken(UserDetails userDetails) {
            return generateToken(new HashMap<>(), userDetails);
        }

        public boolean isTokenValid(String token, UserDetails userDetails) {
            final String userName = extractUserName(token);
            return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
        }

        private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
            final Claims claims = extractAllClaims(token);
            return claimsResolvers.apply(claims);
        }

        private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
            return Jwts.builder()
                    .claims(extraClaims)
                    .subject(userDetails.getUsername())
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 48))
                    .signWith(getSigningKey()).compact();
        }

        private boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }

        private Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
        }

        private Claims extractAllClaims(String token) {
            return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token)
                    .getPayload();
        }

        private SecretKey getSigningKey() {
            byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
            return Keys.hmacShaKeyFor(keyBytes);
        }

    public String extractToken(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        if (StringUtils.isNotEmpty(authHeader) && StringUtils.startsWith(authHeader, "Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}

