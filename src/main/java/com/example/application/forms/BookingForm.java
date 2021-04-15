package com.example.application.forms;

import com.example.application.Backend.model.*;
import com.example.application.Backend.service.BookingService;
import com.example.application.views.Customer.BookingView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.example.application.Backend.model.Customer;

public class BookingForm extends FormLayout {

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
        this.bookingView = bookingView;
        this.bookingService = bookingService;

        ConfigureBinder();
        ConfigureButtons();
        ConfigureComboBoxes();

        buttonLayout.add(confirm, cancel);
    }
    private void closeForm()
    {
        this.setVisible(false);
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
        customer = new Customer(first_Name.getValue(), last_Name.getValue(), phone.getValue());
        bookingObject = new BookingObject(customer, chairPicker.getValue(), rowPicker.getValue(), paymentPicker.getValue());
        binderCustomer.setBean(customer);
        binder.setBean(bookingObject);
    }
    public void toggleForm(boolean bool)
    {
        if(bool)
        {
            remove(first_Name, last_Name, phone, rowPicker, chairPicker, paymentPicker, buttonLayout);
            clearForm();
            fillForm();
            add(first_Name, last_Name, phone, rowPicker, chairPicker, paymentPicker, buttonLayout);
            this.setVisible(true);
        }
        else
        {
            clearForm();
            remove(first_Name, last_Name, phone, rowPicker, chairPicker, paymentPicker, buttonLayout);
            this.setVisible(false);
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

    public void ConfigureButtons()
    {
        confirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);


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
        chairPicker.setItemLabelGenerator(Chair::convertNummer);
        chairPicker.setItems(bookingService.findChair(rowPicker.getValue().getSalong_id()));
        chairPicker.setEnabled(true);
    }

    public void ConfigureRowPicker(boolean bool)
    {
        if(bool) {
            rowPicker.setEnabled(true);
            rowPicker.setItemLabelGenerator(Row::convertNummer);
            rowPicker.setItems(bookingService.findRow(bookingView.getSelection().getSalong()));
            rowPicker.addValueChangeListener(event -> ConfigureChairPicker());
        }
        else
        {
            rowPicker.setEnabled(false);
        }
    }
}
