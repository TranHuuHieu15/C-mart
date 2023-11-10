package com.sti.cmart.service.impl;

import com.sti.cmart.entity.Account;
import com.sti.cmart.entity.Role;
import com.sti.cmart.entity.RoleName;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Slf4j // cái ni để sử dụng logger
@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public Account extractUserName(String token) {
        Claims claims = extractAllClaims(token);

        String username = claims.get("username", String.class);
        Long accountId = claims.get("accountId", Long.class);
        List<Integer> roles = claims.get("roles", List.class);

        Account account = new Account();
        account.setId(accountId);
        account.setUsername(username);
        account.setRoles(getRoles(roles));
        return account;
//        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }


    //!Lấy thông tin người dùng cuối cùng từ token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }

    // Phương thức này sẽ tạo ra token từ thông tin người dùng

    public String generateAccessToken(Account account, List<Integer> roles) {
        return generateAccessToken(getExtraClaims(account, roles));
    }

    public String generateRefreshToken(Account account, List<Integer> roles) {
        return generateRefreshToken(getExtraClaims(account, roles), account);
    }

    public Map<String, Object> getExtraClaims(Account account, List<Integer> roles) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", account.getUsername());
        extraClaims.put("accountId", account.getId());
        extraClaims.put("roles", roles);
        return extraClaims;
    }

    private String generateAccessToken(Map<String, Object> extraClaims) {
        return Jwts.builder().setClaims(extraClaims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    private String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +  1000 * 60 * 60 * 24 * 10)) // 10 ngày
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
                // Đoạn này sẽ tạo ra chữ ký cho token của người dùng bằng thuật toán HS256
    }

    /* Phương thức này sẽ kiểm tra xem token có hợp lệ hay không
     *  Thông qua tên người dùng và thời gian hết hạn của token
     */
    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    // Phương thức này sẽ kiểm tra xem token có hết hạn hay không
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Phương thức này sẽ trả về đối tượng Key dùng để tạo ra token
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static Set<Role> getRoles(List<Integer> index) {
        Set<Role> roles = new HashSet<>();
        for (Integer integer : index) {
            Role role = new Role();
            role.setRoleName(RoleName.values()[integer]);
            roles.add(role);
        }
        return roles;
    }

}
