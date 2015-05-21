package com.sds.icto.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.icto.mysite.domain.BoardVo;
import com.sds.icto.mysite.repository.BoardDao;
@Service
public class BoardService {
	
	@Autowired
	BoardDao boardDao;
	
	public void addBoard(BoardVo vo){
		boardDao.insertBoard(vo);
	}
	
	public void removeBoard(BoardVo vo){
		boardDao.deleteBoard(vo);
	}
	
	public void modifyBoard(BoardVo vo){
		boardDao.updateBoard(vo);
	}
	
	public void modifyBoardViewCnt(BoardVo vo){
		boardDao.updateBoardViewCnt(vo);
	}
	public BoardVo selectBoardByNo(Long no){
		BoardVo board = boardDao.selectBoardByNo(no);
		return board;
	}
	
	public List<BoardVo> selectBoard(){
		List<BoardVo> list = boardDao.selectBoard();
		return list;
	}
	
	public List<BoardVo> selectBoardByKeyword(String word){
		List<BoardVo> list = boardDao.selectBoardByKeyword(word);
		return list;
	}
	
}
