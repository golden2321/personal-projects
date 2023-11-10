# Auteurs : Mariama Dianquinh Bambara (20077631)
# et Riste Popov (20241764)
# Date : 19 octobre 2023
#
# Programme qui génère un labyrinthe

from PIL import Image
import math
from random import *

# Classe qui permet d'avoir une structure de données qui peuvent être accédé en 
# appelant un objet ex: obj = struct(id = 5, value = 6) donc: obj.id = 5
class struct:
    def __init__(self, **fields):
        self.__dict__.update(fields)
    def __repr__(self):
        return 'struct('+(', '.join(list(map(lambda f:f+'='+repr(self.__dict__[f]),
        self.__dict__))))+')'

# Fonction pour créer un écran selon une taille
def setScreenMode(largeur, hauteur):
    return Image.new('RGB', (largeur, hauteur), "white")

# Fonction pour mettre à jour un pixel
def setPixel(image, x, y, color):
    pixels = image.load()
    pixels[x, y] = color

# La procedure tracerMur prend en parametre un tableau, la largeur de l'écran,
# la dimension des cellules, et une variable booleene égale à True si c'est 
# le tableau des murs vertical ou False pour les murs horizontales, et trace 
# chacun des murs en noir
def tracerMur(tab,largeur,dimension,siVerti, image):    
    if siVerti == True:
        largeur = largeur +1
    for i in range(len(tab)):
        ligne = tab[i] // largeur
        colonne = tab[i] % largeur
        lignePixel = ligne*dimension + ligne
        colonnePixel = colonne*dimension + colonne

        for _ in range(dimension + 2):
            setPixel(image, colonnePixel, lignePixel, (0, 0, 0))
            if siVerti == True:
                lignePixel += 1
            else:
                colonnePixel += 1  

# La fonction voisin prend la coordonnée (coordX, coordY) d'une cellule et la 
# taille d'une grille (largeur=nx et hauteur=ny) et retourne un tableau 
# contenant le numéro des cellules voisines. 
def voisins(coordX, coordY, largeur, hauteur):
    tabVoisins = []
    if coordX > 0:
        celluleOuest = coordY * largeur + coordX - 1
        tabVoisins.append(celluleOuest)
    if coordX < largeur - 1:
        celluleEst = coordY * largeur + coordX + 1
        tabVoisins.append(celluleEst)
    if coordY > 0:
        celluleNord = (coordY-1) * largeur + coordX
        tabVoisins.append(celluleNord)
    if coordY < hauteur - 1:
        celluleSud = (coordY+1) * largeur + coordX
        tabVoisins.append(celluleSud)
    return tabVoisins


# La fonction retirer prend en paramètre un tableau et un element x
# puis retourne le même tableau sans l'élement donné en parametre
def retirer(tab, x):
    tab.remove(x)
    return tab       

# La fonction ajouter prend en paramètre un tableau  et un élement x
# puis retourne le même tableau contenant en plus l'élement donne en parametre
def ajouter(tab, x):
    if x not in tab:
        return tab + [x]
    return tab

# La fonction contient prend en paramètre un tableau avec des structures et 
# un élement x retourne True si x est est contenu dans la structure numCell
def contient(tab, x):

    for element in tab:
        if element.numCell == x:
            return True
    else:
        return False

# La fonction sequence prend un entier non-négatif n en paramètre et retourne 
# un  tableau de longueur n contenant en ordre les valeurs entières de 0 à n-1 
# inclusivement. 
def sequence(longueur):
    tableau = list(range(longueur))
    return tableau

# La fonction coordonnee prend en paramètre le numero d'une cellule et la 
# largeur d'une grille, puis retourne le numero de la ligne et de la colonne 
# de la cellule de cette grille
def coordonnee(cellule, largeur):
    coordX = cellule % largeur
    coordY = cellule // largeur
    return struct(x=coordX,y=coordY)
    

