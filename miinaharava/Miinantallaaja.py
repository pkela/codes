from random import randint
from math import*
import time

def valinta_ikkuna():

    ##Valinta  
    print("Tervetuloa pelaamaan miinaharavaa!\n")

    while True:
        valinta = input("Valitse 1. jos haluat aloittaa uuden pelin.\nValitse 2. jos haluat katsoa tilastot.\nValitse 3. jos haluat lopettaa.\n")

        if (valinta == "1"):
            print("Valitsit uuden pelin")
         
            ##Kentän luonti
            
            kentankoko = ['a','b']

            while True:
                leveys = input("Anna kentän leveys: ")
                korkeus = input("Anna kentän korkeus: ")

                kentankoko[0] = leveys
                kentankoko[1] = korkeus

                while True:
                    if (kentankoko[0].isdigit() != True) or (kentankoko[1].isdigit() != True):
                        print("\nSyötä leveys ja korkeus kokonaislukuina")
                        break
                        
                    elif(int(kentankoko[0]) <= 0 or int(kentankoko[1]) <= 0):
                        print("\nEihän nuin pienelle kentälle mahdu pommeja ollenkaan!")
                        break
                        
                    else:
                        kentta = kentan_luonti(int(kentankoko[0]), int(kentankoko[1]))
                        kenttaaa = kentta

         
                        ##Pommien luonti
                     
                        pommien_lkm = pommien_maara(kentta)

                        jaljella = []
                        for x in range(int(kentankoko[0])):
                            for y in range(int(kentankoko[1])):
                                jaljella.append((x, y))
                        for i in range (0, pommien_lkm):
                            miinoita_satunnainen(kentta, jaljella)

                        ##Pelaaminen
                        
                        naapurit(kentta)
                        pelaaminen(kenttaaa, leveys, korkeus, pommien_lkm)

        if (valinta == "2"):

            ##Tulostaa kaikkien pelien tulokset        
            print("\nKaikkien pelien tiedot:\n")
            tulosta_tulokset()
            valinta_ikkuna()
            
            return None

        if (valinta == "3"):
            ##Kertoo käyttäjälle hyvästit ja lopettaa ohjelman        
            print("\nTervetuloa pelaamaan uudestaan!")

            exit()
            return None

        else:
            print("Syötä valinta kokonaislukuina 1, 2 tai 3")
    
    return None


def kentan_luonti(leveys, korkeus):
    
    ##Luo kentän ja tarkastaa kentän koon
    
    kentta = []
 
    if korkeus <= 0 or leveys <= 0:
        print("Noin pienelle kentälle ei mahdu ainuttakaan pistettä!")
        kentan_luonti(leveys, korkeus)
        
    ##Luodaan kenttä tekemällä lista, jonka sisälle laitetaan listoja
        
    else:
        j = 0
        while j < korkeus:
            kentta_leveys = []
            i = 0
            while i < leveys:
                kentta_leveys.append("o")
                i = i + 1
            kentta.append(kentta_leveys)
            j = j + 1

    return kentta

def tulosta_kentta(kentta):
 
    ##Tulostaa annetun 2-ulotteisen, planeettaa kuvaavan
    ##listan ihmissilmällä ystävällisessä muodossa.

    print("X", end='  ')
    print("|", end=' ')
        
    for i in range(0, len(kentta[0])):
        if i <= 9:
            print(i, end=' ')
        else:
            print(i, end='')
    print()
    
    for i in range(0, len(kentta[0])+2):
        if i <= 0:
            print("-", end='  ')
        else:
            print("-", end=' ')
    print()
    
    for i in range(0, len(kentta)):
        if i <= 9:
            print(i, end='  | ')
        else:
            print(i, end=' | ')
        print(*kentta[i])
    print()

def miinoita_satunnainen(kentta, jaljella):
    
    ##Asettaa kentälle yhden miinan satunnaiseen, vapaaseen ruutuun ja
    ##palauttaa tämän ruudun koordinaatit.
 
    x = randint(0,(len(kentta[0])-1))
    y = randint(0,(len(kentta)-1))
 
    if kentta[y][x] == "x":
       miinoita_satunnainen(kentta, jaljella)
    else:
        kentta[y][x] = "x"
        jaljella.pop(jaljella.index((x, y)))
        return x, y
            

