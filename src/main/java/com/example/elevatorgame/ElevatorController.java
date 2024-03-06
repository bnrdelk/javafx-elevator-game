package com.example.elevatorgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ElevatorController {

    @FXML
    ImageView elevator;
    @FXML
    ImageView endingImage;
    @FXML
    Label promptLabel;
    @FXML
    Label infoLabel;
    @FXML
    Label floorLabel;
    @FXML
    Label numberOfPeopleLabel;
    @FXML
    Label nameOfLastPersonLabel;
    @FXML
    Button loginButton;
    @FXML
    Button upButton;
    @FXML
    Button downButton;
    @FXML
    Button continueButton;
    @FXML
    Button exitButton;
    private String name;
    private int targetFloor;
    @FXML
    TextField nameText;
    @FXML
    Label travelMeterLabel;
    @FXML
    Label pointLabel;
    @FXML
    Label endingLabel;
    @FXML
    Label endingPromptLabel;
    private int points = 0;
    @FXML
    TextField targetFloorText;
    int primarySize,currentFloor = 0;
    Elevator e = new Elevator();
    private double y,x;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Scene scene2;
    private Parent root2;
    @FXML
    ImageView loginImage;
    @FXML
    private TextField heroNameText;
    private String heroName;
    @FXML//login button's action, switch to game scene.
    public void login(ActionEvent event) throws IOException {
        root = FXMLLoader.load(ElevatorApplication.class.getResource("elevator-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(514);
        stage.setHeight(620);
        stage.setTitle("Elevator Game");
        stage.show();

        heroName = heroNameText.getText(); //getting the user's name to address to person
    }
/*
    //continue button's action, switch to happy ending scene.
    @FXML
    public void continueAction(ActionEvent event) throws IOException {
        if(points >= 4){
            Parent root = FXMLLoader.load(getClass().getResource("happyEnding-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setWidth(514);
            stage.setHeight(620);
            stage.setTitle("END");
            stage.show();
        }
        else
            infoLabel.setText("You can't click continue unless you reach 4 points. Go get points!");
    }
    */

//up button's action, moves dragon up.
    @FXML
    public void up(ActionEvent actionEvent) {
        try{
            if(e.getCurrentFloor() < e.getMaxFloor()){
                primarySize = e.getPeople().getSize();
                currentFloor = e.getCurrentFloor() +1;
                e.goToFloor(currentFloor);

                System.out.println("Going Up!");
                floorLabel.setText("Floor: " + e.getCurrentFloor());

                elevator.setY(y-=25);

                travelMeterLabel.setText("Travel Meter: "+e.getTravelMeter());

                if(primarySize == e.getPeople().getSize()+1){
                    infoLabel.setText(e.getExitingName()+" reaches the target floor and leaves us. It was the best trip ever!");
                    points ++;
                    pointLabel.setText("Points: " + points);
                    numberOfPeopleLabel.setText("Number of People: " + e.getPeople().getSize());
                }
            }
        }

        catch(Exception e){
            if(points < 4)
            infoLabel.setText("You need to add person and gain points "+heroName+". You need 4 points, don't forget!");
            else {
                infoLabel.setText("Congratulations! What a tough game it was..But, you won! Click 'continue' for the story!");
            }
            }
        }


//down button's action, moves dragon down.
    @FXML
    private void down(){
        try{
            if(e.getCurrentFloor() > e.getMinFloor()){

                    primarySize = e.getPeople().getSize();

                    e.goToFloor(e.getCurrentFloor()-1);
                    System.out.println("Going Down!");
                    floorLabel.setText("Floor: " + e.getCurrentFloor());
                    elevator.setY(y+=25);

                    travelMeterLabel.setText("Travel Meter: "+ Elevator.getTravelMeter());

                    if(primarySize == e.getPeople().getSize()+1){
                        infoLabel.setText(e.getExitingName()+" reaches the target floor and leaves us. It was the best trip ever!");
                        points ++;
                        pointLabel.setText("Points: " + points);
                        numberOfPeopleLabel.setText("Number of People: " + e.getPeople().getSize());
                    }


            }
            else{
                infoLabel.setText("Can't go down, you hit the bedrock " + heroName + "! Add people to your dragon and reach their targets so you can gain the points!");
            }
        }
        catch(Exception e){
            if(points < 4)
                infoLabel.setText("You need to add person and gain points." + heroName + " You need 4 points, don't forget!");
            else {
                infoLabel.setText("Congratulations! What a tough game it was..But, you won! Click 'continue' for the story!");
            }
        }
    }

//enter button's action.
    @FXML
    private void enter(){

        if(e.getPeople().getSize() != e.getCapacity()){
            try{
                name = nameText.getText();
                targetFloor = Integer.parseInt(targetFloorText.getText());
                if(0 > targetFloor || targetFloor > 10)
                    promptLabel.setText("Enter the target floor between 0-10");
                else {
                    e.enter(name,targetFloor);

                    numberOfPeopleLabel.setText("Number of people :" + e.getPeople().getSize());
                    nameOfLastPersonLabel.setText("Name of last person: " + name);
                    floorLabel.setText("Floor: " + e.getCurrentFloor());

                    promptLabel.setText(name + "mounted our dragon. ready for adventure " + name + "!");
                }
            }
            catch (NumberFormatException e){
                    promptLabel.setText("Enter only numbers for target, please.");
                }
                //catch (Exception e){
                //  promptLabel.setText("0-10 try again!");
                //}
        }

        else{
            promptLabel.setText("Capacity is full!");
            }
    }
}