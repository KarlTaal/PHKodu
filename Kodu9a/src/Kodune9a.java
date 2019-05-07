/*****************************************************************************************
 *Programmeerimisharjutused. LTAT.03.007
 *2018/2019 kevadsemester
 *
 *Kodutöö. Ülesanne 9a
 *
 *Teema: Leida 2019 aasta Tartu maratoni iga osaleja sõidu dünaamika.
 *
 *
 *                          --- Käitlemine käsureal ---
 *                     Kompileerimine:
 *                                  >javac -encoding utf8 Kodune9a.java
 *                     Käivitamine:
 *                                  >java Kodune9a
 *                                  >java Kodune9a > tulemus.txt
 *
 *
 * Autor: Karl Taal
 ******************************************************************************************/

import java.io.*;
import java.util.*;

public class Kodune9a {
    public static void main(String[] args) throws Exception {
        List<String> abi1 = loeNimed("nimed.txt");  //Loeb kõik nimed sisse järjendisse.
        List<String[]> abi2 = loeAjad("ajad.txt");  //Loeb kõik ajad sisse järjendisse massiividena.
        kirjutaAndmedFaili(abi1, abi2);                     //Luuakse fail andmed.txt, kus on ainult sobivad andmed ja sobival kujul.
        loeAndmedjaVäljastaTulemus("andmed.txt");   //Analüüsitakse faili andmed.txt ja leitakse ülesande lahendus ning väljastatakse see.
    }


