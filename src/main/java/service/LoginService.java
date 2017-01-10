package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuchengguo.personnel.dao.UserDAO;
import com.xuchengguo.personnel.entity.User;

import webEntity.UserModel;
import webEntity.UserPower;

@Service
//服务层，业务逻辑，用于处理登录
public class LoginService {
	@Autowired
	private UserPower power;
	private User user;
	private UserDAO dao;
	public boolean login(UserModel userModel){
		String name=userModel.getUsername();
		String password=userModel.getPassword();
		UserDAO userdao=new UserDAO();
		List<User> result=userdao.queryAllUser();
		for(User u:result){
			String username=u.getUsername();
			if(username.equals(name)&&password.equals(u.getPassword())){
				user=u;
				power.setUserPower(u.getLimitsPower().getId());
				return true;
			}
		}
		return false;
	}
	public LoginService(){
		dao=new UserDAO();
	}
	public int getLimitsPower(){
		return dao.querySingleUser(user.getId()).getLimitsPower().getId();
	}
}
