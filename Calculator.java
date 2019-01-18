import java.util.*;


public class Calculator{
  private double ans;

  public double getAns(){
    return ans;
  }

  public void setAns(double a){
    ans = a;
  }

  /**
   * Simplifies the List by performing the specified
   * operations(power, addition, subtraction, multiplication, division) on the
   * numbers(stored as a String) in the List.
   * @param input List of String being simplified
   * @return String of the simplified double
   */
  private static String asolve(List<String> input, Calculator ahh){
    for (int i = 0; i < input.size(); i++){
      if (input.get(i).equals("ans")) input.set(i, "" + ahh.getAns());
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
      else Double.parseDouble(input.get(i));
    }

    return input.get(0);
  }

  /**
   * Simplifies the expression(given as a List of Strings) completely.
   * @param input The expression written as a List of String
   * @return the answer of the expression
   */
  public static double solve(List<String> input, Calculator ahh){
    for (int i = 0; i < input.size(); i++){
      if (input.get(i).equals("sin(")){
        boolean swap = false;
        for (int j = i; j < input.size(); j++){
          if (input.get(j).equals(")") && swap == false){
            asolve(input.subList(i + 1, j), ahh);
            input.remove(i);
            input.set(i, "" + Math.sin(Math.toRadians(Double.parseDouble(input.get(i)))));
            input.remove(i + 1);
            swap = true;
          }
        }
      }
      if (input.get(i).equals("cos(")){
        boolean swap = false;
        for (int j = i; j < input.size(); j++){
          if (input.get(j).equals(")") && swap == false){
            asolve(input.subList(i + 1, j), ahh);
            input.remove(i);
            input.set(i, "" + Math.cos(Math.toRadians(Double.parseDouble(input.get(i)))));
            input.remove(i + 1);
            swap = true;
          }
        }
      }
      if (input.get(i).equals("tan(")){
        boolean swap = false;
        for (int j = i; j < input.size(); j++){
          if (input.get(j).equals(")") && swap == false){
            asolve(input.subList(i + 1, j), ahh);
            input.remove(i);
            input.set(i, "" + Math.tan(Math.toRadians(Double.parseDouble(input.get(i)))));
            input.remove(i + 1);
            swap = true;
          }
        }
      }
    }
    for (int i = 0; i < input.size(); i++){
      if (input.get(i).equals("(")){
        boolean swap = false;
        for (int j = i; j < input.size(); j++){
          if (input.get(j).equals(")") && swap == false){
            asolve(input.subList(i + 1, j),ahh);
            input.remove(i);
            input.remove(i + 1);
            swap = true;
          }
        }
      }
    }
    return Math.round(Double.parseDouble(asolve(input, ahh)) * 1000.0) / 1000.0;
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
    return Math.round(ans / input.length * 1000.0) / 1000.0;
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
    System.out.println();
    System.out.println("Please choose an input mode: \n\t PEMDAS \n\t mean \n\t median \n\t solve-quadratic \n\t add-pp \n\t subtract-pp \n\t multiply-pp \n\t power-pp \n\t sub \n\t four_function-mono \n\t singleVar-equation \n\t graph\n");
    Scanner scan = new Scanner(System.in);
    String equa = scan.nextLine();
    if (equa.equals("help")){
      System.out.println("Choose a mode by typing the word and pressing enter. Type exit in order to exit the Calculator \nExample: If you want to go into the mode PEMDAS, type PEMDAS and press enter");
      Calculator.main(args);
    }
    if (equa.equals("PEMDAS")){
      System.out.println();
      equa = scan.nextLine();
      Calculator ahh = new Calculator();
      while (!equa.equals("exit mode") && !equa.equals("exit")){
        if (equa.equals("help")){
          System.out.println("Format: Separate doubles and operation symbols with a space. For trigonometry functions it should be formated like this: sin( [degrees] ) \nNote: Answers are rounded to the thousandth place \nExamples: \n\t4 ^ 1 + 5 * 4 - ( 4 * cos( 60 ) ) / 2 \n\t= 23.0\n \n\tsin( 30 ) + 4 \n\t= 4.5");
        }
        else{
          try{
            ArrayList<String> input = new ArrayList<>();
            String[] temp = equa.split(" ");
            for (String arg: temp){
              input.add(arg);
            }
            double a = solve(input, ahh);
            ahh.setAns(a);
            System.out.println("= " + a);
          }catch(Exception e){
            //e.printStackTrace();
            System.out.println("\nPlease enter proper arguments. Type help for an example\n");
          }
        }
        System.out.println();
        equa = scan.nextLine();
      }
      if (equa.equals("exit mode")){
        Calculator.main(args);
      }
    }

    else if (equa.equals("mean")){
      System.out.println();
      equa = scan.nextLine();
      Calculator ahh = new Calculator();
      while (!equa.equals("exit mode") && !equa.equals("exit")){
        if (equa.equals("help")){
          System.out.println("Example: \n\t20 30 50 40 10 \n\tMean: 30\n");
          equa = scan.nextLine();

        }
        else{
          try{
            String[] aaa = equa.split(" ");
            Double[] in = new Double[aaa.length];
            for (int i = 0; i < aaa.length; i++){
              in[i] = Double.parseDouble(aaa[i]);
            }
            System.out.println("Mean: " + mean(in));
          }catch(Exception e){
          System.out.println("\nPlease enter proper arguments. Type help for an example\n");
          }
          System.out.println();
          equa = scan.nextLine();
        }
      }
      if (equa.equals("exit mode")){
        Calculator.main(args);
      }
    }

    else if (equa.equals("median")){
      System.out.println();
      equa = scan.nextLine();
      Calculator ahh = new Calculator();
      while (!equa.equals("exit mode") && !equa.equals("exit")){
        if (equa.equals("help")){
          System.out.println("Example: \n\t20 30 50 40 10 \n\tMedian: 30\n");
          equa = scan.nextLine();
        }
        else{
          try{
            String[] aaa = equa.split(" ");
            Double[] in = new Double[aaa.length];
            for (int i = 0; i < aaa.length; i++){
              in[i] = Double.parseDouble(aaa[i]);
            }
            Arrays.sort(in);
            System.out.println("Median: " + median(in));
          }catch(Exception e){
          System.out.println("\nPlease enter proper arguments. Type help for an example\n");
          }
          System.out.println();
          equa = scan.nextLine();
        }
      }
      if (equa.equals("exit mode")){
        Calculator.main(args);
      }
    }

    else if (equa.equals("solve-quadratic")){
      System.out.println();
      equa = scan.nextLine();
      while (!equa.equals("exit mode") && !equa.equals("exit")){
        if (equa.equals("help")){
          System.out.println("Example: \n\tx^(2) - 1 = 0\n\tReal Roots Found: [1, -1]\n");
          equa = scan.nextLine();
        }
        else{
          try{
            System.out.println("Real Roots Found: " + Arrays.toString(Quad(equa)));
          }catch (ArithmeticException e){
            System.out.println(e.getMessage());
          }catch (Exception e){
            System.out.println("\nPlease enter proper arguments. Type help for an example\n");
          }
          System.out.println();
          equa = scan.nextLine();
        }
      }
      if (equa.equals("exit mode")){
        Calculator.main(args);
      }
    }

    else if (equa.equals("sub")){
      System.out.println();
      equa = scan.nextLine();
      while (!equa.equals("exit mode") && !equa.equals("exit")){
        if (equa.equals("help")){
          System.out.println("Example: \n\t5x^(4) - 4x + 2\t1\n\t= 11\n");
          equa = scan.nextLine();
        }
        else{
          try{
            String[] temp = equa.split("\t");
            System.out.println(Polynomial.parsePoly(temp[0]).sub(Integer.parseInt(temp[1])));
          }catch (ArithmeticException e){
            System.out.println(e.getMessage());
          }catch (Exception e){
            System.out.println("\nPlease enter proper arguments. Type help for an example\n");
          }
          System.out.println();
          equa = scan.nextLine();
        }
      }
      if (equa.equals("exit mode")){
        Calculator.main(args);
      }
    }

    else if (equa.equals("multiply-pp") || equa.equals("subtract-pp") || equa.equals("power-pp") || equa.equals("add-pp")){
      String mm = equa;
      System.out.println();
      equa = scan.nextLine();
      while (!equa.equals("exit mode") && !equa.equals("exit")){
        if (equa.equals("help")){
          System.out.println("Example: \n\t(4x^(2) - 3x)(5x + 4)\t1\n\t= 11\n");
          equa = scan.nextLine();
        }
        else{
          try{
            Polynomial ans = factor(equa, mm);
            if (ans.toString().equals("")) System.out.println("0");
            else System.out.println(ans);
          }catch (ArithmeticException e){
            System.out.println(e.getMessage());
          }catch (Exception e){
            System.out.println("\nPlease enter proper arguments. Type help for an example\n");
          }
          System.out.println();
          equa = scan.nextLine();
        }
      }
      if (equa.equals("exit mode")){
        Calculator.main(args);
      }
    }

    else if (equa.equals("four_function-mono")){
      System.out.println();
      equa = scan.nextLine();
      while (!equa.equals("exit mode") && !equa.equals("exit")){
        if (equa.equals("help")){
          System.out.println("Example: \n\t4x^(5) / 2x^(2) + 8x^(3) - 2\t1\n\t= 10x^(3) - 2\n");
          equa = scan.nextLine();
        }
        else{
          try{
            System.out.println(Polynomial.parsePoly(equa));
          }catch (Exception e){
            System.out.println("\nPlease enter proper arguments. Type help for an example\n");
          }
          System.out.println();
          equa = scan.nextLine();
        }
      }
      if (equa.equals("exit mode")){
        Calculator.main(args);
      }
    }

    else if (equa.equals("singleVar-equation")){
      System.out.println();
      equa = scan.nextLine();
      while (!equa.equals("exit mode") && !equa.equals("exit")){
        if (equa.equals("help")){
          System.out.println("Example: \n\t4x - 4 = 8\n\tx = 4\n");
          equa = scan.nextLine();
        }
        else{
          try{
            System.out.println(Polynomial.singleVar(equa));
          }catch (ArithmeticException e){
            System.out.println(e.getMessage());
          }catch (Exception e){
            System.out.println("\nPlease enter proper arguments. Type help for an example\n");
          }
          System.out.println();
          equa = scan.nextLine();
        }
      }
      if (equa.equals("exit mode")){
        Calculator.main(args);
      }
    }

    else if (equa.equals("graph")){
      System.out.println();
      equa = scan.nextLine();
      while (!equa.equals("exit mode") && !equa.equals("exit")){
        if (equa.equals("help")){
          System.out.println("Example: \n\t5x^(4) - 4x + 2\tno \nor \t4x + 4\t-10\t10\t-10\t10\tyes\n");
          equa = scan.nextLine();
        }
        else{
          try{
            String[] temp = equa.split("\t");
            if (temp.length == 2){
              //System.out.println(temp[0]);
              boolean x = false;
              if (temp[1].equals("yes")) x = true;
              Graph g = new Graph(Polynomial.parsePoly(temp[0]), x);
              System.out.println(g);
            }
            else if(temp.length == 6){
              boolean x = false;
              if (temp[5].equals("yes")) x = true;
              //System.out.println(temp[5]);
              Graph g = new Graph(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), Integer.parseInt(temp[4]), Polynomial.parsePoly(temp[0]), x);
              System.out.println(g);
            }
            else System.out.println("\nPlease enter proper arguments. Type help for an example\n");
          }catch (Exception e){
            System.out.println("\nPlease enter proper arguments. Type help for an example\n");
          }
          System.out.println();
          equa = scan.nextLine();
        }
      }
      if (equa.equals("exit mode")){
        Calculator.main(args);
      }
    }

