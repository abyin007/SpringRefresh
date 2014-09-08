package com.apress.springrecipes.court.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apress.springrecipes.court.domain.Member;
import com.apress.springrecipes.court.service.MemberService;

@Controller
/* @RequestMapping("/member/*") */
public class MemberController {
	private MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	/* @RequestMapping("add") */
	@RequestMapping("/member/add")
	public String addMember(Model model) {
		model.addAttribute("member", new Member());
		// model.addAttribute("guests", memberService.list());
		return "memberList";
	}

	/* @RequestMapping(value={"remove","delete"}, method=RequestMethod.GET) */
	@RequestMapping(value = { "/member/remove", "/member/delete" }, method = RequestMethod.GET)
	public String removeMember(@RequestParam("memberName") String memberName) {
		memberService.removeMember(memberName);
		return "redirect:";
	}

	@RequestMapping("display/{user}")
	public String removeMember(@RequestParam("memberName") String memberName,
			@PathVariable("user") String user) {
		
		/* the special notation {path_variable} to specify its @RequestMapping value.
		@PathVariable("user") String user -> if a request is received in
		the form member/display/jdoe , the handler method has access to the user variable with a jdoe value.
		*/
		return null;
	}
	
	@RequestMapping
	public void memberList() {
		/*Since the class level uses the /member/* URL wildcard, this handler method is executed as a catchall.
		So any URL request (e.g., /member/abcdefg or /member/randomroute) triggers this method.
		*/
	}
	
	/*The last method—memberLogic—lacks any @RequestMapping annotations, this means the method is a
	utility for the class and has no influence on Spring MVC*/
	public void memberLogic(String memberName) {
		
	}
}