# La fonction murCellule prend en paramètre une cellule; la largeur et la 
# hauteur d'une grille et retourne les murs intérieurs de la cellule
def murCellule(cellule, largeur, hauteur):
    ligne = cellule // largeur
    colonne = cellule % largeur
    murHoriz = []
    murVerti = []
    if colonne > 0:
        murOuest = colonne + ligne * (largeur+1)
        murVerti = ajouter(murVerti,murOuest)
    if colonne < largeur - 1:
        murEst = 1 + colonne + ligne * (largeur+1)
        murVerti = ajouter(murVerti,murEst)
    if ligne > 0:
        murNord = colonne + ligne * largeur
        murHoriz = ajouter(murHoriz,murNord)
    if ligne < hauteur - 1:
        murSud = colonne + (ligne+1) * largeur
        murHoriz = ajouter(murHoriz,murSud)

    return struct(murHoriz = murHoriz, murVerti = murVerti)



# La fonction murAEnlever prend en paramètres randomFront (cellule au hasard 
# dans front), le tableau cave; la largeur et la hauteur du labyrinthe , pour 
# trouver les voisins de front et voir si ces voisins sont dans cave; et 
# retoune une structure contenant le numero d'un mur et son orienation
def murAEnlever(randomFront, cave, largeur, hauteur):
    
    coord = coordonnee(randomFront.numCell, largeur)
    # retourne les voisines de la cellule au hasard de front
    voisinsFront = voisins(coord.x,coord.y,largeur, hauteur) 

    # vérifie si voisinsFront sont dans cave
    voisinCave = []
    for val in voisinsFront:
        if contient(cave, val):
            voisinCave= ajouter(voisinCave,val)
    # remplace voisinCave avec une valeur au hasard dans voisinCave        
    voisinCave = voisinCave[math.floor(random()*len(voisinCave))]
    voisinCave = retournStructCellule(voisinCave) 

    # compare tout les murs des deux cellules pour trouver un mur commun
    for murFront in randomFront.murHoriz:
        for murCave in voisinCave.murHoriz:
            if murFront == murCave:
                return struct(mur = murFront, orientation = "horiz")
    for murFront in randomFront.murVerti:
        for murCave in voisinCave.murVerti:
            if murFront == murCave:
                return struct(mur = murFront, orientation = "verti")
            

# La fonction retournStructCellule prend en paramètre un numero de cellule et 
# retourne une structure avec les cellules
def retournStructCellule(numeroCell):
    global grille
    cellule = struct(numCell = numeroCell, murHoriz = 
          grille[numeroCell].murHoriz, murVerti = grille[numeroCell].murVerti)
    return cellule

# La fonction initialiser prend en parametre la largeur et la hauteur de la 
# grille et les tableaux contenant tous les murs horizontales et verticales de 
# la grille. Fait la première iteration de la cavité initiale. Retourne 
# des tableaux et valeurs qui sont utilisé dans iterationCaveFront
def initialiser(largeur,hauteur,verti,horiz):
    
    tabInitialisation = [None]*7         # valeurs de la première itération
    cellules = sequence(largeur*hauteur) # tableau avec toutes les cellules
    
    global grille
    grille = [] # structure avec numero et murs des cellules
    for cellule in cellules:
        tempMurs = murCellule(cellule, largeur, hauteur)
        grille.append(struct(numCell = cellule, murHoriz = tempMurs.murHoriz,
                                                 murVerti = tempMurs.murVerti))
        
    cellInitiale = grille.copy()  # tableau avec toutes les cellules avant 
                                  # qu'elles soient mis dans cave et front    
    tabInitialisation[0]=cellInitiale
    
    # contiennent structures avec murs et numero des cellules
    cave = [] 
    front = []

    # Trouver la cavité initiale au hasard
    cellHasard = math.floor(random()*len(cellInitiale))
    cave.append(cellInitiale.pop(cellHasard))
    retirer(cellules,cellHasard)

    # Touver les cellules voisine à la cavité initiale (front)
    coordCave = coordonnee(cave[0].numCell,largeur)
    for voisin in voisins(coordCave.x, coordCave.y, largeur, hauteur):
        retirer(cellules,voisin)
        front.append(retournStructCellule(voisin))

    #cellule au hasard dans front
    randomFront = front[math.floor(random()*len(front))] 


    murRetire = murAEnlever(randomFront, cave, largeur, hauteur)
    if murRetire.orientation == "verti":
        verti.remove(murRetire.mur)
    else:
        horiz.remove(murRetire.mur)    
    
    # enlever randomFront de front et ajouter a cave
    nouvCave = front.pop(front.index(randomFront))
    cave.append(nouvCave)
    
    # ensemble avec plusieurs tableaux et valeurs 
    # réutilisé dans iteractionCaveFront
    tabInitialisation[1]=front
    tabInitialisation[2]=verti
    tabInitialisation[3]=horiz
    tabInitialisation[4]=cave
    tabInitialisation[5]=nouvCave
    tabInitialisation[6]=cellules
        
    return tabInitialisation


