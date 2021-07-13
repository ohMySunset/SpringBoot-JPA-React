package org.zerock.j10.user.security.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.zerock.j10.user.security.util.JWTUtil;
import org.zerock.j10.user.service.MemberDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter {

    private String pattern;
    private AntPathMatcher matcher;
    private JWTUtil jwtUtil;

    @Autowired
    private MemberDetailsService memberDetailsService;

    public ApiCheckFilter(String pattern) {
        this.pattern = pattern;
        this.matcher = new AntPathMatcher();
        this.jwtUtil = new JWTUtil();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("ApiCheckFilter...............");
        // 사용자가 요청한 URI과 패턴이 일치하는지 확인
        String requestURI = request.getRequestURI();

        boolean matchResult = matcher.match(pattern, requestURI);
        //uri와 매칭되는지 확인 후 다음 실행지로 이동시킴
        if (matchResult == false) {
            log.info("passing...............");
            // 필터의 마지막 동작 : 다음에 실행할 목적지로 이동시킴
            filterChain.doFilter(request, response);
            return;
        }

        // 헤더에서 토큰 값 가져오기
        String tokenValue = request.getHeader("Authorization");
        String email = null;
        log.info(tokenValue);

        String jwtStr = tokenValue.substring(7);

        try {
            email = jwtUtil.validateAndExtract(jwtStr);

            log.info("=========extract result: " + email);

            log.info("--------------------------------------------");

        } catch (Exception e) {
            // 토큰값이 일치하지 않을 경우 처리
            log.error(e.getMessage());
            makeErrorMessage(response, e);
            return;
        }

        checkSecurityContext(email, request);
        // 토큰값이 일치하면 원하는 요청으로 이동
        filterChain.doFilter(request, response);
    }

    private void checkSecurityContext(String email, HttpServletRequest request) {

        // 전에 해당 이메일로 로그인 한 기록이 있는지 확인
        Authentication beforeAuth = SecurityContextHolder.getContext().getAuthentication();

        log.info(beforeAuth);
        log.info("===============================================");

        if (beforeAuth == null) {
            // 만약 현재 로그인한 상태가 아니면 유저 정보 가져옴
            UserDetails userDetails = memberDetailsService.loadUserByUsername(email);

            log.info(userDetails);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    //UserDetails의 loadUserByUsername은 아이디만 있으면 유저정보를 가져올 수 있음.
                    userDetails, null, userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // springContext에 인증 정보를 넣어줌 (비밀 번호 없이 가능)
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
    }

    private void makeErrorMessage(HttpServletResponse response, Exception exception) {
        try {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            String content = "{\"msg\" : \"" + exception.getClass().getSimpleName() + "\"}";
            response.getWriter().println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}