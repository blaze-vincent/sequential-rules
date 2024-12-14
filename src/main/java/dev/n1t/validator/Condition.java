package dev.n1t.validator;

/**
 *
 * @param <T> Type of input data to make an assertion about
 */
public interface Condition<T> {
    boolean isMet(T input);

    static <T> Condition<T> allAreMet(Condition<T> ...conditions){
        return (T input) -> {
            for(Condition<T> condition : conditions)
                if (!condition.isMet(input))
                    return false;

            return true;
        };
    }

    static <T> Condition<T> anyAreMet(Condition<T> ...conditions){
        return (T input) -> {
            for (Condition<T> condition : conditions)
                if(condition.isMet(input))
                    return true;

            return false;
        };
    }
}
