package org.zerock.j10.user.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    private String email;

    private String mname;

    private String mpw;

    @ElementCollection(fetch = FetchType.LAZY)   // PK없이 FK만 가지고 조인하는 테이블
    @Builder.Default
    private Set<MemberRole> memberRoleSet = new HashSet<>();

    public void addMemberRole(MemberRole role) {
        memberRoleSet.add(role);
    }

}
