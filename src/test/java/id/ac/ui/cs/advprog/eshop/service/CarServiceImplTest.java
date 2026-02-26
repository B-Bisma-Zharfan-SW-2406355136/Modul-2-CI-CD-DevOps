package id.ac.ui.cs.advprog.eshop.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {
    @Mock
    CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    Car car1;
    Car car2;

    @BeforeEach
    void setUp() {
        this.car1 = new Car();
        this.car1.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.car1.setCarName("Toyota Prius");
        this.car1.setCarColor("Yellow");
        this.car1.setCarQuantity(2);

        this.car2 = new Car();
        this.car2.setCarId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        this.car2.setCarName("Honda CR-V");
        this.car2.setCarColor("Red");
        this.car2.setCarQuantity(1);
    }

    @Test
    void testCreateCar() {
        carService.create(this.car1);
        verify(carRepository, times(1)).create(car1);
    }

    @Test
    void testFindAllCar() {
        List<Car> carList = Arrays.asList(car1, car2);
        Iterator<Car> carIterator = carList.iterator();

        when(carRepository.findAll()).thenReturn(carIterator);

        List<Car> result = carService.findAll();

        assertEquals(carList, result);
        assertEquals(carList.size(), result.size());
        assertEquals(carList.get(0).getCarId(), result.get(0).getCarId());
        assertEquals(carList.get(1).getCarId(), result.get(1).getCarId());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindAllIfEmpty() {
        List<Car> carList = new ArrayList<>();
        Iterator<Car> carIterator = carList.iterator();

        when(carRepository.findAll()).thenReturn(carIterator);

        List<Car> result = carService.findAll();

        assertEquals(carList, result);
        assertEquals(0, result.size());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindCarById() {
        when(carRepository.findById(car1.getCarId())).thenReturn(car1);

        Car result = carService.findById(car1.getCarId());
        assertEquals(car1, result);
        assertEquals(car1.getCarId(), result.getCarId());
        verify(carRepository, times(1)).findById(car1.getCarId());
    }

    @Test
    void testUpdateCar() {
        carService.update(car1.getCarId(), car1);
        verify(carRepository, times(1)).update(car1.getCarId(), car1);
    }

    @Test
    void testDeleteCarById() {
        carService.deleteCarById(car1.getCarId());
        verify(carRepository, times(1)).delete(car1.getCarId());
    }
}
