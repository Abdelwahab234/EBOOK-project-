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
    
    Button search_button = new Button("Seach");
    Button back = new Button("BACK");
//   connection work in the defult page user must connect first to search in the data  
    Book defult_page  ;
    
//    details 
    Label search_l = new Label("BOOK Name:");
    TextField search  = new TextField(); 
//    user can search by name or isbn the two diffrate way is availble :D 
    TextField search_isbn = new TextField() ; 
    Label search_isbn_l = new Label("ISBN:") ; 
    
//    or label 
    Label or_label = new Label("or");
    Label search_result = new Label("RESULT SEARCH");
    
    
// must give to this class the connection 
    
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
    
    Search( Book defult){
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
        this.search_isbn_l.setBackground(Color.YELLOW);
        
        this.or_label.setBackground(Color.red);
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
//        fonts 
        
        this.search.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.search_l.setFont(new Font("SansSerif", Font.PLAIN, 25));
        this.search_isbn.setFont(new Font("SansSerif", Font.PLAIN, 25));
        this.search_isbn_l.setFont(new Font("SansSerif", Font.PLAIN, 25));
        this.or_label.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.back.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.search_button.setFont(new Font("SansSerif", Font.PLAIN, 20));
        this.search_result.setFont(new Font("SansSerif", Font.PLAIN, 20));
        
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
        
        
        publisherId.setBounds(50, 550, 290, 30);
        publisherName.setBounds(50, 590, 290, 30);
        city.setBounds(50, 630, 290, 30);
        phone.setBounds(50, 670, 290, 30);
        
                isbn.setBounds(450, 550, 290, 30);
        title.setBounds(450, 590, 290, 30);
        type.setBounds(450, 630, 290, 30);
        pageCount.setBounds(450, 670, 290, 30);
        price.setBounds(450, 710, 290, 30);
        
         authorId.setBounds(750, 550, 290, 30);
        fname.setBounds(750, 590, 290, 30);
        lname.setBounds(750, 630, 290, 30);

//        
        this.search_button.addActionListener(this);
    }
    
    
    
     void showe(boolean stat){ 
        this.setVisible(stat);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.back){
            
            this.showe(false);
            this.defult_page.showe(true);
            
        } else if(e.getSource() == this.search_button){
            if (this.defult_page.is_connect){
//                check the user put the data in isbn or search field :D 
                if((this.search.getText().isEmpty() || this.search_isbn.getText().isEmpty())){
                
                SmallTools.get_book_data(this,new String[] {this.search.getText(), this.search_isbn.getText() }, this.defult_page.conn);
                
                }
                
            }else{ 
            
//               this.error connection :D dont forgot make it 
                
                
            } 
        
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
