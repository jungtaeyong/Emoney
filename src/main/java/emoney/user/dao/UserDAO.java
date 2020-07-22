package emoney.user.dao;

import java.util.List;

import emoney.user.domain.LoginHistoryVO;
import emoney.user.domain.UserVO;
import emoney.user.dto.LoginDTO;

public interface UserDAO {
	public List<UserVO> viewAll() throws Exception;
	public int viewId(String name) throws Exception;
	public int idCheck(String id) throws Exception;
	public int nicknameCheck(String nickname) throws Exception;
	public int signup(UserVO uvo) throws Exception;
	public int naverSignup(UserVO uvo) throws Exception;
	public UserVO login(String userId) throws Exception;
	public int insertLoginHistory(LoginHistoryVO lvo) throws Exception;
	public int updateUserLastLogin(UserVO uvo) throws Exception;
}
