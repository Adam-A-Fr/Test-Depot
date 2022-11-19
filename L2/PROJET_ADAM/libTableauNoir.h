#ifndef _TABLEAUNOIR_H
#define _TABLEAUNOIR_H

/** @file */

/*! \mainpage La librairie libTableauNoir

  La librairie <b>libTableauNoir</b> permet d'utiliser facilement une
fenêtre graphique, et fournit des
fonctions permettant d'y dessiner, ainsi que des fonctions permettant
de connaître la position de la souris, et l'état du clavier (la liste
des touches enfoncées), à un instant donné, de jouer des sons et de mesurer
le temps.

\section Dépendances

libTableauNoir repose sur la librairie <b>SDL2</b>. Avant de pouvoir l'utiliser,
il faut que les modules suivants soient installés :
- sdl2
- sdl2_image
- sdl2_mixer

Avec une distribution Debian, par exemple, on peut utiliser la commande suivante :
\code{.sh}
sudo apt-get install libsdl2-dev libsdl2-image-dev libsdl2-mixer-dev
\endcode

\section Installation

libTableauNoir s'installe avec les <i>autotools</i> :
\code{.sh}
./configure
make && sudo make install
\endcode

\section Utilisation
Toutes les déclarations se trouvent dans le fichier <i>@ref libTableauNoir.h</i>.

Les fonctionnalités se décomposent grossièrement en quatre groupes :

\subsection secInit Initialisation de la fenêtre graphique
La fonction essentielle pour initialiser la librairie et pouvoir utiliser
toutes ses fonctionnalités est \ref creerTableau -- à appeler une seule fois,
au tout début d'un programme.

Voir le module \ref init "Initialisation et gestion de la fenêtre" pour plus de détails.

\subsection Affichage

Les fonctions pour afficher des formes géométriques et des fichiers d'image sont
documentées dans le module \ref affichage "Gestion de l'affichage".

\subsection Audio

Les fonctions permettant de jouer des sons depuis des fichiers audio ou de générer des
signaux sonores simples sont documentées dans le module
\ref audio "Gestion du son".

\subsection Interactivité

Les fonctions permettant de réagir aux actions d'un utilisateur sont documentées
dans le module \ref entrees "Gestion des périphériques d'entrée et du temps".
Elles permettent de connaître l'état de la souris et du clavier, ainsi que de
mesurer le temps qui passe.
*/

/** \defgroup biblio Initialisation et gestion de la fenêtre
    \anchor init
    
    La fonction essentielle pour initialiser la librairie et pouvoir utiliser
    toutes ses fonctionnalités est \ref creerTableau -- à appeler une seule fois,
    au tout début d'un programme.
 */

/** \defgroup affichage Gestion de l'affichage
    Ce module permet de gérer l'affichage à l'intérieur de la fenêtre libTableauNoir
*/

/** \defgroup audio Gestion du son
 * Module sonore de libTableauNoir
 */

/** \defgroup entrees Gestion des périphériques d'entrée et du temps
    Ce module permet de lire l'état de la souris, du clavier et de l'horloge
 */


/** \ingroup affichage
    \defgroup dessin Affichage de formes élémentaires
    Les fonctions de dessin ne prennent en argument que les coordonnées
    géométriques des formes à tracer. Le choix des couleurs et de l'épaisseur
    du tracé doit être fait <b>avant</b>, à l'aide des fonctions de sélection
    de stylo.
    
    Un stylo reste actif tant qu'un autre stylo n'a pas été choisi.
*/

/** \ingroup dessin
    \defgroup stylos Stylos utilisés pour les tracés
    Choix des caractéristiques du stylo utilisé pour les tracés
*/

/** \ingroup dessin
    \defgroup formes Tracer et effacer des formes
    Tracé de formes élémentaires (disque, segment, rectangle)
*/


