package edu.pizza.especialidades;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaChocolateQueso extends Pizza {
    private String chocolate;

    public PizzaChocolateQueso(String name, String chocolate, Topping... toppings) {
        super(name);
        this.chocolate = chocolate;
        agregarIngredientesPorDefecto();
    }

    private void agregarIngredientesPorDefecto() {

        addTopping(new Topping("Chocolate", 2.0));
        addTopping(new Topping("Queso Crema", 1.5));
        addTopping(new Topping("Fresas", 1.0));
    }

    public String getChocolate() {
        return chocolate;
    }

    public void setChocolate(String chocolate) {
        this.chocolate = chocolate;
    }
}

