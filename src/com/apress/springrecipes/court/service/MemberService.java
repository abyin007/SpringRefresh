package com.apress.springrecipes.court.service;

import com.apress.springrecipes.court.domain.Member;

public interface MemberService {
	
	public String addMember(Member member);
	public String removeMember(String memberName);
}
