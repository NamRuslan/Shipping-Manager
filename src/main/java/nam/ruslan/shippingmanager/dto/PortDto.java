package nam.ruslan.shippingmanager.dto;

public class PortDto {
    private int total;
    private int used;
    private int free;

    public PortDto(int total, int used, int free) {
        this.total = total;
        this.used = used;
        this.free = free;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }
}
