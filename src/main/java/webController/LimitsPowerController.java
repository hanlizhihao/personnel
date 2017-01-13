package webController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuchengguo.personnel.entity.LimitsPower;

import service.LimitsPowerService;

@Controller
public class LimitsPowerController {
	@Autowired
	private LimitsPowerService service;
	@RequestMapping(value="/limitis",method=RequestMethod.GET)
	public String limits(Model model){
		List<LimitsPower> limits=service.getAll();
		if(limits==null){
			model.addAttribute("url","");//获取introductions
			return "error";
		}
		model.addAttribute("introductionList",limits);
		return "limits";
	}
}
