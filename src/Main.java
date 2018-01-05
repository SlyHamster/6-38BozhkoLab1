import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        JsonFile jsonFile = new JsonFile();
        jsonFile.parseJson("3.json");
        Point start = jsonFile.getStartPoint();
        Point finish = jsonFile.getFinishPoint();
        List<Polygon> polygons = jsonFile.getPolygonArea();




        long startTime = System.nanoTime();
        PRM3Algo algo = new PRM3Algo(start, finish, polygons);
        algo.prm3Algo();
        long finalTime = System.nanoTime();
        System.out.println("Время выполнения " + (finalTime - startTime)/1000000);


        List<LineSegment> end = algo.getEndPath();

        JsonFile jsonFile1 = new JsonFile();
        jsonFile.dataToJsonLine1(end);


        List<Point> vseTochki = algo.getPointsInSegments();


//        test1(algo);

//        List<Point> viborka = new ArrayList<>();
//        for(int i = 50; i < 51; i++){
//            viborka.add(vseTochki.get(i));
//        }
//
//        algo.poistNaimenshegoPuto(vseTochki.get(0));
//        List<LineSegment>





//        Point point1 = new Point(4.0, 21.0);
//        algo.addPointToGraf(point1);

//        Point point1 = new Point(4.0, 21.0);
//        algo.addPointToGraf(point1);




//        Point point1 = new Point(21.0, 28.0);
//        Point point2 = new Point(21.0, 26.0);
//        Point point3 = new Point(20.0, 27.0);
//        Point point4 = new Point(23.0, 27.0);
////
////        boolean bool  = algo.intersectNew(point1, point2, point3, point4);
//
//        LineSegment l = new LineSegment(point1, point2);
//        boolean bool = algo.peresech(l);
//        System.out.println(bool);

//        Point p = algo.findNewPointTwo(l);
//        System.out.println(p.X + " " + p.Y);
//
//        boolean b = algo.pr(polygons, p);
//        System.out.println(b);

//        Point point10 = new Point(3.0, 21.0);
//        Point point11 = new Point(2.0, 23.0);
//        LineSegment l1 = new LineSegment(point10, point11);
////
//        Point cent = algo.findCenterLine(l1);
////
//

//
//        List<LineSegment> al = new ArrayList<>();
//        al.add(lineSegment1);
//        al.add(lineSegment2);
//        al.add(lineSegment3);
//        al.add(lineSegment4);
//
//        List<Point> search = new ArrayList<>();
//        search.add(start);
//
//        int c = 0;
//        algo.findPath(al, search);
//
//        for(int i = 0; i < al.size(); i++){
//            System.out.println(al.get(i).cost  + " ");
//        }
//
//        algo.restorationPath(al);
////
//        List<LineSegment> endP = algo.getEndPath();
//        for(int i = 0; i < endP.size(); i++){
//            System.out.println(endP.get(i).startPoint.X + ";" + endP.get(i).startPoint.Y + "  " + endP.get(i).finishPoint.X + ";" + endP.get(i).finishPoint.Y );
//        }
//
//        System.out.println();
//        List<Point> endPoint = algo.getEndPoint();
//        for(int i = 0; i < endPoint.size(); i++){
//            System.out.println(endPoint.get(i).X + "; " + endPoint.get(i).Y);
//        }


//        List<Point> po = new ArrayList<>();
////        Point point = new Point((long)6, (long)21);
////        Point point1 = new Point((long)6, (long)22);
////        Point point2 = new Point((long)6, (long)23);
//        Point point3 = new Point((long)6, (long)25);
////        po.add(point);
////        po.add(point1);
////        po.add(point2);
//
//        boolean bool = algo.findPointInList(po, point3);
//        System.out.println(bool);



//        Polygon polygon1 = polygons.get(3);
//        FindPath findPath = new FindPath();
//        findPath.algo(polygons);

//


//        System.out.println(bool1);
//        findPath.algo(polygons);


//        Point p11 = new Point(5.0, 2.0);
//        Point p12 = new Point(6.0, 4.0);
//        LineSegment s1 = new LineSegment(p11, p12);
//        Point p21 = new Point(6.0, 4.0);
//        Point p22 = new Point(2.0, 5.0);
//        LineSegment s2 = new LineSegment(p21, p22);

//       System.out.println(findPath.intersection(s1, s2));

//

//        boolean bool =  algo.intersection(s1, s2);
//        System.out.println(bool);
//
//        for (int i = 0; i < all.size(); i++) {
//            boolean bool = algo.pr(polygon2, all.get(i));
//            System.out.println(bool);
//        }

    }

    public static void test1(PRM3Algo algo){
        System.out.println("тест 1");
        //        принадлежит ли точка списку
        Point point1 = new Point(23.0, 1.0);
        Point point2 = new Point(24.0, 1.0);
        LineSegment line1 = new LineSegment(point1, point2);

        Point point7 = new Point(23.0, 1.0);
        Point point8 = new Point(24.0, 4.0);
        LineSegment line11 = new LineSegment(point7, point8);

        List<LineSegment> lines = new ArrayList<>();
        Point point3 = new Point(23.0, 1.0);
        Point point4 = new Point(24.0, 1.0);
        LineSegment line2 = new LineSegment(point3, point4);
        lines.add(line2);

        Point point5 = new Point(27.0, 3.0);
        Point point6 = new Point(24.0, 1.0);
        LineSegment line3 = new LineSegment(point5, point6);
        lines.add(line3);


        System.out.println(algo.prinadlezitOntezokSkisku(line1, lines));
        System.out.println(algo.prinadlezitOntezokSkisku(line11, lines));

    }
}
