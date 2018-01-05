import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonFile {
    private Point startPoint;
    private Point finishPoint;
    private List<Polygon> polygonArea;


    public JsonFile(){
        polygonArea = new ArrayList<>();

    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getFinishPoint() {
        return finishPoint;
    }

    public List<Polygon> getPolygonArea() {
        return polygonArea;
    }

    public void dataToJson(List<Point> points){

        JSONArray pointArray = new JSONArray();
        for(int i = 0; i < points.size(); i ++){
            JSONObject object = new JSONObject();
            object.put("cx", points.get(i).X);
            object.put("cy", points.get(i).Y);
            pointArray.add(object);
        }
        try (FileWriter writer = new FileWriter("fff.json")){
            writer.write(pointArray.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(JsonFile.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }



    public void dataToJson1(List<Point> points){

        JSONArray pointArray = new JSONArray();
        for(int i = 0; i < points.size(); i ++){
            JSONObject object = new JSONObject();
            object.put("cx", points.get(i).X);
            object.put("cy", points.get(i).Y);
            pointArray.add(object);
        }
        try (FileWriter writer = new FileWriter("vse.json")){
            writer.write(pointArray.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(JsonFile.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }

    public void dataToJsonLine(List<LineSegment> lineSegments){

        JSONArray pointArray = new JSONArray();
        for(int i = 0; i < lineSegments.size(); i ++){
            JSONObject object = new JSONObject();
            object.put("x1", lineSegments.get(i).startPoint.X);
            object.put("y1", lineSegments.get(i).startPoint.Y);
            object.put("x2", lineSegments.get(i).finishPoint.X);

            object.put("y2", lineSegments.get(i).finishPoint.Y);


            pointArray.add(object);
        }
        try (FileWriter writer = new FileWriter("line.json")){
            writer.write(pointArray.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(JsonFile.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }

    public void dataToJsonLine1 (List<LineSegment> lineSegments){

        JSONArray pointArray = new JSONArray();
        for(int i = 0; i < lineSegments.size(); i ++){
            JSONObject object = new JSONObject();
            object.put("x1", lineSegments.get(i).startPoint.X);
            object.put("y1", lineSegments.get(i).startPoint.Y);
            object.put("x2", lineSegments.get(i).finishPoint.X);

            object.put("y2", lineSegments.get(i).finishPoint.Y);


            pointArray.add(object);
        }
        try (FileWriter writer = new FileWriter("lineVse.json")){
            writer.write(pointArray.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(JsonFile.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }





    public void parseJson(String str){
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(str));
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject start  = (JSONObject) jsonObject.get("start");

            Long startX = (long)start.get("x");
            Long startY = (long)start.get("y");
            startPoint = new Point((double)startX, (double)startY);

            JSONObject finish = (JSONObject) jsonObject.get("finish");
            Long finishX = (long)finish.get("x");
            Long finishY = (long)finish.get("y");
            finishPoint = new Point((double)finishX, (double)finishY);

            JSONArray polygons = (JSONArray) jsonObject.get("polygons");
            List<JSONObject> polygonJSON = new ArrayList<>();
            Iterator<JSONObject> iterator = polygons.iterator();
            while (iterator.hasNext()){
                polygonJSON.add(iterator.next());
                //System.out.println(iterator.next());
            }

            //List<Polygon> polygonsAll = new ArrayList<>();
            for(int i = 0; i < polygonJSON.size(); i++){
                Polygon polygon = new Polygon();
                Long centerX = (long) polygonJSON.get(i).get("x");
                Long centerY = (long) polygonJSON.get(i).get("y");
                polygon.addCenter((double)centerX, (double)centerY);
                JSONArray vertices = (JSONArray) polygonJSON.get(i).get("vertices");
               // System.out.println(vertices);

                Iterator<JSONObject> iterator1 = vertices.iterator();
                while (iterator1.hasNext()){
                    JSONObject obgectt = (JSONObject) iterator1.next();
                    Long pXX = (long) obgectt.get("x");
                    Long pYY = (long) obgectt.get("y");

                    polygon.addVertice((double)pXX, (double)pYY);
//                   // polygonJSON.add(iterator.next());
                    //System.out.println(pXX + " " + pYY);

                }
                polygonArea.add(polygon);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
