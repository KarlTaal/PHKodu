/*****************************************************************************************
 *Programmeerimisharjutused. LTAT.03.007
 *2018/2019 kevadsemester
 *
 *Kodutöö. Ülesanne nr 3 a
 *
 *Teema: Maatriksi muundamine erinevate meetoditega:
 *       1)Ridade vahetus.
 *       2)Veergude vahetus.
 *       3)Rea ja veeru omavaheline vahetamine.
 *       4)Peegelpildid pea- ja kõrvaldiagonaali kaudu.
 *       5)Suurima positiivsete elementidega alammaatriksi leidmine.
 *
 *Kasutab andmefaile: M_abi.class
 *
 *                          --- Käitlemine käsureal ---
 *                     Kompileerimine:
 *                                  >javac -encoding utf8 Kodune3a.java
 *                     Käivitamine:
 *                                  >java Kodune3a
 *                                  >java Kodune3a > tulemus.txt
 *
 * Autor: Karl Taal
 ******************************************************************************************/
public class Kodune3a {
    public static void main(String[] args) {
        System.out.println("Kodutöö nr 3a." + "                          " + "Programmi väljund");
        System.out.println("=========================================================:");
        System.out.println();

        //ÜLESANNE 1&2 - Ridade vahetus ja veergude vahetus.
        System.out.println("Antud maatriks:");
        int[][] maatriks1 = M_abi.juhumaatriks(-99, 99, 6, 9);  //i- arvude min väärtus, i1- arvude max väärtus
        M_abi.printM_ind("\t", maatriks1);                                //i2- maatriksi ridade arv, i3- maatriksi veergude arv
        System.out.println();
        ridadeVahetus(3, 1, maatriks1);     //Parameetriteks rea indeksid, mida soovid omavahel vahetada.
        veergudeVahetus(1, 3, maatriks1);   //Parameetriteks veeru indeksid, mida soovid omavahel vahetada.

        //ÜLESANNE 3 - Rea ja veeru vahetamine.
        System.out.println("Antud ruutmaatriks:");
        int[][] maatriks2 = M_abi.juhumaatriks(1, 99, 11, 11);  //i- arvude min väärtus, i1- arvude max väärtus
        M_abi.printM_ind("\t", maatriks2);                                //i2- maatriksi ridade arv, i3- maatriksi veergude arv
        System.out.println();
        reaveeruVahetus(1, 3, maatriks2);   //Parameetriteks rea indeks(r) ja veeru indeks(v), mida soovid omavahel vahetada.

        //ÜLESANNE 4 - Pea-ja kõrvaldiagonaali järgi peegeldus.
        System.out.println("Antud ruutmaatriks:");
        int[][] maatriks3 = M_abi.juhumaatriks(-99, 99, 6, 6);  //i- arvude min väärtus, i1- arvude max väärtus
        M_abi.printM("\t", maatriks3);                                    //i2- maatriksi ridade arv, i3- maatriksi veergude arv
        System.out.println();
        peadiagonaaliPeegeldus(maatriks3);
        korvaldiagonaaliPeegeldus(maatriks3);

        //ÜLESANNE 5 - Suurima positiivsete liikmetega alammaatriksi leidmine ja selle elementide muutmine.
        System.out.println("Antud maatriks:");
        int[][] maatriks4 = M_abi.juhumaatriks(-19, 99, 6, 9);  //i- arvude min väärtus, i1- arvude max väärtus
        M_abi.printM_ind("\t", maatriks4);                                //i2- maatriksi ridade arv, i3- maatriksi veergude arv
        positiivseteVahetus(maatriks4);

        System.out.println("=========================================================.");
        System.out.println("Karl Taal " + "                        " + new java.sql.Timestamp(System.currentTimeMillis()));
    }


