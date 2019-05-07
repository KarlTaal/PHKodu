/*****************************************************************************************
 *Programmeerimisharjutused. LTAT.03.007
 *2018/2019 kevadsemester
 *
 *Kodutöö. Ülesanded nr 5 a
 *
 *Teema: 1) Rekursiivselt leida tõeväärtus, kas antud müntidega on võimalik tasuda täpselt.
 *       2) Rekursiivselt leida kaalude komplekt, mis ületab minimaalselt antud summat.
 *
 *Abimaterjalid:  Kaks meetodit, mis oli Ahti Peder foorumisse postitanud- massiivide käsitlemise abimeetodid.
 *
 *                          --- Käitlemine käsureal ---
 *                     Kompileerimine:
 *                                  >javac -encoding utf8 Kodu5a.java
 *                     Käivitamine:
 *                                  >java Kodu5a
 *                                  >java Kodu5a > tulemus.txt
 *
 * Autor: Karl Taal
 ******************************************************************************************/
public class Kodu5a {
    public static void main(String[] args) {
        //Mõlema programmi käivitamised
        tööMündid();              //Ülesanne 1.
        System.out.println();
        System.out.println();
        System.out.println();
        tööKaalud();              //Ülesanne 2.
    }


    /**
     * Tõeväärtuse leidmine, kas antud müntidega saab täpselt tasuda mingit summat.
     * Rakendime tõeväärtuse leidmiseks, kas saab tasuda täpselt: mündid(x, s, 0, "", 0)
     *
     * @param x         Antud müntide massiiv.
     * @param s         Antud summa, mis tuleb tasuda.
     * @param vahesumma Antud vahesumma, esialgu 0.
     * @param mündid    Antud müntide komplekt, mis on valikus hetkel, esialgu tühi sõne.
     * @param i         Antud massiivi indeks, esialgu 0.
     * @return Tagastab tõeväärtuse, kas antud müntidega saab täpselt tasuda mingit summat.
     * Autor: Karl Taal.
     */
    public static Boolean mündid(int[] x, int s, int vahesumma, String mündid, int i) {
        if (vahesumma > s || i == x.length && vahesumma != s) {                //Esimene baas kontrollib, kas summa on läinud üle tasutavast
            return false;                                                      //või oleme teinud valiku kõikide müntide osas ja ei ole ikka sobiv summa meile.
        }                                                                      //Sellisel juhul tagastatakse false.
        if (vahesumma == s) {                                                  //Teises baasis kontrollitakse, kas vahesumma on täpselt see, mis on vaja tasuda.
            String vastus = "";                                                //Kui on täpselt summa, mis tuleb tasuda, siis prinditakse hetkel vaadeldav
            for (String münt : mündid.strip().split(" ")) {              //müntide komplekt välja ja tagastatakse true.
                vastus += münt + "+";
            }
            System.out.println(vastus.substring(0, vastus.length() - 1));
            return true;
        }

        //Esimene hargnemine on see, kui me ei võta münti valikuss, teine hargnemine on mündi võtmine valikusse.
        //Igal sammul suurendatakse ka i'd, et liiikuda järgmise elemendi juurde ja teise haru juures liidetakse ka münt valikusse.
        //Disjunktsiooni tõttu kui kuskil tagastatakse true, siis on lõpptulemus kindlasti true ja edasi ei vaadatagi.
        return mündid(x, s, vahesumma, mündid, i + 1) || mündid(x, s, vahesumma + x[i], mündid + x[i] + " ", i + 1);
    }


    /**
     * Minimaalselt kaalupiiri ületava kaalude komplekti leidmine rekursiivselt.
     * Rakendamine kaalude massiivist komplekti leidmiseks, mis ületab kaalupiiri minimaalselt: kaalud(x, s, komplekt, 0)
     *
     * @param x        Antud massiiv erinevate kaaludega.
     * @param s        Antud kaalupiir.
     * @param komplekt Antud esialgu tühi olev massiiv.
     * @param i        Antud massiivi indeks, esialgu 0.
     * @return Tagastab komplekti kaale, mis üleb kaalupiiri minimaalselt.
     * Autor: Karl Taal
     */
    public static double[] kaalud(double[] x, double s, double[] komplekt, int i) {
        if (massiiviSumma(komplekt) > s) {              //Esimeses baasis kontrollitakse, kas praegu valikus oleva komplekti summa ületab kaalupiiri.
            return komplekt;                            //Kui see on tõene, siis tagastatakse antud komplekt, sest see on 1 võimalik vastus.
        }
        if (i == x.length) {                            //Teine baas kontrollib, kas oleme teinud valiku kõigi elementide osas.
            double[] mõttetu = {Double.MAX_VALUE};      //Kui see on tõene, siis järelikult esimene baas oli false ja seega ei ole see meile sobiv komplekt.
            return mõttetu;                             //Tuleb tagastada komplekt, mis hiljem võrdluses kindasti välistatakse.
        }                                               //Kuna me otsime minimaalset sobivat komplekti, võime tagastada mingi komplekti, millel on väga suur väärtus.

        double[] a = kaalud(x, s, komplekt, i + 1);   //Esimeses hargnemises otsustame kaalu mitte komplekti lisada.
        double[] uus = lisaMassiivi(komplekt, x[i]);    //Komplektile kaalu lisamine.
        double[] b = kaalud(x, s, uus, i + 1);        //Teises harus otsustame kaalu komplekti lisada.


        //Siin kohal meil on kaks komplekti valikus, millest üks tuleb rekursioonipuul ülespoole saata. Meie valime väiksema, kuna otsima minimaalset.
        double[] väikseim = a;                          //Väärtustame esialgu minimaalseks komplektiks a.
        if (massiiviSumma(a) > massiiviSumma(b)) {      //Kui komplekt b on väiksem kui komplekt a, siis väärtustame väikseima ümber b komplektiks.
            väikseim = b;
        }

        return väikseim;                                //Tagastame väikseima komplekti. Lõpuks jõuab rekursioonist välja sobivatest variantidest minimaalne.
    }


