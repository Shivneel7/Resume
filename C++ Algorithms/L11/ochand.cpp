#include <iostream>
#include <vector>
#include <list>
#include <queue>
#include <climits>

using namespace std;

struct edge{
    int v;
    int w;

    edge(int v,int w){
        this->v = v;
        this->w = w;
    }
};

struct Vertex{
    public:
        int id;
        int pi = -1;
        int d = INT_MAX;
        bool inQueue = true;
        bool isValid = true;

        list<edge> adj;

        void addEdge(int v, int w){
            adj.push_back(*(new edge(v,w)));
        }

        void print(){
            cout<< id << ": " << d << " pi: "<<pi << " isValid: " << isValid<<" ";
        }

        void printEdges(){
            cout<< id << ": ";
            for(edge ed: adj){
                cout <<  ed.v << "(" << ed.w << ") -> ";
            }
        }

        Vertex(int i){
            this->id = i;
        }
};

struct compare{
    bool operator()(const Vertex* a, const Vertex* b){
        return a->d > b->d;
    }
};

void printEdges(vector<Vertex*> v, int n){
    for(int i = 0; i < n; i ++){
        v[i]->printEdges();
        cout << endl;
    }
}

int main(){
    int v;
    int e;

    cin >> v;
    cin >> e;

    vector<Vertex*> vertexes = vector<Vertex*>(v);
    priority_queue<Vertex*, vector<Vertex*>,compare> pq;
   
    for(int i = 0; i < v; i ++){
        vertexes[i] = new Vertex(i);
        if(i==0){
            vertexes[i]->d = 0;
        }
        pq.push(vertexes[i]);
    }

    for(int i = 0; i < e; i ++){
        int x, y, w;
        cin >> x;
        cin >> y;
        cin >> w;
        vertexes[x]->addEdge(y,w);
        vertexes[y]->addEdge(x,w);
    }
    
    //printEdges(vertexes,v);

    while(!pq.empty()){
        Vertex* u = pq.top();
        pq.pop();
        u->inQueue = false;

        if(u->isValid){
            for(edge ed: u->adj){
                Vertex* v = vertexes[ed.v];
                if(v->inQueue && ed.w < v->d){

                    //make new vertex, push it to pq, and invalidate old one
                    Vertex* copy = new Vertex(v->id);
                    copy->pi =  u->id;
                    copy->d = ed.w;
                    copy->adj = v->adj;

                    v->isValid = false;

                    pq.push(copy);
                    
                    vertexes[ed.v] = copy;
                }
            }
        }
     }

    for(int i = 1; i < v; i ++){
        cout << vertexes[i]->pi << endl;
    }
}