    /**
     * Maatriksi kahe rea vahetamine.
     * Rakendamine maatriksi kahe rea vahetamiseks nende indeksite kaudu: ridadeVahetus(r1, r2, m).
     *
     * @param r1 Antud esimene rea indeks.
     * @param r2 Antud teine rea indeks.
     * @param m  Antud maatriks.
     *           Autor: Karl Taal
     */
    public static void ridadeVahetus(int r1, int r2, int[][] m) {
        int[][] tulemus = m.clone();            //Teeb klooni, et esialgsele maatriksile säiliks ligipääs.
        int[] uus1 = m[r1];                     //Salvestab muutujasse antud read.
        int[] uus2 = m[r2];
        tulemus[r1] = uus2;                     //Määrab enne loodud muutujate abiga read ümber.
        tulemus[r2] = uus1;
        System.out.println("vahetatud read indeksitega " + r1 + " ja " + r2 + ":");
        M_abi.printM_ind("\t", tulemus);
        System.out.println();
    }


    /**
     * Maatriksi kahe veeru vahetamine.
     * Rakendamine maatriksi kahe veeru vahetamiseks nende indeksite kaudu: veergudeVahetus(v1, v2, m).
     *
     * @param v1 Antud esimene veeru indeks.
     * @param v2 Antud teine veeru indeks.
     * @param m  Antud maatriks.
     *           Autor: Karl Taal
     */
    public static void veergudeVahetus(int v1, int v2, int[][] m) {
        int[][] tulemus = m.clone();                 //Teeb klooni, et esialgsele maatriksile säiliks ligipääs.
        int[] veerg1 = new int[tulemus.length];      //Loob 2 uut muutujat veergude jaoks.
        int[] veerg2 = new int[tulemus.length];
        for (int i = 0; i < tulemus.length; i++) {   //Paneb muutujatesse veergude elemendid ja samalajal
            veerg1[i] = tulemus[i][v1];              //muudab vanu veerge.
            veerg2[i] = tulemus[i][v2];
            tulemus[i][v1] = veerg2[i];
            tulemus[i][v2] = veerg1[i];
        }
        System.out.println("vahetatud veerud indeksitega " + v1 + " ja " + v2 + ":");
        M_abi.printM_ind("\t", tulemus);
        System.out.println();
    }


    /**
     * Maatriksi mingi rea ja veeru omavaheline vahetamine.
     * Rakendamine maatriksi mingi rea ja veeru omavaheliseks vahetamiseks indeksite kaudu: reaveeruVahetus(r, v, m).
     *
     * @param r Antud mingi rea indeks.
     * @param v Antud mingi veeru indeks.
     * @param m Antud maatriks.
     *          Autor: Karl Taal
     */
    public static void reaveeruVahetus(int r, int v, int[][] m) {
        int[][] tulemus = m.clone();                   //Teeb klooni, et esialgsele maatriksile säiliks ligipääs.
        if (tulemus.length != tulemus[0].length) {     //Kontroll, kas tegemist on ruutmaatriksiga.
            System.out.println("Tegemist pole ruutmaatriksiga, progamm lõpetab töö!");
            System.exit(1);
        }
        int[] veerg = new int[tulemus.length];         //Loon muutuja veeru elementide jaoks.
        int[] rida = tulemus[r];                       //Salvestab muutujasse etteantud indeksiga rea.
        for (int i = 0; i < tulemus.length; i++) {     //Lisab veeru elementide muutujasse vastavad elemendid.
            veerg[i] = tulemus[i][v];
        }
        tulemus[r] = veerg;                            //Määrab rea elementideks veeru elemendid.
        for (int i = 0; i < tulemus.length; i++) {     //Muudab veeru elemendid rea elementideks.
            tulemus[i][v] = rida[i];                   //Kuna rida pannakse veeru peale viimasena, siis läheb 1 element
        }                                              //kaotsi juhul kui vahetavate rea ja veeru indeksid pole samad.
        System.out.println("vahetatud rida indeksiga " + r + " ja veerg indeksiga " + v + ":");
        M_abi.printM_ind("\t", tulemus);
        System.out.println();
    }


