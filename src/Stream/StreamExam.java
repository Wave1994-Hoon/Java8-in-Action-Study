package Stream;

import java.util.*;
import static java.util.stream.Collectors.toList;


public class StreamExam {
    public static void main(String[] args) {
        System.out.println("Start");
        Test();

    }

    public static void Test() {
        List<Dish> menu = Arrays.asList(
            new Dish("pork1", false, 800, Dish.Type.MEAT),
            new Dish("pork2", true, 9000, Dish.Type.MEAT),
            new Dish("pork3", false, 100, Dish.Type.MEAT),
            new Dish("pork4", true, 200, Dish.Type.MEAT),
            new Dish("pork5", false, 300, Dish.Type.MEAT),
            new Dish("pork6", true, 400, Dish.Type.MEAT),
            new Dish("pork7", false, 500, Dish.Type.MEAT),
            new Dish("pork8", true, 600, Dish.Type.MEAT),
            new Dish("pork9", false, 700, Dish.Type.MEAT),
            new Dish("pork10",true, 800, Dish.Type.MEAT)
        );

        /* Collection Example */
        List<Dish> lowCaloriesDishes = new ArrayList<>();

        for(Dish dish: menu) {
            if (dish.getCalories() < 400) {
                lowCaloriesDishes.add(dish);
            }
        }

        Collections.sort(lowCaloriesDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d2.getCalories(), d2.getCalories());
            }
        });

        List<String> lowCaloriesName = new ArrayList<>();
        for(Dish dish: lowCaloriesDishes) {
            lowCaloriesName.add(dish.getName());
        }

        /* Stream Example */
        List<String> lowCaloricDishesName2 = menu
            .stream()
//            .parallelStream()  -> 멀티코어 아키텍쳐에서 병렬로 실행
            .filter(dish -> dish.getCalories() < 400)
            .sorted()
            .map(Dish::getName)
            .collect(toList());


        /* 중간 연산 Example */
        List<String> names = menu
            .stream()
            .filter(dish -> {
                System.out.println("filtering" + dish.getName());
                return dish.getCalories() > 300;
            })
            .map(dish -> {
                System.out.println("mapping" + dish.getName());
                return dish.getName();
            })
            .limit(3)
            .collect(toList());

        List<Dish> vegetarianDishes1 = new ArrayList<>();
        for(Dish dish: menu) {
            if (dish.isVegetarian()) {
                vegetarianDishes1.add(dish);
            }
        }

        List<Dish> vegetarianDishes2 = menu
            .stream()
            .filter(Dish::isVegetarian)
            .collect(toList());

        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words
            .stream()
            .map(String::length)
            .collect(toList());

        List<Integer> dishNameLengths = menu
            .stream()
            .map(Dish::getName)
            .map(String::length)
            .collect(toList());

        // TODO: FlatMap
    }

}
