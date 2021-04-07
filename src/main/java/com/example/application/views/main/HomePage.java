package com.example.application.views.main;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.PageTitle;

import javax.persistence.Entity;

@PageTitle("Välj ett alternativ")
@CssImport("./views/helloworld/hello-world-view.css")

public class HomePage extends AppLayout {

    private H1 viewTitle;
    private final Tabs menu;

    public HomePage(){

        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        menu = createMenu();
        addToDrawer(createDrawerContent(menu));

        }

        private Component createHeaderContent() {
            HorizontalLayout layout = new HorizontalLayout();
            layout.setId("header");
            layout.getThemeList().set("dark", true);
            layout.setWidthFull();
            layout.setSpacing(false);
            layout.setAlignItems(FlexComponent.Alignment.CENTER);
            viewTitle = new H1();
            layout.add(viewTitle);
            layout.add(new Avatar());
            return layout;


    }
    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        logoLayout.add(new Image("images/javabio.png", "My App logo"));
        logoLayout.add(new H1("Biografen"));
        layout.add(logoLayout, menu);
        return layout;
    }
    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        return tabs;
    }






}
