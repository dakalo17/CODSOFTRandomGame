package com.number.game;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    protected Label lblGuessesRemaining;
    @FXML
    private TextField txtRandomGenerated;
    @FXML
    private TextField txtRandomGuess;
    private Random random;
    private int guessedNumber;
    private int guessesRemaining;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
    private int generateRandomNumber(int lower,int upper){
        return random.nextInt(lower,upper);
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
        if(guessedNumber == generated){
            //correct
            CustomDialog.show("Random guess","You guessed correctly.");
        }else if(guessedNumber > generated){
            //too high
            CustomDialog.show("Random guess","You guessed too high.");
        }else {
            //too low
            CustomDialog.show("Random guess","You guessed too low.");
        }
        guessesRemaining--;
        lblGuessesRemaining.setText(String.valueOf(guessesRemaining));
    }

    @FXML
    protected void onGenerateClick(ActionEvent actionEvent) {
        int randNumber = generateRandomNumber(1,100);
        txtRandomGenerated.setText(String.valueOf(randNumber));
    }
}