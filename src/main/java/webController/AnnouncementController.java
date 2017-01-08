package webController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuchengguo.personnel.dao.AnnouncementDAO;
import com.xuchengguo.personnel.entity.Announcement;

import service.AnnouncementService;

@Controller
@RequestMapping("/ann")
public class AnnouncementController {
	@Autowired
	private AnnouncementService service;

	
}
