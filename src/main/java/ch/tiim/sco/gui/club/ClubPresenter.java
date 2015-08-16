package ch.tiim.sco.gui.club;

import ch.tiim.inject.Inject;
import ch.tiim.log.Log;
import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.Club;
import ch.tiim.sco.database.model.Team;
import ch.tiim.sco.gui.Page;
import ch.tiim.sco.lenex.model.Nation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.InputStream;
import java.sql.SQLException;

public class ClubPresenter extends Page {
    private static final Log LOGGER = new Log(ClubPresenter.class);
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
    private Spinner<Integer> spinnerClubId;
    @FXML
    private TextField fieldEnglishShortName;
    @FXML
    private ListView<Team> listTeams;

    @Inject(name = "db-controller")
    private DatabaseController db;

    private ObservableList<Team> teams = FXCollections.observableArrayList();
    private ObservableList<Club> clubs = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        listClubs.setItems(clubs);
        listTeams.setItems(teams);

        listClubs.getSelectionModel().selectedItemProperty().addListener((o, oVal, nVal) -> selectClub(nVal));

        choiceNationality.getItems().setAll(Nation.values());
        choiceType.getItems().setAll(ch.tiim.sco.lenex.model.Club.TypeClub.values());

        spinnerClubId.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0));
    }

    @FXML
    private void onBtnAdd() {
        Club c = getClubFromFields();
        try {
            db.getTblClub().addClub(c);
        } catch (SQLException e) {
            LOGGER.warning(e);
        }
        updateClubs();
    }

    @FXML
    private void onBtnDelete() {
        Club c = listClubs.getSelectionModel().getSelectedItem();
        try {
            db.getTblClub().deleteClub(c);
        } catch (SQLException e) {
            LOGGER.warning(e);
        }
        updateClubs();
    }

    @FXML
    private void onBtnEdit() {
        Club c = listClubs.getSelectionModel().getSelectedItem();
        Club newC = getClubFromFields();
        newC.setId(c.getId());
        try {
            db.getTblClub().updateClub(newC);
        } catch (SQLException e) {
            LOGGER.warning(e);
        }
        updateClubs();
    }

    @FXML
    private void onBtnTeamEdit() {

    }

    private Club getClubFromFields() {
        return new Club(
                -1,
                fieldName.getText(),
                fieldShortName.getText(),
                fieldEnglishName.getText(),
                fieldEnglishShortName.getText(),
                fieldClubCode.getText(),
                choiceNationality.getValue() == null ? null : choiceNationality.getValue().toString(),
                spinnerClubId.getValue()
        );
    }

    private void selectClub(Club c) {
        if (c == null) return;
        fieldName.setText(c.getName());
        fieldShortName.setText(c.getNameShort());
        fieldEnglishName.setText(c.getNameEnglish());
        fieldEnglishShortName.setText(c.getNameEnglishShort());
        fieldClubCode.setText(c.getCode());
        if (c.getNationality() != null && !c.getNationality().isEmpty()) {
            choiceNationality.setValue(Nation.valueOf(c.getNationality()));
        } else {
            choiceNationality.setValue(null);
        }
        spinnerClubId.getEditor().setText(String.valueOf(c.getIdExtern()));
    }


    private void updateClubs() {
        try {
            Club i = listClubs.getSelectionModel().getSelectedItem();
            clubs.setAll(db.getTblClub().getAll());
            listClubs.getSelectionModel().select(i);
        } catch (SQLException e) {
            LOGGER.warning(e);
        }
    }

    @Override
    public void opened() {
        updateClubs();
    }

    @Override
    public String getName() {
        return "Club";
    }

    @Override
    public InputStream getIcon() {
        return ClubPresenter.class.getResourceAsStream("icon2.png");
    }
}
