package service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xuchengguo.personnel.dao.IntroductionDAO;
import com.xuchengguo.personnel.entity.Introduction;

@Service
public class IntroductionService {
	private IntroductionDAO dao;
	private int sign=0;
	private List<Introduction> introductions;
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
			introductions=dao.queryBill(id);
			return introductions;
		}
	}
	public int getIntroductionCount(){
		int number=dao.queryPagecount();
		return number;
	}
	public Introduction getSingleIntroduction(int id){
		 return introductions.get(id-1);
	}
	public boolean changeIntroduction(Introduction model){
		boolean sign=dao.changeIntroduction(model);
		return sign;
	}
}
