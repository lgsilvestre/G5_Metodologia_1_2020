/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author leunam
 */
public class NodeCorners{
    private Point rotatePoint;
    private Point B;
    private Point C;
    private Point D;
    private Point translVector;
    private double width = 0;
    private double height = 0;
    private double degrees = 0;
    
    public NodeCorners(double rotatePX, double rotatePY){
        this.rotatePoint = new Point(rotatePX, -rotatePY);
        updatePoints();
    }
    
    public void rotatePoints(double degrees){
        this.degrees = Math.toRadians(degrees);
        /*Vector de traslación para llevar el punto de rotación al origen y trasladar
          los otros puntos*/
        translVector = new Point(-rotatePoint.X, -rotatePoint.Y);
        rotatePoint = new Point(0, 0);
       
        
        /*Aplica el vector de traslación  a los puntos*/
        B = new Point(B.X + translVector.X, B.Y + translVector.Y);
        C = new Point(C.X + translVector.X, C.Y + translVector.Y);
        D = new Point(D.X + translVector.X, D.Y + translVector.Y);

        
        /*Aplica la rotación de los puntos respecto al origen*/
        B = new Point(B.X*Math.cos(this.degrees)-B.Y*Math.sin(this.degrees), B.X*Math.sin(this.degrees)+B.Y*Math.cos(this.degrees));
        C = new Point(C.X*Math.cos(this.degrees)-C.Y*Math.sin(this.degrees), C.X*Math.sin(this.degrees)+C.Y*Math.cos(this.degrees));
        D = new Point(D.X*Math.cos(this.degrees)-D.Y*Math.sin(this.degrees), D.X*Math.sin(this.degrees)+D.Y*Math.cos(this.degrees));

        
        /*Devuelve los puntos a su posición original*/
        translVector = new Point(-translVector.X, -translVector.Y);
        rotatePoint = new Point(translVector.X, translVector.Y);
        B = new Point(B.X + translVector.X, B.Y + translVector.Y);
        C = new Point(C.X + translVector.X, C.Y + translVector.Y);
        D = new Point(D.X + translVector.X, D.Y + translVector.Y);
        
    }
    
    public void translatePoints(double X, double Y){
        rotatePoint = new Point(X, -Y);
        updatePoints();
    }
    
    public void updatePoints(){
        this.B = new Point(rotatePoint.X + width, rotatePoint.Y);
        this.C = new Point(rotatePoint.X, rotatePoint.Y-height);
        this.D = new Point(rotatePoint.X+width, rotatePoint.Y-height);
    }
    
    
    public void setRotatePoint(double X, double Y){
        this.rotatePoint.X = X;
        this.rotatePoint.Y = -Y;
    }
    
    public void showPoints(){
        System.out.println("A: "+"("+rotatePoint.X+" "+rotatePoint.Y+")");
        System.out.println("B: "+"("+B.X+" "+B.Y+")");
        System.out.println("C: "+"("+C.X+" "+C.Y+")");
        System.out.println("D: "+"("+D.X+" "+D.Y+")");
    }
    
    public void setWidth(double width){
        this.width = width;
    }
    
    public void setHeight(double height){
        this.height = height;
    }

    public Point getA() {
        return rotatePoint;
    }

    public Point getB() {
        return B;
    }

    public Point getC() {
        return C;
    }

    public Point getD() {
        return D;
    }

    public void setA(Point rotatePoint) {
        this.rotatePoint = rotatePoint;
    }

    public void setB(Point B) {
        this.B = B;
    }

    public void setC(Point C) {
        this.C = C;
    }

    public void setD(Point D) {
        this.D = D;
    }
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
}
