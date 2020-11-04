#include <iostream>

using namespace std;

//Prints an array up to size
void printArray(int* arr, int size){
    for(int i = 0; i < size; i++){
        cout << arr[i] << ";";
    }
    cout << endl;
}

//Insertion sort, returns the sorted array
int* insertionSort(int* arr, int size){
    for(int i = 1; i < size; i++){
        int key = arr[i];
        int j = i-1;
        while(j >= 0 && key < arr[j]){
            int temp = arr[j+1];
            arr[j+1] = arr[j];
            arr[j] = temp;
            j--;
        }
        //printArray(arr, size);
        //prints only the sorted part of the array.
        printArray(arr, i+1);
    }
}


int main(){
    int size = 0;
    cin >> size;
    int* arr = new int[size];
    for(int i = 0; i < size; i++){
        cin >> arr[i];
    }
    //printArray(arr,size);
    int* sorted = insertionSort(arr,size);
}
