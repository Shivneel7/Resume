//Shivneel Chand
//Strongly Connected Component Calculator
#include <iostream>
#include <vector>
#include <list>

using namespace std;

class Vertex{
    public:
        int start; // start times are not needed for this alorithm, but I might as well have them for future code
        int finish;
        int SCCID;
        bool isFresh = true; // from the textbook, white = true; black = false, gray doesnt matter for the actual algo
        
        void print(){
            cout << "start: " << start << " end: " << finish << " isFresh: " << isFresh;
        }
};

//The three functions below help me debug the code by allowing me to see my data structs
void printVertexes(vector<Vertex> v, int n){
    for(int i = 0; i < n; i ++){
        cout << i << ":";
        v[i].print();
        cout<< endl;
    }
}

void printList(list<int> l){
    for(int i : l)
        cout << i << "->"; 
}

void printEdges(vector<list<int>> e, int n){
    for(int i = 0; i < n; i ++){
        cout << i << ":";
        printList(e[i]);
        cout << ";" << endl;
    }
}
////////////////////////////////////////////////////////////

//Reverses the edges
vector<list<int>> reverse(vector<list<int>> edges, int v){
    vector<list<int>> result = vector<list<int>>(v);
    for(int i = 0; i < v; i++){
        for(int e: edges[i]){
            result[e].push_back(i);
        }
    }
    return result;
}

//Used for first DFS, sets the finishing times.
int DFS(vector<Vertex>& vertexes, const vector<list<int>>& edges, int i, int time, list<int>& sorted){
    time++;
    Vertex* v = &vertexes[i];
    v->start = time; // fun fact, start times are not needed for the algorithm
    v->isFresh = false;
    for(int adj : edges[i]){
        if(vertexes[adj].isFresh){
            time = DFS(vertexes, edges, adj, time, sorted);
        }
    }

    time ++;
    v->finish = time;//fun fact, since I set the sorted order in the next line, this is not needed for the algo either
    //I actually do not need to keep track of the time at all since I set the order using the recursion. 
    sorted.push_front(i);//sets the sorted order
    return time;
}

//Used for the second DFS
void DFS(vector<Vertex>& vertexes, const vector<list<int>>& edges, int i, list<int>& results){
    Vertex* v = &vertexes[i];
    v->isFresh = false;
    results.push_back(i);

    for(int adj : edges[i]){
        if(vertexes[adj].isFresh){
            DFS(vertexes, edges, adj, results);
        }
    }

}

//returns the minimum value in a list of ints
int getMin(const list<int>& l){
    int min = l.front();
    for(int i : l)
        if(i < min)
            min = i;
    return min;
}

//Sets the SSCID of each vertex to the lowest ID in the SCC
void setMin(const list<int>& SCC, vector<Vertex>& vertexes){
    int min = getMin(SCC);
    for(int i : SCC)
        vertexes[i].SCCID = min;
}

int main(){
    int v;
    cin >> v;
    
    vector<list<int>> edges = vector<list<int>>(v);
    vector<Vertex> vertexes = vector<Vertex>(v);

    int e;
    cin >> e;

    for(int i = 0; i < e; i ++){
        int x, y;
        cin >> x;
        cin >> y;
        edges[x].push_back(y);
    }
    

    //saves indices of vertexes sorted in decreasing order of their finish time
    list<int> order;

    //First DFS
    int time = 0;
    for(int i = 0; i < v; i ++){
        if(vertexes[i].isFresh){
            time = DFS(vertexes, edges, i, time, order);
        }
    }
    

    //reset the vertexes
    for(Vertex& temp : vertexes){
        temp.isFresh = true;
    }


    //Second DFS
    vector<list<int>> reversed = reverse(edges,v);
    for(int i : order){
        list<int> SCC;
        if(vertexes[i].isFresh){
            DFS(vertexes, reversed, i, SCC);
            setMin(SCC,vertexes); // set the SCCID of each vertex to the minimum ID of the SCCID
        }
    }

    //Print results
    for(Vertex v : vertexes){
        cout << v.SCCID << endl;
    }

    return 0;
}