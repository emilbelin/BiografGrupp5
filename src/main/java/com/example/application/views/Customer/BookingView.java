package com.example.application.views.Customer;

import com.example.application.Backend.model.BookingViewModel;
import com.example.application.Backend.model.Customer;
import com.example.application.Backend.model.ShowViewModel;
import com.example.application.Backend.service.BookingService;
import com.example.application.Backend.service.ShowService;
import com.example.application.forms.BookingForm;
import com.example.application.views.Staff.ShowsView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.router.Route;

import javax.management.Notification;

@Route(value = "Bokning", layout = CustomerLayout.class)
public class BookingView extends VerticalLayout {
    protected ShowService showService;
    protected BookingForm form;
    protected BookingService bookingService;
    protected Grid<ShowViewModel> grid = new Grid<>(ShowViewModel.class);
    protected SingleSelect<Grid<ShowViewModel>, ShowViewModel> select = grid.asSingleSelect();

    public BookingView(ShowService showService,BookingService bookingService)
    {
        this.bookingService = bookingService;
        this.showService = showService;
        this.form = new BookingForm(bookingService, this);

        setSizeFull();
        grid.setColumns("film", "biograf", "salong", "platser_kvar", "tid", "datum", "id");
        grid.removeColumn(grid.getColumnByKey("id"));
        grid.getColumnByKey("platser_kvar").setHeader("Platser Kvar");
        grid.asSingleSelect().addValueChangeListener(event -> selectHandler());
        populateGrid();
        form.setVisible(false);
        add(grid, form);


    }

    public ShowViewModel getSelection()
    {
        return select.getValue();
    }
    public void populateGrid()
    {
        grid.setItems(showService.findForestallningView());
    }
    public void selectHandler()
    {
        boolean bool;
        bool = select.getValue() != null;
        form.toggleForm(bool);
        form.ConfigureRowPicker(bool);
        System.out.println(bool);
        if(select.getValue() != null)
        {
            if(select.getValue().getPlatser_kvar() < 1)
            {
                form.addNotification("Inga platser kvar!");
                form.setVisible(false);
            }
        }

    }
}