/** \ingroup affichage
    \defgroup image Images
    Affichage des images chargées depuis des fichiers.
    
    Pour afficher une image à l'écran, il faut :
    - charger en mémoire le fichier image, à l'aide de la fonction @ref chargerImage
      qui retourne une structure de type @ref Image. Cette opération doit être faite
      une seule fois pendant l'exécution du programme pour chaque fichier image
    - éventuellement en faire des copies modifiées avec @ref rotozoomImage
    - appeler @ref afficherImage
*/

/** \ingroup affichage
    \defgroup tampon Tampon (buffer)
    Mode de bufferisation.
    Pour améliorer la fluidité et la rapidité de l'affichage, on peut utiliser
    un tampon mémoire pour composer une image sans l'afficher, puis la rendre visible
    seulement une fois qu'elle est terminée.
*/


/** \ingroup entrees
 * \defgroup pause Mise en pause
 * Mise en pause d'un programme dans l'attente d'un évènement.
 * 
 * Les évènements que l'on peut attendre sont :
 * - les actions de l'utilisateur (appui sur une touche du clavier ou de la souris)
 * - le passage du temps
 */

/** \ingroup entrees
 * \defgroup etat Lecture de l'état des périphériques
 * Lecture de l'état des périphériques et mesure du temps qui passe */

/** \ingroup etat */
/***************************************************************************
 * structure de données contenant un état du clavier et de la souris.
 * Cette structure, retournée par la fonction @ref lireEtatSourisClavier, permet de
 * consulter l'état dans lequel le clavier et la souris se trouvait
 * <i>au moment où</i> cette fonction a été appelée.
 ***************************************************************************/

typedef struct {
  int sourisBoutonGauche;      /**< 1 si le bouton gauche  est enfoncé, 0 sinon */
  int sourisBoutonDroit;       /**< 1 si le bouton droit   est enfoncé, 0 sinon */
  int sourisBoutonMilieu;      /**< 1 si le bouton central est enfoncé, 0 sinon */
  int sourisX;                 /**< Position du pointeur de la souris en X. */
  int sourisY;                 /**< Position du pointeur de la souris en Y. */
  
  char touchesClavier[512];    /**< Tableau des touches enfoncées (1 pour une
                                    touche enfoncée, 0 sinon)
                                    Les index des touches A à Z vont de 4 à 29.
                                    Ceux des touches 0 à 9 en ligne vont de 30 à 39.
                                    Ceux des touches 1 à 9 du clavier numérique: de 89 à 97.
                                    Le 0 du clavier numérique a pour code 98.
                                    Flèches droite, gauche, bas, haut : 79 à 82.
                                    cf. https://wiki.libsdl.org/SDLScancodeLookup
                                    Tableau de 512 cases. */
  double temps;                /**< le temps écoulé en secondes
                                    depuis l'initialisation de libTableauNoir */
} EtatSourisClavier;

///\ingroup image
/**********************************************************
 * Structure décrivant une image stockée en mémoire.
 * Une variable de ce type est nécessaire pour les
 * fonctions de manipulation et d'affichage des images.
 *  - le champ @ref chargement_reussi peut être
 *    consulté (voir @ref chargerImage)
 *  - les autres champs sont utilisés en interne
 *    par <i>libTableauNoir</i> et ne doivent pas
 *    être modifiés.
 **********************************************************/

typedef struct
{
  int chargement_reussi;  ///< Égal à 1 si le chargement a réussi, 0 sinon.
  void * surface;         // Champs propre à libTableauNoir. Vous n'aurez pas à les utiliser.
  void * texture; 
  void * render;
  double angle, rx, ry;
  int a_liberer;
} Image;




/***************************************************/
/* structure permettant de stocker des composantes */
/* de couleur et de transparence                   */
/***************************************************/
typedef struct {
    unsigned char r,v,b,a;
} tRVBA ;

/// \ingroup stylos
/*******************************************************
 * structure permettant de stocker les stylos.
 *******************************************************/
