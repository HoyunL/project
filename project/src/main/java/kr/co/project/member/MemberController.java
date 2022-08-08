package kr.co.project.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@GetMapping("/member/join.do")
	public String join() {
		return "member/join";
	}
	
	@PostMapping("/member/join.do")
	public String join(MemberVO vo, Model model) {
		if(service.insert(vo) > 0 ) {
			model.addAttribute("msg", "정상적으로 회원가입 되었습니다!!");
			model.addAttribute("url", "login.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "회원가입 오류 입니다ㅠㅠ");
			return "common/alert";
		}
		
	}
	 
	@GetMapping("/member/emailDupCheck.do")
	public void emailDupCheck(@RequestParam String email, HttpServletResponse res) throws IOException {
		int count = service.emailDupCheck(email);
		boolean r = false;
		if (count == 1) r = true;
		PrintWriter out = res.getWriter();
		out.print(r);
		out.flush();
	}
	
	@GetMapping("/member/login.do")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/member/login.do")
	public String login(MemberVO vo, HttpSession sess, Model model) {
		if(service.loginCheck(vo, sess)) {
			return "redirect:/board/index.do";
		} else {
			model.addAttribute("msg","이메일 비밀번호를 확인해 주세요");
			return "common/alert";
		}
	}
	
	@GetMapping("/member/logout.do")
	public String logout(Model model, HttpServletRequest req) {
		HttpSession sess = req.getSession();
		sess.invalidate(); // 세션초기화(세션객체에 있는 모든 값들이 삭제)
		//sess.removeAttribute("loginInfo"); // 세션객체의 해당값만 삭제
		model.addAttribute("msg","로그아웃 되었습니다!");
		model.addAttribute("url","/project/board/index.do"); //project/board/index.do
		return "common/alert";
	}
	@GetMapping("/member/findEmail.do")
	public String findEmail() {
		
		return "member/findEmail";
	}
	
	@PostMapping("/member/findEmail.do")
	public String findEmail(Model model, MemberVO param) { // MemberVO param
		MemberVO vo = service.findEmail(param);
		if(vo != null) {
			model.addAttribute("result", vo.getEmail());
		}
		return "common/return";
	}
	@GetMapping("/member/findPwd.do")
	public String findPwd() {
		
		return "member/findPwd";
	}
	
	@PostMapping("/member/findPwd.do")
	public String findPwd(Model model, MemberVO param) { // MemberVO param
		MemberVO vo = service.findPwd(param);
		if(vo != null) {
			model.addAttribute("result", vo.getEmail());
		}
		return "common/return";
	}
	
	
}






