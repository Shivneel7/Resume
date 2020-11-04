// A Divide and Conquer based program for maximum subarray sum problem 
#include <iostream> 
#include <limits.h> 
  
using namespace std;

// Find the maximum possible sum in arr[] auch that arr[m] is part of it 
int max(int a, int b){
  if(a < b){
    return b;
  }
  return(a);
}
int maxCrossingSum(int arr[], int l, int m, int h) 
{ 
    // Include elements on left of mid. 
    int sum = 0;
    int left_sum = INT_MIN;
    for (int i = m; i >= l; i--) 
    { 
        sum = sum + arr[i]; 
        if (sum > left_sum) 
          left_sum = sum; 
    } 
  
    // Include elements on right of mid 
    sum = 0; 
    int right_sum = INT_MIN; 
    for (int i = m+1; i <= h; i++) 
    { 
        sum = sum + arr[i]; 
        if (sum > right_sum) 
          right_sum = sum; 
    } 
  
    // Return sum of elements on left and right of mid 
    return left_sum + right_sum; 
} 
  
// Returns sum of maxium sum subarray in aa[l..h] 
int maxSubArraySum(int arr[], int low, int high) 
{ 
   // Base Case: Only one element 
   int l = low;
   int h = high;

   if (l == h) 
     return arr[l]; 
  
   // Find middle point 
   int m = (l + h)/2; 
  int mid = m;
    int left = maxSubArraySum(arr,low,mid);
    int right = maxSubArraySum(arr,mid+1,high);
    int cross = maxCrossingSum(arr, low, mid, high);
   /* Return maximum of following three possible cases 
      a) Maximum subarray sum in left half 
      b) Maximum subarray sum in right half 
      c) Maximum subarray sum such that the subarray crosses the midpoint */
return max(max(cross,left),right);
   
} 
  
/*Driver program to test maxSubArraySum*/
int main() 
{ 
    int size = 0;
    cin >> size;
    int* arr = new int[size];
    for(int i = 0; i < size; i++){
        cin >> arr[i];
    }
    cout << maxSubArraySum(arr, 0, size-1);
   return 0; 
} 
