public class Graph{
  private char[][] grid;
  private Polynomial equation;
  private int startX = -20;
  private int startY = -20;
  private int endX = 20;
  private int endY = 20;
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
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
        if (grid[i][j] == 'O') out += ANSI_RED + "O" + ANSI_RESET + " ";
        else out += grid[i][j] + " ";
      }
    }
    return out + "\n\nX Bounds: " + startX + " to " + endX +
                        "\nY Bounds: " + startY + " to " + endY;
  }
  public static void main(String[] args) {
    Polynomial a = new Polynomial();
    a.add(new Monomial(new Fraction(1,1), 'x', 2));
    System.out.println(ANSI_RED+ a + ANSI_RESET); //x^2
    Graph g1 = new Graph(-10, 15, -20, 20, a);
    System.out.println(g1);

  }
}
