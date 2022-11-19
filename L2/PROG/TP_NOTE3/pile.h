#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

typedef struct var_s var_t;
struct var_s
{
    liste_t l;
    liste_t l1;
    liste_t l2;
    int k;
};

typedef struct list_s list_t;
struct list_s
{
    node_t first;
    node_t last;
};

typedef var_t array_element_t;

typedef  struct myStack_element_s myStack_element_t;
struct myStack_element_s {
	array_element_t * t;
	int n;
	int p;
	int line;
};

typedef struct myStack_s  myStack_t;
struct myStack_s {
	int capacity;
	int top;
	myStack_element_t * values;
};

myStack_t * create_myStack(int);
void delete_myStack(myStack_t **);
myStack_element_t pop(myStack_t *);
myStack_element_t top(const myStack_t *);
void push(myStack_element_t, myStack_t *);
int is_emptyp(myStack_t *);
int is_full(myStack_t *);


myStack_t * create_myStack(int n) {
	myStack_t * s = (myStack_t*)malloc(sizeof(myStack_t));
	if (NULL == s) {
		perror("malloc fails");
		return NULL;
	}
	
	s->top = -1;

	s->values = (myStack_element_t*)malloc(sizeof(myStack_element_t) * n);
	if (NULL == s->values) {
		free(s);
		s->capacity = 0;
		return NULL;
	}
	
	s->capacity = n;
	
	return s;
}

void delete_myStack(myStack_t ** ps) {
	assert(NULL != ps);
	assert(NULL != *ps);
	
	if (NULL == (*ps)->values) {
		free((*ps)->values);
	}
	
	free(*ps);
	*ps = NULL;
}

myStack_element_t pop(myStack_t * s) {
	assert(NULL != s);
	assert(! is_emptyp(s));
	assert(NULL != s->values);
	return s->values[(s->top)--];
}

myStack_element_t top(const myStack_t * s) {
	assert(NULL != s);
	assert(! is_emptyp(s));
	assert(NULL != s->values);
	return s->values[(s->top)];
}

void push(myStack_element_t x, myStack_t * s) {
	assert(NULL != s);
	assert(! is_full(s));
	assert(NULL != s->values);
	s->values[++(s->top)] = x;
}

int is_emptyp(myStack_t * s) {
	assert(NULL != s);
	return (-1) == s->top;
}

int is_full(myStack_t * s) {
	assert(NULL != s);
	return s->top == (s->capacity - 1);
}
