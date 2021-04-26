package NamedEntityRecognition.Model;

/**
 * @author Marouane Zoubir Guettatfi
 */

import java.util.Arrays;

public class lion_algorithm {


    //Lion résident
    public static Float[] centre(Float[][] normalisation_vecteur) {
        Float[] centre = new Float[4];
        centre[0] = (float) 0.0;
        centre[1] = (float) 0.0;
        centre[2] = (float) 0.0;
        centre[3] = (float) 0.0;

        for (int i = 0; i < normalisation_vecteur.length; i++) {
            centre[0] = centre[0] + normalisation_vecteur[i][0];
            centre[1] = centre[1] + normalisation_vecteur[i][1];
            centre[2] = centre[2] + normalisation_vecteur[i][2];
            centre[3] = centre[3] + normalisation_vecteur[i][3];
        }
        centre[0] = centre[0] / normalisation_vecteur.length;
        centre[1] = centre[1] / normalisation_vecteur.length;
        centre[2] = centre[2] / normalisation_vecteur.length;
        centre[3] = centre[3] / normalisation_vecteur.length;

        return centre;
    }


    //Femelles
    public static Float[][] distance(Float[][] normalisation_vecteur_test, Float[][] normalisation_vecteur) {
        Float[][] distance = new Float[normalisation_vecteur_test.length][normalisation_vecteur.length];
        Float[][] distance_min = new Float[normalisation_vecteur_test.length][7];
        Float[] distance_min_trns = new Float[normalisation_vecteur.length];

        for (int i = 0; i < normalisation_vecteur_test.length; i++) {
            for (int h = 0; h < normalisation_vecteur.length; h++) {
                distance[i][h] = (float) 0.0;
            }
        }
        for (int i = 0; i < normalisation_vecteur_test.length; i++) {
            for (int j = 0; j < normalisation_vecteur.length; j++) {
                Float dist = (float) 0.0;
                for (int f = 0; f < 4; f++) {
                    dist = dist + ((float) Math.pow(normalisation_vecteur_test[i][f] - normalisation_vecteur[j][f], 2));
                }
                distance[i][j] = (float) Math.sqrt(dist);
            }
        }
        //min 7
        for (int i = 0; i < normalisation_vecteur_test.length; i++) {
            for (int j = 0; j < normalisation_vecteur.length; j++) {
                distance_min_trns[j] = distance[i][j];
//				Arrays.sort(distance[i][j]);
            }
            Arrays.sort(distance_min_trns);
            distance_min[i][0] = distance_min_trns[0];
            distance_min[i][1] = distance_min_trns[1];
            distance_min[i][2] = distance_min_trns[2];
            distance_min[i][3] = distance_min_trns[3];
            distance_min[i][4] = distance_min_trns[4];
            distance_min[i][5] = distance_min_trns[5];
            distance_min[i][6] = distance_min_trns[6];
        }
        return distance_min;
    }


    // distance_avec_centre
    public static Float[] distance_avec_centre(Float[][] normalisation_vecteur_test, Float[] centre) {
        Float[] distance_avec_centre = new Float[normalisation_vecteur_test.length];
        for (int j = 0; j < normalisation_vecteur_test.length; j++) {
            Float dist = (float) 0.0;
            for (int f = 0; f < 4; f++) {
                dist = dist + ((float) Math.pow(normalisation_vecteur_test[j][f] - centre[f], 2));
            }
            distance_avec_centre[j] = (float) Math.sqrt(dist);
        }
        return distance_avec_centre;
    }

    //moyenne des Femelles
    public static Float[] moyenne_des_Femelles(Float[][] distance) {
        Float[] moyenne_des_Femelles = new Float[distance.length];
        for (int j = 0; j < distance.length; j++) {
            Float moyenne = (float) 0.0;
            for (int i = 0; i < 7; i++) {
                moyenne = moyenne + distance[j][i];
            }
            moyenne_des_Femelles[j] = moyenne / 7;
        }
        return moyenne_des_Femelles;
    }


    //ecartype des Femelles

    public static Float[] ecartype_des_Femelles(Float[] moyenne_des_Femelles, Float[][] distance) {
        Float[] ecartype_des_Femelles = new Float[distance.length];

        for (int j = 0; j < distance.length; j++) {
            Float variance = (float) 0.0;
            for (int i = 0; i < 7; i++) {
                variance = variance + (float) Math.pow(moyenne_des_Femelles[j] - distance[j][i], 2);
            }

            ecartype_des_Femelles[j] = (float) Math.sqrt((variance / 7));
        }

        return ecartype_des_Femelles;
    }


