/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book;

import java.awt.Button;
import java.awt.Color;
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


/**
 *
 * @author hopao
 */
public class Book extends Frame implements  ActionListener  {   
//    publisher acount detaild
       Label publisher_id , publisher_name ; 
       //        labels user welcome
       
       // book details felds
       
//       
//   البوليين فاريبل ده الهتشيك بيه ال كونيكشن شغال ولا لا 
       boolean is_connect =false ;
//       search page  
       Search search_page = new Search(this);
//    buttons frame 1 (this  frame);
//    the frame we will insert in him :D 
       InsertFrame insert_frame = new InsertFrame(this);
       Label status = new Label("status : ");
       Button connect = new Button("connect") ;
       Button disconnect = new Button("Disconnect") ;
       Button close = new Button("close");
       Button insert_frame_B = new Button("Insert Form");
//       الفورم البنبحث فيه
       Button search = new Button("SEARCH BOOK");
//        sql connection
        String url = "jdbc:sqlserver://localhost:1433;databaseName=EBOOK;encrypt=true;trustServerCertificate=true";
        Connection conn;
        String user = "sa";
        String password = "1234";
        
   // compisition class for frame 2 
        
//        log in bublisher id freme 
       SignIn signin = new SignIn(this); 
       
        
  
        
        
    /**
     * @param args the command line arguments
     */
    Book(){ 
        
        this.setLayout(null);
        this.insert_frame.add(this.close);
        
        this.insert_frame.setLayout(null);
        
        this.setBounds(500, 0 ,1000 , 1000 ); 
        this.insert_frame.setBounds(500, 0 ,1000 , 900 );
        this.status.setBounds(50 , 100 , 350 , 100  );
        this.connect.setBounds(120 , 300 , 200  , 100 );
        this.disconnect.setBounds(120 , 500 , 200  , 100 );
        this.insert_frame_B.setBounds(120 , 700 , 200  , 100   );
        this.search.setBounds(120 , 850 , 200  , 100   );
        this.close.setBounds(900 , 50 , 100 ,100);

        
        this.status.setBackground(Color.red);
        this.close.setBackground(Color.red);
        this.close.setBackground(Color.red);
        
        add(this.status);
        this.add(this.connect);
        this.add(this.disconnect);  
//        close 
        this.add(this.close); 
        this.add(this.insert_frame_B);
        
        this.add(this.search);
        

        this.status.setAlignment(CENTER);
        
        this.close.addActionListener(this);
        this.disconnect.addActionListener(this);
        this.connect.addActionListener(this);
        this.insert_frame_B.addActionListener(this);
        this.search.addActionListener(this);
//        my algo in sign in publisher page  
        
        
        
        
        this.showe(false);
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Book b = new Book();
    }
    public void connect() {
        
           try {
               this.conn =  DriverManager.getConnection(url, user, password);
               this.is_connect = true ;
                this.status.setText("Status : Connected");
               this.status.setBackground(Color.GREEN);
               
               
           } catch (SQLException ex) {
//               Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
                        this.status.setText("not connected");
                               this.status.setBackground(Color.ORANGE);
           }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.connect){
            
            this.connect(); 
           
        } 
        else if (e.getSource() == this.disconnect ){ 
            try {
           
                if(this.conn == null){
                this.status.setText("not Connected Already :D");
                return ; 
          
                } 
                this.conn.close();
                this.status.setText("Sucssefly disconnect");
                this.status.setBackground(Color.RED);
//                الهدف منها اني اساعد ال انسيرت فورم
                this.is_connect = false ;
                
            } catch (SQLException ex) {
                
                this.status.setText("cant disconnect the database");
                Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            
            
            
        }
        else if (e.getSource() == this.close){
            this.dispose();
            
        }
        else if (e.getSource() == this.insert_frame_B){ 
           this.dispose();
         ///
           this.insert_frame.setVisible(true);
           this.insert_frame.connect = this.conn ;
           
        
        }
        else if(e.getSource() == this.search){
            this.showe(false);
            this.search_page.showe(true);
            
        }else 
        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
     void showe(boolean stat){ 
        this.setVisible(stat);
    }
     
     void edit_user_details(){
     //                        انا عدلتهم من هنا عشان كان في مشكله معايا من ناحيه اني معرفتش الحاجات دي فلأول و كدا
                                this.publisher_id.setBounds(600 , 30 , 300 ,100);
                                this.publisher_name.setBounds(600 , 130 , 300 ,100);
//                               colors
//                                id 
                                this.publisher_id.setBackground(java.awt.Color.GREEN);
                                this.setFont(new Font("SansSerif", Font.PLAIN, 20));
                                this.add(this.publisher_id);
//                                name details
                                this.publisher_name.setBackground(java.awt.Color.GREEN);
                                this.publisher_name.setFont(new Font("SansSerif", Font.PLAIN, 20));
//                                ALIGNMENT 
                                this.publisher_id.setAlignment(CENTER);
                                this.publisher_name.setAlignment(CENTER);
                                this.add(this.publisher_name);

     
     }
    
}

