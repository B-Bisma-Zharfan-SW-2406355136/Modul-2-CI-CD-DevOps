package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {
    @Mock
    private CarService carService;

    @Mock
    private Model model;

    @InjectMocks
    private CarController carController;

    private Car sampleCar;

    @BeforeEach
    void setUp(){
        this.sampleCar = new Car();
        this.sampleCar.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.sampleCar.setCarName("Toyota Prius");
        this.sampleCar.setCarColor("Yellow");
        this.sampleCar.setCarQuantity(2);
    }

    @Test
    void testCreateCarPage(){
        String viewName = carController.createCarPage(model);

        assertEquals("createCar", viewName);
        verify(model, times(1)).addAttribute(eq("car"), any(Car.class));
    }

    @Test
    void testCreateCarPost(){
        String viewName = carController.createCarPost(sampleCar, model);

        assertEquals("redirect:/car/listCar", viewName);
        verify(carService, times(1)).create(sampleCar);
    }

    @Test
    void testCarListPage(){
        List<Car> carList = new ArrayList<>();
        carList.add(sampleCar);
        when(carService.findAll()).thenReturn(carList);
        String viewName = carController.carListPage(model);

        assertEquals("carList", viewName);
        verify(model, times(1)).addAttribute("cars", carList);
        verify(carService, times(1)).findAll();
    }

    @Test
    void testEditCarPage(){
        String carId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        when(carService.findById(carId)).thenReturn(sampleCar);

        String viewName = carController.editCarPage(carId, model);

        assertEquals("editCar", viewName);
        verify(carService, times(1)).findById(carId);
        verify(model, times(1)).addAttribute("car", sampleCar);
    }

    @Test
    void testEditCarPageIfCarNotFound(){
        String carId = "non-existent-car-id";
        when(carService.findById(carId)).thenReturn(null);

        String viewName = carController.editCarPage(carId, model);

        assertEquals("editCar", viewName);
        verify(carService, times(1)).findById(carId);
        verify(model, times(1)).addAttribute("car", null);
    }

    @Test
    void testEditCarPost(){
        String carId = "eb558e9f-1c39-460e-8860-71af6af63bd6";

        String viewName = carController.editCarPost(sampleCar, model);

        assertEquals("redirect:/car/listCar", viewName);
        verify(carService, times(1)).update(carId, sampleCar);
    }

    @Test
    void testEditCarPostIfCarNotFound(){
        String carId = "non-existent-car-id";
        sampleCar.setCarId(carId); 

        String viewName = carController.editCarPost(sampleCar, model);

        assertEquals("redirect:/car/listCar", viewName);;
        verify(carService, times(1)).update(carId, sampleCar);
    }

    @Test
    void testDeleteCar(){
        String carId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        carController.deleteCar(carId);

        verify(carService, times(1)).deleteCarById(carId);
    }

    @Test
    void testDeleteCarIfCarNotFound(){ 
        String carId = "non-existent-car-id";
        carController.deleteCar(carId);

        verify(carService, times(1)).deleteCarById(carId);
    }
}
