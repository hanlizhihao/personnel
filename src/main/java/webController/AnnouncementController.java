package webController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuchengguo.personnel.entity.Announcement;
import service.AnnouncementService;

@Controller
@RequestMapping("/ann")
public class AnnouncementController {
	@Autowired
	private AnnouncementService service;
	public AnnouncementController(AnnouncementService service){
		this.service=service;
	}
	@RequestMapping(value="detials/{id}",method=RequestMethod.GET)
	public String annDetials(@PathVariable String id,Model model){
		Announcement ann=service.getSingleAnnouncement(Integer.valueOf(id));
		if(ann==null){
			model.addAttribute("url","../../");//标识向上退几级
			return "error";
		}
		model.addAttribute("ann",ann);
		return "announcement_detials";
		}
	@RequestMapping(value="/change",method=RequestMethod.POST)
	public String changeIntroduction(Announcement intr,Model model){
		System.out.println(intr.getContent());
		if(service.changeAnnouncement(intr)){
			return "redirect:../index";
		}else{
			model.addAttribute("url","../../");
			return "error";
		}
	}
}
