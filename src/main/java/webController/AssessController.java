package webController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuchengguo.personnel.entity.Assess;
import service.AssessService;
import webEntity.AssessModel;
/**
 * 综述考核管理：添加使用model，更改使用entity,
 * @author Administrator
 *
 */
@Controller
public class AssessController {
	@Autowired
	private AssessService service;
	public AssessController(){
		service=new AssessService();
	}
	@RequestMapping(value="/assess",method=RequestMethod.GET)
	public String assess(Model model){
		List<Assess> introductions=service.getPageAssess(1);
		int pageCount=service.getPageCount();
		if(introductions==null){
			model.addAttribute("url","");//获取introductions
			return "error";
		}
		model.addAttribute("introductionList",introductions);
		model.addAttribute("pageCount",pageCount);
		return "assess";
	}
	//根据路径参数，返回分页信息
	@RequestMapping(value="/assess/{id}", method=RequestMethod.GET)
	public String assesses(@PathVariable String id,Model model){//分页显示
		List<Assess> introductions=service.getPageAssess(Integer.valueOf(id));
		int pageCount=service.getPageCount();
		if(introductions==null){
			model.addAttribute("url","../");//标识向上退几级
			return "error";
		}
		model.addAttribute("introductionList",introductions);
		model.addAttribute(pageCount);
		return "assess";
	}
	@RequestMapping(value="/assess/detials/{id}",method=RequestMethod.GET)
	public String singleIntroducton(Model model,@PathVariable String id){
		Assess assess=service.getSingleAssess(Integer.valueOf(id));
		if(assess==null){
			model.addAttribute("url","../../");
			return "error";
		}
		model.addAttribute("introduction",assess);
		return "assess_detials";
	}
	@RequestMapping(value="/assess/change",method=RequestMethod.POST)
	public String changeAssess(Assess intr,Model model){
		if(service.changeAssess(intr)){
			return "redirect:../assess";
		}else{
			model.addAttribute("url","../../");
			return "error";
		}
	}
	//实际做添加的处理地址
	@RequestMapping(value="/assess/adds",method=RequestMethod.GET)
	public String addAssess(AssessModel intr,Model model){
		if(service.addAssess(intr)){
			return "redirect:../assess";
		}else{
			model.addAttribute("url","../");
			return "error";
		}
	}
	@RequestMapping(value="/assess/delete/{id}")
	public String deleteAssess(Model model,@PathVariable String id){
		if(service.deleteAssess(Integer.valueOf(id))){
			return "redirect:../../assess";
		}else{
			model.addAttribute("url","../../");
			return "error";
		}
	}
}
