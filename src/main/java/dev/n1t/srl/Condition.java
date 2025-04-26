package dev.n1t.srl;

/**
 * Condition to be evaluated against an input to determine whether an action should be executed in a rule
 * @param <T> Type of input data to make an assertion about
 */
public interface Condition<T> {
    boolean isMet(T input);

    @SafeVarargs
    static <T> Condition<T> allAreMet(Condition<T> ...conditions){
        return (T input) -> {
            for(Condition<T> condition : conditions)
                if (!condition.isMet(input))
                    return false;

            return true;
        };
    }

    @SafeVarargs
    static <T> Condition<T> anyAreMet(Condition<T> ...conditions){
        return (T input) -> {
            for (Condition<T> condition : conditions)
                if(condition.isMet(input))
                    return true;

            return false;
        };
    }
}
