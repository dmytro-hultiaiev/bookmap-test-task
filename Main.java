import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader file_input = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter file_output = new BufferedWriter(new FileWriter("output.txt"))){

            TreeMap<Integer, Integer> ask = new TreeMap<>();
            TreeMap<Integer, Integer> bid = new TreeMap<>();
            TreeMap<Integer, Integer> sprad = new TreeMap<>();

            String line = file_input.readLine();
            String command;
            String type;
            String price;
            String size;

            while (line != null){
                StringTokenizer st = new StringTokenizer(line,",");
                while (st.hasMoreTokens()){
                    command = st.nextToken();

                    if(command.equals("u")){
                        price = st.nextToken();
                        size = st.nextToken();
                        type = st.nextToken();

                        if(type.equals("ask")){
                            ask.put(Integer.parseInt(price), Integer.parseInt(size));
                        }
                        else{
                            bid.put(Integer.parseInt(price), Integer.parseInt(size));
                        }

                        sprad.remove(Integer.parseInt(price));
                    }
                    if(command.equals("q")){
                        type = st.nextToken();

                        if(type.equals("best_bid")){
                            int num = bid.lastKey();
                            file_output.write(num + "," + bid.get(num) + "\n");
                        }
                        else if(type.equals("best_ask")){
                            int num = ask.firstKey();
                            file_output.write(num + "," + ask.get(num) + "\n");
                        }
                        else{
                            price = st.nextToken();
                            if(bid.containsKey(Integer.parseInt(price))){
                                file_output.write(bid.get(Integer.parseInt(price)) + "\n");
                            }
                            if(ask.containsKey(Integer.parseInt(price))){
                                file_output.write(ask.get(Integer.parseInt(price)) + "\n");
                            }
                            if(sprad.containsKey(Integer.parseInt(price))){
                                file_output.write("0" + "\n");
                            }
                        }
                    }
                    if(command.equals("o")){
                        type = st.nextToken();
                        size = st.nextToken();
                        if(type.equals("buy")){
                            int remainder = ask.get(ask.firstKey()) - Integer.parseInt(size);
                            if(remainder == 0){
                                sprad.put(ask.lastKey(), 0);
                                ask.remove(ask.lastKey());
                            }
                            else{
                                ask.put(ask.firstKey(), ask.get(ask.firstKey()) - Integer.parseInt(size));
                            }
                        }
                        else {
                            int remainder = bid.get(bid.lastKey()) - Integer.parseInt(size);
                            if(remainder == 0){
                                sprad.put(bid.lastKey(), 0);
                                bid.remove(bid.lastKey());
                            }
                            else {
                                bid.put(bid.lastKey(), bid.get(bid.lastKey()) - Integer.parseInt(size));
                            }
                        }
                    }
                }
                line = file_input.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}