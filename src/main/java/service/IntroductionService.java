package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuchengguo.personnel.dao.IntroductionDAO;
import com.xuchengguo.personnel.entity.Introduction;

import webModel.IntroductionModel;
import webModel.UserPower;
/**
 * @author Administrator
 *
 */
@Service
public class IntroductionService {
	@Autowired
	private UserPower power;
	private IntroductionDAO dao;
	public IntroductionService(){
		dao=new IntroductionDAO();
	}
	//请求的页码
	public ArrayList<IntroductionModel> getIntroductions(int page){
		List<Introduction> introductions=new ArrayList<>();
		if(power.getUserPower()==-1){
			return null;
		}
		//查找第一页的信息
		introductions=dao.queryIntroduction(1);
		ArrayList<IntroductionModel> result=new ArrayList<>();
		//将introduction装在到introductionModel
		for(Introduction i:introductions){
			IntroductionModel in=new IntroductionModel();
			in.setAge(i.getAge());
			in.setBirthday(i.getBirthday());
			in.setBirthplace(i.getBirthplace());
			int sign=i.getDepartmentId();
			switch(sign){
			case 1:in.setDepartment("控告申诉检察部门");break;
			case 2:in.setDepartment("反贪污贿赂部门");break;
			case 3:in.setDepartment("反渎职侵权部门");break;
			case 4:in.setDepartment("侦查监督部门");break;
			case 5:in.setDepartment("公诉部门");break;
			case 6:in.setDepartment("监所检察部门");break;
			case 7:in.setDepartment("民事行政检察部门");break;
			case 8:in.setDepartment("职务犯罪预防部门");break;
			case 9:in.setDepartment("案件管理部门");break;
			case 10:in.setDepartment("检察技术部门");break;
			case 11:in.setDepartment("纪检、监察部门");break;
			case 12:in.setDepartment("机关服务中心");break;
			}
			in.setEducationBackground(i.getEducationBackground());
			in.setGraduationSchool(i.getGraduationSchool());
			in.setId(i.getId());
			in.setJoinPartyTime(i.getJoinPartyTime());
			in.setJoinWorkTime(i.getJoinWorkTime());
			in.setName(i.getName());
			in.setNation(i.getNation());
			in.setNativePlace(i.getNativePlace());
			in.setNowJob(i.getNowJob());
			in.setPhysicalCondition(i.getPhysicalCondition());
			in.setResume(i.getResume());
			in.setRewardsPunishment(i.getRewardsPunishment());
			in.setSex(i.getSex());
			result.add(in);
		}
		return result;
	}
	public int getIntroductionCount(){
		if(power.getUserPower()==-1){
			return 0;
		}
		int number=dao.queryPagecount();
		return number;
	}
	public Introduction getSingleIntroduction(int id){
		Introduction introduction=new Introduction();
		if(power.getUserPower()==-1){
			return null;
		}
		introduction=dao.querySingle(id);
		 return introduction;
	}
	public boolean deleteIntroduction(int id){
		if(power.getUserPower()==-1){
			return false;
		}
		boolean sign=dao.deleteIntroduction(id);
		return sign;
	}
	public boolean changeIntroduction(Introduction model){
		if(power.getUserPower()==-1){
			return false;
		}
		boolean sign=dao.changeIntroduction(model);
		return sign;
	}
	public boolean addIntroduction(Introduction model){
		if(power.getUserPower()==-1){
			return false;
		}
		boolean sign=dao.addIntroduction(model, model.getDepartmentId());
		return sign;
	}
}
