/*****************************************************************************************
 *Programmeerimisharjutused. LTAT.03.007
 *2018/2019 kevadsemester
 *
 *Kodutöö. Ülesanne 7b
 *
 *Teema: Leida tekstist 10 kõige sagedasemat sõna, milles on vähemalt 5 tähte ja mida esineb
 *       tekstis ka väiketähega algavalt. Suure- ja väiketähega algavad sõnad loetakse samaväärseteks.
 *
 *
 *                          --- Käitlemine käsureal ---
 *                     Kompileerimine:
 *                                  >javac -encoding utf8 Kodune7b.java
 *                     Käivitamine:
 *                                  >java Kodune7b
 *                                  >java Kodune7b > tulemus.txt
 *
 * Autor: Karl Taal
 ******************************************************************************************/

import java.io.*;
import java.util.*;

public class Kodune7b {
    public static void main(String[] args) throws Exception {
        String[] failid = {"Kõrboja_sisu_puhastekst.txt", "KuldninagaMees_est.txt"};

        //Failist saadakse sisu kätte meetodiga failiSisu.
        //Siis tagastatakse sobiv sõnastik meetodiga tagastaSobivSõnastik ja seejärel läheb töösse meetod väljastaSagedasemad.
        väljastaSagedasemad(tagastaSobivSõnastik(failiSisu(failid[0])), failid[0]);
        System.out.println();
        System.out.println();
        System.out.println();
        väljastaSagedasemad(tagastaSobivSõnastik(failiSisu(failid[1])), failid[1]);
    }


    /**
     * Failist sõnade sisse lugemine ja suuremate kui 5 tähemärgiliste sõnade ülelugemine.
     * Rakendamine failist kõikide sõnade sisse lugemiseks ja suuremate kui 5 tähemärgiliste sõnade ülelugemiseks: failiSisu(failinimi)
     *
     * @param fNimi Antud failinimi.
     * @return Tagastab sõnastiku, kus on igale vähemalt 5 tähelisele sõnale vastavusse pandud selle sõna
     *         tekstis esinemise arv. Suure- ja väikealgustähega sõnad loetakse siin erinevateks.
     * @throws Exception
     * Autor: Karl Taal.
     */
    public static HashMap<String, Integer> failiSisu(String fNimi) throws Exception {
        HashMap<String, Integer> sõnastik = new HashMap<>();    //Loon sõnastikud, et hoida iga sõna ja selle esinemise arvu.

        // faili ridade käitlemise tsükkel:
        try (Scanner f = new Scanner(new File(fNimi), "UTF-8")) {
            while (f.hasNextLine()) {                                            //Käiakse fail läbi ridahaaval, nii kaua kuni ridu on.
                String rida = f.nextLine();
                for (String a : rida.split(" ")) {                         //Eraldatakse iga rea sõnad lähtudes eeldusest, et sõnad on eraldatud tühikutega.
                    a = a.replaceAll("[.\",:!;«?»'()…]", "");    //Käiakse iga sõna läbi ja eemaldatakse ebavajalikud sümbolid ja märgid.
                    //Selle, et mida täpselt eemaldada sain teada käies faili läbi programmiga, mis leidis kõik erinevad tähemärgid.
                    //Ei lisanud seda koodi siia, programmi lõpus on massiivid kommentaaridena, mis tulemuseks sain.
                    //Kui sõne ei ole tühisõne, siis kontrollitakse, kas sõne alguses või lõpus on sidekriips ja kui on, sis see eemaldatakse.
                    //Seda ei saanud enne teha, sest mõned sõnad sisaldavad ka keskel sidekriipsu ja neid ei tahtnud eemaldada.
                    StringBuilder sõna = new StringBuilder(a);
                    if (sõna.length() > 0) {
                        if (sõna.charAt(0) == '-') {
                            sõna.deleteCharAt(0);
                        }
                        if (sõna.charAt(sõna.length() - 1) == '-') {
                            sõna.deleteCharAt(sõna.length() - 1);
                        }
                    }
                    //Kui sõna on vähemalt 5 tähe suurune, siis lisatakse sõna sõnastikku väärtusega 1, aga
                    //kui sõna on juba sõnastikus olemas, siis võetakse eelmine väärtus ja liidetakse sellele 1 juurde.
                    if (sõna.length() >= 5) {
                        if (sõnastik.containsKey(sõna.toString())) {
                            int eelmine = sõnastik.get(sõna.toString());
                            sõnastik.put(sõna.toString(), eelmine + 1);
                        } else {
                            sõnastik.put(sõna.toString(), 1);
                        }
                    }
                }
            }
        }
        return sõnastik;  //Tagastatakse sõnastik.
    }


