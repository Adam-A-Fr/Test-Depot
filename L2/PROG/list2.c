#include <liste2.h>
#include <stdio.h> /* Pour printf */
#include <stdlib.h> /* Pour NULL, malloc, free */

struct node2 * new_node2(Item v) {
	struct node2 * res = malloc(sizeof(struct node2));
	res->value = v;
	res->prev = NULL;
	res->next = NULL;
	return res;
}

void delete_node2(struct node2 *m) {
	free(m);
}

struct list2 * new_list2() {
	struct list2 * res = malloc(sizeof(struct list2));
	res->first = NULL;
	res->last = NULL;
	res->size = 0;
	return res;
}

void add_1st_node2_list2(struct list2 *l, struct node2 *m) {
	if (l->size == 0) {
		l->first = m;
		l->last = m;
	}
	else {
		m->next = l->first;
		l->first->prev = m;
		l->first = m;
	}
	++l->size;
}

void add_last_node2_list2(struct list2 *l, struct node2 *m) {
	if (l->size == 0) {
		l->first = m;
		l->last = m;
	}
	else {
		l->last->next = m;
		m->prev = l->last;
		l->last = m;
	}
	++l->size;
}

struct node2 * extract_1st_node2_list2(struct list2 *l) {
	struct node2 * res = NULL;
	if (l->size != 0) {
		l->size --;
		res = l->first;
		l->first = res->next;
		if (l->first != NULL)
			l->first->prev = NULL;
		else
			l->last = NULL;
		res->next = NULL;
	}
	return res;
}

struct node2 * extract_last_node2_list2(struct list2 *l) {
	struct node2 * res = NULL;
	if (l->size != 0) {
		l->size --;
		res = l->last;
		l->last = res->prev;
		if (l->last != NULL)
			l->last->next = NULL;
		else
			l->first = NULL;
		res->prev = NULL;
	}
	return res;
}

void print_list2(const struct list2 *l) {
	struct node2 * tmp;
	for (tmp = l->first; tmp != NULL; tmp = tmp->next) {
		printf("%f ", tmp->value);
	}
	printf("\n");
}

void delete_list2(struct list2 *l) {
	while (l->size != 0)
		delete_node2(extract_1st_node_list2(l));
	free(l);
}