def pommien_maara(kentta):
    
    ##Pyydetään käyttäjältä pommien määrää ja tarkastetaan, että
    ##pommien määrä mahtuu kentälle

    pommi = ['a']
    
    while True:
        pommit = input("Syötä kuinka monta pommia haluat pelikentälle: ")
        print()
        pommi[0] = pommit
        
        leveys = len(kentta[0])
        korkeus = len(kentta)
        ruutujen_maara = leveys * korkeus

        while True:
            if (pommi[0].isdigit() != True):
                print("Syötä pommien määrä kokonaislukuina")
                break
                        
            elif(int(pommi[0]) >= ruutujen_maara):
                print("Kenttä on liian pieni näin monelle pommille!")
                break
                        
            else:
                return (int(pommi[0]))

def kysy_koordinaatit(leveys, korkeus):
    
    ##Pyydetään käyttäjältä koordinaatit ja tarkastetaan, että ne ovat
    ##kentän sisällä
    
    while True:
        koordinaatit = input("Anna avattavan ruudun koordinaatit: ")
        pisteet = koordinaatit.split()
        print()

        while True:
            if (len(pisteet) == 0):
                print("Anna kaksi koordinaattia välilyönnillä erotettuna")
                break
            elif (len(pisteet) != 2):
                print ("Anna kaksi koordinaattia välilyönnillä erotettuna")
                break
            elif (pisteet[0].isdigit() != True) or (pisteet[1].isdigit() != True):
                print ("Anna koordinaatit kokonaislukuina")
                break
            elif (int(pisteet[0]) < 0) or (int(pisteet[0]) >= leveys) or (int(pisteet[1]) < 0) or (int(pisteet[1]) >= korkeus):
                print("Koordinaatit ovat ruudukon ulkopuolella")
                break
            else:
                return int(pisteet[0]), int(pisteet[1])

def avaa_ruutuja(kentta, pommien_lkm):
    
    ##Luodaan puhdas kenttä, jonka käyttäjä näkee
    
    korkeus = len(kentta)
    leveys = len(kentta[0])
    puhdas_kentta = kentan_luonti(leveys, korkeus)

    ##Tallennetaan tietoja pelistä(Päivämäärä, kellonaika, pelin kesto, tehdyt
    ##tehdyt siirrot ja lopputulos
    
    pvm = time.strftime("%d/%m/%y")
    klo = time.strftime("%H:%M:%S")
    kesto = time.clock()
    siirrot = 0
    lopputulos = 0

    tulosta_kentta(puhdas_kentta)

    ##Pyydetään käyttäjältä koordinaatit ja tarkistetaan onko se pommi
    
    while True:
        x, y = kysy_koordinaatit(leveys, korkeus)

        if x == None and y == None:
            return None
        
        if kentta[y][x] == "x":
            print("\nHävisit pelin\n")
            lopputulos = 1
            siirrot = siirrot + 1
            loppu = time.clock()
            kesto1 = (loppu - kesto) / 60
            kesto2 = round(kesto1, 2)
            tiedot = pelin_tiedot(pvm, klo, kesto2, siirrot, lopputulos)
            tulosta_kentta(kentta)
            valinta_ikkuna()
            return None
        
    ##Jos koordinaatit ei ole pommi, tarkistetaan onko se tyhjä ruutu, jolloin
    ##tehdään floodfill, eli avataan kaikki "tyhjät" ruudut numeroihin asti ja jos
    ##koordinaatit on numero, avataan numero
    
        elif kentta[y][x] == "1":
            puhdas_kentta[y][x] = "1"
            kentta[y][x] = "1"

        elif kentta[y][x] == "2":
            puhdas_kentta[y][x] = "2"
            kentta[y][x] = "2"

        elif kentta[y][x] == "3":
            puhdas_kentta[y][x] = "3"
            kentta[y][x] = "3"

        elif kentta[y][x] == "4":
            puhdas_kentta[y][x] = "4"
            kentta[y][x] = "4"

        elif kentta[y][x] == "5":
            puhdas_kentta[y][x] = "5"
            kentta[y][x] = "5"

        elif kentta[y][x] == "6":
            puhdas_kentta[y][x] = "6"
            kentta[y][x] = "6"

        elif kentta[y][x] == "7":
            puhdas_kentta[y][x] = "7"
            kentta[y][x] = "7"

        elif kentta[y][x] == "8":
            puhdas_kentta[y][x] = "8"
            kentta[y][x] = "8"
               
        else:
            puhdas_kentta, kentta = aukaise_monta(puhdas_kentta, kentta, x, y)

        tulosta_kentta(puhdas_kentta)
        
    ##Katsotaan loppuiko peli
        
        voitto = tarkista_voitto(puhdas_kentta, pommien_lkm)
        siirrot = siirrot + 1

    ##Jos voitto tapahtui, tallennetaan pelin tiedot tiedostoon ja tulostetaan
    ##tiedot käyttäjällekkin
    
        if voitto == 1:
            print("Voitit pelin!\n")
            lopputulos = 2
            loppu = time.clock()
            kesto1 = (loppu - kesto) / 60
            kesto2 = round(kesto1, 2)
            tiedot = pelin_tiedot(pvm, klo, kesto2, siirrot, lopputulos)
            tulosta_pelin_tiedot(tiedot)
            valinta_ikkuna()
            
            return None
        else:
            pass

