package com.xuchengguo.personnel.dao;

import com.xuchengguo.personnel.entity.Announcement;
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
    //添加账单
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
        bill.setId(1);
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
        return true;
    }
    //删除id的账单
    public boolean deleteBill(int id){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        Bill a=(Bill)session.get(Bill.class,id);
        Transaction ts= session.beginTransaction();
        session.delete(a); 
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
        return true;
    }
    //修改账单
    public boolean changeBill(Bill bill){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        Transaction ts= session.beginTransaction();
        session.update(bill);
        try{
            ts.commit();
        }catch(Exception e){
            e.printStackTrace();
            ts.rollback();
            return false;
        }finally{
            session.close();
        }
        System.out.print("修改账单信息成功");
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
}
