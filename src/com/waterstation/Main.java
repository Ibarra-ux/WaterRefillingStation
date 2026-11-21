
package com.waterstation;

import com.waterstation.repository.InMemoryOrderRepository;
import com.waterstation.repository.OrderRepository;
import com.waterstation.service.WaterStationService;
import com.waterstation.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {

        OrderRepository repository = new InMemoryOrderRepository();

        WaterStationService service =
                new WaterStationService(repository);

        ConsoleUI ui = new ConsoleUI(service);

        ui.start();
    }
}
