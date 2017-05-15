package webController;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuchengguo.personnel.entity.Membership;

import service.MembershipService;
import webModel.MembershipModel;

@Controller
public class MembershipController {
	@Autowired
	private MembershipService service;
	public MembershipController(){
		service=new MembershipService();
	}
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
	@RequestMapping(value="/membership/add",method=RequestMethod.GET)
	public String toAddMembership(){
		return "membership_add";
	}
	//实际做添加的处理方法
	@RequestMapping(value="/membership/adds",method=RequestMethod.POST)
	public String addMembership(MembershipModel intr,Model model){
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
