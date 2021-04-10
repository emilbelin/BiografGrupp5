package com.example.application.views.Staff;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

@CssImport("./styles/shared-styles.js")
public class StaffLayout extends AppLayout {
    public StaffLayout()
    {
        createHeader();
        createDrawer();
    }

    private void createHeader()
    {
        H1 logo = new H1("Personal Sida");
        logo.addClassName("logo");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");

        addToNavbar(header);
    }

    private void createDrawer()
    {
        RouterLink forestallningarLink = new RouterLink("Föreställningar", ShowsView.class);
        forestallningarLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink filmLink = new RouterLink("Filmer", MovieView.class);
        filmLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(forestallningarLink, filmLink));
    }
}
