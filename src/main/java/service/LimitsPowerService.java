package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuchengguo.personnel.dao.LimitsPowerDAO;
import com.xuchengguo.personnel.entity.LimitsPower;

import webModel.UserPower;

@Service
public class LimitsPowerService {
	@Autowired
	private UserPower power;
	private LimitsPowerDAO dao;
	public LimitsPowerService(){
		dao=new LimitsPowerDAO();
	}
	public List<LimitsPower> getAll(){
		if(power.getUserPower()==1){
			List<LimitsPower> result=dao.queryLimitsPower();
			return result;
		}else{
			return null;
		}
	}
}
