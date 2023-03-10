package mk.ukim.finki.dians.pizzicanto.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.dians.pizzicanto.model.Pizzeria;
import mk.ukim.finki.dians.pizzicanto.service.PizzeriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Controller
@RequestMapping({"/","/Pizzicanto"})
public class PizzeriaController {
    private final PizzeriaService pizzeriaService;

    public PizzeriaController(PizzeriaService pizzeriaService){
        this.pizzeriaService = pizzeriaService;
    }

    @GetMapping()
    public String getHomepage(Model model,HttpServletRequest req) {
        //checks if RANDOM button was clicked
        Pizzeria rPizzeria=(Pizzeria) req.getSession().getAttribute("RPizzeria");
        if(rPizzeria!=null){
            model.addAttribute("random",true);
            model.addAttribute("latitude",rPizzeria.getLatitude());
            model.addAttribute("longitude",rPizzeria.getLongitude());
        }
        //checks if the GET DIRECTIONS button was clicked
        Pizzeria sPizzeria=(Pizzeria) req.getSession().getAttribute("sPizzeria");
        if(sPizzeria!=null){
            model.addAttribute("selected",true);
            model.addAttribute("la",sPizzeria.getLatitude());
            model.addAttribute("lo",sPizzeria.getLongitude());
        }
        model.addAttribute("states",pizzeriaService.getStates());
        return "homepage";
    }

    @PostMapping("/setCities")
    @ResponseBody
    public List<String> afterSelectingState(@RequestParam("state") String state) {
        return pizzeriaService.getCitiesInState(state);
    }


    @PostMapping("/setPizzerias")
    @ResponseBody
    public List<String> afterSelectingCity(@RequestParam String state,
                                           @RequestParam String city){
        return pizzeriaService.getPizzeriasInCity(state,city)
                .stream().map(Pizzeria::getName).distinct().toList();
    }

    //after clicking the GET DIRECTIONS button
    @PostMapping("/GetDirections")
    public String getDirections(@RequestParam("stateSelect") String state,
                                @RequestParam("citySelect") String city,
                                @RequestParam("pizzeriaSelect") String name,
                                HttpServletRequest req){
        Pizzeria p=pizzeriaService.getPizzeriaByStateAndCityAndName(state,city,name);
        req.getSession().setAttribute("sPizzeria",p);
        return "redirect:/Pizzicanto";
    }

    //after clicking the RANDOM button generates a random number for the id,finds pizzeria by id and displays directions
    @PostMapping("/Random")
    public String RandomMap(HttpServletRequest req){
        Random random=new Random();
        Optional<Pizzeria> pizzeria=pizzeriaService.getPizzeriaById(random.nextLong(0,pizzeriaService.getPizzerias().size()));
        pizzeria.ifPresent(value -> req.getSession().setAttribute("RPizzeria", value));
        return "redirect:/Pizzicanto";
    }

}
