package ch.tiim.sco.gui.team;

import ch.tiim.inject.Inject;
import ch.tiim.log.Log;
import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.Team;
import ch.tiim.sco.database.model.TeamMember;
import ch.tiim.sco.gui.Page;
import ch.tiim.sco.gui.addmember.AddMemberView;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class TeamPresenter extends Page {
    private static final Log LOGGER = new Log(TeamPresenter.class);
    @FXML
    private ListView<Team> listTeams;
    @FXML
    private TextField fieldName;
    @FXML
    private TableView<TeamMember> tableMembers;
    @FXML
    private TableColumn<TeamMember, String> colFirstName;
    @FXML
    private TableColumn<TeamMember, String> colLastName;
    @FXML
    private TableColumn<TeamMember, String> colGender;
    @FXML
    private TableColumn<TeamMember, String> colAddress;
    @FXML
    private TableColumn<TeamMember, String> colEmail;
    @FXML
    private TableColumn<TeamMember, String> colBirthday;

    @Inject(name = "db-controller")
    private DatabaseController db;

    private ObservableList<Team> teams = FXCollections.observableArrayList();
    private ObservableList<TeamMember> members = FXCollections.observableArrayList();

    @Inject
    private void injected() {
        updateTeams();
    }

    @FXML
    private void initialize() {
        listTeams.setItems(teams);
        listTeams.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
            updateMembers()
        );
        tableMembers.setItems(members);

        colFirstName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFirstName()));
        colLastName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getLastName()));
        colGender.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().isFemale() ? "F" : "M"));
        colAddress.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getAddress()));
        colEmail.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getEmail()));
        colBirthday.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getBirthDay().toString()));
    }

    @FXML
    private void onBtnAdd() {
        try {
            if (!fieldName.getText().trim().isEmpty()) {
                db.getTblTeam().addTeam(new Team(-1, fieldName.getText()));
                updateTeams();
            }
        } catch (SQLException e) {
            LOGGER.warning(e);
        }
    }

    @FXML
    private void onBtnEdit() {
        Team t = listTeams.getSelectionModel().getSelectedItem();
        if (t != null && !fieldName.getText().trim().isEmpty()) {
            t.setName(fieldName.getText());
            try {
                db.getTblTeam().editTeam(t);
                updateTeams();
            } catch (SQLException e) {
                LOGGER.warning(e);
            }
        }
    }

    @FXML
    private void onBtnDelete() {
        try {
            if (listTeams.getSelectionModel().getSelectedItem() != null) {
                db.getTblTeam().deleteTeam(listTeams.getSelectionModel().getSelectedItem());
                updateTeams();
            }
        } catch (SQLException e) {
            LOGGER.warning(e);
        }
    }

    @FXML
    private void onBtnMemberEdit() {
        Team t = listTeams.getSelectionModel().getSelectedItem();
        if (t != null) {
            AddMemberView v = new AddMemberView(t);
            v.getController().showAndWait();
        }
        updateMembers();
    }

    @Override
    public void opened() {
        updateTeams();
    }

    @Override
    public String getName() {
        return "Team";
    }


    private void updateTeams() {
        try {
            int i = listTeams.getSelectionModel().getSelectedIndex();
            teams.setAll(db.getTblTeam().getAllTeams());
            listTeams.getSelectionModel().select(i);
        } catch (SQLException e) {
            LOGGER.warning(e);
        }
    }

    private void updateMembers() {
        Team t = listTeams.getSelectionModel().getSelectedItem();
        if (t == null) {
            members.clear();
        } else {
            try {
                members.setAll(db.getTblTeamContent().getMembersForTeam(t));
            } catch (SQLException e) {
                LOGGER.warning(e);
            }
        }
    }
}
