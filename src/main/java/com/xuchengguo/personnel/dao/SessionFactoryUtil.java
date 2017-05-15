package com.xuchengguo.personnel.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * 单例模式获取SessionFacory
 * SessionFactory很耗费内存
 * @author Administrator 2017-1-5
 */
public class SessionFactoryUtil {
    private volatile static SessionFactory instance=null;//volatitle防止出现线程安全问题
    //static静态方法，类方法,通过类名来访问
    public static SessionFactory getSessionFactory(){
    	//双if判断实现在多线程的情况下，单例获取SessionFactory
        if(instance==null){
        	//同步SessionFactoryUtil，同步是指在同一时刻只有一个线程访问这个对象
            synchronized(SessionFactoryUtil.class){
                if(instance==null){
                	//Configuration是SesssionFactory的配置类，它将会记录一些配置信息
                    Configuration configuration = new Configuration();
                    configuration.configure("hibernate.cfg.xml");
                    //在这里添加实体类
                    configuration.addAnnotatedClass(com.xuchengguo.personnel.entity.Announcement.class);
                    configuration.addAnnotatedClass(com.xuchengguo.personnel.entity.Assess.class);
                    configuration.addAnnotatedClass(com.xuchengguo.personnel.entity.Procurator.class);
                    configuration.addAnnotatedClass(com.xuchengguo.personnel.entity.Bill.class);
                    configuration.addAnnotatedClass(com.xuchengguo.personnel.entity.User.class);
                    configuration.addAnnotatedClass(com.xuchengguo.personnel.entity.LimitsPower.class);
                    configuration.addAnnotatedClass(com.xuchengguo.personnel.entity.Department.class);
                    configuration.addAnnotatedClass(com.xuchengguo.personnel.entity.Introduction.class);
                    configuration.addAnnotatedClass(com.xuchengguo.personnel.entity.Membership.class);
                    configuration.addAnnotatedClass(com.xuchengguo.personnel.entity.StatisticsBig.class);
                    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
                    //生成一个配置好的SessionFactory
                    instance = configuration.buildSessionFactory(serviceRegistry);
                }
            }
        }
        return instance;
    }
}
