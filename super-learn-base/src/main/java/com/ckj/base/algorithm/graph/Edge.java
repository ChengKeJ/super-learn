package com.ckj.base.algorithm.graph;

/**
 * @author c.kj
 * @Description
 * @Date 2021/8/10
 * @Time 10:53 AM
 **/
public class Edge {


    /**
     * beginVertex是边的起始顶点<br>
     * 普通情况是不用显示地存储beginVertex，但是生成最小生成树时需要
     */
    private Vertex beginVertex;

    /**
     * 由于Edge是存储在Vertex中的，所以包含这个边的vertex是开始点
     * endVertex是结束点
     */
    private Vertex endVertex;

    /**
     * 边的权值
     */
    private double weight;

    /**创建边
     * @param beginVertex 边的开始点
     * @param endVertex 边的结束点
     * @param weight  边的权值
     */
    public Edge(Vertex beginVertex,Vertex endVertex, double weight) {
        this.beginVertex = beginVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }


    /**返回边的开始点
     * @return
     */
    public Vertex getBeginVertex() {
        return beginVertex;
    }

    /** 返回边的结束点
     * @return
     */
    public Vertex getEndVertex() {
        return endVertex;
    }

    /**返回边的权值
     * @return
     */
    public double getWeight() {
        return weight;
    }

    /**设置边的权值
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }




}
