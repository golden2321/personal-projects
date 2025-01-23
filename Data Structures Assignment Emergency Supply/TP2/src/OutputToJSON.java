import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import graph.Graph;
import graph.Vertex;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class OutputToJSON {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonObject rootObject = new JsonObject();
    JsonObject task1and2 = new JsonObject();
    JsonObject graphRepresentation = new JsonObject();
    JsonArray costMatrix = new JsonArray();
    JsonArray resourceAllocations = new JsonArray();
    JsonObject remainingCapacities = new JsonObject();
    JsonObject task3 = new JsonObject();
    JsonObject ressourceRedistribution = new JsonObject();
    JsonArray transfers = new JsonArray();
    JsonObject finalResourceLevels =  new JsonObject();
    JsonObject task4 = new JsonObject();
    JsonObject dynamicRsoursceSharing = new JsonObject();
    JsonObject initialClusters = new JsonObject();
    JsonArray mergingSteps = new JsonArray();
    JsonObject membershipAfterMerging = new JsonObject();
    JsonArray queries = new JsonArray();


    public JsonObject getTask3() {
        return task3;
    }

    public void setTask3(JsonObject task3) {
        this.task3 = task3;
    }


    public void outlineOfFile(){
        graphRepresentation.add("Cost Matrix", costMatrix);
        task1and2.add("Graph Representation", graphRepresentation);
        task1and2.add("Resource Allocation", resourceAllocations);
        task1and2.add("Remaining Capacities", remainingCapacities);
        ressourceRedistribution.add("Transfers", transfers);
        task3.add("Resource Redistribution", ressourceRedistribution);
        task3.add("Final Resource Levels", finalResourceLevels);
        dynamicRsoursceSharing.add("Initial Clusters",initialClusters);
        dynamicRsoursceSharing.add("Merging Steps", mergingSteps);
        dynamicRsoursceSharing.add("Cluster Membership After Merging", membershipAfterMerging);
        dynamicRsoursceSharing.add("Queries", queries);
        task4.add("Dynamic Resource Sharing",dynamicRsoursceSharing);
        rootObject.add("Task 1 and 2", task1and2);
        rootObject.add("Task 3", task3);
        rootObject.add("Task 4",task4);

    }

    public void addToTask4(ArrayList<City> cities){
        for(City city : cities){
            initialClusters.addProperty("City " + city.getName(), "Cluster " + city.getCluster());
        }
    }
    public  void mergeStep(String city1, String city2, int cluster){
        JsonObject detailJson = new JsonObject();
        detailJson.addProperty("Action", "Merge");
        JsonArray arrayCity = new JsonArray();
        arrayCity.add("City " + city1);
        arrayCity.add("City " + city2);
        detailJson.add("Cities", arrayCity );
        detailJson.addProperty("Cluster after Merge", "Cluster " + cluster);
        mergingSteps.add(detailJson);
    }
    public void afterMerger(ArrayList<City> cities){
        for(City city : cities){
            membershipAfterMerging.addProperty("City " + city.getName(), "Cluster " + city.getCluster());
        }
    }
    public void doQueries(String city1, String city2, String yesOrNo){
        JsonObject detailJson = new JsonObject();
        detailJson.addProperty("Query", "Are City " + city1 + " and City " + city2 + " in the same cluster?");
        detailJson.addProperty("Result",yesOrNo);
        queries.add(detailJson);
    }

    public void addToTask3(int transferamount, String fromName, String toName){
        JsonObject detailJson = new JsonObject();
        detailJson.addProperty("From", "Warehouse " + fromName);
        detailJson.addProperty("To", (String) "Warehouse " + toName);
        detailJson.addProperty("Units", transferamount);
        transfers.add(detailJson);
    }

    public void addTask3(EmergencySupplyNetwork network){

        Map<Warehouse, Vertex<Warehouse>> warehouseVertexMap = network.getWarehouseVertexMap();

        // sort the warehouses
        List<Map.Entry<Warehouse, Vertex<Warehouse>>> sortedWarehouses = warehouseVertexMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(Warehouse::getId)))
                .collect(Collectors.toList());

        for (Map.Entry<Warehouse, Vertex<Warehouse>> entry : sortedWarehouses) {
            Warehouse warehouse = entry.getKey();
            int remainingCapacity = warehouse.getCapacity();
            finalResourceLevels.addProperty("Warehouse " + warehouse.getId(), remainingCapacity);
        }
    }


    public void addTask1and2(EmergencySupplyNetwork network, List<AllocationRecord> allocations){



        Graph graph = network.getGraph();
        Map<City, Vertex<City>> cityVertexMap = network.getCityVertexMap();
        Map<Warehouse, Vertex<Warehouse>> warehouseVertexMap = network.getWarehouseVertexMap();

        // sort the warehouses
        List<Map.Entry<Warehouse, Vertex<Warehouse>>> sortedWarehouses = warehouseVertexMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(Warehouse::getId)))
                .collect(Collectors.toList());

        // sort the cities
        List<Map.Entry<City, Vertex<City>>> sortedCities = cityVertexMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(City::getId)))
                .collect(Collectors.toList());

        for(Map.Entry<City, Vertex<City>> cityEntry : sortedCities){
            Vertex<City> vertexCity = cityEntry.getValue();
            JsonObject cityJson = new JsonObject();
            cityJson.addProperty("City", vertexCity.getElement().getName());
            for(Map.Entry<Warehouse, Vertex<Warehouse>> warehouseEntry : sortedWarehouses){
                String warehouseId = Integer.toString(warehouseEntry.getValue().getElement().getId());
                double cost = (double) graph.getEdge(cityEntry.getValue(), warehouseEntry.getValue()).getElement();
                cityJson.addProperty("Warehouse " + warehouseId, cost);
            }
            costMatrix.add(cityJson);
        }

        Map<String, JsonObject> cityAllocations = new HashMap<>();
        for (AllocationRecord allocation : allocations) {
            JsonObject allocationJson = cityAllocations.computeIfAbsent(allocation.getCity(), k -> {
                JsonObject obj = new JsonObject();
                obj.addProperty("City", allocation.getCity());
                obj.addProperty("Priority", allocation.getPriority());
                return obj;
            });

            if (allocation.getAllocated() instanceof Map) {
                Map<String, Object> details = (Map<String, Object>) allocation.getAllocated();
                JsonArray warehouseAllocations = allocationJson.getAsJsonArray("Allocated");
                if (warehouseAllocations == null) {
                    warehouseAllocations = new JsonArray();
                    allocationJson.add("Allocated", warehouseAllocations);
                }
                JsonObject detailJson = new JsonObject();
                detailJson.addProperty("Units", (Integer) details.get("Units"));
                detailJson.addProperty("Warehouse", (String) details.get("Warehouse"));
                warehouseAllocations.add(detailJson);
            } else {
                allocationJson.addProperty("Allocated", (Integer) allocation.getAllocated());
            }
        }

        // Add all city allocation entries to the main resource allocations array
        cityAllocations.values().forEach(resourceAllocations::add);

        for (Map.Entry<Warehouse, Vertex<Warehouse>> entry : sortedWarehouses) {
            Warehouse warehouse = entry.getKey();
            int remainingCapacity = warehouse.getCapacity();
            remainingCapacities.addProperty("Warehouse " + warehouse.getId(), remainingCapacity);
        }




    }

    public void WriteToFile(String outputFile){
        try (FileWriter writer = new FileWriter(outputFile)){
            gson.toJson(rootObject, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
