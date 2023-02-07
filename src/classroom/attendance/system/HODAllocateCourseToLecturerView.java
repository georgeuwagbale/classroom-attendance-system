package classroom.attendance.system;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author uwagb
 */
public class HODAllocateCourseToLecturerView extends javax.swing.JFrame {
private static Dictionary<String, Integer> userNameDict; 
private int departmentID;
static DefaultTableModel td;
    /**
     * Creates new form HODAllocateCourseToLecturerView
     */
    public HODAllocateCourseToLecturerView(int DepartmentID) {
        
        initComponents();
        this.td = (DefaultTableModel)data_table.getModel();
        this.departmentID = DepartmentID; 
        this.userNameDict = new Hashtable<String, Integer>();
        populateDataTable(DepartmentID);
    }
    
    public static void populateDataTable(int DepartmentID){
        
        //String LecturerName = null;
        //String LecturerCourse = "";
        
        //td.addRow(data);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAS", "root", "example");
            
            // Get all LecturerID
            PreparedStatement ps = con.prepareStatement("Select LecturerID from Lecturer where FacultyID in ("
                    + "select FacultyID from Faculty where DepartmentID=?"
                    + ")");
            ps.setInt(1,DepartmentID);
            ResultSet rs = ps.executeQuery();
            int LecturerID = 0;
            
            while (rs.next()){
                String[] data = {
                    "",
                    ""
                };
                
                LecturerID = rs.getInt("LecturerID");
                //System.out.println("Lecturer: " + rs.getString("LecturerID"));
                if (LecturerID > 0){
                    // Get Course Name for each Course assigned to a LecturerID
                    PreparedStatement ps1 = con.prepareStatement(""
                             + "select Name from Course where CourseID in("
                             + "select CourseID from LecturerCourse where LecturerID=?"
                             + ")");
                    
                     ps1.setInt(1,LecturerID);
                     
                     ResultSet rs1 = ps1.executeQuery();

                     //String LecturerCourse = "";
                        while (rs1.next()){
                         //System.out.println("Course: " + rs1.getString("Name"));
                             if (rs1.getString("Name") != null){
                                data[1] += " " + rs1.getString("Name") + ";";
                            }
                         
                        }
                     
                     // End
                     
                     //System.out.println("Course: "+ data[1]);
                     //LecturerCourse = "";
                     
                     // Get FirstName and LastName for each LecturerID
                     PreparedStatement ps2 = con.prepareStatement(""
                             + "select FirstName, LastName from User where UserID in ("
                             + "select Faculty.UserID from Faculty where FacultyID in ("
                             + "select Lecturer.FacultyID from Lecturer where LecturerID=?"
                             + ")"
                             + ")");
                     ps2.setInt(1, LecturerID);
                     ResultSet rs2 = ps2.executeQuery();

                    while (rs2.next()){
                         //System.out.println("User: " + rs2.getString("FirstName") + " " + rs2.getString("LastName"));
                         data[0] = rs2.getString("FirstName") + " " + rs2.getString("LastName");
                         HODAllocateCourseToLecturerView.userNameDict.put(data[0], LecturerID);
                    } 
                    // End
                }
                
                
                boolean flag = true;
                int td_row = HODAllocateCourseToLecturerView.td.getRowCount();
                for(int i=0; i<td_row; i++){
                    System.out.println(data[1]);
                    if(HODAllocateCourseToLecturerView.td.getValueAt(i, 0).equals(data[0]) ){
                        //String courses = (String) HODAllocateCourseToLecturerView.td.getValueAt(i, 1);
                        
                        HODAllocateCourseToLecturerView.td.setValueAt(data[1], i, 1);
                        flag = false;
                    }
                    
                }
                
                if(flag){
                    HODAllocateCourseToLecturerView.td.addRow(data);
                }
                
                
                // End
                
            }
             con.close();
         
        }catch(Exception e){
            System.out.print(e);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        data_table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        data_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lecturer", "Course"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        data_table.getTableHeader().setReorderingAllowed(false);
        data_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                data_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(data_table);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void data_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_data_tableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel td = (DefaultTableModel)data_table.getModel();
        int row = data_table.getSelectedRow();
        String LecturerName = "";
        int LecturerID = 0;
        try{
            LecturerName = String.valueOf(td.getValueAt(row, 0));
            LecturerID = this.userNameDict.get(LecturerName);
            //System.out.println("Name: " + LecturerName +"; " + "ID: " + LecturerID);
            // Instantial the Add or Remove Course View
            new AddRemoveCourseView(LecturerID, this.departmentID).setVisible(true);
            
            // End
        }catch (Exception e){
            System.out.println(e);
        }
        //populateDataTable(this.departmentID);
    }//GEN-LAST:event_data_tableMouseClicked

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
            java.util.logging.Logger.getLogger(HODAllocateCourseToLecturerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HODAllocateCourseToLecturerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HODAllocateCourseToLecturerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HODAllocateCourseToLecturerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new HODAllocateCourseToLecturerView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable data_table;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
