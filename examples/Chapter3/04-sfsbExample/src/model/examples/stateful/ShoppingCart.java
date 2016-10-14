package examples.stateful;

import java.util.Map;

public interface ShoppingCart {
    public void addItem(String id, int quantity);
    public void removeItem(String id, int quantity);
    public Map<String, Integer> getItems();
    public void checkout(int paymentId);
    public void cancel();
}


