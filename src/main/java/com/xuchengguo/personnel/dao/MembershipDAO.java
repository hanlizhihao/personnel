package com.xuchengguo.personnel.dao;

import com.xuchengguo.personnel.entity.Introduction;
import com.xuchengguo.personnel.entity.Membership;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *人事变动信息
 * @author Administrator 2017-1-7
 */
public class MembershipDAO {
    //添加人事变动信息，参数中的id是指部门的id
    //接受的参数为一个Membership
    public boolean addMembership(Membership people) {
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
        System.out.print("添加人事变动信息成功");
        return true;
    }
    //取消指定id的人事变动,这里需要注意，不是删除数据，而是将指定列设置为一个空字符串，
    //如果删除会将人员的简介信息也删除，因为使用了视图
    public boolean cancelMembership(int id) {
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        Membership a = (Membership) session.get(Membership.class, id);
        Transaction ts = session.beginTransaction();
        a.setNextJob("");
        a.setRemoveJob("");
        a.setChangeReason("");
        session.update(a);
        try {
            ts.commit();
        } catch (Exception e) {
            e.printStackTrace();
            ts.rollback();
            return false;
        } finally {
            session.close();
        }
        System.out.print("取消人事变动申请成功");
        return true;
    }
    //批准id的人事变动申请，这里需要注意的是，可能有人有多个职务，如果免除或是改变职务
    //则会将原有的所有职务都免除，解决办法是在页面提示，将职务写全
    public boolean approveMemebership(int id){
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        Membership a = (Membership) session.get(Membership.class, id);
        Transaction ts = session.beginTransaction(); 
        //removeJob是拟免职务，如果拟免职务有指为免职
        if(!"".equals(a.getRemoveJob())){
            a.setRemoveJob("");
            //任免原因
            a.setChangeReason("");
        }
        if(!"".equals(a.getNextJob())){
            a.setNowJob(a.getNextJob());
            a.setNextJob("");
            a.setChangeReason("");
        }
        session.update(a);
        try {
            ts.commit();
        } catch (Exception e) {
            e.printStackTrace();
            ts.rollback();
            return false;
        } finally {
            session.close();
        }
        System.out.print("批准人事变动申请成功");
        return true;
    }
    //修改人事变动
    public boolean changeMembership(Membership people) {
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
        System.out.print("修改人事变动信息成功");
        return true;
    }
    //查询人事变动信息，返回的对象包括部门的信息
    public List<Membership> queryMembership(int page) {
        String hql = "from Membership order by id";
        int pageSize = 10;
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        Query query = session.createQuery(hql);
        //增加分页起点
        int from = (page - 1) * pageSize;
        query.setFirstResult(from);
        query.setMaxResults(pageSize);
        List<Membership> result = query.getResultList();
        System.out.print("分页查询人事变动信息成功");
        session.close();
        return result;
    }
    //数据总行数
    public int queryPagecount() {
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        String hql = "select count(*) from Membership";//查询总行数
        Query query = session.createQuery(hql);
        int rows = Integer.valueOf(query.getSingleResult().toString());//查询总行数
        System.out.println("人事变动信息的总数为：" + rows);
        session.close();
        return rows;
    }
    //人事变动中的部门调换
    public boolean departmentTransfer(int userId,int departmentId){
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        Introduction user=(Introduction)session.get(Introduction.class, userId);
        user.setDepartmentId(departmentId);
        Transaction ts = session.beginTransaction();
        session.update(user);
        try {
            ts.commit();
        } catch (Exception e) {
            e.printStackTrace();
            ts.rollback();
            return false;
        } finally {
            session.close();
        }
        System.out.print("部门调换成功");
        return true;
    }
    public Membership querySingleMembership(int id){
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        Membership result=(Membership)session.get(Membership.class, id);
        session.close();
        return result;
    }
}