    /**
     * Andmete sisse lugemine ja iga inimese sõidudünaamika väljastamine.
     * Rakendamine andmete sisse lugemiseks ja iga inimese sõidudünaamika leidmiseks: loeAndmedjaVäljastaTulemus(failinimi)
     *
     * @param failinimi Antud String tüüpi andmete failinimi.
     * @throws Exception Autor: Karl Taal.
     */
    public static void loeAndmedjaVäljastaTulemus(String failinimi) throws Exception {
        //Luuakse kõik vajalikud järjendid andmete hoidmiseks, et neid hiljem kasutada.
        List<String> Read = new ArrayList<>();
        List<Integer> Matu = new ArrayList<>();
        List<Integer> Ande = new ArrayList<>();
        List<Integer> Kuutse = new ArrayList<>();
        List<Integer> Peebu = new ArrayList<>();
        List<Integer> Palu = new ArrayList<>();
        List<Integer> Hellenurme = new ArrayList<>();
        //Luuakse järjend järjenditest, et saaks tegevusi mugavamalt läbi viia tsüklitega.
        List<List<Integer>> listid = new ArrayList<>(Arrays.asList(Matu, Ande, Kuutse, Peebu, Palu, Hellenurme));

        //Loetakse sisse andmed, mis on juba sobivad. St pole selliseid ridu, kust oleks mõni aeg puudu.
        try (Scanner f = new Scanner(new File(failinimi), "UTF-8")) {
            while (f.hasNextLine()) {
                String failirida = f.nextLine();
                Read.add(failirida);    //Lisan juba kõik read järjendisse, et hiljem ei peaks uuesti faili lugema hakkama.
                StringTokenizer rida = new StringTokenizer(failirida, ";");     //Teen rea tükkideks.

                //Kõik tükid paigutatakse vastavatasse järjenditesse nii, et aegade asemel on sekundite arv sõidu algusest.
                //Lõpetamise aega ei teisende sekunditeks ümber, kuna sisaldab ka sajandikke ja on juba järjestatud õigesti.
                // Kontrollisin eraldi teise programmiga ja lõpu aegades ei leidu ka ühtegi korduvat väärtust, mis võiks ülesande püstituse tõttu probleeme tekitada.
                rida.nextToken(); //Nime vaatlemine, seda ei lisata kuskile.
                for (int i = 0; i < listid.size(); i++) {
                    StringTokenizer aeg = new StringTokenizer(rida.nextToken(), ":");
                    //Kasutasin siin seda replaceAll'i kuna ilma selleta oli tulemus error. Kontrollisin sõnesi vaadates nende pikkusi ja
                    //selgus, et nad sisaldasid midagi veel, kuid visuaalselt seda näha polnud. Eeldan, et see on midagi seoses utf8 kodeeringuga,
                    // arvestades seda infot, mida googlest leidsin.
                    String a = aeg.nextToken().replaceAll("[^\\p{Graph}\n\r\t ]", "");
                    String b = aeg.nextToken().replaceAll("[^\\p{Graph}\n\r\t ]", "");
                    String c = aeg.nextToken().replaceAll("[^\\p{Graph}\n\r\t ]", "");
                    //Vastavad väljad korrutatakse sobilike suurustega, et saada sekundite arv ja lisada järjendisse.
                    int abi1 = Integer.parseInt(a) * 3600;
                    int abi2 = Integer.parseInt(b) * 60;
                    int abi3 = Integer.parseInt(c);
                    listid.get(i).add(abi1 + abi2 + abi3);
                }
                rida.nextToken();    //Lõpu aja vaatlemine, seda pole ka põhjust kusagile salvestada.
            }
        }

        //Eemaldab duplikaadid listidest ja sorteerib väiksemast suurememaks.
        for (int i = 0; i < listid.size(); i++) {
            Set<Integer> hulk = new LinkedHashSet<>();
            hulk.addAll(listid.get(i));
            listid.get(i).clear();
            listid.get(i).addAll(hulk);
            Collections.sort(listid.get(i));
        } //Nüüd on meil kõik erinevad sekundite arvud, millal läbiti mingit punkti kellegi poolt ja need on ka järjestatud väiksemast suuremaks.
        //Sorteerimine on vajalik selleks, et saaks hiljem indeksi järgi määrata koha arvu.


        //Algab tulemuste leidmine ja väljastamine
        System.out.println("Kodutöö nr 9a." + "                          " + "Programmi väljund");
        System.out.println("=========================================================:");
        System.out.println();

        int lõpetamisekoht = 1; //Muutuja lõpetamise koha määramiseks. See kasvab alati ühe võrra kuna nimed on juba paremusejärjestuses järjestatud.
        for (String andmerida : Read) {
            String tulemus = "";
            StringTokenizer tükid = new StringTokenizer(andmerida, ";"); //Teen eelnevalt ära salvestatud faili read tükkideks.
            tulemus += tükid.nextToken() + "\t";    //Tulemuse reale liidetakse otsa nimi.

            //Leitakse iga vahepunkti koha arv ja liidetakse tulemuse reale otsa.
            for (int j = 0; j < 6; j++) {
                //Teen vahepunktide ajad tükkideks eraldus märgi kooloni koha pealt.
                StringTokenizer ajatükid = new StringTokenizer(tükid.nextToken(), ":");
                int sekundid = 0;
                //Leian sekundite arvu. Siin tuli taas seda replaceAll'i kasutada, vastasel korral tuli error. Jälle vist selle utf8 kodeeringu pärast.
                sekundid += Integer.parseInt(ajatükid.nextToken().replaceAll("[^\\p{Graph}\n\r\t ]", "")) * 3600;
                sekundid += Integer.parseInt(ajatükid.nextToken().replaceAll("[^\\p{Graph}\n\r\t ]", "")) * 60;
                sekundid += Integer.parseInt(ajatükid.nextToken().replaceAll("[^\\p{Graph}\n\r\t ]", ""));
                //Tulemus reale liidetakse otsa iga vahepunkti koha arv. See leitakse eelnevalt sorteeritud järjendite abiga.
                //Leitakse selle sekundite arvu indeks järjendis ja liidetakse 1 juurde, sest järjendid algavad nullist, kuid kohad algvad ikkagi ühest.
                tulemus += listid.get(j).indexOf(sekundid) + 1 + "\t";
            }
            tulemus += lõpetamisekoht; //Liidetakse lõpetamise koht ka juurde, mis kasvab alati 1 võrra, sest nimed olid juba õigesti järjestatud öõpetamise järgi.
            System.out.println(tulemus);
            lõpetamisekoht++;   //Suurendatakse lõpetamise koha muutuja väärtust järgmise rea tarbeks.
        }

        System.out.println();
        System.out.println("=========================================================.");
        System.out.println("Karl Taal " + "                        " + new java.sql.Timestamp(System.currentTimeMillis()));
    }


