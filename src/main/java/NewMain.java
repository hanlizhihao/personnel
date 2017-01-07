
import com.xuchengguo.personnel.dao.DepartmentDAO;


/**
 *
 * @author Administrator
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DepartmentDAO d=new DepartmentDAO();
        d.addDepartment("测试添加部门","测试而已", 1);
    }
    
}
