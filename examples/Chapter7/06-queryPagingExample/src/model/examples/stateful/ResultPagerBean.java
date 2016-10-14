package examples.stateful;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class ResultPagerBean implements ResultPager {
    @PersistenceContext(unitName="QueryPaging")
    private EntityManager em;
    
    private String reportQueryName;
    private int currentPage;
    private int maxResults;
    private int pageSize;
    
    public int getPageSize() {
        return pageSize;
    }
    
    public int getMaxPages() {
        return maxResults / pageSize;
    }

    public void init(int pageSize, String countQueryName, 
                     String reportQueryName) {
        this.pageSize = pageSize;
        this.reportQueryName = reportQueryName;
        maxResults = ((Long) em.createNamedQuery(countQueryName)
                                    .getSingleResult()).intValue();
        currentPage = 0;
    }
    
    public List getCurrentResults() {
        return em.createNamedQuery(reportQueryName)
                 .setFirstResult(currentPage * pageSize)
                 .setMaxResults(pageSize)
                 .getResultList();
    }
    
    public void next() {
        currentPage++;
    }
    
    public void previous() {
        currentPage--;
        if (currentPage < 0) {
            currentPage = 0;
        }
    }
    
    public int getCurrentPage() {
        return currentPage;
    }
    
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    
    @Remove
    public void finished() {
    }
}
