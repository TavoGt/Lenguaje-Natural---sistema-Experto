package appprolog;

import java.util.ArrayList;
import java.util.Hashtable;
import jpl.Query;

/**
 *
 * @author gtavo
 */
public class Principal extends javax.swing.JFrame {

    ArrayList<String> listNombres;
    String[] parentesco = {"progenitor", "hombre", "mujer", "descendiente", "padre", "hermano", "hermana", "nieto", "tia","tio", "papa", "mama", "esposo"};
    

    public Principal() {
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPregunta = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtRespuesta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setText("PARENTESCO");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("PREGUNTA:");

        txtPregunta.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        btnBuscar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("RESPUESTA:");

        txtRespuesta.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRespuesta))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(243, 243, 243))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBuscar)
                        .addGap(260, 260, 260))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addGap(124, 124, 124)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(130, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String[] palabras = new String[3];
        listNombres = new ArrayList<>();
        obtenerNombres();
        String input = txtPregunta.getText();
        String inputFormat = "";
        if (input.charAt(0) == '¿' && input.charAt(input.length() - 1) == '?') {
            inputFormat = input.substring(1, input.length() - 1);
        } else if (input.charAt(0) == '¿' && input.charAt(input.length() - 1) != '?') {
            inputFormat = input.substring(1);
        } else if (input.charAt(0) != '¿' && input.charAt(input.length() - 1) == '?') {
            inputFormat = input.substring(0, input.length() - 1);
        } else {
            inputFormat = input;
        }

        String[] entradas = inputFormat.toLowerCase().split(" ");
        boolean encontrado = false;
        int cnt = 0;
        for (int i = 0; i < entradas.length; i++) {

            for (int j = 0; j < parentesco.length; j++) {
                if (entradas[i].equals(parentesco[j])) {
                    encontrado = true;
                    palabras[0] = entradas[i];
                    j = parentesco.length;
                }
            }

            if (encontrado == false) {
                for (int j = 0; j < listNombres.size(); j++) {
                    if (entradas[i].equals(listNombres.get(j))) {
                        cnt++;
                        palabras[cnt] = entradas[i];
                        j = listNombres.size();
                    }
                }
            }
            encontrado = false;
        }

        if (cnt == 1) {
            txtRespuesta.setText(consultarPersona(palabras[0], palabras[1]));
        } else {
            if (consultarParentesco(palabras[0], palabras[1], palabras[2])) {
                txtRespuesta.setText("SI");
            } else {
                txtRespuesta.setText("NO");
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    public String consultarPersona(String parentesco, String persona) {
        String respuesta = "";
        String consulta = "consult('c:/prolog/parientes.pl').";
        Query resultado = new Query(consulta);
        if (resultado.hasSolution()) {
            String pregunta = parentesco + "(X," + persona + ").";
            Query myConsult = new Query(pregunta);
            Hashtable solucion;
            while (myConsult.hasMoreSolutions()) {
                solucion = myConsult.nextSolution();
                respuesta += solucion.get("X").toString().toUpperCase() + ", ";
            }
        }

        return respuesta;
    }

    public boolean consultarParentesco(String parentesco, String X, String Y) {
        String consulta = "consult('c:/prolog/parientes.pl').";
        Query resultado = new Query(consulta);
        if (resultado.hasSolution()) {
            String pregunta = parentesco + "(" + X + "," + Y + ").";
            Query myConsult = new Query(pregunta);
            return myConsult.hasSolution();
        }

        return false;
    }

    public void obtenerNombres() {
        String consulta = "consult('c:/prolog/parientes.pl').";
        Query resultado = new Query(consulta);
        if (resultado.hasSolution()) {
            //System.out.println("Conexión exitosa");
            String pregunta = "progenitor(X,Y).";
            Query myConsult = new Query(pregunta);
            Hashtable solucion;
            int contX = 0, contY = 0;
            while (myConsult.hasMoreSolutions()) {
                solucion = myConsult.nextSolution();
                for (String nombre : listNombres) {
                    if (solucion.get("X").toString().equals(nombre)) {
                        contX++;
                    }
                    if (solucion.get("Y").toString().equals(nombre)) {
                        contY++;
                    }
                }

                if (contX == 0) {
                    listNombres.add(solucion.get("X").toString());
                }

                if (contY == 0) {
                    listNombres.add(solucion.get("Y").toString());
                }

                //System.out.println(solucion.get("X")+" " +solucion.get("Y"));
                contX = contY = 0;
            }
        }
    }

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtPregunta;
    private javax.swing.JTextField txtRespuesta;
    // End of variables declaration//GEN-END:variables
}
