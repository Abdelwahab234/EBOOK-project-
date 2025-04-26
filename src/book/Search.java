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

/**
 *
 * @author hopao
 */
public class Search extends Frame implements ActionListener{
    Button update = new Button("Update");
    Button cancel = new Button("Cancel");
    Button submit = new Button("Submit");
    Button delete = new Button("Delete");
    Button search_button = new Button("Seach");
    Button back = new Button("BACK");
//   connection work in the defult page user must connect first to search in the data  
    Book defult_page  ;
    Label error_connect = new Label("Please Connect to Server"); 
//    details 
    Label search_l = new Label("BOOK Name:");
    TextField search  = new TextField(); 
//    user can search by name or isbn the two diffrate way is availble :D 
    TextField search_isbn = new TextField() ; 
    Label search_isbn_l = new Label("ISBN:") ; 
    
//    or label 
    Label or_label = new Label("or");
    Label search_result = new Label("RESULT SEARCH");
    
    TextField[] textFields ;
    Label[] Labels ;
// mut give to this class the connection 
    
//    result search 
    // -------- Publisher Attributes --------
        Label publisherId = new Label("Publisher-ID");
        Label publisherName = new Label("Publisher:");
        Label city = new Label("City:");
        Label phone = new Label("Phone:");

        // -------- Book Attributes --------
        Label isbn = new Label("ISBN:");
        Label title = new Label("Title:");
        Label type = new Label("Type:");
        Label pageCount = new Label("Page-Count:");
        Label price = new Label("Price:");

        // -------- Author Attributes --------
        Label authorId = new Label("Author-ID:");
        Label fname = new Label("Fname:");
        Label lname = new Label("Lname:");
        
// fields for edit :D 
        
        
        
    // -------- Publisher Attributes --------
TextField publisherIdField = new TextField();
TextField publisherNameField = new TextField();
TextField cityField = new TextField();
TextField phoneField = new TextField();

// -------- Book Attributes --------
TextField isbnField = new TextField();
TextField titleField = new TextField();
TextField typeField = new TextField();
TextField pageCountField = new TextField();
TextField priceField = new TextField();

// -------- Author Attributes --------
TextField authorIdField = new TextField();
TextField fnameField = new TextField();
TextField lnameField = new TextField();




