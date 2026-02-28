package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.model.Car;

@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {

    @InjectMocks
    CarRepositoryInMemory carRepository;

    Car car;

    @BeforeEach
    void setUp() {
        this.car = new Car();
        this.car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.car.setCarName("Toyota Prius");
        this.car.setCarColor("Yellow");
        this.car.setCarQuantity(2);
        this.carRepository.create(this.car);
    }

    @Test
    void testCreateAndFind() {
        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());

        Car savedCar = carIterator.next();
        assertEquals(this.car.getCarId(), savedCar.getCarId());
        assertEquals(this.car.getCarName(), savedCar.getCarName());
        assertEquals(this.car.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        CarRepositoryInMemory carRepositoryEmpty = new CarRepositoryInMemory();
        Iterator<Car> carIterator = carRepositoryEmpty.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneCar() {
        Car car2 = new Car();
        car2.setCarId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        car2.setCarName("Honda CR-V");
        car2.setCarQuantity(1);
        this.carRepository.create(car2);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());

        Car savedCar = carIterator.next();
        assertEquals(this.car.getCarId(), savedCar.getCarId());

        savedCar = carIterator.next();
        assertEquals(car2.getCarId(), savedCar.getCarId());

        assertFalse(carIterator.hasNext());
    }

    @Test
    void testDelete() {
        this.carRepository.delete(this.car.getCarId());
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testDeleteIfCarNotFound() {
        String nonExistentCarId = "non-existent-car-id";
        this.carRepository.delete(nonExistentCarId);
        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
    }

    @Test
    void testUpdate() {
        Car updatedCar = new Car();
        updatedCar.setCarId(this.car.getCarId());
        updatedCar.setCarName("Updated Car Name");
        updatedCar.setCarColor("Red");
        updatedCar.setCarQuantity(5);

        this.carRepository.update(this.car.getCarId(),updatedCar);
        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());

        Car savedCar = carIterator.next();
        assertEquals(updatedCar.getCarId(), savedCar.getCarId());
        assertEquals(updatedCar.getCarName(), savedCar.getCarName());
        assertEquals(updatedCar.getCarColor(), savedCar.getCarColor());
        assertEquals(updatedCar.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testUpdateIfCarNotFound() {
        Car updatedCar = new Car();
        updatedCar.setCarId("non-existent-car-id");
        updatedCar.setCarName("Updated Car Name");
        updatedCar.setCarColor("Red");
        updatedCar.setCarQuantity(5);

        Car result = this.carRepository.update("non-existent-car-id", updatedCar);
        assertEquals(null, result);
    }   
}
