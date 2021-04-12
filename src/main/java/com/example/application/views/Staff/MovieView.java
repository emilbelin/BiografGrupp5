package com.example.application.views.Staff;
import com.example.application.Backend.model.Movie;
import com.example.application.Backend.service.MovieService;
import com.example.application.forms.FilmForm;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "", layout = StaffLayout.class)
@PageTitle("Lägg till/ta bort filmer")
public class MovieView extends VerticalLayout {

    private MovieService movieService;
    private Grid<Movie> grid = new Grid<>(Movie.class);
    protected SingleSelect<Grid<Movie>, Movie> selection = grid.asSingleSelect();
    private FilmForm form;

    public MovieView(MovieService movieService) {
        this.movieService = movieService;
        setSizeFull();
        HorizontalLayout test = new HorizontalLayout();
        //grid.setColumns("fornamn", "efternamn", "telefonnummer");
        grid.setColumns("titel", "sprak", "aldergrans", "genre", "langd");
        grid.getColumnByKey("titel").setHeader("Titel");
        grid.getColumnByKey("sprak").setHeader("Språk");
        grid.getColumnByKey("aldergrans").setHeader("Åldersgräns");
        grid.getColumnByKey("genre").setHeader("Genre");
        grid.getColumnByKey("langd").setHeader("Längd(Minuter)");
        form = new FilmForm(movieService, this);
        add(test, grid, form);
        updateList();

    }

    /**
     * @return the Movie object that is currently selected in the grid
     */
    public Movie getSelection()
    {
        return selection.getValue();
    }


    /**
     * Fills the grid with data from the mySQL database.
     * MovieService.findAll() runs a query and returns a list of objects from the DB.
     */
    public void updateList()
    {
        grid.setItems(movieService.findAll());
    }
}