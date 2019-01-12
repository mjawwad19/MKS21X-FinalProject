import java.util.*;


public class Calculator{
  private static String asolve(List<String> input){
    for (int i = 0; i < input.size(); i++){
      if (input.get(i).equals("^")){
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
      if (input.get(i).equals("sin(")){
        boolean swap = false;
        for (int j = 0; j < input.size(); j++){
          if (input.get(j).equals(")") && swap == false){
            asolve(input.subList(i + 1, j));
            input.remove(i);
            input.set(i, "" + Math.sin(Math.toRadians(Double.parseDouble(input.get(i)))));
            input.remove(i + 1);
            swap = true;
          }
        }
      }
      if (input.get(i).equals("cos(")){
        boolean swap = false;
        for (int j = 0; j < input.size(); j++){
          if (input.get(j).equals(")") && swap == false){
            asolve(input.subList(i + 1, j));
            input.remove(i);
            input.set(i, "" + Math.cos(Math.toRadians(Double.parseDouble(input.get(i)))));
            input.remove(i + 1);
            swap = true;
          }
        }
      }
      if (input.get(i).equals("tan(")){
        boolean swap = false;
        for (int j = 0; j < input.size(); j++){
          if (input.get(j).equals(")") && swap == false){
            asolve(input.subList(i + 1, j));
            input.remove(i);
            input.set(i, "" + Math.tan(Math.toRadians(Double.parseDouble(input.get(i)))));
            input.remove(i + 1);
            swap = true;
          }
        }
      }
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

  public static Fraction[] Quad(String input){
    Polynomial ans = Polynomial.parsePoly(input);
    return ans.solveQuad();
  }

  public static Polynomial factor(String input){
    String[] out = input.split("\\)\\(");
    Polynomial a = Polynomial.parsePoly(out[0].substring(1));
    Polynomial b = Polynomial.parsePoly(out[1].substring(0, out[1].length() - 1));
    return a.multiply(b);
  }

  public static double mean(List<String> input){
    double ans = 0.0;
    for (int i = 0; i < input.size(); i++) {
      ans += Integer.parseInt(input.get(i));
    }
    return (ans / input.size());
  }

  public static double median(List<String> input){
    int center = input.size() / 2;
    if (input.size() % 2 == 0) {
      center--;
      ArrayList<String> inTween = new ArrayList<>();
      inTween.add(input.get(center));
      inTween.add(input.get(center + 1));
      return mean(inTween);
    }
    return (double) Integer.parseInt(input.get(center));
  }

  public static void main(String[] args) {
    ArrayList<String> input = new ArrayList<>();
    if (args.length > 0 && args[0].equals("PEMDAS")){
      String[] temp = args[1].split(" ");
      for (String arg: temp){
        input.add(arg);
      }
      System.out.println(solve(input));
    }
    else if (args.length > 0 && args[0].equals("MEAN")) {
      for (int i = 1; i < args.length; i++){
        input.add(args[i]);
      }
      System.out.println("Mean: " + mean(input));
    }
    else if (args.length > 0 && args[0].equals("MEDIAN")) {
      for (int i = 1; i < args.length; i++) {
        input.add(args[i]);
      }
      System.out.println("Median: " + median(input));
    }
    else if (args.length > 0 && args[0].equals("solve-quadratic")){
      System.out.println(Arrays.toString(Quad(args[1])));
    }
    else if (args.length > 0 && args[0].equals("sub-poly")){
      System.out.println(Polynomial.parsePoly(args[1]).sub(Integer.parseInt(args[2])));
    }
    if (args.length > 0 && args[0].equals("factor")){
      System.out.println(factor(args[1]));
    }
    if (args.length > 0 && args[0].equals("linear")){
      System.out.println(Polynomial.linear(args[1]));
    }
  }
}
