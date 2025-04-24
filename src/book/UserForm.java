package book;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserForm extends Frame implements ActionListener {
    Book defult_page  ;
    Button back = new Button("back");
    Button close = new Button("close");
//    الكونيكشن الباسهولي  signin
    Connection connection ;
    Label nameLabel = new Label("Name:");
    TextField nameField = new TextField();

    Label cityLabel = new Label("City:");
    TextField cityField = new TextField();

    Label phoneLabel = new Label("Phone:");
    TextField phoneField = new TextField();

    Button submitButton = new Button("Submit");
    
    SignIn back_to ; 

    Label statusLabel = new Label();

    public UserForm(  Connection conn , SignIn back_to_Sign , Book b ) {
        this.defult_page = b ; 
        back_to = back_to_Sign; 
        setTitle("User Info Form");
        setSize(400, 400);
        setLayout(null);
        setLocation(300, 100);
        this.connection= conn;

// Name
        nameLabel.setBounds(50, 110, 80, 30);
        nameField.setBounds(150, 110, 200, 30);

// City
        cityLabel.setBounds(50, 160, 80, 30);
        cityField.setBounds(150, 160, 200, 30);

// Phone
        phoneLabel.setBounds(50, 210, 80, 30);
        phoneField.setBounds(150, 210, 200, 30);
// Button
        
        submitButton.setBounds(150, 260, 100, 40);
        submitButton.addActionListener(this);
// Status Label
        statusLabel.setBounds(50, 320, 300, 30);
        statusLabel.setAlignment(Label.CENTER);
        
//        back
        close.setBounds(345, 40, 50, 50);
        back.setBounds(290, 40, 50, 50);
        close.setBackground(Color.red);
        // Add to frame
        this.add(nameLabel);
        this.add(nameField);
        this.add(cityLabel);
        this.add(cityField);
        this.add(phoneLabel);
        this.add(phoneField);
        this.add(submitButton);
        this.add(statusLabel);
        this.add(back);
        this.add(close);
        this.back.addActionListener(this);
        
        this.close.addActionListener(this);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        collect the data from user 
        String name = nameField.getText();
        String city = cityField.getText();
        String phone = phoneField.getText();
 String id = null ;
        if (e.getSource() == this.close) {
         System.exit(0);
        } 
//        خليتهاالتانيه عشان ممكن تكون فاضيه بس اليوزر داس علي ال   كلوز
        else if (e.getSource() == this.submitButton) { 
            if (name.isEmpty() || city.isEmpty() || phone.isEmpty()){
                
            statusLabel.setText("Please fill all fields.");
            statusLabel.setBackground(Color.RED);
            }else {
//                user add the data :D 
            //          in here i will open the small window and add the data to SQL  and pass the same data in defult page  :D   
//###################################################################################
//            insert the data by last id 
                try {
                    id = SmallTools.last_id(this.connection , "Publisher_id" , "Publisher") ;
                    this.connection.createStatement().execute(String.format("INSERT INTO Publisher Values('%s', '%s' , '%s' , '%s' )",id, name,city ,phone));
                } catch (SQLException ex) {
                    Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            HelloFrame hello = new HelloFrame("WELCOME : "+this.nameField.getText(),"ID : "+id);
            
//            close the program from here :D $$$$$$################################افتكر الله يخليك
            
            this.dispose();
//            open the defult page and add user label 
            this.defult_page.publisher_name = new Label ("WELCOME : "+this.nameField.getText());
            this.defult_page.publisher_id = new Label("ID : "+id);
            this.defult_page.showe(true);
            this.defult_page.edit_user_details();
            try {
                //            اقفل الكونيكش ن بعد مضفت الداتا فبابلشر داتابيز 
                this.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
            }

            statusLabel.setBackground(Color.GREEN);
        }
        
        }
        else if(e.getSource()== this.back){
        this.setVisible(false);
        this.back_to.setVisible(true);
        
            try {
                //        close the connection  عشان كد كدا لو دخل تاني هفتحلو الكونيكشن
                this.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    
    }

}