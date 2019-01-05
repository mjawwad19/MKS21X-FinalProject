import java.util.*;
public class Polynomial{
  private ArrayList<Monomial> monos;

  public Polynomial(){
    monos = new ArrayList<>();
  }

  public Polynomial(ArrayList<Monomial> m){
    monos = new ArrayList<>();
    for (Monomial term: m){
      monos.add(term);
    }
  }

  public void add(Monomial other){
    boolean added = false;
    for (int i = 0; i < monos.size(); i++){
      if (monos.get(i).likeTerms(other)){
        monos.get(i).setCoef(monos.get(i).getCoef().add(other.getCoef()));
        added = true;
      }
    }
    if (!added) monos.add(other);
  }

  public void subtract(Monomial other){
    add(other.multiply(new Monomial(new Fraction(-1, 1), other.getVar(), 0)));
  }

  public String toString(){
    String ans = "";
    for (Monomial term: monos){
      if (term.toString().equals("0"));
      else if (Double.parseDouble(term.getCoef().toString()) < 0) ans += " - " + term.multiply(new Monomial(new Fraction(-1, 1), term.getVar(), 0));
      else if (monos.indexOf(term) != 0) ans += " + " + term;
      else ans += term;
    }
    return ans;
  }

  public Fraction[] solveQuad(){
    Fraction a = new Fraction(0, 1);
    Fraction b = new Fraction(0, 1);
    Fraction c = new Fraction(0, 1);
    for (Monomial term: monos){
      if (term.getDeg() == 2) a = term.getCoef();
      if (term.getDeg() == 1) b = term.getCoef();
      if (term.getDeg() == 0) c = term.getCoef();
    }
    Fraction[] ans = new Fraction[2];
    Fraction discrim = b.power(2).subtract(a.multiply(c).multiply(new Fraction(4, 1)));
    if (Double.parseDouble(discrim.toString()) < 0) throw new IllegalArgumentException("There are no real roots, the discriminant is " + discrim);
    ans[0] = ((new Fraction(0, 1)).subtract(b).add(discrim.power(0.5))).divide(a.multiply(new Fraction(2,1)));
    ans[1] = ((new Fraction(0, 1)).subtract(b).subtract(discrim.power(0.5))).divide(a.multiply(new Fraction(2,1)));
    return ans;
  }

  public static void main(String[] args) {
    Polynomial a = new Polynomial();
    a.add(new Monomial(new Fraction(4, 1), 'x', 2));
    a.add(new Monomial(new Fraction(-6, 1), 'x', 5));
    a.add(new Monomial(new Fraction(9, 1), 'x', 5));
    a.subtract(new Monomial(new Fraction(3, 1), 'x', 5));
    System.out.println(a);
    Polynomial b = new Polynomial();
    b.add(new Monomial(new Fraction(1, 1), 'x', 2));
    b.add(new Monomial(new Fraction(-1, 1), 'x', 1));
    b.add(new Monomial(new Fraction(-6, 1), 'x', 0));
    System.out.println(b.solveQuad()[0]);
    System.out.println(b.solveQuad()[1]);
    System.out.println(b);
  }
}
