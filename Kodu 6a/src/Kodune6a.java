/*****************************************************************************************
 *Programmeerimisharjutused. LTAT.03.007
 *2018/2019 kevadsemester
 *
 *Kodutöö. Ülesanded nr 6 a
 *
 *Teema: 1) Massiivi elemendite summa leidmine- iteratiivselt, unaar-rekursiooniga, binaar-rekursiooniga.
 *       2) Massiivis elementide kohtade vahetamine paarikaupa unaar-rekursiooniga.
 *       3) Massiivist märgimuutude arvu leidmine- iteratiivselt, unaar-rekursiooniga, binaar-rekursiooniga.
 *
 *                          --- Käitlemine käsureal ---
 *                     Kompileerimine:
 *                                  >javac -encoding utf8 Kodune6a.java
 *                     Käivitamine:
 *                                  >java Kodune6a
 *                                  >java Kodune6a > tulemus.txt
 *
 * Mõningane eeskuju: http://tufangorel.blogspot.com/2015/09/binary-recursive-sum-of-integer-array-in-java.html
 * Autor: Karl Taal
 ******************************************************************************************/

import java.util.*;

public class Kodune6a {

    //Siin asuvad testmeetodid, millele antakse ette vajalikud andmed.
    public static void main(String[] args) {

        //Luuakse massiiv juhumassiivide pikkustega.
        int[] suurused1 = {0, 1, 2, 8, 15, 5000};
        testÜl1(suurused1);         //Ülesanne 1

        System.out.println();
        System.out.println();
        System.out.println();

        //Luuakse massiiv juhumassiivide pikkustega.
        int[] suurused2 = {0, 1, 2, 8, 15};
        testÜl2(suurused2);         //Ülesanne 2

        System.out.println();
        System.out.println();
        System.out.println();

        //Luuakse massiivid testimiseks ja pannakse omakorda massiivi.
        int[] j1 = {0, 0, 4, 0, 0, 0, 0, 0, 0, 0, -9, 0, 0, 0};
        int[] j2 = {22, 0, -9, 8, -9, 0, 0, -12, 18, 0, 28, 0, 25, 0, 25, 0, 10};
        int[] j3 = {24, 0, 0, -1, -6, 0, 0, 38, 19, 0, 28, 0, -2, 0, 9};
        int[] j4 = {33, 0, -8, 3, -11, 27, 0, -4, 16, -4, 0, 24, 4, -15, 0, 0};
        int[] j5 = {29, 35, 26, -1, -3, 34, 17, 20, 11, 2, 1};
        int[][] massiivid = {j1, j2, j3, j4, j5};
        testÜl3(massiivid);         //Ülesanne 3
    }


    /**
     * Massiivi summa leidmine iteratiivsel.
     * Rakendamine etteantud massiivi elementide summa leidmiseks: summaIteratiivselt(massiiv)
     *
     * @param massiiv Antud int tüüpi massiiv.
     * @return Tagastab massiivi elementide summa.
     * Autor: Karl Taal.
     */
    public static int summaIteratiivselt(int[] massiiv) {
        int summa = 0;              //Esialgseks summaks määratakse 0.
        for (int i : massiiv) {     //Läbitakse kõik elemendid massiivis ühekaupa.
            summa += i;             //Iga element liidetakse summale juurde.
        }
        return summa;               //Tagastatakse summa.
    }


    /**
     * Massiivi summa leidmine unaar-rekursiooniga.
     * Rakendamine etteantud massiivi elementide summa leidmiseks: summaUnaarRek(massiiv, 0, 0)
     *
     * @param massiiv Antud int tüüpi massiiv.
     * @param summa   Antud esialgne summa null.
     * @param i       Antud esimese elemendi indeks null.
     * @return Tagastab massiivi elementide summa.
     * Autor: Karl Taal.
     */
    public static int summaUnaarRek(int[] massiiv, int summa, int i) {
        if (i == massiiv.length) {      //Kui indeks on võrdne massiivi pikkusega, siis on jõutud massiivi lõppu.
            return summa;               //Tagastatakse summa.
        }
        // Igal uuel väljakutsel suurendatakse indeksid 1 võrra ja summale liidetakse antud indeksiga element juurde.
        return summaUnaarRek(massiiv, summa + massiiv[i], i + 1);
    }


