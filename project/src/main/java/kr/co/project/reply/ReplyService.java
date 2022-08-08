package kr.co.project.reply;

import java.util.Map;

public interface ReplyService {
	
	//목록
	Map index(ReplyVO vo);
	//상세
	ReplyVO view(int no);
	// 수정폼
	ReplyVO edit(int no);
	//수정처리
	boolean update(ReplyVO vo);
	// 삭제처리
	boolean delete(int no);
	// 등록처리
	boolean insert(ReplyVO vo);
	// 답변처리
	boolean reply(ReplyVO vo);
}
