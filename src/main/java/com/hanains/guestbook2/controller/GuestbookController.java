package com.hanains.guestbook2.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanains.guestbook2.dao.guestDao;
import com.hanains.guestbook2.vo.guestVo;



@Controller
public class GuestbookController {
	
	@Autowired
	private  guestDao dao;
	
	@RequestMapping("/")
	public String main(Model model){
		List<guestVo> list = dao.getlist();
		model.addAttribute("list", list);
		
		return "/WEB-INF/views/index.jsp";
	}
	
	@RequestMapping("/insert")
	public String insert(@ModelAttribute guestVo vo){
		dao.insert(vo);
		return "redirect:/";
	}
	
	@RequestMapping("/deleteform")
	public String deleteform(@ModelAttribute guestVo vo){		
		return "/WEB-INF/views/deleteform.jsp";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,Model model){
		Long no = Long.parseLong(request.getParameter("no"));
		dao.delete(no, request.getParameter("password"));
		return "redirect:/";
	}
	
	
}
