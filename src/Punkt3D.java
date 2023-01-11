public class Punkt3D implements Comparable<Punkt3D> {
    private double x;
    private double y;
    private double z;
    public Punkt3D(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public double GetX(){ return x; }
    public double GetY(){ return y; }
    public double GetZ(){ return z; }
    public void SetX(double x){ this.x = x; }
    public void SetY(double y){ this.y = y; }
    public void SetZ(double z){ this.z = z; }
    public void Print(){ System.out.println("x: " + x + " y: " + y + " z: " + z); }
    public void Move(Wektor3D vector){
        x = x + vector.GetX();
        y = y + vector.GetY();
        z = z + vector.GetZ();
    }
    public Punkt2D Project(double c, double d) {
        double X, Y;
        if(c - z == 0)
        {
            throw new ArithmeticException();
        }
        else
        {
            X = ((c * x) - (z * d)) / (c - z);
            Y = (c * y) / (c - z);
        }
        return new Punkt2D(X, Y);
    }
    @Override
    public int compareTo(Punkt3D point)
    {
        if(this.equals(point))
            return 0;
        if (x < point.x || (x == point.x && y < point.y) || (x == point.x && y < point.y && z < point.z) )
            return -1;
        return 1;
    }
    public boolean equals(Object point)
    {
        if(!(point instanceof Punkt3D))
            return false;
        return ((Punkt3D) point).x == x && ((Punkt3D) point).y == y && ((Punkt3D) point).z == z;
    }


}