package edu.pizza.especialidades;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaPulpoPatatas extends Pizza {
    private String pulpo;

    public PizzaPulpoPatatas(String name, String pulpo, Topping... toppings) {
        super(name);
        this.pulpo = pulpo;
        agregarIngredientesPorDefecto();
    }

    private void agregarIngredientesPorDefecto() {

        addTopping(new Topping("Pulpo", 3.0));
        addTopping(new Topping("Patatas", 2.0));
        addTopping(new Topping("Aceitunas Negras", 1.0));
        addTopping(new Topping("Queso Mozzarella", 2.0));
    }

    public String getPulpo() {
        return pulpo;
    }

    public void setPulpo(String pulpo) {
        this.pulpo = pulpo;
    }
}
