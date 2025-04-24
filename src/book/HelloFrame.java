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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author hopao
 */
public class HelloFrame  extends Frame implements ActionListener{
//    this attr for the defult page i will pass to him the main data :D 
    Book defult_page;
    
    Label name = new Label() ;
    Label id  = new Label();
    Button ok = new Button("ok");
    
    HelloFrame(String name_u , String id_u ){
        this.setLayout(null);
        
         this.name.setText(name_u);
         this.id.setText(id_u);
//         set bounds 
      this.setBounds(400, 100, 400, 300);        
this.id.setBounds(50, 40, 300, 30);       
this.name.setBounds(50, 90, 300, 30);     
this.ok.setBounds(150, 150, 100, 40);      
this.name.setFont(new Font("SansSerif" , Font.ITALIC , 20));
this.id.setFont(new Font("SansSerif" , Font.ITALIC , 20));
this.ok.setFont(new Font("SansSerif" , Font.ITALIC , 20));

//         set background colors 
         this.id.setBackground(Color.red);
         this.name.setBackground(Color.red);
         
//         add 
         this.add(name);
         this.add(id);
         this.add(ok);
         this.setVisible(true );

         
         this.ok.addActionListener(this);
         
//         alignment the text
         this.name.setAlignment(CENTER);
         this.id.setAlignment(CENTER);
         
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.ok){
            this.setVisible(false);
            this.dispose();
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
