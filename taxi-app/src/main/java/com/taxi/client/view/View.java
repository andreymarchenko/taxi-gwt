package com.taxi.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.taxi.client.presenter.Presenter;
import com.taxi.client.view.dialog.OrderDialog;
import com.taxi.client.view.map.Map;
import com.vaadin.polymer.paper.widget.PaperButton;

import javax.inject.Inject;
import java.util.ArrayList;

public class View extends Composite {

    interface ViewUiBinder extends UiBinder<Widget, View> {
    }

    private static ViewUiBinder ourUiBinder = GWT.create(ViewUiBinder.class);

    @UiField
    RootLayoutPanel mapPanel;

    private Map map;
    private Presenter presenter;
    private EventBus eventBus;

    @Inject
    public View(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    private void loadMapApi() {
        boolean sensor = true;

        ArrayList<LoadApi.LoadLibrary> loadLibraries = new ArrayList<LoadApi.LoadLibrary>();
        loadLibraries.add(LoadApi.LoadLibrary.ADSENSE);
        loadLibraries.add(LoadApi.LoadLibrary.DRAWING);
        loadLibraries.add(LoadApi.LoadLibrary.GEOMETRY);
        loadLibraries.add(LoadApi.LoadLibrary.PANORAMIO);
        loadLibraries.add(LoadApi.LoadLibrary.PLACES);
        loadLibraries.add(LoadApi.LoadLibrary.WEATHER);
        loadLibraries.add(LoadApi.LoadLibrary.VISUALIZATION);

        Runnable onLoad = new Runnable() {
            @Override
            public void run() {
                map = new Map();
                map.setPresenter(presenter);
                mapPanel.add(map);
                prepareMap();
            }
        };

        LoadApi.go(onLoad, loadLibraries, sensor);
    }

    public void createUi() {
        initWidget(ourUiBinder.createAndBindUi(this));
        loadMapApi();
        RootPanel.get("root").add(this);
    }

    private void prepareMap() {
        if (map != null) {
            map.getMapWidget().addClickHandler(new ClickMapHandler() {
                @Override
                public void onEvent(ClickMapEvent clickMapEvent) {

                }
            });
        }
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}