package com.xuchengguo.personnel;

import com.xuchengguo.personnel.dao.AnnouncementDAO;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnouncementDAO dao=new AnnouncementDAO();
        dao.addTest();
    }
}