def pelaaminen(kentta, leveys, korkeus, pommien_lkm):

    ##Kutsutaan pelialgoritmiä
    
    avaa_ruutuja(kentta, pommien_lkm)

def naapurit(kentta):

    ##Luodaan kentälle pommien viereen numerot, jotka kertovat montako pommia on lähettyvillä
    
    x = len(kentta[0])
    y = len(kentta)

    for i in range(len(kentta)):
        for j in range(len(kentta[0])):
            if kentta[i][j] == "x":
                try:
                    for a in range(i-1,i+2):
                        if a >= 0 and a < len(kentta):
                            for b in range(j-1,j+2):
                                if b >= 0 and b < len(kentta[0]):
                                    if kentta[a][b] == "o":
                                        kentta[a][b] = "1"
                                    elif kentta[a][b] == "1":
                                        kentta[a][b] = "2"
                                    elif kentta[a][b] == "2":
                                        kentta[a][b] = "3"
                                    elif kentta[a][b] == "3":
                                        kentta[a][b] = "4"
                                    elif kentta[a][b] == "4":
                                        kentta[a][b] = "5"
                                    elif kentta[a][b] == "5":
                                        kentta[a][b] = "6"
                                    elif kentta[a][b] == "6":
                                        kentta[a][b] = "7"
                                    elif kentta[a][b] == "7":
                                        kentta[a][b] = "8"
                                        
                except IndexError:
                    pass
                
    return kentta

def aukaise_monta(puhdas_kentta, kentta, x , y):

    ##Avataan monta ruutua kerralla, eli jos käyttäjän antama koordinaatti on
    ##tyhjä, niin tämä algoritmi avaa kaikki tyhjät vierestä, kunnes vastaan tulee
    ##numero, joka avataan myös
    
    if (x >= 0 and x < len(kentta[0]) and y >= 0 and y < len(kentta)):
        
        if kentta[y][x] == "o":
            kentta[y][x] = " "
            puhdas_kentta[y][x] = " "

            try:
                for a in range(y-1,y+2):
                    if a >= 0 and a < len(kentta):
                        for b in range(x-1,x+2):
                            if b >= 0 and b < len(kentta[0]):
                                if kentta[a][b] == "1":
                                    kentta[a][b] = "1"
                                    puhdas_kentta[a][b] = "1"
                                elif kentta[a][b] == "2":
                                    kentta[a][b] = "2"
                                    puhdas_kentta[a][b] = "2"
                                elif kentta[a][b] == "3":
                                    kentta[a][b] = "3"
                                    puhdas_kentta[a][b] = "3"
                                elif kentta[a][b] == "4":
                                    kentta[a][b] = "4"
                                    puhdas_kentta[a][b] = "4"
                                elif kentta[a][b] == "5":
                                    kentta[a][b] = "5"
                                    puhdas_kentta[a][b] = "5"
                                elif kentta[a][b] == "6":
                                    kentta[a][b] = "6"
                                    puhdas_kentta[a][b] = "6"
                                elif kentta[a][b] == "7":
                                    kentta[a][b] = "7"
                                    puhdas_kentta[a][b] = "7"
                                elif kentta[a][b] == "8":
                                    kentta[a][b] = "8"
                                    puhdas_kentta[a][b] = "8"

            except IndexError:
                pass
            
            #Vasen
            if x > 0:
                aukaise_monta(puhdas_kentta, kentta, x-1, y)
            #Oikea
            if x < len(kentta[y])-1:
                aukaise_monta(puhdas_kentta, kentta, x+1, y)
            #Ylös
            if y > 0:
                aukaise_monta(puhdas_kentta, kentta, x, y-1)
            #Alas
            if y < len(kentta) -1:
                aukaise_monta(puhdas_kentta, kentta, x, y+1)
            #Vasemmalle alas          
            if x > 0 and y < len(kentta)-1:
                aukaise_monta(puhdas_kentta, kentta, x-1, y+1)
            #Vasemmalle ylös
            if y > 0 and x > 0:
                aukaise_monta(puhdas_kentta, kentta, x-1, y-1)
            #Oikealle alas
            if x < len(kentta[y]) -1 and y < len (kentta) -1:
                aukaise_monta(puhdas_kentta, kentta, x + 1, y + 1)
            #Oikealle ylös
            if x < len (kentta[y]) - 1 and y > 0:
                aukaise_monta(puhdas_kentta, kentta, x + 1, y - 1)
    
    return (puhdas_kentta, kentta)

