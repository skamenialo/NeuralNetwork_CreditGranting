package com.skamenialo.creditgranting.gui;

import com.skamenialo.creditgranting.Client;
import com.skamenialo.creditgranting.NeuralNetwork;
import com.skamenialo.creditgranting.RandomNumber;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.ResourceBundle;

public class MainWindow extends javax.swing.JFrame {

    public static ResourceBundle bundle = ResourceBundle.getBundle("com/skamenialo/creditgranting/gui/Text");
    public static NeuralNetwork sNeuralNetwork;
    public static List<Client> sLearningSet;
    public static List<Client> sVerifyingSet;

    /**
     * Creates new form TheWindow
     */
    public MainWindow() {
        super(bundle.getString("UDZIELANIE KREDYTÓW"));
        initComponents();
        sNeuralNetwork = new NeuralNetwork(this, sLearningSet);
    }

    public void reset() {
        sNeuralNetwork.reset();
        setFail(0);
        setAge(0);
        setEndFail(0);
        jTextField1.setText("");
        jButton3.setText(bundle.getString("ROZPOCZNIJ NAUKĘ "));
        jButton4.setText(bundle.getString("RESETUJ"));
        jButton5.setEnabled(false);
    }

    public void stop() {
        sNeuralNetwork.stop();
        jButton3.setText(bundle.getString("KONTYNUUJ NAUKĘ "));
        jButton4.setText(bundle.getString("RESETUJ"));
        jButton5.setEnabled(true);
    }

    public void setFail(int i) {
        jLabel5.setText(String.valueOf(i));
    }

    public void setAge(int i) {
        jLabel3.setText(String.valueOf(i));
    }

    public void setEndFail(int i) {
        jLabel7.setText(String.valueOf(i));
    }

    private static List<Client> getStaticLearningSet() {
        List<Client> learningElements = new ArrayList<>();
        learningElements.add(new Client(false, 18, 0, 0, 0, false, 0, 1000));
        learningElements.add(new Client(false, 40, 2, 1000, 12, true, 2000, 3000));
        learningElements.add(new Client(false, 46, 3, 2059.0, 11, true, 1223.0, 5218.0));
        learningElements.add(new Client(true, 55, 3, 4469.0, 36, false, 0.0, 3816.0));
        learningElements.add(new Client(false, 33, 2, 2515.0, 15, true, 2700.0, 3009.0));
        learningElements.add(new Client(false, 74, 0, 4005.0, 192, true, 4107.0, 3681.0));
        learningElements.add(new Client(false, 36, 3, 1743.0, 22, true, 4970.0, 8664.0));
        learningElements.add(new Client(true, 21, 2, 2141.0, 22, true, 278.0, 4092.0));
        learningElements.add(new Client(true, 69, 1, 3404.0, 252, false, 0.0, 3370.0));
        learningElements.add(new Client(false, 77, 1, 2122.0, 156, true, 3870.0, 4905.0));
        learningElements.add(new Client(false, 32, 2, 1674.0, 5, false, 0.0, 4315.0));
        learningElements.add(new Client(false, 33, 3, 3223.0, 6, true, 2033.0, 7736.0));
        learningElements.add(new Client(false, 52, 3, 2644.0, 15, false, 0.0, 8325.0));
        learningElements.add(new Client(true, 71, 0, 5158.0, 228, true, 3510.0, 4096.0));
        learningElements.add(new Client(false, 32, 2, 2401.0, 17, true, 3943.0, 5686.0));
        learningElements.add(new Client(true, 53, 3, 3297.0, 15, true, 630.0, 4240.0));
        learningElements.add(new Client(false, 60, 2, 2420.0, 33, true, 3086.0, 6145.0));
        learningElements.add(new Client(false, 79, 0, 2146.0, 132, true, 3463.0, 2870.0));
        learningElements.add(new Client(false, 68, 0, 1360.0, 264, false, 0.0, 5313.0));
        learningElements.add(new Client(true, 29, 0, 2076.0, 27, true, 2157.0, 3444.0));
        learningElements.add(new Client(true, 62, 0, 2995.0, 3, false, 0.0, 6929.0));
        learningElements.add(new Client(true, 52, 2, 4958.0, 22, true, 4541.0, 5350.0));
        learningElements.add(new Client(false, 26, 3, 1065.0, 17, false, 0.0, 4648.0));
        learningElements.add(new Client(false, 20, 3, 3653.0, 35, true, 4192.0, 7437.0));
        learningElements.add(new Client(false, 80, 2, 2336.0, 120, false, 0.0, 5641.0));
        learningElements.add(new Client(true, 24, 1, 1249.0, 19, false, 0.0, 1588.0));
        return learningElements;
    }

