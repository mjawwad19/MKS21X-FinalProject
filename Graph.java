public class Graph{
  private char[][] grid;
  private Polynomial equation;
  private int startX = -20;
  private int startY = -20;
  private int endX = 20;
  private int endY = 20;
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String WHITE = "\033[107m\033[30m";
  /**
    * creates empty graph grid (2D array) [helper]
    * @param minX is the left bound of the graph/grid
    * @param maxX is the right bound of the graph/grid
    * @param minY is the bottom bound of the graph/grid
    * @param maxY is the top bound of the graph/grid
    */
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
  /**
    * plots the equation into the graph/grid
    * @param Eq is the equation that will be plotted
    */
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
  /**
    * Initializes a graph with the plotting of the equation given, using default bounds
    * @param eq is the equation to be graphed within default Bounds
    */
  public Graph(Polynomial eq) {
    setGraph(startX, endX, startY, endY);
    setEq(eq);
    System.out.println(WHITE + eq);
  }
  /**
    * Initializes a graph with the plotting of the equation given, using user given bounds
    * @param eq is the equation to be graphed within default Bounds
    * @param minX is the left bound of the graph/grid
    * @param maxX is the right bound of the graph/grid
    * @param minY is the bottom bound of the graph/grid
    * @param maxY is the top bound of the graph/grid
    */
  public Graph (int minX, int maxX, int minY, int maxY, Polynomial eq) {
    startX = minX;
    startY = minY;
    endX = maxX;
    endY = maxY;
    setGraph(startX, endX, startY, endY);
    setEq(eq);
    System.out.println(WHITE + ANSI_RED + eq + ANSI_RESET + WHITE);
  }
  /**
    * Returns a String (viewable in terminal) of the graph, changing the background color to white to easily see the equation, which is plotted in red.
    * @return a String of the graph.
    */
  public String toString() {
    System.out.println("\033[107m\033[30m");
    String out = "";
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (j == 0) out += "\n";
        if (grid[i][j] == 'O') out += ANSI_RED + "O" + ANSI_RESET + WHITE + " ";
        else out += grid[i][j] + " ";
      }
    }
    return out + "\n\nX Bounds: " + startX + " to " + endX +
                        "\nY Bounds: " + startY + " to " + endY + ANSI_RESET;
  }
  /*public static void main(String[] args) {
    Polynomial a = new Polynomial();
    a.add(new Monomial(new Fraction(1,1), 'x', 2));
    Graph g1 = new Graph(-10, 15, -20, 20, a);
    System.out.println(g1);

  }*/
}
