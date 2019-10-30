#include<iostream>
#include<stdlib.h>
#include<omp.h>
#include<time.h>
using namespace std;

void parbubble(int *, int);

void bubble(int *, int);
void swap(int &, int &);


void parbubble(int *a, int n)
{
	for(  int i = 0;  i < n;  i++ )
	 {       
		int first = i % 2;      
		#pragma omp parallel num_threads(1)
		#pragma omp parallel for shared(a,first)
		for(  int j = first;  j < n-1;  j += 2  )
		  {       
			if(  a[ j ]  >  a[ j+1 ]  )
			 {       
  				swap(  a[ j ],  a[ j+1 ]  );
			 }       
	      }       
	 }
}

void bubble(int *b, int n)
{
	for(  int i = 0;  i < n;  i++ )
	 {       
		int fi = i % 2;      

		//#pragma omp parallel for shared(a,first)
		for(  int j = fi;  j < n-1;  j += 2  )
		  {       
			if(  b[ j ]  >  b[ j+1 ]  )
			 {       
  				swap(  b[ j ],  b[ j+1 ]  );
			 }       
	      }       
	 }
}

void swap(int &a, int &b)
{

	int test;
	test=a;
	a=b;
	b=test;

}

int main()
{
	clock_t t1,t2;
	int *a,*b,n;
	cout<<"\n enter total no of elements=>";
	cin>>n;
	a=new int[n];
	b=new int[n];
	
	printf("Max number of threads: %i \n",omp_get_max_threads());
	
	#pragma omp parallel
	#pragma omp single
		{
		printf("Number of threads: %i \n",omp_get_num_threads());
		}
	
	cout<<"\n enter elements=>"<<endl;
	for(int i=0;i<n;i++)
	{
		//cin>>a[i];
		a[i] = rand() % (999 + 1 - 2) + 2;
		b[i] = rand() % (999 + 1 - 2) + 2;
		//cout<<a[i]<<endl;
	}
	
	t1= clock();
	parbubble(a,n);
	t2 = clock();
	//cout<<"\n sorted array is=>"<<endl;
	
	/*for(int i=0;i<n;i++)
	{
		cout<<a[i]<<endl;
	}*/
	t1 = (float)(t2-t1);
	cout<<"parallel Execution time is "<<t1<<" ms"<<endl;
	
	t1= clock();
	bubble(b,n);
	t2 = clock();
	//cout<<"\n sorted array is=>"<<endl;
	
	/*for(int i=0;i<n;i++)
	{
		cout<<a[i]<<endl;
	}*/
	t1 = (float)(t2-t1);
	cout<<"sque     Execution time is "<<t1<<" ms"<<endl;
	
return 0;
}

