package emoney.user.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import emoney.user.domain.LoginHistoryVO;
import emoney.user.domain.UserVO;
import emoney.user.dto.LoginDTO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Inject SqlSession sql;
	
	private static final String ns = "emoney.user.mapper.userMapper";

	@Override
	public List<UserVO> viewAll() throws Exception {
		return sql.selectList(ns+".viewAll");
	}
	
	@Override
	public int viewId(String name) throws Exception {
		return sql.selectOne(ns+".viewId", name);
	}
	
	@Override
	public int idCheck(String id) throws Exception {
		return sql.selectOne(ns+".idCheck", id);
	}
	
	@Override
	public int nicknameCheck(String nickname) throws Exception {
		return sql.selectOne(ns+".nicknameCheck",nickname);
	}
	
	@Override
	public int signup(UserVO uvo) throws Exception {
		return sql.insert(ns+".signup",uvo);
	}
	
	@Override
	public int naverSignup(UserVO uvo) throws Exception {
		return sql.insert(ns+".naverSignup",uvo);
	}
	
	@Override
	public UserVO login(String userId) throws Exception {
		return sql.selectOne(ns+".login",userId);
	}
	
	@Override
	public int insertLoginHistory(LoginHistoryVO lvo) throws Exception {
		return sql.insert(ns+".insertLoginHistory",lvo);
	}
	
	@Override
	public int updateUserLastLogin(UserVO uvo) throws Exception {
		return sql.update(ns+".updateUserLastLogin", uvo);
	}
}
