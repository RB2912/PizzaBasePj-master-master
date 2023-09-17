package edu.pizza.especialidades;
import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaSalmonQuesoCabra extends Pizza {
    private String salmon;

    public PizzaSalmonQuesoCabra(String name, String salmon, Topping... toppings) {
        super(name);
        this.salmon = salmon;
        agregarIngredientesPorDefecto();
    }

    private void agregarIngredientesPorDefecto() {
        // Agrega los ingredientes por defecto de la pizza de Salmón y Queso de Cabra
        // Puedes personalizar esta lista según tus preferencias
        addTopping(new Topping("Salmón", 3.0));
        addTopping(new Topping("Queso de Cabra", 2.0));
        addTopping(new Topping("Eneldo", 1.0));
        addTopping(new Topping("Cebolla Roja", 1.0));
    }

    public String getSalmon() {
        return salmon;
    }

    public void setSalmon(String salmon) {
        this.salmon = salmon;
    }
}
