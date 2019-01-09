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

  public static double Quad(List<String> input){
    Polynomial ans = new Polynomial();
    return 0;
  }

  public static double mean(List<String> input){
    double ans;
    for (int i = 1; i < input.size(); i++) {
      ans += (double)(i);
    }
    return ((ans / input.size()) - 1);
  }

  public static int median(List<String> input){
    int center = (input.size() / 2) + 1;
    return (int) Integer.parseInt(input.get(center));
  }

  public static void main(String[] args) {
    System.out.println("\033[107m\033[30m");
    ArrayList<String> input = new ArrayList<>();
    if (args.length > 0 && args[0].equals("PEMDAS")){
      String[] temp = args[1].split(" ");
      for (String arg: temp){
        input.add(arg);
      }
      System.out.println(solve(input));
    }
    if (args.length > 0 && args[0].equals("MEAN")) {
      System.out.println(mean(input));
    }
  }
}
