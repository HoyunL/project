package kr.co.project.member;

import javax.servlet.http.HttpSession;

public interface MemberService {
	int insert(MemberVO vo);
	int emailDupCheck(String email);
	boolean loginCheck(MemberVO vo, HttpSession sess); //체크
	MemberVO findEmail(MemberVO vo); 
	MemberVO findPwd(MemberVO vo); 
}
