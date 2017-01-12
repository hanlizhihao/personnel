package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuchengguo.personnel.dao.AssessDAO;
import com.xuchengguo.personnel.entity.Assess;

import webEntity.AssessModel;
import webEntity.UserPower;

@Service
public class AssessService {
	@Autowired
	private UserPower power;
	private AssessDAO dao;
	private int page;
	private List<Assess> anns;
	private int id;//缓存id
	public AssessService(){
		dao=new AssessDAO();
	}
	public boolean addAssess(AssessModel model){
		if(power.getUserPower()==1||power.getUserPower()==2||power.getUserPower()==3||power.getUserPower()==5){
			boolean sign=dao.addCheck(model.getName(),model.getJob(), model.getQuality(),model.getAbility(),model.getOutstanding());
			return sign;
		}else{
			return false;
		}
	}
	public List<Assess> getPageAssess(int page){
		if(power.getUserPower()==1||power.getUserPower()==2||power.getUserPower()==3||power.getUserPower()==5){
			if(null!=anns&&this.page==page){
				return anns;//缓存策略
			}
			anns=dao.queryPage(page);
			return anns;
		}else{
			return null;
		}
	}
	public int getPageCount(){
		return dao.queryPagecount();
	}
	public Assess getSingleAssess(int id){
		if(power.getUserPower()==1||power.getUserPower()==2||power.getUserPower()==3||power.getUserPower()==5){
			Assess assess=dao.querySingleAssess(id);
			this.id=id;
			return assess;
		}else{
			return null;
		}
	}
	public boolean changeAssess(Assess assess){
		if(power.getUserPower()==1||power.getUserPower()==2||power.getUserPower()==3||power.getUserPower()==5){
			assess.setId(id);//将缓存的id注入，防止出现将id更改以后不能正确更新
			boolean sign=dao.changeCheck(assess);
			return sign;
		}else{
			return false;
		}
	}
	public boolean deleteAssess(int id){
		if(power.getUserPower()==1||power.getUserPower()==2||power.getUserPower()==3||power.getUserPower()==5){
			boolean sign=dao.deleteCheck(id);
			return sign;
		}else{
			return false;
		}
	}
	
}
