package boomerang.example;

import org.checkerframework.checker.units.qual.C;

public class BoomerangExample3 {
    public static void main(String[] args) {
        System.out.println("---May Alias Analysis---");
        Circle a = new Circle();            //Reference object of a is created
        Circle b= new Circle();            //Reference object of b is created
        a.radius = 1;               // a & b are not pointing to the same object reference
        b.radius = 2;
        double b1 = b.area(b.radius);
        System.out.println("Area of object b :"+b1);
        int y =a.radius;
        double a1 = a.area(y);
        System.out.println("Area of variable y :"+a1);


        System.out.println("---Must Alias Analysis---");
        Circle x = new Circle();            //Reference object of x is created
        Circle z = x;                       // z points to the same object reference as that of x
        x.radius = 1;
        z.radius = 2;
        double z1 = z.area(z.radius);
        System.out.println("Area of object z :"+z1);
        y = x.radius;
        double y1 = x.area(y);
        System.out.println("Area of variable y :"+y1);

    }

    public static class Circle {
        int radius;

        static double area(int r) {
            System.out.println("Radius : "+r);
            double ans = 3.142 * r * r;
            return ans;
        }

    }
}
