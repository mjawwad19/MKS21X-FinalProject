import java.util.*;
public class Polynomial{
  private ArrayList<Monomial> monos;

  /**
   * Initializes monos as an empty ArrayList
   */
  public Polynomial(){
    monos = new ArrayList<>();
  }

  /**
   * Initializes Polynomial with a given Monomial ArrayList
   * @param m the ArrayList of Monomials to be added to the Polynomial
   */
  public Polynomial(ArrayList<Monomial> m){
    monos = new ArrayList<>();
    for (Monomial term: m){
      monos.add(term);
    }
  }

  /**
   * Returns an ArrayList of all the Monomials in the Polynomial
   * @return an ArrayList of the Monomials in the Polynomial
   */
  public ArrayList<Monomial> getMonos(){
    return monos;
  }

  /**
   * Adds a Monomial to the Polynomial
   * @param other the Monomial to be added
   */
  public void add(Monomial other){
    if (getMonos().size() > 0 && getMonos().get(0).getVar() != other.getVar()) throw new ArithmeticException("Polynomial can only have one variable");
    boolean added = false;
    Monomial to = new Monomial(other.getCoef(), other.getVar(), other.getDeg());
    for (int i = 0; i < monos.size(); i++){
      if (monos.get(i).likeTerms(other)){
        monos.get(i).add(to);
        added = true;
      }
    }
    if (!added) monos.add(to);
  }

  /**
   * Adds a Polynomial to the Polynomial
   * @param other the Polynomial to be added
   */
  public void add(Polynomial other){
    for (Monomial term: other.getMonos()){
      this.add(term);
    }
  }

  /**
   * Subtracts a Monomial from the Polynomial
   * @param other the Monomial to be subtracted
   * @throws ArithmeticException if the Monomial has a different variable than the Polynomial
   */
  public void subtract(Monomial other){
    this.add(other.multiply(new Monomial(new Fraction(-1, 1), other.getVar(), 0)));
  }

  /**
   * Subtracts a Polynomial from the Polynomial
   * @param other the Polynomial to be subtracted
   */
  public void subtract(Polynomial other){
    Polynomial opp = new Polynomial();
    for (Monomial t: other.getMonos()) {
      opp.subtract(t);
    }
    this.add(opp);
  }

  /**
   * Multiplies the Polynomial by a Monomial
   * @param other the Monomial to be multiplied
   */
  public void multiply(Monomial other){
    for (Monomial term: monos){
      if (term.getVar() == other.getVar()){
        term.setCoef(term.getCoef().multiply(other.getCoef()));
        term.setDeg(term.getDeg() + other.getDeg());
      }
    }
  }

  /**
   * Multiplies the Polynomial by a Polynomial
   * @param other the Polynomial to be multiplied
   * @return a new polynomial which is the product of the invoker and other.
   */
  public Polynomial multiply(Polynomial other){
    Polynomial out = new Polynomial();
    for (Monomial term: monos){
      for (Monomial mon: other.getMonos()){
        out.add(term.multiply(mon));
      }
    }
    return out;
  }

  /**
   * Divides the Polynomial by a Monomial
   * @param other the Monomial divisor
   */
  public void divide(Monomial other){
    for (Monomial term: monos){
      if (term.getVar() == other.getVar()){
        term.setCoef(term.getCoef().divide(other.getCoef()));
        term.setDeg(term.getDeg() - other.getDeg());
      }
    }
  }

  /**
   * Substitutes the variable in the Polynomial with a given integer
   * @param v the value to substitute the variable with
   * @return the evalulation after subsituting the variable with v
   */
  public Fraction sub(int v) {
    Fraction ans = new Fraction(0, 1);
    for (Monomial term: monos) {
      ans = ans.add(term.sub(v));
    }
    return ans;
  }
  /**
    * Creates the derivative of the caller as a new Polynomial
    * Still a WIP since not all derivative rules have been layed out.
    * Will probably be a secret mode
    * @return first derivate of this as new Polynomial
    */
  public Polynomial derive(){
    Polynomial d = new Polynomial();
    for (Monomial term: monos) {
      d.add(term.derive());
    }
    return d;
  }

  /**
   * Converts the Polynomial to a String
   * @return A String of the Polynomial
   */
  public String toString(){
    if (getMonos().size() == 1) return getMonos().get(0).toString();
    String ans = "";

    for (Monomial term: monos){
      if (term.toString().equals("0"));
      if (getMonos().indexOf(term) == 0) ans += term;
      else if (Double.parseDouble(term.getCoef().toString()) < 0) ans += " - " + term.multiply(new Monomial(new Fraction(-1, 1), term.getVar(), 0));
      else if (monos.indexOf(term) != 0) ans += " + " + term;
      else ans += term;
    }
    return ans;
  }

