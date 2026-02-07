package com.svalero.practicasfeb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataStore implements Serializable {
    private static final long serialVersionUID = 1L;

    public List<Car> cars = new ArrayList<>();
    public List<Customer> customers = new ArrayList<>();
    public List<Sale> sales = new ArrayList<>();
}
