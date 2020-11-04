#include <iostream>
#include <vector>
#include <string>

using namespace std;

void printVec(int* v, int size){
    for(int i = 0; i < size; i ++){
        cout << v[i] << ";";
    }
}

void print2DArr(int** arr, int size, int v){
    for(int i = 0; i < size; i ++){
        printVec(arr[i], v);
        cout << endl;
    }
}

void countingSort(int** a, int size, int k, int digit, int** b){

    int* c = new int[k];

    for(int i = 0; i < size; i ++){
        c[a[i][digit]]++;
    }

    for(int i = 1; i < k; i ++){
        c[i] = c[i] + c[i-1];
    }

    for(int i = size-1; i >= 0; i--){

        // c[a[i][digit]]--;
        // b[c[a[i][digit]]] = a[i];
        
        b[--c[a[i][digit]]] = a[i]; // this also works, which is pretty cool
        
    }

    for(int i = 0; i < size; i++){
        a[i] = b[i];
    }
}

void radixSort(int** arr, int size, int** b, int k){
    for(int i = 9; i >= 0; i--){
        countingSort(arr,size, k, i, b);
    }
}

int main(){

    //Size of each vector Default: 10
    int VECSIZE = 3;

    //Number of possible values for each digit Default: 3
    int K = 5;
    
    //number of vectors
    int size = 0;
    cin >> size;

    int** arr = (int**)malloc(sizeof(int*) * size);

    //auxillary memory for counting sort
    int** b = (int**)malloc(sizeof(int*) * size);


    for(int i = 0; i < size; i++){
        arr[i] =  new int[VECSIZE];
        b[i] = new int[VECSIZE];

        for(int j = 0; j < VECSIZE; j++){
            cin >> arr[i][j];
        }
    }
///////////////////////////////////
    //radixSort(arr,size, b, K);

    //print2DArr(arr,size, VECSIZE);


//////////////////////////
    cout<<endl;
    countingSort(arr,size,K, 2,b);
    print2DArr(arr,size,VECSIZE);
    cout<<endl;
    countingSort(arr,size,K, 1,b);
    print2DArr(arr,size,VECSIZE);
    free(arr);

    return 0;   
}