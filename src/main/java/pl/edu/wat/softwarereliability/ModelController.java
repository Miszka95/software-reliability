package pl.edu.wat.softwarereliability;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class ModelController {

    private static final String PAGE = "index";

    private final ModelService modelService;

    @GetMapping
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView(PAGE);
        modelAndView.addObject("input", new Input());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView handleRequest(@ModelAttribute Input input) {
        Output output = modelService.calculateModels(input);
        ModelAndView modelAndView = new ModelAndView(PAGE);
        modelAndView.addObject("output", output);
        return modelAndView;
    }
}
