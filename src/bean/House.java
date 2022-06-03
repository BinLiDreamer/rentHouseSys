package bean;

import java.io.Serializable;
import java.util.Objects;

public class House implements Serializable {
    public static final long serialVersionUID = 1L;
    private int id;
    private int rent;
    private String address;

    private String state;

    private boolean isPublish;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public House() {
    }

    public boolean getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(boolean publish) {
        isPublish = publish;
    }

    public House(int id, int rent, String address, String state, boolean isPublish) {
        this.id = id;
        this.rent = rent;
        this.address = address;
        this.state = state;
        this.isPublish = isPublish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", rent=" + rent +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return id == house.id && rent == house.rent && isPublish==house.isPublish&& Objects.equals(address, house.address) && Objects.equals(state, house.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rent,isPublish, address, state);
    }
}
