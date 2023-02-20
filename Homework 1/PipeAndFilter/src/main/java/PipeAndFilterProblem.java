import java.io.*;
import java.util.Scanner;

public class PipeAndFilterProblem {

    public static void main(String[] args) throws FileNotFoundException {
        ClassLoader loader = PipeAndFilterProblem.class.getClassLoader();
        Scanner scanner = new Scanner(new File(loader.getResource("input.csv").getFile()));
        Pipe<String> groupedPizzasPipe = new Pipe<>();
        RemoveNamesFilter removeNamesFilter = new RemoveNamesFilter();
        groupedPizzasPipe.addFilter(removeNamesFilter);
        StringBuilder resPipe=new StringBuilder();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.startsWith("id"))
                continue;
            resPipe.append(groupedPizzasPipe.runFilters(line)).append("United States\n") ;
        }
        System.out.println(resPipe);
        FileWriter writer= null;
        try {
            writer = new FileWriter("C:\\Users\\Dimitar\\Downloads\\PipeAndFilter\\src\\main\\resources\\output.csv");
            writer.write(String.valueOf(resPipe));
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
