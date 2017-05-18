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
	//登录页，主页，将跳转到登录页
	@RequestMapping(value={"/","/login"},method=RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute(new UserModel());
		return "login";
	}
	@RequestMapping(value="/gongkai",method=RequestMethod.GET)
	public String gongkai(Model model){
		List<Announcement> announcementList=annService.getAllAnnouncement();
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
			//分页获取第一页的检务公告
			List<Announcement> announcementList=annService.getPageAnnouncement(1);
			//将检务公告集合存储到Model中，供视图使用
			//Model中的这些数据，将会被Thymeleaf去解析，渲染页面
			model.addAttribute(announcementList);
			//检务公告的数据总行数，放在Model中，以供视图使用
			int pageCount=annService.getPageCount();
			model.addAttribute("pageCount",pageCount);
			return "index";
		}else{
			return "login";
		}
	}
	//等请求发送到/index时，会先验证权限，是否为-1，如果为-1则没登录，则重定向到登录页
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(Model model){
		if(power.getUserPower()==-1){
			//重定向
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

