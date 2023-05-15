package nodes;

public abstract class Node implements GameObject{
    private double x;
    private double y;
    private double xChange;
    private double yChange;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getxChange() {
        return xChange;
    }

    public void setxChange(double xChange) {
        this.xChange = xChange;
    }

    public double getyChange() {
        return yChange;
    }

    public void setyChange(double yChange) {
        this.yChange = yChange;
    }

    public void update(){
        x += xChange;
        y += yChange;
    }
}
