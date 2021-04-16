package com.example.application.views.Staff;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightCondition;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Router;
import com.vaadin.flow.router.RouterLink;

import javax.swing.plaf.basic.BasicTabbedPaneUI;

@CssImport("./styles/shared-styles.js")
public class StaffLayout extends AppLayout {
    /**
     * The common layout between all "Staff" pages.
     * Contains
     * a Header with a logo and a "back" button
     * a Drawer containing links to the staff pages.
     */
    public StaffLayout()
    {
        createHeader();
        createDrawer();
    }


    private void createHeader()
    {
        H1 logo = new H1("Personal Sida");
        logo.addClassName("logo");
        Button backButton = new Button("Gå till Startsidan");
        backButton.addClickListener(event -> UI.getCurrent().navigate(""));
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
        HorizontalLayout button = new HorizontalLayout(backButton);
        header.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, logo);
        header.setWidth("90%");
        header.addClassName("header");

        addToNavbar(header, button);
    }

    private void createDrawer()
    {
        RouterLink showsLink = new RouterLink("Föreställningar", ShowsView.class);
        showsLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink filmLink = new RouterLink("Filmer", MovieView.class);
        filmLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink scheduleLink = new RouterLink("Schema", ScheduleView.class);
        scheduleLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(showsLink, filmLink, scheduleLink));
    }
}
