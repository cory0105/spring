package dw.jdbcproject.Repository;

import dw.jdbcproject.Model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // save와 같은 메서드명은 아무렇게나 쓰면 됨
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
