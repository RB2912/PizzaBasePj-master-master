package edu.pizza.especialidades;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaItaliana extends Pizza {
    private String salsa;

    public PizzaItaliana(String name, String salsa, Topping... toppings) {
        super(name);
        this.salsa = salsa;
        agregarIngredientesPorDefecto();
    }

    private void agregarIngredientesPorDefecto() {
        addTopping(new Topping("Tomate", 1.0));
        addTopping(new Topping("Queso Mozzarella", 2.0));
        addTopping(new Topping("Albahaca", 0.5));
        addTopping(new Topping("Aceitunas Negras", 1.0));
        addTopping(new Topping("Jamón", 1.5));
    }

    public String getSalsa() {
        return salsa;
    }

    public void setSalsa(String salsa) {
        this.salsa = salsa;
    }
}
