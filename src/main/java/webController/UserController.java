package webController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.UserService;
import webModel.UserEntity;

@Controller
public class UserController {
	@Autowired
	private UserService service;
	public UserController(){
		service=new UserService();
	}
	@RequestMapping(value={"/user_query","/user_manage"},method=RequestMethod.GET)
	public String user(Model model){
		List<UserEntity> introductions=service.getAllUsers();
		if(introductions==null){
			model.addAttribute("url","");//获取introductions
			return "error";
		}
		model.addAttribute("introductionList",introductions);
		return "user";
	}
	@RequestMapping(value="/user/detials/{id}",method=RequestMethod.GET)
	public String singleUser(Model model,@PathVariable String id){
		UserEntity assess=service.getSingleUser(Integer.valueOf(id));
		if(assess==null){
			model.addAttribute("url","../../");
			return "error";
		}
		model.addAttribute("introduction",assess);
		return "user_detials";
	}
	@RequestMapping(value="/user/change",method=RequestMethod.POST)
	public String changeUser(UserEntity intr,Model model){
		if(service.changeUser(intr)){
			return "redirect:../user_query";
		}else{
			model.addAttribute("url","../");
			return "error";
		}
	}	
	//实际做添加的处理地址
	@RequestMapping(value="/user/adds",method=RequestMethod.GET)
	public String addUser(UserEntity intr,Model model){
		if(service.addUser(intr)){
			return "redirect:../user_query";
		}else{
			model.addAttribute("url","../");
			return "error";
		}
	}
	@RequestMapping(value="/user/delete/{id}")
	public String deleteUser(Model model,@PathVariable String id){
		if(service.deleteUser(Integer.valueOf(id))){
			return "redirect:../../bill";
		}else{
			model.addAttribute("url","../../");
			return "error";
		}
	}
}
