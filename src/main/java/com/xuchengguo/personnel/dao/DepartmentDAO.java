package com.xuchengguo.personnel.dao;

import com.xuchengguo.personnel.entity.Department;
import com.xuchengguo.personnel.entity.LimitsPower;
import com.xuchengguo.personnel.entity.User;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *部门,style类型，0为内设部门，1为直属事业单位
 * @author Administrator 2017-1-7
 */
public class DepartmentDAO {
    //查找出所有关于部门的信息,查出部门信息的同时，实质上也包含了每个部门对应人员信息
    public List<Department> queryDepartment() {
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        String hql = "from Department order by id";
        Query query = session.createQuery(hql);
        List<Department> result = query.getResultList();
        System.out.print("查询部门信息成功");
        session.close();
        return result;
    }
    //添加部门，0位内设机构
    public boolean addDepartment(String name,String job,int style){
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        Department department=new Department();
        department.setName(name);
        department.setStyle(style);
        department.setDepartmentJob(job);
        Transaction t = session.beginTransaction();
        try {
            session.save(department);
            t.commit();
            System.out.println("成功添加一个部门");
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
    //删除部门
    public boolean deleteDepartment(int id) {
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        Department a = session.get(Department.class, id);
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
        System.out.print("删除部门信息成功");
        return true;
    }
    //修改部门信息
    public boolean changeDepartment(Department department) {
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction ts = session.beginTransaction();
        session.update(department);
        try {
            ts.commit();
        } catch (Exception e) {
            e.printStackTrace();
            ts.rollback();
            return false;
        } finally {
            session.close();
        }
        System.out.print("修改部门信息成功");
        return true;
    }
}
