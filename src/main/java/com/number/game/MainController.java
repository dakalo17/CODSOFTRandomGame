package com.number.game;

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
    private TextField txtRandomGenerated;
    @FXML
    private TextField txtRandomGuess;
    private Random random;
    private int guessedNumber;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        random = new Random();
        txtRandomGuess.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                int input =0;
                try {
                    input = Integer.parseInt(keyEvent.getCharacter());
                }catch (NumberFormatException ignore){
                    keyEvent.consume();
                    return;
                }

                String character = keyEvent.getCharacter();

                if(character.matches("[0-9]")){
                    if(keyEvent.getSource() instanceof TextField textField && !textField.getText().isEmpty()){
                    }

                }
            }
        });
    }
    private int generateRandomNumber(int lower,int upper){
        return random.nextInt(lower,upper);
    }

    @FXML
    protected void onClickStart(ActionEvent actionEvent) {


        try {
            guessedNumber = Integer.parseInt(txtRandomGuess.getText());
        }catch (NumberFormatException ignored){
            //wrong format
        }



    }

    @FXML
    protected void onGenerateClick(ActionEvent actionEvent) {
        int randNumber = generateRandomNumber(1,100);
        txtRandomGenerated.setText(String.valueOf(randNumber));
    }
}