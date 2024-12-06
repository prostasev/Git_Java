import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Главное окно с выбором лабораторной работы
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Выбор лабораторной работы");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(3, 1));

            // Кнопка для Лабораторной работы 1
            JButton lab1Button = new JButton("Лабораторная работа 1 (Пиццы)");
            lab1Button.addActionListener(e -> runLab1());  // Открытие Лабораторной работы 1

            // Кнопка для Лабораторной работы 3
            JButton lab3Button = new JButton("Лабораторная работа 3 (Сортировка массива)");
            lab3Button.addActionListener(e -> runLab3());  // Открытие Лабораторной работы 3

            frame.add(lab1Button);
            frame.add(lab3Button);
            frame.setVisible(true);
        });
    }

    // Лабораторная работа 1 - Пиццы
    // Лабораторная работа 1 - Пиццы
    public static void runLab1() {
        PizzaRepository repository = new PizzaRepository();

        // Генерация 40 пицц
        for (int i = 0; i < 40; i++) {
            double price = 5 + Math.random() * 10; // Цена от 5 до 15
            double weight = 200 + Math.random() * 400; // Вес от 200 до 600 грамм
            double diameter = 20 + Math.random() * 20; // Диаметр от 20 до 40 см
            double calories = 800 + Math.random() * 800; // Калорийность от 800 до 1600

            // Создаём случайный тип пиццы
            Pizza pizza;
            if (i % 3 == 0) {
                pizza = new Pepperoni(price, weight, diameter, calories);
            } else if (i % 3 == 1) {
                pizza = new Cheese(price, weight, diameter, calories);
            } else {
                pizza = new Meat(price, weight, diameter, calories);
            }

            repository.addPizza(pizza);
        }

        JFrame lab1Frame = new JFrame("Лабораторная работа 1 (Пиццы)");
        lab1Frame.setSize(400, 400);
        lab1Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        lab1Frame.setLayout(new BorderLayout());

        DefaultListModel<String> pizzaListModel = new DefaultListModel<>();
        JList<String> pizzaList = new JList<>(pizzaListModel);
        JScrollPane scrollPane = new JScrollPane(pizzaList);

        // Панель для фильтрации
        JPanel filterPanel = new JPanel();
        JTextField minWeightField = new JTextField(5);
        JTextField minDiameterField = new JTextField(5);
        JButton filterButton = new JButton("Фильтровать");

        filterPanel.add(new JLabel("Мин. вес:"));
        filterPanel.add(minWeightField);
        filterPanel.add(new JLabel("Мин. диаметр:"));
        filterPanel.add(minDiameterField);
        filterPanel.add(filterButton);

        filterButton.addActionListener(e -> {
            try {
                double minWeight = minWeightField.getText().isEmpty() ? 0 : Double.parseDouble(minWeightField.getText());
                double minDiameter = minDiameterField.getText().isEmpty() ? 0 : Double.parseDouble(minDiameterField.getText());

                pizzaListModel.clear();

                List<Pizza> filteredPizzas = repository.getPizzasByWeight(minWeight);

                if (minDiameter > 0) {
                    filteredPizzas = filteredPizzas.stream()
                            .filter(pizza -> pizza.getDiameter() > minDiameter)
                            .collect(Collectors.toList());
                }

                filteredPizzas.forEach(pizza -> pizzaListModel.addElement(pizza.toString()));

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(lab1Frame, "Введите корректные данные!");
            }
        });

        // Отображение всех пицц
        repository.getPizzasByWeight(0).forEach(pizza -> pizzaListModel.addElement(pizza.toString()));

        lab1Frame.add(scrollPane, BorderLayout.CENTER);
        lab1Frame.add(filterPanel, BorderLayout.SOUTH);
        lab1Frame.setVisible(true);
    }



    // Лабораторная работа 3 - Сортировка массива
    public static void runLab3() {
        JFrame lab3Frame = new JFrame("Лабораторная работа 3 (Сортировка массива)");
        lab3Frame.setSize(400, 400);
        lab3Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JTextField arrayInputField = new JTextField();
        panel.add(new JLabel("Введите элементы массива (через пробел):"));
        panel.add(arrayInputField);

        JButton sortButton = new JButton("Сортировать");
        panel.add(sortButton);

        sortButton.addActionListener(e -> {
            try {
                String[] inputArray = arrayInputField.getText().split(" ");
                int[] arr = Arrays.stream(inputArray)
                        .mapToInt(Integer::parseInt)
                        .toArray();
                BubbleSort.bubbleSort(arr);
                JOptionPane.showMessageDialog(lab3Frame, "Отсортированный массив: " + Arrays.toString(arr));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(lab3Frame, "Введите корректные элементы массива!");
            }
        });

        lab3Frame.add(panel);
        lab3Frame.setVisible(true);
    }
}

// Репозиторий для пицц
class PizzaRepository {
    private final List<Pizza> pizzas;

    public PizzaRepository() {
        this.pizzas = new ArrayList<>();
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    public List<Pizza> getPizzasByWeight(double minWeight) {
        return pizzas.stream()
                .filter(pizza -> pizza.getWeight() > minWeight)
                .collect(Collectors.toList());
    }

    public List<Pizza> getPizzasByDiameter(double minDiameter) {
        return pizzas.stream()
                .filter(pizza -> pizza.getDiameter() > minDiameter)
                .collect(Collectors.toList());
    }

    public List<Pizza> getAllPizzas() {
        return pizzas;
    }
}

// Классы для различных типов пицц
class Cheese extends Pizza {
    public Cheese(double price, double weight, double diameter, double calories) {
        super(price, weight, diameter, calories);
    }
}

class Meat extends Pizza {
    public Meat(double price, double weight, double diameter, double calories) {
        super(price, weight, diameter, calories);
    }
}

class Pepperoni extends Pizza {
    public Pepperoni(double price, double weight, double diameter, double calories) {
        super(price, weight, diameter, calories);
    }
}

// Абстрактный класс для пицц
abstract class Pizza {
    protected double price;
    protected double weight;
    protected double diameter;
    protected double calories;

    public Pizza(double price, double weight, double diameter, double calories) {
        this.price = price;
        this.weight = weight;
        this.diameter = diameter;
        this.calories = calories;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return String.format("Цена: %.2f, Вес: %.2f, Диаметр: %.2f, Калорийность: %.2f", price, weight, diameter, calories);
    }
}

// Класс для сортировки массива
class BubbleSort {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}
