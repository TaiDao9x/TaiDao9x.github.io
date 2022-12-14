package funtional_interface_builtin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("Hiên", "An", "Hằng", "Duy"));

        // Cách 1: Anonymous class

        Consumer<String> consumer = new Consumer<>() {

            @Override
            public void accept(String s) {
                System.out.println(s.toUpperCase());
            }
        };
//        names.forEach(consumer);
//
//        //Cách 2: lambda
//        names.forEach(s -> System.out.println(s.toLowerCase()));

        Consumer<String> consumer1 = name -> System.out.println(name.toLowerCase());

        Consumer<String> consumer2 = name -> System.out.print(name + "-");

        print(names, consumer);
        print(names, consumer1);
        print(names, consumer2);
    }

    public static void print(List<String> list, Consumer<String> consumer) {
        for (String ele : list) {
            consumer.accept(ele);
        }
    }
}
