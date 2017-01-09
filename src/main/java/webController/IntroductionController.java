package webController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuchengguo.personnel.entity.Introduction;

import service.IntroductionService;

/**
 * 访问introduction返回一个页面，
 * 后面加id开始进行分页
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/introduction")
public class IntroductionController {
	@Autowired
	private IntroductionService service;
	public IntroductionController(IntroductionService service){
		this.service=service;
	}
	@RequestMapping(method=RequestMethod.GET)
	public String introduction(Model model){
		List<Introduction> introductions=service.getIntroductions(1);
		int pageCount=service.getIntroductionCount();
		model.addAttribute(introductions);
		model.addAttribute("pageCount",pageCount);
		return "introductions";
	}
	//根据路径参数，返回分页信息
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String introductions(@PathVariable String id,Model model){
		List<Introduction> introductions=service.getIntroductions(Integer.valueOf(id));
		int pageCount=service.getIntroductionCount();
		model.addAttribute(introductions);
		model.addAttribute(pageCount);
		return "introductions";
	}
	@RequestMapping(value="detials/{id}",method=RequestMethod.GET)
	public String singleIntroducton(Model model,@PathVariable String id){
		Introduction introduction=service.getSingleIntroduction(Integer.valueOf(id));
		model.addAttribute("introduction",introduction);
		return "introduction_detials";
	}

}
