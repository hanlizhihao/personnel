/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xuchengguo.personnel.dao;

import com.xuchengguo.personnel.entity.Announcement;
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
    //查询为分页查询
    public int queryall(){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        String hql="select count(*) from announcement";//查询总行数
        Query query= session.createQuery(hql);
       int rows=query.getMaxResults();//查询总行数
       return rows;
    }
}
