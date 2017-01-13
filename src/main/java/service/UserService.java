package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuchengguo.personnel.dao.LimitsPowerDAO;
import com.xuchengguo.personnel.dao.UserDAO;
import com.xuchengguo.personnel.entity.Bill;
import com.xuchengguo.personnel.entity.LimitsPower;
import com.xuchengguo.personnel.entity.User;

import webModel.BillModel;
import webModel.UserEntity;
import webModel.UserPower;
@Service
public class UserService {
	@Autowired
	private UserPower power;
	private User user;
	private UserDAO dao;
	private int id;
	public UserService(){
		dao=new UserDAO();
	}
	public boolean addUser(UserEntity model){
		if(power.getUserPower()==1){
			int limitsID=0;
			switch(model.getLimitsPower()){
			case "管理员":
				limitsID=1;
				break;
			case "检察长":
				limitsID=2;
				break;
			case "副检察长":
				limitsID=3;
				break;
			case "检察员":
				limitsID=4;
				break;
			case "人事管理员":
				limitsID=5;
				break;
			case "普通人员":
				limitsID=6;
				break;
			}
			boolean sign=dao.addUser(model.getName(), model.getUsername(), model.getPassword(),limitsID);
			return sign;
		}else{
			return false;
		}
	}
	public ArrayList<UserEntity> getAllUsers(){
		if(power.getUserPower()==1){
			List<User> result=dao.queryAllUser();
			ArrayList<UserEntity> es=new ArrayList<>();
			for(User u:result){
				UserEntity e=new UserEntity();
				e.setId(u.getId());
				e.setLimitsPower(u.getLimitsPower().getName());
				e.setName(u.getName());
				e.setPassword(u.getPassword());
				e.setUsername(u.getUsername());
				es.add(e);
			}
			return es;
		}else{
			return null;
		}
	}
	public UserEntity getSingleUser(int id){
		if(power.getUserPower()==1){
			User assess=dao.querySingleUser(id);
			this.id=id;
			user=assess;
			UserEntity user=new UserEntity();
			user.setId(id);
			user.setLimitsPower(assess.getLimitsPower().getName());
			user.setName(assess.getName());
			user.setPassword(assess.getPassword());
			user.setUsername(assess.getUsername());
			return user;
		}else{
			return null;
		}
	}
	public boolean changeUser(UserEntity assess){
		if(power.getUserPower()==1){
			LimitsPowerDAO ldao=new LimitsPowerDAO();
			LimitsPower result=ldao.querySingleLimitsPower(assess.getLimitsPower());
			user.setLimitsPower(result);
			user.setName(assess.getName());
			user.setPassword(assess.getPassword());
			user.setUsername(assess.getUsername());
			boolean signs=dao.changeUser(user);
			return signs;
			}else{
				return false;
			}
	}
	public boolean deleteUser(int id){
		if(power.getUserPower()==1){
			boolean sign=dao.deleteUser(id);
			return sign;
		}else{
			return false;
		}
	}
	
}
