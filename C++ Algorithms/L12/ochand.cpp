#include <iostream>
#include <vector>
#include <list>
#include <climits>

using namespace std;

class Edge{
    public:
        int u;
        int v;
        int w;

        Edge(int ue, int ve, int we){
            this->u = ue;
            this->v = ve;
            this->w = we;
        }
};

int main(){
    int V;
    int E;

    cin >> V >> E;

    vector<list<Edge>> edges(V);
    vector<int> d(V, INT_MAX);

    for(int i = 0; i < E; i ++){
        int x,y,w;
        cin >> x >> y >> w;
        edges[x].push_back(*new Edge(x,y,w));
    }

    d[0] = 0;

    //Bellman Ford First Loop
    for(int i = 0; i < V; i++){
        for(list<Edge> l: edges){
            for(Edge e : l){
                //relax
                if(d[e.u] != INT_MAX){ // Gotta check this or else we get integer overflow since we add to the MAX_INT
                int newDistance = d[e.u] + e.w;
                    if(d[e.v] > newDistance){
                        d[e.v] = newDistance;
                    }
                }

            }
        }
    }

    //Negative Loop Checker
    for(list<Edge> l: edges){
        for(Edge e : l){
            if(d[e.u] != INT_MAX){
                int newDistance = d[e.u] + e.w;
                if(d[e.v] > newDistance){
                    cout << "FALSE" << endl;
                    return 0;
                }
            }
        }
    }

    cout << "TRUE" << endl;
    for(int distance : d){
        if(distance == INT_MAX)
            cout << "INFINITY" << endl;
        else
            cout << distance << endl;
    }
    return 0;
}