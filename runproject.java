import java.io.*;
public class runproject {
  public static void main(String[] args)throws IOException{
    // capacity for now set to 3 for demo it can be changed

    cache<Integer> cache=new cache(3);
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    int choice=1;
    while(choice!=0){
        System.out.println("Select choice");
        System.out.println("1: Put \n2: Get \n0: Exit");
        choice= Integer.parseInt(br.readLine());
        String key;
        int value;
        switch (choice) {
            case 1:
                System.out.println("Enter Key");
                key=br.readLine();

                System.out.println("Enter value");
                value=Integer.parseInt(br.readLine());
                cache.put(key,value);
                System.out.println("Inserted key:"+key+"\n");
                break;
            case 2:
                System.out.println("Enter key");
                key=br.readLine();
                System.out.println("value is: "+ cache.get(key)+ "\n");
                break;
            default:
                System.out.println("choice amingo \n");
        }
    }
  }  
}
