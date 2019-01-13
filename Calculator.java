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
    String[] in = input.split(" = ");
    Polynomial ans = Polynomial.parsePoly(in[0]);
    ans.subtract(Polynomial.parsePoly(in[1]));
    return ans.solveQuad();
  }


  private static String drwl(int len){
    String a = "-";
    String l = String.join("", Collections.nCopies(len, a));
    return l;
  }

  public static Polynomial factor(String input, String method){
    String[] out = input.split("\\)\\(");
    Polynomial a = Polynomial.parsePoly(out[0].substring(1));
    Polynomial b = Polynomial.parsePoly(out[1].substring(0, out[1].length() - 1));
    if (method.equals("multiply-pp")) {
      System.out.println("  " + a + "\n*(" + b + ")\n" + drwl(out[0].length()+ 2));
      return a.multiply(b);
    }
    else if (method.equals("subtract-pp")) {
      System.out.println("  " + a + "\n-(" + b + ")\n" + drwl(out[0].length()+ 2));
      a.subtract(b);
      return a;
    }
    else if (method.equals("add-pp")) {
      System.out.println("  " + a + "\n+(" + b + ")\n" + drwl(out[0].length() + 2));
      a.add(b);
      return a;
    }
    return a;
  }

  public static double mean(Double[] input){
    double ans = 0.0;
    for (int i = 0; i < input.length; i++) {
      ans += input[i];
    }
    return ans / input.length;
  }

  public static double median(Double[] input){
    int center = input.length / 2;
    if (input.length % 2 == 0) {
      center--;
      Double[] inTween = new Double[2];
      inTween[0] = input[center];
      inTween[1] = input[center + 1];
      return mean(inTween);
    }
    return input[center];
  }

  public static void main(String[] args) {
    String msg = "If you would like to use this calculator, please use the following format:  \n\nPEMDAS [expression(no variable)] \nmean [num1] [num2]...  \nmedian [num1] [num2]... \nsolve-quadratic \" [quadratic equation] \" \nadd-pp \" ([polynomial])([polynomial]) \" \nsubtract-pp \" ([polynomial])([polynomial]) \" \nmultiply-pp \" ([polynomial])([polynomial]) \" \nfour_function-poly [expression(with one variable)] \nsub \" [polynomial/monomial] \" [int] \" \nlinear \" [linear equation with one variable] \" ";
    String needTest = "\n\ngraph [polynomial] [xbound1] [xbound2] [ybound1] [ybound2] or graph [polynomial]";
    try {
      ArrayList<String> input = new ArrayList<>();
      if (args.length > 0 && args[0].equals("PEMDAS")){
        String[] temp = args[1].split(" ");
        for (String arg: temp){
          input.add(arg);
        }
        System.out.println(solve(input));
      }
      else if (args.length > 0 && args[0].equals("mean")) {
        Double[] in = new Double[args.length - 1];
        for (int i = 1; i < args.length; i++){
          in[i-1] = Double.parseDouble(args[i]);
        }
        System.out.println("Mean: " + mean(in));
      }
      else if (args.length > 0 && args[0].equals("median")) {
        Double[] in = new Double[args.length - 1];
        for (int i = 1; i < args.length; i++){
          in[i-1] = Double.parseDouble(args[i]);
        }
        Arrays.sort(in);
        System.out.println("Median: " + median(in));
      }
      else if (args.length > 0 && args[0].equals("solve-quadratic")){
        System.out.println("Real Roots Found: " + Arrays.toString(Quad(args[1])));
      }
      else if (args.length > 0 && args[0].equals("sub")){
        System.out.println(Polynomial.parsePoly(args[1]).sub(Integer.parseInt(args[2])));
      }
      else if (args.length > 0 && (args[0].equals("multiply-pp") ||
                                   args[0].equals("add-pp") ||
                                   args[0].equals("subtract-pp"))){
        System.out.println(factor(args[1],args[0]));
      }
      else if (args.length > 0 && (args[0].equals("four_function-mono"))){
        System.out.println(Polynomial.parsePoly(args[1]));
      }
      else if (args.length > 0 && args[0].equals("linear")){
        System.out.println(Polynomial.linear(args[1]));
      }
      else if (args.length > 0 && args[0].equals("graph")){
        if (!(args.length > 1)) {
          Graph g1 = new Graph(Polynomial.parsePoly(args[1]));
          System.out.println(g1);
        }
        else if (!(args.length < 6)) {
          Graph g1 = new Graph(Integer.parseInt(args[2]), Integer.parseInt(args[3]),
                               Integer.parseInt(args[4]), Integer.parseInt(args[5]), Polynomial.parsePoly(args[1]));
          System.out.println(g1);
        }
      }
      else System.out.println(msg);
    }catch(IllegalArgumentException e){
      System.out.println(e);
    }catch(Exception e) {
      System.out.println(msg);
      //e.printStackTrace();
    }
  }
}
