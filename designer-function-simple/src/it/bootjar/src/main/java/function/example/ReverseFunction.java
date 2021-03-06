package function.example;

import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class ReverseFunction implements Function<String, String> {

    @Override
    public String apply(String value) {
        System.out.println("Reversing " + value);
        return new StringBuilder(value).reverse().toString();
    }

}
