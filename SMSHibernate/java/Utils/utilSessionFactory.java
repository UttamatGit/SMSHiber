package Utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class utilSessionFactory {
    private  static SessionFactory sessionFactory;

    public utilSessionFactory() {
    }

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            synchronized (utilSessionFactory.class){
                if (sessionFactory  == null){
                    sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
                }
            }
        }
        return sessionFactory;
    }
    public void closeSessionFactory(){
        if (sessionFactory.isOpen() && sessionFactory != null){
            sessionFactory.close();
        }
    }
}
