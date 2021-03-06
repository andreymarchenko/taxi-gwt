package com.taxi.client.controller;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.taxi.client.commands.*;
import com.taxi.client.model.ClientDataModel;
import com.taxi.client.service.EndPoint;
import com.taxi.shared.dto.ClientDto;
import com.taxi.shared.dto.LoginDto;
import com.taxi.shared.dto.OrderDto;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import javax.inject.Inject;

public class Controller {
    private final ClientDataModel clientDataModel;

    private EventBus eventBus;

    private final EndPoint endPoint = GWT.create(EndPoint.class);

    @Inject
    public Controller(final ClientDataModel clientDataModel,
                      final EventBus eventBus) {
        this.clientDataModel = clientDataModel;
        this.eventBus = eventBus;
        bind();
    }

    public void bind() {
        eventBus.addHandler(LoginCommand.TYPE, new LoginCommandHandler() {
            @Override
            public void onLogin(LoginDto loginDto, LoginCommand loginCommand) {
                endPoint.login(loginDto, new MethodCallback<ClientDto>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(Method method, ClientDto clientDto) {
                        clientDataModel.setActiveClient(clientDto);
                    }
                });
            }
        });

        eventBus.addHandler(RegistrationCommand.TYPE, new RegistrationCommandHandler() {
            @Override
            public void onRegister(ClientDto clientDto, RegistrationCommand registrationCommand) {
                endPoint.createClient(clientDto, new MethodCallback<ClientDto>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(Method method, ClientDto clientDto) {

                    }
                });
            }
        });

        eventBus.addHandler(CreateOrderCommand.TYPE, new CreateOrderCommandHandler() {
            @Override
            public void onCreateOrder(OrderDto orderDto, CreateOrderCommand createOrderCommand) {
                OrderDto orderDto1 = orderDto;
                endPoint.createOrder(orderDto, new MethodCallback<OrderDto>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        System.out.println("ОрдерНЕТУТ");
                    }

                    @Override
                    public void onSuccess(Method method, OrderDto orderDto) {
                        System.out.println("ОрдерТУТ");
                    }
                });
            }
        });
    }
}
