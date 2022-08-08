package kr.co.project.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	int insert(MemberVO vo);
	int emailDupCheck(String email); 
	MemberVO loginCheck(MemberVO vo);
	MemberVO findEmail(MemberVO vo);
	MemberVO findPwd(MemberVO vo);
	int updateTempPwd(MemberVO vo);
}
