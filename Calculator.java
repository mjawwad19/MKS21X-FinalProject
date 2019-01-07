import java.util.*;
public class Calculator{
  public static void main(String[] args) {
    String[] temp = args[0].split(" ");
    ArrayList<String> input = new ArrayList<>();
    for (String arg: temp){
      input.add(arg);
    }
    for (int i = 0; i < input.size(); i++){
      if (input.get(i).equals("*")){
        input.set(i, "" + Double.parseDouble(input.get(i - 1)) * Double.parseDouble(input.get(i + 1)));
        input.remove(i - 1);
        input.remove(i);
        i -= 1;
      }
      else if (input.get(i).equals("/")){
        input.set(i, "" + Double.parseDouble(input.get(i - 1)) / Double.parseDouble(input.get(i + 1)));
        input.remove(i - 1);
        input.remove(i);
        i -= 1;
      }
    }
    for (int i = 0; i < input.size(); i++){
      if (input.get(i).equals("+")){
        input.set(i, "" + (Double.parseDouble(input.get(i - 1)) + Double.parseDouble(input.get(i + 1))));
        input.remove(i - 1);
        input.remove(i);
        i -= 1;
      }
      else if (input.get(i).equals("-")){
        input.set(i, "" + (Double.parseDouble(input.get(i - 1)) - Double.parseDouble(input.get(i + 1))));
        input.remove(i - 1);
        input.remove(i);
        i -= 1;
      }
    }
    System.out.println(input.get(0));
  }
}
