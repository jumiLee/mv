package com.vassystem.service;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import common.util.ParamVO;
import com.vassystem.dao.UserAttendDAO;
import com.vassystem.dao.UserInfoDAO;
import com.vassystem.packet.UserAttendPacket;

@Service 
public class UserAttendServiceImpl implements UserAttendService {

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="UserAttendDAO") 
	private UserAttendDAO userAttendDAO; 
	
	@Resource(name="UserInfoDAO") 
	private UserInfoDAO userInfoDAO; 

	@Override
	public UserAttendPacket selectUserAttendList(int user_account) throws Exception {
		
		UserAttendPacket userAttendPacket = new UserAttendPacket();
		userAttendPacket.userAttendList = userAttendDAO.selectUserAttendList(user_account);
		
		return userAttendPacket;
	}
	
	/**
	 * session check
	 */
	@Override
	public int checkSession(int user_account, String sid) {
		//Setting parameters
		ParamVO vo = new ParamVO(); 
		vo.setInStrParam01(sid);
		vo.setInParam01(user_account);
				
		//return userInfoDAO.selectUserBySid(vo);
		return 1; //현재 사용하지 않는 기능
	}
	
	/**
	 * get sessionId
	 */
	@SuppressWarnings("unused")
	private String genSessionId(int user_account) {
		
		if(user_account == 0) {
			return StringUtils.EMPTY;
		}
		
		String sid ="";
		sid = RandomStringUtils.randomAlphanumeric(10);
		sid = sid + user_account;
		
		//Setting parameters
		ParamVO vo = new ParamVO(); 
		vo.setInStrParam01(sid);
		vo.setInParam01(user_account);
				
		userInfoDAO.updatSid(vo);
		
		return sid;
	}
}