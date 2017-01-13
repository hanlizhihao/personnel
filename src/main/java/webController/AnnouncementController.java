package webController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuchengguo.personnel.entity.Announcement;
import service.AnnouncementService;
import webModel.AnnouncementModel;
import webModel.IntroductionModel;

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
		System.out.println(intr.getContent());
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
	@RequestMapping(value="add_annoucement")
	public String toAddAnnouncement(){
		return "announcement_add";
	}
	//根据路径参数，返回分页信息
	@RequestMapping(value="/ann/{id}", method=RequestMethod.GET)
	public String announcements(@PathVariable String id,Model model){//分页显示
		List<Announcement> introductions=service.getPageAnnouncement(Integer.valueOf(id));
		int pageCount=service.getPageCount();
		if(introductions==null){
			model.addAttribute("url","../");//标识向上退几级
			return "error";
		}
		model.addAttribute(introductions);
		model.addAttribute(pageCount);
		return "announcement_manage";
	}
	@RequestMapping(value="manage_annoucement")
	public String toManageAnnouncement(Model model){
		List<Announcement> announcementList=service.getPageAnnouncement(1);
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
		return "announcement_manage";
		}
}
