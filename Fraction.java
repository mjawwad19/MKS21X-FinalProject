import java.io.*;
import java.util.*;
public class Fraction{
  private int numerator,denominator;

  /**
   * Initializes the numerator and denominator and simplifies the Fraction
   * @param num the numerator (an integer)
   * @param deno the denominator (an integer)
   */
  public Fraction(int num, int deno){
    numerator = num;
    denominator = deno;
    simplify();
  }

  /**
   * Initializes the Fraction given the value of it as a Double
   * @param doub the value of the Fraction
   */
  public Fraction(double doub){
    String dub = "" + doub;
    String[] d = dub.split("\\.");
    denominator = (int)Math.pow(10, d[1].length());
    numerator = Integer.parseInt(d[0]) * denominator + Integer.parseInt(d[1]);
    simplify();
  }

  /**
   * Returns the value of the numerator
   * @return the numerator of the fraction
   */
  public int getNum(){
    return numerator;
  }

  /**
   * Returns the value of the denominator
   * @return the denominator of the fraction
   */
  public int getDeno(){
    return denominator;
  }

  /**
   * Simplifies the Fraction to lowest terms
   * @return the simplified Fraction
   */
  private void simplify(){
    int g = gcf(Math.abs(getNum()),Math.abs(getDeno()));
    numerator /= g;
    denominator /= g;
  }

  /**
   * Finds the greatest common factor of two integers
   * @param a the first integer
   * @param b the second integer
   * @return the greatest common factor
   */
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

  /**
   * Adds two Fractions
   * @param other a second Fraction
   * @return A new fraction that is the sum of the original two
   */
  public Fraction add(Fraction other){
    return new Fraction(getNum() * other.getDeno() + getDeno() * other.getNum(), getDeno() * other.getDeno());
  }

  /**
   * Subtracts two Fractions
   * @param other a second Fraction
   * @return A new fraction that is the difference of the original two
   */
  public Fraction subtract(Fraction other){
    return new Fraction(getNum() * other.getDeno() - getDeno() * other.getNum(), getDeno() * other.getDeno());
  }

  /**
   * Multiplies two Fractions
   * @param other a second Fraction
   * @return A new fraction that is the product of the original two
   */
  public Fraction multiply(Fraction other){
    return new Fraction(getNum() * other.getNum(), getDeno() * other.getDeno());
  }

  /**
   * Adds two Fractions
   * @param other a second Fraction
   * @return A new fraction that is the quotient of the original two
   * @throws IllegalArgumentException if other is equal to 0 (division by 0)
   */
  public Fraction divide(Fraction other){
    if (other.getNum() == 0) throw new IllegalArgumentException("cannot divide " + this + " by 0");
    return new Fraction(getNum() * other.getDeno(), getDeno() * other.getNum());
  }

  /**
   * Raises the Fraction to a certain power
   * @param pow the power to be raised
   * @return A new fraction that is the original raised to the power of pow
   */
  public Fraction power(double pow){
    return new Fraction(Math.round(Math.pow(getNum(), pow) / Math.pow(getDeno(), pow) * 1000.0) / 1000.0);
  }

  /**
   * Converts the Fraction into a String
   * @return A String of the value of the Fraction (as a Double)
   */
  public String toString(){
    if (numerator == 0) return "0";
    if (denominator == 1) return "" + getNum();
    return "" + (double)getNum()/getDeno();
  }

  /**
    * rounds a Fraction class instance to int. (Will be helpful for graphing)[helper]
    * @return the nearest int.
    */
  public int round() {
    return (int) Double.parseDouble(this.toString());
  }

/*  public static void main(String[]args){
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
  }*/

}
