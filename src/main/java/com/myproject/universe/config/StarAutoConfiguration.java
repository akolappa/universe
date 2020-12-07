package com.myproject.universe.config;


import com.myproject.universe.base.Graph;
import com.myproject.universe.finder.GraphFinder;
import com.myproject.universe.finder.impl.StarGraphFinder;
import com.myproject.universe.representation.Star;
import com.myproject.universe.utils.StarGraphCreater;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class StarAutoConfiguration {

    @Bean(name = "graphFinder")
    @Scope("prototype")
    public GraphFinder getGraphFinder(Graph<Star> graph){
        return new StarGraphFinder(graph);
    }

    @Bean(name = "graph")
    @Scope("singleton")
    public Graph<Star> getGraph(){
        return StarGraphCreater.getGraph();
    }
}
