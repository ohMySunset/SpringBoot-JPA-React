package org.zerock.j10.user.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.zerock.j10.user.security.CustomAccessDeniedHandler;
import org.zerock.j10.user.security.filter.ApiCheckFilter;
import org.zerock.j10.user.security.filter.ApiLoginFilter;
import org.zerock.j10.user.security.filter.handler.LoginFailHandler;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    {
        log.info("SecurityConfig...................");
        log.info("SecurityConfig...................");
        log.info("SecurityConfig...................");

    }

    /**
     * Bcrypt 암호화처리 : 복호화 불가능
     *
     * @return new BCryptPasswordEncoder()
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    /**
     * 403 접근에러 처리
     *
     * @return CustomAccessDeniedHandler()
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {

        return new CustomAccessDeniedHandler();
    }

    /**
     * api uri 경로에 따른 접근제어 처리
     *
     * @return ApiCheckFilter()
     */
    @Bean
    public ApiCheckFilter apiCheckFilter() {
        return new ApiCheckFilter("/api/board/**/*");
    }

    @Bean
    public ApiLoginFilter apiLoginFilter() throws Exception {
        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/login", authenticationManager());
        apiLoginFilter.setAuthenticationFailureHandler(new LoginFailHandler());

        return apiLoginFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("configure.......");

//        http.authorizeRequests()
//                .antMatchers("/sample/all").permitAll()
//                .antMatchers("/sample/member").hasRole("USER")
//                .antMatchers("/sample/admin").hasRole("ADMIN");
//                  // 누구나 접근할 수 있는 요청
//
//        http.formLogin();
//        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

        // 세션 생성 막기
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // security에 필터 추가
        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);


        http.csrf().disable();

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("user00")
//                .password("$2a$10$Uc5j0RVNcD9.TTjWe78KL.mcBNeYTh26u6v4yXydT7LwaadUtzYha")
//                .authorities("ROLE_USER");
//
//    }


}
