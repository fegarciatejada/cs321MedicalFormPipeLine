package com.gmu.cs321.fall2023.fgct;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Approver implements ActionListener {
    private JFrame inbox = new JFrame("User Inbox");
    private JTextArea cTextArea = new JTextArea("Your submission has been received! Here is a copy of your answers: \n\n Name: \n DOB: \n Address: \n Alien Number: \n Medical Condition: \n Phone Number: \n Condition Start Date: \n", 5, 10);
    private JTextArea nTextArea = new JTextArea(10, 50);
    private JPanel inboxScr = new JPanel();
    private JLabel idLabel;
    private JTextField saveCondition;
    JComboBox conditionList;

    private int currentFormId;
    private MedicalForm currentForm;
    private String nextStepsMessage;
    // END: abpxx6d04wxr

    // FILEPATH: /C:/Users/rdev/dev/cs321MedicalFormPipeLine/src/main/java/com/gmu/cs321/fall2023/fgct/Approver.java
    // BEGIN: be15d9bcejpp
    /**
     * Sets up and shows Approver Screen
     */
  
    /**
     * Sets up and shows Approver Screen
     */
    public void showApproverScreen() {
        /**
         * Approver Screen
         */
        JFrame approver = new JFrame("Approver Screen");
        approver.setVisible(true); 
        approver.setSize(1000, 700);
        approver.setLayout(new BoxLayout(approver.getContentPane(), BoxLayout.Y_AXIS));
        approver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Scrolling Approver
        JPanel approverScr = new JPanel();
        BoxLayout saLayout = new BoxLayout(approverScr, BoxLayout.Y_AXIS);
        approverScr.setLayout(saLayout);
        approverScr.setAlignmentX(Component.CENTER_ALIGNMENT);

        /**
         * Information Panel
         */
        JPanel info = new JPanel();
        FlowLayout infoLayout = new FlowLayout();
        info.setLayout(infoLayout);
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.setPreferredSize(new Dimension(800,80));
        info.setMaximumSize(new Dimension(800, 80));

        //Get and show current Medical Form ID
        currentFormId = Workflow.getNextComplete();
        if(currentFormId == -1) {
            idLabel = new JLabel("Current Form ID: No form available.");
        } else {
            currentForm = MedicalForm.getForm(currentFormId); 
            idLabel = new JLabel("Current Form ID: " + currentFormId);
            cTextArea.setText("Your submission has been recieved! Here is a copy of your answers: \n\n Name: " + currentForm.getName() + "\n DOB: " + currentForm.getDOB() + "\n Address: " + currentForm.getAddress() + "\n Alien Number: " + currentForm.getAlienNumber() + "\n Medical Condition: " + currentForm.getCondition() + "\n Phone Number: " + currentForm.getPhoneNumber() + "\n Condition Start Date: " + currentForm.getDateWhenConditionStarted() + "\n");
        }
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.add(idLabel);

        //Reload button
        JButton reload = new JButton("Reload");
        reload.setAlignmentX(Component.CENTER_ALIGNMENT);
        reload.setActionCommand("reload");
        reload.addActionListener(this);
        info.add(reload);

        //Add to scroll panel
        approverScr.add(info);
        
        /**
         * Confirmation Panel
         */

        JPanel confirmation = new JPanel();
        BoxLayout cLayout = new BoxLayout(confirmation, BoxLayout.Y_AXIS);
        confirmation.setLayout(cLayout);
        confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmation.setPreferredSize(new Dimension(800, 450));
        confirmation.setMaximumSize(new Dimension(800, 450));
        
        //Border
        TitledBorder cBorder = new TitledBorder("Confirmation Message"); 
        confirmation.setBorder(cBorder);
        
        //Message Text
        JLabel cLabel = new JLabel("Message:");
        cLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmation.add(cLabel);
        
        //Text Area
        //JTextArea cTextArea = new JTextArea("Your submission has been recieved! Here is a copy of your answers: \n\n Name: \n DOB: \n Address: \n Alien Number: \n Medical Condition: \n Phone Number: \n Condition Start Date: \n", 5, 10);
        confirmation.add(cTextArea);
        
        //Send button
        JButton cSend = new JButton("Send");
        cSend.setAlignmentX(Component.CENTER_ALIGNMENT);
        cSend.setActionCommand("confirmation");
        cSend.addActionListener(this);
        confirmation.add(cSend);
        
        //Add to scroll panel
        approverScr.add(confirmation);

        /**
         * Neccessary Next Steps Panel
         */

        JPanel nextSteps = new JPanel();
        BoxLayout nLayout = new BoxLayout(nextSteps, BoxLayout.Y_AXIS);
        nextSteps.setLayout(nLayout);
        nextSteps.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextSteps.setPreferredSize(new Dimension(800, 450));
        nextSteps.setMaximumSize(new Dimension(800, 450));
        
        //Border
        TitledBorder nBorder = new TitledBorder("Necessary Next Steps Message"); 
        nextSteps.setBorder(nBorder);
        
        //Message Text
        JLabel nLabel = new JLabel("Message:");
        nLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextSteps.add(nLabel);
        
        //Medical Condition Drop Down
        ArrayList<String> medicalConditions = new ArrayList<>();
        medicalConditions.add("");
        conditionList = new JComboBox(medicalConditions.toArray());
        conditionList.setActionCommand("conditionList");
        conditionList.addActionListener(this);
        nextSteps.add(conditionList);

        //Text Area
        nextSteps.add(nTextArea);
        
        //Send button
        JButton nSend = new JButton("Send");
        nSend.setAlignmentX(Component.CENTER_ALIGNMENT);
        nSend.setActionCommand("nextSteps");
        nSend.addActionListener(this);
        nextSteps.add(nSend);
        
        //"Add Next Steps" Sub-Panel
        JPanel addNextSteps = new JPanel();
        FlowLayout anLayout = new FlowLayout();
        addNextSteps.setLayout(anLayout);
        addNextSteps.setAlignmentX(Component.CENTER_ALIGNMENT);
        addNextSteps.setPreferredSize(new Dimension(800, 40));
        addNextSteps.setMaximumSize(new Dimension(800, 40));

        //"Add Next Steps" Text
        JLabel anLabel = new JLabel("New Condition: ");
        anLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addNextSteps.add(anLabel);

        //"Add Next Steps" text field
        saveCondition = new JTextField(20);
        addNextSteps.add(saveCondition);

        //"Add Next Steps" button
        JButton saveMessage = new JButton("Save Message");
        saveMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveMessage.setActionCommand("saveMessage");
        saveMessage.addActionListener(this);
        addNextSteps.add(saveMessage);

        //Add "Add Next Steps" Sub-Panel to Necessary Next Steps Panel
        nextSteps.add(addNextSteps);

        //Add to scroll panel
        approverScr.add(nextSteps);

        //Add scroll panel to frame   
        JScrollPane saScrBar = new JScrollPane(approverScr, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        approver.add(saScrBar);     

        /**
         * User Message Inbox
         */
        inbox.setVisible(true); 
        inbox.setSize(1000, 700);
        inbox.setLayout(new BoxLayout(inbox.getContentPane(), BoxLayout.Y_AXIS));
        inbox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Scrolling Inbox
        BoxLayout iLayout = new BoxLayout(inboxScr, BoxLayout.Y_AXIS);
        inboxScr.setLayout(iLayout);
        inboxScr.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Message Text
        JLabel iLabel = new JLabel("Messages:");
        iLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        inboxScr.add(iLabel);

        //Scroll Bar
        JScrollPane scrBar = new JScrollPane(inboxScr, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        inbox.add(scrBar);

   }

    public void actionPerformed(ActionEvent e) {
            if("nextSteps".equals(e.getActionCommand())) {
                sendNextSteps();
                
            } else if("confirmation".equals(e.getActionCommand())) {
                sendConfirmation();
            } else if ("reload".equals(e.getActionCommand())) {
                currentFormId = Workflow.getNextComplete();
                //currentFormId = 123; //TODO: delete laterrrr
                //currentForm = new MedicalForm("Peter Parker", currentFormId, "Fairfax", "Tuberculosis", 456, 2020); //TODO: delete laterrrr
                if(currentFormId == -1) {
                    idLabel.setText("Current Form ID: No form available.");
                } else {
                    currentForm = MedicalForm.getForm(currentFormId); //TODO: FIX MEDICAL FORM
                    idLabel.setText("Current Form ID: " + currentFormId); 
                    cTextArea.setText("Your submission has been recieved! Here is a copy of your answers: \n\n Name: " + currentForm.getName() + "\n DOB: " + currentForm.getDOB() + "\n Address: " + currentForm.getAddress() + "\n Alien Number: " + currentForm.getAlienNumber() + "\n Medical Condition: " + currentForm.getCondition() + "\n Phone Number: " + currentForm.getPhoneNumber() + "\n Condition Start Date: " + currentForm.getDateWhenConditionStarted() + "\n");
                    if(!(Workflow.getNextStepsMessage(currentForm.getCondition()).equals(""))) {
                        conditionList.setSelectedItem(currentForm.getCondition());
                    } else {
                        conditionList.setSelectedItem("");
                    }
                }
                if(currentForm != null) {
                    currentForm.changeStatus("Approved");
                }
            } else if ("saveMessage".equals(e.getActionCommand())) {
                Workflow.insertNextSteps(saveCondition.getText(), nTextArea.getText());
                conditionList.addItem(saveCondition.getText());

            } else if ("conditionList".equals(e.getActionCommand())) {
                nextStepsMessage = Workflow.getNextStepsMessage(conditionList.getSelectedItem().toString());
                nTextArea.setText(nextStepsMessage);
            }
    }

    public boolean sendConfirmation() {
        JPanel message = new JPanel();
        BoxLayout mLayout = new BoxLayout(message, BoxLayout.Y_AXIS);
        message.setLayout(mLayout);
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        message.setPreferredSize(new Dimension(800, 450));
        message.setMaximumSize(new Dimension(800, 450));

        //Border
        TitledBorder mBorder = new TitledBorder("Confirmation Message"); 
        message.setBorder(mBorder);
        
        //Message Text
        JLabel mLabel = new JLabel("<html>" + cTextArea.getText().replaceAll("(\r\n|\n)", "<br />") + "</html>");
        mLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        message.add(mLabel);

        //Add to scroll panel
        inboxScr.add(message);
        inboxScr.revalidate();
        inboxScr.repaint();

        return true;
    }

    public boolean sendNextSteps() {
        JPanel message = new JPanel();
        BoxLayout nLayout = new BoxLayout(message, BoxLayout.Y_AXIS);
        message.setLayout(nLayout);
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        message.setPreferredSize(new Dimension(800, 450));
        message.setMaximumSize(new Dimension(800, 450));

        //Border
        TitledBorder mBorder = new TitledBorder("Next Steps Message"); 
        message.setBorder(mBorder);
        
        //Message Text
        JLabel mLabel = new JLabel("<html>" + nTextArea.getText().replaceAll("(\r\n|\n)", "<br />") + "</html>");
        mLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        message.add(mLabel);

        //Add to frame
        inboxScr.add(message);
        inboxScr.revalidate();
        inboxScr.repaint();

        return true;
    }

    public boolean deleteData(int formId) {
        return MedicalForm.getForm(formId).deleteForm();
    }

}
