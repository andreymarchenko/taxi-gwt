package com.taxi.client.presenter;

import com.google.web.bindery.event.shared.EventBus;
import com.taxi.client.controller.Controller;
import com.taxi.client.view.View;

import javax.inject.Inject;

public class Presenter {

    private Controller controller;
    private View view;
    private EventBus eventBus;

    @Inject
    public Presenter(Controller controller,
                     View view,
                     EventBus eventBus) {
        this.controller = controller;
        this.view = view;
        this.eventBus = eventBus;
        view.setPresenter(this);
    }

    public void bind() {

    }
}
