#include <iostream>

using namespace std;


//Prints an array up to size
void printArray(int* arr, int size){
    for(int i = 0; i < size; i++){
        cout << arr[i] << ";";
    }
}

//checks if a number is a power of 2
bool isPowerOfTwo(int n){
    if(n == 0){
        return 0;
    }
    while(n!=1){
        if(n%2 != 0){
            return 0;   
        }
        n = n/2;
    }
    return 1;
}

// prints heaps in a more heaplike way. only works for heaps of small size
void printHeap(int *arr, int size){
    int spaces = size/2;
    int counter = 2;
    
    for(int i = 0; i < size; i++){
        if(i == 0){
            for(int j = 0; j < spaces*3; j++){
                cout << " ";
            }
            cout << arr[i] << endl;
        }else{
            for(int j = 0; j < spaces*2; j++){
                cout << " ";
            }
            cout << arr[i];
            counter++;
            if(isPowerOfTwo(counter)){
                cout<< endl;
                spaces = spaces/2;
            }
        }
    }
    cout << endl;
}

void maxHeapify(int* arr, int size, int parent){
    int left = 2 * parent + 1;
    int right = 2 * parent + 2;
    int largest = parent;
    
    if(left < size && arr[left] > arr[parent]){
        largest = left;
    }
    if(right < size && arr[right] > arr[largest]){
        largest = right;
    }
    if(largest != parent){
        int temp = arr[largest];
        arr[largest] = arr[parent];
        arr[parent] = temp;
        maxHeapify(arr, size, largest);
    }
}   

void buildHeap(int* arr, int size){
    for(int i = size/2; i >= 0; i--){
        maxHeapify(arr,size,i);
    }
}

void heapSort(int* arr, int size){
    buildHeap(arr, size);
    //don't need to check last element since it will already be the first element in the list
    for(int i = size-1; i > 0; i--){
        // printHeap(arr, size);
        // cout << endl;
        int temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;
        maxHeapify(arr, i, 0);
    }
    // printHeap(arr, size);
}

int main(){
    int size = 0;
    cin >> size;
    int* arr = new int[size];
    for(int i = 0; i < size; i++){
        cin >> arr[i];
    }
    ///////////////////////////////

    // heapSort(arr, size);
    
    // printArray(arr,size);
/////////////////////////////////////////////////////
    buildHeap(arr,size);
    printArray(arr,size);

    return 0;
}