/* Ne pas toucher aux champs qui la composent */

typedef struct
{
    int epaisseur;
    tRVBA couleur;
    void * _texture_point;
    void * _texture_effac;
} tStylo ;

/**
* \addtogroup audio
* @{
* \defgroup audiofile Fichiers audio
* Module de lecture de fichiers audio
* - les fichiers doivent d'abord être chargés en mémoire à l'aider
*   de la méthode @ref chargerAudio, qui retourne une structure de type @ref Son
* - pour démarrer la lecture du @ref Son chargé, il faut le passer en paramètre à
*   l'une des méthodes @ref jouerSon ou @ref fadeInSon, qui retourne une @ref PisteAudio.
* - la piste audio peut ensuite être mise en pause, relancée, supprimée et son volume 
*   peut être modifié
*   avec les méthodes @ref mettreEnPausePiste, @ref relancerPiste, @ref supprimerPiste 
*   et @ref volumePiste
*/


/// \addtogroup audiofile
/// @{

/**
**************************************
@brief structure de données permettant de
stocker un fichier audio en mémoire
avant de le jouer                   
**************************************
*/
typedef struct
{
    int chargement_reussi;     ///< Égal à 1 si le chargement a réussi, 0 sinon.
    void * chunk;              ///< Champs propre à libTableauNoir. Vous n'aurez pas à les utiliser.
} Son;

/** représente une piste audio (channel).
 *  type pour les pistes audio */
typedef struct Piste {
  int channel     ;    //  Champs propre à libTableauNoir
  long long usage ;    //  Champs propre à libTableauNoir
} PisteAudio;
/**  @} */

/** \defgroup signal Emission de signaux audio de base
    Ce module permet de diffuser des signaux sonores sinusoïdaux
    ou carrés.
*/
/**  @} */

/** \ingroup signal
    @brief Structure utilisée pour faire référence à un signal en cours de diffusion
*/ 
typedef struct _signal {
  long long idx;    //  Champs propre à libTableauNoir
  long long usage;  //  Champs propre à libTableauNoir
} Signal ;


// **********************************************************************************
//
// Fonctions indispensables.
//
// **********************************************************************************

/** \addtogroup biblio
 ** @{ */

/********************************************************
 * <b>Initialisation indispensable</b> de la bibliothèque.
 *                                          
 * Fonction à appeler en premier
 * pour créer la fenêtre graphique
 ********************************************************/

void creerTableau();

/** Change le titre de la fenêtre libTableauNoir. */
void changerTitre(const char * titre /**< nouveau titre */);

/** désactive/réactive l'image du pointeur de souris
 *
 *  @param invisible 
                     - 1 pour cacher le pointeur de souris
 *                   - 0 pour le réactiver                          */
void cacherSouris( int invisible );

/** Fixe la taille du tableau.
 * La taille initiale est de 800 x 600 */

void fixerTaille(int largeur, int hauteur);

/** enregistre la taille "physique" courante du tableau
 * aux adresses passées en paramètre.                   */

void tailleFenetre(int * largeur, int * hauteur);

/** enregistre la taille de l'écran (en pixels)
 * aux adresses passées en paramètre.          */

void tailleEcran(int * largeur, /**< adresse où stocker la largeur */
		 int * hauteur  /**< adresse où stocker la longueur */);
		 

/** affiche le tableau en mode plein écran. */

void tableauPleinEcran();
    
/** rend la fenêtre redimensionnable par l'utilisateur ou non.
 *
 *   @param r  1 pour la rendre redimensionnable par l'utilisateur  
 *             0 pour empêcher l'utilisateur de la redimensionner   */
void tableauRedimensionnable( int r );

/** fermeture de la fenêtre graphique. */
void fermerTableau() ;

/** @} */


/// \addtogroup stylos
/// @{

// **********************************************************************************
//
// Fonctions des styles et des couleurs.
//
// **********************************************************************************

