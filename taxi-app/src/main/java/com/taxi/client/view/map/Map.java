package com.taxi.client.view.map;

import com.google.gwt.ajaxloader.client.ArrayHelper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.adsense.AdFormat;
import com.google.gwt.maps.client.adsense.AdUnitOptions;
import com.google.gwt.maps.client.adsense.AdUnitWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.controls.ControlPosition;
import com.google.gwt.maps.client.events.channelnumber.ChannelNumberChangeMapEvent;
import com.google.gwt.maps.client.events.channelnumber.ChannelNumberChangeMapHandler;
import com.google.gwt.maps.client.events.drag.DragMapEvent;
import com.google.gwt.maps.client.events.drag.DragMapHandler;
import com.google.gwt.maps.client.events.format.FormatChangeMapEvent;
import com.google.gwt.maps.client.events.format.FormatChangeMapHandler;
import com.google.gwt.maps.client.events.mapchange.MapChangeMapEvent;
import com.google.gwt.maps.client.events.mapchange.MapChangeMapHandler;
import com.google.gwt.maps.client.events.position.PositionChangeMapEvent;
import com.google.gwt.maps.client.events.position.PositionChangeMapHandler;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.overlays.MarkerOptions;
import com.google.gwt.maps.client.services.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.taxi.client.presenter.Presenter;
import com.taxi.client.service.EndPoint;
import com.taxi.client.view.dialog.OrderDialog;
import com.taxi.client.view.dialog.OrderInfoDialog;

import java.util.ArrayList;
import java.util.List;

public class Map extends Composite {

    private VerticalPanel panel;
    private List<Marker> markers;
    private Presenter presenter;
    private MapWidget mapWidget;
    private LatLng origin;
    private LatLng destination;
    private Integer clickNumber = 0;
    private Boolean isRouteBuilt = false;
    private OrderDialog orderDialog;
    private OrderInfoDialog orderInfoDialog;

    private final EndPoint endPoint = GWT.create(EndPoint.class);

    public Map() {
        this.panel = new VerticalPanel();
        this.markers = new ArrayList<>();
        initWidget(panel);
        drawMap();
        drawMapAds();
    }

    public void bind() {
        this.getMapWidget().addDblClickHandler(dblClickMapEvent -> {
            if (markers.size() < 2) {
                MarkerOptions options = MarkerOptions.newInstance();
                options.setDraggable(true);
                options.setFlat(true);
                options.setPosition(LatLng.newInstance(
                        dblClickMapEvent.getMouseEvent().getLatLng().getLatitude(),
                        dblClickMapEvent.getMouseEvent().getLatLng().getLongitude()));

                final Marker marker = Marker.newInstance(options);
                marker.setMap(mapWidget);

                if (markers.size() == 0) {
                    origin = dblClickMapEvent.getMouseEvent().getLatLng();
                    orderDialog.getFrom().setText(dblClickMapEvent.getMouseEvent().getLatLng().getToString());
                }
                else if (markers.size() == 1) {
                    destination = dblClickMapEvent.getMouseEvent().getLatLng();
                    orderDialog.getTo().setText(dblClickMapEvent.getMouseEvent().getLatLng().getToString());
                }

                marker.addDragHandler(new DragMapHandler() {
                    @Override
                    public void onEvent(DragMapEvent dragMapEvent) {

                    }
                });
                markers.add(marker);
            }

            orderDialog.getCalculateRouteButton().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    if(markers.size() == 2) {
                        drawDirections();
                        orderDialog.setMakeOrderButtonClickable(true);
                    }
                }
            });

