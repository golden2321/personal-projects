import java.util.*;

import graph.AdjacencyMapGraph;
import graph.Edge;
import graph.Vertex;
import ca.umontreal.IFT2015.pqueues.SortedPriorityQueue;

public class EmergencySupplyNetwork {

    Map<City, Vertex<City>> cityVertexMap = new HashMap<>();
    Map<Warehouse, Vertex<Warehouse>> warehouseVertexMap = new HashMap<>();

    AdjacencyMapGraph graph = new AdjacencyMapGraph<>(false);

    public Map<City, Vertex<City>> getCityVertexMap() {
        return cityVertexMap;
    }

    public AdjacencyMapGraph getGraph() {
        return graph;
    }

    public Map<Warehouse, Vertex<Warehouse>> getWarehouseVertexMap() {
        return warehouseVertexMap;
    }

    public double distCostCityWarehouse(City city, Warehouse warehouse){
        double distance = Math.sqrt(Math.pow((city.getX()-warehouse.getX()),2)
                        + Math.pow((city.getY()-warehouse.getY()),2));
        int coeff;
        if (distance <= 10){
            coeff = 1;
        }
        else if (distance > 10 && distance <= 20){
            coeff = 2;
        }
        else{
            coeff = 3;
        }

        double cost = distance * coeff;
        return cost;
    }

    public void createGraph(ArrayList<City> cities,ArrayList<Warehouse> warehouses){


        for(City city:cities){
            this.cityVertexMap.put(city, this.graph.insertVertex(city));
        }

        for(Warehouse warehouse : warehouses){
            this.warehouseVertexMap.put(warehouse, this.graph.insertVertex(warehouse));
        }

        double cost;
        for(City city : cities){
            for(Warehouse warehouse : warehouses){
                cost = distCostCityWarehouse(city, warehouse);
                this.graph.insertEdge(this.cityVertexMap.get(city),this.warehouseVertexMap.get(warehouse),cost);
            }
        }
    }


    public List<AllocationRecord> allocateResources(){

        SortedPriorityQueue<Integer, Vertex<City>> priorityQueue = new SortedPriorityQueue();
        List<AllocationRecord> allocations = new ArrayList<>(); // keep track of all allocations to put in json file

        // take cities from vertex hashmap and put them in priorityQueue
        for(Map.Entry<City, Vertex<City>> entry : cityVertexMap.entrySet()){
            Vertex<City> vertexCity = entry.getValue();

            String priority = vertexCity.getElement().getPriority();
            if (priority.equals("High")){
                priorityQueue.insert(1,vertexCity);
            }
            else if (priority.equals("Medium")){
                priorityQueue.insert(2,vertexCity);
            }
            else if (priority.equals("Low")){
                priorityQueue.insert(3,vertexCity);
            }
            else {
                throw new IllegalArgumentException("Invalid priority: " + priority);
            }
        }

        // allocation of resources according to priority and transport cost
        while(priorityQueue.isEmpty() == false){
            Vertex<City> cityVertex = priorityQueue.removeMin().getValue();
            Iterable<Edge<Double>> tansportCosts = graph.outgoingEdges(cityVertex);

            // queue for the cheapest transport cost. If one warehouse can't fill demand go to next one
            SortedPriorityQueue<Double, Edge<Double>> cheapestRoutes = new SortedPriorityQueue<>();
            for(Edge<Double> tansportCost : tansportCosts){
                cheapestRoutes.insert(tansportCost.getElement(), tansportCost);
            }

            // transfer ressources from warehouse to city
            Vertex<Warehouse> warehouseVertex;
            City city = cityVertex.getElement();
            while(cityVertex.getElement().getDemand() != 0){
                if(cheapestRoutes.isEmpty() == true){
                    // all warehouses empty can't fill demand of all cities
                    break;
                }
                warehouseVertex = graph.opposite(cityVertex, cheapestRoutes.removeMin().getValue());
                Warehouse warehouse = warehouseVertex.getElement();

                int allocatedAmount = 0;

                if (city.getDemand() <= warehouse.getCapacity()){
                    allocatedAmount = city.getDemand();
                    warehouse.setCapacity(warehouse.getCapacity() - city.getDemand());
                    city.setDemand(0);
                }
                else {
                    allocatedAmount = warehouse.getCapacity();
                    city.setDemand(city.getDemand() - warehouse.getCapacity());
                    warehouse.setCapacity(0);
                }

                if(allocatedAmount != 0){
                    // Create a new record for the allocation
                    Map<String, Object> allocationDetails = new HashMap<>();
                    allocationDetails.put("Units", allocatedAmount);
                    allocationDetails.put("Warehouse", "Warehouse " + warehouse.getId());

                    // Add to allocations list
                    allocations.add(new AllocationRecord(city.getName(), city.getPriority(), allocationDetails));

                    city.getUsedWarehouses().add(warehouse.getId());
                }


            }




        }


        return allocations;
    }

}
