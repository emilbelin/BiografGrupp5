package com.example.application.views.Kund;
import com.example.application.Backend.KundService;
import com.example.application.Backend.Kund;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.router.Route;


@Route("")
@PageTitle("Välj en film")
@CssImport("./views/helloworld/hello-world-view.css")
public class FilmView extends VerticalLayout {

    private KundService kundService;
    private Grid<Kund> grid = new Grid<>(Kund.class);
    private BokningsForm form;

    public FilmView(KundService kundService) {
        this.kundService = kundService;
        setSizeFull();
        grid.setSizeFull();
        grid.setColumns("fornamn", "efternamn", "telefonnummer");
        form = new BokningsForm();
        Div content = new Div(grid, form);
        content.setSizeFull();
        add(content);
        updateList();
    }

    private void updateList()
    {
        grid.setItems(kundService.findAll());
    }
}