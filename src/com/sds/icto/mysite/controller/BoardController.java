package com.sds.icto.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sds.icto.mysite.domain.BoardVo;
import com.sds.icto.mysite.domain.MemberVo;
import com.sds.icto.mysite.service.BoardService;
import com.sds.icto.mysite.service.MemberService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/list")
	public String list(Model model){
		List<BoardVo> list = boardService.selectBoard();
		model.addAttribute("list", list);
		return "board/list";
	}
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(){
		return "board/insertform";
	}
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(BoardVo vo, String password, HttpSession session){
		MemberVo authMember = (MemberVo)session.getAttribute("authMember");
		if(authMember == null){
			return "redirect:/board/list";
		}
		MemberVo member = new MemberVo();
		member.setEmail(authMember.getEmail());
		member.setPassword(password);
		System.out.println(member);
		MemberVo m = memberService.authMember(member);
		System.out.println("m : "+m);
		if(m == null){
		// 비밀번호가 맞지않음
			return "board/inserterror";
		}else{
			// 비밀번호가 맞음
			vo.setMemberNo(m.getNo());
			vo.setMemberName(m.getName());
			
			boardService.addBoard(vo);
			return "redirect:/board/list";
		}
	}
	@RequestMapping("/delete")
	public String delete(BoardVo vo){
		boardService.removeBoard(vo);
		return "redirect:/board/list";
	}
	@RequestMapping("/view")
	public String view(BoardVo vo, Model model, HttpSession session){
		boardService.modifyBoardViewCnt(vo);

		BoardVo board = boardService.selectBoardByNo(vo.getNo());
		model.addAttribute("board", board);

		MemberVo authMember = (MemberVo)session.getAttribute("authMember");
		if(authMember.getNo() == board.getMemberNo()){
			return "board/updateform";
		}else{
			return "board/view";
		}
	}
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String search(String search, Model model){
		List<BoardVo> list = boardService.selectBoardByKeyword(search);
		System.out.println(list);
		model.addAttribute("list", list);
		return "board/list";
	}
	@RequestMapping("/update")
	public String update(BoardVo vo){
		boardService.modifyBoard(vo);
		return "redirect:/board/list";
	}
	
	
	
	
	
}
