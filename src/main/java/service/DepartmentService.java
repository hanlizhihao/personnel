package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuchengguo.personnel.dao.DepartmentDAO;
import com.xuchengguo.personnel.entity.Department;

import webModel.DepartmentModel;
import webModel.UserPower;

@Service
public class DepartmentService {
	private DepartmentDAO dao;
	@Autowired
	private UserPower power;
	private List<Department> departments=null;
	private int id;//用于标识在改操作之前，所查的id是哪个
	public DepartmentService(){
		dao=new DepartmentDAO();
		departments=dao.queryDepartment();
	}
	public Department getSingleDepartment(int id){
		if(power.getUserPower()==-1){
			return null;
		}
		return departments.get(id-1);
	}
	public List<Department> getAllDepartment(){
		if(power.getUserPower()==-1){
			return null;
		}
		return departments;
	}
	//更改部门信息作了权限控制，只允许管理员更改
	public boolean changeDepartment(DepartmentModel model){
		if(power.getUserPower()==1){
			Department department=departments.get(id);
			department.setDepartmentJob(model.getJob());
			department.setId(model.getId());
			department.setName(model.getName());
			int signStyle;
			if("内设机构".equals(model.getStyle())){
				signStyle=0;
			}else{
				signStyle=1;
			}
			department.setStyle(signStyle);
			boolean sign=dao.changeDepartment(department);
			return sign;
		}else{
			return false;
		}
	}
	public boolean addDepartment(DepartmentModel model){
		if(power.getUserPower()==1){
			int signStyle;
			if("内设机构".equals(model.getStyle())){
				signStyle=0;
			}else{
				signStyle=1;
			}
			boolean sign=dao.addDepartment(model.getName(),model.getJob(),signStyle);
			return sign;
		}else{
			return false;
		}
	}
}
