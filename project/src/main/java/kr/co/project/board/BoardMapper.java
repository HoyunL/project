package kr.co.project.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
	
	int insert(BoardVO vo);
	int count(BoardVO vo);
	List<BoardVO> list(BoardVO vo);
	BoardVO view(int no);
	void updateViewcount(int no); // ?
	int update(BoardVO vo);
	int delete(int no);
	
}