    /**
     * Massiivi summa leidmine binaar-rekursiooniga.
     * Rakendamine etteantud massiivi elementide summa leidmiseks: summaBinaarRek(massiiv, 0, massiiv.length-1)
     *
     * @param massiiv       Antud int tüüpi massiiv.
     * @param esimeneindeks Antud massiivi esimene indeks.
     * @param viimaneindeks Antud massiivi viimane indeks.
     * @return Tagastab massiivi elementide summa.
     * Autor: Karl Taal.
     */
    public static int summaBinaarRek(int[] massiiv, int esimeneindeks, int viimaneindeks) {
        if (esimeneindeks > viimaneindeks) {    //Esimene baas kontrollib, kas esimene indeks on läinud suuremaks kui viimane indeks.
            return 0;                           //Kui jah, siis tagastakse lihtsalt null.
        }
        if (esimeneindeks == viimaneindeks) {   //Teine baas kontrollib, kas jõutud jaotamisega sinnamaale, et esimene ja teine indeks on võrdseks läinud.
            return massiiv[esimeneindeks];      //Kui jah, siis tagastakse antud indeksiga element ja see läheb liitmisele.
        }   //Rekursiooni puu- https://gyazo.com/ac6ea439e39219b482c99674bae32d60
        int keskindeks = (esimeneindeks + viimaneindeks) / 2;  //Defineeritakse keskmine indeks.
        //Massiiv jagatakse kaheks, kus esimeses pooles vaadeldakse elemente, mis jäävad vahemikku 0 kuni keskmine indeks ja
        //teises pooles vaadeldakse elemente, mis jäävad vahemikku keskmine indeks+1 kuni viimane element.
        //Lisaks liidetakse tagastatud väärtused kokku ja lõpuks tagastatakse ka see.
        return summaBinaarRek(massiiv, esimeneindeks, keskindeks) + summaBinaarRek(massiiv, keskindeks + 1, viimaneindeks);
    }


    /**
     * Massiivis elementide kohtade vahetamine paarikaupa unaar-rekursiooniga.
     * Rakendamine massivis elementide kohtade vahetamiseks paarikaupa: vahetaElemendipaarid(massiiv, 0)
     *
     * @param massiiv Antud massiiv.
     * @param i       Antud massiivi esimene indeks null.
     * @return Tagastab massiivi, kus on elemendid vahetatud paarikaupa.
     * Autor: Karl Taal.     *
     */
    public static int[] vahetaElemendipaarid(int[] massiiv, int i) {
        if (i > massiiv.length - 2) {   //Baas kontrollib, kas indeks on läinud suuremaks kui massiivi pikkus-2.
            //-2 just sellepärast, et vahetatakse paarikaupa ja kui paaritu arv elemente on, siis viimast elementi me ei puudu.
            return massiiv;             //Tagastatakse valmis massiiv.
        }
        int a = massiiv[i];             //Tehakse elementide vahetus.
        int b = massiiv[i + 1];
        massiiv[i] = b;
        massiiv[i + 1] = a;
        //Kutsutakse uuesti meetod välja, kus indeksi muutuja on suurendatud 2 võrra, sest muudame paarikaupa.
        return vahetaElemendipaarid(massiiv, i + 2);
    }


