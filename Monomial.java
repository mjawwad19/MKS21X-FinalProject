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
    if (getDeg() == 0) return "" + getCoef();
    return "" + getCoef() + getVar() + "^" + "(" + getDeg() + ")";
  }

  public static void main(String[] args) {
    Monomial a = new Monomial(new Fraction(4, 1), 'x', 2);
    Monomial b = new Monomial(new Fraction(0, 4), 'w', 5);
    Monomial c = new Monomial(new Fraction(3,5), 'q', 0);
    System.out.println(a);
    System.out.println(b);
    System.out.println(c);
  }
}
