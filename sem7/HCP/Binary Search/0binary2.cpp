#include<iostream>
#include<stdlib.h>
#include<omp.h>
#include<time.h>
using namespace std;

int binary(int *, int, int, int);

int binary(int *a, int low, int high, int key)
{
	
	int mid;	
	mid=(low+high)/2;
	int low1,low2,high1,high2,mid1,mid2,found=0,loc=-1;

	#pragma omp parallel sections
	{
	    #pragma omp section
    		{ 
			low1=low;
			high1=mid;
			
			while(low1<=high1)
			{

				if(!(key>=a[low1] && key<=a[high1]))
				{
					low1=low1+high1;
					continue;
				}
				
								mid1=(low1+high1)/2;
				
				if(key==a[mid1])
				{
					found=1;
					loc=mid1;
					low1=high1+1;
				}
					
				else if(key>a[mid1])
				{

					low1=mid1+1;
				}
				
				else if(key<a[mid1])
					high1=mid1-1;
			
			}
		}
				   			
    

    	    #pragma omp section
    		{ 
      			low2=mid+1;
			high2=high;
			while(low2<=high2)
			{
	
				if(!(key>=a[low2] && key<=a[high2]))
				{
					low2=low2+high2;
					continue;
				}
				
				cout<<"here2";
				mid2=(low2+high2)/2;
				
				if(key==a[mid2])
				{

					found=1;
					loc=mid2;
					low2=high2+1;	
				}									
				else if(key>a[mid2])
				{

				low2=mid2+1;
				}
				else if(key<a[mid2])
				high2=mid2-1;

			}	
    		}
	}

	return loc;
}


int main()
{
	clock_t  t1,t2;

	int *a,i,n,key,loc=-1;
	cout<<"\n enter total no of elements=>";
	cin>>n;
	a=new int[n];
	
	cout<<"\n enter elements=>";
	for(i=0;i<n;i++)
	{
	  cin>>a[i];
        }
	
	cout<<"\n enter key to find=>";
	cin>>key;
	
	
	t1 = clock();
	loc=binary(a,0,n-1,key);
	t2 = clock();
	if(loc==-1)
		cout<<"\n Key not found.";
	else
		cout<<"\n Key found at position=>"<<loc+1;
	
	t1 = (t2-t1);
	cout<<"\n Execution time is: "<<t1<<" ms"<<endl;
	return 0;
}
/*apr@C04L0801:~$ g++ omp_binary_search.cpp -fopenmp
apr@C04L0801:~$ ./a.out

 enter total no of elements=>10

 enter elements=>1
2
3
4
5
6
7
8
9
10

 enter key to find=>8
here2
 Key found at position=>8apr@C04L0801:~$ ./a.out

 enter total no of elements=>12

 enter elements=>1
2
3
4
5
6
7
8
9
10
11
12

 enter key to find=>15

 Key not found.apr@C04L0801:~$ 
*/
