package edu.formularios;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;
import edu.pizza.especialidades.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class frmPizza {
    private JPanel jpanelPrincipal;
    private JComboBox<Topping> comboBoxToppings;
    private JComboBox<Pizza> comboBoxTipoPizza;
    private JTextField txtPizza;
    private JButton btnAddIngrediente;
    private JLabel lblTotal;
    private JList<String> lista1;
    private JButton btnPreparar;
    private JRadioButton pequeñaRadioButton;
    private JRadioButton medianaRadioButton;
    private JRadioButton grandeRadioButton;
    private JList<String> listpreparacion; // JList para el estado de preparación

    private DefaultListModel<String> modeLista = new DefaultListModel<>();
    private DefaultListModel<String> modeloListaPreparacion = new DefaultListModel<>(); // Modelo para el JList de preparación
    private double total = 0;
    private List<Topping> ingredientes = new ArrayList<>();

    private List<Pizza> pizzasDisponibles = new ArrayList<>(); // Lista de pizzas disponibles

    // Declarar una variable de instancia para el factor de ajuste de precio
    private double factorPrecio = 1.0; // Por defecto, sin cambio de precio

    public JPanel getJpanelPrincipal() {
        return jpanelPrincipal;
    }

    public frmPizza() {
        // Cambiar el color de fondo del formulario a un tono claro
        jpanelPrincipal.setBackground(new Color(220, 183, 156)); // Color gris claro

        // Cambiar el color de fondo de los botones
        btnAddIngrediente.setBackground(new Color(173, 216, 230)); // Color celeste claro
        btnPreparar.setBackground(new Color(173, 216, 230)); // Color celeste claro

        // Cambiar el color de texto de los botones
        btnAddIngrediente.setForeground(Color.BLACK); // Texto negro
        btnPreparar.setForeground(Color.BLACK); // Texto negro

        // Cambiar el color de fondo de las etiquetas
        lblTotal.setBackground(new Color(240, 240, 240)); // Color gris claro
        lblTotal.setOpaque(true); // Hacer que la etiqueta sea opaca para mostrar el color de fondo

        // Cambiar el color de texto de las etiquetas
        lblTotal.setForeground(Color.BLACK); // Texto negro

        // Cambiar el color de fondo del JList de preparación
        listpreparacion.setBackground(new Color(240, 240, 240)); // Color gris claro
        listpreparacion.setOpaque(true); // Hacer que el JList sea opaco para mostrar el color de fondo

        // Cambiar el color de texto del JList de preparación
        listpreparacion.setForeground(Color.BLACK); // Texto negro


        cargarToppings();
        cargarPizzasDisponibles(); // Cargar las pizzas disponibles

        // Cargar las pizzas disponibles en el comboBoxTipoPizza
        DefaultComboBoxModel<Pizza> pizzaModel = new DefaultComboBoxModel<>(pizzasDisponibles.toArray(new Pizza[0]));
        comboBoxTipoPizza.setModel(pizzaModel);

        // Agregar un ActionListener al comboBoxTipoPizza para actualizar la información al seleccionar una pizza
        comboBoxTipoPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarInformacionPizzaSeleccionada();
            }
        });

        btnAddIngrediente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Topping ingrediente = (Topping) comboBoxToppings.getSelectedItem();
                modeLista.addElement(ingrediente.toString());
                lista1.setModel(modeLista);
                total += ingrediente.getPrecio();
                lblTotal.setText(String.valueOf(total));
                ingredientes.add(ingrediente); // Agregar el ingrediente a la lista de ingredientes
            }
        });

        // Agrega un MouseListener a la lista1 para detectar doble clic en un elemento
        lista1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Verifica si se hizo doble clic
                    int selectedIndex = lista1.getSelectedIndex();
                    if (selectedIndex != -1) {
                        String selectedIngredient = modeLista.getElementAt(selectedIndex);

                        // Encuentra el ingrediente seleccionado en la lista de ingredientes
                        for (Topping topping : ingredientes) {
                            if (topping.toString().equals(selectedIngredient)) {
                                total -= topping.getPrecio(); // Resta el precio del ingrediente eliminado
                                ingredientes.remove(topping);
                                break;
                            }
                        }

                        modeLista.remove(selectedIndex); // Elimina el elemento del modelo de lista
                        lblTotal.setText(String.valueOf(total)); // Actualiza el precio total
                    }
                }
            }
        });

        // Agregar un ActionListener al radio button pequeñaRadioButton
        pequeñaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                factorPrecio = 0.9; // Por ejemplo, reducir el precio en un 10% para tamaño pequeño
                actualizarPrecioTotal();
            }
        });

        // Agregar un ActionListener al radio button medianaRadioButton
        medianaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                factorPrecio = 1.0; // El precio no cambia para tamaño mediano
                actualizarPrecioTotal();
            }
        });

        // Agregar un ActionListener al radio button grandeRadioButton
        grandeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                factorPrecio = 1.2; // Por ejemplo, aumentar el precio en un 20% para tamaño grande
                actualizarPrecioTotal();
            }
        });

        // Configurar el modelo de lista para el JList listpreparacion
        listpreparacion.setModel(modeloListaPreparacion);

        btnPreparar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombrePizza = txtPizza.getText();
                if (nombrePizza.isEmpty()) {
                    JOptionPane.showMessageDialog(jpanelPrincipal, "La pizza debe tener un nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // No continuar si no se cumple la validación
                }

                if (ingredientes.isEmpty()) {
                    JOptionPane.showMessageDialog(jpanelPrincipal, "La pizza debe tener al menos un ingrediente.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // No continuar si no se cumple la validación
                }

                if (!pequeñaRadioButton.isSelected() && !medianaRadioButton.isSelected() && !grandeRadioButton.isSelected()) {
                    JOptionPane.showMessageDialog(jpanelPrincipal, "Debes seleccionar un tamaño para la pizza.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // No continuar si no se cumple la validación
                }

                Pizza pizza = new Pizza(nombrePizza);

                for (int i = 0; i < modeLista.getSize(); i++) {
                    String toppingNombre = modeLista.getElementAt(i);

                    for (Topping topping : ingredientes) {
                        if (topping.toString().equals(toppingNombre)) {
                            pizza.addTopping(topping);
                            break;
                        }
                    }
                }

                // Agregar el estado de preparación de la pizza al JList listpreparacion
                modeloListaPreparacion.addElement("Agregando ingredientes...");

                // Agregar los nombres de los ingredientes
                for (int i = 0; i < modeLista.getSize(); i++) {
                    modeloListaPreparacion.addElement(" - " + modeLista.getElementAt(i));
                }

                modeloListaPreparacion.addElement("Horneando...");
                modeloListaPreparacion.addElement("Empaquetando...");
                modeloListaPreparacion.addElement("¡La pizza está lista!");

                // Mostrar el mensaje de que la pizza está lista en el JLabel
                listpreparacion.setName("La pizza está lista para ser servida.");
            }
        });
    }

    private void cargarToppings() {
        ingredientes.add(new Topping("", 0.0)); // Ingrediente vacío
        ingredientes.add(new Topping("champiñones", 4.55));
        ingredientes.add(new Topping("Tomates", 2.55));
        ingredientes.add(new Topping("Cebolla", 6.55));
        ingredientes.add(new Topping("Salchicha", 10.55));
        ingredientes.add(new Topping("Calamares", 11.55));
        ingredientes.add(new Topping("Chucho", 14.55));

        DefaultComboBoxModel<Topping> model = new DefaultComboBoxModel<>(ingredientes.toArray(new Topping[0]));
        comboBoxToppings.setModel(model);
    }

    private void cargarPizzasDisponibles() {
        // Agrega las pizzas disponibles a la lista
        pizzasDisponibles.add(new PizzaItaliana("Pizza Italiana", "Salsa de tomate", new Topping("Tomate", 1.0), new Topping("Queso Mozzarella", 2.0), new Topping("Albahaca", 0.5), new Topping("Aceitunas Negras", 1.0), new Topping("Jamón", 1.5)));
        pizzasDisponibles.add(new PizzaYoLaArmo("Pizza Yo La Armo", 0));
        pizzasDisponibles.add(new PizzaTerneraKimchi("Pizza Ternera Kimchi", "", new Topping("Ternera", 3.0), new Topping("Kimchi", 2.0), new Topping("Queso Mozzarella", 2.0), new Topping("Pimientos", 1.0)));
        pizzasDisponibles.add(new PizzaSalmonQuesoCabra("Pizza Salmón Queso Cabra", "", new Topping("Salmón", 3.0), new Topping("Queso de Cabra", 2.0), new Topping("Eneldo", 1.0), new Topping("Cebolla Roja", 1.0)));
        pizzasDisponibles.add(new PizzaPulpoPatatas("Pizza Pulpo Patatas", "", new Topping("Pulpo", 3.0), new Topping("Patatas", 2.0), new Topping("Aceitunas Negras", 1.0), new Topping("Queso Mozzarella", 2.0)));
        pizzasDisponibles.add(new PizzaEspinacasFresas("Pizza Espinacas Fresas", "", new Topping("Espinacas", 1.5), new Topping("Fresas", 1.0), new Topping("Queso Mozzarella", 2.0), new Topping("Cebolla", 1.0)));
        pizzasDisponibles.add(new PizzaChocolateQueso("Pizza Chocolate Queso", "", new Topping("Chocolate", 2.0), new Topping("Queso Crema", 1.5), new Topping("Fresas", 1.0)));

        DefaultComboBoxModel<Pizza> model = new DefaultComboBoxModel<>(pizzasDisponibles.toArray(new Pizza[0]));
        comboBoxTipoPizza.setModel(model);
    }

    private void actualizarInformacionPizzaSeleccionada() {
        // Obtén la pizza seleccionada en el comboBoxTipoPizza
        Pizza pizzaSeleccionada = (Pizza) comboBoxTipoPizza.getSelectedItem();

        // Actualiza la información de precio e ingredientes en la GUI
        txtPizza.setText(pizzaSeleccionada.getName());
        modeLista.clear();
        actualizarPrecioTotal();

        for (Topping topping : pizzaSeleccionada.getToppings()) {
            modeLista.addElement(topping.toString());
            total += topping.getPrecio();
        }

        lblTotal.setText(String.valueOf(total));
    }

    // Método para actualizar el precio total teniendo en cuenta el factor de ajuste
    private void actualizarPrecioTotal() {
        total *= factorPrecio;
        lblTotal.setText(String.valueOf(total));
    }


}
