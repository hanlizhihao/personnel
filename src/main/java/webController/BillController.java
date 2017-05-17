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

import com.xuchengguo.personnel.entity.Bill;

import service.BillService;
import webModel.BillModel;
import webModel.BillOutputModel;
import webModel.StatisticsModel;
/**
 * 账套管理的所有都在这，接受使用BillModel，更改使用Bill统计报表与此类似
 * @author Administrator
 *
 */
@Controller
public class BillController {
	@Autowired
	BillService service;
	@RequestMapping(value="/bills/{page}",produces="application/json")
	@ResponseBody
	public List<BillOutputModel> getBillByPage(@PathVariable String page){
		List<Bill> bills=new ArrayList<>();
		bills=service.getPageBill(Integer.valueOf(page));
		List<BillOutputModel> result=new ArrayList<>();
		for(Bill b:bills){
			BillOutputModel m=new BillOutputModel();
			m.setDescribe(b.getDescribeBill());
			m.setId(b.getId());
			m.setName(b.getName());
			m.setNumber(b.getNumber());
			m.setStyle(b.getStyle());
			m.setTimeBill(b.getTimeBill());
			result.add(m);
		}
		return result;
	}
	@RequestMapping(value="/bill",method=RequestMethod.GET)
	public String bill(Model model){
		List<Bill> introductions=service.getPageBill(1);
		int pageCount=service.getPageCount();
		if(introductions==null){
			model.addAttribute("url","");//获取introductions
			return "error";
		}
		model.addAttribute("introductionList",introductions);
		model.addAttribute("pageCount",pageCount);
		return "bill";
	}
	//根据路径参数，返回分页信息
	@RequestMapping(value="/bill/{id}", method=RequestMethod.GET)
	public String bills(@PathVariable String id,Model model){//分页显示
		List<Bill> introductions=service.getPageBill(Integer.valueOf(id));
		int pageCount=service.getPageCount();
		if(introductions==null){
			model.addAttribute("url","../");//标识向上退几级
			return "error";
		}
		model.addAttribute("introductionList",introductions);
		model.addAttribute(pageCount);
		return "bill";
	}
	@RequestMapping(value="/bill/detials/{id}",method=RequestMethod.GET)
	public String singleBill(Model model,@PathVariable String id){
		Bill assess=service.getSingleBill(Integer.valueOf(id));
		if(assess==null){
			model.addAttribute("url","../");
			return "error";
		}
		model.addAttribute("introduction",assess);
		return "bill_detials";
	}
	@RequestMapping(value="/bill/change",method=RequestMethod.POST)
	public String changeBill(Bill intr,Model model){
		if(service.changeBill(intr)){
			return "redirect:../bill";
		}else{
			model.addAttribute("url","../");
			return "error";
		}
	}
	//实际做添加的处理地址
	@RequestMapping(value="/bill/adds",method=RequestMethod.GET)
	public String addBill(BillModel intr,Model model){
		if(service.addBill(intr)){
			return "redirect:../bill";
		}else{
			model.addAttribute("url","../");
			return "error";
		}
	}
	@RequestMapping(value="/bill/delete/{id}")
	public String deleteBill(Model model,@PathVariable String id){
		if(service.deleteBill(Integer.valueOf(id))){
			return "redirect:../bill";
		}else{
			model.addAttribute("url","../");
			return "error";
		}
	}
	@RequestMapping(value="/project",method=RequestMethod.GET)
	public String billProject(Model model){
		List<Bill> result=service.getBillProject();
		if(result==null){
			model.addAttribute("url","");
			return "error";
		}else{
			model.addAttribute("introductionList",result);
			return "project";
		}
	}
	@RequestMapping(value="/finance",method=RequestMethod.GET)
	public String billfinance(Model model){
		List<Bill> result=service.getBillFinancial();
		if(result==null){
			model.addAttribute("url","");
			return "error";
		}else{
			model.addAttribute("introductionList",result);
			return "finance";
		}
	}
	@RequestMapping(value="/logistics",method=RequestMethod.GET)
	public String billLogistics(Model model){
		List<Bill> result=service.getBillLogistics();
		if(result==null){
			model.addAttribute("url","");
			return "error";
		}else{
			model.addAttribute("introductionList",result);
			return "logistics";
		}
	}
	@RequestMapping(value="/stitistics",method=RequestMethod.GET)
	public String billStitistics(Model model){
		List<StatisticsModel> s1=service.getBigStatistics(); 
		List<StatisticsModel> s2=service.getProjectSts() ;
		List<StatisticsModel> s3=service.getFincialSts(); 
		List<StatisticsModel> s4=service.getLogisticsSts(); 
		if(s1==null||s2==null||s3==null||s4==null){
			model.addAttribute("url","");
			return "error";
		}else{
			model.addAttribute("s1",s1);
			model.addAttribute("s2",s2);
			model.addAttribute("s3",s3);
			model.addAttribute("s4",s4);
			return "stitistics";
		}
	}
	
}
