package kr.gracelove.greencarrestapi.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.gracelove.greencarrestapi.domain.address.Address;
import kr.gracelove.greencarrestapi.domain.member.Member;
import kr.gracelove.greencarrestapi.domain.member.MemberRepository;
import kr.gracelove.greencarrestapi.web.dto.MemberRequestDto;
import kr.gracelove.greencarrestapi.web.dto.MemberResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MemberApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void member_등록된다() throws Exception {
        //given
        String name = "grace";
        Address address = new Address("경기도 용인시 처인구", "백옥대로", "111-222");
        String email = "govlmo91@gmail.com";
        String password = "1234";

        MemberRequestDto member = MemberRequestDto.builder()
                .name(name)
                .address(address)
                .email(email)
                .password(password)
                .build();

        String url = "http://localhost:" + port + "/api/v1/members";

        HttpEntity<MemberRequestDto> requestEntity = new HttpEntity<>(member);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Long.class);

        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);
    }

    @Test
    void member_findById() {
        String name = "test";
        String password = "1234";
        String email = "govlmo91@gmail.com";
        Address address = new Address("경기도 용인시 처인구", "백옥대로", "111-123");

        Member member = Member.builder()
                .name(name)
                .password(password)
                .email(email)
                .address(address)
                .build();

        memberRepository.save(member);

        String url = "http://localhost:" + port + "/api/v1/members/"+member.getId();
        MemberResponseDto exchange = restTemplate.getForObject(url, MemberResponseDto.class);

        System.out.println("exchange = " + exchange);
    }



}