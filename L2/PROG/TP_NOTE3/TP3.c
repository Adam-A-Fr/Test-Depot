/*ABADA ADAM 12000059 et HAKIKI MOHAMMED IHEB 12207046*/
//Ceci est un test de maj

#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <string.h>
#include <assert.h>
#include "pile.h"

/* declaration of data types*/
typedef int element_t;

typedef struct node_s *node_t;
struct node_s
{
    element_t value;
    node_t next;
};

typedef struct list_s list_t;
struct list_s
{
    node_t first;
    node_t last;
};

typedef struct var_s var_t;
struct var_s
{
    liste_t l;
    liste_t l1;
    liste_t l2;
    int k;
};

/* declaration of functions */

list_t emptylist();
void delete_list(list_t *);
element_t list_first(list_t);
void list_push_back(element_t, list_t *);
void list_push_front(element_t, list_t *);
int is_empty(list_t);

node_t list_node_remove_first(list_t *);
void list_node_push_back(list_t *, node_t);
void list_node_push_front(list_t *, node_t);

int list_size(list_t);
void list_split(list_t *, list_t *, list_t *);
void list_merge(list_t *, list_t *, list_t *);
list_t list_merge_sort(list_t *);
list_t list_iter_merge_sort(list_t *);

/* Question 1*/
list_t emptylist()
{
    list_t *l = (list_t *)malloc(sizeof(list_t *));
    if (NULL == l)
    {
        perror("echec malloc");
        return *l;
    }
    l->first = NULL;
    l->last = NULL;
    return *l;
}

list_size
// Fonction créer maillon
node_t creermaillon(int val)
{
    node_t l = (node_t)malloc(sizeof(node_t));
    if (NULL == l)
    {
        perror("echec malloc");
        return l;
    }
    l->value = val;
    l->next = NULL;
    return l;
}

// Fonction supression
void delete_list(list_t *a)
{
    assert(NULL != a);
    assert(NULL != a);
    node_t t = a->first;
    node_t ts = t->next;

    // Liberation des maillons

    while (NULL != t)
    {
        free(t);
        t = ts;
        ts = ts->next;
    };

    // Liberation de la struct liste
    free(a);
    a = NULL;
}

// retourne le premier élément
element_t list_first(list_t l)
{
    return l.first->value;
}

// Mettre un element a la fin
void list_push_back(element_t t, list_t *l)
{
    node_t b = creermaillon(t);
    if (NULL != l->first)
    {
        l->last->next = b;
        l->last = b;
    }
    else
    {
        l->first = b;
        l->last = b;
    }
}
// Mettre un element au début
void list_push_front(element_t t, list_t *l)
{
    node_t b = creermaillon(t);
    if (NULL != l->first)
    {
        b->next = l->first;
        l->first = b;
    }
    else
    {
        l->first = b;
        l->last = b;
    }
}
// verifie si la liste est vide
int is_empty(list_t l)
{

    if ((NULL != l.first))
        return 1;
    else
        return 0;
}

// retourne le premier element de la liste est le supprime
node_t list_node_remove_first(list_t *l)
{

    node_t res = l->first;
    if (NULL != res)
    {
        l->first = res->next;
        res->next = NULL;
    }
    return res;
}

// mettre un maillon a la fin
void list_node_push_back(list_t *l, node_t b)
{
    if (NULL != l->first)
    {
        l->last->next = b;
        l->last = b;
    }
    else
    {
        l->first = b;
        l->last = b;
    }
}
// mettre un maillon au debut
void list_node_push_front(list_t *l, node_t b)
{
    if (NULL != l->first)
    {
        b->next = l->first;
        l->first = b;
    }
    else
    {
        l->first = b;
        l->last = b;
    }
}
// retourne la taille
int list_size(list_t l)
{
    node_t tmp = l.first;
    int cpt = 0;

    while (NULL != tmp)
    {
        cpt += 1;
        tmp = tmp->next;
    }
    return cpt;
}
// divise la liste en deux
void list_split(list_t *l, list_t *l1, list_t *l2)
{

    int t = list_size(*l);
    int i = 0;
    while (i < t / 2)
    {
        list_node_push_back(l1, list_node_remove_first(l));
        i++;
    }
    while (i < t)
    {
        list_node_push_back(l2, list_node_remove_first(l));
        i++;
    }
}
// fusionne deux liste en conservant le tri
void list_merge(list_t *l, list_t *l1, list_t *l2)
{

    while ((is_empty(*l1) == 1 || (is_empty(*l2) == 1)))
    {
        if ((!is_empty(*l1)))
        {                                                                                              
            list_node_push_back(l, list_node_remove_first(l2));
        }
        else if ((!is_empty(*l2)))
        {
            list_node_push_back(l, list_node_remove_first(l1));
        }
        else
        {
            if (l1->first->value > l2->first->value)
            {
                list_node_push_back(l, list_node_remove_first(l2));
            }
            else
            {
                list_node_push_back(l, list_node_remove_first(l1));
            }
        }
    }
}

max(0,n%(2*b)+b); min(b,n%2*b);

// affiche la liste
void print_list(list_t *l)
{
    node_t m;
    for (m = l->first; m != NULL; m = m->next)
        printf("%3d ", m->value);
    printf("\n");
}

list_t list_merge_sort(list_t *o)
{
    if (list_size(*o) < 2){
        return *o;
    }    
    else
    {
        list_t l1 = emptylist();
        list_t l2 = emptylist();
        list_split(o, &l1, &l2);
        l1 = list_merge_sort(&l1);
        l2 = list_merge_sort(&l2);
        list_merge(o, &l1, &l2);
        return *o;
    }
}

list_t list_iter_merge_sort(list_t *o){ 
    myStack_s s1 = create_myStack(list_size(o));
    list_t s2 = emptylist();
}

// test des fonctions
int main()
{
    list_t l = emptylist();
    list_t l1 = emptylist();
    list_t l2 = emptylist();
    print_list(&l);
    node_t b = creermaillon(1);
    list_node_push_front(&l, b);
    print_list(&l);
    list_push_back(4, &l);
    print_list(&l);
    list_push_front(2, &l);
    print_list(&l);
    list_push_back(3, &l);
    print_list(&l);
    list_split(&l, &l1, &l2);
    print_list(&l1);
    print_list(&l2);
    list_merge(&l, &l2, &l1);
    print_list(&l);
    print_list(&l1);
    print_list(&l2);
    list_merge_sort(&l);
    print_list(&l); 
    return 0;
}
