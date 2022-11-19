#include "list.h"
#include <stdio.h>
#include <stdlib.h>

struct node * new_node(Item v) {
  struct node * res = malloc(sizeof(struct node));
  res->value = v;
  res->next = NULL;
  return res;
}

void delete_node(struct node * m) {
  free(m);
}

struct list * new_list() {
  struct list * res = malloc(sizeof(struct list));
  res->first = NULL;
  res->size = 0;
  return res;
}

void add_node_list(struct list *l, struct node *m) {
  m->next = l->first;
  l->first = m;
  ++l->size;
}

void print_list(const struct list *l) {
  struct node * m;
  for(m = l->first; m != NULL; m = m->next)
    printf("%3d ", m->value);
  printf("\n");
}

struct node * extract_node_list(struct list *l) {
  struct node * res = l->first;
  if (res != NULL) {
    l->first = res->next;
    --l->size;
    res->next = NULL;
  }
  return res;
}

void delete_list(struct list *l) {
  while (l->size != 0)
    delete_node(extract_node_list(l));
  free(l);
}