    /**
     * Maatriksi peegelduse leidmine peadiagonaali suhtes.
     * Rakendamine maatriksi peadiagonaali kaudu leitud peegelduse printimiseks: peadiagonaaliPeegeldus(ma).
     *
     * @param ma Antud maatriks.
     *           Autor: Karl Taal
     */
    public static void peadiagonaaliPeegeldus(int[][] ma) {
        int[][] tulemus = new int[ma.length][ma[0].length];         //Teeb klooni elementide tasemel,
        for (int i = 0; i < ma.length; i++) {                       //et esialgsele maatriksile säiliks ligipääs.
            tulemus[i] = ma[i].clone();
        }
        if (tulemus.length != tulemus[0].length) {                  //Kontrollib,kas tegu on ruutmaatriksiga.
            System.out.println("Tegemist pole ruutmaatriksiga, progamm lõpetab töö!");
            System.exit(1);
        }
        for (int i = 0, k = 1; i < tulemus.length - 1; i++, k++) {  //Esimene tsükkel liigub ridade tasemel.
            for (int j = k; j < tulemus.length; j++) {              //Teine tsükkel liigub maatriksi elementide tasemel.
                int ülemine = tulemus[i][j];                        //Luuakse muutujad originaalsete väärtustega.
                int alumine = tulemus[j][i];
                tulemus[i][j] = alumine;                            //Elemendid määratakse ümber muutujate abiga.
                tulemus[j][i] = ülemine;
            }
        }
        System.out.println("peegeldus peadiagonaali suhtes:");
        M_abi.printM("\t", tulemus);
        System.out.println();
    }


    /**
     * Maatriksi peegelduse leidmine kõrvaldiagonaali suhtes.
     * Rakendamine maatriksi kõrvaldiagonaali kaudu leitud peegelduse printimiseks: kõrvaldiagonaaliPeegeldus(ma).
     *
     * @param ma Antud maatriks.
     *           Autor: Karl Taal
     */
    public static void korvaldiagonaaliPeegeldus(int[][] ma) {
        int[][] tulemus2 = new int[ma.length][ma[0].length];        //Teeb klooni elementide tasemel,
        for (int i = 0; i < ma.length; i++) {                       //et esialgsele maatriksile säiliks ligipääs.
            tulemus2[i] = ma[i].clone();
        }
        if (tulemus2.length != tulemus2[0].length) {                //Kontroll, kas tegemist on ruutmaatriksiga.
            System.out.println("Tegemist pole ruutmaatriksiga, progamm lõpetab töö! ");
            System.exit(1);
        }
        for (int i = 0, k = 1, p = tulemus2.length - 1; i < tulemus2.length - 1; i++, k++, p--) { //Esimene tsükkel liigub ridade tasemel.
            for (int j = 0, u = tulemus2.length - 1; j < tulemus2.length - k; j++, u--) {         //Teine tsükkel liigub maatriksi elementide tasemel.
                int ulemine = tulemus2[i][j];           //Luuakse muutujad originaalsete väärtustega.
                int alumine = tulemus2[u][p];
                tulemus2[u][p] = ulemine;               //Muutujate abil määratakse elemendid ümber.
                tulemus2[i][j] = alumine;
            }
        }
        System.out.println("peegeldus kõrvaldiagonaali suhtes:");
        M_abi.printM("\t", tulemus2);
        System.out.println();
    }


    /**
     * Alammaatriksi koostamine ülemise vasaku tipu elemendi ja alumise parema tipu elemndi koordinaatide kaudu.
     * Rakendamine alammaatriksi moodustamiseks: alammaatriks(x1, y1, x2, y2, m)
     *
     * @param x1 Antud ülemise vasaku tipu elemendi rea indeks.
     * @param y1 Antud ülemise vasaku tipu veeru indeks.
     * @param x2 Antud alumise parema tipu elemendi rea indeks.
     * @param y2 Antud alumise parema tipu veeru indeks.
     * @param m  Antud maatriks.
     * @return Mingi maatriksi alammaatriks, mis koostati vastaval kahele elemendile.
     * Autor: Karl Taal
     */
    public static int[][] alammaatriks(int x1, int y1, int x2, int y2, int[][] m) {
        int[][] alam = new int[x2 - x1 + 1][y2 - y1 + 1];               //Luuakse tühi alammaatriks kahele koordinaatidele sobivates suurustes.
        for (int i = 0, X = x1; i < alam.length; i++, X++) {            //Esimene tsükkel liigu ridade tasemel.
            for (int k = 0, Y = y1; k < alam[0].length; k++, Y++) {     //Teine tsükkel liigub maatriksi elementide tasemel.
                alam[i][k] = m[X][Y];                                   //Elementideks määratakse originaalses maatriksis
            }                                                           //olevad elemendid vastavates kohtades, orienteerudes siis
        }                                                               //ette antud kahe elemendi koordinaatide kaudu.
        return alam;                                                    //Tagastab loodud alammaatriksi koos väärtustega antud kohas.
    }