    /**
     * Massivile ühe elemendi lisamine.
     * Rakendine massiivile elemendi lisamiseks: lisaMassiivi(a, x)
     *
     * @param a Antud double tüüpi massiiv.
     * @param x Antud double tüüpi arv.
     * @return Tagastab uue massiivi, kuhu on lisatud antud arv.
     * Autor: Ahti Peder.
     */
    public static double[] lisaMassiivi(double[] a, double x) {
        //Tulemus - uus massiiv a++[x]
        double[] uus = new double[a.length + 1];
        for (int i = 0; i < a.length; i++)
            uus[i] = a[i];
        uus[uus.length - 1] = x;
        return uus;
    }


    /**
     * Massiivi elementide summa leidmine.
     * Rakendamine massiivi elementide summa leidmiseks:  massiiviSumma(a)
     *
     * @param a Antud double tüüpi massiiv.
     * @return Tagastab massiivi elementide summa.
     * Autor: Ahti Peder.
     */
    public static double massiiviSumma(double[] a) {
        //tagastab antud massiivi summa
        double abi = 0;
        for (int i = 0; i < a.length; i++)
            abi += a[i];
        return abi;
    }


    /**
     * Meetod Mündid meetodi tööle panekuks mitmete erinevate massiivide korral.
     * Autor: Karl Taal.
     */
    public static void tööMündid() {
        System.out.println("Kodutöö nr 5a-1." + "                        " + "Programmi väljund");
        System.out.println("=========================================================:");
        System.out.println();

        int[] x1 = {1, 5, 10, 30, 50, 200};
        int[] x2 = {2, 20, 30, 50, 100, 200};
        int[] x3 = {1, 30, 50, 200};
        int[] x4 = {2, 30, 50, 100};
        int[] x5 = {1, 5, 10, 20, 30, 50, 100};
        int[] x6 = {1, 2, 20, 30, 50, 200};
        int[] x7 = {1, 2, 100};
        int[][] m = {x1, x2, x3, x4, x5, x6, x7};
        int[] summad = {137, 120, 163, 75, 163, 101, 191};

        for (int k = 0; k < m.length; k++) {
            String mündid = "";
            for (int i : m[k]) {
                mündid += i + " ";
            }
            System.out.println("Taskus on mündid " + mündid);
            System.out.print("Ostusumma s = " + summad[k] + "  ");

            if (mündid(m[k], summad[k], 0, "", 0)) {        //Siin toimub meetodi mündid rakendamine.
                System.out.println();
            } else {
                System.out.println("Pole võimalik tasuda!");
                System.out.println();
            }
        }

        System.out.println("=========================================================.");
        System.out.println("Karl Taal " + "                        " + new java.sql.Timestamp(System.currentTimeMillis()));
    }


    /**
     * Meetod Kaalud meetodi tööle panekuks mitmete erinevate massiividega.
     * Autor: Karl Taal.
     */
    public static void tööKaalud() {
        System.out.println("Kodutöö nr 5a-2." + "                        " + "Programmi väljund");
        System.out.println("=========================================================:");
        System.out.println();

        double[] w1 = {2, 3, 2.1, 3.1, 2, 3.2};
        double[] w2 = {4.1, 4.37, 4.69, 4.39, 4.47, 3.26, 4.8, 4.6, 4.18};
        double[] w3 = {4.22, 3.58, 4.28, 3.48, 4.18, 4.07, 3.42, 4.37, 3.56};
        double[] w4 = {3.62, 3.98, 3.69, 4.67, 4.69, 3.83, 3.84, 4.58, 4.89};

        double kaalupiir = 10;
        double[][] järjendid = {w1, w2, w3, w4};

        for (int i = 0; i < järjendid.length; i++) {
            double[] komplekt = {};
            double[] tulemus = kaalud(järjendid[i], kaalupiir, komplekt, 0);     //Siin toimub Kaalud meetodi rakendamine.
            System.out.print("Ehitustööriistade kaalud: ");
            for (double v : järjendid[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
            System.out.println("Kaalupiir : " + kaalupiir);
            System.out.println("Tulemus kaal : " + massiiviSumma(tulemus));
            System.out.print("Komplektis on tööriistad kaaludega: ");
            for (double v : tulemus) {
                System.out.print(v + " ");
            }
            System.out.println();
            System.out.println();
        }

        System.out.println("=========================================================.");
        System.out.println("Karl Taal " + "                        " + new java.sql.Timestamp(System.currentTimeMillis()));
    }
}
