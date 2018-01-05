import javax.swing.text.Segment;
import java.util.ArrayList;
import java.util.List;

public class PRM3Algo {
    private Point startPoint;
    private Point finishPoint;
    private List<Polygon> polygons;
    private List<Point> pointsInSegments;
    private List<Point> pointsNeInSefment;
    private List<Point> allPoints;
    private List<Point> pointsGraf;
    private List<LineSegment> lineGraf;
    private List<Point> viewedVertices;
    private Integer countNeighbor;
    private boolean pathhh;
    private int maxCost;
    private List<LineSegment> endPath;
    private List<Point> endPoint;


    public List<Point> getPointsInSegments() {
        return allPoints;
    }

    public List<LineSegment> getEndPath() {
        return endPath;
    }

    public List<Point> getEndPoint() {
        return endPoint;
    }

    public PRM3Algo(Point start, Point finish, List<Polygon> polygons){
        pathhh = false;
        startPoint = start;
        finishPoint = finish;
        this.polygons = polygons;
        allPoints = createListPoints();
        viewedVertices = new ArrayList<>();

        pointsGraf = allPoints;
        pointsGraf.add(startPoint);
        pointsGraf.add(finishPoint);

        lineGraf = new ArrayList<>();
        countNeighbor = 3;
        maxCost = 0;
        endPath = new ArrayList<>();
        endPoint = new ArrayList<>();
    }

    private List<Point> createListPoints(){
        List<Point> points = new ArrayList<>();
        for(int i = 0; i < polygons.size(); i++){
            for(int k = 0; k < polygons.get(i).vertice.size(); k++){
                points.add(polygons.get(i).vertice.get(k));
            }
        }
        return points;
    }

    private Point randomPoint(){
        double xx = Math.ceil(Math.random() * 51.0 - 1.0);
        double yy = Math.ceil(Math.random() * 31.0 - 1.0);
        return new Point(xx, yy);
    }

    public void prm3Algo(){
        List<Point> pointsOutPolygon = new ArrayList<>();
        List<Point> pointsInPolygon = new ArrayList<>();
        int countMaxPoint = 1000;
        boolean flagEnd = false;
        int countInteration = 0;

        while( countInteration != countMaxPoint && pathhh != true){
            Point point = randomPoint();

            if(findPointInList(viewedVertices, point)){
                continue;
            } else{
                viewedVertices.add(point);
            }

            if((proverka(polygons, point) == true && isThePointAVertex(allPoints, point) == false)){
//                System.out.println("принадлежит");
                pointsInPolygon.add(point);
                continue;
            } else{
                pointsOutPolygon.add(point);
                pointsGraf.add(point);
                poistNaimenshegoPuto(point);
//                addPointToGraf(point);
                List<Point> search = new ArrayList<>();
                search.add(startPoint);
                JsonFile jsonFile3 = new JsonFile();
                jsonFile3.dataToJsonLine(lineGraf);
//

                maxCost = 0;

                for(int i = 0; i < lineGraf.size(); i++){
                    lineGraf.get(i).setCost(-1);
                }
                findPath(search);
//                if(pathhh == true){
//                    restorationPath();
//                    System.out.println("Решение нашлось!");
//                }
            }
           countInteration++;
        }
        if(pathhh == true){
            restorationPath();
            System.out.println("Решение нашлось!");
        } else {
            System.out.println("Решение не нашлось!");
        }

//        JsonFile jsonFile = new JsonFile();
//        jsonFile.dataToJson1(pointsOutPolygon);
//        jsonFile.dataToJson(pointsInPolygon);
    }


