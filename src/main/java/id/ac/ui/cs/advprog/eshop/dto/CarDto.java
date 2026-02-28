package id.ac.ui.cs.advprog.eshop.dto;

public class CarDto {
    // Hanya field yang perlu diinput oleh user
    private String carName;
    private String carColor;
    private int carQuantity;

    // Getter dan Setter
    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarColor(){
        return carColor;
    }

    public void setCarColor(String carColor){
        this.carColor = carColor;
    }

    public int getCarQuantity() {
        return carQuantity;
    }

    public void setCarQuantity(int carQuantity) {
        this.carQuantity = carQuantity;
    }
}