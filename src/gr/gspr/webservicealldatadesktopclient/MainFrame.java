/*
 *Apache License, Version 2.0
 */

package gr.gspr.webservicealldatadesktopclient;

import gr.gspr.webservicealldatadesktopclient.util.MyProxySelector;
import gr.gspr.webservicealldatadesktopclient.util.ProxySettings;
import gr.gspr.webservicealldatadesktopclient.util.Utils;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.ProxySelector;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.Arrays;

/**
 *
 * @author j.vlachos@gsis.gr
 */
public class MainFrame extends javax.swing.JFrame {
    public String proxyUrl="";
    public BigInteger proxyPort=BigInteger.valueOf(0);
    public String proxyUsername="";
    public String proxyPassword="";
    
    
            private Task task;
     class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
            jButton1.setEnabled(false);
            
            String user=jTextFieldUserName.getText().trim();
            char[] passAr=jPasswordField1.getPassword();
            String ps = new String(passAr);
            String result = "";
            
            if (user.isEmpty() || ps.isEmpty()){
               result = result + "Εισάγετε Όνομα χρήστη και Κωδικό" + "\r\n"; 
               jTextFieldUserName.requestFocus();
            }
            
            
            String afm=afmField1.getText().trim();
            boolean check = false;
            
            if (afm.isEmpty()) {
                result = result + "Εισάγετε ΑΦΜ για αναζήτηση" + "\r\n";
                afmField1.requestFocus();
            } else {

                check = Utils.checkAFM(afm);
                if (!check) {
                    result = result + "Ο ΑΦΜ που δώσατε για αναζήτηση είναι λανθασμένος" + "\r\n";
                    afmField1.requestFocus();
                }
            }
            
            
            String afmDelegator="";
            try{
               afmDelegator= afmField2.getText().trim();
               if (!afmDelegator.isEmpty()) {
                    check = Utils.checkAFM(afmDelegator);
                    if (!check) {
                        result = result + "Ο ΑΦΜ εξουσιοδοτούντος που δώσατε είναι λανθασμένος" + "\r\n";
                        afmField2.requestFocus();
                    }
               }
            }catch(Exception e){
                //no  afmDelegator
            }

            if (result.length() > 0) {
                jTextArea1.setText(result);
                return null;
            }



            try{
            
      
                
                try {
                   
                    // Create a trust manager that does not validate certificate chains
                    final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                        @Override
                        public void checkClientTrusted( final X509Certificate[] chain, final String authType ) {
                        }
                        @Override
                        public void checkServerTrusted( final X509Certificate[] chain, final String authType ) {
                        }
                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    } };

