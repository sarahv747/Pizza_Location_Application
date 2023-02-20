package mk.ukim.finki.dians.pizzicanto.repository;

import mk.ukim.finki.dians.pizzicanto.data.DataHolder;
import mk.ukim.finki.dians.pizzicanto.model.Pizzeria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PizzeriaRepository {

    //for the first dropdown menu
    public List<String> findStates(){
        List<String> states= new ArrayList<>(DataHolder.pizzerias.stream().map(Pizzeria::getState).distinct().toList());
        states.add(0,"");
        return states;
    }

    //for the second dropdown menu
    public List<String> findCitiesByState(String state){
        List<String> cities= new ArrayList<>(DataHolder.pizzerias.stream().filter(p->p.getState().equals(state)).map(Pizzeria::getCity).distinct().toList());
        cities.add(0,"");
        return cities;
    }

    //for the third dropdown menu
    public List<Pizzeria> findPizzeriasByStateAndByCity(String state, String city) {
        return DataHolder.pizzerias.stream().filter(p->p.getCity().equals(city) && p.getState().equals(state)).distinct()
                .collect(Collectors.toList());
    }
    public Pizzeria findPizzeriaByStateAndByCityAndByName(String state,String city,String name){
        return findPizzeriasByStateAndByCity(state,city).stream().filter(p->p.getName().equals(name)).findFirst().get();
    }
    public List<Pizzeria> findPizzerias() {
        return DataHolder.pizzerias;
    }
    public Optional<Pizzeria> findPizzeriaById(Long Id) {
        return DataHolder.pizzerias.stream().filter(p->p.getId().equals(Id)).findFirst();
    }

}