package examples.stateful;

import java.util.Map;

public interface ShoppingCart {
    public void addItem(String id, Integer quantity);
    public Map<String, Integer> getItems();
    public void cancel();
}