    public void findPath(List<Point> searchTops){


        for(int i = 0; i < searchTops.size(); i++){
            if(searchTops.get(i).X == finishPoint.X && searchTops.get(i).Y == finishPoint.Y){
                pathhh = true;
//                restorationPath();
//                System.out.println("Решение нашлось!");
//                System.out.println("Решение нашлось");

            }
        }

        if(pathhh == false){
            maxCost++;
            List<Point> children = new ArrayList<>();
            for(int i = 0; i < searchTops.size(); i++){
                for(int k = 0; k < lineGraf.size(); k++){
                    if(lineGraf.get(k).cost < 0){
                        if(searchTops.get(i).X == lineGraf.get(k).startPoint.X && searchTops.get(i).Y == lineGraf.get(k).startPoint.Y){
                            children.add(lineGraf.get(k).finishPoint);
                            lineGraf.get(k).setCost(maxCost);
                        }
                        if(lineGraf.get(k).finishPoint.X == searchTops.get(i).X && lineGraf.get(k).finishPoint.Y == searchTops.get(i).Y){
                            children.add(lineGraf.get(k).startPoint);
                            lineGraf.get(k).setCost(maxCost);
                        }
                    }

                }
            }
            if(children.size() > 0){
                findPath(children);
            } else {
//                System.out.println("решения нет");
            }
        }
    }

    public void restorationPath(){
        Point tPoint  = finishPoint;
        endPoint.add(tPoint);

        while(maxCost > 0){
            for(int k = 0; k < lineGraf.size(); k++){
                if(lineGraf.get(k).cost == maxCost){
                    if(lineGraf.get(k).startPoint.X == tPoint.X && lineGraf.get(k).startPoint.Y == tPoint.Y){
                        tPoint = lineGraf.get(k).finishPoint;
                        endPoint.add(tPoint);
                        endPath.add(lineGraf.get(k));
                        maxCost--;
                        continue;
                    }
                    if(lineGraf.get(k).finishPoint.X == tPoint.X && lineGraf.get(k).finishPoint.Y == tPoint.Y){
                        tPoint = lineGraf.get(k).startPoint;
                        endPoint.add(tPoint);
                        endPath.add(lineGraf.get(k));
                        maxCost--;
                    }
                }
            }
        }
    }


    private double distanceCalculation(Point a, Point b){
        return Math.sqrt(Math.pow(a.X - b.X, 2) + Math.pow(a.Y - b.Y, 2));
    }


//    public void addPointToGraf(Point point){
//        List<Point> tempL = new ArrayList<>();
//        int countN = 0;
//        while(countN < countNeighbor){
//            double distance = 100.0;
//            int nomer = 0;
//            for(int i = 0; i < pointsGraf.size(); i++){
//                if(findPointInList(tempL, pointsGraf.get(i)) == false){
//
////                        if(dis != 0 && dis < distance){
//                            LineSegment lir = new LineSegment(point, pointsGraf.get(i));
//                            Point centerLine = findCenterLine(lir);
////
//                            if(vixodzaGranizu(lir) == false){
//                                if(intersTwoLines(lir) == false){
//                                    if(peresech(lir) == false){
//                                        double dis = distanceCalculation(point, pointsGraf.get(i));
//                                        if(proverka(polygons, centerLine) == false){
//                                            if(dis != 0 && dis < distance){
//                                                distance = dis;
//                                                nomer = i;
//                                            }
//
//                                        }
//
//                                    }
//                                }
//                            }
//                }
//
//            }
//            tempL.add(pointsGraf.get(nomer));
//            countN++;
//        }
//
//        for(int i = 0; i < tempL.size(); i++){
//            LineSegment line = new LineSegment(point, tempL.get(i));
//            lineGraf.add(line);
//        }
//
//        JsonFile jsonFile3 = new JsonFile();
//        jsonFile3.dataToJsonLine(lineGraf);
//    }


    public boolean vixodzaGranizu(LineSegment a){
        boolean flag = false;
        if(a.startPoint.X < 0 || a.finishPoint.X < 0 || a.startPoint.Y < 0 || a.finishPoint.Y < 0){
            flag = true;
        }
        if(a.startPoint.X > 50 || a.finishPoint.X > 50 || a.startPoint.Y > 30 || a.finishPoint.Y > 30){
            flag = true;
        }
        return flag;
    }

