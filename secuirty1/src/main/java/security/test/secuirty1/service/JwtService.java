package security.test.secuirty1.service;

import java.util.HashMap;
import java.util.Date;
import org.hibernate.mapping.Map;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
    private final String secret = "eyJhbGciOiJIUzM4NCJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTcwMDgyODg0MiwiaWF0IjoxNzAwODI4ODQyfQ.YpUKaurYL-LxZUhq99LgyWwAXuqjU2bEXtDEx44ljzdbRWTwaVCOa5mn3_-Js2Rs";
    
    public String generateToken(String username)
    {
        Map<String,Object> claims = new HashMap<>();
        return createToken(username,claims);
    }
    private String createToken(String username,Map <String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)).signWith(getSignKey(),SignatureAlgorithm.HS256).compact(); 
    }
    private Key getSignKey(){
        byte [] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }
    public Date extractExpirationDate(String token){
        return extractClaim(token,Claims::getExpiration);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) { 
        final Claims claims = extractAllClaims(token); 
        return claimsResolver.apply(claims); 
    } 
    public Claims extractAllClaims(String token){
        return Jwts
        .parserBuilder()
        .setSigningKey(getSignKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
    }
}
