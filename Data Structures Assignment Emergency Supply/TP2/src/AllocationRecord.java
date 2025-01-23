public class AllocationRecord {

    private String city;
    private String priority;
    private Object allocated;

    public AllocationRecord(String city, String priority, Object allocated) {
        this.city = city;
        this.priority = priority;
        this.allocated = allocated;
    }

    public String getCity() {
        return city;
    }

    public String getPriority() {
        return priority;
    }

    public Object getAllocated() {
        return allocated;
    }
}
