package com.ckj.base.algorithm.graph;

import lombok.Data;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author c.kj
 * @Description
 * @Date 2021/8/10
 * @Time 10:52 AM
 **/
@Data
public class Vertex<T>{

    /**
     * 能够标识这个定点的属性，可以用不同类型来标识顶点如String,Integer....
     */
    private T label;

    /**
     * 这个定点对应的边<br>
     * 如果为有向图，则代表以这个定点为起点的边
     */
    private List<Edge> edgeList;

    /**
     * 表示这个顶点是否已被访问，在bfs和dfs中会被使用到
     */
    private boolean visited;

    /**
     * 该顶点的前驱节点<br>
     * 在求图中某两个顶点之间的最短路径时，在从起始顶点遍历过程中，需要记录下遍历到某个顶点时的前驱顶点
     */
    private Vertex previousVertex;

    /**
     * 这个定点的权值（注意不是边的权值）
     */
    private double cost;

    /**创建顶点
     * @param label  这个顶点的标识
     * @param cost  这个顶点的权值
     */
    public Vertex(T label,double cost){
        this.label=label;
        //用链表存储边
        edgeList=new LinkedList<>();
        visited=false;
        previousVertex=null;
        this.cost=cost;
    }

    //下面与顶点的标识相关

    /**返回顶点的标识
     * @return
     */
    public T getLabel() {
        return label;
    }

    /**
     * 根据顶点的标识确定是否是同一个顶点
     */
    @Override
    public boolean equals(Object otherVertex) {
        boolean result;
        //如果otherVertex为空或者类不同，直接返回false
        if(otherVertex==null||getClass()!=otherVertex.getClass()){
            return false;
        }
        Vertex other=(Vertex)otherVertex;
        //根据label确定是否是同一个顶点
        result=label.equals(other.getLabel());
        return result;
    }

    //下面与顶点的边相关

    /** 返回边的迭代器
     * @return
     */
    public Iterator<Edge> getEdgeIterator(){
        return edgeList.iterator();
    }

    /**返回是否有以这个顶点为出发点的边数
     * @return
     */
    public int getEdgeCount(){
        return edgeList.size();
    }

    /**将这个顶点与endVertex连接，边的权值为weight
     * @param endVertex
     * @param weight
     * @return 如果顶点已经与endVertex连接，那么将会更新权值，返回false<br>
     * 如果顶点没有与endVertex相连，则互相连接，返回true
     */
    public boolean connect(Vertex endVertex,double weight){
        Iterator<Edge> iterator=getEdgeIterator();
        Edge edge=null;
        Vertex vertex=null;
        while(iterator.hasNext()){
            edge=iterator.next();
            vertex=edge.getEndVertex();
            if(vertex.equals(endVertex)){
                //如果顶点已经与endVertex连接，那么将会更新权值，返回false
                edge.setWeight(weight);
                return false;
            }
        }
        //如果顶点没有与endVertex相连，则互相连接，返回true
        edge=new Edge(this,endVertex, weight);
        edgeList.add(edge);
        return true;
    }

    /**将这个顶点与endVertex连接的边删除
     * @param endVertex
     * @return  如果顶点已经与endVertex连接，那么将会删除这条边，返回true<br>
     * 如果顶点没有与endVertex连接，则啥都不做，返回false
     */
    public boolean disconnect(Vertex endVertex){
        Iterator<Edge> iterator=getEdgeIterator();
        Edge edge=null;
        Vertex vertex=null;
        while(iterator.hasNext()){
            edge=iterator.next();
            vertex=edge.getEndVertex();
            if(vertex.equals(endVertex)){
                //如果顶点已经与endVertex连接，那么将会删除这条边，返回true
                //edgeList.remove(edge);
                iterator.remove();
                return true;
            }
        }
        //如果顶点没有与endVertex连接，则啥都不做，返回false
        return false;
    }

    /**返回是否有以这个顶点为出发点,以endVertex为结束点的边
     * @return 如果有，返回那条边<br>
     * 如果没有，返回null
     */
    public Edge hasNeighbourVertex(Vertex endVertex){
        Iterator<Edge> iterator=getEdgeIterator();
        Edge edge=null;
        Vertex vertex=null;
        while(iterator.hasNext()){
            edge=iterator.next();
            vertex=edge.getEndVertex();
            if(vertex.equals(endVertex)){
                //如果顶点已经与endVertex连接，那么将返回这个边
                return edge;
            }
        }
        //没有则返回null
        return null;
    }



    //下面是与顶点是否被访问相关

    /**返回顶点是否被访问
     * @return
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * 访问这个顶点
     */
    public void visit(){
        visited=true;
    }

    /**
     * 不访问这个顶点，或者说是清除访问状态
     */
    public void unVisit(){
        visited=false;
    }

    /**获得以这个顶点为出发点，相邻的第一个没有被访问的顶点
     * @return 如果没有，返回null<br>
     * 如果有，返回对应的顶点
     */
    public Vertex getUnvisitedVertex(){
        Iterator<Edge> iterator=getEdgeIterator();
        Edge edge=null;
        Vertex vertex=null;
        while(iterator.hasNext()){
            edge=iterator.next();
            vertex=edge.getEndVertex();
            if(vertex.isVisited()==false){
                return vertex;
            }
        }
        //没有则返回null
        return null;
    }

    //下面与前驱节点相关

    /**返回顶点的前驱节点
     * @return
     */
    public Vertex getPreviousVertex() {
        return previousVertex;
    }

    /**设置顶点的前驱节点
     * @param previousVertex
     */
    public void setPreviousVertex(Vertex previousVertex) {
        this.previousVertex = previousVertex;
    }

    //下面与顶点的权值相关

    /**返回顶点的权值
     * @return
     */
    public double getCost() {
        return cost;
    }

    /** 设置顶点的权值
     * @param cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }


}