    Search( Book defult){
     this.Labels   = new Label[] {
    this.publisherId,
    this.publisherName,
    this.city,
    this.phone,
    this.isbn,
    this.title ,
    this.type,
    this.pageCount,
    this.price,
    this.authorId,
    this.fname,
    this.lname
};
     this.textFields = new TextField []  {
    this.publisherIdField,
    this.publisherNameField,
    this.cityField,
    this.phoneField,
    this.isbnField,
    this.titleField,
    this.typeField,
    this.pageCountField,
    this.priceField,
    this.authorIdField,
    this.fnameField,
    this.lnameField
};
        this.setLayout(null);
//        this.connect =  defult.conn ; work in defult page :D
        
             this.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.defult_page = defult ; 
        this.setBounds(500, 0 ,1000 , 1000);
        this.search.setBounds(210 , 70, 220 , 35);
        this.search_l.setBounds(50 , 70, 200 , 35);
        this.search_isbn_l.setBounds(520 , 70, 200 , 35);
        this.or_label.setBounds(420 , 70, 100 , 35);
        this.search_result.setBounds(380 , 300, 210 , 35);
        this.search_isbn.setBounds(730 , 70 ,200 ,35);
        this.search_button.setBounds(400 , 150, 220 , 39);
        this.search_l.setBackground(Color.YELLOW);
        this.back.setBounds(400 , 195, 220 , 39);
        this.error_connect.setBounds(380 , 245, 260 , 39);
        this.search_isbn_l.setBackground(Color.YELLOW);
        
        this.or_label.setBackground(Color.red);
        this.error_connect.setBackground(Color.red);
//        set the text labels in center :D 
        this.or_label.setAlignment(CENTER);
        this.search_isbn_l.setAlignment(CENTER);
        this.search_result.setAlignment(CENTER);
//        add objects of button and this things ;
        this.add(search) ; 
        this.add(search_button) ; 
        this.add(search_l) ; 
        this.add(this.search_isbn_l) ; 
        this.add(this.search_isbn) ; 
        this.add(this.or_label);
        this.add(this.back);
        this.add(this.search_result);
        this.add(this.error_connect);
//        fonts 
        
        this.search.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.search_l.setFont(new Font("SansSerif", Font.PLAIN, 25));
        this.search_isbn.setFont(new Font("SansSerif", Font.PLAIN, 25));
        this.search_isbn_l.setFont(new Font("SansSerif", Font.PLAIN, 25));
        this.or_label.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.back.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.search_button.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.search_result.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.error_connect.setFont(new Font("SansSerif", Font.PLAIN, 18));
//        add actions or events 
        this.back.addActionListener(this);
//       Search details
        add(publisherId);
        add(publisherName);
        add(city);
        add(phone);

        add(isbn);
        add(title);
        add(type);
        add(pageCount);
        add(price);

        add(authorId);
        add(fname);
        add(lname);
        
        setdefultbounds();
        
//        text fields  for edit 
        // -------- Publisher TextFields --------
publisherIdField.setBounds(50, 390, 290, 30);
publisherNameField.setBounds(50, 490, 290, 30);
cityField.setBounds(50, 590, 290, 30);
phoneField.setBounds(50, 670, 290, 30);

// -------- Book TextFields --------
isbnField.setBounds(450, 390, 290, 30);
titleField.setBounds(450, 490, 290, 30);
typeField.setBounds(450, 590, 290, 30);
pageCountField.setBounds(450, 670, 290, 30);
priceField.setBounds(450, 750, 290, 30); 

// -------- Author TextFields --------
authorIdField.setBounds(750, 390, 290, 30);
fnameField.setBounds(750, 495, 290, 30);
lnameField.setBounds(750, 580, 290, 30);
this.update.setBounds(680, 790, 220 , 39);
this.cancel.setBounds(680, 790, 220 , 39);
this.submit.setBounds(50, 790, 220 , 39);
this.submit.setBounds(50, 790, 220 , 39);
this.delete.setBounds(420, 790, 220 , 39);
//     add text fields we will edit in 
//this.add(publisherIdField);
//this.add(publisherNameField);
//this.add(cityField);
//this.add(phoneField);
this.add(this.update);
this.add(this.delete);
this.add(this.cancel);
this.add(this.submit);
this.add(isbnField);
this.add(titleField);
this.add(typeField);
this.add(pageCountField);
this.add(priceField);

//this.add(authorIdField);
//this.add(fnameField);
//this.add(lnameField);

                        this.submit.setVisible(false);

//        
        this.search_button.addActionListener(this);
        this.update.addActionListener(this);
        this.delete.addActionListener(this);
        this.cancel.addActionListener(this);
        this.submit.addActionListener(this);
        this.error_connect.setVisible(false);
        SmallTools.visibilty(this.textFields,false);
       this.update.setVisible(false);
       this.delete.setVisible(false);
       this.cancel.setVisible(false);
    }
    
    
    