    /**
     * Nimede ja vastavate aegade faili kirjutamine nii, et eraldajaks on semikoolon.
     * Rakendamine andmete faili kirjutamiseks sobival kujul: kirjutaAndmedFaili(nimed, ajad)
     *
     * @param nimed Antud järjend String tüüpi objektidega (nimed).
     * @param ajad  Antud järjend String tüüpi massiividine, mis sisaldavad vahepunktide aegasi.
     * @throws Exception Autor: Karl Taal.
     */
    public static void kirjutaAndmedFaili(List<String> nimed, List<String[]> ajad) throws Exception {
        File fail = new File("andmed.txt");
        try (java.io.PrintWriter f = new java.io.PrintWriter(fail, "UTF-8")) {
            //Faili kirjutatakse igale reale sõitja nimi ja temale vastavad ajad. Eraldajateks on semikoolonid.
            for (int i = 0; i < nimed.size(); i++) {
                String[] punktideAjad = ajad.get(i);
                //Faili kirjuttakse ainult read, kus on kõikidel punktidel väärtus olemas.
                if (punktideAjad.length == 7) {
                    //Reale kirjutatakse nimi.
                    f.print(nimed.get(i) + ";");
                    //Reale kirjutatakse vahepunktide ajad.
                    for (int j = 0; j < 6; j++) {
                        f.print(punktideAjad[j] + ";");
                    }
                    //Reale kirjutatakse lõpuaeg.
                    f.println(punktideAjad[6]);
                }
            }
        }
    }


    /**
     * Failist vahepunktide aegade sisse lugemine ja nende järjendi tagastamine.
     * Rakendamine failist vahepunktide aegade sisse lugemiseks ja nende järjendi tagastamiseks: loeAjad(failinimi)
     *
     * @param failinimi Antud String tüüpi failinimi.
     * @return Tagastatakse sisse loetud vahepunktide aegade järjend.
     * @throws Exception Autor: Karl Taal.
     */
    public static List<String[]> loeAjad(String failinimi) throws Exception {
        List<String[]> ajad = new ArrayList<>();
        //Failist, kus on kõik ajad, loetakse ajad sisse ja paigutatakse järjendisse massiividena.
        try (Scanner f = new Scanner(new File(failinimi), "UTF-8")) {
            while (f.hasNextLine()) {
                String rida = f.nextLine();
                rida = rida.trim();
                //Teeb rea tükkideks, eraldajaks on siin tabulatsioonimärk.
                StringTokenizer st = new StringTokenizer(rida, "\t");
                String[] abi = new String[st.countTokens()]; //Abi muutuja massiiv aegade hoidmiseks.
                int i = 0;
                //Aegade paigutamine massiivi
                while (st.hasMoreTokens()) {
                    abi[i] = st.nextToken();
                    i++;
                }
                ajad.add(abi); //Massiivi lisamine järjendisse.
            }
        }
        return ajad; //Tagastatakse ajad.
    }


    /**
     * Failist nimede sisse lugemine ja nimede järjendi tagastamine.
     * Rakendamine failist nimede sisse lugemiseks ja nende järjendi tagastamiseks: loeNimed(failinimi)
     *
     * @param failinimi Antud String tüüpi failinimi.
     * @return Tagastatakse sisse loetud nimede järjend.
     * @throws Exception Autor: Karl Taal.
     */
    public static List<String> loeNimed(String failinimi) throws Exception {
        List<String> nimed = new ArrayList<>();
        //Loetakse nimed sisee ja pannakse järjendisse.
        try (Scanner f = new Scanner(new File(failinimi), "UTF-8")) {
            while (f.hasNextLine()) {
                nimed.add(f.nextLine().trim()); //Reavahetuse eemaldamine ja järjendisse lisamine.
            }
        }
        return nimed; //Tagastatakse järjend.
    }
}