/** Choix de la couleur du fond.
 *
 * Le résultat n'est visible que lorsqu'une des fonctions d'effaçage est appelée
 * (@ref effacerTableau, @ref effacerPoint, @ref effacerSegment ou @ref effacerRectangle)
 * @param rouge, vert, bleu : composantes de couleur Rouge, Vert, Bleu (chaque composante
 * est un entier compris entre 0 et 255) */

void choisirCouleurFond( int rouge, int vert, int bleu );

/** \brief choix des caractéristiques du stylo que la bibliothèque utilisera
    pour tracer les formes élémentaires
    
    @param taille : largeur de la pointe
    @param rouge, vert, bleu : composantes de couleur Rouge, Vert, Bleu (chaque
    composante est un entier compris entre 0 et 255) */

void choisirTypeStylo( int taille, int rouge, int vert, int bleu );

/** \brief choix de la couleur de remplissage des figures pleines
    
    Seule la fonction @ref tracerRectangle est concernée : le tour du rectangle est
    tracé à la couleur et l'épaisseur du <b>stylo</b>, alors que sa surface intérieure
    est remplie avec la couleur du <b>pinceau</b>.

    @param rouge, vert, bleu : composantes de couleur (entre 0 et 255) */

void choisirCouleurPinceau( int rouge, int vert, int bleu );

/** \brief Choix de l'opacité des tracés
    
    Seul le stylo courant est affecté.
    @param opacite : l'opacité est comprise entre 0 (tracé complètement transparent = invisible)
    et 255 (tracé complètement opaque). */

void fixerOpacite( int opacite );

/** \brief Sauvegarde du stylo courant
    
    Permet de conserver dans une variable les caractéristiques d'un stylo choisies
    avec la fonction @ref choisirTypeStylo.
    @return une structure utilisable avec la fonction @ref selectionnerStylo
*/

tStylo stockerStylo() ;

/** \brief sélection d'un stylo sauvegardé avec la fonction précédente */

void selectionnerStylo( tStylo stylo /**< le stylo à sélectionner pour les tracé subséquents*/);

/** \brief recyclage d'un stylo 

    Cett fonction est obsolète et gardée pour des raisons de  rétro-compatibilité */

void recyclerStylo( tStylo stylo );

/// @}


/// \addtogroup formes
/// @{
// **********************************************************************************
//
// Fonctions de dessin.
//
// **********************************************************************************

/** \brief Effacer la fenêtre

    efface le contenu de la fenêtre en traçant un rectangle de la couleur choisie avec
    @ref choisirCouleurFond
*/

void effacerTableau();

/** Tracer un point.
    Cette fonction trace en fait un <i>disque</i> dont le diamètre est la taille
    du stylo en cours d'utilisation.
    @param x : abscisse du centre
    @param y : ordonnée du centre */

void tracerPoint( double x, double y );

/** Tracer un point de la couleur du fond. */
/** @param x : abscisse du centre
    @param y : ordonnée du centre */

void effacerPoint( double x, double y );

/** Tracer un segment de droite entre deux points.
    @param x1 : abscisse du premier point
    @param y1 : ordonnée du premier point
    @param x2 : abscisse du second point
    @param y2 : ordonnée du second point  */

void tracerSegment( double x1, double y1, double x2, double y2 );

/** Tracer un segment de droite entre deux points
    de la couleur choisie pour le fond.
    @param x1 : abscisse du premier point
    @param y1 : ordonnée du premier point
    @param x2 : abscisse du second point
    @param y2 : ordonnée du second point  */

void effacerSegment( double x1, double y1, double x2, double y2 );

/** Tracer un rectangle.                                          
 *  Le rectangle est tracé avec deux côtés horizontaux et deux
 *  côtés verticaux. Sa position est définie par les deux sommets
 *  d'une de ses diagonales.
    @param x1 : abscisse du premier sommet de la diagonale
    @param y1 : ordonnée du premier sommet de la diagonale
    @param x2 : abscisse du second sommet de la diagonale
    @param y2 : ordonnée du second sommet de la diagonale  */

