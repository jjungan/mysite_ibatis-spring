package com.sds.icto.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.sds.icto.mysite.domain.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	
	public void insertBoard(BoardVo vo){
		sqlMapClientTemplate.insert("board.insert", vo);
	}
	
	public void deleteBoard(BoardVo vo){
		sqlMapClientTemplate.delete("board.delete", vo);
	}
	public void updateBoard(BoardVo vo){
		sqlMapClientTemplate.update("board.update", vo);
	}
	
	public void updateBoardViewCnt(BoardVo vo){
		sqlMapClientTemplate.update("board.updateViewCnt", vo);
	}
	
	public BoardVo selectBoardByNo(Long no){
		return (BoardVo) sqlMapClientTemplate.queryForObject("board.selectByNo", no);
	}
	
	@SuppressWarnings("unchecked")
	public List<BoardVo> selectBoard(){
		List<BoardVo> list = null;
		list = sqlMapClientTemplate.queryForList("board.selectAll");
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<BoardVo> selectBoardByKeyword(String word){
		List<BoardVo> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("word", word);
		list = sqlMapClientTemplate.queryForList("board.selectByKeyword", map);
		return list;
	}
}
