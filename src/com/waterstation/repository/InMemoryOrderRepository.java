
package com.waterstation.repository;

import java.util.ArrayList;
import java.util.List;
import com.waterstation.model.WaterOrder;

public class InMemoryOrderRepository implements OrderRepository {

    private List<WaterOrder> orders = new ArrayList<>();

    @Override
    public void save(WaterOrder order) {
        orders.add(order);
    }

    @Override
    public List<WaterOrder> findAll() {
        return new ArrayList<>(orders);
    }
}
