package com.xuchengguo.personnel.dao;

import com.xuchengguo.personnel.entity.Procurator;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator 2017-1-6
 */
public class ProcuratorDAO {
    //添加，名字、检察官等级、年龄、简历、检察官等级id
    public boolean addProcurator(String name,String grade,int age,String resume,int gradeID){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        Procurator procurator=new Procurator();
        procurator.setAge(age);
        procurator.setGrade(grade);
        procurator.setName(name);
        procurator.setResume(resume);
        procurator.setGradeId(gradeID);
        Transaction t= session.beginTransaction();
        try{
        session.save(procurator);
        t.commit();// 提交事务
        System.out.print("成功添加一条检查官信息");
        return true;
        }catch(Exception e){
            t.rollback();
            e.printStackTrace();
            return false;
        }finally{
          session.close();
        }
    }
    //修改Procurator
    public boolean changeProcurator(Procurator a){
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
        System.out.print("修改检察官信息成功");
        return true;
    }
    //根据id删除检察官信息
    public boolean deleteProcurator(int id){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        Procurator a=(Procurator)session.get(Procurator.class,id);
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
        System.out.print("删除检察官信息成功");
        return true;
    }
    //根据grade_id查询检察官信息
    public List<Procurator> queryProcurator(int grade_id){
        String hql="from Procurator where gradeId=?";
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        Query query=session.createQuery(hql);
        query.setParameter(0,grade_id);
        List<Procurator> procurators=query.getResultList();
        System.out.println("查询检查官信息成功");
        return procurators;
    }
}
