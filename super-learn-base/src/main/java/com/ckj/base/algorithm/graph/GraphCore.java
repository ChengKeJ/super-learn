package com.ckj.base.algorithm.graph;


/**
 * @author c.kj
 * @Description 图数据结构
 * @Date 2021/8/10
 * @Time 10:45 AM
 **/
public class GraphCore {



    public static void main(String[] args) {

        Graph<String> graph=new Graph<>(true);
        graph.addVertex("A", 0);
        graph.addVertex("B", 0);
        graph.addVertex("C", 1);
        graph.addVertex("D", 0);
        graph.addEdge("A", "B", 3);
        graph.addEdge("A", "C", 4);
        graph.addEdge("C", "B", 5);
        graph.addEdge("C", "D", 7);
        graph.addEdge("B", "D", 6);

        graph.printGraph();




    }

}
