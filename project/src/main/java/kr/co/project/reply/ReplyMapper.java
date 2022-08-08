package kr.co.project.reply;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper {
	
	int insert(ReplyVO vo);
	int count(ReplyVO vo);
	List<ReplyVO> list(ReplyVO vo);
	ReplyVO view(int no);
	void updateViewcount(int no); // ?
	int update(ReplyVO vo);
	int delete(int no);
	int gnoUpdate(int gno);
	int onoUpdate(ReplyVO vo);
	int reply(ReplyVO vo);
}
