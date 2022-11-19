import java.util.*;

public class tp5{




public static void main(String[] args) {
   
    ArrayList<String> list = new ArrayList<String>();
    
    list.add("aaa");
    System.out.println(list);
    list.add("aa");
    System.out.println(list);
    list.add(0,"a");
    System.out.println(list);

    for(int i=0;i<list.size(); i++){
        if(list.get(i).length()%2==0){
            list.set(i,"b");
        }
    }
    System.out.println(list);

    boolean trouve = false;
    for(String e:list) {
        trouve = e.equals("a");
        if (trouve) {
            System.out.println("Chaine a presente");
        }
    }    

}


    



















}