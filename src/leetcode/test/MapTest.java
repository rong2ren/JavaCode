package test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapTest {

    public MapTest() {
    }

    public static void main(String[] args) {
        //Create HashMap Object
        HashMap<Integer,String> students = new HashMap<Integer, String>();
 
        //Add objects in HashMap
        students.put(1, "Jai");
        students.put(5, "Mahesh");
        students.put(3, "Vishal");
        students.put(4, "Hemant");
        students.put(2, "Narender");
 
        //Print HashMap objects 
        System.out.println("-------------Hash table--------------");
        for (Map.Entry<Integer, String> entry : students.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }


        //Create LinkedHashMap Object
        LinkedHashMap<Integer, String> students2 = new LinkedHashMap<Integer, String>();
 
        //Add objects in LinkedHashMap
        students2.put(1, "Jai");
        students2.put(4, "Hemant");
        students2.put(5, "Narender");
        students2.put(2, "Mahesh");
        students2.put(3, "Vishal");
 
        //Print LinkedHashMap objects 
        System.out.println("-------------Linked Hash table--------------");
        for (Map.Entry<Integer, String> entry : students.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
    
}
