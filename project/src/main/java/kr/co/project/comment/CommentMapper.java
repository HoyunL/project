package kr.co.project.comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CommentMapper {
	int insert(CommentVO vo);
	int count(CommentVO vo);
	List<CommentVO> list(CommentVO vo);
	int delete(int no);
}
