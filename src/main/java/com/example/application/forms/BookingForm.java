package com.example.application.forms;

import com.example.application.Backend.model.*;
import com.example.application.Backend.service.BookingService;
import com.example.application.views.Customer.BookingView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.example.application.Backend.model.Customer;
import com.vaadin.flow.dom.Style;

import java.util.ArrayList;
import java.util.List;

public class BookingForm extends FormLayout {

    protected List<Customer> customers;

    protected BookingService bookingService;
    protected BookingView bookingView;

    protected TextField first_Name = new TextField("Namn");
    protected TextField last_Name = new TextField("Efternamn");
    protected TextField phone = new TextField("Telefonnummer");
    protected ComboBox<Row> rowPicker = new ComboBox<>("Rad");
    protected ComboBox<Chair> chairPicker = new ComboBox<>("Stol");
    protected ComboBox<PaymentMethod> paymentPicker = new ComboBox<>("Betalning");

    protected BookingObject bookingObject = new BookingObject(null, null, null, null);
    protected Customer customer = new Customer("","","");

    protected Binder<Customer> binderCustomer = new Binder<>(Customer.class);
    protected Binder<BookingObject> binder = new Binder<>(BookingObject.class);

    protected Button confirm = new Button("Boka");
    protected Button cancel = new Button("Avbryt");

    protected HorizontalLayout buttonLayout = new HorizontalLayout();

    public BookingForm(BookingService bookingService, BookingView bookingView)
    {
        customers = bookingService.findCustomers();

        this.bookingView = bookingView;
        this.bookingService = bookingService;

        ConfigureBinder();
        ConfigureButtonTheme();
        ConfigureButtonHandler();
        ConfigureComboBoxes();

        buttonLayout.add(confirm, cancel);
    }

    private void closeForm()
    {
        this.setVisible(false);
    }

    //Fixme dåligt kodat
    private int FrankensteinsMonster()
    {
        int returnValue = 0;
        for(Customer customer: customers)
        {

            if (phone.getValue().equals(customer.getTelefonnummer())) {
                returnValue += 1;
            }
            if(first_Name.getValue().equals(customer.getFornamn()) && last_Name.getValue().equals(customer.getEfternamn()))
            {
                returnValue += 1;
            }

        }
        if(returnValue > 2)
        {
            returnValue = 2;
        }
        return returnValue;

    }
    public void existerandeKundBiljett()
    {
        int tempInt = bookingService.findWithPhoneNumber(phone.getValue());
        bookingService.addBooking(tempInt, paymentPicker.getValue().getId(), bookingView.getSelection().getId(), chairPicker.getValue().getId());
        addNotification("Biljett bokad " + "på telefonnummer " + phone.getValue());
        toggleForm(true);
        bookingView.populateGrid();
    }

    public void kundFinnsRedanError()
    {
        addNotification("Var god använd ett annat telefonnummer");
        customers = bookingService.findCustomers();
        phone.setValue("");
    }

    public void nyKundBiljett()
    {
        bookingService.addCustomer(first_Name.getValue(), last_Name.getValue(), phone.getValue());
        int tempInt = bookingService.findWithPhoneNumber(phone.getValue());
        bookingService.addBooking(tempInt, paymentPicker.getValue().getId(), bookingView.getSelection().getId(), chairPicker.getValue().getId());
        addNotification("Biljett bokad " + "på telefonnummer " + phone.getValue());
        customers = bookingService.findCustomers();
        toggleForm(true);
        bookingView.populateGrid();
    }

    public void addNotification(String message)
    {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Label label = new Label(message);
        label.setWidthFull();
        Notification notify = new Notification();
        notify.setPosition(Notification.Position.MIDDLE);
        Button notificationButton = new Button("Close");
        horizontalLayout.add(label, notificationButton);
        notificationButton.addClickListener(event -> notify.close());
        notify.add(horizontalLayout);
        notificationButton.setVisible(true);
        notify.open();
    }

    private void clearForm()
    {
        customer = new Customer("","","");
        bookingObject = new BookingObject(null,null,null,null);
        binderCustomer.setBean(customer);
        binder.setBean(bookingObject);
    }

    private void fillForm()
    {
        customer = new Customer( first_Name.getValue(), last_Name.getValue(), phone.getValue());
        bookingObject = new BookingObject(customer, chairPicker.getValue(), rowPicker.getValue(), paymentPicker.getValue());
        binderCustomer.setBean(customer);
        binder.setBean(bookingObject);
    }

    public void toggleForm(boolean bool)
    {
        if(bool)
        {
            chairPicker.setEnabled(false);
            rowPicker.setEnabled(false);
            remove(first_Name, last_Name, phone, rowPicker, chairPicker, paymentPicker, buttonLayout);
            clearForm();
            fillForm();
            add(first_Name, last_Name, phone, rowPicker, chairPicker, paymentPicker, buttonLayout);
            this.setVisible(true);
        }
        else
        {
            this.setVisible(false);
            remove(first_Name, last_Name, phone, rowPicker, chairPicker, paymentPicker, buttonLayout);
        }
    }

    public void ConfigureBinder()
    {
        binderCustomer.forField(first_Name).bind(Customer::getFornamn, Customer::setFornamn);
        binderCustomer.forField(last_Name).bind(Customer::getEfternamn, Customer::setEfternamn);
        binderCustomer.forField(phone).bind(Customer::getTelefonnummer, Customer::setTelefonnummer);

        binder.forField(rowPicker).bind(BookingObject::getRow, BookingObject::setRow);
        binder.forField(chairPicker).bind(BookingObject::getChair, BookingObject::setChair);
        binder.forField(paymentPicker).bind(BookingObject::getPaymentMethod, BookingObject::setPaymentMethod);

        binderCustomer.setBean(customer);
        binder.setBean(bookingObject);
    }

    public void ConfigureButtonTheme() {

        confirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);

    }

    public void CheckForNameAndPhone()
    {
        switch (FrankensteinsMonster())
        {
            case(0):
                nyKundBiljett();
                break;
            case(1):
                kundFinnsRedanError();
                break;
            case(2):
                existerandeKundBiljett();
                break;
            default:
                System.out.println("fel");
        }
    }
    public void ConfigureButtonHandler()
    {
        confirm.addClickListener(event -> CheckForNameAndPhone());
        cancel.addClickListener(event -> toggleForm(false));
    }

    public void ConfigureComboBoxes()
    {
        rowPicker.setEnabled(false);
        chairPicker.setEnabled(false);

        paymentPicker.setItemLabelGenerator(PaymentMethod::getBetalningsmetod);
        paymentPicker.setItems(bookingService.findPaymentMethod());

    }

    public void ConfigureChairPicker()
    {
        if(rowPicker.getValue()!=null) {
            chairPicker.setItemLabelGenerator(Chair::convertNummer);
            chairPicker.setItems(bookingService.findChair(rowPicker.getValue().getId()));
            chairPicker.setEnabled(true);
        }
    }

    public void ConfigureRowPicker(boolean bool)
    {
        if(bool && !rowPicker.isEnabled()){
            rowPicker.setEnabled(true);
            rowPicker.setItemLabelGenerator(Row::convertNummer);
            rowPicker.setItems(bookingService.findRow(bookingView.getSelection().getSalong()));
            rowPicker.addValueChangeListener(event -> ConfigureChairPicker());
        }
    }
}
