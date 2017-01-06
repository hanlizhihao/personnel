/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xuchengguo.personnel.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * 单例模式获取SessionFacory
 * @author Administrator 2017-1-5
 */
public class SessionFactoryUtil {
    private volatile static SessionFactory instance=null;//volatitle防止出现线程安全问题
    public static SessionFactory getSessionFactory(){
        if(instance==null){
            synchronized(SessionFactoryUtil.class){
                if(instance==null){
                    Configuration configuration = new Configuration();
                    configuration.configure("hibernate.cfg.xml");
                    //在这里添加实体类
                    configuration.addAnnotatedClass(com.xuchengguo.personnel.entity.Announcement.class);
                    configuration.addAnnotatedClass(com.xuchengguo.personnel.entity.Assess.class);
                    configuration.addAnnotatedClass(com.xuchengguo.personnel.entity.Procurator.class);
                    configuration.addAnnotatedClass(com.xuchengguo.personnel.entity.Bill.class);
                    configuration.addAnnotatedClass(com.xuchengguo.personnel.entity.User.class);
                    configuration.addAnnotatedClass(com.xuchengguo.personnel.entity.LimitsPower.class);
                    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
                    instance = configuration
                    .buildSessionFactory(serviceRegistry);
                }
            }
        }
        return instance;
    }
}