void tracerRectangle( double x1, double y1, double x2, double y2 );

/** Tracer un rectangle de la couleur du fond.             */
/** 
    @param x1 : abscisse du premier sommet de la diagonale
    @param y1 : ordonnée du premier sommet de la diagonale
    @param x2 : abscisse du second sommet de la diagonale
    @param y2 : ordonnée du second sommet de la diagonale  */

void effacerRectangle( double x1, double y1, double x2, double y2 );

/// @}

/*******************************************************/
/* Fonctions qui attendent une action de l'utilisateur */
/*******************************************************/
/// \addtogroup pause
/// @{

/** suspend l'exécution jusqu'à ce que l'utilisateur clique sur la fenêtre
 * avec le bouton de gauche de la souris.                                 */

void attendreClicGauche();

/** suspend l'exécution jusqu'à ce que l'utilisateur clique sur la fenêtre
 * avec le bouton de droite de la souris.                                 */

void attendreClicDroit();

/** suspend l'exécution jusqu'à ce que l'utilisateur clique sur la fenêtre
 * avec le bouton du milieu de la souris.                                 */

void attendreClicMilieu();

/** suspend l'exécution jusqu'à l'appui d'une touche.
 * @return le code de la touche enfoncée                                  */

int attendreTouche();

/** suspend l'exécution pendant n millisecondes.
 * @param ms : la durée de la pause, en millisecondes                     */

void attendreNms( int ms );

/** suspend l'exécution jusqu'à ce que l'utilisateur
 * ferme la fenêtre du tableau noir.                                      */

void attendreFermeture() ;

/// @}

/**************************************/
/*   Fonctions de consultation d'état */
/**************************************/

/// \addtogroup etat
/// @{

/** Photographie de l'état de la souris, du clavier et de l'horloge.
 * @return l'état des périphériques <b>au moment où la fonction est
 * appelée</b>.
 *
 * L'état évolue forcément au cours du temps, et il faut donc rappeler
 * cette fonction régulièrement pour se tenir au courant.
 */
EtatSourisClavier lireEtatSourisClavier(void);

/** Lire la couleur d'un pixel.
 * @param x : abscisse du pixel
 * @param y : ordonnée du pixel
 * @param [out] rouge  adresse où ranger la composante rouge lue
 * @param [out] vert adresse où ranger la composante verte lue
 * @param [out] bleu adresse où ranger la composante bleue lue
 
 * Note : cette fonction récupère la couleur d'un pixel à partir d'une copie de    
 *        la fenêtre chargée en mémoire. Avant d'appeler cette fonction
 *        il faut donc avoir réalisé cette copie à l'aide de la fonction
 *        @ref chargerPixelsEnMemoire.*/

void lirePixel(int x, int y, int * rouge, int * vert, int * bleu);

/** Lire le contenu de la fenêtre graphique et le stocker en mémoire.
 * Cette fonction doit être appelée avant @ref lirePixel                        */
void chargerPixelsEnMemoire();

/** Indique si l'utilisateur souhaite fermer la fenêtre.
    Consultation de l'état de la fenêtre graphique :
    @return
    - 1 si l'utilisateur a cliqué sur le bouton de fermeture de la fenêtre
    - 0 sinon
*/

int fermetureDemandee() ;

/** Touche pressée.
 * @param key : code de la touche concernée
 * @return
 *         - 1 si la touche est passée de l'état <i>relâché</i>
 * à l'état <i>enfoncé</i>
 *         - 0 sinon         */
int toucheEnfoncee(int key);

/** Touche relâchée.
 * @param key : code de la touche concernée
 * @return
 *         - 1 si la touche est passée de l'état <i>enfoncé</i>
 * à l'état <i>relâché</i>
 *         - 0 sinon         */
