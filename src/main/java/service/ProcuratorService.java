package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuchengguo.personnel.dao.ProcuratorDAO;
import com.xuchengguo.personnel.entity.Procurator;

import webModel.ProcuratorModel;
import webModel.UserPower;

@Service
public class ProcuratorService {
	private int id;//为了防止在改动检查官等级信息时，将id更改，故在此缓存上一次查询单一实例时的id
	@Autowired
	private UserPower power;
	private ProcuratorDAO dao;
	public ProcuratorService(){
		dao=new ProcuratorDAO();
	}
	public boolean addProcurator(ProcuratorModel model){
		if(power.getUserPower()==1||power.getUserPower()==5){
			Procurator procurator=new Procurator();
			procurator.setAge(model.getAge());
			procurator.setGradeId(model.getGradeId());
			procurator.setId(model.getId());
			procurator.setName(model.getName());
			procurator.setResume(model.getResume());
			switch(model.getGradeId()){
			case 0: procurator.setGrade("首席大检察官"); 
			break;
			case 1:procurator.setGrade("一级大检察官");break;
			case 2:procurator.setGrade("二级大检察官");break;
			case 3:procurator.setGrade("一级高级检察官");break;
			case 4:procurator.setGrade("二级高级检察官");break;
			case 5:procurator.setGrade("三级高级检察官");break;
			case 6:procurator.setGrade("四级高级检察官");break;
			case 7:procurator.setGrade("一级检察官");break;
			case 8:procurator.setGrade("二级检察官");break;
			case 9:procurator.setGrade("三级检察官");break;
			case 10:procurator.setGrade("四级检察官");break;
			case 11:procurator.setGrade("五级检察官");break;
			}
			boolean success= dao.addProcurator(procurator.getName(),procurator.getGrade(),procurator.getAge(),
					procurator.getResume(),procurator.getGradeId());
			return success;
		}
		return false;
	}
	public List<Procurator> getAllProcurator(){
		if(power.getUserPower()==1||power.getUserPower()==5){
			List<Procurator> result=dao.queryProcurator();
			return result;
		}
		return null;
	}
	public Procurator getSingleProcurator(int id){
		if(power.getUserPower()==1||power.getUserPower()==5){
			Procurator procurator=dao.querySingleProcurator(id);
			this.id=id;
			return procurator;
		}
		return null;
	}
	public boolean changeProcurator(ProcuratorModel model){
		if(power.getUserPower()==1||power.getUserPower()==5){
			Procurator procurator=dao.querySingleProcurator(id);
			if(procurator==null){
				System.out.println("procurator为空");
				return false;
			}
			procurator.setAge(model.getAge());
			switch(model.getGradeId()){
			case 0: procurator.setGrade("首席大检察官"); 
			break;
			case 1:procurator.setGrade("一级大检察官");break;
			case 2:procurator.setGrade("二级大检察官");break;
			case 3:procurator.setGrade("一级高级检察官");break;
			case 4:procurator.setGrade("二级高级检察官");break;
			case 5:procurator.setGrade("三级高级检察官");break;
			case 6:procurator.setGrade("四级高级检察官");break;
			case 7:procurator.setGrade("一级检察官");break;
			case 8:procurator.setGrade("二级检察官");break;
			case 9:procurator.setGrade("三级检察官");break;
			case 10:procurator.setGrade("四级检察官");break;
			case 11:procurator.setGrade("五级检察官");break;
			}
			procurator.setGradeId(model.getGradeId());
			procurator.setId(model.getId());
			procurator.setName(model.getName());
			procurator.setResume(model.getResume());
			boolean success=dao.changeProcurator(procurator);
			return success;
		}
		return false;
	}
	public boolean deleteProcurator(int id){
		if(power.getUserPower()==1||power.getUserPower()==5){
			boolean success=dao.deleteProcurator(id);
			return success;
		}
		return false;
	}
}
