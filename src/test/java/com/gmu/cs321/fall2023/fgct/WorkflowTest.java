package com.gmu.cs321.fall2023.fgct;

/**
 * Imports
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Hanna Mendoza-Garcia
 * Used to test Workflow.java
 */
public class WorkflowTest {
    //Example medical forms used to test
    private MedicalForm form1 = new MedicalForm("John Doe", 1, "address1", "Covid", 2, 3, 703);
    private MedicalForm form2 = new MedicalForm("Peter Parker", 4, "address2", "Tuberculosis", 5, 6, 704);
    private MedicalForm form3 = new MedicalForm("Mary Jane", 7, "address3", "Chickenpox", 8, 9, 705);

    /**
     * Check if single form is correctly inserted and returned from completeQueue.
     * Each form is tested one at a time.
     */
    @Test
    public void singleFormCompleteQueueTest() {
        assertTrue(Workflow.isCompleteEmpty()); //completeQueue should start empty

        assertTrue(Workflow.insertComplete(3)); //Insert form1 into completeQueue successfully
        assertFalse(Workflow.isCompleteEmpty()); //completeQueue should not be empty anymore

        assertEquals(Workflow.getNextComplete(), 3); //Check if IDs match
        assertTrue(Workflow.isCompleteEmpty()); //completeQueue should be empty

        assertTrue(Workflow.insertComplete(6)); //Insert form2 into completeQueue successfully
        assertFalse(Workflow.isCompleteEmpty()); //completeQueue should not be empty anymore

        assertEquals(Workflow.getNextComplete(), 6); //Check if IDs match
        assertTrue(Workflow.isCompleteEmpty()); //completeQueue should be empty

        assertTrue(Workflow.insertComplete(9)); //Insert form3 into completeQueue successfully
        assertFalse(Workflow.isCompleteEmpty()); //completeQueue should not be empty anymore

        assertEquals(Workflow.getNextComplete(), 9); //Check if IDs match
        assertTrue(Workflow.isCompleteEmpty()); //completeQueue should be empty
    }

    /**
     * Check if multiple forms are inserted and returned in the correct order from completeQueue,
     * if all forms are are inserted first and then are returned.
     */
    @Test
    public void multipleFormsCompleteQueueTest() {
        assertTrue(Workflow.isCompleteEmpty()); //completeQueue should start empty

        assertTrue(Workflow.insertComplete(3)); //Insert form1 into completeQueue successfully
        assertFalse(Workflow.isCompleteEmpty()); //completeQueue should not be empty anymore

        assertTrue(Workflow.insertComplete(6)); //Insert form2 into completeQueue successfully
        assertFalse(Workflow.isCompleteEmpty());

        assertTrue(Workflow.insertComplete(9)); //Insert form3 into completeQueue successfully
        assertFalse(Workflow.isCompleteEmpty());

        assertEquals(Workflow.getNextComplete(), 3); //Check if IDs match for form1
        assertFalse(Workflow.isCompleteEmpty());

        assertEquals(Workflow.getNextComplete(), 6); //Check if IDs match for form2
        assertFalse(Workflow.isCompleteEmpty());

        assertEquals(Workflow.getNextComplete(), 9); //Check if IDs match for form3
        assertTrue(Workflow.isCompleteEmpty()); //completeQueue should be empty
    }

    /**
     * Check if multiple forms are inserted and returned in the correct order from completeQueue,
     * if 2 forms are inserted first, 1 is returned, 1 is inserted, and the last 2 are returned.
     */
    @Test
    public void multipleFormsInterweavedCompleteQueueTest() {
        assertTrue(Workflow.isCompleteEmpty()); //completeQueue should start empty

        assertTrue(Workflow.insertComplete(3)); //Insert form1 into completeQueue successfully
        assertFalse(Workflow.isCompleteEmpty()); //completeQueue should not be empty anymore

        assertTrue(Workflow.insertComplete(6)); //Insert form2 into completeQueue successfully
        assertFalse(Workflow.isCompleteEmpty());

        assertEquals(Workflow.getNextComplete(), 3); //Check if IDs match for form1
        assertFalse(Workflow.isCompleteEmpty());

        assertTrue(Workflow.insertComplete(9)); //Insert form3 into completeQueue successfully
        assertFalse(Workflow.isCompleteEmpty());

        assertEquals(Workflow.getNextComplete(), 6); //Check if IDs match for form2
        assertFalse(Workflow.isCompleteEmpty());

        assertEquals(Workflow.getNextComplete(), 9); //Check if IDs match for form3
        assertTrue(Workflow.isCompleteEmpty()); //completeQueue should be empty
    }

