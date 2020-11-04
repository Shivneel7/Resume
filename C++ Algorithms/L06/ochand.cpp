#include <iostream>
#include <list>
#include <string>
#include <vector>

using namespace std;

vector<list<int>> table;



void makeTable(int size){

    for(int i = 0; i < size; i++){
        table.push_back(list<int>());
    }

}

//Prints contents of lists
void printList(list<int> l){

    for(int i : l){
        cout << i << "->"; 
    }
}

//prints contents of the hashTable
void printTable(int size){
    for(int i = 0; i < size; i ++){
        cout << i << ":";
        printList(table[i]);
        cout << ";" << endl;
    }
}

int h(int k, int m){
    return (k % m);
}

int main(){
    int size;
    cin >> size;

    makeTable(size);
    
    string input;
    cin >> input;

    while(input != "e"){
        char c = input[0];

        if(c == 'o'){ // print
            printTable(size);

        }else{
            
            int k = stoi(input.substr(1));  
            list<int>* l = &table[h(k,size)];

            //insert
            if(c == 'i'){
                l->push_front(k);

            //delete
            }else if(c == 'd'){ // delete

                bool found = 0;
                for(list<int>::iterator it = l->begin(); it != l->end(); it++){
                    
                    if(*it == k){
                        l->erase(it);
                        cout << k << ":DELETED;\n";
                        found = true;
                        break;
                    }
                }
                if(!found)
                    cout<<k<<":DELETE_FAILED;\n";

            //search        
            }else if(c == 's'){ // search

                int count = 0;
                bool found = 0;

                for(int i: *l){
                   if(i == k){
                       cout << k <<":FOUND_AT"<< h(k,size) <<","<< count << ";\n";
                       found = true;
                       break;
                   }else
                       count++;
                }
                if(!found)
                    cout<< k <<":NOT_FOUND;\n";
            }
        }
        cin >> input;
    }
}