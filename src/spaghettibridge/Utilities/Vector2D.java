/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaghettibridge.Utilities;

/**
 *
 * @author Matheus Markies
 */
public class Vector2D {

    private double X;
    private double Y;

    public Vector2D(double X, double Y) {
        this.X = X;
        this.Y = Y;
    }

    public void y(double Y) {
        this.Y = Y;
    }

    public void x(double X) {
        this.X = X;
    }

    public double y() {
        return Y;
    }

    public double x() {
        return X;
    }
    
    public static Vector2D add(Vector2D a, Vector2D b) {
        Vector2D c = new Vector2D(a.x() + b.x(), a.y() + b.y());
        return c;
    }

    public static Vector2D subtract(Vector2D a, Vector2D b) {
        Vector2D c = new Vector2D(a.x() - b.x(), a.y() - b.y());
        return c;
    }

    public static Vector2D multiply(Vector2D a, Vector2D b) {
        Vector2D c = new Vector2D(a.x() * b.x(), a.y() * b.y());
        return c;
    }

    public static Vector2D divide(Vector2D a, Vector2D b) {
        Vector2D c = new Vector2D(a.x() / b.x(), a.y() / b.y());
        return c;
    }
    
    public static double distance(Vector2D a, Vector2D b) {
        return Math.sqrt((a.x() - b.x()) * (a.x() - b.x()) +  (a.y() - b.y()) * (a.y() - b.y()));
    }
    
    public static Vector2D getCenter(Vector2D a, Vector2D b) {
        Vector2D vector2D = new Vector2D(b.x() + (a.x() - b.x())/2, b.y() + (a.y() - b.y())/2);
        return vector2D;
    }
    
    public static double abs(Vector2D a) {
        return Math.sqrt(a.x() * a.x() + a.y() * a.y());
    }
    
    @Override
    public String toString() {
        return "X:" + x() + ",Y:" + y();
    }
    
    @Override
    public boolean equals(Object x) {
        if (x == null) {
            return false;
        }
        if (this.getClass() != x.getClass()) {
            return false;
        }
        Vector2D that = (Vector2D) x;
        return (this.x() == that.x() && this.y() == that.y());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.X) ^ (Double.doubleToLongBits(this.X) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.Y) ^ (Double.doubleToLongBits(this.Y) >>> 32));
        return hash;
    }
}
