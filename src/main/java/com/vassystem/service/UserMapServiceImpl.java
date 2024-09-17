package com.vassystem.service;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vassystem.packet.UserMapPacket;
import com.vassystem.repository.CharacterCompIds;
import com.vassystem.repository.UserMap;
import com.vassystem.repository.UserMapRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service 
public class UserMapServiceImpl implements UserMapService{

	private final UserMapRepository userMapRepository;
	
	public UserMapPacket save (int user_account, int char_id, int user_char_sn, int map_type, String map_data) {
		
		UserMapPacket userMapPacket = new UserMapPacket();
		
		UserMap userMap = new UserMap();
		userMap.setCharacterCompIds(getCharacterCompIds(user_account, char_id, user_char_sn, map_type));
		userMap.setMapData(map_data);
		
		userMapRepository.save(userMap); //return data 보내지 않고 성공여부만 보냄.
		userMapPacket.setHeader(user_account);
		
		return userMapPacket;
	}
	
	public UserMapPacket findById(int user_account, int char_id, int user_char_sn, int map_type) {
		
		UserMapPacket userMapPacket = new UserMapPacket();
		Optional<UserMap> userMapOptional =  userMapRepository.findById(getCharacterCompIds(user_account, char_id, user_char_sn, map_type));
		
		if(userMapOptional.isPresent()) {
			userMapPacket.setHeader(user_account);
			userMapPacket.userMap = userMapOptional.get();
		}else {
			userMapPacket.setHeader(user_account,-1,"No Map Data exist!");
		}
		userMapPacket.setHeader(user_account);
		
		return userMapPacket;
	}
	
	private CharacterCompIds getCharacterCompIds(int user_account, int char_id, int user_char_sn, int map_type) {
		
		return new CharacterCompIds(user_account, char_id, user_char_sn, map_type);
	}
}
