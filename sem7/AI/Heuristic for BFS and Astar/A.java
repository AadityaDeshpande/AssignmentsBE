import java.util.*;

public class A {
   static HashMap<String,Integer> hm=new HashMap();
   static ArrayList<String> close=new ArrayList();
   static HashMap<String,Integer> open=new HashMap();
   static int mn=0;
   static Scanner sc=new Scanner(System.in);
   static String arr[][]=new String[20][20];
   static int getVertex=0;

public static void accept()
{     
    System.out.println("enter the number of vertices");
    getVertex=sc.nextInt();
    
    for(int i=0;i<getVertex;i++)
    {
     	System.out.println("Enter the vertex and heursitic value");
     	String ver=sc.next();
     	int heu=sc.nextInt();
     	hm.put(ver,heu);
     	arr[i][0]=ver;
     	
     	System.out.println("Enter no of children");
     	int child=sc.nextInt();
     	
		for(int k=1;k<=child*2;k++) //first increment then check.
	     	{
              System.out.println("Enter the child for "+ver);
              arr[i][k++]=sc.next();
              System.out.println("Enter the distance for "+ver);
              arr[i][k]=sc.next();
	        }
    }
    
    for(Map.Entry m:hm.entrySet())
        System.out.println(m.getKey()+" "+m.getValue());
  	
  	for(int j=0;j<getVertex;j++)
  	{
      	System.out.println();
    	for(int k=0;k<getVertex*2;k++)  
    	{
        	if(arr[j][k]==null)
            	continue;
        	System.out.print(" "+arr[j][k]);
        }
   	}
 }
 
public static void a_star()
{
    int g=0,f=0,flag=0;
    System.out.println("\nEnter the starting and end node");
    String start=sc.next();
    String end=sc.next();
    f=g+hm.get(start);
    close.add(start);
   // open.put(start,f);
   
    while(!(start.equals(end)))
    {
    	for(int k=0;k<getVertex;k++)
    	{
     		if(arr[k][0].equals(start) && arr[k][0]!=null)
     		{
      			for(int h=1;h<=getVertex*2;h=h+2)
      			{
        			if(arr[k][h]!=null) 
        			{
            			g=mn+Integer.parseInt(arr[k][h+1]);
             			flag=1;
             			
					   	System.out.println("\nFor node "+arr[k][h]+"\nDistance value: "+g);
					   	System.out.println("Heuristic value: "+hm.get(arr[k][h]));
						f=g+hm.get(arr[k][h]);
						System.out.println("f("+arr[k][h]+")= "+f);
					  	open.put(arr[k][h],f);
       				}
      			}
      		break;
     		}
    	}
    
    if(flag==0)
    {
    System.out.println("The "+start+" node has no child ...Plz reenter");
    break;
    }
     start=Amin(start); 
   	}
   
}
 public static String Amin(String ma)
 {
     String start="";
     int min=1000;
     System.out.println("Open list so far");
     for(Map.Entry m:open.entrySet())
     {
         System.out.print(m.getKey()+" "+m.getValue()+" | ");
        
     	if((int)m.getValue() < min )
     	  {
          min=(int)m.getValue();
          start=(String)m.getKey();
          }
     }
       open.clear();
       
     for(int i=0;i<getVertex;i++)
     {
		  if(arr[i][0].equals(ma) && arr[i][0]!=null)
		  {
			   for(int j=1;j<=getVertex*2;j=j+2)
			   {
					if( arr[i][j]!=null && arr[i][j].equals(start))
					{
				   		mn=mn+Integer.parseInt(arr[i][j+1]);
				   	}
			   }
		  }
     }
	System.out.println();
    
    close.add(start);
     //open.remove(start);
    System.out.println();
    System.out.println("min Cost: "+min+" traveled till node: "+start);
    System.out.println("Total traveled so far: "+mn);
    //System.out.println("Open after removing");
    System.out.println();
    for(Map.Entry m:open.entrySet())
    	System.out.print(m.getKey()+" "+m.getValue());
    System.out.println("closed after removing");
    System.out.print(close);
    return start;    
 }

public static void bfs()
{
    int i=1,j=0;
    System.out.println("\nEnter the starting and end node");
    String start=sc.next();
    String end=sc.next();
    
    close.add(start);
    while(!(start.equals(end)))
    {
		for(int k=0;k<getVertex;k++)
		{
			if(arr[k][0].equals(start) && arr[k][0]!=null)
			{
		  		for(int h=1;h<=getVertex*2;h=h+2)
		  		{
		    		if(arr[k][h]!=null)
		    		{
		      			open.put(arr[k][h],hm.get(arr[k][h]));
		    		}
		  		}
		  	break;
		 	}
		}
	start=BFSmin(); 
	}
}

public static String BFSmin()
{
     String start="";
     int k=0;
     int min=1000;
     System.out.println("Open list so far");
     for(Map.Entry m:open.entrySet())
     {
        System.out.print(m.getKey()+" "+m.getValue()+" | ");
     	if((int)m.getValue() < min )
     	  {
          	min=(int)m.getValue();
          	start=(String)m.getKey();
          }
     }
     close.add(start);
     open.remove(start);
     System.out.println();
     System.out.println("min Cost: "+min+" traveled till node: "+start);
     //System.out.println("Open after removing");
     System.out.println();
     for(Map.Entry m:open.entrySet())
         System.out.print(m.getKey()+" "+m.getValue());
      System.out.println("closed after removing");
       System.out.print(close);
     return start;    
}


