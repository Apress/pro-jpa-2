package examples.stateful;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;

@Stateful
public class ShoppingCartBean implements ShoppingCart {
    private HashMap<String,Integer> items = new HashMap<String,Integer>();

    @TransactionAttribute(SUPPORTS)
    public void addItem(String item, Integer quantity) {
        Integer orderQuantity = items.get(item);
        if (orderQuantity == null) {
            orderQuantity = 0;
        }
        orderQuantity += quantity;
        items.put(item, orderQuantity);
    }
    
    public Map<String, Integer> getItems() {
        return items;
    }

    @Remove
    public void cancel() {}
}