    /**
     * Check if correct output (-1) is returned when getNextComplete() is called on an empty completeQueue.
     */
    @Test
    public void emptyCompleteQueueTest() {
        assertTrue(Workflow.isCompleteEmpty()); //completeQueue should start empty
        assertEquals(Workflow.getNextComplete(), -1); //should return -1
        assertTrue(Workflow.isCompleteEmpty()); //completeQueue should still be empty
    }

    /**
     * Check if single form is correctly inserted and returned from savedQueue.
     * Each form is tested one at a time.
     */
//    @Test
//    public void singleFormSavedQueueTest() {
//        assertTrue(Workflow.isSavedEmpty()); //savedQueue should start empty
//
//        assertTrue(Workflow.insertSaved(form1)); //Insert form1 into savedQueue successfully
//        assertFalse(Workflow.isSavedEmpty()); //savedQueue should not be empty anymore
//
//        assertEquals(Workflow.getNextSaved(), 3); //Check if IDs match for form1
//        assertTrue(Workflow.isSavedEmpty()); //savedQueue should be empty
//
//        assertTrue(Workflow.insertSaved(form2)); //Insert form2 into savedQueue successfully
//        assertFalse(Workflow.isSavedEmpty()); //savedQueue should not be empty anymore
//
//        assertEquals(Workflow.getNextSaved(), 6); //Check if IDs match for form2
//        assertTrue(Workflow.isSavedEmpty()); //savedQueue should be empty
//
//        assertTrue(Workflow.insertSaved(form3)); //Insert form3 into savedQueue successfully
//        assertFalse(Workflow.isSavedEmpty()); //savedQueue should not be empty anymore
//
//        assertEquals(Workflow.getNextSaved(), 9); //Check if IDs match for form3
//        assertTrue(Workflow.isSavedEmpty()); //savedQueue should be empty
//    }

    /**
     * Check if multiple forms are inserted and returned in the correct order from savedQueue,
     * if all forms are are inserted first and then are returned.
     */
//    @Test
    public void multipleFormsSavedQueueTest() {
        assertTrue(Workflow.isSavedEmpty()); //savedQueue should start empty

        assertTrue(Workflow.insertSaved(form1)); //Insert form1 into savedQueue successfully
        assertFalse(Workflow.isSavedEmpty()); //savedQueue should not be empty anymore

        assertTrue(Workflow.insertSaved(form2)); //Insert form2 into savedQueue successfully
        assertFalse(Workflow.isSavedEmpty());

        assertTrue(Workflow.insertSaved(form3)); //Insert form3 into savedQueue successfully
        assertFalse(Workflow.isSavedEmpty());

        assertEquals(Workflow.getNextSaved(), 3); //Check if IDs match for form1
        assertFalse(Workflow.isSavedEmpty());

        assertEquals(Workflow.getNextSaved(), 6); //Check if IDs match for form2
        assertFalse(Workflow.isSavedEmpty());

        assertEquals(Workflow.getNextSaved(), 9); //Check if IDs match for form3
        assertTrue(Workflow.isSavedEmpty()); //savedQueue should be empty
    }

    /**
     * Check if multiple forms are inserted and returned in the correct order from savedQueue,
     * if 2 forms are inserted first, 1 is returned, 1 is inserted, and the last 2 are returned.
     */
//    @Test
    public void multipleFormsInterweavedSavedQueueTest() {
        assertTrue(Workflow.isSavedEmpty()); //savedQueue should start empty

        assertTrue ( Workflow.insertSaved(form1)); //Insert form1 into savedQueue successfully
        assertFalse(Workflow.isSavedEmpty()); //savedQueue should not be empty anymore

        assertTrue(Workflow.insertSaved(form2)); //Insert form2 into savedQueue successfully
        assertFalse(Workflow.isSavedEmpty());

        assertEquals(Workflow.getNextSaved(), 3); //Check if IDs match for form1
        assertFalse(Workflow.isSavedEmpty());

        assertTrue(Workflow.insertSaved(form3)); //Insert form3 into savedQueue successfully
        assertFalse(Workflow.isSavedEmpty());

        assertEquals(Workflow.getNextSaved(), 6); //Check if IDs match for form2
        assertFalse(Workflow.isSavedEmpty());

        assertEquals(Workflow.getNextSaved(), 9); //Check if IDs match for form3
        assertTrue(Workflow.isSavedEmpty()); //savedQueue should be empty
    }

