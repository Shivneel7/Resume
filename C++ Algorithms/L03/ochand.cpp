#include <iostream>
#include <climits>

using namespace std;

int maxCrossingArray(int* arr, int low, int mid, int high){

    int leftSum = INT_MIN;
    int sum = 0;

    for(int i = mid; i >= low; i--){
        sum += arr[i];
        if(sum > leftSum){
            leftSum = sum;
        }
    }

    int rightSum = INT_MIN;
    sum = 0;

    for(int i = mid + 1; i <= high; i++){
        sum += arr[i];
        if(sum > rightSum){      
            rightSum = sum;
        }
    }

    return (rightSum + leftSum);
}

int maxSubarray(int* arr, int low, int high){

    if(low == high){
        return arr[low];
    }

    int mid = (low + high)/2;
    int left = maxSubarray(arr,low,mid);
    int right = maxSubarray(arr,mid+1,high);
    int cross = maxCrossingArray(arr, low, mid, high);

    int max;
    if(cross > left){
        max = cross;
    }else{
        max = left;
    }
    if(right > max){
        max = right;
    }
    return max;

}

int main(){
    int size = 0;
    cin >> size;
    int* arr = new int[size];
    for(int i = 0; i < size; i++){
        cin >> arr[i];
    }
    cout << maxSubarray(arr, 0, size-1);
    return 0;
}
