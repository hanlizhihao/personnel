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

import com.xuchengguo.personnel.entity.Introduction;

import service.IntroductionService;
import webModel.IntroductionModel;

/**
 * 访问introduction返回一个页面，
 * 后面加id开始进行分页
 * @author Administrator
 *
 */
@Controller
public class IntroductionController {
	@Autowired
	private IntroductionService service;
	//以json数据格式返回introduction数据
	@RequestMapping(value="/introductions/{page}",produces="application/json")
	@ResponseBody
	public List<IntroductionModel> getIntroductionByPage(@PathVariable String page){
		List<IntroductionModel> result=new  ArrayList<>();
		result=service.getIntroductions(Integer.valueOf(page));
		return result;
	}
	@RequestMapping(value="/introduction",method=RequestMethod.GET)
	public String introduction(Model model){
		ArrayList<IntroductionModel> introductions=service.getIntroductions(1);
		int pageCount=service.getIntroductionCount();
		if(introductions==null){
			model.addAttribute("url","");//获取introductions
			return "error";
		}
		model.addAttribute("introductionList",introductions);
		model.addAttribute("pageCount",pageCount);
		return "introductions";
	}
	//根据路径参数，返回分页信息
	@RequestMapping(value="/introduction/{id}", method=RequestMethod.GET)
	public String introductions(@PathVariable String id,Model model){//分页显示
		ArrayList<IntroductionModel> introductions=service.getIntroductions(Integer.valueOf(id));
		int pageCount=service.getIntroductionCount();
		if(introductions==null){
			model.addAttribute("url","../");//标识向上退几级
			return "error";
		}
		model.addAttribute("introductionList",introductions);
		model.addAttribute(pageCount);
		return "introductions";
	}
	//详情页处理
	@RequestMapping(value="/introduction/detials/{id}",method=RequestMethod.GET)
	public String singleIntroducton(Model model,@PathVariable String id){
		Introduction introduction=service.getSingleIntroduction(Integer.valueOf(id));
		if(introduction==null){
			model.addAttribute("url","../../");
			return "error";
		}
		int departmentID=introduction.getDepartmentId();
		switch(departmentID){
		case 1:model.addAttribute("department", "控告申诉检察部门");break;
		case 2:model.addAttribute("department", "反贪污贿赂部门");break;
		case 3:model.addAttribute("department", "反渎职侵权部门");break;
		case 4:model.addAttribute("department", "侦查监督部门");break;
		case 5:model.addAttribute("department", "公诉部门");break;
		case 6:model.addAttribute("department", "监所检察部门");break;
		case 7:model.addAttribute("department", "民事行政检察部门");break;
		case 8:model.addAttribute("department", "职务犯罪预防部门");break;
		case 9:model.addAttribute("department", "案件管理部门");break;
		case 10:model.addAttribute("department", "检察技术部门");break;
		case 11:model.addAttribute("department", "纪检、监察部门");break;
		case 12:model.addAttribute("department", "机关服务中心");break;
		}
		model.addAttribute("introduction",introduction);
		return "introduction_detials";
	}
	/**
	 * introduction显示的时候需要考虑显示部门的问题，而introduction里只有一个departmentId表示所属部门
	 * @param intr 为了能够将参数映射到Introduction上，我们必需在页面上使用select标签，以对应departmentID这个属性
	 * @param model，对于添加introduction，与改变类似
	 * @return
	 */
	@RequestMapping(value="/introduction/change",method=RequestMethod.POST)
	public String changeIntroduction(Introduction intr,Model model){
		if(service.changeIntroduction(intr)){
			return "redirect:../introduction";
		}else{
			model.addAttribute("url","../../");
			return "error";
		}
	}
	@RequestMapping(value="/introduction/add",method=RequestMethod.GET)
	public String toAddIntroduction(){
		return "introduction_add";
	}
	//实际做添加的处理地址
	@RequestMapping(value="/introduction/adds",method=RequestMethod.POST)
	public String addIntroduction(Introduction intr,Model model){
		if(service.addIntroduction(intr)){
			return "redirect:../introduction";
		}else{
			model.addAttribute("url","../");
			return "error";
		}
	}
	@RequestMapping(value="/introduction/delete/{id}")
	public String deleteIntroducton(Model model,@PathVariable String id){
		if(service.deleteIntroduction(Integer.valueOf(id))){
			return "redirect:../../introduction";
		}else{
			model.addAttribute("url","../../");
			return "error";
		}
	}
}
