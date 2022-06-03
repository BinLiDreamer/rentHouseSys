package bean;

import java.io.Serializable;
import java.util.Objects;

public class HouseInfo implements Serializable {
    public static final long serialVersionUID = 1l;
    private int id;
    private int hostId;
    private int houseId;

    public HouseInfo() {
    }

    public HouseInfo(int id, int hostId, int houseId) {
        this.id = id;
        this.hostId = hostId;
        this.houseId = houseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HouseInfo houseInfo = (HouseInfo) o;
        return id == houseInfo.id && hostId == houseInfo.hostId && houseId == houseInfo.houseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hostId, houseId);
    }
}
