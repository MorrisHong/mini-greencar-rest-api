package kr.gracelove.greencarrestapi.domain.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByName(String name);

    @Override
    Page<Member> findAll(Pageable pageable);
}
