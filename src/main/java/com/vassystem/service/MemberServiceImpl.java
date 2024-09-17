package com.vassystem.service;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.util.ParamVO;
import common.util.UserLevelType;
import com.vassystem.dao.MemberDAO;
import com.vassystem.dao.UserInfoDAO;
import com.vassystem.dto.Member;
import com.vassystem.packet.MemberInfoPacket;
import com.vassystem.packet.MemberInitialInfoPacket;
import com.vassystem.packet.ResultPacket;

@Service 
public class MemberServiceImpl implements MemberService {

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="MemberDAO") 
	private MemberDAO memberDAO; 

	@Resource(name="UserInfoDAO") 
	private UserInfoDAO userInfoDAO; 
	
	
	@Autowired
	UserCharacterService characterService;
	
	@Autowired
	UserAttendService userAttendService;
	
	@Override
	public Member selectMember(String email, String pwd) throws Exception {
		///Setting parameters
		ParamVO vo = new ParamVO(); 
		vo.setInStrParam01(email); 
		vo.setInStrParam02(pwd);
		
		return memberDAO.selectMember(vo);
	}
	/**
	 * login check
	 */
	@Override
	public ResultPacket loginCheck(String email, String pwd) throws Exception {
		
		ResultPacket resultPacket = new ResultPacket();
		Member member = new Member();
		int resultCd = 0;
		String resultMsg = "";
		int user_account = 0;
		
		//Setting parameters
		ParamVO vo = new ParamVO(); 
		vo.setInStrParam01(email);
		
		//call procedure
		member = memberDAO.selectMember(vo);
				
		//set return message
		if(member != null) {
			if (!pwd.equals(member.pwd)) {
				resultCd = -12;
				resultMsg = "wrong password";
			}else {
				user_account = member.account;			
				//loginCheckPacket.userDetail = userInfoDAO.selectUserDetail(user_account);
				resultPacket.sid = genSessionId(user_account);
			}
		}else {
			resultCd = -11;
			resultMsg = "No member exist";
		}		
		
		//login success -> register Attend
		if(resultCd ==0) {
			userAttendService.registerUserAttend(user_account);
		}
		resultPacket.setHeader(user_account, resultCd, resultMsg);
				
		return resultPacket;
	}	
	
	/**
	 * login check and return Member Info 
	 * 로그인 성공하면, 계정에 해당하는 member정보를 리턴해줌.
	 */
	@Override
	public MemberInfoPacket loginCheckWithRtn(String email, String pwd) throws Exception {
		
		ResultPacket resultPacket = new ResultPacket();
		MemberInfoPacket memberInfoPacket = new MemberInfoPacket();
		int user_account = 0;
		
		//call procedure
		resultPacket = loginCheck(email, pwd);
				
		//login success 
		if(resultPacket.resultCd ==0) {
			user_account = resultPacket.account;
			//register Attend
			userAttendService.registerUserAttend(user_account);
			
			//해당 멤버 정보 조회
			memberInfoPacket.userDetail = userInfoDAO.selectUserDetail(user_account);	
		}
		
		memberInfoPacket.setHeader(user_account, resultPacket.resultCd, resultPacket.resultMsg);
				
		return memberInfoPacket;
	}	
	/* register */
	@Override
	public ResultPacket register(UserLevelType userLevel, 
								 String email, 
								 String pwd, 
								 String nickname,
								 int ch_type,
								 String ch_id) throws Exception{
		ResultPacket resultPacket = new ResultPacket();
		int resultCd = 0;
		String resultMsg = "";
		int user_account= 0;
		
		//Setting parameters
		ParamVO vo = new ParamVO(); 
		vo.setInParam01(userLevel.getCode());
		vo.setInStrParam01(email);
		vo.setInStrParam02(nickname);
		vo.setInStrParam03(pwd);
		vo.setInParam02(ch_type);
		vo.setInStrParam04(ch_id);
				
		//call procedure
		memberDAO.registerMember(vo);
		resultCd = vo.getOutParam02();
		
		if(resultCd == 0) {
			user_account = vo.getOutParam01();
			//loginCheckPacket.userDetail = userInfoDAO.selectUserDetail(user_account);	
			resultPacket.sid = genSessionId(user_account);
			//출석등록 
			userAttendService.registerUserAttend(user_account);
		}else {
		//set return message
			switch(resultCd) {
			case -11:
				resultMsg = "사용중인 이메일입니다";
				break;
			case -12:
				resultMsg = "사용중인 닉네임입니다.";
				break;
			}
		}
		resultPacket.setHeader(user_account, resultCd, resultMsg);
		
		return resultPacket;
	}
	
	
	/* Set and Get initial user information */
	@Override
	public MemberInitialInfoPacket getUserInitialInfo(int user_account) throws Exception {
		MemberInitialInfoPacket memberInitialInfoPacket = new MemberInitialInfoPacket();
		
		//Set  notice and attend flag info
		memberInitialInfoPacket = userInfoDAO.selectUserInitialInfo(user_account);
		//Set user Info 
		memberInitialInfoPacket.userDetail = userInfoDAO.selectUserDetail(user_account);
		//Set Character Info
		memberInitialInfoPacket.carryUserCharacter = characterService.selectCarryCharacter(user_account);
		
		return memberInitialInfoPacket;
	}
	
	/**
	 * session check
	 */
	@Override
	public
	int checkSession(int user_account, String sid) {
		//Setting parameters
		ParamVO vo = new ParamVO(); 
		vo.setInStrParam01(sid);
		vo.setInParam01(user_account);
				
		//return userInfoDAO.selectUserBySid(vo);
		return 1; //현재 사용하지 않는 기능
	}
	
	/**
	 * Assign SessionId when user login
	 */
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