    public static String classification(Float distance_avec_centre_organization, Float distance_avec_centre_autre, Float distance_avec_centre_localisation
            , Float distance_avec_centre_MISC, Float distance_avec_centre_PER, Float moyenne_des_Femelles_organization, Float moyenne_des_Femelles_autre,
                                 Float moyenne_des_Femelles_localisation, Float moyenne_des_Femelles_MISC, Float moyenne_des_Femelles_PER, Float ecartype_des_Femelles_organization,
                                 Float ecartype_des_Femelles_autre, Float ecartype_des_Femelles_localisation, Float ecartype_des_Femelles_MISC, Float ecartype_des_Femelles_PER) {
        String clas = "autre";

        Float[] distance_avec_centre_min = new Float[4];
        distance_avec_centre_min[0] = distance_avec_centre_organization;
        distance_avec_centre_min[1] = distance_avec_centre_localisation;
        distance_avec_centre_min[2] = distance_avec_centre_MISC;
        distance_avec_centre_min[3] = distance_avec_centre_PER;
        Float[] moyenne_des_Femelles_min = new Float[4];
        moyenne_des_Femelles_min[0] = moyenne_des_Femelles_organization;
        moyenne_des_Femelles_min[1] = moyenne_des_Femelles_localisation;
        moyenne_des_Femelles_min[2] = moyenne_des_Femelles_MISC;
        moyenne_des_Femelles_min[3] = moyenne_des_Femelles_PER;
        Float[] ecartype_des_Femelles_min = new Float[4];
        ecartype_des_Femelles_min[0] = ecartype_des_Femelles_organization;
        ecartype_des_Femelles_min[1] = ecartype_des_Femelles_localisation;
        ecartype_des_Femelles_min[2] = ecartype_des_Femelles_MISC;
        ecartype_des_Femelles_min[3] = ecartype_des_Femelles_PER;
        Arrays.sort(distance_avec_centre_min);
        Arrays.sort(moyenne_des_Femelles_min);
        Arrays.sort(ecartype_des_Femelles_min);
                  /*
		  System.out.println("///////////////////////////////////////");
		  System.out.println("distance_avec_centre_entité");
		  System.out.println(distance_avec_centre_min[0]);
		  System.out.println("distance_avec_centre_	autre");
		  System.out.println(distance_avec_centre_autre);
		  System.out.println("ecartype_des_Femelles_entité");
		  System.out.println(ecartype_des_Femelles_min[0]);
		  System.out.println("ecartype_des_Femelles_autre");
		  System.out.println(ecartype_des_Femelles_autre);
		  System.out.println("moyenne_minimal_entité");
		  System.out.println(moyenne_des_Femelles_min[0]);
		  System.out.println("moyenne_minimal_autre");
		  System.out.println(moyenne_des_Femelles_autre);
                  */
        Float[] entité = new Float[3];
        entité[0] = distance_avec_centre_min[0];
        entité[1] = moyenne_des_Femelles_min[0];
        entité[2] = ecartype_des_Femelles_min[0];

        Float[] autre = new Float[3];
        autre[0] = distance_avec_centre_autre;
        autre[1] = moyenne_des_Femelles_autre;
        autre[2] = ecartype_des_Femelles_autre;
        Arrays.sort(entité);
        Arrays.sort(autre);

        if (distance_avec_centre_min[0] < distance_avec_centre_autre && ecartype_des_Femelles_min[0] < ecartype_des_Femelles_autre && moyenne_des_Femelles_min[0] < moyenne_des_Femelles_autre) {
            clas = "entité";

        }
        if (distance_avec_centre_min[0] > distance_avec_centre_autre && ecartype_des_Femelles_min[0] > ecartype_des_Femelles_autre && moyenne_des_Femelles_min[0] > moyenne_des_Femelles_autre) {
            clas = "autre";

        }
        if (distance_avec_centre_min[0] < distance_avec_centre_autre && distance_avec_centre_min[0] > distance_avec_centre_autre || moyenne_des_Femelles_min[0] < moyenne_des_Femelles_autre && moyenne_des_Femelles_min[0] > moyenne_des_Femelles_autre) {
            if (ecartype_des_Femelles_min[0] < ecartype_des_Femelles_autre) {
                clas = "entité";
            }
            if (ecartype_des_Femelles_min[0] > ecartype_des_Femelles_autre) {
                clas = "autre";
            }
        }
        if (distance_avec_centre_min[0] < distance_avec_centre_autre && ecartype_des_Femelles_min[0] < ecartype_des_Femelles_autre) {
            clas = "entité";
        }
        if (distance_avec_centre_min[0] > distance_avec_centre_autre && ecartype_des_Femelles_min[0] > ecartype_des_Femelles_autre) {
            clas = "autre";
        } else {
            if (entité[0] < autre[0]) {
                clas = "entité";
            }
            if (entité[0] > autre[0]) {
                clas = "autre";
            }
            if (entité[0] == autre[0]) {
                clas = "entité";
            }
        }


        return clas;
    }
}