int toucheRelachee(int key);

/** Bouton gauche enfoncé.
 * @return
 * - 1 si le bouton gauche de la souris est passé de l'état <i>relâché</i>
 * à l'état <i>enfoncé</i>
 * - 0 sinon
*/
int sourisBoutonGaucheEnfonce();
  
/** Bouton gauche relâché.
 * @return
 * - 1 si le bouton gauche de la souris est passé de l'état <i>enfoncé</i>
 * à l'état <i>relâché</i>
 * - 0 sinon
*/
int sourisBoutonGaucheRelache();

/** Bouton droit enfoncé.
 * @return
 * - 1 si le bouton droit de la souris est passé de l'état <i>relâché</i>
 * à l'état <i>enfoncé</i>
 * - 0 sinon
*/
int sourisBoutonDroitEnfonce();
  
/** Bouton droit relâché.
 * @return
 * - 1 si le bouton droit de la souris est passé de l'état <i>enfoncé</i>
 * à l'état <i>relâché</i>
 * - 0 sinon
*/
int sourisBoutonDroitRelache();

/** Bouton du milieu enfoncé.
 * @return
 * - 1 si le bouton du milieu de la souris est passé de l'état <i>relâché</i>
 * à l'état <i>enfoncé</i>
 * - 0 sinon
*/
int sourisBoutonMilieuEnfonce();
  
/** Bouton du milieu relâché.
 * @return
 * - 1 si le bouton du milieu de la souris est passé de l'état <i>enfoncé</i>
 * à l'état <i>relâché</i>
 * - 0 sinon
*/
int sourisBoutonMilieuRelache();

/** transforme un code de touche en chaîne de caractères.
 * @param code : le code d'une touche (correspondant à un index dans le tableau
 *                  touchesClavier de la structure EtatSourisClavier)
 * @return un pointeur sur la chaîne de caractère décrivant le caractère
 *                  correspondant.                                              */
const char * codeToucheVersChaine(int code);

/// @}

/// \addtogroup image
/// @{
// **********************************************************************************
//
// Fonctions d'images.
//
// **********************************************************************************

/** Chargement d'un fichier d'image en mémoire.
    Les principaux formats de fichier d'images sont reconnus (bmp, jpeg, tiff, png...)
    @param nom_fichier : le chemin d'accès au fichier image
    @return une structure permettant d'accéder à l'image en mémoire
    pour la manipuler et l'afficher.  
    Si l'image a bien été chargée, le champ <b>chargement_reussi</b> de la structure retournée
    contient 1, sinon 0.
*/

Image chargerImage( char * nom_fichier );

/** Chargement d'un fichier d'image en mémoire.
    Si cette image est agrandie par la fonction @ref rotozoomImage, elle ne sera pas lissée,
    contrairement à une image chargée par la fonction @ref chargerImage.
    @param nom_fichier : le chemin d'accès au fichier image
    @return une structure permettant d'accéder à l'image en mémoire
    pour la manipuler et l'afficher.  
    Si l'image a bien été chargée, le champ <b>chargement_reussi</b> de la structure retournée
    contient 1, sinon 0.
 */
Image chargerPixelArt(char * nom_fichier);

/** Définition d'une "couleur de transparence" pour une image.
    Lors de l'affichage de l'image, les pixels de cette couleur seront ignorés. */

void fixerCouleurDeTransparence( Image image, ///< image à modifier
				 int rouge,  ///< composante rouge de la couleur de transparence
				 int vert,  ///< composante verte de la couleur de transparence
				 int bleu  ///< composante bleue de la couleur de transparence
				 );

/** Création d'une image à partir d'une zone rectangulaire de la fenêtre graphique.
    @return une structure permettant d'accéder à l'image en mémoire
    pour la manipuler et l'afficher.
    Si l'image a bien été créée, le champ <b>chargement_reussi</b> de la structure retournée
    contient 1, sinon 0.
 */