     void showe(boolean stat){ 
        this.setVisible(stat);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String search_s = this.search.getText();
        String isbn_s = this.search_isbn.getText(); // .isEmpty()
        if(e.getSource() == this.back){
            
            this.showe(false);
            this.defult_page.showe(true);
            this.error_connect.setVisible(false);
            
        } else if(e.getSource() == this.search_button){
//            use it for redesign like defult :D
               cancel_fun_redesign();
            SmallTools.visibilty(this.textFields, false);

            this.error_connect.setVisible(false);
            if (this.defult_page.is_connect){
                SmallTools.clear(new Label[] {this.authorId,this.city,this.error_connect,this.fname,this.isbn,this.lname, this.pageCount , this.phone ,this.price , this.publisherId ,this.publisherName ,this.title , this.type });
//                check the user put the data in isbn or search field :D 
                if(((search_s.isEmpty()  ||(   search_s.length() == 0  )) &&( (isbn_s.isEmpty()  ||(   isbn_s.length() == 0  )) )) ){
                
                
                this.error_connect.setVisible(true);
                this.error_connect.setText("Please Fail The Fields ");
                this.update.setVisible(false);
                this.delete.setVisible(false);
                
                }else{
                    System.out.println(search_s.isEmpty());
                   
//                    لو لقي الداتا هيظهر ال ابديت لو لا مش هيظهرها 
                    
                if(SmallTools.get_book_data(this,new String[] {this.search.getText(), this.search_isbn.getText() }, this.defult_page.conn,this.error_connect)){
                    this.update.setVisible(true);
                    this.delete.setVisible(true);
                }else {
                    this.update.setVisible(false);
                    this.delete.setVisible(false);
                }
                }
               
                
            }else{ 
              this.update.setVisible(false);
              this.delete.setVisible(false);
//               this.error connection :D dont forgot make it 
                
         
                    
                this.error_connect.setVisible(true);
                
            } 
        
            
//            update %%%%%%%%%%%%%%%%#$#$#$#%#$%#$%#$%$#%$%#%$
        }else if(e.getSource() == this.update){
            if(this.moveto()){
//               go
                
            }else {
            return ;
            }
            SmallTools.visibilty(this.textFields, true);
//            SmallTools.visibilty(this.Labels, true);
            this.update.setVisible(false);
            this.delete.setVisible(false);
            this.cancel.setVisible(true);
            this.submit.setVisible(true);
            
            publisherId.setBounds(50, 350, 290, 30);       
publisherName.setBounds(50, 450, 290, 30);  
city.setBounds(50, 530, 290, 30);          
phone.setBounds(50, 620, 290, 30);        



isbn.setBounds(450, 350, 290, 30);         
title.setBounds(450, 450, 290, 30);            
type.setBounds(450, 530, 290, 30);            
pageCount.setBounds(450, 620, 290, 30);    
price.setBounds(450, 710, 290, 30);       


authorId.setBounds(750, 350, 290, 30);         
fname.setBounds(750, 450, 290, 30);            
lname.setBounds(750, 530, 290, 30);            
            
        } else if (e.getSource() == this.cancel){
            cancel_fun_redesign();
            this.update.setVisible(true);
            this.delete.setVisible(true);
//            (Connection connect ,String ispn, String title , String type , String price , String page_count )  
        }else if (e.getSource() == this.submit){
            
            if(SmallTools.update_fun(this.defult_page.conn,this.isbnField.getText(),this.titleField.getText(),this.typeField.getText(),this.priceField.getText(),this.pageCountField.getText() , this.isbn.getText().split(":")[1])){
           
                this.error_connect.setText("UBDATED :D");
                this.error_connect.setVisible(true);
                this.search_isbn.setText(this.isbnField.getText());
                this.search.setText(this.titleField.getText());
                SmallTools.get_book_data(this,new String[] {this.search.getText(), this.search_isbn.getText() }, this.defult_page.conn,this.error_connect);
                cancel_fun_redesign();
        }else{
                this.error_connect.setVisible(true);
                this.error_connect.setText("ERORR D: ");
            } 
            
            
                
            
        }else if (e.getSource() == this.delete){
            SmallTools.delete_data(this.defult_page.conn ,  this.isbn.getText().split(":")[1], this);
        
        }
       
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   void setdefultbounds(){ 
   
        publisherId.setBounds(50, 450, 290, 30);
        publisherName.setBounds(50, 590, 290, 30);
        city.setBounds(50, 630, 290, 30);
        phone.setBounds(50, 670, 290, 30);
        
          isbn.setBounds(450, 450, 290, 30);
        title.setBounds(450, 590, 290, 30);
        type.setBounds(450, 630, 290, 30);
        pageCount.setBounds(450, 670, 290, 30);
        price.setBounds(450, 710, 290, 30);
        
         authorId.setBounds(750, 450, 290, 30);
        fname.setBounds(750, 590, 290, 30);
        lname.setBounds(750, 630, 290, 30);
       
   } 
    
   boolean moveto(){
       try{
           System.out.println();
           
    this.publisherIdField.setText( publisherName.getText().indexOf(":") != -1? publisherName.getText().split(":")[1] : "");
    this.publisherNameField.setText(publisherName.getText().indexOf(":")!= -1 ? publisherName.getText().split(":")[1] : "");
    this.cityField.setText(city.getText().indexOf(":") != -1 ? city.getText().split(":")[1] : "");
    this.phoneField.setText(phone.getText().indexOf(":") != -1 ? phone.getText().split(":")[1] : "");
    this.isbnField.setText(isbn.getText().indexOf(":") != -1? isbn.getText().split(":")[1] : "");
    this.titleField.setText(title.getText().indexOf(":") != -1 ? title.getText().split(":")[1] : "");
    this.typeField.setText(type.getText().indexOf(":") != -1 ? type.getText().split(":")[1] : "");
    this.pageCountField.setText(pageCount.getText().indexOf(":")!= -1 ? pageCount.getText().split(":")[1] : "");
    this.priceField.setText(price.getText().indexOf(":") != -1? price.getText().split(":")[1] : "");
    this.priceField.setText(price.getText().indexOf(":") != -1? price.getText().split(":")[1] : "");
    
       }
       catch(Exception e ){
           return false;
           
       }
            
       return true;
     
   
      

        
   }
   
   void cancel_fun_redesign(){
   
                        this.submit.setVisible(false);

            this.cancel.setVisible(false);
            SmallTools.visibilty(this.textFields, false);
            SmallTools.visibilty(this.Labels, true);
        
            this.cancel.setVisible(false);
            setdefultbounds();
            this.update.setVisible(true);
            this.delete.setVisible(true);
            
   }
}
