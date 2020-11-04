#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

//Prints an array up to size
void printArray(int* arr, int size){
    for(int i = 0; i < size; i++){
        cout << arr[i] << ";";
    }
}

int partition(int* arr, int p, int r){

    
    ////////random pivot
    srand(time(NULL));
    int random = (rand() % (r-p)) + p;

    int x = arr[random];
    arr[random] = arr[r];
    arr[r] = x;
    /////////


    int i = p - 1;

    for(int j = p; j <= r-1; j++){
        if(arr[j] <= x){
            i++;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    int temp = arr[i+1];
    arr[i+1] = arr[r];
    arr[r] = temp;

    return i + 1;
}

void quickSort(int* arr,int p ,int r){
    if(p < r){
        int q = partition(arr, p, r);
        quickSort(arr, p, q-1);
        quickSort(arr, q+1, r);
    }
}

int main(){
    int size = 0;
    cin >> size;
    int* arr = new int[size];
    for(int i = 0; i < size; i++){
        cin >> arr[i];
    }
    quickSort(arr,0 ,size-1);
    printArray(arr,size);
    return 0;
}
