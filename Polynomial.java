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

  public Polynomial add(Monomial other){
    for (int i = 0; i < monos.size(); i++){
      if (monos.get(i).likeTerms(other)){
        monos.get(i).setCoef(monos.get(i).getCoef().add(other.getCoef()));
        return new Polynomial(monos);
      }
    }
    monos.add(other);
    return new Polynomial(monos);
  }

  public Polynomial subtract(Monomial other){
    return add(other.multiply(new Monomial(new Fraction(-1, 1), other.getVar(), 0)));
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

  public static void main(String[] args) {
    Polynomial a = new Polynomial();
    a = a.add(new Monomial(new Fraction(4, 1), 'x', 2));
    a = a.add(new Monomial(new Fraction(-6, 1), 'x', 5));
    a = a.add(new Monomial(new Fraction(9, 1), 'x', 5));
    a = a.subtract(new Monomial(new Fraction(3, 1), 'x', 5));
    System.out.println(a);
  }
}
