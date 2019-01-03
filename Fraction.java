public class Fraction{
  private int numerator,denominator;

  public Fraction(int num, int deno){
    numerator = num;
    denominator = deno;
    simplify();
  }

  public int getNum(){
    return numerator;
  }

  public int getDeno(){
    return denominator;
  }

  private void simplify(){
    int g = gcf(getNum(),getDeno());
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

  public String toString(){
    if (numerator == 0) return "0";
    if (denominator == 1) return "" + getNum();
    return "" + (double)getNum()/getDeno();
  }

  public static void main(String[]args){
    Fraction test = new Fraction(30,15);
    System.out.println(test);
  }

}
