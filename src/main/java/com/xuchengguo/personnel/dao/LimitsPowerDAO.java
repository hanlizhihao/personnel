package com.xuchengguo.personnel.dao;

import com.xuchengguo.personnel.entity.LimitsPower;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *权限管理，仅可以查看，不允许修改，主要因为，这张表并不是实现权限控制的关键所在，权限控制的逻辑在控制器上，
 * 因此更改这里的信息毫无作用
 * @author Administrator 2017-1-6
 */
public class LimitsPowerDAO {
    //查找出所有关于权限的信息
    public List<LimitsPower> queryLimitsPower(){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        String hql="from LimitsPower order by id";
        Query query=session.createQuery(hql);
        List<LimitsPower> result=query.getResultList();
        System.out.print("查询权限信息成功");
        session.close();
        return result;
    }
    public LimitsPower querySingleLimitsPower(String name){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        String hql="from LimitsPower where name=?";
        Query query=session.createQuery(hql);
        query.setParameter(0,name);
        List<LimitsPower> result=query.getResultList();
        session.close();
        return result.get(0);
    }
}
