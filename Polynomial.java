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

  public ArrayList<Monomial> getMonos(){
    return monos;
  }

  public void add(Monomial other){
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

  public void add(Polynomial other){
    for (Monomial term: other.getMonos()){
      this.add(term);
    }
  }

  public void subtract(Monomial other){
    this.add(other.multiply(new Monomial(new Fraction(-1, 1), other.getVar(), 0)));
  }

  public void subtract(Polynomial other){
    Polynomial opp = new Polynomial();
    for (Monomial t: other.getMonos()) {
      opp.add(t.multiply(new Monomial(new Fraction(-1, 1), 'x', 0)));
    }
    this.add(opp);
  }

  public void multiply(Monomial other){
    for (Monomial term: monos){
      if (term.getVar() == other.getVar()){
        term.setCoef(term.getCoef().multiply(other.getCoef()));
        term.setDeg(term.getDeg() + other.getDeg());
      }
    }
  }

  public Polynomial multiply(Polynomial other){
    Polynomial out = new Polynomial();
    for (Monomial term: monos){
      for (Monomial mon: other.getMonos()){
        out.add(term.multiply(mon));
      }
    }
    return out;
  }

  public void divide(Monomial other){
    for (Monomial term: monos){
      if (term.getVar() == other.getVar()){
        term.setCoef(term.getCoef().divide(other.getCoef()));
        term.setDeg(term.getDeg() - other.getDeg());
      }
    }
  }

  public Fraction sub(int v) {
    Fraction ans = new Fraction(0, 1);
    for (Monomial term: monos) {
      ans = ans.add(term.sub(v));
    }
    return ans;
  }

  public String toString(){
    if (getMonos().size() == 1) return getMonos().get(0).toString();
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
      if (term.getDeg() > 2) throw new IllegalArgumentException("The polynomial is not a quadratic, the highest degree of " + term.getVar() + " is " + term.getDeg());
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
    for (int i = 0; i < input.size(); i++){
      if (input.get(i).equals("*")){
        input.set(i, "" + Monomial.parseMono(input.get(i-1)).multiply(Monomial.parseMono(input.get(i + 1))));
        input.remove(i - 1);
        input.remove(i);
      }
      else if(input.get(i).equals("/")){
        input.set(i, "" + Monomial.parseMono(input.get(i-1)).divide(Monomial.parseMono(input.get(i + 1))));
        input.remove(i - 1);
        input.remove(i);
      }
    }
    if (input.size() > 0) ans.add(Monomial.parseMono(input.get(0)));
    for (int i = 1; i < input.size(); i += 2){
      if (input.get(i).equals("+")){
        ans.add(Monomial.parseMono(input.get(i+1)));
      }
      if (input.get(i).equals("-")){
        ans.subtract(Monomial.parseMono(input.get(i+1)));
      }
    }
    return ans;
  }


  public static String linear(String input){
    String[] arg = input.split(" = ");
    Polynomial left = Polynomial.parsePoly(arg[0]);
    Polynomial right = Polynomial.parsePoly(arg[1]);
    left.subtract(right);
    char vari = ' ';
    Fraction ans = new Fraction(0);
    Fraction c = new Fraction(0);
    Fraction d = new Fraction(1);
    for (Monomial term: left.getMonos()){
      if (term.getDeg() == 0) c = term.getCoef();
      if (term.getDeg() == 1){
        d = term.getCoef();
        vari = term.getVar();
      }
    }
    return vari + " = " + ans.subtract(c).divide(d);
  }

  public Polynomial power(int p){
    if (p == 0) {
      ArrayList<Monomial> ans = new ArrayList<>();
      ans.add(Monomial.parseMono("1"));
      return new Polynomial(ans);
    }
    Polynomial temp = Polynomial.parsePoly(this.toString());
    for(int i = 1; i < p; i++){
      temp = temp.multiply(this);
    }
    return temp;
  }

  public static void main(String[] args) {
    /*
    Polynomial a = new Polynomial();
    a.add(new Monomial(new Fraction(4, 1), 'x', 2));
    a.add(new Monomial(new Fraction(-6, 1), 'x', 5));
    a.add(new Monomial(new Fraction(9, 1), 'x', 5));
    a.subtract(new Monomial(new Fraction(6, 1), 'x', 5));
    System.out.println(a); //4x^2 - 3x^5
    System.out.println(a.sub(2)); //16/1 - 96/1 = -80/1 or - 80
    System.out.println();
    Polynomial b = new Polynomial();
    b.add(new Monomial(new Fraction(1, 1), 'x', 2));
    b.add(new Monomial(new Fraction(-1, 1), 'x', 1));
    b.add(new Monomial(new Fraction(-6, 1), 'x', 0));
    System.out.println(b.solveQuad()[0]); //3
    System.out.println(b.solveQuad()[1]); //-2
    System.out.println(b); //x^2 - x - 6
    System.out.println(b.sub(3)); //0
    System.out.println();
    b.multiply(new Monomial(new Fraction(4, 1), 'x', 2));
    System.out.println(b); //4x^2 - 4x - 24
    b.divide(new Monomial(new Fraction(4, 1), 'x', 2));
    System.out.println(b); // x^2 - x - 6
    b.add(a);
    System.out.println(b); // 5x^2 - x - 6 -3x^5
    b.subtract(a);
    System.out.println(b);
    System.out.println(a);
    System.out.println(parsePoly("5x^(2) - 4x + 5"));
    System.out.println(a.multiply(b)); /*(4x^2 - 3x^5) (x^2 - x - 6) =
    4x^4 - 4x^3 - 24x^2 - 3x^7 - 3x^6 - 18x^5*/
    Polynomial c = Polynomial.parsePoly("-3x");
    System.out.println(c);
  }
}
