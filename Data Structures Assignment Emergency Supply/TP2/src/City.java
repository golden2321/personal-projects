import graph.Vertex;

import java.util.ArrayList;
import java.util.HashSet;

public class City implements Vertex {

    private String name;
    private int id;
    private int x;
    private int y;
    private int demand;
    private String priority;

    private HashSet<Integer> usedWarehouses = new HashSet<>();

    private Integer cluster;

    public City(){};
    public City(String name, int id, int x, int y, int demand, String priority) {
        this.name = name;
        this.id = id;
        this.x = x;
        this.y = y;
        this.demand = demand;
        this.priority = priority;
    }

    // Getters et Setters
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public HashSet<Integer> getUsedWarehouses() {
        return usedWarehouses;
    }

    public void setUsedWarehouses(HashSet<Integer> usedWarehouses) {
        this.usedWarehouses = usedWarehouses;
    }

    public Integer getCluster() {
        return cluster;
    }

    public void setCluster(Integer cluster) {
        this.cluster = cluster;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name=" + name +
                ", x=" + x +
                ", y=" + y +
                ", demand=" + demand +
                ", priority='" + priority + '\'' +
                '}';
    }
    public String getElement(){
        return name;
    }
}
