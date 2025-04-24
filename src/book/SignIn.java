/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book;

import com.sun.glass.ui.Cursor;
import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import static java.awt.Label.CENTER;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;

/**
 *
 * @author hopao
 */
public class SignIn extends Frame implements ActionListener{
      Book defult_page ; 
//      page if the user new publisher ; 
      UserForm  user_form ;
      Label label_publisher_id = new Label("Publisher Id") ;
      Label stat = new Label("please put you publisher ID ");
      TextField  publisher_id = new TextField(); 
//      button sign
      Button sign_b = new Button("sign in "); 
//      new publisher 
      Button new_publisher = new Button("New Publisher");
      
       String url = "jdbc:sqlserver://localhost:1433;databaseName=EBOOK;encrypt=true;trustServerCertificate=true";
//       conn the link with SQL 
        Connection conn;
        String user = "sa";
        String password = "1234";
     
      SignIn(Book defult){
          this.defult_page = defult ; 
          this.show(true);
//      set Bounds      
          
          this.label_publisher_id.setBounds(150 , 200 , 200 , 100);
          this.publisher_id.setBounds(150 , 350 , 200 , 50);
          this.setBounds(200,0 , 550 , 750); 
          this.sign_b.setBounds(150 , 450 , 200 , 50);
          this.stat.setBounds(140 , 100 , 250 , 70);
          this.new_publisher.setBounds(150 , 550 , 200 , 50);
          this.setLayout(null);
          
//          set fonts
          
          label_publisher_id.setFont(new Font("SansSerif", Font.PLAIN, 30));
          stat.setFont(new Font("SansSerif", Font.PLAIN, 20));
          stat.setBackground(java.awt.Color.red);
//          add 
          this.add(this.label_publisher_id);
          this.add(this.publisher_id);
          this.add(this.sign_b);
          this.add(this.stat);
          this.sign_b.addActionListener(this);
          this.add(this.new_publisher);
          this.new_publisher.addActionListener(this);
//          set colors
          this.label_publisher_id.setBackground(java.awt.Color.GREEN);
          this.label_publisher_id.setAlignment(CENTER);
          this.stat.setAlignment(CENTER);
          this.stat.setVisible(false);
          this.new_publisher.setFont(new Font("SansSerif" , Font.ITALIC , 20));
          
          
//          connect to the database to get the publisher id :D 
          this.connectp();
             
      }    

    @Override
    public void actionPerformed(ActionEvent e) {
            if (e.getSource() == this.sign_b){
                if(this.publisher_id.getText().isEmpty() ){
                    this.stat.setVisible(true);
                    System.out.println("null publisher id ");
                }
                
                
                else {
             
                    try {
//                      search for the id publisher in publisher taple :D XD  
                        Statement statment  = this.conn.createStatement();
                        ResultSet result = statment.executeQuery(String.format("select * from Publisher where Publisher_ID = %s ",this.publisher_id.getText()));
//                       checker :D 
                        // if result.next() return true this is mean found the id :D
                        
                        if(result.next()){
                            
                        this.showme(false);
                        this.defult_page.showe(true);
//                        data of the user
                        this.defult_page.publisher_id = new Label("ID : " + result.getString("Publisher_ID"));
                        this.defult_page.publisher_name =new Label("WELCOME : " +result.getString("Name"));
                        
                        
//                        انا عدلتهم من هنا عشان كان في مشكله معايا من ناحيه اني معرفتش الحاجات دي فلأول و كدا
                                this.defult_page.edit_user_details();

                        
                        System.out.println(result.getString("Name"));
                        
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, ex);
                    }
            

                }
            }
            else if (e.getSource() == this.new_publisher){ 
                this.showme(false);
//                connect to database to insert the user information :D 
                try {
                    user_form = new UserForm(  DriverManager.getConnection(url, user, password),this, this.defult_page);
                } catch (SQLException ex) {
                    Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, ex);
                }

            
            }
        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//    disble it when the publisher id is true :D 
    void showme(boolean stat){ 
        if(!stat){
            this.dispose();
        } 
        this.setVisible(stat);
    }
    void connectp(){
             try {
               this.conn =  DriverManager.getConnection(url, user, password);
         
               System.out.println("done");
               
               
           } catch (SQLException ex) {
               System.out.println("error D: in connect function in signin class ");
                        
           }
    } 
    
}