            if (!isRouteBuilt) {
                clickNumber++;
                if (clickNumber == 1) {
                    origin = dblClickMapEvent.getMouseEvent().getLatLng();
                }
                else {
                    destination = dblClickMapEvent.getMouseEvent().getLatLng();
                    isRouteBuilt = true;
                }
            }
        });
        orderDialog.getMakeOrderButton().addClickHandler(event -> {
            if (orderDialog.isMakeOrderButtonClickable()) {
                orderInfoDialog = new OrderInfoDialog();
                orderInfoDialog.show();
                getDistance();
            }
        });
    }

    private MapOptions createDefaultMapOptions() {
        MapOptions options = MapOptions.newInstance();
        options.setZoom(11);
        options.setDisableDoubleClickZoom(true);
        options.setCenter(LatLng.newInstance(56.1937, 44.0027));
        options.setMapTypeId(MapTypeId.ROADMAP);
        return options;
    }

    private void drawMap() {
        mapWidget = new MapWidget(createDefaultMapOptions());
        panel.add(mapWidget);
        mapWidget.setSize(Double.toString(Window.getClientWidth()), Double.toString(14.0 / 15 * Window.getClientHeight() - 8));
    }

    private void drawDirections() {
        final DirectionsRenderer directionsDisplay = DirectionsRenderer.newInstance(
                DirectionsRendererOptions.newInstance());
        directionsDisplay.setMap(mapWidget);

        DirectionsRequest request = DirectionsRequest.newInstance();
        request.setOrigin(origin);
        request.setDestination(destination);
        request.setTravelMode(TravelMode.DRIVING);
        request.setOptimizeWaypoints(true);

        DirectionsService o = DirectionsService.newInstance();

        o.route(request, new DirectionsResultHandler() {
            public void onCallback(DirectionsResult result, DirectionsStatus status) {
                if (status == DirectionsStatus.OK) {
                    directionsDisplay.setDirections(result);
                } else if (status == DirectionsStatus.INVALID_REQUEST) {

                } else if (status == DirectionsStatus.MAX_WAYPOINTS_EXCEEDED) {

                } else if (status == DirectionsStatus.NOT_FOUND) {

                } else if (status == DirectionsStatus.OVER_QUERY_LIMIT) {

                } else if (status == DirectionsStatus.REQUEST_DENIED) {

                } else if (status == DirectionsStatus.UNKNOWN_ERROR) {

                } else if (status == DirectionsStatus.ZERO_RESULTS) {

                }

            }
        });
    }

    private void getDistance() {
        LatLng[] ao = new LatLng[1];
        ao[0] = origin;
        JsArray origins = ArrayHelper.toJsArray(ao);

        LatLng[] ad = new LatLng[1];
        ad[0] = destination;
        JsArray destinations = ArrayHelper.toJsArray(ad);

        DistanceMatrixRequest request = DistanceMatrixRequest.newInstance();
        request.setOrigins(origins);
        request.setDestinations(destinations);
        request.setTravelMode(TravelMode.DRIVING);

        DistanceMatrixService o = DistanceMatrixService.newInstance();
        o.getDistanceMatrix(request, (response, status) -> {

            if (status == DistanceMatrixStatus.INVALID_REQUEST) {

            } else if (status == DistanceMatrixStatus.MAX_DIMENSIONS_EXCEEDED) {

            } else if (status == DistanceMatrixStatus.MAX_ELEMENTS_EXCEEDED) {

            } else if (status == DistanceMatrixStatus.OK) {

                @SuppressWarnings("unused")
                JsArrayString dest = response.getDestinationAddresses();
                @SuppressWarnings("unused")
                JsArrayString org = response.getOriginAddresses();
                JsArray<DistanceMatrixResponseRow> rows = response.getRows();
                DistanceMatrixResponseRow d = rows.get(0);
                JsArray<DistanceMatrixResponseElement> elements = d.getElements();
                for (int i = 0; i < elements.length(); i++) {
                    DistanceMatrixResponseElement e = elements.get(i);
                    Distance distance = e.getDistance();
                    Duration duration = e.getDuration();

                    @SuppressWarnings("unused")
                    DistanceMatrixElementStatus st = e.getStatus();
                    orderInfoDialog.setDistance(distance.getText());
                    orderInfoDialog.setDuration(duration.getText());
                }

            } else if (status == DistanceMatrixStatus.OVER_QUERY_LIMIT) {

            } else if (status == DistanceMatrixStatus.REQUEST_DENIED) {

            } else if (status == DistanceMatrixStatus.UNKNOWN_ERROR) {

            }

        });
    }

    private void drawMapAds() {
        AdUnitOptions options = AdUnitOptions.newInstance();
        options.setFormat(AdFormat.HALF_BANNER);
        options.setPosition(ControlPosition.RIGHT_CENTER);
        options.setMap(mapWidget);
        options.setPublisherId("pub-0032065764310410");
        options.setChannelNumber("4000893900");

        AdUnitWidget adUnit = new AdUnitWidget(options);

        adUnit.addChannelNumberChangeHandler(new ChannelNumberChangeMapHandler() {
            @Override
            public void onEvent(ChannelNumberChangeMapEvent event) {
            }
        });

        adUnit.addFormatChangeHandler(new FormatChangeMapHandler() {
            @Override
            public void onEvent(FormatChangeMapEvent event) {
            }
        });

        adUnit.addMapChangeHandler(new MapChangeMapHandler() {
            @Override
            public void onEvent(MapChangeMapEvent event) {
            }
        });

        adUnit.addPositionChangeHandler(new PositionChangeMapHandler() {
            @Override
            public void onEvent(PositionChangeMapEvent event) {
            }
        });
    }

    public void launchApp(/*List of markers*/) {
        //It's necessary to draw all markers
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void clearMap() {
        for (int i = 0; i < markers.size(); i++) {
            markers.get(i).clear();
        }
    }

    public MapWidget getMapWidget() {
        return this.mapWidget;
    }

    public void setOrderDialog(OrderDialog orderDialog) {
        this.orderDialog = orderDialog;
    }
}
