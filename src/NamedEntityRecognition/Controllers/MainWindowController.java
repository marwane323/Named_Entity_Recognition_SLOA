package NamedEntityRecognition.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import NamedEntityRecognition.Model.lion_algorithm;
import NamedEntityRecognition.Model.vecteur;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class MainWindowController {
    /* ------------------------------- Constants ------------------------------- */

    public static final String[] EXTENSIONS = new String[]{"txt"};
    protected String path = "";


    /* ------------------------------- FXML Objects ------------------------------- */


    @FXML
    private Label labelPERFile;
    @FXML
    private Label labelORGFile;
    @FXML
    private Label labelLOCFile;
    @FXML
    private Label labelMISCFile;
    @FXML
    private Label labelOTHERFile;
    @FXML
    private Label labelTestFile;
    @FXML
    private Label labelClassify;
    @FXML
    private JFXButton buttonChoosePERFile;
    @FXML
    private JFXButton buttonChooseORGFile;
    @FXML
    private JFXButton buttonChooseLOCFile;
    @FXML
    private JFXButton buttonChooseMISCFile;
    @FXML
    private JFXButton buttonChooseOtherFile;
    @FXML
    private JFXButton buttonChooseTestFile;
    @FXML
    private JFXButton buttonClassify;
    @FXML
    private JFXButton buttonClear;
    @FXML
    private JFXButton buttonExit;

    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;


    @FXML
    private JFXTextField txtRecall;
    @FXML
    private JFXTextField txtPrecision;
    @FXML
    private JFXTextField txtFMeasure;
    @FXML
    private JFXTextField txtTruePositives;
    @FXML
    private JFXTextField txtTrueNegatives;
    @FXML
    private JFXTextField txtFalsePositives;
    @FXML
    private JFXTextField txtFalseNegatives;

    /* ------------------------------- Other Objects & Variables ------------------------------- */

    private final ObservableList<String> Names = FXCollections.observableArrayList();
    private static Float moyenne_Rappel;
    private static Float moyenne_Précision;
    private static Float moyenne_F_measure;
    DecimalFormat df = new DecimalFormat("0.000");
    File filePER, fileORG, fileLOC, fileMISC, fileOther, fileTest;

    /* ------------------------------- Initialize Method ------------------------------- */

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */

    public void initialize(){
        getPERFile();
        getORGFile();
        getLOCFile();
        getMISCFile();
        getOtherFile();
        getTestFile();
    }

    /* ------------------------------- FXML Methods ------------------------------- */

    @FXML
    public void getPERFile(){

        //Creating an instance of the FileChooser to choose a file in the UI
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        buttonChoosePERFile.setOnAction(e -> {
            filePER = fileChooser.showOpenDialog(buttonChoosePERFile.getScene().getWindow());

            if (filePER == null){
                System.out.println("PER File selection canceled");
                labelPERFile.setTextFill(Color.RED);
                labelPERFile.setText("No file is selected");
            } else {
                labelPERFile.setTextFill(Color.GREEN);
                labelPERFile.setText(filePER.getName());
            }
        });
    }

    @FXML
    public void getORGFile(){

        //Creating an instance of the FileChooser to choose a file in the UI
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        buttonChooseORGFile.setOnAction(e -> {
            fileORG = fileChooser.showOpenDialog(buttonChooseORGFile.getScene().getWindow());

            if (fileORG == null){
                System.out.println("ORG File selection canceled");
                labelORGFile.setTextFill(Color.RED);
                labelORGFile.setText("No file is selected");
            } else {
                labelORGFile.setTextFill(Color.GREEN);
                labelORGFile.setText(fileORG.getName());
            }
        });
    }

    @FXML
    public void getLOCFile(){

        //Creating an instance of the FileChooser to choose a file in the UI
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        buttonChooseLOCFile.setOnAction(e -> {
            fileLOC = fileChooser.showOpenDialog(buttonChooseLOCFile.getScene().getWindow());

            if (fileLOC == null){
                System.out.println("LOC File selection canceled");
                labelLOCFile.setTextFill(Color.RED);
                labelLOCFile.setText("No file is selected");
            } else {
                labelLOCFile.setTextFill(Color.GREEN);
                labelLOCFile.setText(fileLOC.getName());
            }
        });
    }

    @FXML
    public void getMISCFile(){

        //Creating an instance of the FileChooser to choose a file in the UI
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        buttonChooseMISCFile.setOnAction(e -> {
            fileMISC = fileChooser.showOpenDialog(buttonChooseMISCFile.getScene().getWindow());

            if (fileMISC == null){
                System.out.println("MISC File selection canceled");
                labelMISCFile.setTextFill(Color.RED);
                labelMISCFile.setText("No file is selected");
            } else {
                labelMISCFile.setTextFill(Color.GREEN);
                labelMISCFile.setText(fileMISC.getName());
            }
        });
    }

    @FXML
    public void getOtherFile(){

        //Creating an instance of the FileChooser to choose a file in the UI
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        buttonChooseOtherFile.setOnAction(e -> {
            fileOther = fileChooser.showOpenDialog(buttonChooseOtherFile.getScene().getWindow());

            if (fileOther == null){
                System.out.println("Other File selection canceled");
                labelOTHERFile.setTextFill(Color.RED);
                labelOTHERFile.setText("No file is selected");
            } else {
                labelOTHERFile.setTextFill(Color.GREEN);
                labelOTHERFile.setText(fileOther.getName());
            }
        });
    }

    @FXML
    public void getTestFile(){

        //Creating an instance of the FileChooser to choose a file in the UI
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        buttonChooseTestFile.setOnAction(e -> {
            fileTest = fileChooser.showOpenDialog(buttonChooseTestFile.getScene().getWindow());

            if (fileTest == null){
                System.out.println("Test File selection canceled");
                labelTestFile.setTextFill(Color.RED);
                labelTestFile.setText("No file is selected");
            } else {
                labelTestFile.setTextFill(Color.GREEN);
                labelTestFile.setText(fileTest.getName());
            }
        });
    }

    @FXML
    public void classification_vis(ActionEvent event) throws IOException {

        labelClassify.setTextFill(Color.GREEN);
        labelClassify.setText("Starting Classification...");

        int number_line_organization = vecteur.number_line(fileORG.getAbsolutePath());
        int number_line_autre = vecteur.number_line(fileOther.getAbsolutePath());
        int number_line_localisation = vecteur.number_line(fileLOC.getAbsolutePath());
        int number_line_MISC = vecteur.number_line(fileMISC.getAbsolutePath());
        int number_line_PER = vecteur.number_line(filePER.getAbsolutePath());

        System.out.println(number_line_organization);
        System.out.println(number_line_autre);
        System.out.println(number_line_localisation);
        System.out.println(number_line_MISC);
        System.out.println(number_line_PER);


        //vecteur entité
        int[][] vecteur_entité_organization = vecteur.vecteur_entité(number_line_organization, fileORG.getAbsolutePath());
        int[][] vecteur_entité_autre = vecteur.vecteur_entité(number_line_autre, fileOther.getAbsolutePath());
        int[][] vecteur_entité_localisation = vecteur.vecteur_entité(number_line_localisation, fileLOC.getAbsolutePath());
        int[][] vecteur_entité_MISC = vecteur.vecteur_entité(number_line_MISC, fileMISC.getAbsolutePath());
        int[][] vecteur_entité_PER = vecteur.vecteur_entité(number_line_PER, filePER.getAbsolutePath());

        //normalisation vecteur
        Float[][] normalisation_vecteur_organization = vecteur.normalisation_vecteur(vecteur_entité_organization);
        Float[][] normalisation_vecteur_autre = vecteur.normalisation_vecteur(vecteur_entité_autre);
        Float[][] normalisation_vecteur_localisation = vecteur.normalisation_vecteur(vecteur_entité_localisation);
        Float[][] normalisation_vecteur_MISC = vecteur.normalisation_vecteur(vecteur_entité_MISC);
        Float[][] normalisation_vecteur_PER = vecteur.normalisation_vecteur(vecteur_entité_PER);

        Float[] centrevecteur_organization = lion_algorithm.centre(normalisation_vecteur_organization);
        Float[] centrevecteur_autre = lion_algorithm.centre(normalisation_vecteur_autre);
        Float[] centrevecteur_localisation = lion_algorithm.centre(normalisation_vecteur_localisation);
        Float[] centrevecteur_MISC = lion_algorithm.centre(normalisation_vecteur_MISC);
        Float[] centrevecteur_PER = lion_algorithm.centre(normalisation_vecteur_PER);

        //------------------------------------------test------------------------------------------------

        int number_line_test = vecteur.number_line(fileTest.getAbsolutePath());

        //vecteur entité
        int[][] vecteur_entité_test = vecteur.vecteur_entité(number_line_test, fileTest.getAbsolutePath());

        //normalisation vecteur
        Float[][] normalisation_vecteur_test = vecteur.normalisation_vecteur(vecteur_entité_test);

        //distance
        Float[][] distance_test_organization = lion_algorithm.distance(normalisation_vecteur_test, normalisation_vecteur_organization);
        Float[][] distance_test_localisation = lion_algorithm.distance(normalisation_vecteur_test, normalisation_vecteur_localisation);
        Float[][] distance_test_autre = lion_algorithm.distance(normalisation_vecteur_test, normalisation_vecteur_autre);
        Float[][] distance_test_MISC = lion_algorithm.distance(normalisation_vecteur_test, normalisation_vecteur_MISC);
        Float[][] distance_test_PER = lion_algorithm.distance(normalisation_vecteur_test, normalisation_vecteur_PER);

        //---------------------------------------------- distance_avec_centre-----------------------------------------------------

        Float[] distance_avec_centre_organization = lion_algorithm.distance_avec_centre(normalisation_vecteur_test, centrevecteur_organization);
        Float[] distance_avec_centre_autre = lion_algorithm.distance_avec_centre(normalisation_vecteur_test, centrevecteur_autre);
        Float[] distance_avec_centre_localisation = lion_algorithm.distance_avec_centre(normalisation_vecteur_test, centrevecteur_localisation);
        Float[] distance_avec_centre_MISC = lion_algorithm.distance_avec_centre(normalisation_vecteur_test, centrevecteur_MISC);
        Float[] distance_avec_centre_PER = lion_algorithm.distance_avec_centre(normalisation_vecteur_test, centrevecteur_PER);

        //---------------------------------------------- moyenne_des_Femelles-----------------------------------------------------

        Float[] moyenne_des_Femelles_organization = lion_algorithm.moyenne_des_Femelles(distance_test_organization);
        Float[] moyenne_des_Femelles_autre = lion_algorithm.moyenne_des_Femelles(distance_test_autre);
        Float[] moyenne_des_Femelles_localisation = lion_algorithm.moyenne_des_Femelles(distance_test_localisation);
        Float[] moyenne_des_Femelles_MISC = lion_algorithm.moyenne_des_Femelles(distance_test_MISC);
        Float[] moyenne_des_Femelles_PER = lion_algorithm.moyenne_des_Femelles(distance_test_PER);

        //---------------------------------------------- ecartype_des_Femelles-----------------------------------------------------

        Float[] ecartype_des_Femelles_organization = lion_algorithm.ecartype_des_Femelles(moyenne_des_Femelles_organization, distance_test_organization);
        Float[] ecartype_des_Femelles_autre = lion_algorithm.ecartype_des_Femelles(moyenne_des_Femelles_autre, distance_test_autre);
        Float[] ecartype_des_Femelles_localisation = lion_algorithm.ecartype_des_Femelles(moyenne_des_Femelles_localisation, distance_test_localisation);
        Float[] ecartype_des_Femelles_MISC = lion_algorithm.ecartype_des_Femelles(moyenne_des_Femelles_organization, distance_test_MISC);
        Float[] ecartype_des_Femelles_PER = lion_algorithm.ecartype_des_Femelles(moyenne_des_Femelles_organization, distance_test_PER);


        String[][] vecteur_mots = vecteur.vecteur_mots(number_line_test, fileTest.getAbsolutePath());


        int entité_vrais_clas = ecartype_des_Femelles_localisation.length;
        int autre_vrais_clas = 0;
        int programme_classé_entite = 0;
        int programme_classé_autre = 0;

        for (int i = 0; i < ecartype_des_Femelles_autre.length; i++) {
            String résulta = lion_algorithm.classification(distance_avec_centre_organization[i], distance_avec_centre_autre[i], distance_avec_centre_localisation[i]
                    , distance_avec_centre_MISC[i], distance_avec_centre_PER[i], moyenne_des_Femelles_organization[i], moyenne_des_Femelles_autre[i],
                    moyenne_des_Femelles_localisation[i], moyenne_des_Femelles_MISC[i], moyenne_des_Femelles_PER[i], ecartype_des_Femelles_organization[i],
                    ecartype_des_Femelles_autre[i], ecartype_des_Femelles_localisation[i], ecartype_des_Femelles_MISC[i], ecartype_des_Femelles_PER[i]);


            String clas = vecteur.vecteur_mots(number_line_test, fileTest.getAbsolutePath())[i][1];
            if (résulta.equals("entité") && (clas.equals("I-ORG") || clas.equals("I-LOC") || clas.equals("I-MISC") || clas.equals("I-PER"))) {
                programme_classé_entite = programme_classé_entite + 1;
            }
            if (clas.equals("O")) {
                autre_vrais_clas = autre_vrais_clas + 1;
            }
            if (résulta.equals("autre") && (clas.equals("O"))) {
                programme_classé_autre = programme_classé_autre + 1;
            }


        }

        labelClassify.setTextFill(Color.GREEN);
        labelClassify.setText("Classification Completed!");

        txtTruePositives.setText(String.valueOf(programme_classé_entite));
        txtFalseNegatives.setText(String.valueOf(((entité_vrais_clas - autre_vrais_clas) - programme_classé_entite)));
        txtFalsePositives.setText(String.valueOf((autre_vrais_clas - programme_classé_autre)));
        txtTrueNegatives.setText(String.valueOf(programme_classé_autre));

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("True Positives", programme_classé_entite));
        series.getData().add(new XYChart.Data<>("False Positives", (autre_vrais_clas - programme_classé_autre)));
        series.getData().add(new XYChart.Data<>("True Negatives", programme_classé_autre));
        series.getData().add(new XYChart.Data<>("False Negatives", ((entité_vrais_clas - autre_vrais_clas) - programme_classé_entite)));

        barChart.getData().add(series);


        Float Rappel_entité = programme_classé_entite / (programme_classé_entite + ((float) (entité_vrais_clas - autre_vrais_clas) - programme_classé_entite));
        Float Précision_entité = programme_classé_entite / (programme_classé_entite + (float) (autre_vrais_clas - programme_classé_autre));
        Float Rappel_autre = programme_classé_autre / (programme_classé_autre + (float) (autre_vrais_clas - programme_classé_autre));
        Float Précision_autre = programme_classé_autre / (programme_classé_autre + ((float) (entité_vrais_clas - autre_vrais_clas) - programme_classé_entite));
        Float F_measure_entité = (2 * Rappel_entité * Précision_entité) / (Précision_entité + Rappel_entité);
        Float F_measure_autre = (2 * Rappel_autre * Précision_autre) / (Précision_autre + Rappel_autre);

        moyenne_Rappel = (Rappel_autre + Rappel_entité) / 2;

        moyenne_Précision = (Précision_autre + Précision_entité) / 2;

        moyenne_F_measure = (F_measure_autre + F_measure_entité) / 2;

        txtPrecision.setText(df.format(moyenne_Précision));
        txtRecall.setText(df.format(moyenne_Rappel));
        txtFMeasure.setText(df.format(moyenne_F_measure));


        Float Ts = (programme_classé_entite + programme_classé_autre) / (programme_classé_entite + programme_classé_autre + (float) (autre_vrais_clas - programme_classé_autre) + ((float) (entité_vrais_clas - autre_vrais_clas) - programme_classé_entite));

    }


    /**
     * Clears all fields
     */
    @FXML
    public void clearFields() {
        filePER = new File("");
        labelPERFile.setText("");
        fileORG = new File("");
        labelORGFile.setText("");
        fileLOC = new File("");
        labelLOCFile.setText("");
        fileMISC = new File("");
        labelMISCFile.setText("");
        fileOther = new File("");
        labelOTHERFile.setText("");
        fileTest = new File("");
        labelTestFile.setText("");
        labelClassify.setText("");

        barChart.getData().clear();
        barChart.layout();

        txtRecall.setText("");
        txtPrecision.setText("");
        txtFMeasure.setText("");
        txtTruePositives.setText("");
        txtTrueNegatives.setText("");
        txtFalsePositives.setText("");
        txtFalseNegatives.setText("");
    }

    //Exit Button Action
    @FXML
    public void exitButtonAction(ActionEvent event){
        //Setting an confirmation pop-up message and showing it to the user
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to exit!!",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Platform.exit();
        }
    }



}
