#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <string.h>

typedef struct {
    int jour;
    int mois;
    int annee;
}date_t;

typedef struct{
char * nom;
char* prenom;
date_t* naissance;
}individu;

typedef struct{
char* titre;
date_t* date_creation;
char* ville_creation;
individu* compositeur;
}opera;

int age_revolu(individu ind, date_t d);
date_t * date_creer(int j, int m, int a);
date_t  date_creerr(int j, int m, int a);

int age_revolu(individu ind, date_t d){
    int cmp = d.annee - (ind.naissance->annee);
    if(ind.naissance->mois < d.mois){
        return cmp-1;
    }
    if(ind.naissance->mois > d.mois){
        return cmp;
    }
    if(ind.naissance->mois == d.mois){
        if(ind.naissance->jour <= d.jour)
            return cmp-1;
        else
            return cmp;    
    }
    return 0;
}


date_t date_creerr(int j, int m, int a){
    date_t date;
    date.jour = j;
    date.mois = m;
    date.annee = a;
    return date;
}

int main (){
    individu ind;
    ind.naissance->annee=2000;
    ind.naissance->mois=9;
    ind.naissance->jour=15;
    date_t  ajrd = date_creerr(14,9,2023);
    printf ("%d \n",age_revolu(ind,ajrd));
    return 0;
}
