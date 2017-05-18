package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuchengguo.personnel.dao.UserDAO;
import com.xuchengguo.personnel.entity.User;

import webModel.UserModel;
import webModel.UserPower;

@Service
//服务层，业务逻辑，用于处理登录
public class LoginService {
	//自动注入
	@Autowired
	private UserPower power;
	private User user;
	private UserDAO dao;
	public boolean login(UserModel userModel){
		String name=userModel.getUsername();
		String password=userModel.getPassword();
		UserDAO userdao=new UserDAO();
		List<User> result=userdao.queryAllUser();
		//加强for循环，用于从一个集合中遍历元素
		for(User u:result){
			String username=u.getUsername();
			if(username.equals(name)&&password.equals(u.getPassword())){
				//判断通过，说明用户可以登录，并且将可登录的用户赋值给LoginService的一个属性就是user
				user=u;
				//会改变当前访问这个用户所对应UserPower这个对象的属性，以标识其已经登录过
				power.setUserPower(u.getLimitsPower().getId());
				power.setUser(u);
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
