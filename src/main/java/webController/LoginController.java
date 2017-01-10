package webController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuchengguo.personnel.entity.Announcement;

import service.AnnouncementService;
import service.LoginService;
import webEntity.UserModel;
import webEntity.UserPower;
@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private AnnouncementService annService;
	@Autowired
	private UserPower power;
	public  LoginController(LoginService service) {
		loginService=service;
	}
	@RequestMapping(value={"/","/login"},method=RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute(new UserModel());
		return "login";
	}
	//Valid注解用于检验UserModel的属性是否满足条件
	@RequestMapping(value={"/","/login"},method=RequestMethod.POST)
	public String login(@Valid UserModel userModel,Errors errors,Model model){
		if(errors.hasErrors()){
			return "login";
		}
		boolean sign= loginService.login(userModel);
		if(sign){
			List<Announcement> announcementList=annService.getPageAnnouncement(1);
			model.addAttribute(announcementList);
			int pageCount=annService.getPageCount();
			model.addAttribute("pageCount",pageCount);
			power.setUserPower(loginService.getLimitsPower());
			return "index";
		}else{
			return "login";
		}
	}
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(){
		return "login";
	}
}

