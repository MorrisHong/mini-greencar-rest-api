package kr.gracelove.greencarrestapi.domain.member;

import kr.gracelove.greencarrestapi.domain.address.Address;
import kr.gracelove.greencarrestapi.web.dto.MemberUpdateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();
    }

    @Test
    void save_member() {
        Address address = new Address("경기도 용인시 처인구","백옥대로", "111-111");
        String testName = "grace";
        String testEmail = "govlmo91@gmail.com";
        String testPassword = "1234";

        Member member = Member.builder()
                .name(testName)
                .email(testEmail)
                .password(testPassword)
                .address(address)
                .build();
        memberRepository.save(member);

        em.flush();
        em.clear();

        assertThat(member.getId()).isGreaterThan(0L);
        assertThat(member.getAddress()).isEqualTo(address);
        assertThat(member.getName()).isEqualTo(testName);
        assertThat(member.getEmail()).isEqualTo(testEmail);
        assertThat(member.getPassword()).isEqualTo(testPassword);
        assertThat(member.getCreatedDate()).isNotNull();
        assertThat(member.getModifiedDate()).isNotNull();
    }

    @Test
    void update_member() {
        Address address = new Address("경기도 용인시 처인구","백옥대로", "111-111");
        String testName = "grace";
        String testEmail = "govlmo91@gmail.com";
        String testPassword = "1234";


        String updatedName = "updatedName";
        String updatedEmail = "updatedEmail";
        String updatedPassword = "1111";

        Member member = Member.builder()
                .name(testName)
                .email(testEmail)
                .password(testPassword)
                .address(address)
                .build();

        Member savedMember = memberRepository.save(member);

        MemberUpdateRequestDto dto = MemberUpdateRequestDto.builder()
                .name(updatedName)
                .email(updatedEmail)
                .password(updatedPassword)
                .address(address).build();

        savedMember.updateMember(dto);

        assertThat(savedMember.getAddress()).isEqualTo(address);
        assertThat(savedMember.getName()).isEqualTo(updatedName);
        assertThat(savedMember.getEmail()).isEqualTo(updatedEmail);
        assertThat(savedMember.getPassword()).isEqualTo(updatedPassword);

    }

}