package com.xuchengguo.personnel.dao;

import com.xuchengguo.personnel.entity.Department;
import com.xuchengguo.personnel.entity.Introduction;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator 2017-1-7
 */
public class IntroductionDAO {
    //添加部门人员信息，参数中的id是指部门的id
    //接受的参数为一个Introduction
    public boolean addIntroduction(Introduction people,int id) {
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        Department department = (Department) session.get(Department.class, id);
        Transaction t = session.beginTransaction();
        try {
            session.save(people);
            session.saveOrUpdate(department);
            t.commit();
            System.out.println("成功添加一个部门成员");
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
    //删除id的部门成员
    public boolean deleteIntroduction(int id) {
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        Introduction a = (Introduction) session.get(Introduction.class, id);
        Transaction ts = session.beginTransaction();
        session.delete(a);
        try {
            ts.commit();
        } catch (Exception e) {
            e.printStackTrace();
            ts.rollback();
            return false;
        } finally {
            session.close();
        }
        System.out.print("删除部门成员信息成功");
        return true;
    }
    //修改部门成员
    public boolean changeIntroduction(Introduction people) {
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction ts = session.beginTransaction();
        session.update(people);
        try {
            ts.commit();
        } catch (Exception e) {
            e.printStackTrace();
            ts.rollback();
            return false;
        } finally {
            session.close();
        }
        System.out.print("修改部门成员信息成功");
        return true;
    }
    //查询部门成员简介，返回的对象包括部门的信息
    public List<Introduction> queryBill(int page) {
        String hql = "from Introduction order by id";
        int pageSize = 10;
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        Query query = session.createQuery(hql);
        //增加分页起点
        int from = (page - 1) * pageSize;
        query.setFirstResult(from);
        query.setMaxResults(pageSize);
        List<Introduction> result = query.getResultList();
        System.out.print("分页查询部门成员简介信息成功");
        session.close();
        return result;
    }
    //数据总行数
    public int queryPagecount() {
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        String hql = "select count(*) from Introduction";//查询总行数
        Query query = session.createQuery(hql);
        int rows = Integer.valueOf(query.getSingleResult().toString());//查询总行数
        System.out.println("部门成员简介信息的总数为：" + rows);
        session.close();
        return rows;
    }
    public Introduction querySingle(int id){
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        Introduction result=session.get(Introduction.class, id);
        return result;
    }
}
