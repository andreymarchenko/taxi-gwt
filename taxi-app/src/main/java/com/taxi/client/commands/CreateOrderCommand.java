package com.taxi.client.commands;

import com.google.gwt.event.shared.GwtEvent;
import com.taxi.shared.dto.OrderDto;

public class CreateOrderCommand extends GwtEvent<CreateOrderCommandHandler> {
    OrderDto orderDto;

    public static Type<CreateOrderCommandHandler> TYPE = new Type<CreateOrderCommandHandler>();

    public CreateOrderCommand(OrderDto orderDto) {this.orderDto = orderDto;}

    @Override
    public Type<CreateOrderCommandHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(CreateOrderCommandHandler handler) {
        handler.onCreateOrder(orderDto, this);
    }

    public static CreateOrderCommand create(OrderDto orderDto){
            return new CreateOrderCommand(orderDto);
    }
}
