package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuchengguo.personnel.dao.DepartmentDAO;
import com.xuchengguo.personnel.dao.MembershipDAO;
import com.xuchengguo.personnel.entity.Department;
import com.xuchengguo.personnel.entity.Membership;

import webEntity.MembershipModel;
import webEntity.UserPower;

@Service
public class MembershipService {
	@Autowired
	private UserPower power;
	private MembershipDAO dao;
	private int sign=0;//标识第几次查询
	private List<Membership> introductions=null;
	private int id;//为了防止在改动人事信息时，将id更改，故在此缓存上一次查询单一实例时的id
	private Membership introduction=null;
	private int page;
	public MembershipService(){
		dao=new MembershipDAO();
	}
	//请求的页码
	public ArrayList<MembershipModel> getMemberships(int page){
		//只允许管理员，检察长，副检察长，人事管理员来更改人事
		if(power.getUserPower()==-1||power.getUserPower()==4||power.getUserPower()==6){
			return null;
		}
		if(sign==0){
			introductions=dao.queryMembership(1);
			sign=1;
		}else{
			introductions=dao.queryMembership(page);
		}
		ArrayList<MembershipModel> result=new ArrayList<>();
		//将Membership装在到MembershipModel
		for(Membership i:introductions){
			if("".equals(i.getNextJob())&&"".equals(i.getRemoveJob())){
				continue;
			}
			MembershipModel in=new MembershipModel();
			in.setAge(i.getAge());
			Department sign=i.getDepartmentId();
			in.setDepartment(sign.getName());
			in.setId(i.getId());
			in.setName(i.getName());
			in.setNowJob(i.getNowJob());
			in.setResume(i.getResume());
			in.setRewardsPunishment(i.getRewardsPunishment());
			in.setSex(i.getSex());
			in.setRemoveJob(i.getRemoveJob());
			in.setNextJob(i.getNextJob());
			in.setChangeReason(i.getChangeReason());
			result.add(in);
		}
		this.page=page;
		return result;
	}
	public int getMembershipCount(){
		if(power.getUserPower()==-1||power.getUserPower()==4||power.getUserPower()==6){
			return 0;
		}
		int number=dao.queryPagecount();
		return number;
	}
	//这个注意返回的是Membership
	public Membership getSingleMembership(int id){
		if(power.getUserPower()==-1||power.getUserPower()==4||power.getUserPower()==6){
			return null;
		}
		if(introduction!=null&&this.id==id){
			return introduction;
		}
		if(id-1>=introductions.size()&&page==1){
			introduction=introductions.get(introductions.size()-1);
		}else if(id-1<introductions.size()&&page==1){
			introduction=introductions.get(id-1);
		}else if(page!=1){
			introduction=dao.querySingleMembership(id);//索引问题
		}
		 this.id=id;
		 return introduction;
	}
	//一种是取消人事变动，一种是同意人事变动
	public boolean cancelMembership(int id){
		if(power.getUserPower()==-1||power.getUserPower()==4||power.getUserPower()==6){
			return false;
		}
		boolean sign=dao.cancelMembership(id);
		introduction=null;//更改以后清空缓存
		introductions=null;
		return sign;
	}
	public boolean approveMembership(int id){
		if(power.getUserPower()==-1||power.getUserPower()==4||power.getUserPower()==6){
			return false;
		}
		boolean sign=dao.approveMemebership(id);
		introduction=null;//更改以后清空缓存
		introductions=null;
		return sign;
	}
	public boolean changeMembership(MembershipModel model){
		if(power.getUserPower()==-1||power.getUserPower()==4||power.getUserPower()==6){
			return false;
		}
		Membership membership=getSingleMembership(id);
		if(membership==null){
			return false;
		}
		membership.setAge(model.getAge());
		String departmentName=model.getDepartment();
		DepartmentDAO departmentDAO=new DepartmentDAO();
		List<Department> departemnts=departmentDAO.queryDepartment();
		for(Department d:departemnts){
			if(d.getName().equals(departmentName)){
				membership.setDepartmentId(d);
				break;
			}
		}
		membership.setId(model.getId());
		membership.setName(model.getName());
		membership.setNowJob(model.getNowJob());
		membership.setResume(model.getResume());
		membership.setRewardsPunishment(model.getRewardsPunishment());
		membership.setSex(model.getSex());
		membership.setRemoveJob(model.getRemoveJob());
		membership.setNextJob(model.getNextJob());
		membership.setChangeReason(model.getChangeReason());
		membership.setAge(model.getAge());
		boolean success=dao.changeMembership(membership);
		introduction=null;//更改以后清空缓存
		introductions=null;
		return success;
	}
	public boolean addMembership(MembershipModel model){
		if(power.getUserPower()==-1||power.getUserPower()==4||power.getUserPower()==6){
			return false;
		}
		Membership membership=getSingleMembership(id);
		if(membership==null){
			return false;
		}
		membership.setAge(model.getAge());
		String departmentName=model.getDepartment();
		DepartmentDAO departmentDAO=new DepartmentDAO();
		List<Department> departemnts=departmentDAO.queryDepartment();
		for(Department d:departemnts){
			if(d.getName().equals(departmentName)){
				membership.setDepartmentId(d);
				break;
			}
		}
		membership.setId(model.getId());
		membership.setName(model.getName());
		membership.setNowJob(model.getNowJob());
		membership.setResume(model.getResume());
		membership.setRewardsPunishment(model.getRewardsPunishment());
		membership.setSex(model.getSex());
		membership.setRemoveJob(model.getRemoveJob());
		membership.setChangeReason(model.getChangeReason());
		membership.setAge(model.getAge());
		membership.setNextJob(model.getNextJob());
		boolean success=dao.addMembership(membership, model.getId());
		introduction=null;//更改以后清空缓存
		introductions=null;
		return success;
	}
}
