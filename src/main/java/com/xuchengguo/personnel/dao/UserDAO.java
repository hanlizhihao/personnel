package com.xuchengguo.personnel.dao;

import com.xuchengguo.personnel.entity.LimitsPower;
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
public class UserDAO {
       //添加用户
    public boolean addUser(String name,String username,String password,int id){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        LimitsPower limitsPower=(LimitsPower)session.get(LimitsPower.class,id);
        User user=new User();
        user.setLimitsPower(limitsPower);
        user.setName(name);
        user.setPassword(password);
        user.setUsername(username);
        Transaction t= session.beginTransaction();
        try{
            session.save(user);
            session.saveOrUpdate(limitsPower);
            t.commit();
            System.out.println("成功添加一个用户");
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

    public boolean deleteUser(int id) {
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        User a = (User) session.get(User.class, id);
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
        System.out.print("删除账户信息成功");
        return true;
    }
    //修改用户信息
    public boolean changeUser(User user){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        Transaction ts= session.beginTransaction();
        session.update(user);
        try{
            ts.commit();
        }catch(Exception e){
            e.printStackTrace();
            ts.rollback();
            return false;
        }finally{
            session.close();
        }
        System.out.print("修改用户信息成功");
        return true;
    }
        //查询用户，返回的对象包括权限的信息
    public List<User> queryUser(int page){        
        String hql="from User order by id";
        int pageSize=10;
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        Query query=session.createQuery(hql);
        //增加分页起点
        int from=(page-1)*pageSize;
        query.setFirstResult(from);
        query.setMaxResults(pageSize);
        List<User> result=query.getResultList();
        System.out.print("分页查询用户信息成功");
        session.close();
        return result;
    }
    //数据总行数
    public int queryPagecount() {
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        String hql = "select count(*) from User";//查询总行数
        Query query = session.createQuery(hql);
        int rows = Integer.valueOf(query.getSingleResult().toString());//查询总行数
        System.out.println("用户信息的总数为：" + rows);
        session.close();
        return rows;
    }
    public List<User> queryAllUser(){
        SessionFactory sf = SessionFactoryUtil.getSessionFactory();
        Session session = sf.openSession();
        String hql="from User order by id";
        Query query=session.createQuery(hql);
        List<User> result=query.getResultList();
        System.out.println("成功查询所有用户信息");
        return result;
    }
}
