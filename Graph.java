import java.util.*;

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
  private ArrayList<ArrayList<Integer>> List(Polynomial eq) {
  ArrayList<ArrayList<Integer>> list = new ArrayList<>() ;
   for (int i = startX; i <= endX; i++){
     ArrayList<Integer> inner = new ArrayList<>();
     for (int j = 0; j < 2; j++){
       if (j == 0) inner.add(i);
       if (j == 1) inner.add(eq.sub(i).round());
     }
     list.add(inner);
   }
   //System.out.println(list);
   return list;
 }
  /*
   * Converts a double ArrayList into an XY table (vertical)
   * @param l is the arraylist to be converted
   * @return a string that looks like a vertical Table
   */
  public String ALToString(ArrayList<ArrayList<Integer>> l) {
    String out = "| X | Y |\n|___|___|\n";
    for (int i = 0; i < l.size(); i++) {
      for (int j = 0; j < 1; j++) {
        String x = "|";
        String y = "|";
        //x vals
        int x0 = l.get(i).get(j);
        int y0 = l.get(i).get(j+1);
        if (x0 < 10 && x0 >= 0) x += " " +x0 + " ";
        else if (x0 < 100 ||x0>-10 && x0 < 0) x += "" + x0 + " ";
        else if (x0 < 1000 || x0<-9 && x0 >-100) x += x0;
        //y vals
        if (y0 < 10 && y0 >= 0)  y += " " +y0 + " |";
        else if (y0 < 100 || y0 > -10 && y0 < 0) y += " " + y0 + "|";
        else if (y0 < 1000 || y0 <-9 -100 && y0 < -9) y +=  y0 + "|";
        out += x + y + "\n";
      }
    }
    out += "---------";
    //System.out.println(x);
    return out;
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
  public Graph(Polynomial eq, boolean l) {
    setGraph(startX, endX, startY, endY);
    setEq(eq);
    if (l)   System.out.println(List(eq));
    System.out.println(WHITE + eq);
    if (l) System.out.println(ALToString(List(eq)));
  }
  /**
    * Initializes a graph with the plotting of the equation given, using user given bounds
    * @param eq is the equation to be graphed within default Bounds
    * @param minX is the left bound of the graph/grid
    * @param maxX is the right bound of the graph/grid
    * @param minY is the bottom bound of the graph/grid
    * @param maxY is the top bound of the graph/grid
    */
  public Graph (int minX, int maxX, int minY, int maxY, Polynomial eq, boolean l) {
    startX = minX;
    startY = minY;
    endX = maxX;
    endY = maxY;
    setGraph(startX, endX, startY, endY);
    setEq(eq);
    if (l) System.out.println(List(eq));
    System.out.println(WHITE + ANSI_RED + eq + ANSI_RESET + WHITE);
    if (l) System.out.println(ALToString(List(eq)));
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
  public static void main(String[] args) {
    Polynomial a = new Polynomial();
    a.add(new Monomial(new Fraction(1,1), 'x', 2));
    Graph g1 = new Graph(-10, 15, -20, 20, a, true);
    System.out.println(g1);
    //int b = g1.endX - g1.startX;
    //System.out.println(b); //shows 25
  }
}
