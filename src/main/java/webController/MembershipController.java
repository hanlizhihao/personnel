package webController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuchengguo.personnel.dao.IntroductionDAO;
import com.xuchengguo.personnel.entity.Introduction;
import com.xuchengguo.personnel.entity.Membership;

import service.MembershipService;
import webModel.MembershipModel;

@Controller
public class MembershipController {
	@Autowired
	private MembershipService service;
	@Autowired
	private IntroductionDAO introductionDAO;
	//以json格式返回MembershipModel数据
	@RequestMapping(value="/membershipPaging/{page}",produces="application/json")
	@ResponseBody
	public List<MembershipModel> getMembershipByPage(@PathVariable String page){
		List<MembershipModel> result=new ArrayList<>();
		result=service.getMemberships(Integer.valueOf(page));
		return result;
	}
	//将数据放在Model上，转向到membership
	@RequestMapping(value="/membership",method=RequestMethod.GET)
	public String membership(Model model){
		ArrayList<MembershipModel> introductions=service.getMemberships(1);
		int pageCount=service.getMembershipCount();
		if(introductions==null){
			model.addAttribute("url","");//获取introductions
			return "error";
		}
		model.addAttribute("membershipList",introductions);
		model.addAttribute("pageCount",pageCount);
		return "membership";
	}
	//根据路径参数，返回分页信息
	@RequestMapping(value="/membership/{id}", method=RequestMethod.GET)
	public String memberships(@PathVariable String id,Model model){//分页显示
		ArrayList<MembershipModel> introductions=service.getMemberships(Integer.valueOf(id));
		int pageCount=service.getMembershipCount();
		if(introductions==null){
			model.addAttribute("url","../");//标识向上退几级
			return "error";
		}
		model.addAttribute("membershipList",introductions);
		model.addAttribute(pageCount);
		return "membership";
	}
	//详情页处理
	@RequestMapping(value="/membership/detials/{id}",method=RequestMethod.GET)
	public String singleMembership(Model model,@PathVariable String id){
		Membership introduction=service.getSingleMembership(Integer.valueOf(id));
		if(introduction==null){
			model.addAttribute("url","../../");
			return "error";
		}
		model.addAttribute("department", introduction.getDepartmentId().getName());
		model.addAttribute("introduction",introduction);//为了简化页面的更改量
		return "membership_detials";
	}
	@RequestMapping(value="/membership/change",method=RequestMethod.POST)
	public String changeMembership(MembershipModel intr,Model model){
		if(service.changeMembership(intr)){
			return "redirect:../membership";
		}else{
			model.addAttribute("url","../../");
			return "error";
		}
	}
	@RequestMapping(value="/membership/add/{id}",method=RequestMethod.GET)
	public String toAddMembership(@PathVariable String id,Model model){
		Introduction introduction=introductionDAO.querySingle(Integer.valueOf(id));
		model.addAttribute("introduction", introduction);
		int departmentID=introduction.getDepartmentId();
		switch(departmentID){
		case 1:model.addAttribute("department", "控告申诉检察部门");break;
		case 2:model.addAttribute("department", "反贪污贿赂部门");break;
		case 3:model.addAttribute("department", "反渎职侵权部门");break;
		case 4:model.addAttribute("department", "侦查监督部门");break;
		case 5:model.addAttribute("department", "公诉部门");break;
		case 6:model.addAttribute("department", "监所检察部门");break;
		case 7:model.addAttribute("department", "民事行政检察部门");break;
		case 8:model.addAttribute("department", "职务犯罪预防部门");break;
		case 9:model.addAttribute("department", "案件管理部门");break;
		case 10:model.addAttribute("department", "检察技术部门");break;
		case 11:model.addAttribute("department", "纪检、监察部门");break;
		case 12:model.addAttribute("department", "机关服务中心");break;
		}
		model.addAttribute("introduction",introduction);
		return "membership_add";
	}
	//实际做添加的处理方法
	@RequestMapping(value="/membership/adds",method=RequestMethod.POST)
	public String addMembership(MembershipModel intr,Model model){
		System.out.println("人事记录传过来的参数是：");
		System.out.println(intr.getNextJob());System.out.println(intr.getRemoveJob());
		System.out.println(intr.getChangeReason());
		if(service.addMembership(intr)){
			return "redirect:../membership";
		}else{
			model.addAttribute("url","../");
			return "error";
		}
	}
	@RequestMapping(value="/membership/cancel/{id}")
	public String cancelMembership(Model model,@PathVariable String id){
		if(service.cancelMembership(Integer.valueOf(id))){
			return "redirect:../../membership";
		}else{
			model.addAttribute("url","../../");
			return "error";
		}
	}
	@RequestMapping(value="/membership/approve/{id}")
	public String approveMembership(Model model,@PathVariable String id){
		if(service.approveMembership(Integer.valueOf(id))){
			return "redirect:../../membership";
		}else{
			model.addAttribute("url","../../");
			return "error";
		}
	}
}
