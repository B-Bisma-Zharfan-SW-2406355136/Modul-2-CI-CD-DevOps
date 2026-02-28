package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.dto.CarDto;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import id.ac.ui.cs.advprog.eshop.repository.CarRepositoryInMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class CarServiceImpl implements CarService{
    @Autowired
    private CarRepository carRepository;

    @Override
    public Car create(CarDto carDto){
        Car car = new Car();
        car.setCarName(carDto.getCarName());
        car.setCarQuantity(carDto.getCarQuantity());
        car.setCarColor(carDto.getCarColor());
        car.setCarId(UUID.randomUUID().toString());
        carRepository.create(car);
        return car;
    }

    @Override
    public List<Car> findAll(){
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> allCar = new ArrayList<>();
        carIterator.forEachRemaining(allCar::add);
        return allCar;
    }

    @Override
    public Car findById(String carId){
        return carRepository.findById(carId);
    }

    @Override
    public void update(String carId, CarDto carDto){
        Car existingCar = carRepository.findById(carId);
        existingCar.setCarName(carDto.getCarName());
        existingCar.setCarColor(carDto.getCarColor());
        existingCar.setCarQuantity(carDto.getCarQuantity());
        carRepository.update(carId, existingCar);
    }

    @Override
    public void deleteCarById(String carId){
        carRepository.delete(carId);
    }
}
