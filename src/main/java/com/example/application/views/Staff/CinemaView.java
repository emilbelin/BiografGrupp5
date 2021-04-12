package com.example.application.views.Staff;

import com.example.application.Backend.model.Cinema;
import com.example.application.Backend.model.Movie;
import com.example.application.Backend.service.CinemaService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.router.Route;

@Route(value = "Cinema", layout = StaffLayout.class)
public class CinemaView extends VerticalLayout {

    CinemaService cinemaService;
    Grid<Cinema> grid = new Grid<>(Cinema.class);
    SingleSelect<Grid<Cinema>, Cinema> selection = grid.asSingleSelect();

    public CinemaView(CinemaService cinemaService)
    {
        this.cinemaService = cinemaService;
        setSizeFull();
        grid.setColumns("namn","telefon", "adress");
        grid.getColumnByKey("namn").setHeader("Biograf");
        grid.getColumnByKey("telefon").setHeader("Telefonnummer");
        grid.getColumnByKey("adress").setHeader("Adress");
        add(grid);
        updateList();
    }

    public void updateList() {
        grid.setItems(cinemaService.findAll());
    }
}
