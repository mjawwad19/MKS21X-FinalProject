import java.util.*;
public class Calculator{
  private static String asolve(List<String> input){
    for (int i = 0; i < input.size(); i++){
      if (input.get(i).equals("**")){
        input.set(i, "" + Math.pow(Double.parseDouble(input.get(i - 1)),Double.parseDouble(input.get(i + 1))));
        input.remove(i - 1);
        input.remove(i);
        i--;
      }
    }
    for (int i = 0; i < input.size(); i++){
      if (input.get(i).equals("*")){
        input.set(i, "" + Double.parseDouble(input.get(i - 1)) * Double.parseDouble(input.get(i + 1)));
        input.remove(i - 1);
        input.remove(i);
        i--;
      }
      else if (input.get(i).equals("/")){
        input.set(i, "" + Double.parseDouble(input.get(i - 1)) / Double.parseDouble(input.get(i + 1)));
        input.remove(i - 1);
        input.remove(i);
        i--;
      }
    }
    for (int i = 0; i < input.size(); i++){
      if (input.get(i).equals("+")){
        input.set(i, "" + (Double.parseDouble(input.get(i - 1)) + Double.parseDouble(input.get(i + 1))));
        input.remove(i - 1);
        input.remove(i);
        i--;
      }
      else if (input.get(i).equals("-")){
        input.set(i, "" + (Double.parseDouble(input.get(i - 1)) - Double.parseDouble(input.get(i + 1))));
        input.remove(i - 1);
        input.remove(i);
        i--;
      }
    }

    return input.get(0);
  }

  public static double solve(List<String> input){
    for (int i = 0; i < input.size(); i++){
      if (input.get(i).equals("(")){
        boolean swap = false;
        for (int j = 0; j < input.size(); j++){
          if (input.get(j).equals(")") && swap == false){
            asolve(input.subList(i + 1, j));
            input.remove(i);
            input.remove(i + 1);
            swap = true;
          }
        }
      }
    }
    return Double.parseDouble(asolve(input));
  }

  public static void main(String[] args) {
    String[] temp = args[0].split(" ");
    ArrayList<String> input = new ArrayList<>();
    for (String arg: temp){
      input.add(arg);
    }

    System.out.println(solve(input));
  }
}
