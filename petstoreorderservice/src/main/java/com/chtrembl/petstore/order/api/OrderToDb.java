package com.chtrembl.petstore.order.api;

import com.chtrembl.petstore.order.model.Order;

public interface OrderToDb {

    String placeOrderToDb(Order order);
}
