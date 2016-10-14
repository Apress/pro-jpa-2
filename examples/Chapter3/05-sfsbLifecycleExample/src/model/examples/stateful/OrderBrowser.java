package examples.stateful;

import java.util.Collection;

import examples.model.Order;

public interface OrderBrowser {
    public Collection<Order> listOrders();
}