    /**
     * Massiivis märgimuutude arvu leidmine iteratiivsele.
     * Rakendamine massiivis märgimuutude leidmiseks: märgimuududIteratiivselt(massiiv)
     *
     * @param massiiv Antud massiiv.
     * @return Tagastab märgimuutude arvu.
     * Autor: Karl Taal
     */
    public static int märgimuududIteratiivselt(int[] massiiv) {
        int muudud = 0;                                         //Määrame alguseks muutude arvu nulliks.
        int märk = 0;                                           //Muutuja viimase mitte nulli märgi meelde jätmiseks.
        int abi = 0;                                            //Abi muutuja, esimese mitte nulli elemendi määramiseks.
        for (int i = 0; i < massiiv.length; i++) {              //Läbime kõik massiivi elemendid.
            if (massiiv[i] != 0 && abi == 0) {                  //Kui massiivi element ei ole null ja abi on ka veel 0, siis oleme leidnu
                märk = massiiv[i];                              //esimese elemendi, mis ei ole null ja selle märgi.
                abi++;
            } else {                                            //Kui esimene element, mis ei ole null, siis edasi läbitakse seda osa.
                if (massiiv[i] != 0) {                          //Kui element ei ole null, siis võrreldakse eelmise mitte null elemendiga.
                    if (massiiv[i] > 0 && märk < 0 || massiiv[i] < 0 && märk > 0) {
                        märk = massiiv[i];                      //Kui tingimused on korras, siis muudame märk muutujat ja lisame muutudele ühe.
                        muudud++;
                    }
                }
            }
        }
        return muudud;  //Tagastakse muutude arv.
    }


    /**
     * Massiivis märgimuutude arvu leidmine unaar-rekursiooniga.
     * Rakendamine massiivis märgimuutude leidmiseks: märgimuududUnaarRek(massiiv, 0)
     *
     * @param massiiv Antud massiiv.
     * @param i       Antud massiivi esimese elemendi indeks null.
     * @return Tagastab märgimuutude arvu.
     * Autor: Karl Taal
     */
    public static int märgimuududUnaarRek(int[] massiiv, int i) {
        if (i == massiiv.length) {      //Esimene baas kontrollib, kas indeksiga on jõutud massiivi lõppu.
            return 0;                   //Kui jah, siis tagastatakse null.
        }
        int leitud = 0;                 //Luuakse muutuja märgimuutude loendamiseks.
        if (massiiv[i] != 0) {          //Kui vaadeldav element ei ole null, siis...
            for (int j = i + 1; j < massiiv.length; j++) {  //Läbime antud elemendist kõik järgnevad elemendid kuni mingi tingimuse täitumiseni.
                //Kui elemendist esimene järgnev element, mis ei ole null, on samamärgiline, siis lihtsalt breakitakse loop.
                if (massiiv[i] < 0 && massiiv[j] < 0 || massiiv[i] > 0 && massiiv[j] > 0) {
                    break;
                }
                //Kui elemendist esimene järgnev element, mis ei ole null, on erimärgilised, siis liidetakse muutujale 1 juurde ja breakitakse loop.
                if (massiiv[i] < 0 && massiiv[j] > 0 || massiiv[i] > 0 && massiiv[j] < 0) {
                    leitud++;
                    break;
                }
            }
        }
        leitud += märgimuududUnaarRek(massiiv, i + 1);  //Kutsutakse uuesti meetod välja ühe võrra suurema ineksiga.
        return leitud;  //Tagastatakse leitud märgimuutude arv.
    }


