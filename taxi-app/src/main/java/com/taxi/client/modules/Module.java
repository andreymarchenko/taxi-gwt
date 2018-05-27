package com.taxi.client.modules;

import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.taxi.client.controller.Controller;
import com.taxi.client.controller.LifeCycle;
import com.taxi.client.model.ClientDataModel;
import com.taxi.client.presenter.Presenter;
import com.taxi.client.view.View;


public class Module extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(Presenter.class).asEagerSingleton();
        bind(Controller.class).in(Singleton.class);
        bind(ClientDataModel.class).in(Singleton.class);
        bind(View.class).in(Singleton.class);
        bind(LifeCycle.class).in(Singleton.class);
    }
}
