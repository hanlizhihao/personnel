package webController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuchengguo.personnel.entity.Department;

import service.DepartmentService;
import webEntity.DepartmentModel;

@Controller
public class DepartmentController {
	@Autowired
	private DepartmentService service;
	@RequestMapping(value="personnel_one")
	public String toOne(Model model){
		Department department=service.getSingleDepartment(1);
		model.addAttribute("department",department );
		int sign=department.getStyle();
		if(sign==0){
			model.addAttribute("style","内设机构");
		}else{
			model.addAttribute("style","直属事业单位");
		}
		return "personnel_one";
	}
	@RequestMapping(value="personnel_two")
	public String toTwo(Model model){
		Department department=service.getSingleDepartment(2);
		model.addAttribute("department",department );
		int sign=department.getStyle();
		if(sign==0){
			model.addAttribute("style","内设机构");
		}else{
			model.addAttribute("style","直属事业单位");
		}
		return "personnel_two";
	}
	@RequestMapping(value="personnel_three")
	public String toThree(Model model){
		Department department=service.getSingleDepartment(3);
		model.addAttribute("department",department );
		int sign=department.getStyle();
		if(sign==0){
			model.addAttribute("style","内设机构");
		}else{
			model.addAttribute("style","直属事业单位");
		}
		return "personnel_three";
	}
	@RequestMapping(value="personnel_four")
	public String toFour(Model model){
		Department department=service.getSingleDepartment(4);
		model.addAttribute("department",department );
		int sign=department.getStyle();
		if(sign==0){
			model.addAttribute("style","内设机构");
		}else{
			model.addAttribute("style","直属事业单位");
		}
		return "personnel_four";
	}
	@RequestMapping(value="personnel_five")
	public String toFive(Model model){
		Department department=service.getSingleDepartment(5);
		model.addAttribute("department",department );
		int sign=department.getStyle();
		if(sign==0){
			model.addAttribute("style","内设机构");
		}else{
			model.addAttribute("style","直属事业单位");
		}
		return "personnel_five";
	}
	@RequestMapping(value="personnel_six")
	public String toSix(Model model){
		Department department=service.getSingleDepartment(6);
		model.addAttribute("department",department );
		int sign=department.getStyle();
		if(sign==0){
			model.addAttribute("style","内设机构");
		}else{
			model.addAttribute("style","直属事业单位");
		}
		return "personnel_six";
	}
	@RequestMapping(value="personnel_seven")
	public String toSeven(Model model){
		Department department=service.getSingleDepartment(7);
		model.addAttribute("department",department );
		int sign=department.getStyle();
		if(sign==0){
			model.addAttribute("style","内设机构");
		}else{
			model.addAttribute("style","直属事业单位");
		}
		return "personnel_seven";
	}
	@RequestMapping(value="personnel_eight")
	public String toEight(Model model){
		Department department=service.getSingleDepartment(8);
		model.addAttribute("department",department );
		int sign=department.getStyle();
		if(sign==0){
			model.addAttribute("style","内设机构");
		}else{
			model.addAttribute("style","直属事业单位");
		}
		return "personnel_eight";
	}
	@RequestMapping(value="personnel_nine")
	public String toNine(Model model){
		Department department=service.getSingleDepartment(9);
		model.addAttribute("department",department );
		int sign=department.getStyle();
		if(sign==0){
			model.addAttribute("style","内设机构");
		}else{
			model.addAttribute("style","直属事业单位");
		}
		return "personnel_nine";
	}
	@RequestMapping(value="personnel_ten")
	public String toTen(Model model){
		Department department=service.getSingleDepartment(10);
		model.addAttribute("department",department );
		int sign=department.getStyle();
		if(sign==0){
			model.addAttribute("style","内设机构");
		}else{
			model.addAttribute("style","直属事业单位");
		}
		return "personnel_ten";
	}
	@RequestMapping(value="personnel_eleven")
	public String toEleven(Model model){
		Department department=service.getSingleDepartment(11);
		model.addAttribute("department",department );
		int sign=department.getStyle();
		if(sign==0){
			model.addAttribute("style","内设机构");
		}else{
			model.addAttribute("style","直属事业单位");
		}
		return "personnel_eleven";
	}
	@RequestMapping(value="personnel_twelve")
	public String toTwelve(Model model){
		Department department=service.getSingleDepartment(12);
		model.addAttribute("department",department );
		int sign=department.getStyle();
		if(sign==0){
			model.addAttribute("style","内设机构");
		}else{
			model.addAttribute("style","直属事业单位");
		}
		return "personnel_twelve";
	}
	@RequestMapping(value="/department/change",method=RequestMethod.POST)
	public String changeDepartment(DepartmentModel intr,Model model){
		if(service.changeDepartment(intr)){
			return "redirect:../../index";
		}else{
			model.addAttribute("url","../../");
			return "error";
		}
	}
	@RequestMapping(value="/department/add",method=RequestMethod.POST)
	public String addDepartment(DepartmentModel intr,Model model){
		if(service.addDepartment(intr)){
			return "redirect:../../index";
		}else{
			model.addAttribute("url","../../");
			return "error";
		}
	}
}
