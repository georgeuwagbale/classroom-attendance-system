/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package classroom.attendance.system;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author uwagb
 */
public class FacultyHomeView extends javax.swing.JFrame {
FacultyLogIn.Faculty loggedInFaculty;
private final Dictionary<String, Integer> departmentNameDict;
private final Dictionary<String, Integer> courseNameDict;
    /**
     * Creates new form FacultyHomeView
     */
    public FacultyHomeView(FacultyLogIn.Faculty newUser) {
        
        initComponents();
        departmentNameDict = new Hashtable<String, Integer>();
        courseNameDict = new Hashtable<String, Integer>();
        this.loggedInFaculty = newUser;
        populateFacultyInfo();
        setFacultyOperations();
    }
    
    public void populateFacultyInfo(){
        first_name_label.setText(this.loggedInFaculty.first_name);
        last_name_label.setText(this.loggedInFaculty.last_name);
        //department_label.setText(this.loggedInFaculty.department);
        role_label.setText(this.loggedInFaculty.role);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAS", "root", "example");
            PreparedStatement ps = con.prepareStatement(""
                                + "select Name from Department where DepartmentID=?");
            ps.setInt(1, this.loggedInFaculty.department);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                department_label.setText(rs.getString("Name"));
                break;
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
        Image image = Toolkit.getDefaultToolkit().createImage(loggedInFaculty.image);
        image = image.getScaledInstance(show_label.getWidth(),
                show_label.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(image);
            show_label.setIcon(icon);
    }
    
    public void setFacultyOperations(){
        if(this.loggedInFaculty.role.equals("HOD")){
            sign_student_out_button.setVisible(false);
            give_concession_button.setVisible(false);
            register_student_button.setVisible(false);
            start_attendance_button.setVisible(false);
            stop_attendance_button.setVisible(false);
            
        }else if(this.loggedInFaculty.role.equals("Lecturer")){
            give_concession_button.setVisible(false);
            register_student_button.setVisible(false);
            
            view_attendance_sheet_button.setVisible(false);
            allocate_course_to_lecturer_button.setVisible(false);
            
        }else if(this.loggedInFaculty.role.equals("Dean")){
            start_attendance_button.setVisible(false);
            stop_attendance_button.setVisible(false);
            register_student_button.setVisible(false);
            allocate_course_to_lecturer_button.setVisible(false);
            sign_student_out_button.setVisible(false);
            
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
        show_label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        first_name_label = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        last_name_label = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        department_label = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        role_label = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        register_student_button = new javax.swing.JButton();
        allocate_course_to_lecturer_button = new javax.swing.JButton();
        view_attendance_sheet_button = new javax.swing.JButton();
        view_attendance_percentage_button = new javax.swing.JButton();
        give_concession_button = new javax.swing.JButton();
        sign_student_out_button = new javax.swing.JButton();
        start_attendance_button = new javax.swing.JButton();
        stop_attendance_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        show_label.setBackground(new java.awt.Color(255, 255, 255));
        show_label.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("First Name");

        first_name_label.setBackground(new java.awt.Color(255, 255, 255));
        first_name_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        first_name_label.setText("first name");
        first_name_label.setOpaque(true);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Last Name");

        last_name_label.setBackground(new java.awt.Color(255, 255, 255));
        last_name_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        last_name_label.setText("last name");
        last_name_label.setOpaque(true);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Department");

        department_label.setBackground(new java.awt.Color(255, 255, 255));
        department_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        department_label.setText("department");
        department_label.setOpaque(true);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Role");

        role_label.setBackground(new java.awt.Color(255, 255, 255));
        role_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        role_label.setText("role");
        role_label.setOpaque(true);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Logout");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("Change Password");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(55, 55, 55)
                                .addComponent(first_name_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(61, 61, 61)
                                .addComponent(last_name_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addGap(52, 52, 52)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(role_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(department_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                        .addComponent(show_label, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(first_name_label))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(last_name_label))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(department_label)))
                    .addComponent(show_label, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(role_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(22, 22, 22))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        register_student_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        register_student_button.setText("Register Student");
        register_student_button.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        allocate_course_to_lecturer_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        allocate_course_to_lecturer_button.setText("Allocate Course To Lecturer");
        allocate_course_to_lecturer_button.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        allocate_course_to_lecturer_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allocate_course_to_lecturer_buttonActionPerformed(evt);
            }
        });

        view_attendance_sheet_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        view_attendance_sheet_button.setText("View Attendance Sheet");
        view_attendance_sheet_button.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        view_attendance_percentage_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        view_attendance_percentage_button.setText("View Attendance Percentage");
        view_attendance_percentage_button.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        view_attendance_percentage_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_attendance_percentage_buttonActionPerformed(evt);
            }
        });

        give_concession_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        give_concession_button.setText("Give Concession");
        give_concession_button.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        sign_student_out_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sign_student_out_button.setText("Sign Student Out");
        sign_student_out_button.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        start_attendance_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        start_attendance_button.setText("Start Attendance");
        start_attendance_button.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        start_attendance_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start_attendance_buttonActionPerformed(evt);
            }
        });

        stop_attendance_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        stop_attendance_button.setText("Stop Attendance");
        stop_attendance_button.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        stop_attendance_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stop_attendance_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(register_student_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(allocate_course_to_lecturer_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(view_attendance_sheet_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(view_attendance_percentage_button, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(give_concession_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sign_student_out_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(start_attendance_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stop_attendance_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(sign_student_out_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(give_concession_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(view_attendance_percentage_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(view_attendance_sheet_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(allocate_course_to_lecturer_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(register_student_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(start_attendance_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stop_attendance_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void allocate_course_to_lecturer_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allocate_course_to_lecturer_buttonActionPerformed
        // TODO add your handling code here:
        new HODAllocateCourseToLecturerView(this.loggedInFaculty.department).setVisible(true);
        //view.setVisible(true);
        //this.loggedInFaculty.allocateCourseToLecturer();
    }//GEN-LAST:event_allocate_course_to_lecturer_buttonActionPerformed

    public String[] populateLecturerCourseOptions(){
        String[] courseOptions = new String[3];
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAS", "root", "example");
            PreparedStatement ps = con.prepareStatement("select * from Course where CourseID in("
                    + "select CourseID from LecturerCourse where LecturerID in("
                    + "select LecturerID from Lecturer where FacultyID in("
                    + "select FacultyID from Faculty where UserID in("
                    + "select UserID from User where Email=?"
                    + ")"
                    + ")"
                    + ")"
                    + ")");
            ps.setString(1, this.loggedInFaculty.email);
            ResultSet rs = ps.executeQuery();
            
            int count = 0;
            while(rs.next()){
                courseOptions[count] = rs.getString("Name");
                this.courseNameDict.put(rs.getString("Name"),rs.getInt("CourseID"));
                count++;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return courseOptions;
    }
    
    public String[] populateDeanCourseOptions(){
        String[] courseOptions = new String[10];
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAS", "root", "example");
            PreparedStatement ps = con.prepareStatement("select * from Course where DepartmentID in ("
                    + "select DepartmentID from Department where SchoolID in ("
                    + "select SchoolID from Department where DepartmentID=?"
                    + ")"
                    + ")");
            ps.setInt(1, this.loggedInFaculty.department);
            
            ResultSet rs = ps.executeQuery();
            int count = 0;
            while(rs.next()){
                courseOptions[count] = rs.getString("Name");
                this.courseNameDict.put(rs.getString("Name"), rs.getInt("CourseID"));
                count++;
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        return courseOptions;
    }
    
    public float getTotalAttendance(int courseID){
        float count = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAS", "root", "example");
            PreparedStatement ps = con.prepareStatement("select AttendanceID from Attendance where CourseID=?");
            ps.setInt(1,courseID);
            ResultSet rs = ps.executeQuery();
            
            
            
            while(rs.next()){
                count++;
            }
            //System.out.println("Attendance Sheet: " + String.valueOf(count));
            
        }catch(Exception e){
            System.out.println(e);
        }
        return count;
    }
    
    public void getParticularStudentPercentageAttendance(){
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAS", "root", "example");
            PreparedStatement ps = con.prepareStatement("");
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void view_attendance_percentage_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_attendance_percentage_buttonActionPerformed
        // TODO add your handling code here:
        
        if(this.loggedInFaculty.role.equals("Lecturer")){
            
            Object value = JOptionPane.showInputDialog(rootPane, "Course", "Select", 1, null,
                    populateLecturerCourseOptions(), DISPOSE_ON_CLOSE);
            //System.out.println(value);
            
            if (value != null){
                int courseID = this.courseNameDict.get(String.valueOf(value));
                
                // Use course to get the next view
                new LecturerViewAttendancePercentage(courseID,
                        String.valueOf(value),getTotalAttendance(courseID)).setVisible(true);
            }
        }
        
        //String[] options = {"A Particular Student", "A Course"};
        
        if(this.loggedInFaculty.role.equals("Dean")){
            //Object choice = JOptionPane.showInputDialog(rootPane, "For who?", "", 1, null,
            //        options,DISPOSE_ON_CLOSE);
            
            //if(choice != null){
            //    if(String.valueOf(choice).equals(options[0])){
            //        Object matricNo = JOptionPane.showInputDialog(rootPane, "Enter Student's Matric No.", "Request", 1);
                    
                    
            //        JOptionPane.showConfirmDialog(rootPane, "");
            //    }
            //}
            
            Object value = JOptionPane.showInputDialog(rootPane, "Course", "Select", 1, null,
                    populateDeanCourseOptions(), DISPOSE_ON_CLOSE);
            
            if (value != null){
                int courseID = this.courseNameDict.get(String.valueOf(value));
                
                // Use course to get the next view
                new LecturerViewAttendancePercentage(courseID,
                        String.valueOf(value),getTotalAttendance(courseID)).setVisible(true);
            }
            
        }
    }//GEN-LAST:event_view_attendance_percentage_buttonActionPerformed

   public void checkForMultipleAttendanceEntry(){
       
   } 
    
    private void start_attendance_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start_attendance_buttonActionPerformed
        // TODO add your handling code here:
        
        Object value = JOptionPane.showInputDialog(rootPane, "Course", "Select", 1, null,
                    populateLecturerCourseOptions(), DISPOSE_ON_CLOSE);
        
        if (value != null){
            int courseID = this.courseNameDict.get(String.valueOf(value));
            Calendar calendar = Calendar.getInstance();
            //Date date_ = new Date();
            java.sql.Date date_ = new java.sql.Date(calendar.getTime().getTime());
            java.sql.Time time_ = new java.sql.Time(date_.getTime());
            //SimpleDateFormat dat = new SimpleDateFormat("yyyy-MM-dd");
            //String date = dat.format(date_);
            //System.out.println(time_);
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAS", "root", "example");
                PreparedStatement ps = con.prepareStatement("insert into Attendance("
                        + "CourseID,Date,StartTime,Status"
                        + ") values(?,?,?,?)");
                ps.setInt(1, courseID);
                ps.setDate(2, date_);
                ps.setTime(3, time_);
                ps.setInt(4, 1);
                ps.executeUpdate();
                
                con.close();
                JOptionPane.showMessageDialog(rootPane, "Attendance Started");
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_start_attendance_buttonActionPerformed

    private void stop_attendance_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stop_attendance_buttonActionPerformed
        // TODO add your handling code here:
        Object value = JOptionPane.showInputDialog(rootPane, "Course", "Select", 1, null,
                    populateLecturerCourseOptions(), DISPOSE_ON_CLOSE);
        
        if (value != null){
            int courseID = this.courseNameDict.get(String.valueOf(value));
            Calendar calendar = Calendar.getInstance();
            java.sql.Date date_ = new java.sql.Date(calendar.getTime().getTime());
            java.sql.Time time_ = new java.sql.Time(date_.getTime());
            
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAS", "root", "example");
                
                PreparedStatement ps = con.prepareStatement("select * from Attendance where "
                    + "CourseID=? and Date=? and Status=?");
                ps.setInt(1, courseID);
                ps.setDate(2, date_);
                ps.setInt(3, 1);
                ResultSet rs = ps.executeQuery();
                
                while(rs.next()){
                    java.sql.Time startTime = rs.getTime("StartTime");
                    if(startTime.getHours() == time_.getHours()){
                        PreparedStatement ps1 = con.prepareStatement("update Attendance set StopTime=?, Status=? where AttendanceID=?");
                        ps1.setTime(1, time_);
                        ps1.setInt(2, 0);
                        ps1.setInt(3, rs.getInt("AttendanceID"));
                        ps1.executeUpdate();
                    }
                }
                con.close();
                JOptionPane.showMessageDialog(rootPane, "Attendance Closed");
            }catch(Exception e){
                System.out.println(e);
            }
            
        }
    }//GEN-LAST:event_stop_attendance_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(FacultyHomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FacultyHomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FacultyHomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FacultyHomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new FacultyHomeView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton allocate_course_to_lecturer_button;
    private javax.swing.JLabel department_label;
    private javax.swing.JLabel first_name_label;
    private javax.swing.JButton give_concession_button;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel last_name_label;
    private javax.swing.JButton register_student_button;
    private javax.swing.JLabel role_label;
    private javax.swing.JLabel show_label;
    private javax.swing.JButton sign_student_out_button;
    private javax.swing.JButton start_attendance_button;
    private javax.swing.JButton stop_attendance_button;
    private javax.swing.JButton view_attendance_percentage_button;
    private javax.swing.JButton view_attendance_sheet_button;
    // End of variables declaration//GEN-END:variables
}
