package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuchengguo.personnel.dao.AnnouncementDAO;
import com.xuchengguo.personnel.entity.Announcement;

import webModel.AnnouncementModel;
import webModel.UserPower;

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
		announcement=ann.querySingleAnnouncement(id);
		this.id=id;
		return announcement;
	}
	public boolean changeAnnouncement(Announcement model){
		if(power.getUserPower()==-1){
			return false;
		}
		model.setId(this.id);
		boolean sign=ann.changeAnnouncement(model);
		announcement=null;
		anns=null;
		return sign;
	}
	public boolean addAnnouncement(AnnouncementModel model){
		if(power.getUserPower()==-1){
			return false;
		}
		boolean success=ann.addAnnouncement(model.getTitle(), model.getContent(), model.getAuthorName(),model.getStyle());
		announcement=null;
		anns=null;
		return success;
	}
	public boolean deleteAnnouncement(int id){
		if(power.getUserPower()==-1||power.getUserPower()==6){
			return false;
		}
		boolean success=ann.deleteAnnouncement(id);
		announcement=null;
		anns=null;
		return success;
	}
}
