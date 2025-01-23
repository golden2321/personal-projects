import graph.Vertex;

public class Warehouse implements Vertex {
    private String name;
    private int id;
    private int x;
    private int y;
    private int capacity;

    public Warehouse(){};
    public Warehouse(String name, int id, int x, int y, int capacity) {
        this.name = name;
        this.id = id;
        this.x = x;
        this.y = y;
        this.capacity = capacity;
    }

    // Getters and Setters
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", name=" + name +
                ", x=" + x +
                ", y=" + y +
                ", capacity=" + capacity +
                '}';
    }
    public String getElement(){
        return name;
    }
}
