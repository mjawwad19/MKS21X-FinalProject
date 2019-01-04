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
  //graphs the equation given on default bounds
  public Graph() {
    setGraph(startX, endX, startY, endY);
  }
  //graphs the equation given and the bounds specified
  public Graph (int minX, int maxX, int minY, int maxY) {
    setGraph(minX, maxX, minY, maxY);

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
    Graph g1 = new Graph(-10, 15, -8, 8);
    System.out.println(g1);
    Graph g2 = new Graph(5, 10, 2, 4);
    System.out.println(g2); //shouldn't have axis
    Graph g3 = new Graph (5, 10, -2 , 4);
    System.out.println(g3); // should have 1 axis near bottom
    Graph g4 = new Graph (5, 10, -2, -4);
    System.out.println(g4); //shouldn't have axis
    Graph g5 = new Graph (-5, -10, 2, 4);
    System.out.println(g5); //none
    Graph g6 = new Graph(-10, -15, -8, -10);
    System.out.println(g6); // none
  }
}
