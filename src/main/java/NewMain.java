
import com.xuchengguo.personnel.dao.AnnouncementDAO;
import com.xuchengguo.personnel.entity.Announcement;
/**
 *
 * @author Administrator
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AnnouncementDAO d=new AnnouncementDAO();
//        Announcement announcement = new Announcement();
//        announcement.setId(3);
//        announcement.setAuthorName("小明");
//        announcement.setContent("日前，哈尔滨市人民检察院以涉嫌玩忽职守罪依法对朱崇梅、李海龙决定逮捕");
//        java.sql.Date send_time = new java.sql.Date(System.currentTimeMillis());
//        announcement.setSendTime(send_time);
//        announcement.setStyleName("案件信息");
//        announcement.setTitle("哈尔滨市检察机关依法对朱崇梅、李海龙决定逮捕");
        d.deleteAnnouncement(4);
        // TODO code application logic here
    }
    
}
