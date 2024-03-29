/*
Title:-Implement Parallel Reduction using Min, Max, Sum and Average operations.
*/
#include<iostream>
#include<time.h>
#include<omp.h>
#include<cstdlib>
using namespace std;
int main(){
clock_t start, finish;
int array[100000];
cout << "\n*********Parallel Execution**********\n";
int n = 100000;
srand((int)omp_get_wtime( ));
for(int i = 0; i < n; i++){
array[i] = rand()%100000000;
}
int min = 9999;
int max = -9999;
int min_index;
int max_index;
float sum = 0;
double start_parallel = omp_get_wtime( );
#pragma omp parallel for reduction(+:sum)
for(int i = 0; i < n; i++){
if(array[i] < min){
min = array[i];
min_index = i;
}
if(array[i] > max){
max = array[i];
max_index = i;
}
sum = sum + array[i];
}
double finish_parallel = omp_get_wtime( );
cout << "\nmin is :" << min;
cout << "\nmax is :" << max;
cout << "\nsum is :" << sum;
cout << "\navg is :" << sum/n;
cout << "\ntotal time taken " << finish_parallel - start_parallel << " sec\n";
cout << "\n*********Sequential Execution**********\n";
min = 9999;
max = -9999;
min_index;
max_index;
sum = 0;
start = clock();
for(int i = 0; i < n; i++){
if(array[i] < min){
min = array[i];
min_index = i;
}
if(array[i] > max){
max = array[i];
max_index = i;
}
sum = sum + array[i];
}
finish = clock();
cout << "\nmin is :" << min;
cout << "\nmax is :" << max;
cout << "\nsum is :" << sum;
cout << "\navg is :" << sum/n;
cout << "\ntotal time taken " << finish - start << " sec\n\n";
cout << "Execution time of Parallel is " << ((finish - start) / (finish_parallel - start_parallel))*100 << "times faster than Sequential Processing \n\n";
return 0;
}
/*
OUTPUT
hp@hp-HP-Notebook:~$ g++ assig1.cpp -fopenmp
hp@hp-HP-Notebook:~$ ./a.out
*********Parallel Execution**********
min is :0
max is :9
sum is :284852
avg is :2.84852
total time taken 0.0101763 sec
*********Sequential Execution**********
min is :0
max is :9
sum is :448706
avg is :4.48706
total time taken 1850 sec
Execution time of Parallel is 181795 times faster than Sequential Processing
hp@hp-HP-Notebook:~$ */
