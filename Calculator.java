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
   * @param ahh aids in getting calculator methods like answers
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
   * @param ahh aids in getting calculator methods like answers
   * @return the answer of the expression
   */
  public static double solve(List<String> input, Calculator ahh){
    for (int i = 0; i < input.size(); i++){
      if (input.get(i).equals("sin(") || input.get(i).equals("cos(") || input.get(i).equals("cos(")){
        String mode = input.get(i);
        boolean swap = false;
        for (int j = i; j < input.size(); j++){
          if (input.get(j).equals(")") && swap == false){
            asolve(input.subList(i + 1, j), ahh);
            input.remove(i);
            if (mode.equals("sin(")) input.set(i, "" + Math.sin(Math.toRadians(Double.parseDouble(input.get(i)))));
            else if (mode.equals("cos(")) input.set(i, "" + Math.cos(Math.toRadians(Double.parseDouble(input.get(i)))));
            else if (mode.equals("tan(")) input.set(i, "" + Math.tan(Math.toRadians(Double.parseDouble(input.get(i)))));
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

  private static String summSym(String f, String n, Polynomial eq,String inc) {
    String sym =   " " + n +" \n___\n\\\n/" + "  =  " + eq.toString() +
    "\n---\n" +  eq.getMonos().get(0).getVar() + "=" + f  + ", increment by: " + inc + "\n" + drwl(20) + "\n";
    return sym;
  }

  public static double summ(int f, int n, Polynomial eq, int k) {
    double sum = 0.0;
    if (k == 0) {
      for (int i = f; i <= n; i++) {
        sum+=  Double.parseDouble(eq.sub(i).toString());
      }
    }
    else {
      for (int i = f; i <= n; i+= k) {
        sum+=  Double.parseDouble(eq.sub(i).toString());
      }
    }
    return sum;
  }

  /**
   * Performs either addition, subtraction, multiplication, or power function on two Polynomials
   * @param input String consisting of the two Polynomials
   * @param method the operation to be done on the Polynomials
   * @return The resulting Polynomial after the operation
   */
  public static Polynomial factor(String input, String method){
    String[] out = input.split("\\)\\(");
    if (out[0].charAt(0) != '(' || out[1].charAt(out[1].length() - 1) != ')') throw new IllegalArgumentException();
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
      System.out.print("= ");
      a = a.power(Integer.parseInt(out[1].substring(0, out[1].length() - 1)));
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
  /*  System.out.println(summSym());
  Polynomial n = new Polynomial();
  n.add(new Monomial(new Fraction(2, 1), 'x', 1));
  System.out.println(n);
  System.out.println(summ(2,5,n));*/
    System.out.println("Please choose an input mode: \n\t PEMDAS \n\t mean \n\t median \n\t solve-quadratic \n\t add-pp \n\t subtract-pp \n\t multiply-pp \n\t power-pp \n\t sub \n\t four_function-mono \n\t singleVar-equation \n\t graph\n\t summation\n");
    Scanner scan = new Scanner(System.in);
    String equa = scan.nextLine();
    if (equa.equals("help")){
      System.out.println("Choose a mode by typing the word and pressing enter. Type exit in order to exit the Calculator \nExample: If you want to go into the mode PEMDAS, type PEMDAS and press enter.\n");
      Calculator.main(args);
    }

    else if (equa.equals("PEMDAS")){
      System.out.println();
      equa = scan.nextLine();
      Calculator ahh = new Calculator();
      while (!equa.equals("exit mode") && !equa.equals("exit")){
        if (equa.equals("help")){
          System.out.println("Format: Separate doubles and operation symbols with a space. Type ans in order to reference the previous answer and use it again.\nNote: Answers are rounded to the thousandth place \n\n List of symbols: \t* :multiplication\n\t\t\t/ :division \n\t\t\t+ :addition\n\t\t\t- :subtraction\n\t\t\t^ :exponent\n\t\t\t( [stuff] ) :parentheses\n\t\t\tsin/cos/tan( [angle in degrees] ) :trig functions\nExamples: \n\t4 ^ 1 + 5 * 4 - ( 4 * cos( 60 ) ) / 2 \n\t= 23.0\n \n\tsin( 30 ) + 4 \n\t= 4.5\nType \"exit mode\" in order to choose another mode.\nType exit to close the Calculator.\n");
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
            System.out.println("\nPlease enter proper input values. Type help for example and format\n");
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
          System.out.println("Format: Separate each double of the list with a space. If you want to reference the previous answer and use it as an input, type ans. \nExample: \n\t20 30 50 40 10 \n\tMean: 30\nType \"exit mode\" in order to choose another mode.\nType exit to close the Calculator.\n");
          equa = scan.nextLine();

        }
        else{
          try{
            String[] aaa = equa.split(" ");
            Double[] in = new Double[aaa.length];
            for (int i = 0; i < aaa.length; i++){
              if (aaa[i].equals("ans")) in[i] = ahh.getAns();
              else in[i] = Double.parseDouble(aaa[i]);
            }
            double a = mean(in);
            ahh.setAns(a);
            System.out.println("Mean: " + a);
          }catch(Exception e){
            System.out.println("\nPlease enter proper input values. Type help for example and format\n");
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
          System.out.println("Format: Separate each double of the list with a space. If you want to reference previous ans and use it as an input, tpye ans. \n Example: \n\t20 30 50 40 10 \n\tMedian: 30\nType \"exit mode\" in order to choose another mode.\nType exit to close the Calculator.\n");
          equa = scan.nextLine();
        }
        else{
          try{
            String[] aaa = equa.split(" ");
            Double[] in = new Double[aaa.length];
            for (int i = 0; i < aaa.length; i++){
              if (aaa[i].equals("ans")) in[i] = ahh.getAns();
              else in[i] = Double.parseDouble(aaa[i]);
            }
            Arrays.sort(in);
            double a = median(in);
            ahh.setAns(a);
            System.out.println("Median: " + a);
          }catch(Exception e){
            System.out.println("\nPlease enter proper input values. Type help for example and format\n");
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
          System.out.println("Format: Enter the quadratic equation with spaces separating each term and symbol.\n\tThe terms with a variable should be formated like this: CV^(e) \n\t\twhere C = coefficient, \n\t\t      V = variable, \n\t\t      and e = integer exponent. \n\tExample: \n\tx^(2) - 1 = 0\n\tReal Roots Found: [1, -1]\nType \"exit mode\" in order to choose another mode.\nType exit to close the Calculator.\n");
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
          System.out.println("Format: Enter the polynomial expression and the value of the variable, separated by a tab. \n\tNote: this mode is limited to polynomials with one variable\nExample: \n\t5x^(4) - 4x + 2 - 6x^(2) + 6x\t1\n\t= 3\nType \"exit mode\" in order to choose another mode.\nType exit to close the Calculator.\n");
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
      Polynomial answer = Polynomial.parsePoly("0");
      while (!equa.equals("exit mode") && !equa.equals("exit")){
        if (equa.equals("help") && mm.equals("add-pp")){
          System.out.println("Format: Enter the two polynomial expressions in the following format:\n\t(poly1)(poly2)\n\n\tNote: If you want to use the previous answer as one of the polynomial inputs, type ans.\nExample: \n\t(4x^(2) - 3x)(5x + 4)\n\t= 4x^(2) + 2x + 4\nType \"exit mode\" in order to choose another mode.\nType exit to close the Calculator.\n");
          equa = scan.nextLine();
        }
        else if (equa.equals("help") && mm.equals("subtract-pp")){
          System.out.println("Format: Enter the two polynomial expressions in the following format:\n\t(poly1)(poly2)\n\n\tNote: If you want to use the previous answer as one of the polynomial inputs, type ans.\nExample: \n\t(4x^(2) - 3x)(5x + 4)\n\t= 4x^(2) - 8x - 4\nType \"exit mode\" in order to choose another mode.\nType exit to close the Calculator.\n");
          equa = scan.nextLine();
        }
        else if (equa.equals("help") && mm.equals("multiply-pp")){
          System.out.println("Format: Enter the two polynomial expressions in the following format:\n\t(poly1)(poly2)\n\n\tNote: If you want to use the previous answer as one of the polynomial inputs, type ans.\nExample: \n\t(4x^(2) - 3x)(5x + 4)\n\t= 20x^(3) + x^(2) - 12x\nType \"exit mode\" in order to choose another mode.\nType exit to close the Calculator.\n");
          equa = scan.nextLine();
        }
        else if (equa.equals("help") && mm.equals("power-pp")){
          System.out.println("Format: Enter the expression and integer exponent in the following format:\n\t(poly1)(int)\n\n\tNote: If you want to use the previous answer as one of the polynomial inputs, type ans.\nExample: \n\t(x - 1)(3)\n\t= x^(3) - 3x^(2) + 3x - 1\nType \"exit mode\" in order to choose another mode.\nType exit to close the Calculator.\n");
          equa = scan.nextLine();
        }
        else{
          try{
            if (equa.contains("ans")){
              for (int i = 0; i < equa.length() - 3; i++){
                if (equa.substring(i, i+3).equals("ans")) equa = equa.substring(0,i) + answer + equa.substring(i + 3);
              }
            }
            Polynomial ans = factor(equa, mm);
            answer = ans;
            if (ans.toString().equals("")) System.out.print("0");
            else System.out.print(ans);
            System.out.println();
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
          System.out.println("Format: Enter the monomial terms separated by spaces. \n\tNote: All terms must contain the same variable and parentheses are not supported. \nExample: \n\t4x^(5) / 2x^(2) + 8x^(3) - 2\n\t= 10x^(3) - 2\nType \"exit mode\" in order to choose another mode.\nType exit to close the Calculator.\n");
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
          System.out.println("Format: Enter a polynomial equation, with the terms and symbols separated by spaces.\nExample: \n\t4x - 4 = 8\n\tx = 3\nType \"exit mode\" in order to choose another mode.\nType exit to close the Calculator.\n");
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
          System.out.println("Format: Enter the expression in terms of the variable x. \n\tIf you want to decide the dimensions of the graph, use the format: \n\t\t[expression] [x-min] [x-max] [y-min] [y-max] yes\n\tElse, use the format: \n\t\t[expresion] no\nExample: \n\t5x^(4) - 4x + 2\tno \nor \t4x + 4\t-10\t10\t-10\t10\tyes\nType \"exit mode\" in order to choose another mode.\nType exit to close the Calculator.\n");
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
    else if (equa.equals("summation")){
      System.out.println();
      equa = scan.nextLine();
      while (!equa.equals("exit mode") && !equa.equals("exit")){
        if (equa.equals("help")){
          System.out.println("Format: [f]\t[n]\t[poly]      [increment(optional)]\n\tThe arguments are separated by tabs. \n\tIntegers f and n and Polynomial poly correspond to the following diagram: \n\t n\n\t___\n\t\\\n\t/  = poly\n\t---\n\tx = f (Note: x is the variable in poly and poly is limited to one variable. \n\t\tDefault increment is 1)\nExample: \n\t2\t5\t2x\n\t= 28.0\nOR: \t2\t5\t2x\t2\n\t= 12.0\nType \"exit mode\" in order to choose another mode.\nType exit to close the Calculator.\n");
          equa = scan.nextLine();
        }
        else{
          try{
            String[] temp = equa.split("\t");
            if (temp.length == 3) {
              System.out.println("\n" + summSym(temp[0],temp[1], Polynomial.parsePoly(temp[2]), "1") +
               summ(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Polynomial.parsePoly(temp[2]), 1));
             }
             else if (temp.length == 4) {
               System.out.println("\n" + summSym(temp[0],temp[1], Polynomial.parsePoly(temp[2]), temp[3]) +
                summ(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Polynomial.parsePoly(temp[2]), Integer.parseInt(temp[3])));
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
    else if (equa.equals("derive")){
      System.out.println();
      equa = scan.nextLine();
      while (!equa.equals("exit mode") && !equa.equals("exit")){
        if (equa.equals("help")){
          System.out.println("THIS MODE IS A WORK IN PROGRESS. WE ARE NOT LIABLE. \nExample: \n\t\"5x^(4) - 4x + 2\"\n = 20x^(3) - 4\n");
          equa = scan.nextLine();
        }
        else{
          //System.out.println(args[0]);
          try{
            String[] temp = equa.split("\""); //first should be [, ______];
            //System.out.println(Arrays.toString(temp));
            System.out.println(Polynomial.parsePoly(temp[1]).derive());
          }catch (IllegalArgumentException e){
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
    else if (equa.equals("integrate")){
      System.out.println();
      equa = scan.nextLine();
      while (!equa.equals("exit mode") && !equa.equals("exit")){
        if (equa.equals("help")){
          System.out.println("THIS MODE IS A WORK IN PROGRESS. WE ARE NOT LIABLE. \nExample: \n\t\"5x^(4) - 4x + 2\"\n = x^(5) - 2x^(2) + 2x");
          equa = scan.nextLine();
        }
        else{
          //System.out.println(args[0]);
          try{
            String[] temp = equa.split("\""); //first should be [, ______];
            //System.out.println(Arrays.toString(temp));
            System.out.println(Polynomial.parsePoly(temp[1]).integrate());
          }catch (IllegalArgumentException e){
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
    else if (!equa.equals("exit") && !equa.equals("help")){
      System.out.println("Please choose a valid mode");
      Calculator.main(args);
    }
  }
}
