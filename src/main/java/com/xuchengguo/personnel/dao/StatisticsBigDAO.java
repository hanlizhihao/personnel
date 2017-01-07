package com.xuchengguo.personnel.dao;

import com.xuchengguo.personnel.entity.Bill;
import com.xuchengguo.personnel.entity.StatisticsBig;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *统计信息访问DAO，一定没有增和删只会有更新
 * @author Administrator 2017-1-7
 */
public class StatisticsBigDAO {
    //查询所有统计信息，返回的是三条数据
    public List<StatisticsBig> queryStatisticsBig(){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        String hql="from StatisticsBig order by id";
        Query query=session.createQuery(hql);
        List<StatisticsBig> result=query.getResultList();
        System.out.print("查询统计信息成功");
        return result;
    }
    /**
     * 
     * @param sign 标识是增或删，0为增，1为删，2为更改
     * @param style 更改哪个项目
     * @param number 数额
     * @return 
     * 在表中，默认1为工程项目，2为财务项目，3为后勤保障
     */
    public boolean updateStatisticsBig(int id,int sign,String style,double number){
        SessionFactory sf=SessionFactoryUtil.getSessionFactory();
        Session session=sf.openSession();
        try{
            switch (sign) {
                case 0:
                    switch(style){
                        case "工程项目":
                            addStatistics(1,session,number);
                            break;
                        case "财务项目":
                            addStatistics(2,session,number);
                            break;
                        case "后勤保障":
                            addStatistics(3,session,number);
                            break;
                    }       
                    break;
                case 1:
                    switch(style){
                        case "工程项目":
                            deleteStatistics(1,session,number);
                            break;
                        case "财务项目":
                            deleteStatistics(2,session,number);
                            break;
                        case "后勤保障":
                            deleteStatistics(3,session,number);
                            break;
                    }       
                    break;
                case 2:
                     switch(style){
                        case "工程项目":
                            changeStatistics(1,session,number,id);
                            break;
                        case "财务项目":
                            changeStatistics(2,session,number,id);
                            break;
                        case "后勤保障":
                            changeStatistics(3,session,number,id);
                            break;
                    }
                break;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        System.out.println("更改统计表成功");
        return true;
    }
    private void addStatistics(int sign,Session session,double number){
        Transaction ts= session.beginTransaction();
        switch (sign) {
            case 1:
                StatisticsBig statis1=(StatisticsBig)session.get(StatisticsBig.class,1);
                statis1.setNumber(statis1.getNumber()+number);
                session.update(statis1);
                try{
                    ts.commit();
                }catch(Exception e){
                    e.printStackTrace();
                    ts.rollback();
                }finally{
                    session.close();
                }       
                break;
            case 2:
                StatisticsBig statis2=(StatisticsBig)session.get(StatisticsBig.class,2);
                statis2.setNumber(statis2.getNumber()+number);
                session.update(statis2);
                try{
                    ts.commit();
                }catch(Exception e){
                    e.printStackTrace();
                    ts.rollback();
                }finally{
                    session.close();
                }       
                break;
            case 3:
                StatisticsBig statis3=(StatisticsBig)session.get(StatisticsBig.class,3);
                statis3.setNumber(statis3.getNumber()+number);
                session.update(statis3);
                try{
                    ts.commit();
                }catch(Exception e){
                    e.printStackTrace();
                    ts.rollback();
                }finally{
                    session.close();
                }
                break;
            default:
                break;
        }
    }
    private void deleteStatistics(int sign,Session session,double number){
        Transaction ts= session.beginTransaction();
        switch (sign) {
            case 1:
                StatisticsBig statis1=(StatisticsBig)session.get(StatisticsBig.class,1);
                statis1.setNumber(statis1.getNumber()-number);
                session.update(statis1);
                try{
                    ts.commit();
                }catch(Exception e){
                    e.printStackTrace();
                    ts.rollback();
                }finally{
                    session.close();
                }       
                break;
            case 2:
                StatisticsBig statis2=(StatisticsBig)session.get(StatisticsBig.class,2);
                statis2.setNumber(statis2.getNumber()-number);
                   session.update(statis2);
                   try {
                       ts.commit();
                   } catch (Exception e) {
                       e.printStackTrace();
                       ts.rollback();
                   } finally {
                       session.close();
                   }
                   break;
               case 3:
                   StatisticsBig statis3 = (StatisticsBig) session.get(StatisticsBig.class, 3);
                   statis3.setNumber(statis3.getNumber() - number);
                   session.update(statis3);
                   try {
                       ts.commit();
                   } catch (Exception e) {
                       e.printStackTrace();
                       ts.rollback();
                   } finally {
                       session.close();
                   }
                   break;
               default:
                   break;
           }
    }
    private void changeStatistics(int sign,Session session,double number,int id){
        Transaction ts= session.beginTransaction();
         switch (sign) {
            case 1:
                StatisticsBig statis1=(StatisticsBig)session.get(StatisticsBig.class,1);
                Bill bill=(Bill)session.get(Bill.class,id);
                if(bill.getNumber()!=number){
                    statis1.setNumber(statis1.getNumber()+number-bill.getNumber());
                }else{
                    return ;
                }
                session.update(statis1);
                try{
                    ts.commit();
                }catch(Exception e){
                    e.printStackTrace();
                    ts.rollback();
                }finally{
                    session.close();
                }       
                break;
            case 2:
                StatisticsBig statis2 = (StatisticsBig) session.get(StatisticsBig.class, 2);
                Bill bill0 = (Bill) session.get(Bill.class, id);
                if (bill0.getNumber() != number) {
                    statis2.setNumber(statis2.getNumber() + number - bill0.getNumber());
                } else {
                    return;
                }
                   session.update(statis2);
                try {
                    ts.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    ts.rollback();
                } finally {
                    session.close();
                }
                break;
            case 3:
                StatisticsBig statis3 = (StatisticsBig) session.get(StatisticsBig.class, 3);
                Bill bill1 = (Bill) session.get(Bill.class, id);
                if (bill1.getNumber() != number) {
                    statis3.setNumber(statis3.getNumber() + number - bill1.getNumber());
                } else {
                    return;
                }
                session.update(statis3);
                try {
                    ts.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    ts.rollback();
                } finally {
                    session.close();
                }
                break;
            default:
                break;
        }
    }
                
}
