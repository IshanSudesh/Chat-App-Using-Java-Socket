package chatapp;

import com.sun.org.apache.xalan.internal.lib.ExsltDatetime;
import java.awt.Color;
import java.io.*;
import java.net.*;

public final class server extends javax.swing.JFrame {

    ObjectOutputStream outputSTM;
    ObjectInputStream inputSTM;
    
    public server() {
        
        initComponents();
//        transparent();
//        buttonSetColour();

        
        try{  
            ServerSocket serverSKT = new ServerSocket(5000);
            Socket connectionSocket = serverSKT.accept();
            outputSTM = new ObjectOutputStream(connectionSocket.getOutputStream());
            inputSTM = new ObjectInputStream(connectionSocket.getInputStream());          
        }catch(IOException e){
            System.out.println(e.toString());
        }
        
    }
    
//    public void transparent(){
//        
//            jScrollPane1.setOpaque(false);
//            jScrollPane1.getViewport().setOpaque(false);
//            jScrollPane1.setBorder(null);
//            jScrollPane1.setViewportBorder(null);
//
//            
//           jTextArea1.setBorder(null);
//           jTextArea1.setBackground(new Color(0,0,0,64));
//    }
    
//    public void buttonSetColour(){
//        jButton1.setForeground(Color.BLACK);
//        jButton1.setBackground(Color.WHITE);
//    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");
        setBackground(new java.awt.Color(78, 152, 87));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 190, 30));

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 500, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(60, 160, 74));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 470));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        checkSpace();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void checkSpace(){
        
          if (!jTextField1.getText().equals("")) {
            String msg = jTextField1.getText();
            SendMessage(msg);
            jTextField1.setText("");
        }
          
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        
        server ser = new server();
        ser.setVisible(true);
        ser.ReciveMessage();
       
    }
    
      private void SendMessage(String msg) {
          
        String time = ExsltDatetime.time();
        time = time.substring(0, 5);
        try {
            outputSTM.writeObject(msg);
            jTextArea1.append("\t\t"+ " " + msg + " " + time + "\n");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
    }

    private void ReciveMessage() {
        
        String msg;
        String time = ExsltDatetime.time();
        time = time.substring(0, 5);
        while (true) {
            try {
                msg = (String) inputSTM.readObject();
                jTextArea1.append("  " + msg + " " + time + "\n");
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
