import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // экземпляр класса
        PizzaRepository repository = new PizzaRepository();

        // закидываю пиццы в репозиторий
        repository.addPizza(new Pepperoni(8.99, 300, 30, 1200));
        repository.addPizza(new Pepperoni(9.49, 350, 32, 1300));
        repository.addPizza(new Pepperoni(7.99, 280, 28, 1150));

        repository.addPizza(new Cheese(7.49, 250, 28, 1000));
        repository.addPizza(new Cheese(8.49, 300, 30, 1100));
        repository.addPizza(new Cheese(9.99, 400, 32, 1400));

        repository.addPizza(new Meat(10.99, 400, 34, 1500));
        repository.addPizza(new Meat(11.49, 450, 36, 1600));
        repository.addPizza(new Meat(9.99, 350, 32, 1300));

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите минимальный вес пиццы для фильтрации: ");
        double minWeight = scanner.nextDouble();

        System.out.println("Пиццы с весом больше " + minWeight + ":");
        repository.getPizzasByWeight(minWeight).forEach(System.out::println);

        System.out.print("Введите минимальный диаметр пиццы для фильтрации: ");
        double minDiameter = scanner.nextDouble();

        System.out.println("Пиццы с диаметром больше " + minDiameter + ":");
        repository.getPizzasByDiameter(minDiameter).forEach(System.out::println);

        scanner.close();
    }
}

// класс репозиторий
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

    public void displayAllPizzas() {
        pizzas.forEach(System.out::println);
    }
}

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
