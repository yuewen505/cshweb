package csh.entity;

/**
 * @author ดิหฌ
 * @create 2018-10-24 18:41
 **/
public class Order {
    private int id;
    private String name;
    private int status;
    private String updatetime;

    public Order(int id, String name, int status, String updatetime) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.updatetime = updatetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
