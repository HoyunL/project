package kr.co.project.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentMapper mapper;
	
	@Override
	public Map index(CommentVO vo) {

		int totalCount = mapper.count(vo); //총게시물수
		int totalPage = totalCount / vo.getPageRow() ; //총페이지수
		if(totalCount % vo.getPageRow() > 0) totalPage++; 
		
		
		//시작인덱스
		int startIdx = (vo.getPage()-1) * vo.getPageRow();
		vo.setStartIdx(startIdx); //처음에 인덱스가 있어야하기떄문에 vo에 값을 넣어줌
		List<CommentVO> list = mapper.list(vo);
		
		//페이징처리
		int endPage = (int)(Math.ceil(vo.getPage()/10.0)*10);
		int startPage = endPage -9;
		if(endPage > totalPage) endPage = totalPage;
		boolean prev = startPage > 1 ? true : false;
		boolean next = endPage < totalPage ? true : false;
		
		Map map = new HashMap();
		map.put("totalCount", totalCount);
		map.put("totalPage", totalPage);
		map.put("endPage", endPage);
		map.put("startPage", startPage);
		map.put("list", list);
		map.put("prev", prev);
		map.put("next", next);
		
		return map;
		
		
		
	}

	@Override
	public int insert(CommentVO vo) {
		
		return mapper.insert(vo);
	}

	@Override
	public int delete(CommentVO vo) {
		
		return mapper.delete(vo.getNo());
	}

}
