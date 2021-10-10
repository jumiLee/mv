package com.vassystem.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.vassystem.dao.CharacterDAO;
import com.vassystem.dto.UserCharacter;
import com.vassystem.packet.CharacterPacket;

@Service 
public class CharacterServiceImpl implements CharacterService {

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="CharacterDAO") 
	private CharacterDAO characterDAO; 

	@Override
	public CharacterPacket selectCharacterList(int user_account) throws Exception {
		
		CharacterPacket characterPacket = new CharacterPacket();
		int resultCd = 0;
		String resultMsg = "";
		
		List<UserCharacter> userCharacterList = characterDAO.selectCharacterList(user_account);
		
		if(CollectionUtils.isEmpty(userCharacterList)) {
			resultCd = -1;
			resultMsg ="There is no character";
		}
		
		characterPacket.userCharacterList = userCharacterList;
		characterPacket.setHeader(user_account, resultCd, resultMsg);
		
		return characterPacket;
	}
	
	/*select Carry Character List */
	@Override
	public UserCharacter selectCarryCharacter(int user_account) throws Exception{
	
		List<UserCharacter> userCharacterList = selectCharacterList(user_account).userCharacterList;
		
		return userCharacterList.stream()
				.filter(s -> s.carry_flag.equals("Y"))
				.findAny()
				.orElse(null);
	}
}