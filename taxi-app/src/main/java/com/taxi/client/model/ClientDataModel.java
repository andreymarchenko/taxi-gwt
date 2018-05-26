package com.taxi.client.model;

import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.taxi.server.model.Client;

import javax.inject.Inject;
import java.util.List;

public class ClientDataModel {
    /*private EventBus eventBus;
    List<Client> allClients;
    Client activeClient;

    public ClientDataModel() {}

    //@Inject
    public ClientDataModel(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public List<Client> getAllClients() {
        return this.allClients;
    }

    public void setAllClients(List<Client> drivers) {
        *//*if (this.allClients != null && drivers != null) {
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
        }*//*
    }

    public void setActiveClient(Client driver) {
*//*        if (driver == null) return;
        else {
            this.activeClient = driver;
            eventBus.fireEvent(ChangeDataEvent.create());
        }*//*
    }

    public Client getActiveClient() {
        return this.activeClient;
    }
*/
}
