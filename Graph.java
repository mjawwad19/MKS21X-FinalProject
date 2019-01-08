public class Graph{
  private char[][] grid;
  private Polynomial equation;
  private int startX = -20;
  private int startY = -20;
  private int endX = 20;
  private int endY = 20;
  // creates empty graph grid (2D array) [helper]
  private void setGraph(int minX, int maxX, int minY, int maxY) {
    int c = Math.abs(maxX- minX) + 1;
    int r = Math.abs(maxY - minY)+ 1;
    if (minX > 0 && maxX > 0 || minX <0 && maxX <0) {
      c = Math.abs(maxX- minX);
    }
    if (minY > 0 && maxY > 0 || minY <0 && maxY <0) {
      r = Math.abs(maxY - minY);
    }
    grid = new char[r][c];
    for (int i = 0; i < r; i++) {
      for (int j= 0; j < c; j++) {
        if (i == r - Math.abs(minY) -1 || j == c - Math.abs(maxX) -1) grid[i][j] = '#';
        else grid[i][j] = '_';
      }
    }
  }


  //graphs the equation [helper]
  private void setEq(Polynomial Eq) {
  for (int i = startX; i <= endX; i++) {
    int y = Eq.sub(i).round();
    //System.out.println(y); may be used later on for Table command
    int x = i + Math.abs(startX);
    if (y >= startY && y <= endY) {
      y = Math.abs(endY - y);
      grid[y][x] = 'O';
    //  System.out.println("" + x + " " + y); debug
    }
  }
}
  //graphs the equation given on default bounds
  public Graph(Polynomial eq) {
    setGraph(startX, endX, startY, endY);
    setEq(eq);
  }

  //graphs the equation given and the bounds specified
  public Graph (int minX, int maxX, int minY, int maxY, Polynomial eq) {
    startX = minX;
    startY = minY;
    endX = maxX;
    endY = maxY;
    setGraph(startX, endX, startY, endY);
    setEq(eq);
  }
  //output for user to see
  public String toString() {
    String out = "";
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (j == 0) out += "\n";
        out += grid[i][j] + " ";
      }
    }
    return out;
  }
  public static void main(String[] args) {
    Polynomial a = new Polynomial();
    a.add(new Monomial(new Fraction(-1,1), 'x', 3));
    a.add(new Monomial(new Fraction(1,1), 'x', 0));
    System.out.println("y = " + a); //-x^3 +1
    Graph g1 = new Graph(-10, 15, -20, 20, a);

    System.out.println(g1);
    /*Graph g2 = new Graph(5, 10, 2, 4);
    System.out.println(g2); //shouldn't have axis
    Graph g3 = new Graph (5, 10, -2 , 4);
    System.out.println(g3); // should have 1 axis near bottom
    Graph g4 = new Graph (5, 10, -2, -4);
    System.out.println(g4); //shouldn't have axis
    Graph g5 = new Graph (-5, -10, 2, 4);
    System.out.println(g5); //none
    Graph g6 = new Graph(-10, -15, -8, -10);
    System.out.println(g6); // none*/
  }
}
