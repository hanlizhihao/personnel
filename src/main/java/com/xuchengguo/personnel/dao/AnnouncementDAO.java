package com.xuchengguo.personnel.dao;

import com.xuchengguo.personnel.entity.Announcement;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *检务公告的DAO
 * @author Administrator 2017-1-5
 */
public class AnnouncementDAO {
    //添加，标题、主要内容、作者名字、类型参数
    public boolean addAnnouncement(String title,String content,String authorName,String style){
    	//静态方法单例获取SessionFactory
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        //开启一个会话
        Session session=sf.openSession();
        //实例化一个Announcement
        Announcement announcement = new Announcement();
        announcement.setStyleName(style);
        announcement.setAuthorName(authorName);
        announcement.setContent(content);
        announcement.setTitle(title);
        //获取系统当前时间
        java.sql.Date send_time = new java.sql.Date(System.currentTimeMillis());
        announcement.setSendTime(send_time);
        //开启一个事务，进行批处理
        Transaction t= session.beginTransaction();
        try{
        session.save(announcement);
        t.commit();// 提交事务
        System.out.print("成功添加一条检务信息");
        return true;
        }catch(Exception e){
        	//出现异常后回滚事务
            t.rollback();
            //打印出错的信息，堆栈踪迹
            e.printStackTrace();
            return false;
        }finally{
          session.close();
        }
    }
    //查询总行数
    public int queryPagecount(){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        //hql是指Hibernate查询语言
        //count(*)返回所有数据的总数
        String hql="select count(*) from Announcement";//查询总行数
        //Query是在hibernate中的一个查询
        Query query= session.createQuery(hql);
       int rows=Integer.valueOf(query.getSingleResult().toString());//查询总行数
       System.out.println("检务信息的总数为："+rows);
       session.close();
       return rows;
    }
    //根据page分页查询检务信息
    public List<Announcement> queryPage(int page){
        String hql="from Announcement order by id";
        int pageSize=10;
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        //用session来创建一个查询
        Query query=session.createQuery(hql);
        //增加分页起点
        int from=(page-1)*pageSize;
        query.setFirstResult(from);
        query.setMaxResults(pageSize);
        //List是一个集合，能够存储任何类型的数据，<Announcement>这个List将会存储一个类型为Announcement的集合
        List<Announcement> result=query.getResultList();
        System.out.print("分页查询检务信息成功");
        session.close();
        return result;
    }
    //修改Announcement
    public boolean changeAnnouncement(Announcement a){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        Transaction ts= session.beginTransaction();
        session.update(a);
        try{
        	//提交事务
            ts.commit();
        }catch(Exception e){
            e.printStackTrace();
            ts.rollback();
            return false;
        }finally{
            session.close();
        }
        System.out.print("修改检务信息成功");
        return true;
    }
    //根据id删除检务信息
    public boolean deleteAnnouncement(int id){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        //根据id获取一个Announcement
        Announcement a=(Announcement)session.get(Announcement.class,id);
        Transaction ts= session.beginTransaction();
        //删除a
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
        System.out.print("删除检务信息成功");
        return true;
    }
    // 根据id查询单个检务信息
    public Announcement querySingleAnnouncement(int id){
    	//获取SessionFactory单例，sf就是一个实例
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        try{
            Announcement a=session.get(Announcement.class, id);
            System.out.println("查询单独检务公告成功");
            return a;
        }catch(Exception e){
        	e.printStackTrace();
        	return null;
        }finally{
        	session.close();
        }
    }
}