Image tn_creer_instantane(int x1, ///< abscisse du premier coin de la zone rectangulaire
			  int y1, ///< ordonnée du premier coin de la zone rectangulaire
			  int x2, ///< abscisse du coin opposé de la zone rectangulaire
			  int y2  ///< ordonnée du coin opposé de la zone rectangulaire
			  );

/** Affichage d'une image chargée en mémoire. */

void afficherImage( Image image, ///< l'image à afficher
		    int x, ///< l'abscisse où placer le bord <b>gauche</b> de l'image
		    int y  ///< l'ordonnée où placer le bord <b>haut</b> de l'image
		    );

/** Libération de la mémoire occupée par une image chargée.
   A utiliser lorsqu'on en a fini avec une image et qu'on n'aura plus l'occasion
   de l'afficher, ni d'afficher une ses copies créées avec la fonction @ref rotozoomImage. */

void libererImage( Image image /**< image à libérer */) ;

/** Création d'une copie modifiée d'une image.
    Cette fonction permet d'obtenir une nouvelle image par rotation et/ou zoom d'une image source.
    L'image source originale n'est pas modifiée.
    La rotation (si l'<i>angle</i> n'est pas nul) est appliquée avant la déformation (si
    <i>zoomX</i> ou <i>zoomY</i> ne sont pas égaux à 1)
*/
Image rotozoomImage( Image source, ///< image source
		     double angle, ///< angle de rotation (en gradients)
		     double zoomX, ///< facteur multiplicatif de zoom en largeur
		     double zoomY  ///< facteur multiplicatif de zoom en hauteur
		     );

/** Retourne la largeur en pixels d'une image. */
int tn_largeur( Image image /**< une image, après modification ou non */);

/** Retourne la hauteur en pixels d'une image */
int tn_hauteur( Image image /**< une image, après modification ou non */);

/// @}

// **********************************************************************************
//
// Fonctions de son
//
// **********************************************************************************
/// \addtogroup audiofile
/// @{

/** @brief Chargement d'un fichier audio en mémoire, depuis un fichier.
    
    Les principaux formats de fichier son sont reconnus (wav, mp3, flac, ogg...)

    @return une structure permettant d'accéder au son en mémoire pour le jouer.
    Si le fichier son a bien été chargé, le champ chargement_reussi de la structure
    retournée contient 1, sinon 0.
    
*/

Son chargerAudio(char * nom_fichier  ///< chemin d'accès au fichier image
		 );

/** @brief Affecte un son à une piste audio libre et démarre la lecture
    
    @return la piste de lecture affectée
*/
PisteAudio jouerSon(Son son,           ///< le son à jouer
		    int nb_repetitions ///< le nombre de répétitions (-1 pour l'infini)
		    );

/** @brief Affecte un son à une piste audio libre et démarre la lecture avec un effet <i>fade in </i>
    (augmentation progressive du volume)
    
    @return la piste de lecture affectée
*/
PisteAudio fadeInSon(Son son,            ///< le son à jouer
		     int nb_repetitions, ///< le nombre de répétitions (-1 pour l'infini)
		     int ms              ///< durée de l'effet <i>fade in</i> en millisecondes
		     );

/**
   @brief Met une piste en pause
*/
void mettreEnPausePiste(PisteAudio piste      ///< piste à mettre en pause
		);

/**
   @brief Relancer la lecture d'une piste en pause
*/
void relancerPiste(PisteAudio piste      ///< piste à relancer
		   );

/**
   @brief Stoppe la lecture d'une piste et la supprime 
*/
void supprimerPiste(PisteAudio piste      ///< piste à supprimer
		   );

/**
   @brief Stoppe la lecture d'une piste avec un effet <i>Fade Out</i> (volume décroissant), puis la supprime 
*/
void fadeOutPiste(PisteAudio piste,      ///< piste à supprimer
		  int ms                 ///< durée de l'effet <i>Fade Out</i> en millisecondes
		   );

