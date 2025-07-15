package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;
import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        Class<?> clas = address.getClass();
        for (Method method : clas.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inspect.class)) {
                System.out.printf(
                        "Method %s returns a value of type %s%n",
                        method.getName(),
                        method.getReturnType().getSimpleName()
                );
            }
        }
        // END
    }
}
