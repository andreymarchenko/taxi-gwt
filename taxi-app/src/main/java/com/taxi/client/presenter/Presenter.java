package com.taxi.client.presenter;

import com.google.web.bindery.event.shared.EventBus;
import com.taxi.client.commands.ChangeDataCommand;
import com.taxi.client.commands.ChangeDataCommandHandler;
import com.taxi.client.commands.LoginCommand;
import com.taxi.client.commands.RegistrationCommand;
import com.taxi.client.controller.Controller;
import com.taxi.client.model.ClientDataModel;
import com.taxi.client.model.DriverDataModel;
import com.taxi.client.model.OperatorDataModel;
import com.taxi.client.model.OrderDataModel;
import com.taxi.client.view.View;
import com.taxi.server.model.Driver;
import com.taxi.shared.dto.ClientDto;
import com.taxi.shared.dto.LoginDto;

import javax.inject.Inject;

public class Presenter {

    private Controller controller;
    private ClientDataModel clientDataModel;
    private View view;
    private EventBus eventBus;

/*    private ClientDataModel clientDataModel;
    private DriverDataModel driverDataModel;
    private OperatorDataModel operatorDataModel;
    private OrderDataModel orderDataModel;*/

    @Inject
    public Presenter(Controller controller,
                     final ClientDataModel clientDataModel,
                     /*final DriverDataModel driverDataModel,
                     final OperatorDataModel operatorDataModel,
                     final OrderDataModel orderDataModel,*/
                     View view,
                     EventBus eventBus) {
        this.controller = controller;
        this.view = view;
        this.clientDataModel = clientDataModel;
        /*this.driverDataModel = driverDataModel;
        this.operatorDataModel = operatorDataModel;
        this.orderDataModel = orderDataModel;*/
        this.eventBus = eventBus;
        view.setPresenter(this);
    }

    public void bind() {
        //Переписать на addLoginEventHandler
        clientDataModel.addDataChangedEventHandler(new ChangeDataCommandHandler() {
            @Override
            public void onChangeData(ChangeDataCommand changeDataCommand) {
                view.loginUser(clientDataModel.getActiveClient());
                view.setActive(clientDataModel.isActive());
            }
        });
    }

    public void login(LoginDto loginDto) {
        eventBus.fireEvent(LoginCommand.create(loginDto));
    }

    public void registration(ClientDto clientDto) {
        eventBus.fireEvent(RegistrationCommand.create(clientDto));
    }

}
