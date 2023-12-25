import java.io.*;
/**
 * Write a description of class test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class test
{
    byte[] byteArray;
    byte[] byteArray1;

    /**
     * Constructor for objects of class test
     */
    public test(byte[] byteArray, byte[] byteArray1)
    {
        this.byteArray = byteArray;
        this.byteArray1 = byteArray1;
        
        String str = new String(byteArray);
        String str1 = new String(byteArray1);
        
        System.out.println(str);
        System.out.println(str1);
    }
    
    public test() throws IOException{
        String t = "1";
        String z = "2";
        
        byteArray = t.getBytes("UTF-8");
        byteArray1 = z.getBytes("UTF-8");
        
        for(byte b:byteArray){
        System.out.print(b);}
        System.out.println("");
        for(byte b:byteArray1){
        System.out.print(b);}
        System.out.println("");

        System.out.println(new String(byteArray));
        System.out.println(new String(byteArray1));
    }
}
