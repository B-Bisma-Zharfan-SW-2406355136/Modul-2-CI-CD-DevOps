package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.dto.CarDto;
import id.ac.ui.cs.advprog.eshop.model.Car;

import java.util.List;

public interface CarService {
    Car create(CarDto car);
    List<Car> findAll();
    Car findById(String carId);
    void update(String carId, CarDto carDto);
    void deleteCarById(String carId);
}