# La procedure iteractionCaveFront prend en parametre un tableau contenant les 
# données d'initiatialisation pour une itération permettant de remplir les 
# tableaux front et cave, et d'éliminer les murs des
# tableaux horiz et verti qui ne seront pas tracés dans le labyrinthe
def iterationCaveFront(tabInit,largeur,hauteur):
    
    # récuperer les variables et les tableau de la première itération
    cellInitiale=tabInit[0];front=tabInit[1];verti=tabInit[2]
    horiz=tabInit[3]; cave=tabInit[4]; nouvCave=tabInit[5];cellules=tabInit[6]
    
    
    while len(front) > 0:
        # Trouver les cellules voisine de la nouvelle cellule de 
        # la cavite et les rajouter a front
        # Vérifier que les voisines font partie du tableau cellules
        if cellInitiale != 0:
            nouvCaveCoord = coordonnee(nouvCave.numCell,largeur)
            for voisin in voisins(nouvCaveCoord.x, nouvCaveCoord.y, 
                                                             largeur, hauteur):
                if voisin in cellules:
                    cellules.remove(voisin)
                    front.append(retournStructCellule(voisin))

        randomFront = front[math.floor(random()*len(front))]

        murRetire = murAEnlever(randomFront, cave, largeur, hauteur)
        if murRetire.orientation == "verti":
            verti.remove(murRetire.mur)
        else:
            horiz.remove(murRetire.mur)  

        nouvCave = front.pop(front.index(randomFront))
        cave.append(nouvCave)


# La procédure laby crée et dessine un labyrinthe aléatoire noir sur fond blanc 
# largeur=nx et de hauteur=ny). 
def laby(largeur, hauteur, dimension):
    
    nbLigneHoriz = hauteur + 1
    nbLigneVerti = largeur + 1
    largeurEcran = largeur*dimension + nbLigneVerti
    hauteurEcran = hauteur*dimension + nbLigneHoriz


    # tableau des murs horizontal et vertical
    horiz = sequence(largeur*(hauteur+1))
    verti = sequence((largeur+1)*hauteur)

    # Pour enlever le premier et dernier mur
    horiz.pop(largeur * (hauteur + 1) - 1)
    horiz.pop(0)
    
    tableauInit=initialiser(largeur,hauteur,verti,horiz)
    iterationCaveFront(tableauInit,largeur,hauteur)
    
    # commencer par un ecran blanc
    image = setScreenMode(largeurEcran, hauteurEcran)    
    for i in range(largeurEcran):
        for j in range(hauteurEcran):
            setPixel(image, i, j, (255,255,255))

    # imprimer chacun des murs dans horiz
    tracerMur(horiz,largeur,dimension,False, image)
    # imprimer chacun des murs dans verti
    tracerMur(verti,largeur,dimension,True, image)

    image.show()
    
laby(20,15,25)