    public boolean vihodZaGranizuTochka(Point point){
        boolean flag = false;
        if(point.X < 0 || point.Y < 0 || point.X > 50 || point.Y > 30){
            flag = true;
        }
        return flag;
    }

    public boolean peresech (LineSegment a){
//        System.out.println(a.startPoint.X + "; " + a.startPoint.Y + " " + a.finishPoint.X  + "; " + a.finishPoint.Y);

        boolean flag = false;
        for(int i = 0; i < allPoints.size(); i++){
//            System.out.println(allPoints.get(i).X + " " + allPoints.get(i).Y);
            if(allPoints.get(i).X !=a.startPoint.X || allPoints.get(i).Y != a.startPoint.Y){
                    if(allPoints.get(i).X != a.finishPoint.X || allPoints.get(i).Y != a.finishPoint.Y){
                        if(perLinandTochka(a, allPoints.get(i))) {
                            if((a.startPoint.X >= allPoints.get(i).X && allPoints.get(i).X >= a.finishPoint.X) || (a.startPoint.X <= allPoints.get(i).X && allPoints.get(i).X <= a.finishPoint.X)){
                                if((a.startPoint.Y >= allPoints.get(i).Y && allPoints.get(i).Y >= a.finishPoint.Y) || (a.startPoint.Y <= allPoints.get(i).Y && allPoints.get(i).Y <= a.finishPoint.Y))
//                                System.out.println("зашел сюда");
                                flag = true;
                            }

                        }
                    }
//
                }
//
//            }
        }
        return flag;
    }

    public boolean perLinandTochka(LineSegment a, Point point) {
        double res = (point.X - a.startPoint.X) * (a.finishPoint.Y - a.startPoint.Y) - (point.Y - a.startPoint.Y) * (a.finishPoint.X - a.startPoint.X);
        if(res == 0){
            return true;
        } else {
            return false;
        }

    }

    public boolean intersTwoLines(LineSegment a){
        boolean flag = false;
        for(int i = 0; i < polygons.size(); i++){
            List<LineSegment> listLineSegmentsOnePolygon = createLineSegments(polygons.get(i));
            for(int k = 0; k < listLineSegmentsOnePolygon.size(); k++){
                LineSegment aa;
                aa = new LineSegment(listLineSegmentsOnePolygon.get(k).finishPoint, listLineSegmentsOnePolygon.get(k).startPoint);
//                if(intersection(listLineSegmentsOnePolygon.get(k), a) && (intersection(aa, a))){
                if(intersectNew(listLineSegmentsOnePolygon.get(k).startPoint, listLineSegmentsOnePolygon.get(k).finishPoint, a.startPoint, a.finishPoint)){
                    flag = true;
                }
            }
        }

        return flag;
    }


    public boolean findPointInList (List<Point> listPoints, Point point){
        for(int i = 0; i < listPoints.size(); i++){
            if(listPoints.get(i) == point){
                return true;
            }
        }
        return false;
    }

    private List<LineSegment> createLineSegments(Polygon polygon){
        List<LineSegment> lineSegments = new ArrayList<>();
        for(int i = 0; i < polygon.vertice.size(); i ++){
            Point firstPoint = polygon.vertice.get(i);
            Point secondPoint;
            if(i != polygon.vertice.size() - 1){
                secondPoint = polygon.vertice.get(i + 1);
            } else {
                secondPoint = polygon.vertice.get(0);
            }
            LineSegment lineSegment = new LineSegment(firstPoint, secondPoint);
            lineSegments.add(lineSegment);
        }
        return lineSegments;
    }


    private boolean isThePointAVertex(List<Point> points, Point point){
        for(int i = 0; i < points.size(); i++){
            if(points.get(i).X == point.X && points.get(i).Y == point.Y){
                return true;
            }
        }
        return false;
    }

