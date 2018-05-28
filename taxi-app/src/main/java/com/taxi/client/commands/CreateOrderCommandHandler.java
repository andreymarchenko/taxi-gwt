package com.taxi.client.commands;

import com.google.gwt.event.shared.EventHandler;
import com.taxi.shared.dto.OrderDto;

public interface CreateOrderCommandHandler extends EventHandler {
    void onCreateOrder(OrderDto orderDto, CreateOrderCommand createOrderCommand);
}
