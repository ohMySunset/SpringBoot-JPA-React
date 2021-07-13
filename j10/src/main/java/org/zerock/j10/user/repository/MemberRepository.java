package org.zerock.j10.user.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.j10.user.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    // Fetch : 명시한 attribute는 eager로 나머지는 Lazy
    // Load : 명시한 attribute는 eager로 나머지는 entity에 명시한 fetch type이나 디폴트 FetchType으로 패치
    @EntityGraph(attributePaths = "memberRoleSet", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Member m where m.email = :email")
    Optional<Member> findByEmail(String email);
}
