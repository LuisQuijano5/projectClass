package com.example.customer;

import com.example.customer.models.Address;
import com.example.customer.models.City;
import com.example.customer.models.Country;
import com.example.customer.models.Customer;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    private TableView<Customer> tblCustomer;
    private List<Customer> customerList = new ArrayList<>();
    private List<Address> addressList = new ArrayList<>();
    private List<City> cityList = new ArrayList<>();
    private List<Country> countryList = new ArrayList<>();
    @Override
    public void start(Stage stage) throws IOException {


        Scene scene = new Scene(buildGUI(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private VBox buildGUI(){
        VBox vbox = new VBox(15);

        tblCustomer = new TableView<>();
        TableColumn c1 = new TableColumn("ID");
        TableColumn <Customer, String> c2= new TableColumn("FULLNAME");
        TableColumn c3 = new TableColumn("EMAIL");

        c1.setCellValueFactory(new PropertyValueFactory<>("id"));
        //c2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        c2.setCellValueFactory((customerData) -> {
            return new SimpleStringProperty(
                    customerData.getValue().getFirstName() + " " +
                    customerData.getValue().getLastName());
        });
        c3.setCellValueFactory(new PropertyValueFactory<>("email"));

        tblCustomer.getColumns().addAll(c1, c2, c3);

        initCountriesAndCities();
        initCustomers();
        ObservableList<Customer> obsList = FXCollections.observableArrayList(customerList);
        tblCustomer.setItems(obsList);

        vbox.getChildren().add(tblCustomer);
        return vbox;
    }

    private void initCountriesAndCities(){
        Country mx = new Country(1, "MEXICO");
        Country us = new Country(2, "USA");
        Country cnd = new Country(3, "CANADA");
        Country arg = new Country(4, "ARG");
        Country eng = new Country(5, "ENGLAND");

        countryList.add(mx);
        countryList.add(us);
        countryList.add(cnd);
        countryList.add(arg);
        countryList.add(eng);

        City celaya = new City(1, "CELAYA", "Guanajuato", mx);
        City salamanca = new City(2, "SALAMANCA", "Guanajuato", mx);
        City irapuato = new City(3, "IRAPUATO", "Guanajuato", mx);

        City chicago = new City(4, "CHICAGO", "Illinois", us);
        City la = new City(5, "Los Angeles", "California", us);
        City houston = new City(6, "HOUSTON", "Texas", us);

        City toronto = new City(7, "TORONTO", "Quebec", cnd);
        City otawa = new City(8, "OTAWA", "Quebec", cnd);
        City montreal = new City(9, "MONTREAL", "Quebec", cnd);

        City ba = new City(10, "BUENOS AIRES", "Mar de Plata", arg);
        City rosario = new City(11, "ROSARIO", "Mar de Plata", arg);
        City velez = new City(12, "VELEZ", "Mar de Plata", arg);

        City london = new City(13, "LONDON", "London", eng);
        City brentford = new City(14, "BRENTFORD", "London", eng);
        City chelsea = new City(15, "CHELSEA", "London", eng);

        cityList.add(celaya);
        cityList.add(salamanca);
        cityList.add(irapuato);
        cityList.add(la);
        cityList.add(chicago);
        cityList.add(houston);
        cityList.add(toronto);
        cityList.add(otawa);
        cityList.add(montreal);
        cityList.add(ba);
        cityList.add(rosario);
        cityList.add(velez);
        cityList.add(london);
        cityList.add(brentford);
        cityList.add(chelsea);
    }

    private void initCustomers(){
        Address ad1 = new Address(1, "AV. 1 #1", "2394829", cityList.get(0));
        Address ad2 = new Address(2, "AVENUE. 1 #1", "2392133", cityList.get(3));
        Address ad3 = new Address(3, "RUE. 1 #1", "2321333", cityList.get(6));
        Address ad4 = new Address(4, "AVENIDA CHE. 1 #1", "4324434", cityList.get(9));
        Address ad5 = new Address(5, "AVENUE MATE. 1 #1", "4324324", cityList.get(12));

        for (int i = 1; i <= 5; i++) {
            customerList.add(new Customer(i, "Customer " + i, "Lastname", "correo@gmail.com", true, ad1));
        }
        for (int i = 6; i <= 10; i++) {
            customerList.add(new Customer(i, "Customer " + i, "Lastname", "mail@gmail.com", true, ad2));
        }
        for (int i = 11; i <= 15; i++) {
            customerList.add(new Customer(i, "Customer " + i, "Lastname", "mailbou@gmail.com", true, ad3));
        }
        for (int i = 16; i <= 20; i++) {
            customerList.add(new Customer(i, "Customer " + i, "Lastname", "correochee@gmail.com", true, ad4));
        }
        for (int i = 21; i <= 25; i++) {
            customerList.add(new Customer(i, "Customer " + i, "Lastname", "mailmate@gmail.com", true, ad5));
        }

    }

    public static void main(String[] args) {
        launch();
    }
}