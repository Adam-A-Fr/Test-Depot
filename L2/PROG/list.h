#ifndef list_H
#define list_H

typedef int Item;

struct node {
  Item value; /* value of node */
  struct node *next; /* pointer to next node */
};
struct list {
  struct node *first; /* pointer to first node of list */
  int size; /* number of nodes in list */
};

/* allocates memory for one new node (with argument as value) and returns its address */
struct node * new_node(Item);

/* frees memory allocated to node pointed by argument */
void delete_node(struct node *);

/* allocates memory for an empty list and returns its address */
struct list * new_list();

/* inserts node pointed by second argument as first node of
  list pointed by second argument */
void add_node_list(struct list *, struct node *);

/* prints all values of list pointed by argument */
void print_list(const struct list *);

/* extracts first node of list pointed by first argument */
/* and returns its address */
struct node * extract_node_list(struct list *);

/* frees all memory allocated to list pointed by argument */
/* and to its nodes */
void delete_list(struct list *);

#endif
