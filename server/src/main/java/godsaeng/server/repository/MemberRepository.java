package godsaeng.server.repository;

import godsaeng.server.domain.Member;
import godsaeng.server.domain.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByPlatformAndPlatformId(Platform platform, String platformId);

    Optional<Member> findByEmailAndPlatform(String email, Platform platform);

    Boolean existsByNickname(String nickname);

    @Query("select m.id from Member m where m.platform = :platform and m.platformId = :platformId")
    Optional<Long> findIdByPlatformAndPlatformId(Platform platform, String platformId);

    Boolean existsByEmailAndPlatform(String email, Platform platform);
}