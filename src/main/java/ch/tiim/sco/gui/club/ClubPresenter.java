package ch.tiim.sco.gui.club;

import ch.tiim.sco.database.model.Club;
import ch.tiim.sco.database.model.Team;
import ch.tiim.sco.gui.Page;
import ch.tiim.sco.lenex.model.Nation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class ClubPresenter extends Page {

    @FXML
    private ListView<Club> listClubs;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldShortName;
    @FXML
    private TextField fieldEnglishName;
    @FXML
    private ChoiceBox<Nation> choiceNationality;
    @FXML
    private TextField fieldClubCode;
    @FXML
    private ChoiceBox<ch.tiim.sco.lenex.model.Club.TypeClub> choiceType;
    @FXML
    private Spinner<?> spinnerClubId;
    @FXML
    private TextField fieldEnglishShortName;
    @FXML
    private ListView<Team> listTeams;

    private ObservableList<Team> teams = FXCollections.observableArrayList();
    private ObservableList<Club> clubs = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        listClubs.setItems(clubs);
        listTeams.setItems(teams);
        choiceNationality.getItems().setAll(Nation.values());
        choiceType.getItems().setAll(ch.tiim.sco.lenex.model.Club.TypeClub.values());
    }

    @FXML
    private void onBtnAdd() {

    }

    @FXML
    private void onBtnDelete() {

    }

    @FXML
    private void onBtnEdit() {

    }

    @FXML
    private void onBtnTeamEdit() {

    }

    private Club getClubFromFields() {
        return null;
    }


    @Override
    public String getName() {
        return "Club";
    }
}
