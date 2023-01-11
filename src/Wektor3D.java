public class Wektor3D {
    private double x;
    private double y;
    private double z;

    public Wektor3D(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Wektor3D(Punkt3D point1, Punkt3D point2){
        x = point2.GetX() - point1.GetX();
        y = point2.GetY() - point1.GetY();
        z = point2.GetZ() - point1.GetZ();
    }
    public double GetX(){ return x; }
    public double GetY(){ return y; }
    public double GetZ(){ return z; }
    public Wektor3D Add(Wektor3D vector){
        double x1 = x + vector.GetX();
        double y1 = y + vector.GetY();
        double z1 = z + vector.GetZ();
        return new Wektor3D(x1, y1, z1);
    }
    public  Wektor3D Subtract(Wektor3D vector){
        double x1 = x - vector.GetX();
        double y1 = y - vector.GetY();
        double z1 = z - vector.GetZ();
        return new Wektor3D(x1, y1, z1);
    }
    public double Scalar(Wektor3D vector){
        return x * vector.x + y * vector.y + z * vector.z;
    }
    public Wektor3D CrossProduct(Wektor3D vector){
        double helpX = x;
        double helpY = y;
        double x1 = (y * vector.GetZ()) - (z * vector.GetY());
        double y1 = (z * vector.GetX()) - (helpX * vector.GetZ());
        double z1 = (helpX * vector.GetY()) - (helpY * vector.GetX());
        return new Wektor3D(x1, y1, z1);
    }
    public boolean Compare(Wektor3D vector)
    {
        return (x == vector.GetX()) && (y == vector.GetY()) && (z == vector.GetZ());
    };

    public void Print() {
        System.out.println("x: " + x + " y: " + y + " z: " + z);
    }
}