    /**
     * Sõnastiku korrastamine nii, et sõnad, mida esineb väiketähega arvestatakse kokku suuretähega algava samaväärse sõnaga (kui seda leidub) ja paigutatakse uude sõnastikku.
     * Rakendamine sõnastikust kõikide väiketähega algavate sõnade väärtuste liitmiseks samaväärsete suuretähega sõnade väärtustega ja tulemuse uude sõnastikku paigutamiseks: tagastaSobivSõnastik(sõnastik)
     *
     * @param sõnastik Antud sõnastik, kus võtmeteks on sõnad ja väärtusteks esinemiste arv.
     * @return Tagastatakse sõnastik, kus on võtmeteks läbivate suurte tähtedega sõnad, mida esines väiketähega ja väärtusteks on nende sõnade väike- ja suuretähega esinemiste summa.
     * Autor: Karl Taal.
     */
    public static HashMap<String, Integer> tagastaSobivSõnastik(HashMap<String, Integer> sõnastik) {
        //Paneb uude sõnastikku ainult sõnad, mis esinevad ka väiksetähega.
        //Lisaks sellele arvestatakse nende puhul suure ja väikse tähega algavad sõnad kokku, sest need on tegelikult samaväärsed sõnad.
        HashMap<String, Integer> uus = new HashMap<>();
        List<String> sõnad = new ArrayList<>(sõnastik.keySet());
        for (String s : sõnad) {
            String kontrollsõna = s.substring(0, 1).toLowerCase() + s.substring(1);
            //Kontroll, kas sõna esineb väiksetähega.
            if (sõnastik.get(kontrollsõna) != null) {
                //Muutujasse salvestatakse väiketähega algava sõna esinemiste arv.
                int väärtus = sõnastik.get(kontrollsõna);
                //Kui seda sõna leidub ka suure tähega, siis liidetakse muutujale juurde ka selle sõna esinemiste arv.
                if (sõnastik.get(s.substring(0, 1).toUpperCase() + s.substring(1)) != null) {
                    väärtus += sõnastik.get(s.substring(0, 1).toUpperCase() + s.substring(1));
                }
                //Uude sõnastikku pannakse vaadeldav sõna läbivate suurtetähtedega ja väärtus määratakse eelnevalt kasutusel olnud muutujaga.
                uus.put(s.toLowerCase(), väärtus);  //Peab valima, kas panna väiketähtedega või suurtähtedega, vastasel juhul hakkab korduseid tulema.
            }
        }
        return uus; //Tagastatakse uus sõnastik.
    }


    /**
     * Väljastatakse sobivas vormis 10 kõige sagedasemat sõna sõnastikust.
     * Rakendamine sõnastikust 10 kõige sagedasema sõna väljastamiseks ekraanile sobivas vormingus: väljastaSagedasemad(sõnastik, failinimi)
     *
     * @param sõnastik  Antud sõnastik, kust leitakse 10 kõige sagedasenmat sõna.
     * @param failinimi Antud failinimi täpsustamiseks, millisest failist sõnastik loodud on.
     * Autor: Karl Taal.
     */
    public static void väljastaSagedasemad(HashMap<String, Integer> sõnastik, String failinimi) {
        System.out.println("Kodutöö nr 7b." + "                          " + "Programmi väljund");
        System.out.println("=========================================================:");
        System.out.println();
        System.out.println("Sagedasemad vähemalt 5-tähelised sõnad tekstifailis " + failinimi + ":");
        System.out.println();

        //Võtab sõnastikust kõik väärtused ja eemaldab korduvad sagedused.
        List<Integer> sagedused = new ArrayList<>(sõnastik.values());
        Set<Integer> sagedusteHulk = new HashSet<>(sagedused);
        sagedused.clear();
        sagedused.addAll(sagedusteHulk);
        //Salvestab muutujasse kõik sõnastikus olevad võtmed.
        List<String> sõnad = new ArrayList<>(sõnastik.keySet());
        //Sorteerib sageduste listi kahanevasse järjekorda.
        Collections.sort(sagedused);
        Collections.reverse(sagedused);

        int i = 0;
        int sõnadeLoendur = 0;
        //Kui sõnade sageduste massiiv saab otsa või on väljastatud juba 10 või rohkem sõna, siis katkestatakse tsükkel.
        while (i < sagedused.size() && sõnadeLoendur < 10) {
            int väärtus = sagedused.get(i);             //Võetakse väärtus sageduste massiivist.
            List<String> sobivad = new ArrayList<>();
            for (String s : sõnad) {                    //Leitakse kõik sõnad, millel on selline väärtus ja lisatakse listi sobivad.
                if (sõnastik.get(s) == väärtus) {
                    sobivad.add(s);
                }
            }
            int tühikuteArv = 3 - Integer.toString(väärtus).length();
            System.out.print(" ".repeat(tühikuteArv) + väärtus + " korda: ");
            System.out.println(sobivad);
            //Peale väljastamist liidetakse sõnadeLoenduri juurde arv kui palju sõnu väljastati ja suurendatakse i'd ühe võrra, et järgmise sageduse juurde minna.
            sõnadeLoendur += sobivad.size();
            i++;
        }

        System.out.println();
        System.out.println("=========================================================.");
        System.out.println("Karl Taal " + "                        " + new java.sql.Timestamp(System.currentTimeMillis()));
    }
}

//Alguses käisin teise programmiga kõik tekstis olevad märgid läbi, et teada saada, millised erinevad märgid tekstis üldse esinevad.

//kõrboja teksti kõik erinevad tähemärgi
// [﻿, A, n, t, o, H, a, s, e, T, m, r, K, õ, b, j, p, S, -, k, i, u, d, L, g, R, l, 1, 9, 6, 7, ., ", E, v, ä, 2, 0, I, U, O, D, P, ü, 3, 8, 4, 5, V, h, ,, :, J, !, N, ;, ö, «, ?, », M, Ü, Ä, ', Õ, —, (, ), …, B]

//kuldnina teksti kõik tähemärgid
// [﻿, K, u, l, d, n, i, a, g, m, e, s, o, k, t, ", E, j, V, ü, r, ., S, p, õ, v, ,, h, b, T, ä, O, A, J, P, H, L, ö, N, :, ?, Ü, !, M, Ö, -]