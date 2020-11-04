#include <iostream>
#include <climits>

using namespace std;

void printSizes(int* a, int n){
    while(n>0){
        cout << a[n] << " ";
        n -= a[n];
    }
    cout << -1 <<endl;
}

void bottomUpCutRod(int* p, int n, int* r, int* s){

    r[0] = 0;
    for(int j = 1; j <= n; j ++){
        int q = INT_MIN;

        for(int i = 0; i < j; i++){
            if(q < p[i]+ r[j-i-1]){
                q = p[i] + r[j-i-1];
                s[j] = i+1;
            }
        }

        r[j] = q;
    }

}

int main(){
    int n;
    cin >> n;

    int p[n];
    
    int* sizes = (int*)malloc(sizeof(int)*(n+1));

    int* totalPrices = (int*)malloc(sizeof(int)*(n+1));

    for(int i = 0; i < n; i++){
        cin >> p[i];
    }

    bottomUpCutRod(p, n, totalPrices,sizes);

    cout << totalPrices[n] << endl;

    printSizes(sizes,n);

    // for(int i = 0; i <= n+1; i++){
    //     cout<< sizes[i] << " ";
    // }

    free(sizes);
    free(totalPrices);
    
    return 0;
}