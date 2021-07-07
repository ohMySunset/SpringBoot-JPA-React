package org.zerock.j10;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.AntPathMatcher;
import org.zerock.j10.user.security.util.JWTUtil;

@SpringBootTest
class J10ApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
    }

    @Test
    public void testEncode(){
        // 1111을 암호화하는 코드
        System.out.println(passwordEncoder.encode("1111"));

        String enStr = "$2a$10$Uc5j0RVNcD9.TTjWe78KL.mcBNeYTh26u6v4yXydT7LwaadUtzYha"; // DB에 보관되는 값

        // 1111을 암호화했을 때 나올 수 있는 암호인지 매칭
        System.out.println(passwordEncoder.matches("1111", enStr));
    }

    @Test
    public void testMatch(){

        // api에 접근 시 인가 받은 사용자만 접근 가능하도록 제어.
        String pattern = "/api/board/**/*";

        AntPathMatcher matcher = new AntPathMatcher();

        System.out.println(matcher.match(pattern, "/api/board/read/123"));

    }

    @Test
    public void testCreateJWT() throws Exception {
        String email = "user88@aaa.com";

        String result = new JWTUtil().generateToken(email);

        System.out.println(result);

    }

    @Test
    public void testValidate() throws Exception {

        String str = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjU2MzczMDksImV4cCI6MTYyODIyOTMwOSwic3ViIjoidXNlcjg4QGFhYS5jb20ifQ.s57GrMzsp1vwGTlxd_vOiajQkWyamP5GGZiV2vWk3KE";

        System.out.println(new JWTUtil().validateAndExtract(str));
    }


}