    public Boolean proverka(List<Polygon> polygons, Point point){
        Boolean flag = false;

        double tempX = point.X + 1.0;
        double tempY = 40.0;
        Point twoPoint = new Point(tempX, tempY);

        LineSegment tempLinesegment = new LineSegment(point, twoPoint);
        for(int i = 0; i < polygons.size(); i++){
            Polygon polygonOne = polygons.get(i);
            List<LineSegment> listLineSegments = createLineSegments(polygonOne);
            Integer count = 0;
            for(int k = 0; k < listLineSegments.size(); k++){
                LineSegment a;
                a = new LineSegment(listLineSegments.get(k).finishPoint, listLineSegments.get(k).startPoint);
//                if(intersection(listLineSegments.get(k), tempLinesegment) || intersection(a, tempLinesegment)){
                if(intersectNew(listLineSegments.get(k).startPoint, listLineSegments.get(k).finishPoint, tempLinesegment.startPoint, tempLinesegment.finishPoint)){
                    count++;
                }
            }

            if(count % 2 == 1){
                flag = true;
            }

        }
        if(flag == true){
            return true;
        } else {
            return false;
        }
    }


    public boolean intersection(LineSegment a, LineSegment b){

        double v1 = (b.finishPoint.X - b.startPoint.X) * (a.startPoint.Y - b.startPoint.Y) -
                (b.finishPoint.Y - b.startPoint.Y) * (a.startPoint.X - b.startPoint.X);
//        System.out.println(v1);
        double v2 = (b.finishPoint.X - b.startPoint.X) * (a.finishPoint.Y - b.startPoint.Y) -
                (b.finishPoint.Y - b.startPoint.Y) * (a.finishPoint.X - b.startPoint.X);
//        System.out.println(v2);
        double v3 = (a.finishPoint.X - a.startPoint.X) * (b.startPoint.Y - a.startPoint.Y) -
                (a.finishPoint.Y - a.startPoint.Y) * (b.startPoint.X - a.startPoint.X);
//        System.out.println(v3);
        double v4 = (a.finishPoint.X - a.startPoint.X) * (b.finishPoint.Y - a.finishPoint.Y) -
                (a.finishPoint.Y - a.startPoint.Y) * (b.finishPoint.X - a.startPoint.X);
//        System.out.println(v4);
//        System.out.println();
        if(v1 * v2 < 0 && v3 * v4 < 0){
//            System.out.println(a.startPoint.X + "; " + a.startPoint.Y + " " + a.finishPoint.X + "; " + a.finishPoint.Y);
            return true;
        } else {
            return false;
        }

    }

    public Point findCenterLine (LineSegment a){
        double centerX = (a.finishPoint.X + a.startPoint.X) / 2;
        double centerY = (a.finishPoint.Y + a.startPoint.Y) / 2;

        Point centerLine = new Point(centerX, centerY);
        return centerLine;
    }

    public Point findNewPoint (LineSegment a){
        double newX;
        if(a.startPoint.X < a.finishPoint.X){
            newX = a.startPoint.X + (a.startPoint.X / 100.0);
        } else {
            newX = a.finishPoint.X + (a.finishPoint.X / 100.0);
        }
        double newY;
        newY = (-(a.startPoint.X * a.finishPoint.Y - a.finishPoint.X* a.startPoint.Y) - (a.startPoint.Y - a.finishPoint.Y) * newX) / (a.finishPoint.X - a.startPoint.X);

        return new Point(newX, newY);
    }

    public Point findNewPointTwo (LineSegment a){
        double newX;
        if(a.startPoint.X > a.finishPoint.X){
            newX = a.startPoint.X - (a.startPoint.X / 100.0);
        } else {
            newX = a.finishPoint.X - (a.finishPoint.X / 100.0);
        }
        double newY;
        newY = (-(a.startPoint.X * a.finishPoint.Y - a.finishPoint.X* a.startPoint.Y) - (a.startPoint.Y - a.finishPoint.Y) * newX) / (a.finishPoint.X - a.startPoint.X);

        return new Point(newX, newY);
    }


