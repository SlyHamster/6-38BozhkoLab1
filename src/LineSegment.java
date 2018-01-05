public class LineSegment {
    public Point startPoint;
    public Point finishPoint;
    public int cost;

    public LineSegment(Point start, Point finish){
        startPoint = start;
        finishPoint = finish;
        cost = -1;
    }

    public void setCost(int i){
        this.cost = i;
    }





}
