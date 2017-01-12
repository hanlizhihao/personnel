package com.xuchengguo.personnel.dao;

import com.xuchengguo.personnel.entity.Assess;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *考核管理
 * @author Administrator 2017-1-6
 */
public class AssessDAO {
    //接受详细参数，添加考核信息
    public boolean addCheck(String name,String job,int quality,int ability,int outstanding){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        Assess check=new Assess();
        check.setJob(job);
        check.setAbility(ability);
        java.sql.Date checkTime = new java.sql.Date(System.currentTimeMillis());
        check.setCheckTime(checkTime);
        check.setName(name);
        check.setOutstanding(outstanding);
        check.setQuality(quality);
        check.setScore(quality/3+ability/3+outstanding/3);
        Transaction t= session.getTransaction();
        try{
            session.save(check);
            t.commit();// 提交事务
            System.out.print("成功添加一条考核信息");
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
        	session.close();
        }
        return true;
    }
    //查询总行数
    public int queryPagecount(){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        String hql="select count(*) from Assess";//查询总行数
        Query query= session.createQuery(hql);
       int rows=Integer.valueOf(query.getSingleResult().toString());//查询总行数
       System.out.println("考核信息的总数为："+rows);
       session.close();
       return rows;
    }
    //根据page分页查询考核信息
    public List<Assess> queryPage(int page){
        String hql="from Assess order by id";
        int pageSize=10;
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        Query query=session.createQuery(hql);
        //增加分页起点
        int from=(page-1)*pageSize;
        query.setFirstResult(from);
        query.setMaxResults(pageSize);
        List<Assess> result=query.getResultList();
        System.out.print("分页查询检务信息成功");
        session.close();
        return result;
    }
        //根据id修改Check
    public boolean changeCheck(Assess a){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        Transaction ts= session.beginTransaction();
        session.update(a);
        try{
            ts.commit();
        }catch(Exception e){
            e.printStackTrace();
            ts.rollback();
            return false;
        }finally{
            session.close();
        }
        System.out.print("修改考核信息成功");
        return true;
    }
        //根据id删除检务信息
    public boolean deleteCheck(int id){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        Assess a=(Assess)session.get(Assess.class,id);
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
        System.out.print("删除考核信息成功");
        return true;
    }
    public Assess querySingleAssess(int id){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        Assess assess=(Assess)session.get(Assess.class, id);
        System.out.println("查询单一考核信息成功");
        session.close();
        return assess;
    }
}
