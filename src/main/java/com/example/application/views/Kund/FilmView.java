package com.example.application.views.Kund;
import com.example.application.Backend.Film.Film;
import com.example.application.Backend.Film.FilmService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.notification.Notification;


@Route("film")
@PageTitle("Välj en film")
@CssImport("./views/helloworld/hello-world-view.css")
public class FilmView extends VerticalLayout {

    private FilmService filmService;
    private Grid<Film> grid = new Grid<>(Film.class);
    SingleSelect<Grid<Film>, Film> selection = grid.asSingleSelect();
    private FilmForm form;

    public FilmView(FilmService filmService) {
        this.filmService = filmService;
        setSizeFull();
        HorizontalLayout test = new HorizontalLayout();
        //grid.setColumns("fornamn", "efternamn", "telefonnummer");
        grid.setColumns("titel", "sprak", "aldergrans", "genre", "langd");
        grid.getColumnByKey("titel").setHeader("Titel");
        grid.getColumnByKey("sprak").setHeader("Språk");
        grid.getColumnByKey("aldergrans").setHeader("Åldersgräns");
        grid.getColumnByKey("genre").setHeader("Genre");
        grid.getColumnByKey("langd").setHeader("Längd");
        form = new FilmForm(filmService, this);
        add(test, grid, form);
        updateList();

    }
    public void updateList()
    {
        grid.setItems(filmService.findAll());
    }
}