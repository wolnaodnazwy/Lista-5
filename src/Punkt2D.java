public class Punkt2D implements Comparable<Punkt2D> {
    private double x;
    private double y;
    public Punkt2D(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double GetX(){ return x; }
    public double GetY(){ return y; }
    public void Print(){ System.out.println("x: " + x + " y: " + y);}
    @Override
    public int compareTo(Punkt2D point)
    {
        if(this.equals(point))
            return 0;
        if (x < point.x || (x == point.x && y < point.y))
            return -1;
        return 1;
    }
    @Override
    public boolean equals(Object point)
    {
        if(!(point instanceof Punkt2D))
            return false;
        return ((Punkt2D) point).x == x && ((Punkt2D) point).y == y;
    }
}