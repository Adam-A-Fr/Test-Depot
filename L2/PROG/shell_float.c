void shell(float *a, int l, int r) {
  int i, j, h;
  int cmp=0;
  int aff=0;
  for (h = 1; 3*h <= r-l-1; h = 3*h + 1){
    cmp++;
  }
  for ( ; h > 0; h /= 3) {
    cmp++;
    for (i = l+h; i <= r; ++i) {
      cmp++;
      for (j = i; j >= l+h && a[j] < a[j-h]; j -= h) {
        cmp++;
        swap(a+j-h, a+j);
        aff=aff+3;
      }
    }
    cmp++;
  }
  cmp++;
  printf("\ncmp=%d et aff=%d",cmp,aff);
}
