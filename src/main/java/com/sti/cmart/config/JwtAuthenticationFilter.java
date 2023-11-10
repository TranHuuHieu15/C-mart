package com.sti.cmart.config;

import com.sti.cmart.entity.Account;
import com.sti.cmart.service.impl.CustomerUserDetailsService;
import com.sti.cmart.service.impl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j // cái ni sử dung logger
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CustomerUserDetailsService customerUserDetailsService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal
    (
                    @NonNull HttpServletRequest request,
                    @NonNull HttpServletResponse response,
                    @NonNull FilterChain filterChain
    )
            throws ServletException, IOException
    {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        // Nếu request không chứa Header là Authorization và prefix của token là Bearer
        // thì chúng ta sẽ bỏ qua filter này
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        // Lấy thông tin người dùng từ token
        Account account = jwtService.extractUserName(jwt);
        /*
         * Kiểm tra lấy thông tin của người dùng
         * Không nhất thiết phải kiểm tra trong database vì thông tin này đã được mã hóa trong token
         * Cùng với việc các dữ liệu lưu trong token là unique và không thể thay đổi nên ta có thể
         * lưu lại token cũ vào SecurityContextHolder để sử dụng lại
         * */
        if (account != null) {

            if (jwtService.isTokenValid(jwt)) {

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        account, null, account.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
