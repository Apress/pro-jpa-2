package examples.stateful;

import java.util.List;

public interface ResultPager {
    public void init(int pageSize, String countQueryName, String reportQueryName);
    public List getCurrentResults();
    public void next();
    public void previous();
    public void finished();
    public int getCurrentPage();
    public int getMaxPages();
}