    /**
     * Maatriksi kõikide elementide koordinaatide leidmine kasvavas järjekorras.
     * Rakendamine kõikide maatriksi koordinaatide leidmiseks. kõikKoordinaadid(m).
     *
     * @param m Antud mingi maatriks.
     * @return Kahe mõõtmeline massiiv kõikide maatriksi elementide koordinaatidega kasvavas järjekorras.
     * Autor: Karl Taal
     */
    public static int[][] kõikKoordinaadid(int[][] m) {
        int r = 0;                                          //Loon rea ja tulba indeksile vastavad muutujad.(x ja y koordinaadid siis).
        int t = 0;
        int loendur1 = 0;                                   //Loon loenduri, mida saan tsüklis kasutada.
        int elementeKokku = m[0].length * m.length;         //Loon muutuja mille väärtus on maatriksi elementide kogus.
        int[][] koordinaadid = new int[elementeKokku][2];   //Loon kahe tasemelise massiivi, kuhu lisatakse iga elemendi koordinaadid.
        for (int i = 0; i < elementeKokku; i++) {           //Tsüklis leitakse kõik maatriksi elementide koordinaadid suurenemise järjekorras.
            int[] kordinaat = {r, t};
            loendur1++;
            t++;
            if (loendur1 == m[0].length) {
                r++;
                loendur1 = 0;
            }
            if (t == m[0].length) {
                t = 0;
            }
            koordinaadid[i] = kordinaat;
        }
        return koordinaadid;                                //Tagastakse kahemõõtmeline massiiv koos kõigi koordinaatidega.
    }


    /**
     * Kontroll meetod, kas kõik maatriksi elemendid on positiivsed (0 on arvestatud positiivseks).
     * Rakendamine tõeväärtuse saamiseks, kas kõik maatriksi elemendid on positiivsed: positiivsuseKontroll(m).
     *
     * @param m Antud mingi maatriks.
     * @return Tõeväärtus false või true, vastavalt kas kõik elemendid on positiivsed.
     * Autor: Karl Taal
     */
    public static Boolean positiivsuseKontroll(int[][] m) {
        boolean tagastus = false;                       //Vaike tõeväärtuseks määratakse false.
        int loendur = 0;                                //Loendur, lugemiseks mitu elementi on positiivne(0 arvasin ka positiivseks).
        int kokku = m.length * m[0].length;             //Muutuja, mis näita mitu elementi maatriksis kokku on.
        for (int i = 0; i < m.length; i++) {            //Tsükliga vaadatakse igat elementi ja kui element on positiivne, siis
            for (int j = 0; j < m[0].length; j++) {     //lisatakse loendurile 1 juurde.
                if (m[i][j] >= 0) {
                    loendur++;
                }
            }
        }
        if (loendur == kokku) {                         //Kui loendur on võrdne elementide arvuga, siis on kõik elemendid positiivsed ja
            tagastus = true;                            //tagastus väärtuseks määrakse true.
        }
        return tagastus;                                //Tagastatakse tõeväärtus.
    }


