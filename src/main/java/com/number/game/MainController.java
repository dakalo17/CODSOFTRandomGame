package com.number.game;


import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.*;

public class MainController implements Initializable {

    @FXML
    private Button btnNewRound;
    @FXML
    private Button btnEndGame;
    @FXML
    private Button btnStartGuess;
    @FXML
    private ListView<String> lvScore;
    @FXML
    private Button btnGenerate;
    @FXML
    protected Label lblGuessesRemaining;
    @FXML
    private TextField txtRandomGenerated;
    @FXML
    private TextField txtRandomGuess;

    private ObservableList<String> scoresList;
    private Random random;
    private int guessesRemaining;


    private int roundCount ;
    private List<Integer> scores;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        scoresList = FXCollections.observableArrayList();
        lvScore.setItems(scoresList);

        scores = new ArrayList<>();
        roundCount = 1;
        random = new Random();
        guessesRemaining = 10;
        lblGuessesRemaining.setText(String.valueOf(guessesRemaining));

        txtRandomGuess.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                if (!keyEvent.getCharacter().matches("[0-9]")) {
                    //erase the textfield if a number is not entered
                    keyEvent.consume();
                }
            }
        });
    }
    @FXML
    protected void onGuessClick(ActionEvent actionEvent) {
        if(txtRandomGenerated.getText().isEmpty()){
            CustomDialog.show("Generate number","You must 1st generate a random number");
            return;
        }
        if(txtRandomGuess.getText().isEmpty()){
            CustomDialog.show("Input number","You must enter a number");
            return;
        }

        if(guessesRemaining == 0){
            CustomDialog.show("Random guess","Your have finished your number of attempts.");
            return;
        }
        int guessedNumber = Integer.parseInt(txtRandomGuess.getText());

        int generated = Integer.parseInt(txtRandomGenerated.getText());

        guessesRemaining--;
        lblGuessesRemaining.setText(String.valueOf(guessesRemaining));

        if(guessedNumber == generated){
            //correct
            CustomDialog.show("Random guess","You guessed correctly.");
            btnStartGuess.setDisable(true);

        }else if(guessedNumber > generated){
            //too high
            CustomDialog.show("Random guess","You guessed too high.");
        }else {
            //too low
            CustomDialog.show("Random guess","You guessed too low.");
        }

    }

    @FXML
    protected void onGenerateClick(ActionEvent actionEvent) {
        int randNumber = random.nextInt(1,100);
        txtRandomGenerated.setText(String.valueOf(randNumber));

        CustomDialog.show("Generated Number","A number has been randomly generated");
        btnGenerate.setDisable(true);
    }

    @FXML
    protected void onNewRoundClick(ActionEvent actionEvent) {


        newRound();
    }

    private void newRound() {
        if (roundCheck(true)) return;


        btnGenerate.setDisable(false);
        btnStartGuess.setDisable(false);
        guessesRemaining = 10;
        lblGuessesRemaining.setText("10");
        roundCount++;
    }

    private boolean roundCheck(boolean btnGenerateState) {
        if(!btnGenerate.isDisabled() && btnGenerateState){
            CustomDialog.show("Round","You must at least do something before doing another round. ");
            return true;
        }

        //implying that the player did not win the current round
        if(!btnStartGuess.isDisabled()) {
            scoresList.add("#" + roundCount + ": " + "0");
            scores.add(0);
        }
        else {
            scoresList.add("#" + roundCount + ": " + guessesRemaining);
            scores.add(guessesRemaining);
        }
        txtRandomGuess.clear();
        txtRandomGenerated.clear();
        return false;
    }

    @FXML
    protected void onEndGameClick(ActionEvent event) {
        if(roundCheck(false))return;

        if(scores == null || scores.isEmpty()){
            CustomDialog.show("End Game","You must at least play 1 round of the game.");
            return;
        }
        int sum =0;

        for (int score : scores){
            sum+=score;
        }
        scoresList.add("Total: "+ sum);

        btnEndGame.setDisable(true);
        btnNewRound.setDisable(true);
        btnStartGuess.setDisable(true);
        btnGenerate.setDisable(true);
        txtRandomGuess.setDisable(true);

    }
}