package com.vassystem.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.vassystem.dto.UserCharacter;
import common.dao.AbstractDAO;
import common.util.ParamVO;

@Repository("CharacterDAO")
public class CharacterDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<UserCharacter> selectCharacterList(int user_account) throws Exception{ 
		return selectList("character.selectUserCharacterList", user_account); 
	}
	
	public UserCharacter selectCharacterShapeInfo(ParamVO paramVO) throws Exception{ 
		return (UserCharacter) selectOne("character.selectCharacterShapeInfo", paramVO); 
	}
	
	public Object modifyUserCharacter(ParamVO paramVO) throws Exception{ 
		return selectOne("character.modifyUserCharacter", paramVO); 
	}
	
	public Object createUserCharacter(ParamVO paramVO) throws Exception{ 
		return selectOne("character.createUserCharacter", paramVO); 
	}
}