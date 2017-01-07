
import com.xuchengguo.personnel.dao.BillDAO;
import com.xuchengguo.personnel.dao.LimitsPowerDAO;


/**
 *
 * @author Administrator
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       BillDAO b=new BillDAO();
       b.addBill("天天向上","工程项目",7000.568,"用来测试统计的DAO",2);
    }
    
}