    /**
     * Maatriksi mingi alammaatriksi kõikide elementide muutmine 100'ks.
     * Rakendamine alammaatriksi elementide muundamiseks: elementideVahetus(x1, y1, x2, y2, m).
     *
     * @param x1 Antud alammaatriksi vasaku ülemise tipu elemendi rea indeks.
     * @param y1 Antud alammaatriksi vasaku ülemise tipu elemendi veeru indeks.
     * @param x2 Antud alammaatriksi parema alumise tipu elemendi rea indeks.
     * @param y2 Antud alammaatriksi parema alumise tipu elemendi veeru indeks.
     * @param m  Antud originaal maatriks.
     * @return Muudetud alammaatriksiga maatriks.
     * Autor: Karl Taal.
     */
    public static int[][] elementideVahetus(int x1, int y1, int x2, int y2, int[][] m) {
        for (int X = x1; X <= x2; X++) {        //Esimene tsükkel liigub ridade tasemel.
            for (int Y = y1; Y <= y2; Y++) {    //Teine tsükkel liigub maatriksi elementide tasemel.
                m[X][Y] = 100;                  //Elemendi väärtuseks määratakse 100.
            }
        }
        return m;                               //Tagastatakse maatriks, kus mingi alammaatriksi elemendid on 100'ks muudetud.
    }


    /**
     * Maatriksi suurima positiivsete arvudega alammaatriksi leidmine ja selle elementide vahetamine.
     * Rakendamine suurima positiivsete arvudega alammaatriksi välja printimine muundatud kujul. postiivseteVahetus(m).
     *
     * @param m Antud maatriks.
     *          Autor: Karl Taal
     */
    public static void positiivseteVahetus(int[][] m) {                         //Kui tulemuseks sobib mitu varianti, siis vastuse moodustamiseks kasutatakse esimesena leitud varianti.
        int[][] vastusKoordinaadi = new int[2][2];                              //Loob muutuja kahe koordinaadi säilitamiseks, mis peaksid vastuseks olema.
        int suurimSiiani = 0;                                                   //Muutuja suurima positiivsete elementidega alammaatriksi jälgimiseks.

        int[][] koordinaadid = kõikKoordinaadid(m);                             //Salvestab muutujasse kõik maatriksi elementide koordinaadid.
        for (int i = 0; i < koordinaadid.length; i++) {                         //Esimene tsükkel määrab alammaatriksi ülemise vasaku tipu elemendi koordinaadid.
            int[] algus = koordinaadid[i];                                      //Alammaatriksi ülemise vasaku tipu koordinaadid.
            for (int j = i; j < koordinaadid.length; j++) {                     //Teine tsükkel määrab ära alamaatriksi alumise parema tipu koordinaadi.
                int[] lopp = koordinaadid[j];                                   //Alammaatriksi alumise parema tipu kooridnaadi.
                if (lopp[0] - algus[0] + 1 > 0 && lopp[1] - algus[1] + 1 > 0) { //Kontrollib kas lopp koordinaadid ei asu alguskoordinaatidest diagonaali vasakule.(lopp koordinaatidega punkt peaks koguaeg diagonaali paremale jääma).
                    int[][] alammaatriks = alammaatriks(algus[0], algus[1], lopp[0], lopp[1], m);   //Loob alammaatriksi antud kahe tipu koordinaatide järgi.
                    if (positiivsuseKontroll(alammaatriks) && (alammaatriks.length * alammaatriks[0].length) > suurimSiiani) {   //Kontrollib, kas kõik elemendid alammaatriksis on positiivse
                        vastusKoordinaadi[0] = algus;       //Määratakse uued paremad koordinaadid.                              //ja kas alammaatriksite elementide arv on suurem senisest suurimast.
                        vastusKoordinaadi[1] = lopp;
                        suurimSiiani = alammaatriks.length * alammaatriks[0].length;    //Uuendatakse muutujat, mis näitab seni suurimat sobivad alammaatriksi elementide arvu.
                    }
                }
            }
        }
        m = elementideVahetus(vastusKoordinaadi[0][0], vastusKoordinaadi[0][1], vastusKoordinaadi[1][0], vastusKoordinaadi[1][1], m);  //Antud alammaatriksi nurga koordinaatide järgi muudetakse elemendid 100'ks.
        System.out.println("suurima positiivse ala nurgaindeksid: (" + vastusKoordinaadi[0][0] + ", " + vastusKoordinaadi[0][1] + ") ja (" + vastusKoordinaadi[1][0] + ", " + vastusKoordinaadi[1][1] + ")");
        System.out.println();
        System.out.println("suurima positiivse ala elementidele omistatud arv 100:");
        M_abi.printM_ind("\t", m);
        System.out.println();
    }
}
