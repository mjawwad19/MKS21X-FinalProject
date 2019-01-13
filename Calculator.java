import java.util.*;


public class Calculator{
  /**
   * Simplifies the List by performing the specified
   * operations(power, addition, subtraction, multiplication, division) on the
   * numbers(stored as a String) in the List.
   * @param input List of String being simplified
   * @return String of the simplified double
   */
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

  /**
   * Simplifies the expression(given as a List of Strings) completely.
   * @param input The expression written as a List of String
   * @return the answer of the expression
   */
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

  /**
   * Parses a String input of a quadratic equation and finds its real roots
   * @param input The quadratic equation written as a String
   * @return A Fraction array that contains the real roots of the quadratic equation
   */
  public static Fraction[] Quad(String input){
    String[] in = input.split(" = ");
    Polynomial ans = Polynomial.parsePoly(in[0]);
    ans.subtract(Polynomial.parsePoly(in[1]));
    return ans.solveQuad();
  }

  /**
   * Draws a horizontal line
   * @param len The length of the horizontal line
   * @return A String of a horizontal line
   */
  private static String drwl(int len){
    String a = "-";
    String l = String.join("", Collections.nCopies(len, a));
    return l;
  }

  /**
   * Performs either addition, subtraction, multiplication, or power function on two Polynomials
   * @param input String consisting of the two Polynomials
   * @param method the operation to be done on the Polynomials
   * @return The resulting Polynomial after the operation
   */
  public static Polynomial factor(String input, String method){
    String[] out = input.split("_");
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
    else if (method.equals("power-pp")) {
      return a.power(Integer.parseInt(out[1].substring(0, out[1].length() - 1)));
    }
    return a;
  }

  /**
   * Calculates the mean of an array of doubles
   * @param input An array of Doubles
   * @return the mean of the Doubles
   */
  public static double mean(Double[] input){
    double ans = 0.0;
    for (int i = 0; i < input.length; i++) {
      ans += input[i];
    }
    return ans / input.length;
  }

  /**
   * Calculates the median of an array of doubles
   * @param input An array of Doubles
   * @return the median of the Doubles
   */
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
    String msg = "\n\n\n\nIf you would like to use this calculator, please use the following format:  \n\n"
                + "PEMDAS \" [expression(no variable)] \" \n \t ex: java Calculator PEMDAS \"4 ^ 2 + 5 * 3 - 6 / 2 \" \n\n"
                + "mean [num1] [num2]... \n \t ex: java Calculator mean 10 20 30 40 92 \n\n"
                + "median [num1] [num2]... \n \t ex: java Calculator median 39 48 49 37 28 12\n\n"
                + "solve-quadratic \" [quadratic equation] \" \n \t ex: java Calculator solve-quadratic \"x^(2) - 1 = 0\" \n\n"
                + "add-pp \" ([polynomial])([polynomial]) \" \n \t ex: java Calculator add-pp \"(4x^(2) - 3x)(5x + 4)\" \n\n"
                + "subtract-pp \" ([polynomial])([polynomial]) \" \n \t ex: java Calculator subtract-pp \"(4x^(2) - 3x)(5x^(3) + 4x)\" \n\n"
                + "multiply-pp \" ([polynomial])([polynomial]) \" \n \t ex: java Calculator multiply-pp \"(x - 1)(x + 1)\" \n\n"
                + "power-pp \" ([polynomial])([int]) \" \n \t ex: java Calculator power-pp \"(x - 1)(3)\" \n\n"
                + "four_function-mono [expression(with one variable)] \n \t ex: java Calculator four_function-mono \"4x^(2) * 5x^(3) - 3x^(6) + 4x\" \n\n"
                + "sub \" [polynomial/monomial] \" [int] \" \n \t ex: java Calculator sub \"4x^(2) + 3\" 8 \n\n"
                + "graph \" [polynomial/monomial(with one variable)] \" OR graph \" [polyomial/monomial(with one variable)] \" [int(X min)] [int(X max)] [int(Y min)] [int(Y max)] \n \t ex: java Calculator graph \" 4x^(2) + 2x + 2 \" -5 5 -10 10 \n\n"
                + "linear \" [linear equation with one variable] \" \n \t ex: java Calculator linear \"4x - 5 = 2\" \n\n\n\n";
    String m1 = "\n\nTo write a polynomial/monomial (nomial for both), please note the following: \n\nVariables to a degree are encapsulated in parenthesis: x^(5)\nPlease place quotation marks with one space from either end around your nomial(s) \" [nomial(s)] \" \nTo have multiply nomials, please place a '_' in between them: \" [nomial]_[nomial] \"";
    String p = "\n\nsolve-quadratic \" [quadratic polynomial] \" \n\nadd-pp \" [polynomial]_[polynomial] \" \n\nsubtract-pp \" [polynomial]_[polynomial] \" \n\nmultiply-pp \" [polynomial]_[polynomial] \" \n\n";
    String m = "add-mm \" [monomial]_[monomial]  \" \n\nsubtract-mm \" [monomial]_[monomial]  \" \n\nmultiply-mm \" [monomial]_[monomial] ] \" \n\ndivide-mm \" [monomial]_[monomial] \"";
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
                                   args[0].equals("power-pp") ||
                                   args[0].equals("subtract-pp"))){
        System.out.println(factor(args[1],args[0]));
      }
      else if (args.length > 0 && (args[0].equals("four_function-mono"))){
        System.out.println(Polynomial.parsePoly(args[1]));
      }
      else if (args.length > 0 && args[0].equals("linear")){
        System.out.println(Polynomial.linear(args[1]));
      }
      else if ((args.length == 2 || args.length == 6) && args[0].equals("graph")){
        if (args.length == 2) {
          //System.out.println(args[1]);
          Graph g1 = new Graph(Polynomial.parsePoly(args[1]));
          System.out.println(g1);
        }
        else if (args.length == 6) {
          Graph g1 = new Graph(Integer.parseInt(args[2]), Integer.parseInt(args[3]),
                               Integer.parseInt(args[4]), Integer.parseInt(args[5]), Polynomial.parsePoly(args[1]));
          System.out.println(g1);
        }
        else System.out.println("Too many bounds/Too little, refer to instructions!");
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
