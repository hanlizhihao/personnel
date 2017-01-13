package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuchengguo.personnel.dao.BillDAO;
import com.xuchengguo.personnel.dao.StatisticsBigDAO;
import com.xuchengguo.personnel.entity.Bill;
import com.xuchengguo.personnel.entity.StatisticsBig;

import webModel.BillModel;
import webModel.StatisticsModel;
import webModel.UserPower;
@Service
public class BillService {
	@Autowired
	private UserPower power;
	private BillDAO dao;
	private int page;
	private List<Bill> anns;
	private int id;//缓存id
	public BillService(){
		dao=new BillDAO();
	}
	public boolean addBill(BillModel model){
		if(power.getUserPower()==1||power.getUserPower()==2){
			boolean sign=dao.addBill(model.getName(), model.getStyle(), model.getNumber(), model.getDescribe(), 
					power.getUser().getId());
			return sign;
		}else{
			return false;
		}
	}
	public List<Bill> getPageBill(int page){
		if(power.getUserPower()==1||power.getUserPower()==2){
			if(null!=anns&&this.page==page){
				return anns;//缓存策略
			}
			anns=dao.queryBill(page);
			return anns;
		}else{
			return null;
		}
	}
	public int getPageCount(){
		return dao.queryPagecount();
	}
	public Bill getSingleBill(int id){
		if(power.getUserPower()==1||power.getUserPower()==2){
			Bill assess=dao.querySingle(id);
			this.id=id;
			return assess;
		}else{
			return null;
		}
	}
	public boolean changeBill(Bill assess){
		if(power.getUserPower()==1||power.getUserPower()==2){
			assess.setId(id);//将缓存的id注入，防止出现将id更改以后不能正确更新
			boolean sign=dao.changeBill(assess);
			return sign;
		}else{
			return false;
		}
	}
	public boolean deleteBill(int id){
		if(power.getUserPower()==1||power.getUserPower()==2){
			boolean sign=dao.deleteBill(id);
			return sign;
		}else{
			return false;
		}
	}
	public List<Bill> getBillProject(){
		if(power.getUserPower()==1||power.getUserPower()==2){
			List<Bill> result=dao.queryProject();
			int index=1;
			for(Bill b:result){
				b.setId(index);
				index=index+1;
			}
			return result;
		}else{
			return null;
		}
	}
	public List<Bill> getBillFinancial(){
		if(power.getUserPower()==1||power.getUserPower()==2){
			List<Bill> result=dao.queryFinancial();
			int index=1;
			for(Bill b:result){
				b.setId(index);
				index=index+1;
			}
			return result;
		}else{
			return null;
		}
	}
	public List<Bill> getBillLogistics(){
		if(power.getUserPower()==1||power.getUserPower()==2){
			List<Bill> result=dao.queryLogistics();
			int index=1;
			for(Bill b:result){
				b.setId(index);
				index=index+1;
			}
			return result;
		}else{
			return null;
		}
	}
	public List<StatisticsModel> getBigStatistics(){
		if(power.getUserPower()==1||power.getUserPower()==2){
			StatisticsBigDAO daoBig=new StatisticsBigDAO();
			List<StatisticsBig> statisticsList=daoBig.queryStatisticsBig();
			ArrayList<StatisticsModel> models=new ArrayList<>();
			double sum=statisticsList.get(0).getNumber()+statisticsList.get(1).getNumber()+statisticsList.get(2).getNumber();
			for(int i=0;i<3;i++){
				double percent=statisticsList.get(i).getNumber()/sum;
				StatisticsModel model=new StatisticsModel();
				model.setId(statisticsList.get(i).getId());model.setName(statisticsList.get(i).getName());
				model.setNumber(statisticsList.get(i).getNumber());model.setPercent(percent);
				models.add(model);
			}
			return models;
		}else{
			return null;
		}
	}
	public List<StatisticsModel> getProjectSts(){
		if(power.getUserPower()==1||power.getUserPower()==2){
			List<Bill> bills=getBillProject();
			double sum=0;
			for(Bill bill:bills){
				sum=sum+bill.getNumber();
			}
			ArrayList<StatisticsModel> models=new ArrayList<>();
			int indexs=1;
			for(Bill bill:bills){
				StatisticsModel model=new StatisticsModel();
				model.setId(indexs);indexs=indexs+1;
				model.setName(bill.getName());
				model.setNumber(bill.getNumber());
				model.setPercent(bill.getNumber()/sum);
				models.add(model);
			}
			return models;
		}else{
			return null;
		}
	}
	public List<StatisticsModel> getFincialSts(){
		if(power.getUserPower()==1||power.getUserPower()==2){
			List<Bill> bills=getBillFinancial();
			double sum=0;
			for(Bill bill:bills){
				sum=sum+bill.getNumber();
			}
			ArrayList<StatisticsModel> models=new ArrayList<>();
			int indexs=1;
			for(Bill bill:bills){
				StatisticsModel model=new StatisticsModel();
				model.setId(indexs);indexs=indexs+1;
				model.setName(bill.getName());
				model.setNumber(bill.getNumber());
				model.setPercent(bill.getNumber()/sum);
				models.add(model);
			}
			return models;
		}else{
			return null;
		}
	}
	public List<StatisticsModel> getLogisticsSts(){
		if(power.getUserPower()==1||power.getUserPower()==2){
			List<Bill> bills=getBillLogistics();
			double sum=0;
			for(Bill bill:bills){
				sum=sum+bill.getNumber();
			}
			ArrayList<StatisticsModel> models=new ArrayList<>();
			int indexs=1;
			for(Bill bill:bills){
				StatisticsModel model=new StatisticsModel();
				model.setId(indexs);indexs=indexs+1;
				model.setName(bill.getName());
				model.setNumber(bill.getNumber());
				model.setPercent(bill.getNumber()/sum);
				models.add(model);
			}
			return models;
		}else{
			return null;
		}
	}
}
