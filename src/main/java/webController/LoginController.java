package webController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuchengguo.personnel.entity.Announcement;

import service.AnnouncementService;
import service.LoginService;
import webModel.UserModel;
import webModel.UserPower;
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
	@RequestMapping(value="/gongkai",method=RequestMethod.GET)
	public String gongkai(Model model){
		List<Announcement> announcementList=annService.getPageAnnouncement(1);
		model.addAttribute(announcementList);
		int pageCount=annService.getPageCount();
		model.addAttribute("pageCount",pageCount);
		return "announcement";
	}
	@RequestMapping(value="/gongkai/detials/{id}",method=RequestMethod.GET)
	public String gongkaiDetails(Model model,@PathVariable String id){
		Announcement ann=annService.getSingleAnnouncement(Integer.valueOf(id));
		if(ann==null){
			model.addAttribute("url","../");//标识向上退几级
			return "error";
		}
		model.addAttribute("ann",ann);
		return "detials";
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
			return "index";
		}else{
			return "login";
		}
	}
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(Model model){
		if(power.getUserPower()==-1){
			return "redirect:/login";
		}else{
			List<Announcement> announcementList=annService.getPageAnnouncement(1);
			model.addAttribute(announcementList);
			int pageCount=annService.getPageCount();
			model.addAttribute("pageCount",pageCount);
			return "index";
		}
	}
}

