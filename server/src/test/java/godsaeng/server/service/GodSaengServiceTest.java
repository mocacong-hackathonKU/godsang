package godsaeng.server.service;

import godsaeng.server.domain.*;
import godsaeng.server.dto.request.GodSaengSaveRequest;
import godsaeng.server.dto.response.GodSaengSaveResponse;
import godsaeng.server.repository.GodSaengRepository;
import godsaeng.server.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GodSaengServiceTest {

    @Autowired
    private GodSaengRepository godSaengRepository;
    @Autowired
    private GodSaengService godSaengService;
    @Autowired
    private MemberRepository memberRepository;



    @DisplayName("같생을 저장할 수 있다.")
    @Test
    void save() {
        String title = "아침 6시 반 기상 갓생 살기";
        String description = "아침 6시 반 기상 후 유의미한 일을 하고 인증해야합니다.";
        List<Week> weeks = new ArrayList<>();
        weeks.add(Week.MON);
        weeks.add(Week.TUE);
        weeks.add(Week.WED);

        GodSaengSaveRequest request = new GodSaengSaveRequest(title, description, weeks);

        String email = "rlawjddn103@naver.com";
        Member savedMember = memberRepository.save(new Member(email, Platform.KAKAO, "11111"));

        godSaengService.save(savedMember.getId(), request);
        List<GodSaeng> actual = godSaengRepository.findAll();

        assertEquals(1, actual.size());
    }

    @DisplayName("같생을 조회할 수 있다.")
    @Test
    void findAll() {
        String title = "아침 6시 반 기상 갓생 살기";
        String description = "아침 6시 반 기상 후 유의미한 일을 하고 인증해야합니다.";
        List<Week> weeks = new ArrayList<>();
        weeks.add(Week.MON);
        weeks.add(Week.TUE);
        weeks.add(Week.WED);

        GodSaengSaveRequest request = new GodSaengSaveRequest(title, description, weeks);

        String email = "rlawjddn103@naver.com";
        Member savedMember = memberRepository.save(new Member(email, Platform.KAKAO, "11111"));

        godSaengRepository.save(new GodSaeng(title, description, weeks, savedMember));
        godSaengRepository.save(new GodSaeng(title, description, weeks, savedMember));

        godSaengService.findAllGodSaeng();
        List<GodSaeng> actual = godSaengRepository.findAll();

        assertEquals(2, actual.size());
    }

    @DisplayName("같생에 참가 신청할 수 있다.")
    @Test
    void attendGodSaeng() {
        String title = "아침 6시 반 기상 갓생 살기";
        String description = "아침 6시 반 기상 후 유의미한 일을 하고 인증해야합니다.";
        List<Week> weeks = new ArrayList<>();
        weeks.add(Week.MON);
        weeks.add(Week.TUE);
        weeks.add(Week.WED);

        GodSaengSaveRequest request = new GodSaengSaveRequest(title, description, weeks);

        String email1 = "rlawjddn103@naver.com";
        Member savedMember1 = memberRepository.save(new Member(email1, Platform.KAKAO, "11111"));

        String email2 = "rlawjddn102@naver.com";
        Member savedMember2 = memberRepository.save(new Member(email2, Platform.KAKAO, "12121"));

        GodSaeng savedGodSaeng = godSaengRepository.save(new GodSaeng(title, description, weeks, savedMember1));

        godSaengService.attendGodSaeng(savedMember2.getId(), savedGodSaeng.getId());

        GodSaeng actual = godSaengRepository.findById(savedGodSaeng.getId()).orElseThrow();

        assertEquals(2, actual.getMembers().size());
    }
}
