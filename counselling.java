import java.util.*;
import java.io.*;

class Details
{
    static int dc=0;
    String name,hno;
    double cutoff;
    Details(String name,String hno,double cutoff)
    {
        dc++;
        this.name=name;
        this.hno=hno;
        this.cutoff=cutoff;
    }
}
class Department implements Serializable
{
    private static final long serialVersionUID = 1L;
    String dname;
    int seats;
    Department(String dname,int seats)
    {
        this.dname=dname;
        this.seats=seats;
    }
}
class College implements Serializable
{
    private static final long serialVersionUID = 1L;
    String cname;
    int code;
    Department[] dep;
    College(int code,String cname,Department[] dep)
    {
        this.code=code;
        this.cname=cname;
        this.dep=dep;
    }
    static void viewcollege(College[] col)
    {
        System.out.println("\nCOLLEGE LIST:");
        for(int i=0;i<col.length;i++)
            System.out.println("("+(i+1)+") "+col[i].code+" "+col[i].cname+" ");
    }
    void viewdepartments()
    {
        System.out.println("\nDEPARTMENT LIST:");
        for(int i=0;i<dep.length;i++)
            System.out.println("("+(i+1)+") "+dep[i].dname);
    }
}
class Choicelist implements Serializable
{
    private static final long serialVersionUID = 1L;
    String cname, dname;
    int code;
    Choicelist(int code,String cname,String dname)
    {
        this.code=code;
        this.cname=cname;
        this.dname=dname;
    }
}
class Student implements Serializable
{
    private static final long serialVersionUID = 1L;
    int randno, rank;
    String name,userid,pwd,allotedcollege=null,alloteddept=null;
    double cutoff;
    boolean choicestatus=true;
    ArrayList<Choicelist> ch=new ArrayList<Choicelist>();
    Student(String name,String userid,String pwd,double cutoff)
    {
        this.name=name;
        this.userid=userid;
        this.pwd=pwd;
        this.cutoff=cutoff;
    }
    void changepwd(Scanner sc)
    {
        String s1;
        while(true)
        {
            System.out.print("\nOld password: ");
            s1=sc.next();
            if(s1.equals(pwd))
            {
                System.out.print("New password: ");
                s1=sc.next();
                pwd=s1;
                System.out.println("Password successfully changed...!!");
                break;
            }
            else
            {
                System.out.print("Invalid password !!!");
            }
        }
    }
    static void getstatus(boolean[] status)
    {
        int i;
        for(i=0;i<status.length;i++)
        {
            if(!status[i])
                break;
        }
        if(i==0)
        {
            System.out.println("Counselling registration phase is open");
        }
        else if(i==1)
        {
            System.out.println("Random number is assigned");
        }
        else if(i==2)
        {
            System.out.println("Ranklist is published");
        }
        else if(i==3)
        {
            System.out.println("Choice filling phase is opened");
        }
        else if(i==4)
        {
            System.out.println("Choice filled by student is submitted");
        }
        else if(i==5)
        {
            System.out.println("College is alloted for the student");
        }
    }
    void getrandno()
    {
        System.out.println("Random number assigned for "+name+" is : "+randno);
    }
    void getrank()
    {
        System.out.println("Rank of "+name+" is : "+rank);
    }
    static void getranklist(ArrayList<Student> st)
    {
        int i,j;
        System.out.println("\nRank of all students:");
        System.out.println("\nRANK    RAND-NO    NAME");
        for(i=0;i<st.size();i++)
        {
            for(j=0;j<st.size();j++)
            {
                if(st.get(j).rank==i+1)
                    System.out.println(" "+st.get(j).rank+"       "+st.get(j).randno+"      "+st.get(j).name);
            }
        }
    }
    void viewchoicelist()
    {
        int i;
        if(ch.size()!=0)
        {
            System.out.println("\n"+name+"'s "+"choice list:");
            for(i=0;i<ch.size();i++)
            {
                System.out.println("("+(i+1)+")  "+ch.get(i).cname+" "+ch.get(i).code+" "+ch.get(i).dname);
            }
        }
        else
            System.out.println("Choices not yet filled...!!");
    }
    boolean compare(Choicelist c1,Choicelist c2)
    {
        if(c1.cname.equals(c2.cname)&&c1.code==c2.code&&c1.dname.equals(c2.dname))
            return true;
        return false;
    }
    void updatechoicelist(College[] col,Scanner sc)
    {
        
        int op,cno,colno,depno;
        if(choicestatus)
        {
            while(true)
            {
                System.out.println("\n1 ---> ADD choice\n2 ---> DELETE choice\n3 ---> EXIT");
                System.out.print("OPTION: ");
                op=sc.nextInt();
                if(op==1)
                {
                    if(ch.size()>0)
                    {
                        viewchoicelist();
                    }
                    College.viewcollege(col);
                    System.out.print("COLLEGE NUMBER: ");
                    colno=sc.nextInt();
                    colno--;
                    if(colno<col.length)
                    {
                        col[colno].viewdepartments();
                        System.out.print("DEP-NO: ");
                        depno=sc.nextInt();
                        depno--;
                        if(depno<col[colno].dep.length)
                        {
                            Choicelist choice=new Choicelist(col[colno].code,col[colno].cname,col[colno].dep[depno].dname);
                            int i;
                            for(i=0;i<ch.size();i++)
                            {
                                if(compare(ch.get(i),(choice)))
                                    break;
                            }
                            if(i==ch.size())
                            {
                                ch.add(choice);
                                System.out.println("Choice successfully added...!!");
                            }
                            else
                            {
                                System.out.println("Inputted choice already exists..!!!");
                            }
                        }
                        else
                        {
                            System.out.println("INVALID DEPARTMENT NUMBER....!!");
                        }
                    }
                    else
                    {
                        System.out.println("INVALID college number");
                    }
                }
                else if(op==2)
                {
                    if(ch.size()==0)
                    {
                        System.out.println("Choicelist is empty...!");
                    }
                    else
                    {
                        viewchoicelist();
                        System.out.print("\nEnter the choice number to delete: ");
                        cno=sc.nextInt();
                        cno--;
                        if(cno<ch.size())
                        {
                            ch.remove(cno);
                            System.out.println("Choice successfully deleted..!!");
                        }
                        else
                        {
                            System.out.println("INVALID choice number....!!");
                        }
                    }
                }
                else if(op==3)
                {
                    break;
                }
                else
                {
                    System.out.println("INVALID OPTION ...!!");
                }
            }
        }
        else
        {
            System.out.println("Choices already locked !!!");
        }
    }
    void lockchoicelist()
    {
        choicestatus=false;
    }
    void getallotedcollege()
    {
        if(allotedcollege!=null)
            System.out.println(name+" , you have been alloted "+alloteddept+" department in "+allotedcollege+" college.");
        else
            System.out.println("Sorry "+name+"....you are not alloted any college as per your choice list");
    }
}
class Admin implements Serializable
{
    private static final long serialVersionUID = 1L;
    boolean[] status = { false, false, false, false, false };
    String name, pwd, userid;
    Admin(String name,String userid,String pwd)
    {
        this.name=name;
        this.userid=userid;
        this.pwd=pwd;
    }
    void changepwd(Scanner sc)
    {
        String s1;
        while(true)
        {
            System.out.print("\nOld password: ");
            s1=sc.next();
            if(s1.equals(pwd))
            {
                System.out.print("New password: ");
                s1=sc.next();
                pwd=s1;
                System.out.println("Password successfully changed...!!");
                break;
            }
            else
            {
                System.out.print("Invalid password !!!");
            }
        }
    }
    void assignrandomno(ArrayList<Student> st)
    {
        Random rand=new Random();
        int i,x=rand.nextInt(8999)+1000;
        for(i=0;i<st.size();i++)
        {
            st.get(i).randno=x+i+1;
        }
        System.out.println("Random number successfully assigned...!!");
    }
    void publishrank(ArrayList<Student> st)
    {
        int i,j,temp;
        double temp1;
        double[] mark=new double[st.size()];
        int[] rno=new int[st.size()];
        for(i=0;i<st.size();i++)
        {
            mark[i]=st.get(i).cutoff;
            rno[i]=st.get(i).randno;
        }
        for(i=0;i<st.size();i++)
        {
            for(j=i+1;j<st.size();j++)
            {
                if(mark[j]>mark[i])
                {
                    temp=rno[i];
                    rno[i]=rno[j];
                    rno[j]=temp;

                    temp1=mark[i];
                    mark[i]=mark[j];
                    mark[j]=temp1;
                }
            }
        }
        for(i=0;i<st.size();i++)
        {
            for(j=0;j<st.size();j++)
            {
                if(rno[i]==st.get(j).randno)
                    st.get(j).rank=i+1;
            }
        }
        System.out.println("Ranklist successfully published...!!!");
    }
    boolean findcollege(College[] col,String cname,String dname)
    {
        int i,j;
        for(i=0;i<col.length;i++)
        {
            if(col[i].cname.equals(cname))
            {
                for(j=0;j<col[i].dep.length;j++)
                {
                    if(col[i].dep[j].dname.equals(dname)&&col[i].dep[j].seats!=0)
                    {
                        col[i].dep[j].seats--;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    void allotcollege(ArrayList<Student> st,College[] col)
    {
        int i,j,k;
        for(i=0;i<st.size();i++)
        {
            for(j=0;j<st.size();j++)
            {
                if(st.get(j).rank==i+1)
                {
                    for(k=0;k<st.get(j).ch.size();k++)
                    {
                        if(findcollege(col,st.get(j).ch.get(k).cname,st.get(j).ch.get(k).dname))
                        {
                            st.get(j).allotedcollege=st.get(j).ch.get(k).cname;
                            st.get(j).alloteddept=st.get(j).ch.get(k).dname;
                            break;
                        }
                    }
                    break;
                }
            }
        }
        System.out.println("College allotment successful...!!");
    }
}
public class counselling
{
    static boolean check(ArrayList<Student> st,String name)
    {
        int i;
        for(i=0;i<st.size();i++)
        {
            if(st.get(i).name.equals(name))
                return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException,ClassNotFoundException
    {
        int op,i,sno;
        String name,userid,pwd,cpwd,hno;
        double cutoff;
        
        FileInputStream fin=new FileInputStream("student.ser");
        ObjectInputStream oin=new ObjectInputStream(fin);
        ArrayList<Student> st=(ArrayList<Student>)oin.readObject();
        fin.close();
        oin.close();

        fin=new FileInputStream("admin.ser");
        oin=new ObjectInputStream(fin);
        Admin ad=(Admin)oin.readObject();
        fin.close();
        oin.close();

        fin=new FileInputStream("college.ser");
        oin=new ObjectInputStream(fin);
        College[] col=(College[])oin.readObject();
        fin.close();
        oin.close();

        Scanner fc=new Scanner(new File("student_details.txt"));
        Details[] de=new Details[10];
        while(fc.hasNext())
        {
            name=fc.next();hno=fc.next();cutoff=fc.nextDouble();
            de[Details.dc]=new Details(name,hno,cutoff);
        }
        fc.close();
        

        boolean[] status=new boolean[5];
        for(i=0;i<5;i++)
            status[i]=ad.status[i];

        Scanner sc=new Scanner(System.in);
        System.out.println("\n\n");
        System.out.println("\t\t\t ~x~x~x~x~x~x~x~x~x~x~x~x~x~x~x~x~");
        System.out.println("\t\t\t|                                 |");
        System.out.println("\t\t\t|         TNEA COUNSELLING        |");
        System.out.println("\t\t\t|                                 |");
        System.out.println("\t\t\t ~x~x~x~x~x~x~x~x~x~x~x~x~x~x~x~x~\n");

        label1:
        while(true)
        {
            System.out.print("\n1 ---> LOGIN as admin\n2 ---> LOGIN as student\n3 ---> REGISTER\n4 ---> EXIT\n\nOPTION: ");
            op=sc.nextInt();
            if(op==1)
            {
                System.out.print("\nUser ID: ");
                userid=sc.next();
                System.out.print("Password: ");
                pwd=sc.next();
                
                if(ad.userid.equals(userid)&&ad.pwd.equals(pwd))
                {
                    System.out.println("\n<----- Welcome "+ad.name+" ----->\n");;
                    while(true)
                    {
                        System.out.println("\n1 ---> ASSIGN RANDOM NUMBER\n2 ---> PUBLISH RANK LIST\n3 ---> OPEN CHOICE FILLING\n4 ---> LOCK CHOICE FILLING\n5 ---> ALLOT COLLEGE\n6 ---> Change password\n7 ---> LOGOUT");
                        System.out.print("Option: ");
                        op=sc.nextInt();
                        if(op==1)
                        {
                            if(status[0])
                            {
                                System.out.println("Random number already assigned");
                            }
                            else
                            {
                                ad.assignrandomno(st);
                                status[0]=true;
                            }
                        }
                        else if(op==2)
                        {
                            if(status[1])
                            {
                                System.out.println("Rank is already published");
                            }
                            else
                            {
                                if(status[0])
                                {
                                    ad.publishrank(st);
                                    status[1]=true;
                                }
                                else
                                {
                                    System.out.println("Rank should be published after random number is assigned");
                                }
                            }
                        }
                        else if(op==3)
                        {
                            if(status[2])
                            {
                                System.out.println("Choice filling phase already opened");
                            }
                            else
                            {
                                if(status[1])
                                {
                                    System.out.println("Choice filling phase opened successfully...!!");
                                    status[2]=true;
                                }
                                else
                                    System.out.println("Choice filling phase should be opened after rank is alloted");
                            }
                        }
                        else if(op==4)
                        {
                            if(status[3])
                            {
                                System.out.println("Choice filling phase is already locked");
                            }
                            else
                            {
                                if(status[2])
                                {
                                    System.out.println("Choice filling phase is locked successfully...!!");
                                    status[3]=true;
                                    for(int j=0;j<st.size();j++)
                                        st.get(j).lockchoicelist();
                                }
                                else
                                {
                                    System.out.println("Choice filling phase should be locked after opening it");
                                }
                            }
                        }
                        else if(op==5)
                        {
                            if(status[4])
                            {
                                System.out.println("College already alloted");
                            }
                            else
                            {
                                if(status[3])
                                {
                                    ad.allotcollege(st,col);
                                    status[4]=true;
                                }
                                else
                                {
                                    System.out.println("College should be alloted after locking the choice filling phase");
                                }
                            }
                        }
                        else if(op==6)
                        {
                            ad.changepwd(sc);
                        }
                        else if(op==7)
                        {
                            continue label1;
                        }
                        else
                        {
                            System.out.println("INVALID OPTION");
                        }
                    }
                }
                else
                {
                    System.out.println("Invalid username or password !!!");
                }
            }
            else if(op==2)
            {
                System.out.print("\nUser ID: ");
                userid=sc.next();
                System.out.print("Password: ");
                pwd=sc.next();
                for(i=0;i<st.size();i++)
                {
                    if(st.get(i).userid.equals(userid)&&st.get(i).pwd.equals(pwd))
                        break;
                }
                if(i<st.size())
                {
                    System.out.println("\n<----- Welcome "+st.get(i).name+" ----->\n");
                    sno=i;
                    while(true)
                    {
                        System.out.println("\n1 ---> Get status\n2 ---> View random number\n3 ---> View my rank\n4 ---> View full ranklist\n5 ---> View choicelist\n6 ---> Update choicelist\n7 ---> Lock choice list\n8 ---> View alloted college\n9 ---> Change password\n10 ---> Logout");
                        System.out.print("\nOption: ");
                        op=sc.nextInt();
                        if(op==1)
                        {
                            Student.getstatus(status);
                        }
                        else if(op==2)
                        {
                            if(status[0])
                            {
                                st.get(sno).getrandno();
                            }
                            else
                            {
                                System.out.println("Random number not yet assigned");
                            }
                        }
                        else if(op==3)
                        {
                            if(status[1])
                            {
                                st.get(sno).getrank();
                            }
                            else
                            {
                                System.out.println("Ranklist not yet published");
                            }
                        }
                        else if(op==4)
                        {
                            if(status[1])
                            {
                                Student.getranklist(st);
                            }
                            else
                            {
                                System.out.println("Ranklist not yet published");
                            }
                        }
                        else if(op==5)
                        {
                            if(status[2])
                            {
                                st.get(sno).viewchoicelist();
                            }
                            else
                            {
                                System.out.println("Choice filling phase not yet opened");
                            }
                        }
                        else if(op==6)
                        {
                            if(status[2])
                            {
                                st.get(sno).updatechoicelist(col,sc);
                            }
                            else
                            {
                                System.out.println("Choice filling phase not yet opened");
                            }
                        }
                        else if(op==7)
                        {
                            if(status[2])
                            {
                                if(st.get(i).choicestatus)
                                {
                                    st.get(sno).lockchoicelist();
                                    System.out.println("Choices are locked successfully...!!");
                                }
                                else
                                    System.out.println("Choices already locked...!!");
                            }
                            else
                            {
                                System.out.println("Choice filling phase not yet opened");
                            }
                        }
                        else if(op==8)
                        {
                            if(status[4])
                            {
                                st.get(sno).getallotedcollege();
                            }
                            else
                            {
                                System.out.println("College not yet alloted...!!");
                            }
                        }
                        else if(op==9)
                        {
                            st.get(sno).changepwd(sc);
                        }
                        else if(op==10)
                        {
                            continue label1;
                        }
                        else
                        {
                            System.out.println("INVALID OPTION");
                        }
                    } 
                }
                else
                {
                    System.out.println("Invalid userid or password");
                }
            }
            else if(op==3)
            {
                if(!status[0])
                {
                    System.out.print("\nName in 12th hallticket: ");
                    name=sc.next();
                    System.out.print("12th Hallticket number: ");
                    hno=sc.next();
                    System.out.print("User ID[Eg:spr2k]: ");
                    userid=sc.next();
                    System.out.print("Set Password: ");
                    pwd=sc.next();
                    System.out.print("Re-enter Password: ");
                    cpwd=sc.next();
                    for(i=0;i<st.size();i++)
                    {
                        if(st.get(i).userid.equals(userid))
                            break;
                    }
                    if(i==st.size()&&pwd.equals(cpwd)&&userid.matches("[A-Za-z][_A-Za-z0-9]{3,10}"))
                    {
                        for(i=0;i<Details.dc;i++)
                        {
                            if(de[i].name.equals(name)&&de[i].hno.equals(hno))
                            {
                                break;
                            }
                        }
                        if(i<Details.dc)
                        {
                            if(check(st,name))
                            {
                                st.add(new Student(name,userid,pwd,de[i].cutoff));
                                System.out.println("Successfully registered for the counselling...!!");
                            }
                            else
                            {
                                System.out.println("You have already registered for counselling...!!");
                            }
                        }
                        else
                            System.out.println("Invalid name or hallticket number...!!");
                    }
                    else
                        System.out.println("Invalid username or passwords...!!");  
                }      
                else
                    System.out.println("Counselling has already started !!!");
            }
            else if(op==4)
            {
                System.out.println("\nThank you...!!!\n");
                break;
            }
            else
            {
                System.out.println("INVALID OPTION");
            }
        }
        sc.close();

        FileOutputStream fout=new FileOutputStream("student.ser");
        ObjectOutputStream out=new ObjectOutputStream(fout);
        out.writeObject(st);
        out.close();
        fout.close();

        for(i=0;i<5;i++)
            ad.status[i]=status[i];
        fout=new FileOutputStream("admin.ser");
        out=new ObjectOutputStream(fout);
        out.writeObject(ad);
        out.close();
        fout.close();

        fout=new FileOutputStream("college.ser");
        out=new ObjectOutputStream(fout);
        out.writeObject(col);
        out.close();
        fout.close();
    }
}