#ifndef _LISTE2_H
#define _LISTE2_H

typedef int Item;

struct node2 {
  Item value; /* value of node */
  struct node *prev; /* pointer to previous node */
	struct node *next; /* pointer to next node */
};

struct list2 {
	struct node2 * first;
	struct node2 * last;
	int sizeof;
};

/* allocates memory for one new node (with argument as value) and returns its address */
struct node2 * new_node2(Item);

/* frees memory allocated to node pointed by argument */
void delete_node2(struct node2 *);

/* allocates memory for an empty (doubly linked) list and returns its address */
struct list2 * new_list2();

/* inserts node pointed by second argument as first node of
  list pointed by second argument */
void add_1st_node2_list2(struct list2 *, struct node2 *);

/* inserts node pointed by second argument as last node of
  list pointed by second argument */
void add_last_node2_list2(struct list2 *, struct node2 *);

/* prints all values of list pointed by argument */
void print_list2(const struct list2 *);

/* extracts first node of list pointed by first argument */
/* and returns its address */
struct node2 * extract_1st_node2_list2(struct list2 *);

/* extracts last node of list pointed by first argument */
/* and returns its address */
struct node2 * extract_last_node2_list2(struct list2 *);

/* frees all memory allocated to list pointed by argument */
/* and to its nodes */
void delete_list2(struct list2 *);

#endif

#endif
