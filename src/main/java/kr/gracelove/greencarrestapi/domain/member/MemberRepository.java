package kr.gracelove.greencarrestapi.domain.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    @Override
    Page<Member> findAll(Pageable pageable);

    Optional<Member> findByName(String username);
}
