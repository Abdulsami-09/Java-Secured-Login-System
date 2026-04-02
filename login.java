import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class login {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Registration");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            int x = sc.nextInt();
            sc.nextLine(); 
            System.out.println();

            switch (x) {

                case 1:
                    System.out.print("Registration name: ");
                    String reguser = sc.nextLine();

                      if(reguser.isEmpty()){
                        System.out.println("Username Cannot be Empty");
                        break;
                    }
                    
                    boolean exist=false;
                    try{
                        BufferedReader br=new BufferedReader(new FileReader("user.txt"));
                        String line;
                        while((line=br.readLine())!=null){
                            String [] data=line.split(",");
                            if(data[0].equals(reguser)){
                                exist=true;
                                break;
                            }
                        }
                        br.close();
                    }catch(IOException e){
                            e.printStackTrace();
                    }
                    if(exist){
                        System.out.println("User Already Exist");
                        break;
                    }


                    System.out.print("Registration Password: ");
                    String regpass = sc.nextLine();

                  

                    
                  

                    if(regpass.length()<4){
                        System.out.println("Password Must be greater than 4.");
                        break;
                    }

                    try{
                        BufferedWriter bw=new BufferedWriter(new FileWriter("user.txt",true));
                        int hashedPass=regpass.hashCode();
                        bw.write(reguser+","+hashedPass);
                        bw.newLine();
                        bw.close();
                        System.out.println("Registration Successfull");
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    
                    System.out.println();
                    break; 

                case 2:
                    int res=3;
                    while(res >0){
                        
                    System.out.print("Enter Username: ");
                    String user = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String pass = sc.nextLine();
                    int hashedinput=pass.hashCode();

                    if(user.isEmpty()){
                        System.out.println("Username Cannot be Empty");
                        break;
                    }

                    boolean found=false;
                    try{
                        BufferedReader br=new BufferedReader(new FileReader("user.txt"));
                        String line;
                        while((line=br.readLine())!=null){
                            String[] data =line.split(",");
                            if(data[0].equals(user)&&Integer.parseInt(data[1])==hashedinput){
                                found=true;
                                break;
                            }
                            

                        }br.close();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    if(found){
                        System.out.println("Login Successful");
                        System.out.println();
                        break;
                    } else {
                        System.out.println("Incorrect Credential");
                        res--;
                        System.out.println(res+" attempts left");
                    }
                if(res==0)
                {
                    System.out.println("Try again after 5 seconds");
                    try{
                        Thread.sleep(5000);
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    res=3;
                }
                 
                    System.out.println();
                }
                   break; 

                case 3:
                    System.out.println("Exited Successfully");
                    System.exit(0);

                default:
                    System.out.println("Enter valid option");
            }
        }
    }
}