    /**
     * Massiivis märgimuutude arvu leidmine binaar-rekursiooniga.
     * Rakendamine massiivis märgimuutude leidmiseks: märgimuududBinaarRek(massiiv, 0, massiiv.length-1)
     *
     * @param massiiv    Antud int tüüpi massiiv.
     * @param algindeks  Antud esimene indeks null.
     * @param loppindeks Antud viimane indeks.
     * @return Tagastab märgimuutude arvu massiivis.
     * Autor: Karl Taal.
     */
    public static int märgimuududBinaarRek(int[] massiiv, int algindeks, int loppindeks) {
        if (algindeks >= loppindeks) {          //Kui algindeks on võrdne või suurem kui lõppindeks, siis tagastakse kohe null.
            return 0;
        }
        if (kasSobib(massiiv, algindeks, loppindeks)) {         //Lisa meetodi abil kontrollitakse, kas indeksid sobivad.
            //Seejärel kontrollitakse, kas indeksitel olevad elemendid on erimärgiga.
            if (massiiv[loppindeks] > 0 && massiiv[algindeks] < 0 || massiiv[loppindeks] < 0 && massiiv[algindeks] > 0) {
                return 1;                                       //Kui on erimärgiga, siis tagastakse 1.
            } else {
                return 0;                                       //Vastasel juhul 0.
            }
        }
        int keskindeks = tagastaKeskindeks(massiiv, algindeks, loppindeks);     //Leitakse sobiv keskmine indeks.
        int a = märgimuududBinaarRek(massiiv, algindeks, keskindeks);           //Väljakutsetel on keskindeks mõlemas harus sees, et ei läheks ühtegi märgimuutu kaotsi.
        int b = märgimuududBinaarRek(massiiv, keskindeks, loppindeks);
        return a + b;                                                           //Tagastatakse mõlema haru summa.
    }


    /**
     * Tõeväärtuse leidmine, kas kahe indeksi vahel ei ole teise eilemente peale nullide.
     * Rakendamine tõeväärtuse leidmiseks, kas kahe indeksi vahel oleval alal ei ole ühtegi teist elementi peale nulli: kasSobib(massiiv, algusindeks, lõppindeks)
     *
     * @param massiiv Antud int tüüpi massiiv.
     * @param i       Antud algusindeks.
     * @param j       Antud lõppindeks.
     * @return Tagastab tõeväärtuse, kas nende indeksite vahel ei ole ühtegi teist elementi peale nulli.
     * Autor: Karl Taal.
     */
    public static boolean kasSobib(int[] massiiv, int i, int j) {
        boolean väärtus = false;            //Alguses määrame tagastuseks false.
        if (i == j) {                       //Kui indeksid on võrdsed, siis tagastame kohe true.
            return true;
        }
        int loendur = 0;                    //Kui indeksid on erinevad, siis kontrollime tsükli ja loenduri abil, kas
        for (int k = i + 1; k < j; k++) {   //nende vahel on kõik elemendid null.
            if (massiiv[k] == 0) {
                loendur++;
            }
        }
        if (loendur == j - i - 1) {
            väärtus = true;                 //Kui kõik klapib, siis muudetakse muutuja trueks.
        }
        return väärtus;                     //Tagastatakse muutuja.
    }


    /**
     * Keskmise elemendi indeksi leidmine, nii et element ei ole null.
     * Rakendamine kahe indeksite vahelise keskelt teise inedeksi leidmine, nii et see element ei oleks null: tagastaKeskindeks(massiiv, esimeneIndeks, teineIndeks)
     *
     * @param massiiv Antud int tüüpi massiiv.
     * @param i       Antud esimene indeks.
     * @param j       Antud teine indeks.
     * @return Tagastakse kahe indeksi vaheline indeks keskelt nii, et element ei ole null.
     * Autor: Karl Taal.
     */
    public static int tagastaKeskindeks(int[] massiiv, int i, int j) {
        int indeks = (i + j) / 2;                   //Alguses määrame keskmiseks indeksi lihtsalt keskmise elemendi indeksi.
        if (massiiv[indeks] == 0) {                 //Kui sellel indeksil olev element on null, siis hakkame otsima sellele lähimat elementim, mis ei ole null.
            int l = 1;                              //Muutuja millega kontrollime ümbrust.
            while (true) {                          //Iga tsükli tiirul kontrollib, kas paremal või vasakul leidub element, mis ei ole null.
                if (massiiv[indeks + l] != 0) {     //Vaadeldakse parasjagu nii kaugeid elemente, kui suureks on läinud muutuja.
                    return indeks + l;
                }
                if (massiiv[indeks - l] != 0) {
                    return indeks - l;
                }
                l++;                                //Suurendatakse muutujat ühe võrra.
            }
        }
        return indeks;                              //Kui kohe alguses element ei olnud null sellel indeksil, siis tagastakse see indeks.
    }


