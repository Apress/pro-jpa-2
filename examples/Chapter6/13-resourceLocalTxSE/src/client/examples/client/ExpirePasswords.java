package examples.client;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import examples.model.User;

public class ExpirePasswords {
    
    public static void main(String[] args) {
        int maxAge = Integer.parseInt(args[0]);
        String defaultPassword = args[1];
        
        EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("admin");
        try {
            EntityManager em = emf.createEntityManager();
        
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, -maxAge);
        
            em.getTransaction().begin();
            Collection expired = 
                em.createQuery("SELECT u FROM User u WHERE u.lastChange > ?1")
                  .setParameter(1, cal)
                  .getResultList();
            for (Iterator i = expired.iterator(); i.hasNext();) {
                User u = (User) i.next();
                System.out.println("Expiring password for " + u.getName());
                u.setPassword(defaultPassword);
            }
            em.getTransaction().commit();
            em.close();
        } finally {
            emf.close();
        }
    }

}
