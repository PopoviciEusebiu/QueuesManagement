package Model;

public class Task implements Comparable<Task> {
    int ID;
    int arrivalTime;
    int serviceTime;

    public Task(int ID, int arrivalTime, int serviceTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getID() {
        return ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public int compareTo(Task o) {
        return this.arrivalTime-o.getArrivalTime();
    }

    public String toString()
    {
        return "(" + getID() +" , " + getArrivalTime() + " , " + getServiceTime() + ")";
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }
}