    /**
     * Suvalise täisarvulise väärtuse leidmine antud vahemikus.
     * Rakendamine juhusliku täisarvu leidmiseks antud vahemikus: suvalineArv(alumine, ulemine)
     *
     * @param alumine Antud juhusliku täisarvu miinimum väärtus.
     * @param ulemine Antud juhusliku täisarvu maksimum väärtus.
     * @return Juhuslik täisarv antud vahemikus.
     * Autor: Karl Taal.
     */
    public static Integer suvalineArv(int alumine, int ulemine) {               // Genereerib suvalisi täisarve vahemikus alumine ja ülemine.
        return (alumine + (int) (Math.random() * ((ulemine - alumine) + 1)));   // Tagastab suvalise täisarvu antud vahemikus.
    }


    /**
     * Juhuslike ette antud vahemikus täisarvuliste väärtustega massiivi loomimine.
     * Rakendamine ette antud pikkuse ja väärtuste vahemikuga juhujärjendi loomiseks: Gen_Juhujärjend(p, mi, ma).
     *
     * @param p  Antud soovitud juhujärjendi suurus.
     * @param mi Antud juhuslike väärtuste miinimum väärtus.
     * @param ma Antud juhuslike väärtuste maksimum väärtus.
     * @return Kriteeriumitele vastav juhuslike väärtustega massiiv.
     * Autor: Karl Taal.
     */
    public static int[] Gen_Juhujärjend(int p, int mi, int ma) {    // Genereerib juhujärjendi etteantud pikkuse(p), min väärtuse(mi) ja maksimumväärtuse(ma) järgi.
        int[] suvajärjend = new int[p];                             // Loob suvajärjendi pikkusega p
        for (int i = 0; i < p; i++) {                               // For tsükliga asendame kõik järjendi elemendid suvaliste täisarvudega.
            suvajärjend[i] = suvalineArv(mi, ma);                   // Paigutab järjendisse hetkel vaadeldavasse kohta suvalise täisarvu vahemikus mi ja ma.
        }
        return suvajärjend;                                         // Tagastab järjendi, kus on suvalised täisarvud.
    }


    /**
     * Juhumassiivide elementide summa leidmise testmeetod.
     *
     * @param suurused Antud juhumassiivide pikkused massiividena.
     *                 Autor: Karl Taal.
     */
    public static void testÜl1(int[] suurused) {
        System.out.println("Kodutöö nr 6a-1." + "                        " + "Programmi väljund");
        System.out.println("=========================================================:");
        System.out.println();

        for (int i : suurused) {
            double algusaeg1 = 0;
            double algusaeg2 = 0;
            double algusaeg3 = 0;
            double aegMs1 = 0;
            double aegMs2 = 0;
            double aegMs3 = 0;


            int[] massiiv = Gen_Juhujärjend(i, -20, 80);

            if (massiiv.length > 3000) {
                algusaeg1 = System.nanoTime();
            }
            int a = summaIteratiivselt(massiiv);                        //Iteratiivse meetodi rakendamine.
            if (massiiv.length > 3000) {
                long lõppaeg1 = System.nanoTime();
                aegMs1 = (lõppaeg1 - algusaeg1) / 1.e6;
                aegMs1 = Math.round(aegMs1 * 100) / 100.00;
            }


            if (massiiv.length > 3000) {
                algusaeg2 = System.nanoTime();
            }

            int b = summaUnaarRek(massiiv, 0, 0);           //Unaar-rekursiivse meetodi rakendamine.
            if (massiiv.length > 3000) {
                long lõppaeg2 = System.nanoTime();
                aegMs2 = (lõppaeg2 - algusaeg2) / 1.e6;
                aegMs2 = Math.round(aegMs2 * 100) / 100.000;
            }


            if (massiiv.length > 3000) {
                algusaeg3 = System.nanoTime();
            }
            int c = summaBinaarRek(massiiv, 0, massiiv.length - 1);     //Binaar-rekursiivse meetodi rakendamine.
            if (massiiv.length > 3000) {
                long lõppaeg3 = System.nanoTime();
                aegMs3 = (lõppaeg3 - algusaeg3) / 1.e6;
                aegMs3 = Math.round(aegMs3 * 100) / 100.0;
            }


            if (massiiv.length < 30) {
                System.out.println("Antud: " + Arrays.toString(massiiv));
            } else {
                System.out.println("Meetodite töötulemused " + massiiv.length + "-elemendilise juhujärjendi korral:");
            }

            System.out.print("Summa iteratiivselt:            " + a);
            if (massiiv.length > 3000) {
                System.out.println(",   aeg " + aegMs1 + " millisek");
            } else {
                System.out.println();
            }


            System.out.print("Summa unaar-rekursiivselt:      " + b);
            if (massiiv.length > 3000) {
                System.out.println(",   aeg " + aegMs2 + " millisek");
            } else {
                System.out.println();
            }


            System.out.print("Summa binaar-rekursiivselt:     " + c);
            if (massiiv.length > 3000) {
                System.out.println(",   aeg " + aegMs3 + " millisek");
            } else {
                System.out.println();
            }


            if (a == b && a == c && b == c) {
                System.out.println("                                TEST OK.");
            } else {
                System.out.println("                                TEST FAILED.");
            }
            System.out.println();
        }
        System.out.println("=========================================================.");
        System.out.println("Karl Taal " + "                        " + new java.sql.Timestamp(System.currentTimeMillis()));
    }


