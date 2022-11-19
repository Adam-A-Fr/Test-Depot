typedef float Item;

#define key(A) (A)
#define less(A, B) (key(A) < key(B))
#define exch(A, B) {Item t = A; A = B; B = t;}

void insertion_list2 (struct list2 * l) {
  struct node2 *p, *q;
  if (l->size < 2) {return; }
  for (p = l->first; p != NULL; p = p->next) {
    for (q = p; q->prev != NULL && less(q->value, q->prev->value); q = q->prev) {
      exch(q->prev->value, q->value);
    }
  }
}
