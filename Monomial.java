public class Monomial{
  private Fraction coef;
  private char var;
  private int degree;

  public Monomial(Fraction co, char v, int deg){
    coef = co;
    var = v;
    degree = deg;
  }

  public Fraction getCoef(){
    return coef;
  }

  public char getVar(){
    return var;
  }

  public int getDeg(){
    return degree;
  }

  public String toString(){
    if ((getCoef() + "").equals("0")) return "0";
    if (getDeg() == 0) return "" + getCoef(); //will have to be updated when we have multivariable monomials.
    return "" + getCoef() + getVar() + "^" + "(" + getDeg() + ")";
  }

  public void setCoef(Fraction co) {
    coef = co;
  }

  public void setVar(char v) {
    var = v;
  }

  public void setDeg(int deg) {
    degree = deg;
  }

  public boolean likeTerms(Monomial other) {
    if (getVar() == other.getVar()) return true;
    return false;
  }

  public boolean likeDeg(Monomial other) {
    if (getDeg() == other.getDeg()) return true;
    return false;
  }
  /*Below is a WIP for add(Monomial) because of certain issues
  1) if you add two monomials together and they aren't compatible, then they
  would create a Polynomial.
  In that case shouldn't this be moved to the Polynomial class?
  2) A proposed solution to this: Make this add a boolean that
  modifies the Monomial calling the function if possible */
  public Monomial add(Monomial other) {
    //Polynomial end = new Polynomial();
    Monomial out = new Monomial(new Fraction(0, 1), 'x', 0); //default
    if (likeTerms(other) && likeDeg(other)) {
      out.setCoef(getCoef().add(other.getCoef()));
      out.setVar(getVar());
      out.setDeg(getDeg());
      //end.add(out);
    }
    /*else {
      end.add(this);
      end.add(other);
    }*/
    return out;
  }

  public Monomial subtract(Monomial other) {
    Monomial out = new Monomial(new Fraction(0, 1), 'x', 0);
    if (likeTerms(other) && likeDeg(other)) {
      out.setCoef(getCoef().subtract(other.getCoef()));
      out.setVar(getVar());
      out.setDeg(getDeg());
    }
    return out;
  }

  public Monomial multiply(Monomial other) {
    Monomial out = new Monomial(new Fraction(0,1), 'x', 0);
    if (likeTerms(other)) {
      out.setCoef(getCoef().multiply(other.getCoef()));
      out.setVar(getVar());
      out.setDeg(getDeg() + other.getDeg());
    }
    //else we're going to need to allow Monomials to have multiple variables
    return out;
  }

  public Monomial divide(Monomial other) {
    Monomial out = new Monomial(new Fraction(0,1), 'x', 0);
    if (likeTerms(other)) {
      out.setCoef(getCoef().divide(other.getCoef()));
      out.setVar(getVar());
      out.setDeg(getDeg() - other.getDeg());
    }
    //else we're going to need to allow Monomials to have multiple variables
    return out;
  }

  public double sub(int var) {
    return  ((double) getCoef().getNum() / getCoef().getDeno()) * (Math.pow(var, getDeg()));
  }

  public static void main(String[] args) {
    Monomial a = new Monomial(new Fraction(4, 1), 'x', 2);
    Monomial b = new Monomial(new Fraction(0, 4), 'w', 5);
    Monomial c = new Monomial(new Fraction(3,5), 'q', 0);
    Monomial d = new Monomial(new Fraction(0,1), 'x', 1);
    Monomial e = new Monomial(new Fraction(5,2), 'x', 2);
    System.out.println(a);
    System.out.println(b);
    System.out.println(c);
    System.out.println(a.likeTerms(b)); //false;
    System.out.println(a.likeTerms(d)); //true;
    System.out.println(a.likeTerms(e)); //true;
    System.out.println(a.likeDeg(e)); //true;
    System.out.println(a.add(e)); // 6.5 x^2
    System.out.println(a.subtract(e)); // 1.5 x^2
    System.out.println(a.multiply(e)); // 10x^4
    System.out.println(a.divide(e)); // 1.6
    System.out.println(a.sub(2)); // 4 * 2^2 = 16.0
    System.out.println(b.sub(2));//0
    System.out.println(c.sub(3)); //0.6
    System.out.println(e.sub(3)); //2.5* 3^2 = 22.5
  }
}
