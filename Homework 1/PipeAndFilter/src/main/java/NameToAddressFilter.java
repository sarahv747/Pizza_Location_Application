import java.util.*;

public class NameToAddressFilter implements Filter<String>{
    @Override
    public String execute(String input) {
        Map<String, String> pizzaMap = new HashMap<>();
        String [] parts=null;
        String [] names=null;
        for (int i = 0; i <input.length(); i++) {
             parts=input.split(",");
             names=Arrays.copyOfRange(parts,4,input.length()-1);
             parts=Arrays.copyOfRange(parts,0,4);
        }
        String name= String.join(" ", names);
        String address=String.join(" ",parts);
        pizzaMap.put(name,address);
        return "";
    }
}
