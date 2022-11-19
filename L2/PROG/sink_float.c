void swap (float *x, float *y) {
  float tmp = *x;
  *x = *y;
  *y = tmp;
}

void sink(float *a, int l, int r) {
  int i, j;
  int cmp=0;
  int aff=0;
  for (i = l; i < r; ++i) {
    cmp++;
    for (j = r; j > i; --j) {
      cmp++;
      if (a[j] < a[j-1]) {
        swap(a+j-1, a+j);
        aff=aff+3;
        cmp++;
      }
    }
    cmp++;
  }
  cmp++;
  printf("\ncmp=%d et aff=%d",cmp,aff);
}
