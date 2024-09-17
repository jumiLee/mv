package com.vassystem.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vassystem.dto.CharacterCustInfo;
import com.vassystem.packet.CharacterPacket;
import com.vassystem.packet.CharacterShapePacket;
import com.vassystem.packet.CharacterSimplePacket;
import com.vassystem.packet.ResultPacket;
import com.vassystem.service.UserCharacterService;


@RestController
public class UserCharacterController {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired 
	private UserCharacterService characterService;
	
	/* character Select */
	@RequestMapping(value="/selectCharacterList.do", produces = "application/json")
	@ResponseBody
	public CharacterPacket selectCharacterList(@RequestParam int user_account) throws Exception {
		
		return characterService.selectCharacterList(user_account);
	}
	
	/* character Shape info Select */
	@RequestMapping(value="/selectCharacterShapeInfo.do", produces = "application/json")
	@ResponseBody
	public CharacterShapePacket selectCharacterShapeInfo(@RequestParam int user_account, @RequestParam int char_id, @RequestParam int char_sn) throws Exception {
		
		return characterService.selectCharacterShapeInfo(user_account, char_id, char_sn );
	}
	
	/* create Character */
	@RequestMapping(value="/createUserCharacter.do", produces = "application/json")
	@ResponseBody
	public CharacterSimplePacket createUserCharacter(	@RequestParam int user_account) throws Exception {
		
		return characterService.createCharacter(user_account);
	}
	
	/* update character info (json 형태의 캐릭터 장착 변경사항 저장) */
	@RequestMapping(value="/modifyUserCharacter.do", produces = "application/json")
	@ResponseBody
	public CharacterPacket modifyUserCharacter(	@RequestParam int user_account, 
									 			@RequestParam int char_id,
									 			@RequestParam int user_char_sn,
									 			@RequestParam String char_cust_info_json) throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		CharacterCustInfo char_cust_info = objectMapper.readValue(char_cust_info_json, CharacterCustInfo.class);
		
		return characterService.modifyUserCharacter(1, user_account, char_id, user_char_sn, char_cust_info, null);
	}
	
	/* update character shape info (text 형태의 캐릭터 외모 변경사항 저장) */
	//저장 후 결과로, 바뀐 shape정보만 보냄.
	@RequestMapping(value="/modifyCharacterShape.do", produces = "application/json")
	@ResponseBody
	public ResultPacket modifyCharacterShape(@RequestParam int user_account, 
									 			@RequestParam int char_id,
									 			@RequestParam int user_char_sn,
									 			@RequestParam String char_shape_info) throws Exception {
		
		CharacterPacket characterPacket = new CharacterPacket();
		ResultPacket resultPacket = new ResultPacket();
		characterPacket = characterService.modifyUserCharacter(2, user_account, char_id, user_char_sn, null, char_shape_info);
		
		resultPacket.setHeader(characterPacket.account, characterPacket.resultCd, characterPacket.resultMsg);
		
		return resultPacket;
	}
	
	//대표 캐릭터 변경
	@RequestMapping(value="/changeMainCharacter.do", produces = "application/json")
	@ResponseBody
	public CharacterPacket changeMainCharacter(@RequestParam int user_account, 
									 			@RequestParam 	int char_id,
									 			@RequestParam int user_char_sn) throws Exception {
		
		CharacterPacket characterPacket = new CharacterPacket();
		characterPacket = characterService.modifyUserCharacter(3, user_account, char_id, user_char_sn, null, null);
		
		return characterPacket;
				
	}
}