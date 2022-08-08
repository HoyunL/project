package kr.co.project.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.project.board.BoardMapper;
import kr.co.project.board.BoardVO;
import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/config/servlet-context.xml")
@Log4j
public class BoardMapperTests {

	@Autowired
	private BoardMapper mapper;

	//@Test
	public void testObj( ) {
		//log.info(mapper);
		BoardVO vo = new BoardVO();
		vo.setTitle("게시물 제목");
		vo.setContent("게시물 내용");
		int r = mapper.insert(vo);
		for(int i =0; i<20; i++) {
			r += mapper.insert(vo);
		}
		log.info("등록개수:" + r);
	}
	
//	//@Test
//	public void count() {
//		
//		Map map = new HashMap();
//		map.put("stype", "all");
//		map.put("sword", "직접");
//		//int totalCount = mapper.count(map);
//		System.out.println("총카운트:" + totalCount);
//	}
	
//	//@Test
//	public void list() {
//		Map map = new HashMap();
//		map.put("startIdx", 50);
//		map.put("pageRow", 50);
//		List<BoardVO> list = mapper.list(map);
//		list.forEach(vo -> log.info(vo));
//		//log.info(list);
//	}
	
	//@Test
	public void view() {
		mapper.updateViewcount(1);
		log.info(mapper.view(1));
	}
	//@Test
	public void update() {
		BoardVO vo = new BoardVO();
		vo.setTitle("제목 수정");
		vo.setContent("내용 수정");
		vo.setNo(24);
		log.info("update:" + mapper.update(vo));
	}
	
	@Test
	public void delete() {
		log.info("delete:" + mapper.delete(23));
	}
}