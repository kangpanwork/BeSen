package beSen.generatorProject.design;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/makeCar")
@RestController
public class GeneratorController {

    @Autowired Car car;

    @GetMapping("/")
    public void get() {
        car.get();
    }
}
