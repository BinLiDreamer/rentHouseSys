package bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String customerName;
    private String phone;
    private String message;
    private int houseInfoId;
    private Timestamp localDateTime;

    private boolean isRead;

    public Message() {
    }

    public Message(int id, String customerName, String phone, String message, int houseInfoId, Timestamp localDateTime,boolean isRead) {
        this.id = id;
        this.customerName = customerName;
        this.phone = phone;
        this.message = message;
        this.houseInfoId = houseInfoId;
        this.localDateTime = localDateTime;
        this.isRead = isRead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHouseInfoId() {
        return houseInfoId;
    }

    public void setHouseInfoId(int houseInfoId) {
        this.houseInfoId = houseInfoId;
    }

    public Timestamp getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(Timestamp localDateTime) {
        this.localDateTime = localDateTime;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean read) {
        isRead = read;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return id == message1.id && houseInfoId == message1.houseInfoId && isRead == message1.isRead && Objects.equals(customerName, message1.customerName) && Objects.equals(phone, message1.phone) && Objects.equals(message, message1.message) && Objects.equals(localDateTime, message1.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, phone, message, houseInfoId, localDateTime, isRead);
    }
}
