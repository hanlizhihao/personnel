package service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xuchengguo.personnel.dao.AnnouncementDAO;
import com.xuchengguo.personnel.entity.Announcement;

@Service
public class AnnouncementService {
	private AnnouncementDAO ann;
	private List<Announcement> anns;
	public AnnouncementService(){
		ann=new AnnouncementDAO();
	}
	public List<Announcement> getPageAnnouncement(int page){
		anns=ann.queryPage(page);
		return anns;
	}
	public int getPageCount(){
		return ann.queryPagecount();
	}
	public Announcement getSingleAnnouncement(int id){
		return anns.get(id-1);//因为索引从0开始
	}
}
