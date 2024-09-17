package com.vassystem.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import common.util.ChannelType;
import common.util.UserLevelType;

import com.vassystem.dto.Member;
import com.vassystem.packet.MemberInfoPacket;
import com.vassystem.packet.MemberInitialInfoPacket;
import com.vassystem.packet.ResultPacket;
import com.vassystem.service.MemberService;



@RestController
public class MemberController {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired 
	private MemberService memberService;
	
	/* member Select */
	@RequestMapping(value="/selectMember.do", produces = "application/json")
	@ResponseBody
	public Member selectMember(	 @RequestParam String email
								,@RequestParam String pwd) throws Exception {
		
		return memberService.selectMember(email, pwd);
	}
	
	
	/* login check */
	@RequestMapping(value="/loginChk.do", produces = "application/json")
	@ResponseBody
	public ResultPacket loginCheck(
			@RequestParam String email
			,@RequestParam String pwd
			) throws Exception {
		
		return memberService.loginCheck(email, pwd);
	}
	
	/* login check with return Member Info */
	@RequestMapping(value="/loginChkWithRtn.do", produces = "application/json")
	@ResponseBody
	public MemberInfoPacket loginChkWithRtn(
			@RequestParam String email
			,@RequestParam String pwd
			) throws Exception {
		
		return memberService.loginCheckWithRtn(email, pwd);
	}
	
	/* member register */
	@RequestMapping(value="/register.do", produces = "application/json")
	@ResponseBody
	public ResultPacket register(
			 @RequestParam String email
			,@RequestParam String pwd
			,@RequestParam String nickname
			) throws Exception {
		
		//channel 구현 미정
		//ch_type (미정)
		int ch_type = ChannelType.GENERAL.getCode();
		//ch_id 
		String ch_id = "";
		return memberService.register(UserLevelType.GENERAL, email, pwd, nickname, ch_type, ch_id);
	}
	
	/* Get Member Initial Info After Log in */
	@RequestMapping(value="/getUserInitialInfo.do", produces = "application/json")
	@ResponseBody
	public MemberInitialInfoPacket getUserInitialInfo(@RequestParam int user_account ) throws Exception {
		
		return memberService.getUserInitialInfo(user_account);
	}
	
	/* AI(test) register */
	@RequestMapping(value="/registerAI.do", produces = "application/json")
	@ResponseBody
	public ResultPacket registerAI(
			@RequestParam String email
			,@RequestParam String pwd
			,@RequestParam String nickname
			) throws Exception {
		
		int ch_type = ChannelType.GENERAL.getCode();
		String ch_id = "";
		return memberService.register(UserLevelType.AI, email, pwd, nickname, ch_type, ch_id);
	}
}