import java.util.ArrayList;
import java.util.HashSet;

public class DynamicResourceSharing {


    public void createClusters(ArrayList<City> cities){

        Integer i = 1;
        for(City city: cities){
            city.setCluster(i);
            i = i + 1;
        }
    }

    public void mergeClusters(ArrayList<City> cities, OutputToJSON output){
        for(City city: cities){
            HashSet<Integer> usedWarehouses = city.getUsedWarehouses();
            for(City comparedCity: cities){
                if(usedWarehouses.equals(comparedCity.getUsedWarehouses())){

                    if(city.getId() != comparedCity.getId() && city.getCluster() != comparedCity.getCluster()){
                        output.mergeStep(city.getName(), comparedCity.getName(), city.getCluster());
                    }
                    comparedCity.setCluster(city.getCluster());

                }

            }
        }
    }
    public void queries(ArrayList<City> cities, OutputToJSON output){
        for (int i = 0; i < cities.size(); i++) {
            for (int j = i + 1; j < cities.size(); j++) {
                if(cities.get(i).getCluster() == cities.get(j).getCluster()){
                    output.doQueries(cities.get(i).getName(), cities.get(j).getName(), "Yes");
                }
                else {
                    output.doQueries(cities.get(i).getName(), cities.get(j).getName(), "No");
                }

            }
        }
    }
}
