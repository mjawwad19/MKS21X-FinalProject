public class Monomial{

  private Fraction coef;
  private char var;
  private int degree;
  
  /**
  * Initializes a Monomial given a Fraction, character, and in
  * @param co is the coefficient of the Monomial
  * @param v is the variable of the Monomial
  * @param deg is the degree of the variable of the Monomial
  */
  public Monomial(Fraction co, char v, int deg){
    coef = co;
    var = v;
    degree = deg;
  }

//--------get methods--------
  /**
    * Retrieves the coefficient of a Monomial.
    * @return the coefficient of the invoking Monomial
    */
  public Fraction getCoef(){
    return coef;
  }

  /**
    * Retrieves the variable of a Monomial.
    * @return the variable of the invoking Monomial
    */
  public char getVar(){
    return var;
  }

  /**
    * Retrieves the degree of a variable in a  Monomial.
    * @return the degree of the invoking Monomial's variable
    */
  public int getDeg(){
    return degree;
  }

//---------set methods--------
  /**
    * Changes the coefficient of a Monomial.
    * @param co is the coefficient that will replace the original coefficient of the Monomial.
    */
  public void setCoef(Fraction co) {
    coef = co;
  }

  /**
    * Changes the variable in a Monomial.
    * @param v is the variable that will replace the original variable of the Monomial.
    */
  public void setVar(char v) {
    var = v;
  }

  /**
    * Changes the degree of a variable in a Monomial.
    * @param deg is the degree that will replace the original degree of the Monomial.
    */
  public void setDeg(int deg) {
    degree = deg;
  }

//--------toString---------
  /**
    * Converts the coefficient of a monomial to a String [helper]
    * @return A String of the coefficient
    */
  private String coString(){
    if ((getCoef() + "").equals("1")) return "";
    //System.out.println(getCoef());
    if ((getCoef() + "").equals("-1")) return "-";
    else return getCoef().toString();
  }

  /**
    * Converts the degree of a Monomial into a String [helper]
    * @return A String of the Monomial without it's coefficient
    */
  private String degString(){
    if ((getDeg() == 1)) return "" + getVar();
    if ((getDeg() == -1)) return "" + getVar();
    if ((getDeg() < -1)) return  "" + getVar() + "^" + "(" + getDeg()*-1 + ")";
    else return "" + getVar() + "^" + "(" + getDeg() + ")";
  }

  /**
   * Converts the Monomial into a String
   * @return A String of the Monomial
   */
  public String toString(){
    if ((getCoef() + "").equals("0")) return "0";
    if (getDeg() == 0) return "" + getCoef(); //will have to be updated when we have multivariable monomials.
    if (getDeg() < 0) return coString() + "/(" + degString() + ")";
    else return coString() + degString();
  }

  /**
    * compares Monomials and returns a boolean if they are combinable (same var and degree)
    * @param other is the Monomial being compared.
    * @return true or false
    */
  public boolean likeTerms(Monomial other) {
    if (getVar() == other.getVar() && getDeg() == other.getDeg()) return true;
    return false;
  }

  /**
    * adds monomials that are combinable together into a single monomial.
    * Note this add feature does not have functionality when the bases or degrees do not match as that would result in a polynomial answer.
    * @param other is the Monomial being added.
    * @return the Monomial that invoked it, now modified
    */
  public Monomial add(Monomial other) {
    if (this.likeTerms(other)) {
      this.setCoef(getCoef().add(other.getCoef()));
      return this;
    }
    else {
      System.out.print("Could not be added or subtracted: ");
      return new Monomial(new Fraction(-1, 1), 'x', 0);
    }
  }

  /**
  * subtracts a monomial only if it is compatible with the monomial resulting in a single monomial.
  * Note this subtract feature does not have functionality when the bases or degree do not match as that would result in a polynomial answer.
  * @param other is the Monomial being subtracted.
  * @return the Monomial that invoked it, now modified
  */
  public Monomial subtract(Monomial other) {
    return add(other.multiply(new Monomial(new Fraction(-1,1), 'x', 0)));
  }

  /**
  * takes two monomials and multiplies them into a single monomial.
  * Note this multiply feature is currently bound to monomials of the same base--_if we have time we will alow monomials to be multivariable.
  * @param other is the monomial being multiplied with.
  * @return a new Monomial which is the product of the invoking Monomial and other
  */
  public Monomial multiply(Monomial other) {
    Monomial out = new Monomial(new Fraction(0,1), getVar(), 0);
    if (getVar() == other.getVar() || getDeg() == 0 || other.getDeg() == 0) {
      out.setCoef(getCoef().multiply(other.getCoef()));
      out.setDeg(getDeg() + other.getDeg());
    }
    //else we're going to need to allow Monomials to have multiple variables
    return out;
  }

  /**
  * takes two monomials and divides them into a single monomial.
  * Note this divide feature does not work when the bases are of different variable.
  * @param other is the monomial that we divide with.
  * @return a new Monomial which is the quotient of the invoking Monomial and other
  */
  public Monomial divide(Monomial other) {
    Monomial out = new Monomial(new Fraction(0,1), getVar(), 0);
    if (getVar() == other.getVar() || other.getDeg() == 0) {
      out.setCoef(getCoef().divide(other.getCoef()));
      out.setDeg(getDeg() - other.getDeg());
    }
    //else we're going to need to allow Monomials to have multiple variables
    return out;
  }

  /**
   * subsitutes a value for the variable and evaluates expression
   * @param v is the integer we are subsituting our variable for.
   * @return the evaluated expression with the subsitution
   */
  public Fraction sub(int v) {
    return  getCoef().multiply(new Fraction((int)Math.pow(v, getDeg()), 1));
  }

  /**
  * Converts a String to a Monomial
  * @param mono is the String to be converted
  * @return a parsed Monomial from String mono
  */
  public static Monomial parseMono(String mono){
    Fraction c = new Fraction(0.0);
    char v = 'x';
    int d = 0;
    boolean added = false;
    for (int i = 0; i < mono.length(); i++){
      if (Character.isLetter(mono.charAt(i))){
        d = 1;
        if (i == 0) c = new Fraction(1);
        else if (mono.charAt(i - 1) == '-') c = new Fraction(-1);
        else c = new Fraction(Double.parseDouble(mono.substring(0,i)));
        v = mono.charAt(i);
        added = true;
        for (int j = i; j < mono.length(); j++){
          if (mono.charAt(j) == ')'){
            d = Integer.parseInt(mono.substring(i + 3, j));
            added = true;
          }
        }
      }
    }
    if (!added) c = new Fraction(Double.parseDouble(mono));
    return new Monomial(c,v,d);
  }

  /**
    * Creates the derivative of the caller as a new Monomial
    * Still a WIP since not all derivative rules have been layed out.
    * Will probably be a secret mode
    * @return first derivate of this as new Monomial
    */
  public Monomial derive() {
    Monomial prime = new Monomial(this.getCoef(), this.getVar(), this.getDeg());
    if (this.getDeg() != 0) {
      prime.setCoef(this.getCoef().multiply(new Fraction(this.getDeg(), 1)));
      prime.setDeg(this.getDeg() - 1);
    }
    else prime.setCoef(new Fraction(0,1));
    return prime;
  }

  /**
    * Creates the antiderivative of the caller as a new Monomial
    * Still a WIP since not all integral rules have been layed out.
    * Will probably be a secret mode
    * @return antiderivate/integral of this as new Monomial
    */
  public Monomial integrate() {
    Monomial in = new Monomial(this.getCoef(), this.getVar(), this.getDeg());
    if (this.getDeg() != -1) {
      in.setCoef(this.getCoef().divide(new Fraction(this.getDeg() + 1, 1)));
      in.setDeg(this.getDeg() + 1);
    }
    else {
      in.setCoef(new Fraction(0,1)); //for now 1/x has nothing, I have to figure out how to do ln x
    }
    return in;
  }
}
