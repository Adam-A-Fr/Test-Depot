/*declaration of data types*/
#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <string.h>
#include <assert.h>

typedef int element_t;
struct binary_tree_s {
    element_t label;
    struct binary_tree_s * left;
    struct binary_tree_s * right;
};

typedef struct binary_tree_s binary_tree_t; 

/*declaration of functions*/

binary_tree_t * empty_tree();
binary_tree_t * cons_binary_tree(element_t,binary_tree_t *,binary_tree_t *);
void delete_binary_tree(binary_tree_t **);

binary_tree_t * left(binary_tree_t *);
binary_tree_t * right(binary_tree_t *);

element_t get_binary_tree_root(const binary_tree_t *);
int is_empty_binary_tree(const binary_tree_t *);

void display_element(element_t);

binary_tree_t * empty_tree(){
    
    binary_tree_t  * t = (binary_tree_t *)malloc(sizeof(binary_tree_t));
    if (NULL == t)
    {
        perror("echec malloc");
        return t;
    }
    return t;

}

binary_tree_t * cons_binary_tree(element_t e,binary_tree_t * L,binary_tree_t * R){
    binary_tree_t  *t = empty_tree();
    t->label = e;
    t->left= L;
    t->right = R; 
    return t;
}

void delete_binary_tree(binary_tree_t ** r){
    if(! is_empty_binary_tree(*r)){
        delete_binary_tree(&((*r)->left));
        delete_binary_tree(&((*r)->right));
    }
    free(*r);
    *r=NULL;
}

binary_tree_t * left(binary_tree_t * t){
    return t->left;
}

binary_tree_t * right(binary_tree_t * t){
    return t->right;
}

element_t get_binary_tree_root(const binary_tree_t * t){
    return t->label;
}

int is_empty_binary_tree(const binary_tree_t * t){
    return (NULL == t);
}

void display_element(element_t t){
    printf("(%d)",t);
    
}

int binary_tree_size (const binary_tree_t * pt){
    if(is_empty_binary_tree(pt)) return 0;
    return 1 + binary_tree_size(left(t))+binary_tree_size(right(t));
}


// test des fonctions
int main()
{
    binary_tree_t  *t=cons_binary_tree(42,NULL,NULL);
    display_element(t->label);
    delete_binary_tree(&t); 
    return 0;
}






