//Shivneel Chand
//Huffman Code Generator using Binary Search Tree for 6 character documents
#include <iostream>
#include <vector>
#include <string>

using namespace std;

//Nodes that belong to both a Huffman Tree and Binary Search Tree
struct Node{
    char c;

    int freq;

    Node* left;
    Node* right;
    Node* huffLeft;
    Node* huffRight;

    Node(char ch, int f){
        this->c = ch;
        this->freq = f;
        this->left = NULL;
        this->right = NULL;
        this->huffLeft = NULL;
        this->huffRight = NULL;
    }
};

//Used to insert the Nodes into a fresh BST before applying Huffman's Code to seach for the nodes with the lowest values.
Node* insertBST(Node* root, char c, int freq){
    if(root == NULL)
        root = new Node(c,freq);

    else if(freq < root->freq)
        root->left = insertBST(root->left, c,freq);

    else
        root->right = insertBST(root->right, c, freq);
    
    return root;
}

//Overloaded
// adds a new Node with a char of '#' to represent a combined tree, Takes in two nodes min1, and min2 to be the
// children of the Node that we are inserting
Node* insertBST(Node* root, int freq, Node* min1, Node* min2){
    if(root == NULL){
        root = new Node('#', freq);

        if(min1->freq < min2->freq){
            root->huffLeft = min1;
            root->huffRight = min2;
        }else{
            root->huffLeft = min2;
            root->huffRight = min1;
        }

    }else if(freq < root->freq)
        root->left = insertBST(root->left, freq, min1, min2);

    else
        root->right = insertBST(root->right, freq, min1, min2);

    return root;
}

// Finds the minimum Node in the BST and removes it from the tree 
Node* findMin(Node* root, Node* father){
    if(root->left == NULL){
        //if the head is the lowest, then return the head, and make head's right child the new head 
        //(the head becomes the right child in the function that calls findMin)
        if(father == NULL)
            return root;
        
        //if the lowest is not the head, and not a leaf, then it has a right child, so set the right child of
        // the parent node to point to the child of the node to be removed
        if(root->right != NULL)
            father->left = root->right;

        // if the root is a leaf, then return root, and set father->left to NULL to remove it from the tree
        else
            father->left = NULL;
        
        return root;
    }

    return findMin(root->left, root);
}

//Fills the array with the codes for each character
void getCodes(Node* root, string* codes, char* current,int i){

    if(root->c != '#'){
        // A is index 0, B is 1, etc
        int index = int(root->c) - 65;
        
        string temp;
        temp += root->c;
        temp += ":";

        // Fills the string with the code
        for(int j = 0; j < i; j ++ )
            temp += char(current[j]);
        
        codes[index] = temp;

    }else{

        current[i] = '0';
        getCodes(root->huffLeft, codes, current, i+1);

        current[i] = '1';
        getCodes(root->huffRight, codes, current , i+1);    
    }
}

int main(){

    Node* head = NULL;

    //Get the frequencies for each character
    for(int i = 0; i < 6; i++){
        int temp;
        cin >> temp;
        head = insertBST(head, 65 + i, temp);
    }

    //get lowest two nodes, combine, repeat
    //5 iterations because there are 6 items in the list.
    for(int i = 0; i < 5; i ++){

        Node* min1 = findMin(head, NULL);
        //if head is returned then head was the lowest root, and should be replaced with its right child
        if(min1 == head)
            head = head->right;
        

        Node* min2 = findMin(head, NULL);
        //if head is returned then head was the lowest root, and should be replaced with its right child
        if(min2 == head)
            head = head->right;
        

        //Combine the two minimum Nodes and add the new combined Node into the BST
        head = insertBST(head, min1->freq + min2->freq, min1, min2);
    }

    //get the codes
    //string* codes holds the huffman codes in alphabetical order
    string* codes = new string[6];

    char* current = new char[6];
    getCodes(head, codes, current,0);

    for(int i = 0; i < 6; i ++)
        cout << codes[i] << endl;
    
    return 0;
}