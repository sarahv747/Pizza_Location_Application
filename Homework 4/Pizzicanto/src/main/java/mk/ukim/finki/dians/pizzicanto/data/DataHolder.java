package mk.ukim.finki.dians.pizzicanto.data;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.dians.pizzicanto.model.Pizzeria;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.List;

@Component
public class DataHolder {
    public static List<Pizzeria> pizzerias;

    //after the DataHolder is initialised
    @PostConstruct
    public void readFromCsv(){
        BufferedReader reader;

        try {
            //read rows from csv file, map them into Pizzeria objects and place them into Arraylist
            reader = new BufferedReader(new FileReader("data.csv"));
            pizzerias = reader.lines().map(Pizzeria::new).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}