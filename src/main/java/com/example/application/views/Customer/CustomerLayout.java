package com.example.application.views.Customer;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class CustomerLayout extends AppLayout {

    public CustomerLayout()
    {
        createHeader();
    }

    public void createHeader()
    {
        H1 logo = new H1("Bokning");
        logo.addClassName("logo");
        Button backButton = new Button("Gå till Startsidan");
        backButton.addClickListener(event -> UI.getCurrent().navigate(""));
        HorizontalLayout header = new HorizontalLayout(logo);
        HorizontalLayout button = new HorizontalLayout(backButton);
        backButton.setWidth("100%");
        backButton.setHeight("100%");
        header.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.setWidth("90%");
        header.addClassName("header");

        addToNavbar(header, button);
    }


}
