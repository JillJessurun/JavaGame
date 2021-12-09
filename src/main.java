/*
comments:
- Hashmaps do not have an order like arrays do. It is completely random.
- happy2.size -> returns size (in this case 3)
- happy2.replace -> first parameter: key you want value replacement of - second parameter: replacement value

 */

import java.util.HashMap;

public class main {
    public static void main(String[] args) {

        //hashmap tutorial
        int a = 10;
        int b = 3;
        int c = 88;

        //EXAMPLE 1 ----------------------------------------------------------------------------------------------------

        //hashmap raw? you need to give the type of the data stored in the hashmap (in this case Integer)
        HashMap<String, Integer> happy = new HashMap<String, Integer>();

        happy.put("a", 10);
        happy.put("b", 3);
        happy.put("c", 88);

        //print the hashmap
        System.out.println(happy);

        //get the value of c via the hashmap
        System.out.println(happy.get("c") + "\n");

        //EXAMPLE 2 ----------------------------------------------------------------------------------------------------
        HashMap<String, String> happy2 = new HashMap<String, String>();
        happy2.put("Sill", "dikzak");
        happy2.put("bobby69", "hoixd");
        happy2.put("speedy420", "password123");

        //print the hashmap
        System.out.println(happy2);

        //remove item from hashmap
        happy2.remove("Sill");

        //print the hashmap
        System.out.println(happy2);

        //see if the hashmap contains a certain key
        if (happy2.containsKey("bobby69")){
            System.out.println("This hashmap contains the username bobby69!");
        }else{
            System.out.println("This hashmap does not contain the username bobby69.");
        }

        if (happy2.containsKey("xdlolyeet")){
            System.out.println("This hashmap contains the username xdlolyeet!");
        }else{
            System.out.println("This hashmap does not contain the username xdlolyeet.");
        }

        //see if the hashmap contains a certain value
        if (happy2.containsValue("hoixd")){
            System.out.println("This hashmap contains the value hoixd!");
        }else{
            System.out.println("This hashmap does not contain the value hoixd.");
        }

        if (happy2.containsValue("yayyyyyy")){
            System.out.println("This hashmap contains the value yayyyyyy!");
        }else{
            System.out.println("This hashmap does not contain the value yayyyyyy.");
        }
    }
}
