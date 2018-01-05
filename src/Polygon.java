import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Polygon {
    List<Point> vertice;
    double centerX;
    double centerY;
    //Map<Long, Long> centerPolygon;


    public Polygon(){
        vertice = new ArrayList<>();

       // listPoints = new ArrayList<>();
       // vertice = new HashMap<Long, Long>();
        //centerX = 0;
        //centerY = 0;
//        listPoints = points;
//        centerPolygon = center;
    }

    public void addCenter(double X, double Y){
        centerX = X;
        centerY = Y;
    }

    public void addVertice(double Xx, double Yy){
        Point point = new Point(Xx, Yy);
        vertice.add(point);
    }
}
