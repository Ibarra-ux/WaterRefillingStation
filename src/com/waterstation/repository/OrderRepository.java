
package com.waterstation.repository;

import java.util.List;
import com.waterstation.model.WaterOrder;

public interface OrderRepository {

    void save(WaterOrder order);

    List<WaterOrder> findAll();
}
