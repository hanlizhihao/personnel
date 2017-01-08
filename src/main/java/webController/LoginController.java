package webController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

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
import webEntity.UserModle;
@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private AnnouncementService annSerive;
	public  LoginController(LoginService service) {
		loginService=service;
	}
	@RequestMapping(value={"/","/login"},method=RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute(new UserModle());
		return "login";
	}
	//Valid注解用于检验UserModel的属性是否满足条件
	@RequestMapping(value={"/","/login"},method=RequestMethod.POST)
	public String login(@Valid UserModle userModle,Errors errors,Model model){
		if(errors.hasErrors()){
			return "login";
		}
		boolean sign= loginService.login(userModle);
		if(sign){
			List<Announcement> announcementList=annSerive.getPageAnnouncement(1);
			model.addAttribute(announcementList);
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

