package de.exxcellent.challenge.algorithms;

public class Pair<T,N> {

    private final T firstValue;

    private final N secondValue;

    public Pair(T firstValue, N secondValue){
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public T getFirstValue(){
        return firstValue;
    }

    public N getSecondValue(){
        return secondValue;
    }
}
