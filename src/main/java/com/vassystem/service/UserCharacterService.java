package com.vassystem.service;


import com.vassystem.dto.CharacterCustInfo;
import com.vassystem.dto.UserCharacter;
import com.vassystem.packet.CharacterPacket;
import com.vassystem.packet.CharacterShapePacket;
import com.vassystem.packet.CharacterSimplePacket;

public interface UserCharacterService {
	
	/*select Character List */
	CharacterPacket selectCharacterList(int user_account) throws Exception;
	
	/*select Character Shape Info */
	CharacterShapePacket selectCharacterShapeInfo(int user_account, int char_id, int char_sn) throws Exception;
	
	/*select Carry Character List */
	UserCharacter selectCarryCharacter(int user_account) throws Exception;
	
	/* update User Character */
	CharacterPacket modifyUserCharacter(int job_code, int user_account, int char_id, int user_char_sn, CharacterCustInfo char_cust_info, String char_shape_info) throws Exception;

	/* Create Character*/
	CharacterSimplePacket createCharacter(int user_account) throws Exception;
	
}