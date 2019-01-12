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

  private static String drwl(int len){
    String a = "-";
    String l = String.join("", Collections.nCopies(len, a));
    return l;
  }

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
    return a;
  }

  public static Monomial factorM(String input, String method){
    String[] out = input.split("_");
    Monomial a = Monomial.parseMono(out[0].substring(1));
    Monomial b = Monomial.parseMono(out[1].substring(0, out[1].length() - 1));
    if (method.equals("multiply-mm")) {
      System.out.println("  " + a + "\n*(" + b + ")\n" + drwl(out[0].length()+ 2));
      return a.multiply(b);
    }
    else if (method.equals("divide-mm")) {
        System.out.println("  " + a + "\n/(" + b + ")\n" + drwl(out[0].length()+ 2));
        return a.divide(b);
    }
    else if (method.equals("subtract-mm")) {
        System.out.println("  " + a + "\n-(" + b + ")\n" + drwl(out[0].length()+ 2));
        return a.subtract(b);
    }
    else if (method.equals("add-mm")) {
        System.out.println("  " + a + "\n+(" + b + ")\n" + drwl(out[0].length() + 2));
        return a.add(b);
    }
    else return b;
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
    String m1 = "\n\nTo write a polynomial/monomial (nomial for both), please note the following: \n\nVariables to a degree are encapsulated in parenthesis: x^(5)\nPlease place quotation marks with one space from either end around your nomial(s) \" [nomial(s)] \" \nTo have multiply nomials, please place a '_' in between them: \" [nomial]_[nomial] \"";
    String p = "\n\nsolve-quadratic \" [quadratic polynomial] \" \n\nadd-pp \" [polynomial]_[polynomial] \" \n\nsubtract-pp \" [polynomial]_[polynomial] \" \n\nmultiply-pp \" [polynomial]_[polynomial] \" \n\n";
    String needTest = "linear \" [polynomial] \"";
    String m = "add-mm \" [monomial]_[monomial]  \" \n\nsubtract-mm \" [monomial]_[monomial]  \" \n\nmultiply-mm \" [monomial]_[monomial] ] \" \n\ndivide-mm \" [monomial]_[monomial] \"";
    String msg = m1 + "\n\nIf you would like to use this calculator, please use the following format:  \n\n\nmean [num1] [num2]...  \n\nmedian [num1] [num2]...\n\nsub-p \" [polynomial] \" [int] \n\nsub-m \" [monomial] \" [int]\n\ngraph \" [nomial] \" [xbound1] [xbound2] [ybound1] [ybound2] or graph \" [nomial] \"\n\n" + needTest + p + m;
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
        for (int i = 1; i < args.length; i++){
          input.add(args[i]);
        }
        System.out.println("Mean: " + mean(input));
      }
      else if (args.length > 0 && args[0].equals("median")) {
        for (int i = 1; i < args.length; i++) {
          input.add(args[i]);
        }
        System.out.println("Median: " + median(input));
      }
      else if (args.length > 0 && args[0].equals("solve-quadratic")){
        System.out.println("Real Roots Found: " + Arrays.toString(Quad(args[1])));
      }
      else if (args.length > 0 && args[0].equals("sub-p")){
        System.out.println(Polynomial.parsePoly(args[1]).sub(Integer.parseInt(args[2])));
      }
      else if (args.length > 0 && args[0].equals("sub-m")){
        System.out.println(Monomial.parseMono(args[1]).sub(Integer.parseInt(args[2])));
      }
      else if (args.length > 0 && (args[0].equals("multiply-pp") ||
                                   args[0].equals("add-pp") ||
                                   args[0].equals("subtract-pp"))){
        System.out.println(factor(args[1],args[0]));
      }
      else if (args.length > 0 && (args[0].equals("multiply-mm") ||
                                   args[0].equals("add-mm") ||
                                   args[0].equals("subtract-mm") ||
                                   args[0].equals("divide-mm"))){
        System.out.println(factorM(args[1],args[0]));
                                   }
      else if (args.length > 0 && args[0].equals("linear")){
        //System.out.println(args[1]);
        System.out.println(Polynomial.linear(args[1]));
      }
      else if ((args.length == 2 || args.length == 6) && args[0].equals("graph")){
        if (args.length == 2) {
          //System.out.println(args[1]);
          Graph g1 = new Graph(Polynomial.parsePoly(args[1]));
          System.out.println(g1);
        }
        if (args.length == 6) {
          Graph g1 = new Graph(Integer.parseInt(args[2]), Integer.parseInt(args[3]),
                               Integer.parseInt(args[4]), Integer.parseInt(args[5]), Polynomial.parsePoly(args[1]));
          System.out.println(g1);
        }
        else System.out.println("Too many bounds/Too little, refer to instructions!");
      }
      else System.out.println(msg);
    }catch(Exception e) {
      System.out.println(msg);
      //e.printStackTrace();
    }
  }
}
