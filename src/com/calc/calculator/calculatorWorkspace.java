/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calc.calculator;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chinex
 */
public class calculatorWorkspace extends javax.swing.JFrame {

    /**
     * Creates new form calculatorWorkspace
     */
    private boolean isNum1Used = false;
    private double  operand1 = 0;
    private double  operand2 = 0;
    private String showoperand1;
    private String showoperand2;
    private char    operation = ' ';
    private String  Soperation;
    private double  result = 0;
    private boolean clearsolutionField = false;
    private boolean isDecimalPointUsed = false;
    private boolean isOperationChaining = false;
    private boolean isMultipleEvents = false;
    printPanel n = new printPanel();
    public calculatorWorkspace() {
        initComponents();
       
        Runnable time = new Timehandler();
                Thread th = new Thread(time);
                th.start();
 
        jButton2.addActionListener(this::jButtonberActions);
        jButton1.addActionListener(this::jButtonberActions);     
        jButton3.addActionListener(this::jButtonberActions); 
        jButton4.addActionListener(this::jButtonberActions);
        jButton5.addActionListener(this::jButtonberActions);
        jButton6.addActionListener(this::jButtonberActions);
        jButton7.addActionListener(this::jButtonberActions);
        jButton8.addActionListener(this::jButtonberActions);
        jButton9.addActionListener(this::jButtonberActions);
        jButton0.addActionListener(this::jButtonberActions);                
        jButtonmultiply.addActionListener(this::calculationActions);
        jButtonmod.addActionListener(this::calculationActions);        
        jButtonplusnminus.addActionListener(this::calculationActions);               
        jButtonsquare.addActionListener(this::calculationActions);     
        jButtonsub.addActionListener(this::calculationActions);
        jButtonadd.addActionListener(this::calculationActions);
        jButtondivide.addActionListener(this::calculationActions);
        jButtonequal.addActionListener(this::equalActionPerformed);
        jButtonClear.addActionListener(this::clearAllActionPerformed);        
        backspacebutton.addActionListener(this::backspaceActionPerformed);
        jButtondot.addActionListener(this::decimalPointActionPerformed);
        solutionField.addKeyListener(new JKeyListener(this));
    }
    
  private void calculationActions(java.awt.event.ActionEvent evt) {
      if (!"".equals(solutionField.getText())) {
            clearsolutionField = true;
            if (!isOperationChaining) 
            {          
                isOperationChaining = true; 
                showoperand2 = solutionField.getText().trim();               
                showoperand1 = solutionField.getText();
                operand1 = Double.parseDouble(solutionField.getText());
                operation = evt.getActionCommand().charAt(0);
                solutionField.setHorizontalAlignment(JTextField.LEFT);
                solutionField.setText(showoperand1 + " " + String.valueOf(operation)+ " ");
                
                isNum1Used = true;
            }
            else if (isMultipleEvents) 
            {
                JOptionPane.showMessageDialog(this, "ONLY TWO OPERANDS AT A TIME");
                isOperationChaining = true;
                operation = evt.getActionCommand().charAt(0); 
            }
   } 
      
  }
  class JKeyListener extends KeyAdapter
  {
        private JKeyListener(calculatorWorkspace This) 
        {
        
        }
        @Override
         public void keyPressed(KeyEvent event)
      {
               
      
             if (!"".equals(solutionField.getText())) {
            clearsolutionField = true;
            if (!isOperationChaining) 
            {          
                isOperationChaining = true; 
                showoperand2 = solutionField.getText().trim();               
                operation = event.getKeyChar();
                JOptionPane.showMessageDialog(FRONTpower2, operation);
                solutionField.setHorizontalAlignment(JTextField.LEFT);
                solutionField.setText(showoperand1 + " " + String.valueOf(operation)+ " ");
                
                isNum1Used = true;
            }
            else if (isMultipleEvents) 
            {
                JOptionPane.showMessageDialog(FRONTpower2, "ONLY TWO OPERANDS AT A TIME");
                isOperationChaining = true;
                operation = event.getKeyChar();
            }
            
   } 
        }
  }

    private void jButtonberActions(java.awt.event.ActionEvent evt) {
        if (clearsolutionField) 
        {          
            clearsolutionField = false;
            isDecimalPointUsed = false;
        }
        solutionField.setHorizontalAlignment(JTextField.LEFT);
        solutionField.setText(solutionField.getText() + evt.getActionCommand());
        isMultipleEvents = true;
    }                                    

