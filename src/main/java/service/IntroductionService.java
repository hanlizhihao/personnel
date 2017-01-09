package service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xuchengguo.personnel.dao.IntroductionDAO;
import com.xuchengguo.personnel.entity.Introduction;

@Service
public class IntroductionService {
	private IntroductionDAO dao;
	public int sign=0;
	public IntroductionService(){
		dao=new IntroductionDAO();
	}
	//请求的页码
	public List<Introduction> getIntroductions(int id){
		if(sign==0){
			List<Introduction> introductions=dao.queryBill(1);
			sign=1;
			return introductions;
		}else{
			List<Introduction> introductions=dao.queryBill(id);
			return introductions;
		}
	}
	public int getIntroductionCount(){
		int number=dao.queryPagecount();
		return number;
	}
	public Introduction getSingleIntroduction(int id){
		 return dao.querySingle(id);
	}
}
