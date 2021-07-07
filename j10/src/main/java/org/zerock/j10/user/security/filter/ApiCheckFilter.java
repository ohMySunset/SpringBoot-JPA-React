package org.zerock.j10.user.security.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.zerock.j10.user.security.util.JWTUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter {

    private String pattern;
    private AntPathMatcher matcher;

    public ApiCheckFilter(String pattern){
        this.pattern = pattern;
        this.matcher = new AntPathMatcher();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 사용자가 요청한 URI과 패턴이 일치하는지 확인
        String requestURI = request.getRequestURI();
        boolean matchResult = matcher.match(pattern,requestURI);

        //uri와 매칭되는지 확인 후 다음 실행지로 이동시킴
        if(matchResult == false){
            log.info("passing.....");
            // 필터의 마지막 동작 : 다음에 실행할 목적지로 이동시킴
            filterChain.doFilter(request, response);
            return;
        }

        log.info("check target..............");

        // 헤더에서 토큰 값 가져오기
        String tokenValue = request.getHeader("Authorization");
        log.info(tokenValue);

        if(tokenValue != null){

            String jwtStr = tokenValue.substring(7);

            try {
                String email = new JWTUtil().validateAndExtract(jwtStr);

                log.info("==========extract result========>> "+ email);

                // 토큰값이 일치하면 원하는 요청으로 이동
                filterChain.doFilter(request,response);

            } catch (Exception e) {
                // 토큰값이 일치하지 않을 경우 처리
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                String content = "{\"msg\" : \"TOKEN ERROR\"}";
                response.getWriter().println(content);
            }

        } else {
            // 토큰값이 일치하지 않을 경우 처리
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            String content = "{\"msg\" : \"TOKEN ERROR\"}";
            response.getWriter().println(content);
        }// end if else





    }
}
