package com.myproject.universe.finder;

import com.myproject.universe.representation.Star;

import java.util.List;

public interface GraphFinder {

    /**
     *
     * @param stars
     * @return Integer
     *
     * This method takes the list of stars and returns the distance of the star path and if there is no path
     * then returns zero.
     */

    Integer findDistanceOfPath(Star... stars);

    /**
     *
     * @param source
     * @param target
     * @param stops
     * @return List of possible paths
     *
     * This method finds the paths between 2 stars with maximum of specified stops.
     */

    List<List<String>> findPathsWithStopsLessthan(Star source, Star target, Integer stops);

    /**
     *
     * @param source
     * @param target
     * @param stops
     * @return List of possible paths
     *
     * This method finds the paths between 2 stars with exactly number of stops between them
     */
    List<List<String>> findPathsWithExactStopsInbetween(Star source, Star target, Integer stops);

    /**
     *
     * @param source
     * @param target
     * @param travelTime
     * @return List of possible paths
     *
     * This method finds the paths between 2 stars with travel time less than specified
     */
    List<List<String>> findPathsWithTravelTime(Star source, Star target, Integer travelTime);

    /**
     *
     * @param source
     * @param target
     * @return the minimum hours of travel
     *
     * This method finds the minimum hours to travel between 2 stars.
     */
    Integer findShortestPathDuration(Star source, Star target);


}
