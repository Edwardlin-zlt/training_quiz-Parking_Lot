import java.util.Objects;

public class Lot {

    private int id;
    private String parkingLotTag;
    private String parkingLotNumber;
    private String carNumber;

    public Lot() {
    }

    public Lot(int id, String parkingLotTag, String parkingLotNumber, String carNumber) {
        this.id = id;
        this.parkingLotTag = parkingLotTag;
        this.parkingLotNumber = parkingLotNumber;
        this.carNumber = carNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParkingLotTag() {
        return parkingLotTag;
    }

    public void setParkingLotTag(String parkingLotTag) {
        this.parkingLotTag = parkingLotTag;
    }

    public String getParkingLotNumber() {
        return parkingLotNumber;
    }

    public void setParkingLotNumber(String parkingLotNumber) {
        this.parkingLotNumber = parkingLotNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lot lot = (Lot) o;
        return id == lot.id &&
            Objects.equals(parkingLotTag, lot.parkingLotTag) &&
            Objects.equals(parkingLotNumber, lot.parkingLotNumber) &&
            Objects.equals(carNumber, lot.carNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parkingLotTag, parkingLotNumber, carNumber);
    }

    @Override
    public String toString() {
        return "Lot{" +
            "id=" + id +
            ", parkingLotTag='" + parkingLotTag + '\'' +
            ", parkingLotNumber='" + parkingLotNumber + '\'' +
            ", carNumber='" + carNumber + '\'' +
            '}';
    }
}
