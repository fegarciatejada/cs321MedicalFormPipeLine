package com.gmu.cs321.fall2023.fgct;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Fernanda G
 * Test class for MedicalForm.
 * This class contains test cases to validate the functionality of the MedicalForm class.
 */
public class MedicalFormTest {
    private MedicalForm form;
    /**
     * Sets up a new MedicalForm instance before each test case.
     */
    @BeforeEach
    void setUp() {
        form = MedicalForm.createNewForm("Elon Tejada", 10292020, "University Dr", "Zika Virus", 123456789, 01012021, 794339073);
    }
    /**
     * Tests that a form can be submitted successfully and cannot be submitted twice.
     */
    @Test
    void testSubmitForm() {
        assertTrue(form.submitForm());
        assertFalse(form.submitForm()); // It should return false if the form is already submitted
    }
    /**
     * Assumes the form data provided in setUp is valid.
     */
    @Test
    void testVerifyForm() {
        assertTrue(form.verifyForm()); // Assumes that verifyForm is always true for valid form
    }
    /**
     * Assumes the save operation always returns true.
     */
    @Test
    void testSaveForm() {
        assertTrue(form.saveForm()); // Assumes saveForm is always true
    }
    /**
     * Tests that the form status can be updated.
     */
    @Test
    void testUpdateForm() {
        String oldStatus = form.getStatus();
        form.updateForm();
        String newStatus = form.getStatus();
        assertNotEquals(oldStatus, newStatus);
        assertEquals("Updated", newStatus);
    }
    /**
     * Tests that a form can be deleted.
     */
    @Test
    void testDeleteForm() {
        assertTrue(form.deleteForm());
        assertNull(MedicalForm.getForm(form.getFormId())); // The form should no longer be available after deletion
    }
    /**
     * Tests the functionality of changing a form's status.
     */
    @Test
    void testChangeStatus() {
        String newStatus = "Processing";
        assertEquals("Status will change to: " + newStatus, form.changeStatus(newStatus));
        assertEquals(newStatus, form.getStatus());
    }
    /**
     * Tests the creation and retrieval of a medical form.
     */
    @Test
    void testCreateAndRetrieveForm() {
        MedicalForm newForm = MedicalForm.createNewForm("Fernanda", 1990, "Whitemoss", "Hepatitis", 987654321, 2023, 3232132);
        assertNotNull(newForm, "Newly created form should not be null");
        assertEquals(newForm, MedicalForm.getForm(newForm.getFormId()), "Should retrieve the same form by its ID");
    }
    /**
     * Tests the form verification logic for failure.
     */
    @Test
    void testVerifyFormFailure() {
        // Create a form with invalid details
        MedicalForm invalidForm = MedicalForm.createNewForm("", -1, "", "", -1, -1, -1);
        assertFalse(invalidForm.verifyForm()); // Verification should fail for invalid form
    }
}
