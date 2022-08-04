/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i2a;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author saliya
 */
public class i2am extends javax.swing.JFrame {

    private java.awt.Image IMG = null;
    private final JFileChooser FILE_CHOOSER;
    private final long MAX_FILE_SIZE = 1024 * 8;
    private BufferedImage BUFF_IMG;
    private double PIXEL_VALUE;
    private PrintWriter PRINT_WRITER;
    private FileWriter FILE_WRITER;
    private String FILE_PATH = "";
    StringBuilder STRING_BUILDER;

    /**
     * Creates new form i2am
     */
    public i2am() {
        initComponents();
        FILE_CHOOSER = new JFileChooser(System.getProperty("user.dir", "."));
        jTextArea1.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 3));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Image to Ascii");

        jButton1.setText("Open File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("...");

        jLabel3.setText("...");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial", 0, 1)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FILE_CHOOSER.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("image files", "png", "jpg");
        FILE_CHOOSER.addChoosableFileFilter(filter);
        FILE_CHOOSER.showOpenDialog(null);
        File file = FILE_CHOOSER.getSelectedFile();
        FILE_PATH = FILE_CHOOSER.getSelectedFile().getPath();
        if (FILE_CHOOSER.getSelectedFile() != null) {
            if (MAX_FILE_SIZE > file.getTotalSpace()) {
                jLabel2.setText("file size limit exceeded !");
            } else {
                try {
                    String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
                    timeStamp = timeStamp.replace(":", "-");
                    String fn = timeStamp + "_" + "asciiart.txt";
                    PRINT_WRITER = new PrintWriter(FILE_WRITER = new FileWriter(fn, true));
                } catch (IOException ex) {
                }
                getImage(FILE_PATH);
                jLabel2.setText(FILE_PATH);
                jLabel3.setText(String.valueOf(file.getName() + " :" + file.length()) + " bytes");
                convertToAscii(FILE_PATH);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(i2am.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new i2am().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    private void getImage(String path) {
        try {
            IMG = new ImageIcon(path).getImage();
            IMG = IMG.getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), java.awt.Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(IMG);
            jLabel1.setIcon(icon);
        } catch (Exception e) {

        }
    }

    public void convertToAscii(String imgname) {
        try {
            BUFF_IMG = ImageIO.read(new File(imgname));
        } catch (IOException e) {

        }

        STRING_BUILDER = new StringBuilder((BUFF_IMG.getWidth() + 1) * BUFF_IMG.getHeight());
        for (int i = 0; i < BUFF_IMG.getHeight(); i++) {
            if (STRING_BUILDER.length() != 0) {
                STRING_BUILDER.append("\n");
            }
            for (int j = 0; j < BUFF_IMG.getWidth(); j++) {
                Color PIXEL_COLOR = new Color(BUFF_IMG.getRGB(j, i));
                PIXEL_VALUE = (((PIXEL_COLOR.getRed() * 0.30) + (PIXEL_COLOR.getBlue() * 0.59) + (PIXEL_COLOR.getGreen() * 0.11)));
                STRING_BUILDER.append(strChar(PIXEL_VALUE));
                printToFile(strChar(PIXEL_VALUE));
            }
            try {
                PRINT_WRITER.println("");
                PRINT_WRITER.flush();
                FILE_WRITER.flush();
            } catch (IOException ex) {
            }
        }

        try {
            PRINT_WRITER.close();
            FILE_WRITER.close();
        } catch (IOException ex) {
        }
        jTextArea1.setText(STRING_BUILDER.toString());
    }

    public String strChar(double g) {
        String str = " ";
        if (g >= 240) {
            str = " ";
        } else if (g >= 210) {
            str = " ";
        } else if (g >= 190) {
            str = "*";
        } else if (g >= 170) {
            str = "+";
        } else if (g >= 120) {
            str = "^";
        } else if (g >= 110) {
            str = "&";
        } else if (g >= 80) {
            str = "8";
        } else if (g >= 60) {
            str = "#";
        } else {
            str = "@";
        }
        return str;
    }

    public void printToFile(String str) {
        try {
            PRINT_WRITER.print(str);
            PRINT_WRITER.flush();
            FILE_WRITER.flush();
        } catch (IOException ex) {

        }
    }
}
