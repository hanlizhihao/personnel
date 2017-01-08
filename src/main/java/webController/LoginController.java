package webController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.LoginService;
import webEntity.UserModle;
@Controller
@RequestMapping({"/","/login"})
public class LoginController {
	@Autowired
	private LoginService loginService;
	public  LoginController(LoginService service) {
		loginService=service;
	}
	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute(new UserModle());
		return "login";
	}
	//Valid注解用于检验UserModel的属性是否满足条件
	@RequestMapping(method=RequestMethod.POST)
	public String login(@Valid UserModle userModle,Errors errors){
		if(errors.hasErrors()){
			return "login";
		}
		boolean sign= loginService.login(userModle);
		if(sign){
			return "index";
		}else{
			return "login";
		}
	}
  

}

