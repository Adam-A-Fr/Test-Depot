

void insertion(float *a, int l, int r) {
  int i, j;
  int cmp=0;
  int aff=0;
  for (i = l+1; i <= r; ++i) {
    cmp++;
    for (j = i; j > l && a[j] < a[j-1]; --j) {
      cmp++;
      swap(a+j-1, a+j);
      aff=aff+3;
    }
    cmp++;
  }
  cmp++;
 printf("\ncmp=%d et aff=%d",cmp,aff);
}
