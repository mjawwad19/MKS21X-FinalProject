import java.io.*;
public class Fraction{
  private int numerator,denominator;

  public Fraction(int num, int deno){
    numerator = num;
    denominator = deno;
    simplify();
  }

  public Fraction(double doub){
    String dub = "" + doub;
    String[] d = dub.split("\\.");
    denominator = (int)Math.pow(10, d[0].length());
    numerator = Integer.parseInt(d[0]) * denominator + Integer.parseInt(d[0]);
    simplify();
  }

  public int getNum(){
    return numerator;
  }

  public int getDeno(){
    return denominator;
  }

  private void simplify(){
    int g = gcf(Math.abs(getNum()),Math.abs(getDeno()));
    numerator /= g;
    denominator /= g;
  }

  private static int gcf(int a, int b){
    int dend, dsor, r;
    if (a == 0 || b == 0) return 1;
    if (a > b){
      dend = a;
      dsor = b;
      r = a % b;
    }
    else{
      dend = b;
      dsor = a;
      r = b % a;
    }
    while (r > 0){
      dend = dsor;
      dsor = r;
      r = dend % dsor;
    }
    return dsor;
  }

  public Fraction add(Fraction other){
    return new Fraction(getNum() * other.getDeno() + getDeno() * other.getNum(), getDeno() * other.getDeno());
  }

  public Fraction subtract(Fraction other){
    return new Fraction(getNum() * other.getDeno() - getDeno() * other.getNum(), getDeno() * other.getDeno());
  }

  public Fraction multiply(Fraction other){
    return new Fraction(getNum() * other.getNum(), getDeno() * other.getDeno());
  }

  public Fraction divide(Fraction other){
    if (other.getNum() == 0) throw new IllegalArgumentException("cannot divide" + this + "by 0");
    return new Fraction(getNum() * other.getDeno(), getDeno() * other.getNum());
  }

  public Fraction power(double pow){
    return new Fraction((int)Math.pow(getNum(), pow), (int)Math.pow(getDeno(), pow));
  }

  public String toString(){
    if (numerator == 0) return "0";
    if (denominator == 1) return "" + getNum();
    return "" + (double)getNum()/getDeno();
  }

  public static void main(String[]args){
    Fraction a = new Fraction(30,15);
    Fraction b = new Fraction(1,2);
    System.out.println(a + " : " + a.getNum() +"/" + a.getDeno());
    System.out.println(b + " : " + b.getNum() +"/" + b.getDeno());
    Fraction c = a.add(b);
    System.out.println(c + " : " + c.getNum() +"/" + c.getDeno());
    Fraction d = c.subtract(b);
    System.out.println(d + " : " + d.getNum() +"/" + d.getDeno());
    Fraction e = a.multiply(c);
    System.out.println(e + " : " + e.getNum() +"/" + e.getDeno());
    Fraction f = b.divide(c);
    System.out.println(f + " : " + f.getNum() +"/" + f.getDeno());
    Fraction g = a.power(2);
    System.out.println(g + " : " + g.getNum() +"/" + g.getDeno());
    Fraction h = new Fraction(4.5);
    System.out.println(h + " : " + h.getNum() +"/" + h.getDeno());
  }

}
