#include <iostream>
#include <climits>

using namespace std;

void printArr(int** s, int i, int j){
    for(int t = 0; t < i; t ++){
        if(t == 0) {
            cout<< "j: ";
        }else if(t == 1){
            cout << "---";
        }else
            cout << t-1 << ": ";

        for(int m = 0; m < j-2; m ++){
            if(t == 0){
                cout << m+2 << " ";
            }else if(t == 1){
                cout << "--";
            }else
                cout << s[t-1][m+2] << " ";
        }
        cout << endl ;
    }
}

void printOptimal(int** s, int i, int j){
    if(i==j){
        cout << 'A'<<i-1;
    }else{
        cout << '(';
        printOptimal(s,i,s[i][j]);
        printOptimal(s, s[i][j]+1 , j);
        cout << ')';
    }
}

int getOptimal(int** s, int* p, int i, int j){ 
    
    if(i  == j)
        return 0;
    cout << i<< " ";

    return p[i-1]*p[s[i][j]]*p[j] + getOptimal(s,p,i,s[i][j]) +  getOptimal(s,p,s[i][j]+1,j);
}

void chain(int* p, int n){
    int m[n][n];
    int** s = (int**)malloc(sizeof(int*) * (n));
    
    for(int i = 0; i < n; i++){
        m[i][i] = 0;
        s[i] = (int*)malloc(sizeof(int) * n);
    }

    for(int l = 2; l < n; l++){
        for(int i = 1; i < n-l+1; i++){
            int j = i+l-1;
            m[i][j] = INT_MAX;
            for(int k = i; k <= j-1; k++){
                int q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                if(q<m[i][j]){
                    m[i][j] = q;
                    s[i][j] = k;
                }
            }
        }
    }

    cout << m[1][n-1 ] << endl;

    printOptimal(s,1,n-1);

    cout<<endl;

    //////////////////////////

    // printArr(s, n,n);

    // int opt = getOptimal(s,p,1,n-1);

    // cout << endl <<opt << endl;
}

    
int main(){
    int n;

    cin >> n;
    n++;
    int* p = new int[n];

    for(int i = 0; i < n; i ++){
        cin >> p[i];
    }

    chain(p,n);

    return 0;
}