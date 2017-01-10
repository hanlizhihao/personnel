package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuchengguo.personnel.dao.IntroductionDAO;
import com.xuchengguo.personnel.entity.Introduction;

import webEntity.UserPower;

@Service
public class IntroductionService {
	@Autowired
	private UserPower power;
	private IntroductionDAO dao;
	private int sign=0;
	private List<Introduction> introductions=null;
	private int page;//请求的页码
	private int id;
	private Introduction introduction=null;
	public IntroductionService(){
		dao=new IntroductionDAO();
	}
	//请求的页码
	public List<Introduction> getIntroductions(int page){
		if(power.getUserPower()==-1){
			return null;
		}
		if(introductions!=null&&this.page==page){//缓存
			return introductions;
		}
		if(sign==0){
			introductions=dao.queryBill(1);
			sign=1;
			return introductions;
		}else{
			introductions=dao.queryBill(page);
			return introductions;
		}
	}
	public int getIntroductionCount(){
		if(power.getUserPower()==-1){
			return 0;
		}
		int number=dao.queryPagecount();
		return number;
	}
	public Introduction getSingleIntroduction(int id){
		if(power.getUserPower()==-1){
			return null;
		}
		if(introduction!=null&&this.id==id){
			return introduction;
		}
		 introduction=introductions.get(id-1);
		 this.id=id;
		 return introduction;
	}
	public boolean changeIntroduction(Introduction model){
		if(power.getUserPower()==-1){
			return false;
		}
		boolean sign=dao.changeIntroduction(model);
		return sign;
	}
}
