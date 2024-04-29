package dw.jdbcproject.Service;

import dw.jdbcproject.Model.Member;
import dw.jdbcproject.Repository.JdbcMemberRepository;
import dw.jdbcproject.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
//    @Autowired
//    JdbcMemberRepository jdbcMemberRepository; // 강한 결합
    @Autowired
    MemberRepository memberRepository; // 약한 결합
    public Member saveMember(Member member){
        return memberRepository.save(member);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }
}
