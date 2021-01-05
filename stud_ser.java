import java.io.*;
import java.util.ArrayList;
public class stud_ser
{
    public static void main(String[] args) throws IOException
    {
        ArrayList<Student> st=new ArrayList<Student>();
        FileOutputStream fout=new FileOutputStream("student.ser");
        ObjectOutputStream out=new ObjectOutputStream(fout);
        out.writeObject(st);
        out.close();
        fout.close();    
    }    
}
