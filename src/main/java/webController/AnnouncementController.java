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

import com.xuchengguo.personnel.entity.Announcement;
import service.AnnouncementService;
import webModel.AnnouncementModel;

@Controller
public class AnnouncementController {
	@Autowired
	private AnnouncementService service;
	public AnnouncementController(AnnouncementService service){
		this.service=service;
	}
	@RequestMapping(value="/ann/detials/{id}",method=RequestMethod.GET)
	public String annDetials(@PathVariable String id,Model model){
		Announcement ann=service.getSingleAnnouncement(Integer.valueOf(id));
		if(ann==null){
			model.addAttribute("url","../");//标识向上退几级
			return "error";
		}
		model.addAttribute("ann",ann);
		return "announcement_detials";
		}
	@RequestMapping(value="/ann/change",method=RequestMethod.POST)
	public String changeAnnouncement(Announcement intr,Model model){
		if(service.changeAnnouncement(intr)){
			return "redirect:../index";
		}else{
			model.addAttribute("url","../");
			return "error";
		}
	}
	@RequestMapping(value="/ann/adds",method=RequestMethod.POST)
	public String addAnnouncement(AnnouncementModel intr,Model model){
		if(service.addAnnouncement(intr)){
			return "redirect:../index";
		}else{
			model.addAttribute("url","../");
			return "error";
		}
	}
	@RequestMapping(value="/add_announcement",method=RequestMethod.GET)
	public String toAddAnnouncement(){
		return "announcement_add";
	}
	/**
	 * 分页announcement，根据page返回json类型的announcement信息
	 * @param page 请求页
	 * @return 
	 */
	@RequestMapping(value="/announcements/{page}",produces="application/json")
	@ResponseBody
	public List<Announcement> getAnnouncements(@PathVariable String page){
		//获取所有数据的行数
		List<Announcement> result=new ArrayList<>();
		int count=service.getPageCount();
		//获取Announcement的数据的页数
		int pageCount=service.getPageCount()/10;
		if(count%10!=0){
			pageCount++;
		}
		//接下来判断传递的参数是否符合1-pageCount
		if(Integer.valueOf(page)<=pageCount){
			result=service.getPageAnnouncement(Integer.valueOf(page));
		}
		return result;
	}
	//根据路径参数，返回分页信息
	@RequestMapping(value="/ann/{id}", method=RequestMethod.GET)
	public String announcements(@PathVariable String id,Model model){//分页显示
		System.out.println("分页请求接受");
		List<Announcement> introductions=service.getPageAnnouncement(Integer.valueOf(id));
		int pageCount=service.getPageCount();
		if(introductions==null){
			model.addAttribute("url","../");//标识向上退几级
			return "error";
		}
		model.addAttribute("announcementList",introductions);
		model.addAttribute(pageCount);
		return "announcement_manage";
	}
	@RequestMapping(value="/manage_announcement")
	public String toManageAnnouncement(Model model){
		List<Announcement> announcementList=service.getPageAnnouncement(1);
		if(announcementList==null){
			model.addAttribute("url","");//标识向上退几级
			return "error";
		}
		model.addAttribute(announcementList);
		int pageCount=service.getPageCount();
		model.addAttribute("pageCount",pageCount);
		return "announcement_manage";
	}
	@RequestMapping(value="/ann/delete/{id}",method=RequestMethod.GET)
	public String annDelete(@PathVariable String id,Model model){
		boolean sign=service.deleteAnnouncement(Integer.valueOf(id));
		if(sign==false){
			model.addAttribute("url","../../");//标识向上退几级
			return "error";
		}
		return "redirect:../../index";
		}
}
