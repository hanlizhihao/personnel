/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xuchengguo.personnel.dao;

import com.xuchengguo.personnel.entity.Announcement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Administrator 2017-1-5
 */
public class AnnouncementDAO {
    public void addTest(){
        try{
            Configuration configuration = new Configuration();
configuration.configure("hibernate.cfg.xml");
ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
        .applySettings(configuration.getProperties()).build();
SessionFactory sf = configuration
        .buildSessionFactory(serviceRegistry);
                Session session=sf.getCurrentSession();
                session.beginTransaction();         //开始事务
                Announcement person = new Announcement();
                person.setAuthor_name("小龙");
                person.setContent("今天发布一则新闻内容上的啊啊啊");
                java.sql.Date send_time = new java.sql.Date(System.currentTimeMillis());
                person.setSend_time(send_time);
                person.setStyle_name("公告");
                person.setTitle("测试新闻");
                session.save(person);
                session.getTransaction().commit(); // 提交事务
        System.out.print("成功");
        }catch(Exception e){
           e.printStackTrace();
        }
    }
}
