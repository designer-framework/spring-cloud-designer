package function.example;

import java.util.function.Function;

public class UpperCaseFunction implements Function<String, String> {

    @Override
    public String apply(String value) {
        System.out.println("Before Uppercasing: " + value);
        System.out.println("After  Uppercasing: " + value.toUpperCase());
        return value.toUpperCase();
    }

}
