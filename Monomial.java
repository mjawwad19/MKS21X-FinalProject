public class Monomial{
  private Fraction coef;
  private char var;
  private int degree;

  public Monomial(Fraction co, char v, int deg){
    coef = co;
    var = v;
    degree = deg;
  }
//--------get methods--------
  public Fraction getCoef(){
    return coef;
  }

  public char getVar(){
    return var;
  }

  public int getDeg(){
    return degree;
  }
//---------set methods--------
  public void setCoef(Fraction co) {
    coef = co;
  }

  public void setVar(char v) {
    var = v;
  }

  public void setDeg(int deg) {
    degree = deg;
  }
//--------toString---------

  private String coString(){
    if ((getCoef() + "").equals("1")) return "";
    else return "" + getCoef();
  }

  private String degString(){
    if ((getDeg() == 1)) return "" + getVar();
    else return "" + getVar() + "^" + "(" + getDeg() + ")";
  }

  public String toString(){
    if ((getCoef() + "").equals("0")) return "0";
    if (getDeg() == 0) return "" + getCoef(); //will have to be updated when we have multivariable monomials.
    else return coString() + degString();
  }
/*method likeTerms compares Monomials and returns a boolean if they are combinable
*@param other is the Monomial being compared. */
  public boolean likeTerms(Monomial other) {
    if (getVar() == other.getVar() && getDeg() == other.getDeg()) return true;
    return false;
  }
/*method add adds monomials that are combinable together into a single monomial.
Note this add feature does not have functionality when the bases or degrees do
not match as that would result in a polynomial answer.
*@param other is the Monomial being added.*/
  public Monomial add(Monomial other) {
    //Polynomial end = new Polynomial();
    Monomial out = new Monomial(new Fraction(0, 1), 'x', 0); //default
    if (likeTerms(other)) {
      out.setCoef(getCoef().add(other.getCoef()));
      out.setVar(getVar());
      out.setDeg(getDeg());
    }
    return out;
  }
/*method subtract subtracts a monomial only if it is compatible with the monomial
calling the method, thus resulting in a single monomial.
Note this subtract feature does not have functionality when the bases or degrees
do not match as that would result in a polynomial answer.
*@param other is the Monomial being subtracted.*/
  public Monomial subtract(Monomial other) {
    Monomial out = new Monomial(new Fraction(0, 1), 'x', 0);
    if (likeTerms(other)) {
      out.setCoef(getCoef().subtract(other.getCoef()));
      out.setVar(getVar());
      out.setDeg(getDeg());
    }
    return out;
  }
/*method multiply takes two monomials and multiplies them into a single monomial.
Note this multiply feature is currently bound to monomials of the same base---
if we have time we will alow monomials to be multivariable.
*@param other is the monomial being multiplied with.*/
  public Monomial multiply(Monomial other) {
    Monomial out = new Monomial(new Fraction(0,1), 'x', 0);
    if (getVar() == other.getVar()) {
      out.setCoef(getCoef().multiply(other.getCoef()));
      out.setVar(getVar());
      out.setDeg(getDeg() + other.getDeg());
    }
    //else we're going to need to allow Monomials to have multiple variables
    return out;
  }
/*method divide takes two monomials and divides them into a single monomial.
Note this divide feature does not work when the bases are of different variable.
To be honest, graphing calculators usually have only x and y as variables so...
*@param other is the monomial that we divide with.*/
  public Monomial divide(Monomial other) {
    Monomial out = new Monomial(new Fraction(0,1), 'x', 0);
    if (getVar() == other.getVar()) {
      out.setCoef(getCoef().divide(other.getCoef()));
      out.setVar(getVar());
      out.setDeg(getDeg() - other.getDeg());
    }
    //else we're going to need to allow Monomials to have multiple variables
    return out;
  }
/*method sub (short for subsitute) subsitutes the variable and performs algebra to
yield a fraction result aka a tangible number. Although graphing calculators don't
have this concept viewable for the user, this will be very important for graphing
via plotting purposes.
*@param v is the integer we are subsituting our variable for.*/
  public Fraction sub(int v) {
    return  getCoef().multiply(new Fraction((int)Math.pow(v, getDeg()), 1));
  }

  public static void main(String[] args) {
    Monomial a = new Monomial(new Fraction(4, 1), 'x', 2);
    Monomial b = new Monomial(new Fraction(0, 4), 'w', 5);
    Monomial c = new Monomial(new Fraction(3,5), 'q', 0);
    Monomial d = new Monomial(new Fraction(0,1), 'x', 1);
    Monomial e = new Monomial(new Fraction(5,2), 'x', 2);
    System.out.println(a); //4x^2
    System.out.println(b); //0
    System.out.println(c); //0.6
    System.out.println(a.likeTerms(b)); //false;
    System.out.println(a.likeTerms(d)); //false;
    System.out.println(a.likeTerms(e)); //true;
    System.out.println(a.add(e)); // 6.5 x^2
    System.out.println(a.subtract(e)); // 1.5 x^2
    System.out.println(a.multiply(e)); // 10x^4
    System.out.println(a.divide(e)); // 1.6
    System.out.println(a.sub(2)); // 4 * 2^2 = 16
    System.out.println(b.sub(2));//0
    System.out.println(c.sub(3)); //0.6
    System.out.println(e.sub(3)); //2.5* 3^2 = 22.5
  }
}
