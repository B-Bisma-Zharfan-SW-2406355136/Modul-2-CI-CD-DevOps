package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.dto.CarDto;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/createCar")
    public String createCarPage(Model model){
        CarDto carDto = new CarDto();
        model.addAttribute("car", carDto);
        return "createCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute CarDto carDto, Model model){
        carService.create(carDto);
        return "redirect:/car/listCar";
    }

    @GetMapping("/listCar")
    public String carListPage(Model model){
        List<Car> allCars = carService.findAll();
        model.addAttribute("cars", allCars);
        return "carList";
    }

    @GetMapping("/editCar/{carId}")
    public String editCarPage(@PathVariable String carId, Model model){
        Car existingCar = carService.findById(carId);

        if (existingCar == null) {
            return "redirect:/car/listCar";
        }

        CarDto carDto = new CarDto();
        carDto.setCarName(existingCar.getCarName());
        carDto.setCarColor(existingCar.getCarColor());
        carDto.setCarQuantity(existingCar.getCarQuantity());

        model.addAttribute("car", carDto);
        model.addAttribute("carId", carId);

        return "editCar";
    }

    @PostMapping("/editCar/{carId}")
    public String editCarPost(@PathVariable String carId, @ModelAttribute CarDto carDto){
        carService.update(carId, carDto);
        return "redirect:/car/listCar";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam("carId") String carId){
        carService.deleteCarById(carId);
        return "redirect:/car/listCar";
    }
}