def tarkista_voitto(puhdas_kentta, pommien_lkm):
    
    ##Tarkistetaan voittiko käyttäjä vertaamalla avaamattomien koordinaattien
    ##määrää pommien määrään
    
    aukaisematta = 0
    voitto = 0

    for i in range(len(puhdas_kentta)):
        for j in range(len(puhdas_kentta[0])):
            if puhdas_kentta[i][j] == "o":
                aukaisematta = aukaisematta + 1


    if aukaisematta == pommien_lkm:
        voitto = 1


    return voitto


def pelin_tiedot(pvm, klo, kesto2, siirrot, lopputulos):

    ##Tallennetaan yhden pelin tiedot listaan
    
    tiedot = []
    pelintiedot = []

    if lopputulos == 1:

        pelintiedot.append(pvm)
        pelintiedot.append(klo)
        pelintiedot.append(kesto2)
        pelintiedot.append(siirrot)
        pelintiedot.append("Häviö")

    elif lopputulos == 2:

        pelintiedot.append(pvm)
        pelintiedot.append(klo)
        pelintiedot.append(kesto2)
        pelintiedot.append(siirrot)
        pelintiedot.append("Voitto")

    tiedot.append(pelintiedot)

    ##Kutsutaan funktiota, joka tallentaa pelin tiedot tiedostoon, jossa kaikkien
    ##pelien tiedot ovat
    
    tulokset(tiedot)


    return tiedot

def tulosta_pelin_tiedot(tiedot):

    ##Tulostetaan pelin tiedot käyttäjän nähtäville

    print("Pelin päivämäärä: {}".format(tiedot[0][0]))
    print("Pelin kellonaika: {}".format(tiedot[0][1]))
    print("Pelin kesto: {} minuuttia".format(tiedot[0][2]))
    print("Pelissä tehdyt siirrot: {} siirtoa".format(tiedot[0][3]))
    print("Pelin lopputulos: {}".format(tiedot[0][4]))
    print()


def tulokset(tiedot):

    ##Kirjoitetaan pelin tiedot tiedostoon, jossa kaikkien pelien tiedot ovat

    with open("tulokset.txt", "a") as kohde:
        for i in range(len(tiedot)):
            tulokset = ''
            for j in range(len(tiedot[i])):
                tulokset += ('{},'.format(tiedot[i][j]))
            tulokset = tulokset[:-1]
            kohde.write("{}\n".format(tulokset))

    return None

def tulosta_tulokset():

    ##Tulostetaan kaikkien pelien tiedot käyttäjän nähtäväksi

    with open("tulokset.txt") as lahde:
        tulokset = lahde.readlines()

    for i in range(len(tulokset)):
        dulos = ''
        dulos = tulokset[i]
        dulos = dulos.strip("\n")
        lista = []
        lista = dulos.split(",")

        print("Pelin päivämäärä: {}".format(lista[0]))
        print("Pelin kellonaika: {}".format(lista[1]))
        print("Pelin kesto: {} minuuttia".format(lista[2]))
        print("Pelissä tehdyt siirrot: {} siirtoa".format(lista[3]))
        print("Pelin lopputulos: {}".format(lista[4]))
        print()
        
    return None

if __name__ == '__main__':

##Kutsutaan valintaikkunaa, jossa käyttäjä voi päättää haluaako pelata, katsoa
##tuloksia, vai lopettaa pelin

    valinta_ikkuna()
