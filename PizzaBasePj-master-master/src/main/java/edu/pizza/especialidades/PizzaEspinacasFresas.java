package edu.pizza.especialidades;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaEspinacasFresas extends Pizza {
    private String espinacas;

    public PizzaEspinacasFresas(String name, String espinacas, Topping... toppings) {
        super(name);
        this.espinacas = espinacas;
        agregarIngredientesPorDefecto();
    }

    private void agregarIngredientesPorDefecto() {

        addTopping(new Topping("Espinacas", 1.5));
        addTopping(new Topping("Fresas", 1.0));
        addTopping(new Topping("Queso Mozzarella", 2.0));
        addTopping(new Topping("Cebolla", 1.0));
    }

    public String getEspinacas() {
        return espinacas;
    }

    public void setEspinacas(String espinacas) {
        this.espinacas = espinacas;
    }
}

