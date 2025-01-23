import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ResourceRedistribution {
    private PriorityQueue<Warehouse> surplusWarehouses;
    private PriorityQueue<Warehouse> needyWarehouses;

    public ResourceRedistribution() {
        surplusWarehouses = new PriorityQueue<>(Comparator.comparingInt(Warehouse::getCapacity).reversed());
        needyWarehouses = new PriorityQueue<>(Comparator.comparingInt(Warehouse::getCapacity));
    }

    public void redistribute(ArrayList<Warehouse> warehouses, OutputToJSON output) {

        // Min heap and max heap of the most needy and in surplus warehouses
        for (Warehouse warehouse : warehouses) {
            if (warehouse.getCapacity() > 50) {
                surplusWarehouses.add(warehouse);
            } else if (warehouse.getCapacity() < 50) {
                needyWarehouses.add(warehouse);
            }
        }

        // resource redistribution
        while (!surplusWarehouses.isEmpty() && !needyWarehouses.isEmpty()) {
            Warehouse surplus = surplusWarehouses.poll();
            Warehouse needy = needyWarehouses.poll();

            int needAmount = 50 - needy.getCapacity();
            int surplusAmount = surplus.getCapacity() - 50;
            int transferAmount = Math.min(needAmount, surplusAmount);

            needy.setCapacity(needy.getCapacity() + transferAmount);
            surplus.setCapacity(surplus.getCapacity() - transferAmount);

            output.addToTask3(transferAmount, surplus.getName(), needy.getName());

            // Re-evaluate and re-add warehouses if they still qualify
            if (needy.getCapacity() < 50) {
                needyWarehouses.add(needy);
            }
            if (surplus.getCapacity() > 50) {
                surplusWarehouses.add(surplus);
            }
        }
    }

}