    public static void main(String[] args) {    
    accept();
    System.out.println("\n############## A* Method ##############");  
    a_star();  
    System.out.println("\nOUTPUT OF A* IS: "+close);
    
	open.clear();
	close.clear();
	
	System.out.println("\n############## BFS Method ##############");  
    bfs();
    System.out.print("\nOUTPUT OF BFS IS: "+close+"\n");
    }
   
}

/*
aaditya@aaditya-inspiron-5559:~/Desktop/lp/AI/Heuristic for BFS and Astar$ javac A.java 
Note: A.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
aaditya@aaditya-inspiron-5559:~/Desktop/lp/AI/Heuristic for BFS and Astar$ java A < input 
enter the number of vertices
Enter the vertex and heursitic value
Enter no of children
Enter the child for s
Enter the distance for s
Enter the child for s
Enter the distance for s
Enter the vertex and heursitic value
Enter no of children
Enter the child for a
Enter the distance for a
Enter the child for a
Enter the distance for a
Enter the vertex and heursitic value
Enter no of children
Enter the child for b
Enter the distance for b
Enter the child for b
Enter the distance for b
Enter the vertex and heursitic value
Enter no of children
Enter the child for y
Enter the distance for y
Enter the vertex and heursitic value
Enter no of children
Enter the child for x
Enter the distance for x
Enter the vertex and heursitic value
Enter no of children
Enter the child for c
Enter the distance for c
Enter the vertex and heursitic value
Enter no of children
Enter the child for d
Enter the distance for d
Enter the vertex and heursitic value
Enter no of children
a 5
b 3
s 0
c 2
d 1
e 0
x 5
y 8

 s a 1 b 12
 a y 7 x 4
 b c 18 d 19
 y e 3
 x e 2
 c e 24
 d e 51
 e
############## A* Method ##############

Enter the starting and end node

For node a
Distance value: 1
Heuristic value: 5
f(a)= 6

For node b
Distance value: 12
Heuristic value: 3
f(b)= 15
Open list so far
a 6 | b 15 | 

min Cost: 6 traveled till node: a
Total traveled so far: 1

closed after removing
[s, a]
For node y
Distance value: 8
Heuristic value: 8
f(y)= 16

For node x
Distance value: 5
Heuristic value: 5
f(x)= 10
Open list so far
x 10 | y 16 | 

min Cost: 10 traveled till node: x
Total traveled so far: 5

closed after removing
[s, a, x]
For node e
Distance value: 7
Heuristic value: 0
f(e)= 7
Open list so far
e 7 | 

min Cost: 7 traveled till node: e
Total traveled so far: 7

closed after removing
[s, a, x, e]
OUTPUT OF A* IS: [s, a, x, e]

############## BFS Method ##############

Enter the starting and end node
Open list so far
a 5 | b 3 | 
min Cost: 3 traveled till node: b

a 5closed after removing
[s, b]Open list so far
a 5 | c 2 | d 1 | 
min Cost: 1 traveled till node: d

a 5c 2closed after removing
[s, b, d]Open list so far
a 5 | c 2 | e 0 | 
min Cost: 0 traveled till node: e

a 5c 2closed after removing
[s, b, d, e]
OUTPUT OF BFS IS: [s, b, d, e]
aaditya@aaditya-inspiron-5559:~/Desktop/lp/AI/Heuristic for BFS and Astar$ 
*/
