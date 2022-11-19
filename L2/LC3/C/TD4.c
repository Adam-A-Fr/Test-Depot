#include <fcntl.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define BUFSIZE 1024

void cat(int fin,int fout){
    unsigned char buff[BUFSIZE];
    int nbyte;
    while((nbyte=read(fin,buff,10))>0){
        write(fout,buff,nbyte);
    }
}
int main(int argc, char *argv[]){
    //EX1
    if(0 != (argc-1)){
        printf("Le nombre d'argument est de %d \n",(argc-1));
        int i;
        for(i=1; i<argc; i++){
            printf("Argument %d = %s\n",i,argv[i]);
        }
    }
    else    
        printf("Pas assez d'argument"); 
    //EX2
    int fin=0;
    int fout=0;    
    unsigned char * buff=(unsigned char*) calloc(BUFSIZE,sizeof(unsigned char));

    //cat(fin,fout);
    int fd=open(argv[1],O_RDWR,0660);
    read(fd,buff,16);
    for(int i=0;i<16;i++){
        printf("%02x ",buff[i]);
    }
    putchar('\n');
    lseek(fd,SEEK_END,-16);
    free(buff);
    buff=(unsigned char*) calloc(BUFSIZE,sizeof(unsigned char));
    read(fd,buff,16);
    for(int i=0;i<16;i++){
        printf("%02x ",buff[i]);
    }
    free(buff);
    return 0;
}