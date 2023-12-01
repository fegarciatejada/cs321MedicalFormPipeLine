//package gmu.cs321;
package com.gmu.cs321.fall2023.fgct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MedicalFormScreen represents a GUI application that allows users to fill out
 * and manage a medical form related to infectious diseases.
 */
public class MedicalFormScreen extends JFrame {
    private final JTextField nameTextField = new JTextField();
    private final JTextField dobTextField = new JTextField();
    private final JTextField addressTextField = new JTextField();
    private final JTextField alienNumberTextField = new JTextField();
    private final JComboBox<String> conditions = new JComboBox<>();
    private final JTextField phoneNumberField = new JTextField();
    private final JTextField conditionStartDateField = new JTextField();
    /**
     * Constructor for MedicalFormAndApprover.
     */
    public MedicalFormScreen () {
        setTitle("Medical Form");
        setSize(550, 450);

        JPanel panel = new JPanel(new GridLayout(10, 2, 5, 7));

        panel.add(new JLabel("Name:"));
        panel.add(nameTextField);

        panel.add(new JLabel("DOB (ddmmyyyy):"));
        panel.add(dobTextField);

        panel.add(new JLabel("Address:"));
        panel.add(addressTextField);

        panel.add(new JLabel("Alien Number:"));
        panel.add(alienNumberTextField);

        panel.add(new JLabel("Condition Start Date (ddmmyyyy):"));
        panel.add(conditionStartDateField);

        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneNumberField);

        panel.add(new JLabel("Medical Condition:"));
        conditions.addItem("Tuberculosis");
        conditions.addItem("HIV/AIDS");
        conditions.addItem("Malaria");
        conditions.addItem("Zika Virus");
        conditions.addItem("Chickenpox");
        conditions.addItem("Measles");
        conditions.addItem("Hepatitis");

        panel.add( conditions );

        JButton submitHit = createSubmitButton();
        panel.add(submitHit);

        JButton deleteHit = createDeleteButton();
        panel.add(deleteHit);

        JButton newFormHit = createNewFormButton();
        panel.add(newFormHit);

        JButton saveHit = createSaveButton();
        panel.add(saveHit);

        add(panel);

        setVisible(true);
    }
    /**
     * Creates and displays a login dialog for user authentication.
     */
    private static void login() {
        JDialog loginDialog = new JDialog((Frame)null, "Login", true);
        loginDialog.setSize(350, 200);
        loginDialog.setLayout(new FlowLayout());
        loginDialog.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        loginDialog.add(panel);

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login To Your Medical Form");

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginDialog.setVisible(false);
                loginDialog.dispose();
                new MedicalFormScreen ();
            }
        });

        loginDialog.setVisible(true);
    }
    /**
     * Creates and returns a submit button.
     * @return  The submit button.
     */
    private JButton createSubmitButton() {
        JButton submitButton = new JButton("Submit Form");
        submitButton.addActionListener(e -> {
            showFormDataScreen();
        });
        return submitButton;
    }
    /**
     * Creates and returns a delete button to reset the form fields.
     * @return The delete button.
     */
    private JButton createDeleteButton() {
        JButton deleteButton = new JButton("Delete Form");
        deleteButton.addActionListener(e -> resetFormFields());
        return deleteButton;
    }
    /**
     * Creates and returns a new form button.
     * @return The new form button.
     */
    private JButton createNewFormButton() {
        JButton newFormButton = new JButton("Create New Form");
        newFormButton.addActionListener(e -> resetFormFields());
        return newFormButton;
    }
    /**
     * Creates and returns a save button to save the form.
     * @return The save button.
     */
    private JButton createSaveButton() {
        JButton saveButton = new JButton("Save Form");
        saveButton.addActionListener(e -> {
            System.out.println("Form data saved for later!");
            printFormData();
        });
        return saveButton;
    }
    /**
     * Resets the form to default.
     */
    private void resetFormFields() {
        nameTextField.setText("");
        dobTextField.setText("");
        addressTextField.setText("");
        alienNumberTextField.setText("");
        conditions.setSelectedIndex(0);
        System.out.println("Form has been reset.");
    }
    /**
     * Displays the data entered in the form in a new window.
     */
    private void showFormDataScreen() {
        
        String dateRegex = "^\\d{2}\\d{2}\\d{4}$";
        String phoneRegex = "^\\d{10}$";

        // Check for empty fields
        if (nameTextField.getText().trim().isEmpty() ||
                dobTextField.getText().trim().isEmpty() ||
                addressTextField.getText().trim().isEmpty() ||
                alienNumberTextField.getText().trim().isEmpty() ||
                phoneNumberField.getText().trim().isEmpty() ||
                conditionStartDateField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!dobTextField.getText().matches(dateRegex) || !conditionStartDateField.getText().matches(dateRegex)) {
            JOptionPane.showMessageDialog(this, "Invalid Date Format. Please use ddmmyyyy", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!phoneNumberField.getText().matches(phoneRegex)) {
            JOptionPane.showMessageDialog(this, "Phone number must be 10 digits long", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        StringBuilder formDataBuilder = new StringBuilder();

        formDataBuilder.append("<html><body>");
        formDataBuilder.append("Name: ").append(nameTextField.getText()).append("<br/>");
        formDataBuilder.append("DOB: ").append(dobTextField.getText()).append("<br/>");
        formDataBuilder.append("Address: ").append(addressTextField.getText()).append("<br/>");
        formDataBuilder.append("Alien Number: ").append(alienNumberTextField.getText()).append("<br/>");
        formDataBuilder.append("Medical Condition: ").append(conditions.getSelectedItem()).append("<br/>");
        formDataBuilder.append("Phone Number: ").append(phoneNumberField.getText()).append("<br/>");
        formDataBuilder.append("Condition Start Date: ").append(conditionStartDateField.getText()).append("<br/>");
        formDataBuilder.append("</body></html>");

        MedicalForm.createNewForm(nameTextField.getText(), Integer.parseInt(dobTextField.getText()), addressTextField.getText(), conditions.getSelectedItem().toString(), Integer.parseInt(alienNumberTextField.getText()), Integer.parseInt(conditionStartDateField.getText()), Long.parseLong(phoneNumberField.getText()));

        String formData = formDataBuilder.toString();

        JFrame formDataFrame = new JFrame("Submitted Form Data");
        formDataFrame.setSize(300, 300);
        formDataFrame.setLocationRelativeTo(null);

        JLabel formDataLabel = new JLabel(formData);
        formDataFrame.add(formDataLabel);

        formDataFrame.setVisible(true);
    }
    /**
     * Debugging purposes.
     */
    private void printFormData() {
        System.out.println("Name: " + nameTextField.getText());
        System.out.println("DOB: " + dobTextField.getText());
        System.out.println("Address: " + addressTextField.getText());
        System.out.println("Alien Number: " + alienNumberTextField.getText());
        System.out.println("Medical Condition: " + conditions.getSelectedItem());
        System.out.println("Phone Numer: " + phoneNumberField.getText());
        System.out.println("Phone Numer: " + conditionStartDateField.getText());

    }
    /**
     * The main
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            login();
        });

        Approver a = new Approver();
        a.showApproverScreen();
    }
}