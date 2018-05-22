package com.taxi.client.controller;

import com.google.web.bindery.event.shared.EventBus;
import com.taxi.client.presenter.Presenter;
import com.taxi.client.view.View;

import javax.inject.Inject;

public class LifeCycle {
    private View view;
    private Presenter presenter;
    private EventBus eventBus;

    @Inject
    public LifeCycle(View view,
                     Presenter presenter,
                     EventBus eventBus) {
        this.view = view;
        this.eventBus = eventBus;
        this.presenter = presenter;
    }

    public void start() {
        view.createUi();
        presenter.bind();
       // eventBus.fireEvent();
    }
}
