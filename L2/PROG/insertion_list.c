typedef float Item;

#define key(A) (A)
#define less(A, B) (key(A) < key(B))

/** Insertion fake node as first node of list pointed by argument */
void insert_fake(struct list *l) {
	add_node_list(l, new_node(0));
}

/** Insertion sort of list pointed by argument */
void insertion_list(struct list *l) {
	struct node *p, *q;
	struct list * tmp = new_list();
	/* Insertion of fake node */
	insert_fake(tmp);
	while (l->size != 0) {
		/* Iterated extraction of first node of l ... */
		p = extract_node_list(l);
		/* ... and insertion of extracted node at correct position in tmp */
		q = tmp->first;
		while ((q->next != NULL) && less(q->next->value, p->value)) {
			q = q->next;
	  }
		p->next = q->next;
		q->next = p;
		++tmp->size;
	}
	/* Deletion of fake node */
	delete_node(extract_node_list(tmp));
	*l = *tmp;
	free(tmp);
}
