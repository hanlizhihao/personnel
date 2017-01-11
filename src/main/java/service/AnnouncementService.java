package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuchengguo.personnel.dao.AnnouncementDAO;
import com.xuchengguo.personnel.entity.Announcement;
import webEntity.UserPower;

@Service
public class AnnouncementService {
	@Autowired
	private UserPower power;
	private AnnouncementDAO ann;
	private List<Announcement> anns=null;
	private int page;
	private Announcement announcement=null;
	private int id;//查询单独一个Announcement的id
	public AnnouncementService(){
		ann=new AnnouncementDAO();
	}
	public List<Announcement> getPageAnnouncement(int page){
		if(power.getUserPower()==-1){
			return null;
		}
		if(null!=anns&&this.page==page){
			return anns;//缓存策略
		}
		anns=ann.queryPage(page);
		this.page=page;
		return anns;
	}
	public int getPageCount(){
		return ann.queryPagecount();
	}
	public Announcement getSingleAnnouncement(int id){
		if(power.getUserPower()==-1){
			return null;
		}
		if(announcement!=null&&this.id==id){
			return announcement;
		}
		this.announcement=anns.get(id-1);//因为索引从0开始
		return announcement;
	}
	public boolean changeAnnouncement(Announcement model){
		if(power.getUserPower()==-1){
			return false;
		}
		boolean sign=ann.changeAnnouncement(model);
		announcement=null;
		anns=null;
		return sign;
	}
}
