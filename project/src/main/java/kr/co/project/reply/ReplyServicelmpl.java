package kr.co.project.reply;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServicelmpl implements ReplyService {
//모든추상메서드를 전부구현해야함- 안하면 에러
	@Autowired
	ReplyMapper mapper; //기능확인완료
	
	@Override
	public Map index(ReplyVO vo) {
		
		int totalCount = mapper.count(vo); //총게시물수
		int totalPage = totalCount / vo.getPageRow() ; //총페이지수
		if(totalCount % vo.getPageRow() > 0) totalPage++; 
		
		//시작인덱스
		int startIdx = (vo.getPage()-1) * vo.getPageRow();
		vo.setStartIdx(startIdx); //처음에 인덱스가 있어야하기떄문에 vo에 값을 넣어줌
		List<ReplyVO> list = mapper.list(vo);
		
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
	public ReplyVO view(int no) {
		mapper.updateViewcount(no);//초회수
		return mapper.view(no);
	}
	@Override
	public ReplyVO edit(int no) {
		return mapper.view(no);
	}
	@Override
	public boolean update(ReplyVO vo) {
		return mapper.update(vo) > 0 ? true:false;
	}
	@Override
	public boolean delete(int no) {
		return mapper.delete(no) > 0 ? true:false;
	}
	
	@Override
	public boolean insert(ReplyVO vo) {
		boolean r = mapper.insert(vo) > 0? true: false;
		if (r) mapper.gnoUpdate(vo.getNo());
		return r; 
	}
	
	@Override
	public boolean reply(ReplyVO vo) {
		mapper.onoUpdate(vo); // 부모의 gno와 같고 , 부모의 ono 보다 큰 ono를  ono+1
		vo.setOno(vo.getNo()+1);
		vo.setNested(vo.getNested()+1);
		return mapper.reply(vo) > 0 ? true : false ;
	}
}