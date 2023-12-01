package com.gmu.cs321.fall2023.fgct;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Fernanda G
 * Represents a medical form with personal and medical details of a patient.
 */
public class MedicalForm {
    private String name;
    private long dob;
    private String address;
    private String condition;
    private int alienNumber;
    private long dateWhenConditionStarted;
    private long phoneNumber;
    private boolean submit;
    private int formId;
    private String status;
    private static int nextID = 0;
    private static List<MedicalForm> forms = new ArrayList<>();
    /**
     * Constructs a new MedicalForm with the provided details.
     *
     * @param name                    the name of the patient
     * @param dob                     the date of birth of the patient
     * @param address                 the address of the patient
     * @param condition               the medical condition of the patient
     * @param alienNumber             a unique identifier for the patient
     * @param dateWhenConditionStarted the date when the condition was first diagnosed
     * @param phoneNumber             the phone number of the patient
     */
    public MedicalForm(String name, long dob, String address, String condition, int alienNumber, long dateWhenConditionStarted, long phoneNumber) {
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.condition = condition;
        this.alienNumber = alienNumber;
        this.dateWhenConditionStarted = dateWhenConditionStarted;
        this.phoneNumber = phoneNumber;
        this.formId = getNextID();
        this.status = "New";
        forms.add(this);
    }
    /**
     * Submits the medical form.
     * @return true if the form was successfully submitted, false if it was already submitted.
     */
    public boolean submitForm() {
        if (!submit) {
            submit = true;
            status = "Submitted";
            return true;
        }
        return false;
    }
    /**
     * Verifies the medical form details.
     * @return true if the details are valid, false otherwise.
     */
    public boolean verifyForm() {
        return dob > 0 && !address.isEmpty() && !condition.isEmpty() && alienNumber > 0 &&phoneNumber>0;
    }
    /**
     * Saves the medical form.
     * @return true if the form was successfully saved
     */
    public boolean saveForm() {
        return true;
    }
    /**
     * Updates the medical form status to "Updated".
     * @return the new status of the form.
     */
    public String updateForm() {
        status = "Updated";
        return status;
    }
    /**
     * Deletes the medical form from the system.
     * @return true if the form was successfully deleted.
     */
    public boolean deleteForm() {
        return forms.remove(this);
    }
    /**
     * Creates and adds a new medical form to the system.
     * @param name                    the name
     * @param dob                     the date of birth
     * @param address                 the address
     * @param condition               the medical condition
     * @param alienNumber             a unique number
     * @param dateWhenConditionStarted the date when the condition was first diagnosed
     * @param phoneNumber             the phone number
     * @return the newly created MedicalForm object
     */
    public static MedicalForm createNewForm(String name, long dob, String address, String condition, int alienNumber, long dateWhenConditionStarted, long phoneNumber) {
        MedicalForm form = new MedicalForm(name, dob, address, condition, alienNumber, dateWhenConditionStarted, phoneNumber);
        return form;
    }

    /**
     * Retrieves a medical form by its ID.
     * @param formId the ID of the medical form to retrieve
     * @return the MedicalForm object with the specified ID, or null if not found
     */
    public static MedicalForm getForm(int formId) {
        for (MedicalForm form : forms) {
            if (form.getFormId() == formId) {
                return form;
            }
        }
        return null;
    }
    /**
     * Changes the status of the medical form.
     * @param newStatus the new status to set
     * @return a message indicating the new status
     */
    public String changeStatus(String newStatus) {
        this.status = newStatus;
        return "Status will change to: " + newStatus;
    }
    /** Method to retrieve unique Id */
    private static int getNextID() {
        return nextID++;
    }
    /** Getters and Setters */
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public long getDob() { return dob; }
    public void setDob(int dob) { this.dob = dob; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
    public int getAlienNumber() { return alienNumber; }
    public void setAlienNumber(int alienNumber) { this.alienNumber = alienNumber; }
    public long getDateWhenConditionStarted() { return dateWhenConditionStarted; }
    public void setDateWhenConditionStarted(int dateWhenConditionStarted) { this.dateWhenConditionStarted = dateWhenConditionStarted; }
    public long getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(int phoneNumber) { this.phoneNumber = phoneNumber; }
    public boolean isSubmitted() { return submit; }
    public int getFormId() { return formId; }
    public String getStatus() { return status; }

    public String getDOB ( ) {
        return  String.valueOf ( this.dob);
    }
}