    else if (!equa.equals("exit") && !equa.equals("help") && !equa.equals("menu")){
      System.out.println("Please choose a valid mode");
      Calculator.main(args);
    }

    /*String msg = "\n\n\n\nIf you would like to use this calculator, please use the following format:  \n\n"
                + "PEMDAS \" [expression(no variable)] \" \n \t ex: java Calculator PEMDAS \"4 ^ 2 + 5 * 3 - 6 / 2 \" \n \t Can be used with trig fxns: sin/cos/tan: \n \t ex: PEMDAS \"4 ^ 2 + 5sin( 30 )\" \n\n"
                + "mean [num1] [num2]... \n \t ex: java Calculator mean 10 20 30 40 92 \n\n"
                + "median [num1] [num2]... \n \t ex: java Calculator median 39 48 49 37 28 12\n\n"
                + "solve-quadratic \" [quadratic equation] \" \n \t ex: java Calculator solve-quadratic \"x^(2) - 1 = 0\" \n\n"
                + "add-pp \"([polynomial])([polynomial]) \" \n \t ex: java Calculator add-pp \"(4x^(2) - 3x)(5x + 4)\" \n\n"
                + "subtract-pp \"([polynomial])([polynomial])\" \n \t ex: java Calculator subtract-pp \"(4x^(2) - 3x)(5x^(3) + 4x)\" \n\n"
                + "multiply-pp \"([polynomial])([polynomial])\" \n \t ex: java Calculator multiply-pp \"(x - 1)(x + 1)\" \n\n"
                + "power-pp \"([polynomial])([int])\" \n \t ex: java Calculator power-pp \"(x - 1)(3)\" \n\n"
                + "four_function-mono \"[expression(with one variable)]\" \n \t ex: java Calculator four_function-mono \"4x^(2) * 5x^(3) - 3x^(6) + 4x\" \n\n"
                + "sub \"[polynomial/monomial]\" [int] \n \t ex: java Calculator sub \"4x^(2) + 3\" 8 \n\n"
                + "singleVar-equation \"[single variable equation]\" \n \t ex: java Calculator singleVar-equation \"4x - 5 = 2\" \n\n"
                + "graph \"[polynomial/monomial(with one variable)]\" OR graph \"[polyomial/monomial(with one variable)]\" [int(X min)] [int(X max)] [int(Y min)] [int(Y max)] \n \t ex: java Calculator graph \" 4x^(2) + 2x + 2 \" -5 5 -10 10 \n\n";
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
      else if (args.length > 0 && args[0].equals("singleVar-equation")){
        System.out.println(Polynomial.singleVar(args[1]));
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
    }catch(ArithmeticException e){
      System.out.println(e);
    }catch(Exception e) {
      System.out.println(msg);
      //e.printStackTrace();
    }*/
  }
}
