package kr.co.project.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.SendMail;


@Service
public class MemberserviceImpl implements MemberService {

	
	@Autowired
	MemberMapper mapper;
	
	
	@Override
	public int insert(MemberVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public int emailDupCheck(String email) {
		return mapper.emailDupCheck(email);
	}

	@Override
	public boolean loginCheck(MemberVO vo, HttpSession sess) {
		
		boolean r = false;
		MemberVO loginInfo = mapper.loginCheck(vo);
		if (loginInfo != null) {
			r = true;
			// 로그인 성공시 세션에 저장 하기위해서
			sess.setAttribute("loginInfo", loginInfo);
		}
		return 	r; //mapper.loginCheck(vo) == null ? false : true;
	}
	
	@Override
	public MemberVO findEmail(MemberVO vo) {
		return mapper.findEmail(vo);
	}
	@Override
	public MemberVO findPwd(MemberVO vo) {
		//update
		MemberVO mv = mapper.findEmail(vo);
		if(mv != null) {
		// 임시비밀번호 생성
		// 영문두자리, 숫자두자리
		String temp = "";
		for (int i=0; i<2; i++) {
			temp += (char) (Math.random()*26+65);
		}
		for (int i=0; i<2; i++) {
			temp += (int) (Math.random()*9);
		}
			
		// 임시비밀번호 update
		vo.setPwd(temp);
		mapper.updateTempPwd(vo);
			
		// email발송
		SendMail.sendMail("dlghdbs7@naver.com", vo.getEmail(), "[더조은]임시비밀번호", "임시비밀번호:" + temp);
			return mv;
		} else {
			return null;
		}
	}
}
