package service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xuchengguo.personnel.dao.AnnouncementDAO;
import com.xuchengguo.personnel.entity.Announcement;

@Service
public class AnnouncementService {
	private AnnouncementDAO ann;
	public AnnouncementService(){
		ann=new AnnouncementDAO();
	}
	public List<Announcement> getPageAnnouncement(int page){
		return ann.queryPage(page);
	}
}
