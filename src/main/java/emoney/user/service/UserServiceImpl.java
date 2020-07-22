package emoney.user.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import emoney.user.dao.UserDAO;
import emoney.user.domain.LoginHistoryVO;
import emoney.user.domain.UserVO;
import emoney.user.dto.LoginDTO;

@Service
public class UserServiceImpl implements UserService {
	
	@Inject UserDAO dao;
	
	@Override
	public List<UserVO> viewAll() throws Exception {
		return dao.viewAll();
	}
	
	@Override
	public int viewId(String name) throws Exception {
		return dao.viewId(name);
	}
	
	@Override
	public int idCheck(String id) throws Exception {
		return dao.idCheck(id);
	}
	
	@Override
	public int nicknameCheck(String nickname) throws Exception {
		return dao.nicknameCheck(nickname);
	}
	
	@Override
	public int signup(UserVO uvo) throws Exception {
		int ret = dao.signup(uvo);
		return ret;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public int naverSignup(LoginHistoryVO lvo, UserVO uvo) throws Exception {
		dao.naverSignup(uvo);
		//insert시 select key로 login함수 제거해도 될 것 같다.
		uvo=dao.login(uvo.getId());
		lvo.setAccntId(uvo.getAccntId());
		int ret = dao.insertLoginHistory(lvo);
		return ret;
	}
	
	@Override
	public UserVO login(String userId) throws Exception {
		return dao.login(userId);
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public int loginLog(LoginHistoryVO lvo, UserVO uvo) throws Exception {
		dao.insertLoginHistory(lvo);
		System.out.println("loginhis!!");
		dao.updateUserLastLogin(uvo);
		System.out.println("userlastlogin!!");
		return 1;
	}
}
