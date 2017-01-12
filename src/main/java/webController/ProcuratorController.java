package webController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuchengguo.personnel.entity.Procurator;

import service.ProcuratorService;
import webEntity.ProcuratorModel;

@Controller
public class ProcuratorController {
	@Autowired
	private ProcuratorService service;
	public ProcuratorController(){
		service=new ProcuratorService();
	}
	@RequestMapping(value="/procurator",method=RequestMethod.GET)
	public String procurator(Model model){
		List<Procurator> introductions=service.getAllProcurator();
		if(introductions==null){
			model.addAttribute("url","");//获取introductions
			return "error";
		}
		model.addAttribute("introductionList",introductions);
		return "procurator";
	}
	//详情页处理
	@RequestMapping(value="/procurator/detials/{id}",method=RequestMethod.GET)
	public String singleProcurator(Model model,@PathVariable String id){
		Procurator introduction=service.getSingleProcurator(Integer.valueOf(id));
		if(introduction==null){
			model.addAttribute("url","../../");
			return "error";
		}
		model.addAttribute("introduction",introduction);//为了简化页面的更改量
		return "procurator_detials";
	}
	@RequestMapping(value="/procurator/change",method=RequestMethod.POST)
	public String changeProcurator(ProcuratorModel intr,Model model){
		if(service.changeProcurator(intr)){
			return "redirect:../procurator";
		}else{
			model.addAttribute("url","../../");
			return "error";
		}
	}
	//实际做添加的处理方法
	@RequestMapping(value="/procurator/adds",method=RequestMethod.GET)
	public String addProcurator(ProcuratorModel intr,Model model){
		if(service.addProcurator(intr)){
			return "redirect:../procurator";
		}else{
			model.addAttribute("url","../");
			return "error";
		}
	}
	@RequestMapping(value="/procurator/delete/{id}")
	public String deleteProcurator(Model model,@PathVariable String id){
		if(service.deleteProcurator(Integer.valueOf(id))){
			return "redirect:../../procurator";
		}else{
			model.addAttribute("url","../../");
			return "error";
		}
	}
	
}