    private static List<Client> getStaticVerifyingSet() {
        List<Client> veryfyingElements = new ArrayList<>();
        veryfyingElements.add(new Client(27, 1, 2500, 36, false, 0, 5000));//przynano
        veryfyingElements.add(new Client(68, 0, 1000, (90 - 68) * 12, false, 0, 2000));//przyznano
        veryfyingElements.add(new Client(18, 0, 0, 0, false, 0, 1000));//nie przyznano
        veryfyingElements.add(new Client(40, 2, 1000, 12, true, 2000, 3000));//nie przyznano
        veryfyingElements.add(new Client(34, 3, 1500, 4, true, 1000, 4000));//nie przyznano
        veryfyingElements.add(new Client(23, 0, 1200, 16, false, 0, 5000));//nie wiem
        veryfyingElements.add(new Client(21, 2, 2141.0, 22, true, 278.0, 4092.0));//przyznano
        return veryfyingElements;
    }

    private static List<Client> getRandomSet(int min, int max) {
        List<Client> set = new ArrayList<>();
        int ilosc = Math.abs(RandomNumber.nextInt(min, max));
        for (int i = 0; i < ilosc; i++) {
            try {
                set.add(Client.getRandomClient());
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return set;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/skamenialo/creditgranting/gui/Text"); // NOI18N
        jButton1.setText(bundle.getString("POKAŻ ZBIÓR UCZĄCY")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(bundle.getString("POKAŻ ZBIÓR WERYFIKUJĄCY")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText(bundle.getString("DOPUSZCZALNA LICZBA BŁĘDÓW:")); // NOI18N

        jButton3.setText(bundle.getString("ROZPOCZNIJ NAUKĘ")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setText(bundle.getString("LICZBA EPOK:")); // NOI18N

        jLabel3.setText(MainWindow.bundle.getString("ZERO")); // NOI18N

        jLabel4.setText(bundle.getString("LICZBA BŁĘDÓW EPOKI:")); // NOI18N

        jLabel5.setText(MainWindow.bundle.getString("ZERO")); // NOI18N

        jLabel6.setText(bundle.getString("LICZBA BŁĘDÓW PO ZAKOŃCZENIU NAUKI:")); // NOI18N

        jLabel7.setText(MainWindow.bundle.getString("ZERO")); // NOI18N

        jButton4.setText(bundle.getString("RESETUJ")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText(bundle.getString("WERYFIKUJ")); // NOI18N
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextField1.setText(MainWindow.bundle.getString("ZERO")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jButton4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel3)
                            .addGap(57, 57, 57)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel5))))
                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new ClientSetDialog(this, true, true).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new ClientSetDialog(this, true, false).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jLabel1.setForeground(Color.black);
        String regex = "[\\d]+";
        Pattern pattern = Pattern.compile(regex);
        if (jTextField1.getText().matches(regex)) {
            sNeuralNetwork.setFail(Integer.parseInt(jTextField1.getText()));
        } else {
            jLabel1.setForeground(Color.red);
            return;
        }
        if (!sNeuralNetwork.isLearning()) {
            jButton3.setText(bundle.getString("AKTUALIZUJ"));
            jButton4.setText(bundle.getString("ZATRZYMAJ"));
            jButton5.setEnabled(false);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sNeuralNetwork.start();
                    stop();
                }
            }).start();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (sNeuralNetwork.isLearning()) {
            stop();
        } else {
            reset();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        List<Client> list = sNeuralNetwork.verifyElements(sVerifyingSet);
        new ClientSetDialog(this, true, list).setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        sLearningSet = getStaticLearningSet();
        for (int i = 0; i < sLearningSet.size(); i++) {
            System.out.println("k[" + i + "]\t" + sLearningSet.get(i).toString());
        }

        /*
        int ilosc = 0;//Math.abs(RandomNumber.nextInt(10, 25));
        for (int i = 0; i < ilosc; i++) {
            try {
                sLearningSet.add(Client.getRandomClient());
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         */
        sVerifyingSet = getStaticVerifyingSet();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
