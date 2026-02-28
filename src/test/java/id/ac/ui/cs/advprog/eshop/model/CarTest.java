package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    Car car;

    @BeforeEach
    void setUp() {
        this.car = new Car();
        this.car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.car.setCarName("Toyota Prius");
        this.car.setCarColor("Yellow");
        this.car.setCarQuantity(2);
    }

    @Test
    void testGetCarId() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.car.getCarId());
    }

    @Test
    void testGetCarColor(){
        assertEquals("Yellow", this.car.getCarColor());
    }

    @Test
    void testGetCarName() {
        assertEquals("Toyota Prius", this.car.getCarName());
    }

    @Test
    void testGetCarQuantity() {
        assertEquals(2, this.car.getCarQuantity());
    }
}