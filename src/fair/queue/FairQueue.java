/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fair.queue;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.out;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author HamzaAli
 */
public class FairQueue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        HashMap<String,String> task = new HashMap<String,String>();
        HashMap<String,String> time = new HashMap<String,String>();
        HashMap<String,Integer> priority = new HashMap<String,Integer>();
        String[] values = null;
        String[] temp=null;
         int i =0;
        try{
            Scanner input = new Scanner(new FileReader("Academic _Schedule.csv"));
            input.nextLine();
           
            while (input.hasNextLine()) 
            {
                String data = input.nextLine();
                values = data.split(",");
                //System.out.println(values[2]);
                temp = values[2].split("\\(");
                
               try{
                    if (temp[1] != null){
                        temp[1] = temp[1].replace("\\)", "");
                        time.put(temp[0], temp[1]);
                    }
                }
                catch (Exception e){
                       time.put(temp[0], "5pm-9pm"); 
                }
                task.put(values[2],  values[0]+"," +values[1]);
               
                i++;
            }
            System.out.println(time);
            System.out.println(task);
            input.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        
        int x=1;
        for (Map.Entry<String, String> entry : task.entrySet()){
            priority.put(entry.getKey(),x);
            x++;
        }  
        System.out.println(priority);
        
        double total_band=838860800; // 800*1024*1024
        int max_user = 7800; // 12000*0.65
        
        double divide = total_band/max_user;
        
       
        BufferedWriter out = null;
        try  
        {
            FileWriter fstream = new FileWriter("out.csv", true); //true tells to append data.
            out = new BufferedWriter(fstream);
            out.write("Date, Start Time, End Time, Hours, Total Bandwidth, % Bandwidth share for Faculty, % Bandwidth share for students, Bandwidth allocation per faculty member, Bandwidth allocation per student\n");
            for (Map.Entry<String, String> entry : task.entrySet()){
                String data = entry.getValue();
                String[] te = data.split(",");
                out.write(te[0] + "\n");
            } 
        }
        catch (IOException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        finally
        {
            if(out != null) {
                out.close();
            }
        }

    }
    
}
