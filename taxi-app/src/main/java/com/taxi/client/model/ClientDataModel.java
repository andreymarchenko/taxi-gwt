package com.taxi.client.model;

import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.taxi.client.commands.ChangeDataCommand;
import com.taxi.client.commands.ChangeDataCommandHandler;
import com.taxi.server.model.Client;
import com.taxi.shared.dto.ClientDto;

import javax.inject.Inject;
import java.util.List;

public class ClientDataModel {

    private EventBus eventBus;
    private ClientDto activeClient;
    private boolean isActive = false;
    private List<ClientDto> allClients;

    public ClientDataModel() {
    }

    @Inject
    public ClientDataModel(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public List<ClientDto> getAllClients() {
        return this.allClients;
    }

    public HandlerRegistration addDataChangedEventHandler(final ChangeDataCommandHandler handler) {
        return eventBus.addHandler(ChangeDataCommand.TYPE, handler);
    }

    public void setAllClients(List<ClientDto> clients) {
        /*if (this.allClients != null && drivers != null) {
            if (this.allClients.size() == drivers.size()) return;
            else {
                this.allClients = drivers;
                eventBus.fireEvent(LoadLightDataEvent.create());
            }
        } else if (this.allClients == null && drivers != null) {
            this.allClients = drivers;
            eventBus.fireEvent(LoadLightDataEvent.create());
        } else if (drivers == null) {
            return;
        }*/
    }

    public void setActiveClient(ClientDto client) {
        if (client == null) return;
        else {
            this.activeClient = client;
            eventBus.fireEvent(ChangeDataCommand.create());
        }
    }

    public ClientDto getActiveClient() {
        return this.activeClient;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
        eventBus.fireEvent(ChangeDataCommand.create());
    }
}
