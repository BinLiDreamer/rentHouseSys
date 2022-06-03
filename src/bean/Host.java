package bean;

import java.io.Serializable;
import java.util.Objects;

public class Host implements Serializable {
    public static final long serialVersionUID = 1l;
    private int id;
    private String name;
    private String phone;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "\tHost{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public Host(int id, String name, String phone,int count) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.count = count;
    }

    public Host() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Host host = (Host) o;
        return id == host.id && Objects.equals(name, host.name) && Objects.equals(phone, host.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone);
    }
}
