/* Author Riste Popov id: 20241764

   Data Structures Assignment: Emergency Supply Network Design

   This app creates a graph with cities and warehouses and decides which cities pull resources from which warehouse,
   according to cost, priority and demand. NetworkApp.java is where the flow of the program happens and it calls other
   classes to accomplish its function. EmergencySupplyNetwork creates the graph and allocates resources to cities,
   ResourceRedistribution redistributes resources among warehouses and DynamicResourceSharing creates clusters of cities
   using the same warehouses.

   The class code is used for different types of data structures.
*/

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NetworkApp {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java NetworkApp <inputfile>");
            System.exit(1);
        }

        String inputFile = args[0];
        String outputFile = args[1].toString();
        ArrayList<String> lines = new ArrayList<>();
        try {
            File file = new File(inputFile);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }

            scanner.close();
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }

        //create lists for cities and warehouses
        ArrayList<City> cities = new ArrayList<>();
        ArrayList<Warehouse> warehouses = new ArrayList<>();
        for (String line : lines) {

            Pattern pattern = Pattern.compile("City\\s+(.*):");
            Matcher matcher = pattern.matcher(line);


            if (matcher.find()) {
                City city = new City();
                city.setName(matcher.group(1));

                pattern = Pattern.compile("ID\\s*=\\s*(\\d+)");
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    city.setId(Integer.parseInt(matcher.group(1)));
                }

                pattern = Pattern.compile("Coordinates\\s*=\\s*\\((\\d+),\\s*(\\d+)\\)");
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    city.setX(Integer.parseInt(matcher.group(1)));
                    city.setY(Integer.parseInt(matcher.group(2)));
                }

                pattern = Pattern.compile("Demand\\s*=\\s*(\\d+)\\s*units");
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    city.setDemand(Integer.parseInt(matcher.group(1)));
                }

                pattern = Pattern.compile("Priority\\s*=\\s*([A-Za-z]+)");
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    city.setPriority(matcher.group(1));
                }
                cities.add(city);
                continue;
            }


            pattern = Pattern.compile("Warehouse\\s+(.*):");
            matcher = pattern.matcher(line);

            if (matcher.find()) {
                Warehouse warehouse = new Warehouse();
                warehouse.setName(matcher.group(1));

                pattern = Pattern.compile("ID\\s*=\\s*(\\d+)");
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    warehouse.setId(Integer.parseInt(matcher.group(1)));
                }

                pattern = Pattern.compile("Coordinates\\s*=\\s*\\((\\d+),\\s*(\\d+)\\)");
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    warehouse.setX(Integer.parseInt(matcher.group(1)));
                    warehouse.setY(Integer.parseInt(matcher.group(2)));
                }

                pattern = Pattern.compile("Capacity\\s*=\\s*(\\d+)\\s*units");
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    warehouse.setCapacity(Integer.parseInt(matcher.group(1)));
                }

                warehouses.add(warehouse);
            }


        }

        // build graph and allocate ressources
        EmergencySupplyNetwork network = new EmergencySupplyNetwork();
        network.createGraph(cities, warehouses);
        List<AllocationRecord> record = network.allocateResources();

        // update json file for task 1 and 2
        OutputToJSON output = new OutputToJSON();
        output.outlineOfFile();
        output.addTask1and2(network, record);

        // redistribute resources among warehouses
        ResourceRedistribution redistribution = new ResourceRedistribution();
        redistribution.redistribute(warehouses, output);
        output.addTask3(network);

        // initial clusters
        DynamicResourceSharing sharing = new DynamicResourceSharing();
        sharing.createClusters(cities);
        output.addToTask4(cities);

        // merge clusters and queries
        sharing.mergeClusters(cities, output);
        output.afterMerger(cities);
        sharing.queries(cities, output);

        output.WriteToFile(outputFile);
    }
}