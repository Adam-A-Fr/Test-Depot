#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <string.h>
#include <time.h>
#include "sink_float.c"
#include "shell_float.c"
#include "insertion_float.c"

void tableau_aleatoire(int,int,int,float**);
void tableau_manuel(int,int,int,int,float**);
void afficher_tableau(int,int,int,float**);


void tableau_aleatoire(int i,int j,int taille,float** tab){
    
    for(i=0; i<taille; i++){
        tab[j][i]=(rand() % 50);
    } 
}

void tableau_manuel(int val,int i,int j,int taille,float** tab){
    printf("\nTableau manuel n°%d\n",j);
    for(i=0; i<taille; i++){
        printf("tab[%d] = ", i);
        scanf("%d",&val);
        tab[j][i]=val;
    }    
}

void afficher_tableau(int i,int j,int taille,float** tab){
    printf("\nTableau n°%d\n",j);
    for(i=0; i<taille; i++){
        printf("|tab[%d]=%.1f| ",i,tab[j][i]);
    }
}

int main(int argc, char **argv)
{
    srand(time(NULL));
    if(argc==3)
    {
        char * stoc;
        int i=0,j=0;
        int val=0;

        int taille=strtol(argv[1],&stoc,10);
        printf("Tableau de taille %d",taille);

        float ** tab=malloc(3*sizeof(float*));
        tab[0]= malloc(taille*sizeof(float));
        tab[1]= malloc(taille*sizeof(float));
        tab[2]= malloc(taille*sizeof(float));
    
        
        int test=strtol(argv[2],&stoc,10);

        if(test==0){
            for(j=0; j<3; j++){
                tableau_manuel(val,i,j,taille,tab);
            }    
        }    

        if(test!=0){
            for(j=0; j<3; j++){
                tableau_aleatoire(i,j,taille,tab);
            }    
        }   

        for(j=0; j<3; j++){ 
            printf("\n"); 
            afficher_tableau(i,j,taille,tab);
        } 
 
        for(j=0; j<3; j++){ 
            if(j==0){
                printf("\n"); 
                shell(tab[j],0,taille-1);
                afficher_tableau(i,j,taille,tab);
            }   

            if(j==1){
                printf("\n"); 
                sink(tab[j],0,taille-1);
                afficher_tableau(i,j,taille,tab);
            }    
            if(j==2){
                printf("\n"); 
                insertion(tab[j],0,taille-1);
                afficher_tableau(i,j,taille,tab);
            }    
        }   
        printf("\n");      

    } 
    else{printf("Pas assez d'argument");}
    return 0;
    
}