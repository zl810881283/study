package edu.cqu.no1.zl.main;

import edu.cqu.no1.zl.domain.User;
import edu.cqu.no1.zl.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by zl on 2016/4/27.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Session session = HibernateUtils.getSession();
        User user = new User("zhangle", "zl7112585", 12);
        Transaction transaction = session.beginTransaction();
        session.save(user);
        //user = session.load(User.class, 1);
        System.out.println(user);
        transaction.commit();
    }
}