    public double area_1 (Point a, Point b, Point c){
        return ((b.X - a.X)*(c.Y - a.Y) - (b.Y - a.Y)*(c.X - a.X));
    }

    public boolean intersect_1 (double a, double b, double c, double d){
        if(a > b){
            double temp = a;
            a = b;
            b = temp;
        }
        if (c > d){
            double temp = c;
            c = d;
            d = temp;
        }
        return (Math.max(a, c) <= Math.min(b, d));
    }

    public boolean intersectNew (Point a, Point b, Point c, Point d){
        return (intersect_1(a.X, b.X, c.X, d.X) && intersect_1(a.Y, b.Y, c.Y, d.Y) &&
                area_1(a, b, c)*area_1(a, b, d) < 0 && area_1(c, d, a)*area_1(c,d,b) < 0);
    }


    public void poistNaimenshegoPuto (Point po){
//        List<LineSegment> gr = new ArrayList<>();

        List<LineSegment> linesPuti = proverkaPuti(po);
        int countN = 0;
        if(linesPuti.size() > 3){
            while(countN < countNeighbor) {
                double distance = 100.0;
                int nomer = 0;
                for(int i = 0; i < linesPuti.size(); i++){
                    double dis = distanceCalculation(linesPuti.get(i).startPoint, linesPuti.get(i).finishPoint);
                    if(dis != 0 && dis < distance){
                        distance = dis;
                        nomer = i;
                    }
                }
                lineGraf.add(linesPuti.get(nomer));
                linesPuti.remove(nomer);
                countN++;
            }
        } else {
            for(int i = 0; i < linesPuti.size(); i++){
                lineGraf.add(linesPuti.get(i));
            }
        }


//        JsonFile jsonFile1 = new JsonFile();
//        jsonFile1.dataToJsonLine1(lineGraf);
}



    public List<LineSegment> proverkaPuti(Point pointsinMethod) {
        List<LineSegment> ensListLineSegment = new ArrayList<>();
                    for(int k = 0; k < allPoints.size(); k++){
                        if(vihodZaGranizuTochka(allPoints.get(k)) == false){
                            if(pointsinMethod.equals(allPoints.get(k)) == false){
                                LineSegment otrezok = new LineSegment(pointsinMethod, allPoints.get(k));
                                if(prinadlezitOntezokSkisku(otrezok, ensListLineSegment) == false){
                                    Point centerr = findCenterLine(otrezok);
                                    if(intersTwoLines(otrezok) == false && proverka(polygons, centerr) == false){
                                        if(peresech(otrezok) == false){
                                            ensListLineSegment.add(otrezok);
                                        }

                                    }
                                }
                            }
                        }

            }

        return ensListLineSegment;
    }




    public boolean prinadlezitOntezokSkisku(LineSegment line1, List<LineSegment> listLines){
        boolean bool = false;
//        System.out.println(line1.startPoint.X + ";" + line1.startPoint.Y + " " + line1.finishPoint.X + ";" + line1.finishPoint.Y);
        for(int i = 0; i < listLines.size(); i++){

//            System.out.println("Точка " + listLines.get(i).startPoint.X + ";" + listLines.get(i).startPoint.Y + " " + listLines.get(i).finishPoint.X + ";" + listLines.get(i).finishPoint.Y);
            if(line1.startPoint.X == listLines.get(i).startPoint.X && line1.startPoint.Y == listLines.get(i).startPoint.Y &&
                    line1.finishPoint.X == listLines.get(i).finishPoint.X && line1.finishPoint.Y == listLines.get(i).finishPoint.Y){
                bool =  true;
            }
        }
        if(bool == true)
            return true;
        else
            return false;
    }
}
