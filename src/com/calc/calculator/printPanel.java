/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calc.calculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author chinex
 */
public class printPanel extends javax.swing.JPanel {

//    String [] memo = new String [100];
//    int count = 0; int lenght = 5;
//    
    
    
    
    /**
     * Creates new form printPanel
     */
    public printPanel() {
        try {
            initComponents();
            newtext.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(printPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    } 

    File newtext = new File ("newtext.txt");
    FileWriter fw = null; 
    
    public void getwork (String solutionprint)
    {
       
        try {
            fw = new FileWriter(newtext.getAbsoluteFile(), true);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(solutionprint); 
            }
        } catch (IOException ex) {
            Logger.getLogger(printPanel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }
    
     public void printwork() throws IOException
    {
        try {
            String text;
            FileReader fr = new FileReader(newtext);
            BufferedReader reader = new BufferedReader(fr);
            while ((text=reader.readLine()) != null) {
               if (!text.startsWith(">"))
                printTextArea.append(text + "\n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(printPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    } 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
     
     
                @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        printTextArea = new javax.swing.JTextArea();
        printPanelExit = new javax.swing.JButton();
        ClearPrintPanelButton = new javax.swing.JButton();
        PrintpanelmemoButton = new javax.swing.JButton();
        DeletememoButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 102, 102));

        printTextArea.setColumns(1);
        printTextArea.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        printTextArea.setRows(5);
        jScrollPane1.setViewportView(printTextArea);

        printPanelExit.setBackground(new java.awt.Color(0, 102, 102));
        printPanelExit.setFont(new java.awt.Font("Bell MT", 1, 18)); // NOI18N
        printPanelExit.setForeground(new java.awt.Color(255, 255, 255));
        printPanelExit.setText("EXIT");
        printPanelExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printPanelExitActionPerformed(evt);
            }
        });

        ClearPrintPanelButton.setBackground(new java.awt.Color(0, 102, 102));
        ClearPrintPanelButton.setFont(new java.awt.Font("Bell MT", 1, 18)); // NOI18N
        ClearPrintPanelButton.setForeground(new java.awt.Color(255, 255, 255));
        ClearPrintPanelButton.setText("CLEAR");
        ClearPrintPanelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearPrintPanelButtonActionPerformed(evt);
            }
        });

        PrintpanelmemoButton.setBackground(new java.awt.Color(0, 102, 102));
        PrintpanelmemoButton.setFont(new java.awt.Font("Bell MT", 1, 18)); // NOI18N
        PrintpanelmemoButton.setForeground(new java.awt.Color(255, 255, 255));
        PrintpanelmemoButton.setText("MEMO");
        PrintpanelmemoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintpanelmemoButtonActionPerformed(evt);
            }
        });

        DeletememoButton.setFont(new java.awt.Font("Bell MT", 1, 18)); // NOI18N
        DeletememoButton.setText("DELETE MEMO");
        DeletememoButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        DeletememoButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        DeletememoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeletememoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PrintpanelmemoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(printPanelExit, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ClearPrintPanelButton)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DeletememoButton)
                .addGap(73, 73, 73))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ClearPrintPanelButton)
                    .addComponent(PrintpanelmemoButton)
                    .addComponent(printPanelExit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DeletememoButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void printPanelExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printPanelExitActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(() -> {
            new calculatorWorkspace().setVisible(true);
        }); 
    }//GEN-LAST:event_printPanelExitActionPerformed

    private void ClearPrintPanelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearPrintPanelButtonActionPerformed
        // TODO add your handling code here:
        printTextArea.setText(" ");
    }//GEN-LAST:event_ClearPrintPanelButtonActionPerformed

    private void PrintpanelmemoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintpanelmemoButtonActionPerformed
        try {
            if (" ".equals(printTextArea.getText()))
            {
               this.printwork();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(printPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_PrintpanelmemoButtonActionPerformed

    private void DeletememoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletememoButtonActionPerformed
        // TODO add your handling code here:
        try {
            fw = new FileWriter(newtext.getAbsoluteFile());
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(" ");
                newtext.delete();
            }
        } catch (IOException ex) {
            Logger.getLogger(printPanel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }//GEN-LAST:event_DeletememoButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClearPrintPanelButton;
    private javax.swing.JButton DeletememoButton;
    private javax.swing.JButton PrintpanelmemoButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton printPanelExit;
    private javax.swing.JTextArea printTextArea;
    // End of variables declaration//GEN-END:variables
}