    private void decimalPointActionPerformed(java.awt.event.ActionEvent evt) {                                             
        if (clearsolutionField) {
            clearsolutionField = false;
            solutionField.setText("");
        }
        if (!isDecimalPointUsed) 
        {
            if ("".equals(solutionField.getText())) 
            {
               solutionField.setHorizontalAlignment(JTextField.LEFT);
               solutionField.setText("0.");
            } 
            else if (!" ".equals(showoperand1)) 
            {
               showoperand1 = solutionField.getText().trim();
//              int trim = showoperand1.length();
//              int sub = (trim - 2);
               solutionField.setHorizontalAlignment(JTextField.LEFT);
               solutionField.setText(showoperand1 + ".");
            }
//            else
//            {
//                showoperand2 = solutionField.getText().trim();
////              int trim = showoperand1.length();
////              int sub = (trim - 2);
//               solutionField.setHorizontalAlignment(JTextField.LEFT);
//               solutionField.setText(showoperand1 + ".");
//            }
            isDecimalPointUsed = true;
        }
    }                                  
    private void clearAllActionPerformed(java.awt.event.ActionEvent evt) {                                         
        operand1 = 0;
        operand2 = 0;
        isNum1Used = false;
        isDecimalPointUsed = false;
        isOperationChaining = false;
        isMultipleEvents = true;        
        solutionField.setText("");
    }     
    private void backspaceActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        //operand1 = 0;
        operand2 = 0;
        isNum1Used = true;
        isDecimalPointUsed = false;
        isOperationChaining = false;
        isMultipleEvents = true;
         if (solutionField.getText().length()> 0)
        {
            StringBuffer sb = new StringBuffer(solutionField.getText());
            sb = sb.deleteCharAt(solutionField.getText().length()-1);
            solutionField.setText(sb.toString());            
            operation = ' ';    
        }
     }
     
     private class Timehandler implements Runnable
 {
        @Override
        public void run() {
            for (;;)
            {
                try {
                    int ch = 10;
                    Calendar cal = new GregorianCalendar();
                    int hr =  cal.get(Calendar.HOUR_OF_DAY);
                    int min =  cal.get(Calendar.MINUTE);
                    int sec =  cal.get(Calendar.SECOND);
                    int day =  cal.get(Calendar.DAY_OF_MONTH);
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);
                    if (ch > hr && ch < min && ch < sec )
                    {
                    String timeString = "0" + hr + ":" + min + ":" + sec ;
                    TimeLabel.setText(timeString);
                    }
                    else if (ch > min && ch < hr & ch < sec )
                    {
                    String timeString = hr + ":" + "0" + min + ":" + sec ;
                    TimeLabel.setText(timeString);
                    }
                    else if (ch > sec && ch < min && ch < hr )
                    {
                    String timeString = hr + ":" + min + ":" + "0" + sec;
                    TimeLabel.setText(timeString);
                    }
                    else if (ch > hr && ch > min && ch < sec )
                    {
                    String timeString = "0" + hr  + ":" + "0" + min + ":" + sec ;
                    TimeLabel.setText(timeString);
                    }
                    else if (ch > hr && ch > sec && ch < min)
                    {
                    String timeString = "0" + hr + ":" + min + ":" + "0" + sec ;
                    TimeLabel.setText(timeString);
                    }
                    else if (ch > min && ch > sec && ch < hr)
                    {
                    String timeString = hr + ":" + "0" + min +":" + "0" + sec;
                    TimeLabel.setText(timeString);
                    } 
                    else if (ch > min && ch > sec && ch > hr)
                    {
                    String timeString = "0" + hr +  ":" + "0" + min +":" + "0" + sec ;
                    TimeLabel.setText(timeString);
                    }   
                    else
                    {
                    String timeString = hr + ":" + min + ":" + sec ;
                    TimeLabel.setText(timeString);
                    }
                    
                    String date =  day + "/" + month + "/" + year ;
                    DateLabel.setText(date);
                    
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(calculatorWorkspace.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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

        figurePanel1 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButtonsquare = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButtonmod = new javax.swing.JButton();
        jButtonadd = new javax.swing.JButton();
        jButtonsub = new javax.swing.JButton();
        jButtonmultiply = new javax.swing.JButton();
        jButtondivide = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButtonplusnminus = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton0 = new javax.swing.JButton();
        jButtonequal = new javax.swing.JButton();
        jButtondot = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        backspacebutton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        ScientificFront = new javax.swing.JPanel();
        FRONTroot2 = new javax.swing.JLabel();
        FRONTpermut = new javax.swing.JLabel();
        FRONTpower2 = new javax.swing.JLabel();
        FRONTopenbracket = new javax.swing.JLabel();
        FRONTpower10 = new javax.swing.JLabel();
        FRONTtan = new javax.swing.JLabel();
        FRONTlog = new javax.swing.JLabel();
        FRONpie = new javax.swing.JLabel();
        FRONTcos = new javax.swing.JLabel();
        FRONTsin = new javax.swing.JLabel();
        FRONTclosebracket = new javax.swing.JLabel();
        FRONTswitch = new javax.swing.JLabel();
        ScientificBack = new javax.swing.JPanel();
        Backswitch = new javax.swing.JLabel();
        BACKcosh = new javax.swing.JLabel();
        BACKtanh = new javax.swing.JLabel();
        BACKsinh = new javax.swing.JLabel();
        BACKabsolute = new javax.swing.JLabel();
        BACKdividedby1 = new javax.swing.JLabel();
        BACKroot3 = new javax.swing.JLabel();
        BACKexppX = new javax.swing.JLabel();
        BACKdeg = new javax.swing.JLabel();
        BACKradian = new javax.swing.JLabel();
        BACKpower3 = new javax.swing.JLabel();
        BACKexp = new javax.swing.JLabel();
        TimeLabel = new javax.swing.JLabel();
        DateLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        memoryButton = new javax.swing.JButton();
        screenPanel = new javax.swing.JPanel();
        solutionField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BEUHIER COMPUTING");
        setBackground(new java.awt.Color(0, 102, 102));
        setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N

        figurePanel1.setBackground(new java.awt.Color(0, 102, 102));

        jButton6.setBackground(new java.awt.Color(0, 0, 0));
        jButton6.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("6");

        jButton9.setBackground(new java.awt.Color(102, 102, 102));
        jButton9.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("9");

        jButtonsquare.setBackground(new java.awt.Color(255, 255, 255));
        jButtonsquare.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButtonsquare.setForeground(new java.awt.Color(51, 51, 51));
        jButtonsquare.setText("^");
        jButtonsquare.setName("^"); // NOI18N

        jButton7.setBackground(new java.awt.Color(102, 102, 102));
        jButton7.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("7");

        jButton8.setBackground(new java.awt.Color(102, 102, 102));
        jButton8.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("8");

        jButtonClear.setBackground(new java.awt.Color(153, 153, 0));
        jButtonClear.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButtonClear.setForeground(new java.awt.Color(255, 255, 255));
        jButtonClear.setText("C");

        jButton4.setBackground(new java.awt.Color(102, 102, 102));
        jButton4.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("4");

        jButtonmod.setBackground(new java.awt.Color(102, 102, 102));
        jButtonmod.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButtonmod.setForeground(new java.awt.Color(255, 255, 255));
        jButtonmod.setText("%");

        jButtonadd.setBackground(new java.awt.Color(102, 102, 102));
        jButtonadd.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButtonadd.setForeground(new java.awt.Color(255, 255, 255));
        jButtonadd.setText("+");

        jButtonsub.setBackground(new java.awt.Color(102, 102, 102));
        jButtonsub.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButtonsub.setForeground(new java.awt.Color(255, 255, 255));
        jButtonsub.setText("-");

        jButtonmultiply.setBackground(new java.awt.Color(102, 102, 102));
        jButtonmultiply.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButtonmultiply.setForeground(new java.awt.Color(255, 255, 255));
        jButtonmultiply.setText("*");

        jButtondivide.setBackground(new java.awt.Color(102, 102, 102));
        jButtondivide.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButtondivide.setForeground(new java.awt.Color(255, 255, 255));
        jButtondivide.setText("÷");
        jButtondivide.setToolTipText("");

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("3");

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("2");

        jButtonplusnminus.setBackground(new java.awt.Color(153, 153, 0));
        jButtonplusnminus.setFont(new java.awt.Font("Bell MT", 1, 42)); // NOI18N
        jButtonplusnminus.setForeground(new java.awt.Color(255, 255, 255));
        jButtonplusnminus.setText("±");
        jButtonplusnminus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonplusnminusActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 0, 0));
        jButton5.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("5");

        jButton0.setBackground(new java.awt.Color(102, 102, 0));
        jButton0.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButton0.setForeground(new java.awt.Color(0, 102, 102));
        jButton0.setText("0");

        jButtonequal.setBackground(new java.awt.Color(0, 204, 102));
        jButtonequal.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButtonequal.setForeground(new java.awt.Color(255, 255, 255));
        jButtonequal.setText("=");

        jButtondot.setBackground(new java.awt.Color(102, 102, 102));
        jButtondot.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButtondot.setForeground(new java.awt.Color(255, 255, 255));
        jButtondot.setText(".");

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("1");

        backspacebutton.setBackground(new java.awt.Color(153, 153, 0));
        backspacebutton.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        backspacebutton.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        ScientificFront.setBackground(new java.awt.Color(0, 102, 102));

        FRONTroot2.setBackground(new java.awt.Color(0, 102, 102));
        FRONTroot2.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        FRONTroot2.setForeground(new java.awt.Color(207, 247, 240));
        FRONTroot2.setText(" 2√X");
        FRONTroot2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationWithXMouseClicked(evt);
            }
        });

        FRONTpermut.setBackground(new java.awt.Color(0, 102, 102));
        FRONTpermut.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        FRONTpermut.setForeground(new java.awt.Color(207, 247, 240));
        FRONTpermut.setText("  n!");
        FRONTpermut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationMouseClicked(evt);
            }
        });

        FRONTpower2.setBackground(new java.awt.Color(0, 102, 102));
        FRONTpower2.setFont(new java.awt.Font("Bell MT", 1, 30)); // NOI18N
        FRONTpower2.setForeground(new java.awt.Color(207, 247, 240));
        FRONTpower2.setText("x^2");
        FRONTpower2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationWithXbefore(evt);
            }
        });

        FRONTopenbracket.setBackground(new java.awt.Color(0, 102, 102));
        FRONTopenbracket.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        FRONTopenbracket.setForeground(new java.awt.Color(207, 247, 240));
        FRONTopenbracket.setText("    (");
        FRONTopenbracket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FRONTopenbracketMouseClicked(evt);
            }
        });

        FRONTpower10.setBackground(new java.awt.Color(0, 102, 102));
        FRONTpower10.setFont(new java.awt.Font("Bell MT", 1, 28)); // NOI18N
        FRONTpower10.setForeground(new java.awt.Color(207, 247, 240));
        FRONTpower10.setText(" 10^");
        FRONTpower10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationMouseClicked(evt);
            }
        });

        FRONTtan.setBackground(new java.awt.Color(0, 102, 102));
        FRONTtan.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        FRONTtan.setForeground(new java.awt.Color(207, 247, 240));
        FRONTtan.setText(" TAN");
        FRONTtan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationMouseClicked(evt);
            }
        });

        FRONTlog.setBackground(new java.awt.Color(0, 102, 102));
        FRONTlog.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        FRONTlog.setForeground(new java.awt.Color(207, 247, 240));
        FRONTlog.setText(" LOG");
        FRONTlog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationMouseClicked(evt);
            }
        });

        FRONpie.setBackground(new java.awt.Color(0, 102, 102));
        FRONpie.setFont(new java.awt.Font("Bell MT", 1, 40)); // NOI18N
        FRONpie.setForeground(new java.awt.Color(207, 247, 240));
        FRONpie.setText("  π");
        FRONpie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationMouseClicked(evt);
            }
        });

        FRONTcos.setBackground(new java.awt.Color(0, 102, 102));
        FRONTcos.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        FRONTcos.setForeground(new java.awt.Color(207, 247, 240));
        FRONTcos.setText("  COS");
        FRONTcos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationMouseClicked(evt);
            }
        });

        FRONTsin.setBackground(new java.awt.Color(0, 102, 102));
        FRONTsin.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        FRONTsin.setForeground(new java.awt.Color(207, 247, 240));
        FRONTsin.setText("  SIN");
        FRONTsin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationMouseClicked(evt);
            }
        });

        FRONTclosebracket.setBackground(new java.awt.Color(0, 102, 102));
        FRONTclosebracket.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        FRONTclosebracket.setForeground(new java.awt.Color(207, 247, 240));
        FRONTclosebracket.setText("       )");
        FRONTclosebracket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FRONTclosebracketMouseClicked(evt);
            }
        });

        FRONTswitch.setBackground(new java.awt.Color(0, 102, 102));
        FRONTswitch.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        FRONTswitch.setForeground(new java.awt.Color(207, 247, 240));
        FRONTswitch.setText("  UP");
        FRONTswitch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FRONTswitchMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout ScientificFrontLayout = new javax.swing.GroupLayout(ScientificFront);
        ScientificFront.setLayout(ScientificFrontLayout);
        ScientificFrontLayout.setHorizontalGroup(
            ScientificFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ScientificFrontLayout.createSequentialGroup()
                .addGroup(ScientificFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ScientificFrontLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(FRONTpermut, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(FRONTlog, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FRONTopenbracket, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FRONTclosebracket, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ScientificFrontLayout.createSequentialGroup()
                        .addContainerGap(18, Short.MAX_VALUE)
                        .addGroup(ScientificFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ScientificFrontLayout.createSequentialGroup()
                                .addComponent(FRONTswitch, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FRONTcos, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(FRONTtan, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FRONTsin, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ScientificFrontLayout.createSequentialGroup()
                                .addComponent(FRONTroot2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(FRONTpower10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(FRONTpower2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(FRONpie, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        ScientificFrontLayout.setVerticalGroup(
            ScientificFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ScientificFrontLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ScientificFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FRONTsin, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FRONTtan, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FRONTcos, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FRONTswitch, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ScientificFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FRONpie, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FRONTpower2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FRONTpower10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FRONTroot2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ScientificFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FRONTpermut, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FRONTlog, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FRONTopenbracket, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FRONTclosebracket, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        ScientificBack.setBackground(new java.awt.Color(0, 102, 102));

        Backswitch.setBackground(new java.awt.Color(0, 102, 102));
        Backswitch.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        Backswitch.setForeground(new java.awt.Color(207, 247, 240));
        Backswitch.setText(" UP");
        Backswitch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackswitchMouseClicked(evt);
            }
        });

        BACKcosh.setBackground(new java.awt.Color(0, 102, 102));
        BACKcosh.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        BACKcosh.setForeground(new java.awt.Color(207, 247, 240));
        BACKcosh.setText(" COSh");
        BACKcosh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationMouseClicked(evt);
            }
        });

        BACKtanh.setBackground(new java.awt.Color(0, 102, 102));
        BACKtanh.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        BACKtanh.setForeground(new java.awt.Color(207, 247, 240));
        BACKtanh.setText("TANh");
        BACKtanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationMouseClicked(evt);
            }
        });

        BACKsinh.setBackground(new java.awt.Color(0, 102, 102));
        BACKsinh.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        BACKsinh.setForeground(new java.awt.Color(207, 247, 240));
        BACKsinh.setText(" SINh");
        BACKsinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationMouseClicked(evt);
            }
        });

        BACKabsolute.setBackground(new java.awt.Color(0, 102, 102));
        BACKabsolute.setFont(new java.awt.Font("Bell MT", 1, 30)); // NOI18N
        BACKabsolute.setForeground(new java.awt.Color(207, 247, 240));
        BACKabsolute.setText(" abs");
        BACKabsolute.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationMouseClicked(evt);
            }
        });

        BACKdividedby1.setBackground(new java.awt.Color(0, 102, 102));
        BACKdividedby1.setFont(new java.awt.Font("Bell MT", 1, 30)); // NOI18N
        BACKdividedby1.setForeground(new java.awt.Color(207, 247, 240));
        BACKdividedby1.setText(" 1/x");
        BACKdividedby1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationWithXMouseClicked(evt);
            }
        });

        BACKroot3.setBackground(new java.awt.Color(0, 102, 102));
        BACKroot3.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        BACKroot3.setForeground(new java.awt.Color(207, 247, 240));
        BACKroot3.setText(" 3√X");
        BACKroot3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationWithXMouseClicked(evt);
            }
        });

        BACKexppX.setBackground(new java.awt.Color(0, 102, 102));
        BACKexppX.setFont(new java.awt.Font("Bell MT", 1, 30)); // NOI18N
        BACKexppX.setForeground(new java.awt.Color(207, 247, 240));
        BACKexppX.setText(" e^x");
        BACKexppX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationWithXMouseClicked(evt);
            }
        });

        BACKdeg.setBackground(new java.awt.Color(0, 102, 102));
        BACKdeg.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        BACKdeg.setForeground(new java.awt.Color(207, 247, 240));
        BACKdeg.setText("  DEG");
        BACKdeg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationMouseClicked(evt);
            }
        });

        BACKradian.setBackground(new java.awt.Color(0, 102, 102));
        BACKradian.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        BACKradian.setForeground(new java.awt.Color(207, 247, 240));
        BACKradian.setText(" RAD");
        BACKradian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationMouseClicked(evt);
            }
        });

        BACKpower3.setBackground(new java.awt.Color(0, 102, 102));
        BACKpower3.setFont(new java.awt.Font("Bell MT", 1, 30)); // NOI18N
        BACKpower3.setForeground(new java.awt.Color(207, 247, 240));
        BACKpower3.setText("x^3");
        BACKpower3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationWithXbefore(evt);
            }
        });

        BACKexp.setBackground(new java.awt.Color(0, 102, 102));
        BACKexp.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        BACKexp.setForeground(new java.awt.Color(207, 247, 240));
        BACKexp.setText(" EXP");
        BACKexp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalculationMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout ScientificBackLayout = new javax.swing.GroupLayout(ScientificBack);
        ScientificBack.setLayout(ScientificBackLayout);
        ScientificBackLayout.setHorizontalGroup(
            ScientificBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ScientificBackLayout.createSequentialGroup()
                .addGroup(ScientificBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ScientificBackLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BACKexp, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BACKdeg, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BACKexppX, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BACKdividedby1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ScientificBackLayout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
                        .addGroup(ScientificBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ScientificBackLayout.createSequentialGroup()
                                .addComponent(Backswitch, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BACKcosh, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(BACKtanh, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BACKsinh, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ScientificBackLayout.createSequentialGroup()
                                .addComponent(BACKpower3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BACKradian, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(BACKroot3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BACKabsolute, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        ScientificBackLayout.setVerticalGroup(
            ScientificBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ScientificBackLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ScientificBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BACKsinh, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BACKtanh, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BACKcosh, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Backswitch, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ScientificBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BACKabsolute, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BACKroot3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BACKradian, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BACKpower3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ScientificBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BACKexp, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BACKdeg, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BACKexppX, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BACKdividedby1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        TimeLabel.setBackground(new java.awt.Color(0, 102, 102));
        TimeLabel.setFont(new java.awt.Font("Bell MT", 1, 48)); // NOI18N
        TimeLabel.setForeground(new java.awt.Color(207, 247, 240));

        DateLabel.setBackground(new java.awt.Color(0, 102, 102));
        DateLabel.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        DateLabel.setForeground(new java.awt.Color(207, 247, 240));

        memoryButton.setBackground(new java.awt.Color(0, 0, 0));
        memoryButton.setFont(new java.awt.Font("Bell MT", 1, 30)); // NOI18N
        memoryButton.setForeground(new java.awt.Color(255, 255, 255));
        memoryButton.setText("M");
        memoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memoryButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout figurePanel1Layout = new javax.swing.GroupLayout(figurePanel1);
        figurePanel1.setLayout(figurePanel1Layout);
        figurePanel1Layout.setHorizontalGroup(
            figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(figurePanel1Layout.createSequentialGroup()
                .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(figurePanel1Layout.createSequentialGroup()
                        .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, figurePanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
                            .addGroup(figurePanel1Layout.createSequentialGroup()
                                .addComponent(ScientificFront, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 2, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, figurePanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(DateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, figurePanel1Layout.createSequentialGroup()
                        .addComponent(jButtonClear, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonplusnminus, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, figurePanel1Layout.createSequentialGroup()
                        .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(backspacebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, figurePanel1Layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, figurePanel1Layout.createSequentialGroup()
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonmod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, figurePanel1Layout.createSequentialGroup()
                        .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, figurePanel1Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, figurePanel1Layout.createSequentialGroup()
                                .addComponent(memoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtondot, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(figurePanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButtonequal, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtondivide, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButtonmultiply, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jButtonsub, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonadd, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, figurePanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonsquare, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(figurePanel1Layout.createSequentialGroup()
                    .addComponent(ScientificBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 297, Short.MAX_VALUE)))
        );
        figurePanel1Layout.setVerticalGroup(
            figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, figurePanel1Layout.createSequentialGroup()
                .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(figurePanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(figurePanel1Layout.createSequentialGroup()
                                .addComponent(DateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ScientificFront, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, figurePanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonsquare, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonplusnminus, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtondivide, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonmod, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(backspacebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonmultiply, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonsub, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonadd, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonequal, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtondot, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton0, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(memoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
            .addGroup(figurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, figurePanel1Layout.createSequentialGroup()
                    .addContainerGap(211, Short.MAX_VALUE)
                    .addComponent(ScientificBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        screenPanel.setBackground(new java.awt.Color(102, 102, 102));

        solutionField.setBackground(new java.awt.Color(0, 102, 102));
        solutionField.setFont(new java.awt.Font("Bell MT", 1, 48)); // NOI18N
        solutionField.setForeground(new java.awt.Color(51, 51, 51));
        solutionField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout screenPanelLayout = new javax.swing.GroupLayout(screenPanel);
        screenPanel.setLayout(screenPanelLayout);
        screenPanelLayout.setHorizontalGroup(
            screenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(screenPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(solutionField)
                .addContainerGap())
        );
        screenPanelLayout.setVerticalGroup(
            screenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(screenPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(solutionField, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(screenPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(figurePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(screenPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(figurePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void updatePanel (JPanel currentPanel)
    {
        figurePanel1.removeAll();
        GroupLayout up = (GroupLayout) figurePanel1.getLayout();   
        up.setHorizontalGroup(
            up.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(currentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                );
        
        up.setVerticalGroup(
           up.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(currentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               );
    }
    private void FRONTswitchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FRONTswitchMouseClicked
        // TODO add your handling code here:
        ScientificBack.setVisible(true);
        ScientificFront.setVisible(false);
    }//GEN-LAST:event_FRONTswitchMouseClicked

    private void BackswitchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackswitchMouseClicked
        // TODO add your handling code here:
        ScientificBack.setVisible(false);
        ScientificFront.setVisible(true);
    }//GEN-LAST:event_BackswitchMouseClicked

    private void CalculationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CalculationMouseClicked
         // TODO add your handling code here:
             Object obj = evt.getSource();
             JLabel jb = (JLabel) obj;
        if (!"".equals(solutionField.getText())) {
            clearsolutionField = true;
            if (!isOperationChaining) {               
                isOperationChaining = true;                
                showoperand1 = (solutionField.getText());
                operand1 = Double.parseDouble(solutionField.getText());
                Soperation = jb.getText();
                solutionField.setHorizontalAlignment(JTextField.LEFT);
                solutionField.setText(String.valueOf(Soperation) + "(" + showoperand1 +  ")");
                showoperand2 = solutionField.getText().trim();
                n.getwork(showoperand2 + "=" ); 
                isNum1Used = true;}                           
   }      
    }//GEN-LAST:event_CalculationMouseClicked

    private void jButtonplusnminusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonplusnminusActionPerformed
        // TODO add your handling code here:
        solutionField.setHorizontalAlignment(JTextField.LEFT);
        solutionField.setText("-");       
    }//GEN-LAST:event_jButtonplusnminusActionPerformed

    private void FRONTopenbracketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FRONTopenbracketMouseClicked
        // TODO add your handling code here:
        solutionField.setHorizontalAlignment(JTextField.LEFT);
        solutionField.setText("(");  
    }//GEN-LAST:event_FRONTopenbracketMouseClicked

    private void FRONTclosebracketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FRONTclosebracketMouseClicked
        // TODO add your handling code here:
        solutionField.setHorizontalAlignment(JTextField.LEFT);
        solutionField.setText(")"); 
    }//GEN-LAST:event_FRONTclosebracketMouseClicked

    private void CalculationWithXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CalculationWithXMouseClicked
        // TODO add your handling code here:
        Object obj = evt.getSource();
             JLabel jb = (JLabel) obj;
        if (!"".equals(solutionField.getText())) {
            clearsolutionField = true;
            if (!isOperationChaining) {               
                isOperationChaining = true;
                showoperand1 = (solutionField.getText());
                operand1 = Double.parseDouble(solutionField.getText());
                Soperation = jb.getText();
                solutionField.setHorizontalAlignment(JTextField.LEFT);
                solutionField.setText(String.valueOf(Soperation.substring(1, 3)) + "(" + showoperand1 +  ")");
                showoperand2 = solutionField.getText().trim();
                n.getwork(showoperand2 + "=" ); 
                isNum1Used = true;}                           
   }      
    }//GEN-LAST:event_CalculationWithXMouseClicked

    private void CalculationWithXbefore(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CalculationWithXbefore
        // TODO add your handling code here:
         Object obj = evt.getSource();
             JLabel jb = (JLabel) obj;
        if (!"".equals(solutionField.getText())) {
            clearsolutionField = true;
            if (!isOperationChaining) {               
                isOperationChaining = true;
                showoperand1 = (solutionField.getText());
                operand1 = Double.parseDouble(solutionField.getText());
                Soperation = jb.getText();
                solutionField.setHorizontalAlignment(JTextField.LEFT);
                solutionField.setText( "(" + showoperand1 +  ")" + String.valueOf(Soperation.substring(1, 3)) );
                showoperand2 = solutionField.getText().trim();
                n.getwork(showoperand2 + "=" ); 
                isNum1Used = true;}                           
   }            
    }//GEN-LAST:event_CalculationWithXbefore

    private void memoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memoryButtonActionPerformed
        try {
            // TODO add your handling code here:
            n.printwork();
            updatePanel(n);
        } catch (IOException ex) {
            Logger.getLogger(calculatorWorkspace.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_memoryButtonActionPerformed

    private void equalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equalActionPerformed
        // TODO add your handling code here
         if (isNum1Used) 
     {
            if (operation != ' ')
        {
            if (isMultipleEvents) 
            {
              showoperand2 = solutionField.getText().trim();
              n.getwork(showoperand2 + "=");
              int trim = showoperand2.length();
              int trim1 = showoperand1.length();
              int sub = (trim1 + 3);
              //String Operand2 = (solutionField.getText().substring(sub, trim));
              operand2 = Double.parseDouble(solutionField.getText().substring(sub, trim));
        }
            switch (operation) 
            {
              
                case '÷':
                    result = operand1 / operand2;
                    break;
                case '+':
                    result = operand1 + operand2;
                    break;
                case '*':
                    result = operand1 * operand2;
                    break;
                case '-':
                    result = operand1 - operand2;
                    break;
                case '%':
                    result = Math.IEEEremainder(operand1 , operand2);
                    break;
                case '^':
                    result = Math.pow(operand1 , operand2);
                    break;
                default:
                    break;
            }
            operand1 = result;
            solutionField.setHorizontalAlignment(JTextField.RIGHT);
            solutionField.setText(Double.toString(operand1));
            showoperand2 = solutionField.getText().trim();
            int len = showoperand2.length();
            char z = showoperand2.charAt(len-1);
            if (z == '0')
            {
            n.getwork(showoperand2.substring(0, len - 2) + " \n ");
            }
            operation = ' ';
            isOperationChaining = false;
            isMultipleEvents = true;
        }
        else
            {
         
          switch   (Soperation) 
                 {
                case "  COS":   
                            result = Math.cos(operand1);
                            break;             
                case " TAN" :
                            result = Math.tan(operand1);
                            break;
                case "  SIN":
                            result = Math.sin(operand1);
                            break;
                case " LOG":
                            result = Math.log(operand1);
                            break;
                case "  π":
                            result = (Math.PI) * operand1;
                            break;
                case " EXP":
                            result = Math.exp(operand1);

                            break;
                case "x^2":                   
                            result = Math.pow(operand1,2);
                            break;
                case "  n!":
                            for (double c = 0 ; operand1 > c ; operand1--)
                            {
                                result = result + operand1;
                            }
                            break;
                case " 10^":
                            result = Math.pow(10,operand1);
                            break;
                case "x^3":
                            result = Math.pow(operand1, 3);
                            break;
                case " abs":
                            result = Math.abs(operand1);
                            break;
                case "TANh" :
                            result = Math.tanh(operand1);
                            break;
                case " COSh" :
                            result = Math.cosh(operand1);
                            break;
                case " SINh" :
                            result = Math.sinh(operand1);
                            break;
                case "  DEG":
                            result = Math.toDegrees(operand1);
                            break;
                case " RAD":
                            result = Math.toRadians(operand1);
                            break;
                case " 1/x":                   
                            result = (1/operand1);
                            break;
                case " e^x":
                           result = Math.expm1(operand1);
                            break;
                case " 3√X":
                           result = Math.cbrt(operand1);
                            break;
                case " 2√X":
                           result = Math.sqrt(operand1);
                            break;
                default:
                            break;
                    }
                    operand1 = result;
                    solutionField.setHorizontalAlignment(JTextField.RIGHT);
                    solutionField.setText(Double.toString(operand1));
                    showoperand2 = solutionField.getText().trim();
                    n.getwork(showoperand2 + " \n");
                    isOperationChaining = false;
                    isMultipleEvents = true;
    }
    }
    }//GEN-LAST:event_equalActionPerformed
     
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(calculatorWorkspace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(calculatorWorkspace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(calculatorWorkspace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(calculatorWorkspace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new calculatorWorkspace().setVisible(true);
        });    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BACKabsolute;
    private javax.swing.JLabel BACKcosh;
    private javax.swing.JLabel BACKdeg;
    private javax.swing.JLabel BACKdividedby1;
    private javax.swing.JLabel BACKexp;
    private javax.swing.JLabel BACKexppX;
    private javax.swing.JLabel BACKpower3;
    private javax.swing.JLabel BACKradian;
    private javax.swing.JLabel BACKroot3;
    private javax.swing.JLabel BACKsinh;
    private javax.swing.JLabel BACKtanh;
    private javax.swing.JLabel Backswitch;
    private javax.swing.JLabel DateLabel;
    private javax.swing.JLabel FRONTclosebracket;
    private javax.swing.JLabel FRONTcos;
    private javax.swing.JLabel FRONTlog;
    private javax.swing.JLabel FRONTopenbracket;
    private javax.swing.JLabel FRONTpermut;
    private javax.swing.JLabel FRONTpower10;
    private javax.swing.JLabel FRONTpower2;
    private javax.swing.JLabel FRONTroot2;
    private javax.swing.JLabel FRONTsin;
    private javax.swing.JLabel FRONTswitch;
    private javax.swing.JLabel FRONTtan;
    private javax.swing.JLabel FRONpie;
    private javax.swing.JPanel ScientificBack;
    private javax.swing.JPanel ScientificFront;
    private javax.swing.JLabel TimeLabel;
    private javax.swing.JButton backspacebutton;
    private javax.swing.JPanel figurePanel1;
    private javax.swing.JButton jButton0;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonadd;
    private javax.swing.JButton jButtondivide;
    private javax.swing.JButton jButtondot;
    private javax.swing.JButton jButtonequal;
    private javax.swing.JButton jButtonmod;
    private javax.swing.JButton jButtonmultiply;
    private javax.swing.JButton jButtonplusnminus;
    private javax.swing.JButton jButtonsquare;
    private javax.swing.JButton jButtonsub;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton memoryButton;
    private javax.swing.JPanel screenPanel;
    private javax.swing.JTextField solutionField;
    // End of variables declaration//GEN-END:variables
}
