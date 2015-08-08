package ch.tiim.trainingmanager.gui.member;

import ch.tiim.inject.Inject;
import ch.tiim.javafx.ValidationListener;
import ch.tiim.log.Log;
import ch.tiim.trainingmanager.database.DatabaseController;
import ch.tiim.trainingmanager.database.model.TeamMember;
import ch.tiim.trainingmanager.gui.Page;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class MemberPresenter implements Page {
    private static final String PATTERN_NAME = "^[^ ].*[^ ]$";
    private static final String PATTERN_EMAIL = "^.+@.+\\..+$|^$";
    private static final String PATTERN_PHONE = "^.*(?=.*\\d).*$|^$";
    private static final Log LOGGER = new Log(MemberPresenter.class);
    @FXML
    private ListView<TeamMember> listMembers;
    @FXML
    private TextField fieldNameFirst;
    @FXML
    private TextField fieldNameLast;
    @FXML
    private TextField fieldEmail;
    @FXML
    private TextField fieldAddress1;
    @FXML
    private TextField fieldAddress2;
    @FXML
    private TextField fieldAddress3;
    @FXML
    private DatePicker fieldBirthday;
    @FXML
    private TextField fieldPhonePriv;
    @FXML
    private TextField fieldPhoneMob;
    @FXML
    private TextField fieldPhoneWork;
    @FXML
    private TextArea fieldNotes;
    @FXML
    private TextField fieldLicense;
    @FXML
    private RadioButton toggleFemale;
    @FXML
    private RadioButton toggleMale;

    @Inject(name = "db-controller")
    private DatabaseController db;

    private final ObservableList<TeamMember> members = FXCollections.observableArrayList();

    @Inject
    private void injected() {
        updateMemberList();
    }

    @FXML
    private void initialize() {
        listMembers.setItems(members);
        listMembers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selected(newValue)
        );
        fieldNameFirst.textProperty().addListener(new ValidationListener(PATTERN_NAME, fieldNameFirst));
        fieldNameLast.textProperty().addListener(new ValidationListener(PATTERN_NAME, fieldNameLast));
        fieldEmail.textProperty().addListener(new ValidationListener(PATTERN_EMAIL, fieldEmail));
        fieldPhonePriv.textProperty().addListener(new ValidationListener(PATTERN_PHONE, fieldPhonePriv));
        fieldPhoneMob.textProperty().addListener(new ValidationListener(PATTERN_PHONE, fieldPhoneMob));
        fieldPhoneWork.textProperty().addListener(new ValidationListener(PATTERN_PHONE, fieldPhoneWork));
    }

    private void selected(TeamMember v) {
        if (v == null) return;
        fieldNameFirst.setText(v.getFirstName());
        fieldNameLast.setText(v.getLastName());
        fieldEmail.setText(v.getEmail());
        String[] address = v.getAddress().split("\n");
        if (address.length == 3) {
            fieldAddress1.setText(address[0]);
            fieldAddress2.setText(address[1]);
            fieldAddress3.setText(address[2]);
        } else {
            fieldAddress1.setText("");
            fieldAddress2.setText("");
            fieldAddress3.setText("");
        }
        fieldBirthday.setValue(v.getBirthDay());
        fieldPhonePriv.setText(v.getPhonePrivate());
        fieldPhoneMob.setText(v.getPhoneMobile());
        fieldPhoneWork.setText(v.getPhoneWork());
        fieldNotes.setText(v.getNotes());
        fieldLicense.setText(v.getLicense());
        toggleFemale.setSelected(v.isFemale());
        toggleMale.setSelected(!v.isFemale());
    }


    @FXML
    private void onBtnNew() {
        if (validate()) {
            try {
                db.getTblTeamMember().addMember(getMember());
                updateMemberList();
            } catch (SQLException e) {
                LOGGER.warning(e);
            }
        }
    }

    @FXML
    private void onBtnEdit() {
        TeamMember m = listMembers.getSelectionModel().getSelectedItem();
        if (validate() && m != null) {
            TeamMember newM = getMember();
            newM.setId(m.getId());
            try {
                db.getTblTeamMember().updateMember(newM);
                updateMemberList();
            } catch (SQLException e) {
                LOGGER.warning(e);
            }
        }
    }

    @FXML
    private void onBtnDelete() {
        TeamMember m = listMembers.getSelectionModel().getSelectedItem();
        if (m != null) {
            try {
                db.getTblTeamMember().deleteMember(m);
                updateMemberList();
            } catch (SQLException e) {
                LOGGER.warning(e);
            }
        }
    }

    @Override
    public void opened() {
        updateMemberList();
    }

    @Override
    public String getName() {
        return "Member";
    }

    private void updateMemberList() {
        try {
            int i = listMembers.getSelectionModel().getSelectedIndex();
            members.setAll(db.getTblTeamMember().getAllMembers());
            listMembers.getSelectionModel().select(i);

        } catch (SQLException e) {
            LOGGER.warning(e);
        }
    }

    private TeamMember getMember() {
        return new TeamMember(
                -1,
                fieldNameFirst.getText(),
                fieldNameLast.getText(),
                fieldBirthday.getValue(),
                fieldAddress1.getText() + "\n" +
                        fieldAddress2.getText() + "\n" +
                        fieldAddress3.getText(),
                fieldPhonePriv.getText(),
                fieldPhoneWork.getText(),
                fieldPhoneMob.getText(),
                fieldEmail.getText(),
                fieldLicense.getText(),
                toggleFemale.isSelected(),
                fieldNotes.getText()
        );
    }

    private boolean validate() {
        return Page.validateTextField(fieldNameFirst, PATTERN_NAME) &&
                Page.validateTextField(fieldNameLast, PATTERN_NAME) &&
                Page.validateTextField(fieldBirthday.getEditor(), ".+") &&
                Page.validateTextField(fieldPhoneMob, PATTERN_PHONE) &&
                Page.validateTextField(fieldPhoneWork, PATTERN_PHONE) &&
                Page.validateTextField(fieldPhonePriv, PATTERN_PHONE) &&
                Page.validateTextField(fieldEmail, PATTERN_EMAIL);
    }
}
