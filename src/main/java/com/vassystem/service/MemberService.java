package com.vassystem.service;

import common.util.UserLevelType;

import com.vassystem.dto.Member;
import com.vassystem.packet.MemberInfoPacket;
import com.vassystem.packet.MemberInitialInfoPacket;
import com.vassystem.packet.ResultPacket;

public interface MemberService {
	
	/*select Member */
	Member selectMember(String email, String pwd) throws Exception;
	
	/* login check */
	ResultPacket loginCheck(String email, String pwd) throws Exception;
	
	/* login check and return Member Info*/
	MemberInfoPacket loginCheckWithRtn(String email, String pwd) throws Exception ;	
	
	/* register */
	ResultPacket register(UserLevelType userLevel, String email, String pwd, String nickname, int ch_type, String ch_id) throws Exception;

	
	/* Set and Get initial user information */
	MemberInitialInfoPacket getUserInitialInfo(int user_account) throws Exception;
	
	/* session check */
	int checkSession(int user_account, String sid);
}