package com.vassystem.packet;

import com.vassystem.dto.UserCharacter;

public class CharacterSimplePacket extends HeaderPacket {
	
	public String sid;
	public UserCharacter userCharacter;
}