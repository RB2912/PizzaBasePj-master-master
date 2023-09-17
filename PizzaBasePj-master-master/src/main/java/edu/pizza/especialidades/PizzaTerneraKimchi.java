package edu.pizza.especialidades;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaTerneraKimchi extends Pizza {
    private String ternera;

    public PizzaTerneraKimchi(String name, String ternera, Topping... toppings) {
        super(name);
        this.ternera = ternera;
        agregarIngredientesPorDefecto();
    }

    private void agregarIngredientesPorDefecto() {

        addTopping(new Topping("Ternera", 3.0));
        addTopping(new Topping("Kimchi", 2.0));
        addTopping(new Topping("Queso Mozzarella", 2.0));
        addTopping(new Topping("Pimientos", 1.0));
    }

    public String getTernera() {
        return ternera;
    }

    public void setTernera(String ternera) {
        this.ternera = ternera;
    }
}