    /**
     * Juhumassiivides paarikaupa elemetide kohtade vahetamise testmeetod.
     *
     * @param suurused Antud juhusmassiivide pikkused massiivina.
     *                 Autor: Karl Taal.
     */
    public static void testÜl2(int[] suurused) {
        System.out.println("Kodutöö nr 6a-2." + "                        " + "Programmi väljund");
        System.out.println("=========================================================:");
        System.out.println();

        for (int i : suurused) {
            int[] massiiv = Gen_Juhujärjend(i, -20, 80);
            int[] varukoopia = massiiv.clone();

            System.out.println("            Antud: " + Arrays.toString(massiiv));
            System.out.println("Pärast vahetamist: " + Arrays.toString(vahetaElemendipaarid(massiiv, 0)));  //Meetodi rakendamine

            if (Arrays.equals(varukoopia, vahetaElemendipaarid(massiiv, 0))) {
                System.out.println("                   TEST OK.");
            } else {
                System.out.println("                   TEST FAILED.");
            }
            System.out.println();
        }

        System.out.println("=========================================================.");
        System.out.println("Karl Taal " + "                        " + new java.sql.Timestamp(System.currentTimeMillis()));
    }


    /**
     * Massiividest märgimuutude arvu leidmise testmeetod.
     *
     * @param massiivid Antud massiiv, mis koosneb massiividest, mis lähevad testimisele.
     *                  Autor: Karl Taal.
     */
    public static void testÜl3(int[][] massiivid) {
        System.out.println("Kodutöö nr 6a-3." + "                        " + "Programmi väljund");
        System.out.println("=========================================================:");
        System.out.println();

        for (int[] ints : massiivid) {
            System.out.print("Antud:  ");
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();

            //Meetodite rakendamine
            System.out.println("Märgimuute, iteratiivselt:        " + märgimuududIteratiivselt(ints));
            System.out.println("Märgimuute, unaar-rekursiivselt:  " + märgimuududUnaarRek(ints, 0));
            System.out.println("Märgimuute, binaar-rekursiivselt: " + märgimuududBinaarRek(ints, 0, ints.length - 1));
            System.out.println();
        }

        System.out.println("=========================================================.");
        System.out.println("Karl Taal " + "                        " + new java.sql.Timestamp(System.currentTimeMillis()));
    }

}