/** @brief Fixe le volume d'une piste */
void volumePiste(PisteAudio piste,    ///< piste dont on veut régler le volume
		 double volume        ///< nombre entre 0 (muet) et 1 (max)
		 );

/// @}

/// \addtogroup signal
/// @{

/** Jouer un signal simple.
 * On peut choisir sa fréquence, sa durée, son volume, sa position (gauche/droite) et sa forme.
 * Il n'y a pas un grand choix de forme, cependant...
 *
 * Il n'est pas nécessaire d'attendre la fin de l'émission d'un signal pour en émettre d'autres,
 * qui peuvent se superposer.
 * Il n'y a pas de limite prévue au nombre de signaux simultanés qui peuvent être diffusés.
 * Cependant, de trop nombreux signaux simultanés risquent de ralentir le programme, et/ou
 * de saturer la mémoire de l'ordinateur.
 * @return une structure qui permet de modifier le signal en cours de diffusion. Il est
 * inutile de conserver la valeur retournée si l'on ne prévoit pas de modifier le signal
 * ou de stopper sa diffusion avant la fin de la durée prévue.
 */

Signal jouerSignal(double freq,           ///< fréquence de la note à jouer (440 pour un <i>la 440</i>)
		   double duree,          ///< durée en secondes, utiliser -1 pour l'infini
		   double volume,         ///< volume (entre 0 et 1)
		   double lateral,        ///< position latérale entre -1 (à gauche) et 1 (à droite)
		   int carre              ///< signal carré (1) ou sinusoïdal (0);
		   );

/** @brief changer la fréquence d'un signal en cours d'émission */
void changerFrequenceSignal(Signal s,           ///< signal à modifier
			    double freq         ///< nouvelle fréquence
			    );

/** @brief changer le volume d'un signal en cours d'émission */
void changerVolumeSignal(Signal s,             ///< signal à modifier
			 double volume         ///< nouveau volume
			 );

/** @brief changer la position latérale d'un signal en cours d'émission */
void changerLateralSignal(Signal s,             ///< signal à modifier
			  double lateral        ///< nouvelle position latérale
			  );

/** @brief arrêter un signal en cours d'émission 
 *
 * Le signal ne peut plus être redémarré par la suite.
 */
void stopperSignal(Signal s                     ///< signal à stopper
		   );

/// @}

///\addtogroup tampon
///@{
// **********************************************************************************
//
// Bufferisation (tampon de dessin)
//
// **********************************************************************************

/* Pour fluidifier l'affichage, on peut utiliser
   la bufferisation pour que l'affichage ne soit pas modifié
   au moment de l'appel d'une fonction de dessin, mais seulement
   lors de l'appel à la fonction "tamponner" : tous les dessins
   effectués avant l'appel à cette fonction deviennent visibles
   à ce moment */

/** faut-il utiliser le mode bufferisation ?
 *  @param unOuZero : 1 pour oui, 0 pour non                          
 *  
 *  En mode bufferisation toutes le fonctions censées afficher quelque chose
 *  sur la fenêtre graphique le font sur une autre cible : un tampon graphique
 *  conservé en mémoire, mais invisible. Il en va de même pour les fonctions
 *  d'effaçage.
 */

void fixerModeBufferisation( int unOuZero );

/** affiche à l'écran une copie du contenu actuel du tampon. */

void tamponner();

/// @}

// **********************************************************************************
//
// Temps
//
// **********************************************************************************

/// \ingroup etat
/** Mesure d'un intervalle de temps.
    Cette fonction mesure l'intervalle de temps qui s'écoule entre deux appels successifs.
    La première fois qu'elle est appelée, la fonction @ref delta_temps retourne 0.
    A chaque appel suivant, elle retourne la durée écoulé depuis l'appel précédent.
    @return la durée écoulée depuis l'appel précédent
*/
float delta_temps();

#endif
