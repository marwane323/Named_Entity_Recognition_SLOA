package NamedEntityRecognition.Model;

/**
 * @author Marouane Zoubir Guettatfi
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class vecteur {

    //number_line
    public static int number_line(String lienn) throws IOException {
        int number_line = 0;
        BufferedReader reader = new BufferedReader(new FileReader(lienn));
        while (reader.readLine() != null) {
            number_line++;
        }
        return number_line;
    }

    //vecteur
    public static int[][] vecteur_entité(int number_line, String lienn) throws IOException {

		int[][] vecteur_entité = new int[number_line][4];
		BufferedReader reader = new BufferedReader(new FileReader(lienn));
		String line = reader.readLine();

		while (line != null) {
			for (int i = 0; i < number_line; i++) {
				String[] terms = line.split(" ");

				if (terms[0].length() != 0){
                    //longueur
                    vecteur_entité[i][0] = terms[0].length();

                    //majuscule
                    if ((65 <= (int) terms[0].charAt(0)) && ((int) terms[0].charAt(0) <= 90)) {
                        vecteur_entité[i][1] = 1;
                    } else {
                        vecteur_entité[i][1] = 0;
                    }

                    //pos
                    if (terms[1].equals("NNP") || terms[1].equals("NNPS") || terms[1].equals("JJ") || terms[1].equals("VBZ")) {
                        vecteur_entité[i][2] = 1;

                    } else {
                        vecteur_entité[i][2] = 0;
                    }

                    //chunk
                    if (terms[2].equals("I-NP")) {
                        vecteur_entité[i][3] = 1;
                    } else {
                        vecteur_entité[i][3] = 0;
                    }
                }
				line = reader.readLine();
			}
		}
		return vecteur_entité;
	}

//normalisation

    public static Float[][] normalisation_vecteur(int[][] vecteur_entité) {
        Float[][] normalisation_vecteur = new Float[vecteur_entité.length][4];

// max et min 

        int min = vecteur_entité[0][0];
        int max = vecteur_entité[0][0];
        for (int i = 0; i < vecteur_entité.length; i++) {
            if (min > vecteur_entité[i][0]) {
                min = vecteur_entité[i][0];
            }

            if (max < vecteur_entité[i][0]) {
                max = vecteur_entité[i][0];
            }
        }
        for (int i = 0; i < vecteur_entité.length; i++) {
            normalisation_vecteur[i][0] = (float) (vecteur_entité[i][0] - min) / (float) (max - min);

            normalisation_vecteur[i][1] = (float) vecteur_entité[i][1];
            normalisation_vecteur[i][2] = (float) vecteur_entité[i][2];
            normalisation_vecteur[i][3] = (float) vecteur_entité[i][3];


        }

        return normalisation_vecteur;

    }


    //mots
    public static String[][] vecteur_mots(int number_line, String lienn) throws IOException {
        String[][] vecteur_mots = new String[number_line][2];


        BufferedReader reader = new BufferedReader(new FileReader(lienn));
        String line = reader.readLine();

        while (line != null) {

            for (int i = 0; i < number_line; i++) {
                String[] terms = line.split(" ");
                if (terms[0].length() != 0) {
                    vecteur_mots[i][0] = terms[0];
                    vecteur_mots[i][1] = terms[3];
                } else {
/*
                    vecteur_mots[i][0] = "pass";
                    vecteur_mots[i][1] = "pass";
*/
                }
                line = reader.readLine();
            }
        }
        return vecteur_mots;
    }
}