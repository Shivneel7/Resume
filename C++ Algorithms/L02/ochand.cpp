#include <iostream>
#include <climits>

using namespace std;

//Prints an array up to size
void printArray(int* arr, int size){
    for(int i = 0; i < size; i++){
        cout << arr[i] << ";";
    }
}

//prints an array given a start and end
void printArray(int* arr, int start, int size){
    for(int i = start; i < size; i++){
        cout << arr[i] << ";"<<endl;
    }
}

void merge(int* arr, int start, int mid , int end){

    int size1 = mid-start+1;
    int size2 = end-mid;

    //two subarrays.
    int* arr1 = new int[size1+1];
    int* arr2 = new int[size2+1];

    //copying the subarrys which will be merged
    for(int i = 0; i < size1; i++){
        arr1[i] = arr[start + i];
    }

    for(int i = 0; i < size2; i++){
        arr2[i] = arr[mid + i + 1];
    }

    //Sentinels values at the end of the subarrays so that once one subarray is empty,
    //there will still be a number for the other subarray to compare against so the rest
    // of its contents get appended to the end of the array.
    arr1[size1] = INT_MAX;
    arr2[size2] = INT_MAX;

    int i = 0;
    int j = 0;

    for(int k = start; k <= end; k++){
        if(arr1[i] < arr2[j]){
            arr[k] = arr1[i];
            i++;
        }else{
            arr[k] = arr2[j];
            j++;
        }
    }

}

void mergeSort(int* arr, int s, int e){
    if(s<e){
        int m = (e+s)/2;
        mergeSort(arr, s, m);
        mergeSort(arr, m+1, e);
        merge(arr, s, m, e);
    }
    
}

int main(){
    int size = 0;
    cin >> size;
    int* arr = new int[size];
    for(int i = 0; i < size; i++){
        cin >> arr[i];
    }
    mergeSort(arr, 0, size-1);
    printArray(arr, size);
}