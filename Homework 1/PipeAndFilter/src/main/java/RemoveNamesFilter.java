public class RemoveNamesFilter implements Filter<String>{
    @Override
    public String execute(String input) {
        String[] fields = input.split(",");

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            if(i==3  || i==4 || i==13 || i>=17)
                res.append(fields[i]).append(",");
        }
        return res.toString();
    }

}