                    // Install the all-trusting trust manager
                    final SSLContext sslContext = SSLContext.getInstance( "TLSv1.2" );
                    sslContext.init( null, trustAllCerts, new java.security.SecureRandom() );
                    // Create an ssl socket factory with our all-trusting manager
                    final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                    HttpsURLConnection.setDefaultSSLSocketFactory( sslSocketFactory );
                 } catch ( final Exception e ) {
                        e.printStackTrace();
                 }
                
                ServiceClient sc=new ServiceClient();
                String un=jTextFieldUserName.getText().trim();
                char[] passArray=jPasswordField1.getPassword();
                String pass = new String(passArray);
                                
                //use the TLSv1.2 protocol
                System.setProperty("https.protocols", "TLSv1.2");
                //call to web service
                String returnValue=sc.getData(un,pass,afm,afmDelegator);
                
                jTextArea1.setText(returnValue);
        

            }catch(Exception e) {
                jTextArea1.setText("Παρουσιάστηκε σφάλμα. Το μήνυμα σφάλματος ήταν:\r\n:"+e.getLocalizedMessage());
            }

                return null;
            }

        
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            
            jButton1.setEnabled(true);
            
        }
       
    }
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        
        initComponents();
        setSize(660, 480);
            
        setLocationRelativeTo(null);
        //proxy:
        loadProxySettings();
        setProxy();
       
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
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldUserName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        afmField1 = new gsis.utils.AfmField();
        jLabel4 = new javax.swing.JLabel();
        afmField2 = new gsis.utils.AfmField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Πρόγραμμα Πελάτης Υπηρεσίας Βασικά Στοιχεία Μητρώου Public, Έκδοση 3.0.3 (ΓΓΠΣ-ΓΓΔΕ)");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(644, 174));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Ειδικός Κωδικός"));
        jPanel3.setName(""); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(640, 50));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Όνομα Χρήστη:");

        jTextFieldUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUserNameActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Κωδικός:");

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jButton3)
                .addGap(0, 110, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setPreferredSize(new java.awt.Dimension(640, 110));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("ΑΦΜ για αναζήτηση:");

        afmField1.setText("afmField1");

        jLabel4.setText("ΑΦΜ εξουσιοδοτούντος:");

        afmField2.setText("afmField2");

        jLabel5.setText("<html>(Εφόσον έχετε εξουσιοδοτηθεί από τρίτο άτομο και θέλετε να κάνετε αναζήτηση για λογαριασμό του)</html>");

        jButton1.setText("Αναζήτηση");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Καθαρισμός");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(afmField1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(afmField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(afmField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(afmField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setMinimumSize(new java.awt.Dimension(644, 323));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(644, 360));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(644, 360));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setMinimumSize(new java.awt.Dimension(640, 160));
        jTextArea1.setPreferredSize(new java.awt.Dimension(640, 230));
        jScrollPane1.setViewportView(jTextArea1);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText("Η εφορμογή κατασκευάστηκε απο το υπουργείο οικονομικών και βελτιώθηκε από την\nομάδα του taxonomy.gr\nΟ κώδικας βρίσκεται στο https://github.com/grousso/gsis-client\nCredits VManolas, Mike8 & GRousopoulos");
        jScrollPane2.setViewportView(jTextArea2);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Αρχείο");

        jMenuItem1.setText("Ορισμός Proxy");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Έξοδος");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTextArea1.setText("Παρακαλώ περιμένετε...");
        
        task = new Task();
        
        task.execute();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        afmField1.setText("");
        afmField2.setText("");
        jTextArea1.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // Set the proxy
        ProxyDialog proxyDialog = new ProxyDialog(new javax.swing.JFrame(), true);

        proxyDialog.setLocationRelativeTo(null);
        proxyDialog.setResizable(false);

        proxyDialog.setVisible(true);
        
        loadProxySettings();
        setProxy();
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jTextFieldUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUserNameActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void setProxy(){
        if (!proxyUrl.isEmpty()){
            ProxySelector.setDefault(new MyProxySelector(proxyUrl,proxyPort));
        }else{
            ProxySelector.setDefault(null);
        }
        if (!proxyUsername.isEmpty()){
           Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
              return new
                 PasswordAuthentication(proxyUsername,proxyPassword.toCharArray());
          }}); 
        }else{
            Authenticator.setDefault(null);
        }
        
    }
    
    void loadProxySettings(){
        proxyUrl="";
        proxyPort=BigInteger.valueOf(0);
        proxyUsername="";
        proxyPassword="";
    
      ProxySettings e = null;
      try
      {
         FileInputStream fileIn = new FileInputStream("./proxysettings.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         e = (ProxySettings) in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i)
      {
         i.printStackTrace();
         return;
      }catch(ClassNotFoundException c)
      {
         System.out.println("ProxySettings class not found");
         c.printStackTrace();
         return;
      }
      
      if (e!=null){
        proxyUrl=e.getUrl().trim();
        proxyPort=e.getPort();
        proxyUsername=e.getUsername().trim();
        proxyPassword=e.getPassword().trim();
        
      }
      
      
      
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try
            {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        catch (ClassNotFoundException e1)
        {
            e1.printStackTrace();
        }
        catch (InstantiationException e1) {
            e1.printStackTrace();
        }
        catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
        catch (UnsupportedLookAndFeelException e1) {
        e1.printStackTrace();
        }
        
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gsis.utils.AfmField afmField1;
    private gsis.utils.AfmField afmField2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextFieldUserName;
    // End of variables declaration//GEN-END:variables
}
