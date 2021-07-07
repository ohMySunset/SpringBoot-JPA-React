package org.zerock.j10.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.zerock.j10.user.entity.Member;
import org.zerock.j10.user.entity.MemberRole;
import org.zerock.j10.user.repository.MemberRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepotests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testInsertDummies(){

        IntStream.rangeClosed(1,100).forEach(i->{

            Member member = Member.builder()
                    .email("user"+i+"@aaa.com")
                    .mpw(passwordEncoder.encode("1111"))
                    .mname("USER"+i)
                    .build();

            member.addMemberRole(MemberRole.USER);

            if(i>80){
                member.addMemberRole(MemberRole.MEMBER);
            }
            if(i>90){
                member.addMemberRole(MemberRole.ADMIN);
            }

            memberRepository.save(member);
        });


    }

    @Test
    public void testLoad(){

        String email = "user10@aaa.com";

        Optional<Member> result = memberRepository.findByEmail(email);

        result.ifPresent(member-> System.out.println(member));

    }
}