    /**
     * Check if correct output (-1) is returned when getNextSaved() is called on an empty savedQueue.
     */
    @Test
    public void emptySavedQueueTest() {
        assertTrue(Workflow.isSavedEmpty()); //savedQueue should start empty
        assertEquals(Workflow.getNextSaved(), -1); //should return -1
        assertTrue(Workflow.isSavedEmpty()); //savedQueue should still be empty
    }

    /**
     * Check if a single medical condition and corresponding next steps message can successfully be added, retrieved, and removed from nextStepsList.
     */
    @Test
    public void singleNextStepsTest() {
        assertTrue(Workflow.insertNextSteps("Covid", "Need to quarantine")); //Insert medical condition and message into nextStepsList successfully
        assertEquals(Workflow.getNextStepsMessage("Covid"), "Need to quarantine"); //Check if next steps message matches
        assertTrue(Workflow.removeNextSteps("Covid")); //Remove medical condition and message from nextStepsList successfully
    }

    /**
     * Check if multiple medical conditions and corresponding next steps messages can successfully be added, retrieved, and removed from nextStepsList.
     */
    @Test
    public void multipleNextStepsTest() {
        assertTrue(Workflow.insertNextSteps("Covid", "Need to quarantine")); //Insert first medical condition and message into nextStepsList successfully
        assertTrue(Workflow.insertNextSteps("Tuberculosis", "Keep room ventilated")); //Insert second medical condition and message into nextStepsList successfully
        assertTrue(Workflow.insertNextSteps("Chickenpox", "Avoid scratching and avoid contact with others")); //Insert third medical condition and message into nextStepsList successfully

        assertEquals(Workflow.getNextStepsMessage("Covid"), "Need to quarantine"); //Check if next steps message for condition 1 matches
        assertEquals(Workflow.getNextStepsMessage("Tuberculosis"), "Keep room ventilated"); //Check if next steps message for condition 2 matches
        assertEquals(Workflow.getNextStepsMessage("Chickenpox"), "Avoid scratching and avoid contact with others"); //Check if next steps message for condition 3 matches

        assertTrue(Workflow.removeNextSteps("Covid")); //Remove medical condition 1 and its message from nextStepsList successfully
        assertTrue(Workflow.removeNextSteps("Tuberculosis")); //Remove medical condition 2 and its message from nextStepsList successfully
        assertTrue(Workflow.removeNextSteps("Chickenpox")); //Remove medical condition 3 and its message from nextStepsList successfully
    }

    /**
     * Check if insertNextSteps() fails if it is called on a medical condition that is already in nextStepsList.
     */
    @Test
    public void insertNextStepsFailTest() {
        assertTrue(Workflow.insertNextSteps("Covid", "Need to quarantine")); //Insert medical condition and message into nextStepsList successfully
        assertFalse(Workflow.insertNextSteps("Covid", "Need to quarantine")); //Attempt to insert same medical condition and message into nextStepsList, should be unsuccessful
        assertFalse(Workflow.insertNextSteps("Covid", "Wear a mask")); //Attempt to insert same medical condition and with a different message into nextStepsList, should be unsuccessful
        assertTrue(Workflow.removeNextSteps("Covid")); //Remove medical condition and its message from nextStepsList successfully
    }

    /**
     * Check if correct output (empty String) is returned when getNextStepsMessage() is called on a medical condition that is not in nextStepsList.
     */
    @Test
    public void noAvailableNextStepsMessageTest() {
        assertEquals(Workflow.getNextStepsMessage("Flu"), ""); //Should fail on empty nextStepsList

        assertTrue(Workflow.insertNextSteps("Covid", "Need to quarantine")); //Insert medical condition and message into nextStepsList successfully
        assertEquals(Workflow.getNextStepsMessage("Flu"), ""); //Should fail on non-empty nextStepsList
        assertTrue(Workflow.removeNextSteps("Covid")); //Remove medical condition and its message from nextStepsList successfully
    }

    /**
     * Check if removeNextSteps() fails if it is called on a medical condition that is not in nextStepsList.
     */
    @Test
    public void removeNextStepsFailTest() {
        assertFalse(Workflow.removeNextSteps("Flu")); //Should fail on empty nextStepsList

        assertTrue(Workflow.insertNextSteps("Covid", "Need to quarantine")); //Insert medical condition and message into nextStepsList successfully
        assertFalse(Workflow.removeNextSteps("Flu")); //Should fail on non-empty nextStepsList
        assertTrue(Workflow.removeNextSteps("Covid")); //Remove medical condition and its message from nextStepsList successfully
    }
}