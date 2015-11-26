package ca.ece.ubc.cpen221.mp5.query;

import java.util.HashSet;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public abstract class Sets {

    public static Set<Restaurant> intersection(Set<Restaurant> firstSet, Set<Restaurant> secondSet) {

        Set<Restaurant> intersection = new HashSet<>();

        for (Restaurant restaurant : firstSet) {
            if (secondSet.contains(restaurant)) {
                intersection.add(restaurant);
            }
        }

        return intersection;
    }

    public static Set<Restaurant> union(Set<Restaurant> firstSet, Set<Restaurant> secondSet) {

        Set<Restaurant> union = new HashSet<>();

        for (Restaurant restaurant : firstSet) {
            union.add(restaurant);
        }
        for (Restaurant restaurant : secondSet) {
            union.add(restaurant);
        }

        return union;
    }

    /*
     * public static Set<Restaurant> intersection(List<Set<Restaurant>> set) {
     * 
     * Set<Restaurant> intersection = new HashSet<>();
     * 
     * for ()
     * 
     * for ( Restaurant restaurant : firstList ) { if
     * (secondList.contains(restaurant)) { intersection.add(restaurant); } }
     * 
     * return intersection; }
     */
}
