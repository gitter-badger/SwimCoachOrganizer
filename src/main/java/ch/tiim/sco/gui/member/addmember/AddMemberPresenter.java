package ch.tiim.sco.gui.member.addmember;

import ch.tiim.inject.Inject;
import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.Team;
import ch.tiim.sco.database.model.TeamMember;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddMemberPresenter {
    private static final Logger LOGGER = LogManager.getLogger(AddMemberPresenter.class.getName());
    @FXML
    private ListView<TeamMember> listExcluded;
    @FXML
    private ListView<TeamMember> listIncluded;
    @FXML
    private Parent root;

    @Inject(name = "main-stage")
    private Stage mainStage;
    @Inject(name = "db-controller")
    private DatabaseController db;
    @Inject(name = "team")
    private Team team;

    private ObservableList<TeamMember> included = FXCollections.observableArrayList();
    private ObservableList<TeamMember> excluded = FXCollections.observableArrayList();


    private Stage stage;


    @FXML
    private void initialize() {
        listExcluded.setItems(excluded);
        listIncluded.setItems(included);
    }

    @Inject
    private void injected() {
        stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Select Set");

        updateExcluded();
        updateIncluded();
    }

    private void updateExcluded() {
        try {
            excluded.setAll(db.getTblTeamContent().getMembersNotInTeam(team));
        } catch (Exception e) {
            LOGGER.warn(e);
        }
    }

    private void updateIncluded() {
        try {
            included.setAll(db.getTblTeamContent().getMembersForTeam(team));
        } catch (Exception e) {
            LOGGER.warn(e);
        }
    }

    @FXML
    void onBtnAdd() {
        TeamMember m = listExcluded.getSelectionModel().getSelectedItem();
        if (m != null) {
            try {
                db.getTblTeamContent().addMemberToTeam(team, m);
            } catch (Exception e) {
                LOGGER.warn(e);
            }
        }
        updateExcluded();
        updateIncluded();
    }

    @FXML
    void onBtnOk() {
        stage.close();
    }

    @FXML
    void onBtnRemove() {
        TeamMember m = listIncluded.getSelectionModel().getSelectedItem();
        if (m != null) {
            try {
                db.getTblTeamContent().removeMemberFromTeam(team, m);
            } catch (Exception e) {
                LOGGER.warn(e);
            }
        }
        updateExcluded();
        updateIncluded();
    }

    public void showAndWait() {
        stage.showAndWait();
    }
}
