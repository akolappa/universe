package com.myproject.universe.utils;

import com.myproject.universe.base.Graph;
import com.myproject.universe.builder.GraphBuilder;
import com.myproject.universe.representation.Star;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class StarGraphCreater {

    public static Graph<Star> getGraph(){
        GraphBuilder graphBuilder = new GraphBuilder();

        Star starA = new Star("A","Solar System");
        Star starB = new Star("B","Alpha Century");
        Star starC = new Star("C","Sirius");
        Star starD = new Star("D","Betelgeuse");
        Star starE = new Star("E","Vega");

        Graph<Star> graph = graphBuilder.from(starA).to(starB,5).to(starD,5).to(starE,7)
                .from(starB).to(starC,4)
                .from(starC).to(starD,8).to(starE,2)
                .from(starD).to(starC,8).to(starE,6)
                .from(starE).to(starB,3)
                .build();

        return graph;
    }
}
