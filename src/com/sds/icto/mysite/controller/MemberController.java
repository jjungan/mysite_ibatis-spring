package com.sds.icto.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sds.icto.mysite.domain.MemberVo;
import com.sds.icto.mysite.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	/* joinform으로 가는 메서드 */
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String joinForm(){
		return "member/joinform";
	}

	/* member insert를 위한 메서드 */
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String join(@ModelAttribute MemberVo vo){
		memberService.joinMember(vo);
		return "member/joinsuccess";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginForm(){
		return "member/loginform";
	}

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@ModelAttribute MemberVo vo, HttpSession session){
		MemberVo member = memberService.authMember(vo);
		if(member == null){
			return "redirect:/member/login?result=fail";
		}
		session.setAttribute("authMember", member);
		return "redirect:/index";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("authMember");
		session.invalidate();
		return "redirect:/index";
	}
	
	@RequestMapping(value="/myinfo", method=RequestMethod.GET)
	public String update(){
		return "member/myinfo";
	}
	@RequestMapping(value="/myinfo", method=RequestMethod.POST)
	public String update(MemberVo vo, HttpSession session){
		MemberVo authMember = (MemberVo)session.getAttribute("authMember");
		if(authMember == null){
			return "redirect:/index";
		}
		vo.setEmail(authMember.getEmail());
		memberService.modifyMember(vo);
		//session에 있는 authMember도 업데이트.
		MemberVo authMember2 = memberService.authMember(vo);
		session.removeAttribute("authMember");
		session.setAttribute("authMember", authMember2);
		
		return "redirect:/index";
	}
}
