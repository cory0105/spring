package dw.jdbcproject.Controller;

import dw.jdbcproject.Model.Member;
import dw.jdbcproject.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping("/member/new")
    public ResponseEntity<Member> saveMember(@RequestBody Member member){
        return ResponseEntity.ok(memberService.saveMember(member));
        // return new ResponseEntity<>(memberService.saveMember(member), HttpStatus.OK);
    }
    @GetMapping("/member/findAll")
    public ResponseEntity<List<Member>> findAll(){
        return ResponseEntity.ok(memberService.findAll());
    }
}
