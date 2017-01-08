package service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xuchengguo.personnel.dao.UserDAO;
import com.xuchengguo.personnel.entity.User;

import webEntity.UserModle;

@Service
//服务层，业务逻辑，用于处理登录
public class LoginService {

	public boolean login(UserModle userModle){
		String name=userModle.getUsername();
		String password=userModle.getPassword();
		UserDAO userdao=new UserDAO();
		List<User> result=userdao.queryAllUser();
		for(User u:result){
			String username=u.getUsername();
			if(username.equals(name)&&password.equals(u.getPassword())){
				return true;
			}
		}
		return false;
	}
	public LoginService(){
		
	}
}
