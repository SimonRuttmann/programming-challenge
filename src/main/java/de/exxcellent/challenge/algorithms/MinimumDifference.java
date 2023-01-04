package de.exxcellent.challenge.algorithms;

import de.exxcellent.challenge.toolbox.Pair;

import java.util.List;


public class MinimumDifference {

    /**
     * Calculates the minimum difference of paris in a list,
     * if there are multiple paris with the same minimal difference, the first is returned
     * @param values A list of pairs
     * @return The index of the pair
     */
    public int calcMinimumDifference(List<? extends Pair<? extends Number, ? extends Number>> values){

        if(values.isEmpty()){
            throw new IllegalArgumentException("Values are empty");
        }

        var minimum = Double.MAX_VALUE;
        var minimumIndex = 0;

        for(int i = 0; i < values.size(); i++){
            var valuePair = values.get(i);
            var diff = Math.abs(valuePair.getFirstValue().doubleValue() - valuePair.getSecondValue().doubleValue());

            if(minimum > diff){
                minimum = diff;
                minimumIndex = i;
            }
        }

        return minimumIndex;
    }
}
