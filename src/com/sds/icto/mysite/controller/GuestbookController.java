package com.sds.icto.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sds.icto.mysite.domain.GuestbookVo;
import com.sds.icto.mysite.service.GuestbookService;


@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	GuestbookService guestbookService;
	
	@RequestMapping("/list")
	public String index(Model model){
		List<GuestbookVo> list = guestbookService.getGuestbook();
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(GuestbookVo vo){
		guestbookService.addGuestbook(vo);
		return "redirect:/guestbook/list";
	}

	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String deleteForm(GuestbookVo vo, Model model){
		model.addAttribute("vo",vo);
		return "guestbook/deleteform";
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(GuestbookVo vo, Model model){
		GuestbookVo gb = guestbookService.getGuestbookByNo(vo.getNo());
		if(!gb.getPassword().equals(vo.getPassword())){
			model.addAttribute("vo",vo);
			model.addAttribute("msg","비밀번호를 확인해주세요");
			return "guestbook/deleteform";
		}
		guestbookService.removeGuestbook(gb);
		return "redirect:/guestbook/list";
	}
}
