package kr.co.project.board;

import java.util.Map;

public interface BoardService {
	
	//목록
	Map index(BoardVO vo);
	//상세
	BoardVO view(int no);
	// 수정폼
	BoardVO edit(int no);
	//수정처리
	boolean update(BoardVO vo);
	// 삭제처리
	boolean delete(int no);
	// 등록처리
	boolean insert(BoardVO vo);
}
