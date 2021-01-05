import java.io.*;

public class serialization
{
    public static void main(String[] args) throws IOException
    {
        Admin ad=new Admin("Dr.ADMIN","admin","admin");
        FileOutputStream fout=new FileOutputStream("admin.ser");
        ObjectOutputStream out=new ObjectOutputStream(fout);
        out.writeObject(ad);
        out.close();
        fout.close();


        Department[] d;
        College[] col=new College[4];

        d=new Department[3];
        d[0]=new Department("CSE",10);
        d[1]=new Department("IT",5);
        d[2]=new Department("ECE",2);
        col[0]=new College(1111,"MIT",d);
        
        d=new Department[5];
        d[0]=new Department("CSE",5);
        d[1]=new Department("IT",1);
        d[2]=new Department("ECE",2);
        d[3]=new Department("EEE",2);
        d[4]=new Department("MECHANICAL",1);
        col[1]=new College(2222,"CEG",d);

        d=new Department[3];
        d[0]=new Department("CSE",3);
        d[1]=new Department("IT",2);
        d[2]=new Department("EEE",1);
        col[2]=new College(3333,"SSN",d);

        d=new Department[3];
        d[0]=new Department("CSE",4);
        d[1]=new Department("IT",2);
        d[2]=new Department("ECE",2);
        col[3]=new College(4444,"KRCE",d);
        
        fout=new FileOutputStream("college.ser");
        out=new ObjectOutputStream(fout);
        out.writeObject(col);
        out.close();
        fout.close();   
    }
}
