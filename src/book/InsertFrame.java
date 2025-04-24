/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import     java.awt.TextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author hopao
 */
class InsertFrame extends Frame implements ActionListener{
        Label first_name_l = new Label("First Name: ");
        TextField first_name = new TextField(); 
        Label last_name_l = new Label("Last Name:");
        TextField last_name = new TextField(); 
      
        Connection connect ; 
       // book details felds
       
        Label error_label = new Label("Please fall all the fields");
        Label error_label_connection = new Label("Please connect to the server");
//       
       Label title = new Label("Title : ");
       TextField title_feild = new TextField(); 
       
       Label type = new Label("Type : ");
       TextField type_feild = new TextField(); 
       
       Label page_count = new Label("Page Count : ");
       TextField page_count_feild = new TextField(); 
       
       Label price = new Label("Price: ");
       TextField price_feild = new TextField(); 
        
       
       Label isbn = new Label("ISBN");
       TextField isbn_feild = new TextField(); 
        
       Button close = new Button("back");
       Button close_f = new Button("close");
       Label done = new Label("Successfully added to the database"); 
//       submit button  ; 
       Button submit = new Button("Submit");
         
         Book defultFrame; 
        InsertFrame(Book b){   
            
            defultFrame = b ;
            
            title.setBounds(50, 50, 100, 30);
            this.title_feild.setBounds(150, 50, 300, 30);
this.error_label_connection.setVisible(false);
type.setBounds(50, 100, 100, 30);
type_feild.setBounds(150, 100, 200, 30);

page_count.setBounds(50, 150, 180, 30);
page_count_feild.setBounds(240, 150, 200, 30);

price.setBounds(50, 200, 100, 30);
price_feild.setBounds(150, 200, 200, 30);
this.first_name_l.setBounds(50, 250, 200, 30);
this.first_name.setBounds(250, 250, 200, 30);
this.last_name.setBounds(250, 290, 200, 30);
this.last_name_l.setBounds(50, 290, 200, 30);
this.isbn.setBounds(50, 330, 200, 30);
this.isbn_feild.setBounds(250, 330, 200, 30);
this.submit.setBounds(150, 380, 300, 30);
this.done.setBounds(145, 415, 314, 30);
this.error_label.setBounds(130, 433, 350, 30);
this.error_label_connection.setBounds(120,463, 375, 30);
             
         
//            set Bounds of buttons 
            this.close.setBounds(800 , 50 , 100 , 100);
            this.close_f.setBounds(900 , 50 , 100 , 100);
            
//            set colors 
            this.error_label.setVisible(false);
             this.title.setBackground(Color.pink);
             this.done.setBackground(Color.GREEN);
             this.type.setBackground(Color.pink);
             this.price.setBackground(Color.pink);
             this.page_count.setBackground(Color.pink);
             this.first_name.setBackground(Color.pink);
             this.first_name_l.setBackground(Color.pink);
             this.last_name.setBackground(Color.pink);
             this.last_name_l.setBackground(Color.pink);
             this.isbn.setBackground(Color.pink);
             this.close.setBackground(Color.BLACK);
             this.error_label.setBackground(Color.red);
             this.error_label_connection.setBackground(Color.red);
             this.close.setBackground(Color.BLACK);
             this.close.setForeground(Color.white);
             this.close_f.setBackground(Color.red);
             this.title.setFont(new Font("SansSerif", Font.PLAIN, 30));
             this.type.setFont(new Font("SansSerif", Font.PLAIN, 30));
             this.page_count.setFont(new Font("SansSerif", Font.PLAIN, 30));
             this.price.setFont(new Font("SansSerif", Font.PLAIN, 30));
             this.submit.setFont(new Font("SansSerif", Font.PLAIN, 30));
             this.error_label.setFont(new Font("SansSerif", Font.PLAIN, 30));
             this.error_label_connection.setFont(new Font("SansSerif", Font.PLAIN, 30));
             this.first_name.setFont(new Font("SansSerif", Font.PLAIN, 20));
             this.type_feild.setFont(new Font("SansSerif", Font.PLAIN, 20));
             this.title_feild.setFont(new Font("SansSerif", Font.PLAIN, 20));
             this.price_feild.setFont(new Font("SansSerif", Font.PLAIN, 20));
             this.page_count_feild.setFont(new Font("SansSerif", Font.PLAIN, 20));
             this.first_name_l.setFont(new Font("SansSerif", Font.PLAIN, 30));
             this.last_name.setFont(new Font("SansSerif", Font.PLAIN, 20));
             this.last_name_l.setFont(new Font("SansSerif", Font.PLAIN, 30));
             this.isbn.setFont(new Font("SansSerif", Font.PLAIN, 30));
             this.isbn_feild.setFont(new Font("SansSerif", Font.PLAIN, 20));
             this.done.setFont(new Font("SansSerif", Font.PLAIN, 20));
             
                
             this.done.setVisible(false);
//             add objects from frame  ضيف كل الزرااير
             
              this.add(this.close);
              this.add(this.close_f);
              this.add(title);
              this.add(title_feild);
              this.add(type);
              this.add(type_feild);
              this.add(page_count);
              this.add(page_count_feild);
              this.add(price);
              this.add(price_feild);
              this.add(this.submit);
              this.add(this.error_label);
              this.add(this.error_label_connection);
              this.add(this.first_name);
              this.add(this.last_name);
              this.add(this.first_name_l);
              this.add(this.last_name_l);
              this.add(this.isbn);
              this.add(this.isbn_feild);
              this.add(this.done);
//           add actions to objects  
              this.close.addActionListener(this);
              this.close_f.addActionListener(this);
              this.submit.addActionListener(this);
              
            
      }

    @Override
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.close){ 
            this.error_label.setVisible(false);
            this.error_label_connection.setVisible(false);
            this.dispose( );
            this.done.setVisible(false);
            this.defultFrame.setVisible(true);

        } 
        else if (e.getSource() == this.close_f){ 
//            close all the programm :D 
            
            
           System.exit(0);
        } else if(e.getSource() == this.submit){ 
//        بتشيك ان الفيلد كلها مليانه مسابش حاجه ناقصه 
        if (this.title_feild.getText().isEmpty() ||this.type_feild.getText().isEmpty() || this.price_feild.getText().isEmpty() || this.page_count.getText().isEmpty()) {
//          
            this.error_label.setVisible(true);
        }
        else{ 
            if(!this.defultFrame.is_connect){this.error_label_connection.setVisible(true); this.error_label.setVisible(false);}
            else{ 
              
//                    #####fucntion you will built to 
                    //                لو ال if
                    // متحققتش يبقي اليوزر فاتح ال كونيكت
//                الفونكشن دي بتضيف كل الداتا الخدناها من اليوز فلفيلدز
                    SmallTools.insert_data(this.connect, this.title_feild.getText(), this.type_feild.getText(),this.price_feild.getText(),this.page_count_feild.getText(),this.first_name.getText(), this.last_name.getText(), this.isbn_feild.getText(),this.defultFrame.publisher_id.getText());
SmallTools.clear(new TextField[] {
    this.title_feild,
    this.first_name,
    this.last_name,
    this.type_feild,
    this.price_feild,
    this.page_count_feild,
    this.isbn_feild
});
            this.done.setVisible(true);
            }
            
            
            
        
        }
            } 
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
         
}
