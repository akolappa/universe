package com.myproject.universe.finder;

import com.myproject.universe.representation.Star;

import java.util.List;

public interface GraphFinder {

    /**
     *
     * @param stars List of Stars path
     * @return Integer The distance of the path
     *
     * This method takes the list of stars and returns the distance of the star path and if there is no path
     * then returns zero.
     */

    Integer findDistanceOfPath(Star... stars);

    /**
     *
     * @param source starting star
     * @param target ending star
     * @param stops number of stops
     * @return List of possible paths
     *
     * This method finds the paths between 2 stars with maximum of specified stops.
     */

    List<List<String>> findPathsWithStopsLessthan(Star source, Star target, Integer stops);

    /**
     *
     * @param source starting star
     * @param target ending star
     * @param stops number of stops
     * @return List of possible paths
     *
     * This method finds the paths between 2 stars with exactly number of stops between them
     */
    List<List<String>> findPathsWithExactStopsInbetween(Star source, Star target, Integer stops);

    /**
     *
     * @param source starting star
     * @param target ending star
     * @param travelTime travel time in hours
     * @return List of possible paths
     *
     * This method finds the paths between 2 stars with travel time less than specified
     */
    List<List<String>> findPathsWithTravelTime(Star source, Star target, Integer travelTime);

    /**
     *
     * @param source starting star
     * @param target ending star
     * @return the minimum hours of travel
     *
     * This method finds the minimum hours to travel between 2 stars.
     */
    Integer findShortestPathDuration(Star source, Star target);


}
