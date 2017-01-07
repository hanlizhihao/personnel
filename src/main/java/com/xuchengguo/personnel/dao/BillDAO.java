package com.xuchengguo.personnel.dao;

import com.xuchengguo.personnel.entity.Bill;
import com.xuchengguo.personnel.entity.User;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator 2017-1-6
 */
public class BillDAO {
    //添加账单，参数中的id是指操作人的id
    public boolean addBill(String name,String style,double number,String describe,int id){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        User user=(User)session.get(User.class,id);
        Bill bill=new Bill();
        bill.setDescribeBill(describe);
        bill.setName(name);
        bill.setNumber(number);
        bill.setStyle(style);
        bill.setTimeBill(new java.sql.Date(System.currentTimeMillis()));
        bill.setOpreaterid(user);
        Transaction t= session.beginTransaction();
        try{
            session.save(bill);
            session.saveOrUpdate(user);
            t.commit();
            System.out.println("成功添加一个账单");
        }catch(Exception e){
            t.rollback();
            e.printStackTrace();
            return false;
        }finally{
          session.close();
        }
        StatisticsBigDAO s=new StatisticsBigDAO();
        s.updateStatisticsBig(id,0, style, number);
        return true;
    }
    //删除id的账单
    public boolean deleteBill(int id){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        Bill a=(Bill)session.get(Bill.class,id);
        Transaction ts= session.beginTransaction();
        session.delete(a); 
        String style=a.getStyle();
        double number=a.getNumber();
        try{
            ts.commit();
        }catch(Exception e){
            e.printStackTrace();
            ts.rollback();
            return false;
        }finally{
            session.close();
        }
        System.out.print("删除账单信息成功");
        StatisticsBigDAO s=new StatisticsBigDAO();
        s.updateStatisticsBig(id,0, style, number);
        return true;
    }
    //修改账单
    public boolean changeBill(Bill bill) {
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction ts = session.beginTransaction();
        session.update(bill);
        try {
            ts.commit();
        } catch (Exception e) {
            e.printStackTrace();
            ts.rollback();
            return false;
        } finally {
            session.close();
        }
        System.out.print("修改账单信息成功");
        StatisticsBigDAO s = new StatisticsBigDAO();
        s.updateStatisticsBig(bill.getId(), 0,bill.getStyle(),bill.getNumber());
        return true;
    }
    //查询账单，返回的对象包括操作人的信息
    public List<Bill> queryBill(int page){        
        String hql="from Bill order by id";
        int pageSize=10;
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        Query query=session.createQuery(hql);
        //增加分页起点
        int from=(page-1)*pageSize;
        query.setFirstResult(from);
        query.setMaxResults(pageSize);
        List<Bill> result=query.getResultList();
        System.out.print("分页查询账单信息成功");
        session.close();
        return result;
    }
    //数据总行数
    public int queryPagecount() {
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        String hql = "select count(*) from Bill";//查询总行数
        Query query = session.createQuery(hql);
        int rows = Integer.valueOf(query.getSingleResult().toString());//查询总行数
        System.out.println("账单信息的总数为：" + rows);
        session.close();
        return rows;
    }
    /**
     * 用于统计报表的查询，分别查出工程项目，财务项目，后勤保障三者的所有项目，从而能
     * 够给出，每个项目占三项之一的总额的比例
     * @return 工程 
     */
    public List<Bill> queryProject(){
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        String hql="from Bill where style=?";
        Query query=session.createQuery(hql);
        query.setParameter(0,"工程项目");
        List<Bill> result=query.getResultList();
        System.out.print("查询工程账单成功");
        session.close();
        return result;
    }
    public List<Bill> queryFinancial() {
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        String hql = "from Bill where style=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, "财务项目");
        List<Bill> result = query.getResultList();
        System.out.print("查询财务账单成功");
        session.close();
        return result;
    }
    //后勤
    public List<Bill> queryLogistics() {
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        String hql = "from Bill where style=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, "后勤保障");
        List<Bill> result = query.getResultList();
        System.out.print("查询后勤保障账单成功");
        session.close();
        return result;
    }
    //根据指定id查出账单
    public Bill querySingle(int id){
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        Bill result=(Bill)session.get(Bill.class, id);
        session.close();
        return result;
    }
}