  /**
   * Solves the quadratic Polynomial expression assuming it is equal to 0
   * @return An array of the two roots of the quadratic expression
   * @throws ArithmeticException if the Polynomial is not a quadratic or if there are no real roots to the quadratic
   */
  public Fraction[] solveQuad(){
    Fraction a = new Fraction(0, 1);
    Fraction b = new Fraction(0, 1);
    Fraction c = new Fraction(0, 1);
    for (Monomial term: getMonos()){
      if (term.getDeg() > 2) throw new ArithmeticException("The polynomial is not a quadratic, the highest degree of " + term.getVar() + " is " + term.getDeg());
      if (term.getDeg() == 2) a = term.getCoef();
      if (term.getDeg() == 1) b = term.getCoef();
      if (term.getDeg() == 0) c = term.getCoef();
    }
    Fraction[] ans = new Fraction[2];
    Fraction discrim = b.power(2).subtract(a.multiply(c).multiply(new Fraction(4, 1)));
    if (Double.parseDouble(discrim.toString()) < 0) throw new ArithmeticException("There are no real roots, the discriminant is " + discrim);
    ans[0] = ((new Fraction(0, 1)).subtract(b).add(discrim.power(0.5))).divide(a.multiply(new Fraction(2,1)));
    ans[1] = ((new Fraction(0, 1)).subtract(b).subtract(discrim.power(0.5))).divide(a.multiply(new Fraction(2,1)));
    return ans;
  }

  /**
   * Converts a String into a Polynomial and simplifies it
   * @param poly a String of the Polynomial
   * @return A simplified Polynomial of the one in the String
   */
  public static Polynomial parsePoly(String poly){
    Polynomial ans = new Polynomial();
    String[] p = poly.split(" ");
    if (p.length == 0) return ans;
    if (p.length == 1){
      Monomial a = Monomial.parseMono(p[0]);
      ans.add(a);
      return ans;
    }
    ArrayList<String> input = new ArrayList<>();
    for (String term: p){
      input.add(term);
    }
    for (int i = 0; i < input.size(); i++){
      if (input.get(i).equals("*")){
        input.set(i, "" + Monomial.parseMono(input.get(i-1)).multiply(Monomial.parseMono(input.get(i + 1))));
        input.remove(i - 1);
        input.remove(i);
      }
      if(input.get(i).equals("/")){
        input.set(i, "" + Monomial.parseMono(input.get(i-1)).divide(Monomial.parseMono(input.get(i + 1))));
        input.remove(i - 1);
        input.remove(i);
      }
    }
    if (input.size() > 0) ans.add(Monomial.parseMono(input.get(0)));
    for (int i = 1; i < input.size(); i += 2){
      String a = (input.get(i + 1));
      if (input.get(i).equals("+")){
        ans.add(Monomial.parseMono(input.get(i+1)));
      }
      else if (input.get(i).equals("-")){
        ans.subtract(Monomial.parseMono(input.get(i+1)));
      }
      else if ((i != 1 && input.get(i - 1) != "+" && input.get(i - 1) != "-") || (i != input.size() - 1 && input.get(i + 1) != "+" && input.get(i + 1) != "-")) throw new IllegalArgumentException();
    }
    return ans;
  }

  /**
   * Solves a single variable equation that has only one variable
   * @param input the single variable Polynomial equation as a String
   * @return a String that contains the answer of the equation and the variable that corresponds to imt
   * @throws ArithmeticException if the variable can be anything or if there is no solution
   */
  public static String singleVar(String input){
    String[] arg = input.split(" = ");
    Polynomial left = Polynomial.parsePoly(arg[0]);
    Polynomial right = Polynomial.parsePoly(arg[1]);
    for (Monomial term: left.getMonos()){
      for (Monomial t: right.getMonos()){
        if (term.getVar() != t.getVar() && (term.getDeg() != 0 || t.getDeg() != 0)) throw new ArithmeticException("The linear equation cannot have more than one variable");
      }
    }
    left.subtract(right);
    if (left.toString().equals(Monomial.parseMono("0").toString())) throw new ArithmeticException("The variable can be any number");
    boolean hasVar = false;
    for (int i = 0; i < left.toString().length(); i++){
      if (Character.isLetter(left.toString().charAt(i))) hasVar = true;
    }
    if (!hasVar) throw new ArithmeticException("There is no solution");
    char vari = ' ';
    Fraction ans = new Fraction(0);
    Fraction c = new Fraction(0);
    Fraction d = new Fraction(1);
    for (Monomial term: left.getMonos()){
      if (term.getDeg() > 1 || term.getDeg() < 0) throw new ArithmeticException("The variable cannot have a degree higher than 1 or less than -1, or else the equation is not linear");
      if (term.getDeg() == 0) c = term.getCoef();
      if (term.getDeg() == 1){
        d = term.getCoef();
        vari = term.getVar();
      }
    }
    return vari + " = " + ans.subtract(c).divide(d);
  }

  /**
   * Raises the Polynomial to a certain nonnegative integer power
   * @param p the integer power to which the Polynomial will be raised to
   * @throws ArithmeticException if the power is a negative number
   * @return a new polynomial which is equivalent to the invoker to power p.
   */
  public Polynomial power(int p){
    if (p == 0) {
      ArrayList<Monomial> ans = new ArrayList<>();
      ans.add(Monomial.parseMono("1"));
      return new Polynomial(ans);
    }
    if (p < 0) throw new ArithmeticException("The Polynomial cannot be raised to a negative power (functionality not supported)");
    Polynomial temp = Polynomial.parsePoly(this.toString());
    for(int i = 1; i < p; i++){
      temp = temp.multiply(this);
    }
    return temp;
  }

  /**
    * Creates the antiderivative of the caller as a new Polynomial
    * Still a WIP since not all integral rules have been layed out.
    * Will probably be a secret mode
    * @return antiderivate/integal of this as new Polynomial
    */
  public Polynomial integrate(){
    Polynomial i = new Polynomial();
    for (Monomial term: monos) {
      Monomial in = term.integrate();
      i.add(in);
    }
    return i;
  }
}
