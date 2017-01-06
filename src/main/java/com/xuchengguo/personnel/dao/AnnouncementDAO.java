/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xuchengguo.personnel.dao;

import com.xuchengguo.personnel.entity.Announcement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator 2017-1-5
 */
public class AnnouncementDAO {
    public void addTest(){
        try{
            SessionFactory sf=SessionFactoryUtil.getSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();         //开始事务
            Announcement announcement = new Announcement();
            announcement.setAuthorName("小龙");
            announcement.setContent("今天发布一则新闻内容上的啊啊啊");
            java.sql.Date send_time = new java.sql.Date(System.currentTimeMillis());
            announcement.setSendTime(send_time);
            announcement.setStyleName("公告");
            announcement.setTitle("测试新闻");
            session.save(announcement);
            session.getTransaction().commit(); // 提交事务
            System.out.print("成功");
        }catch(Exception e){
           e.printStackTrace();
        }
    }
    //添加，标题、主要内容、作者名字、类型参数
    public boolean addAnnouncement(String title,String content,String authorName,String style){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        Announcement announcement = new Announcement();
        announcement.setStyleName(style);
        announcement.setAuthorName(authorName);
        announcement.setContent(content);
        announcement.setTitle(title);
        java.sql.Date send_time = new java.sql.Date(System.currentTimeMillis());
        announcement.setSendTime(send_time);
        Transaction t= session.getTransaction();
        try{
        session.save(announcement);
        t.commit();// 提交事务
        System.out.print("成功添加一条检务信息");
        return true;
        }catch(Exception e){
            t.rollback();
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
        String hql="select count(*) from Announcement";//查询总行数
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
        Query query=session.createQuery(hql);
        //增加分页起点
        int from=(page-1)*pageSize;
        query.setFirstResult(from);
        query.setMaxResults(pageSize);
        List<Announcement> result=query.getResultList();
        System.out.print("分页查询检务信息成功");
        session.close();
        return result;
    }
    //根据id修改Announcement
    public boolean changeAnnouncement(Announcement a){
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
        System.out.print("修改检务信息成功");
        return true;
    }
    //根据id删除检务信息
    public boolean deleteAnnouncement(int id){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        Announcement a=(Announcement)session.get(Announcement.class,id);
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
        System.out.print("删除检务信息成功");
        return true;
    }
}
