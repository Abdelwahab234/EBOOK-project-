/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book;

import java.awt.Label;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hopao
 */
public class SmallTools {
//    if you want store it 
        static  Connection conn ; 
    

    
    
    // الميثود دي هترجعلي اخر 
        //id 
        // زياده واحد كمان و لو مفيش هترجعلك 
        // 0+1
//    هتاخد منك الكونيكت و اسم التيبلي و الكي العاوز اخر حاجه فيه :]
        static String last_id(Connection connect , String key , String table  ) {
     
        try {
//            QUERY EXECUTE :D
            ResultSet result = connect.createStatement().executeQuery( String.format("Select %s from %s ORDER BY %s DESC" , key , table ,key ));
                if (result.next()){ 
//                   convert the int to string D: ; 
          return String.valueOf(Integer.parseInt(result.getString(key)) + 1);
                    
                }else{
                    return "1" ;
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("there was ann error in last id method ");
            return "1" ;
        }
        
//            System.out.println("there was ann error in last id method ");
        /*      return 1 ;*/
    
    }
        
        
        // insert book  and aouther and the join relation data  data 
static int insert_data (Connection connect , String title , String type , String price , String page_count , String fname ,String lname  , String isbn , String publisher_id){
    ResultSet result;
           String id ;
//         بعدل علي السترنج عشان بيطلع  
//           id : 5 
//           فبحاول اطلع الرقم  
//           و اخذنو جوا بارت 
           
           
           
              int  part =Integer.parseInt(publisher_id.split(":")[1].trim()) ;      // بيقسم على أساس ":"
            try {
 
                System.out.println(publisher_id);
              connect.createStatement().executeUpdate(String.format("insert into BOOK Values('%s','%s','%s' ,'%s' ,'%s','%s')",isbn,title ,type , page_count, price,part));
//           author_id function 
//           this function add the author to the table and if not found not dublicate it 
           
           id = SmallTools.Author_id(fname.toLowerCase().replace(" ",""),lname.toLowerCase().replace(" ","") , connect);
           connect.createStatement().executeUpdate(String.format("insert into BOOK_Author Values('%s','%s')",isbn,id));
            } catch (SQLException ex) {
                Logger.getLogger(SmallTools.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    
return 1; 
}

static String Author_id(String fname , String lname ,Connection connect){
//    الفونكشن دي بتضيف ال مؤلف لو مش موجود فلداتا بيز لو موجود خلاص هترجعلك ال 
    // id بتاعو
    ResultSet result ;
            try {
                result= connect.createStatement().executeQuery(String.format("select * from Author where Fname = '%s' ",fname));
                if (result.next()){
                    return result.getString("Author_ID");
                    
                }else{
//                    هنا ملقاش فهيرجعلك id 
//                    جديد ليه
                    String newid= SmallTools.last_id(connect, "Author_ID", "Author");
                    connect.createStatement().executeUpdate(String.format("insert into Author Values('%s' ,'%s' , '%s' )",newid,fname,lname));
                    return newid;
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(SmallTools.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
}
    

static  void clear(TextField[] inputs ){
    
    for(int i = 0 ; i < inputs.length ; i++)inputs[i].setText("");
    
}
        
static  void clear(Label [] inputs ){
    
    for(int i = 0 ; i < inputs.length ; i++)inputs[i].setText("");
    
}
        
        

//search tool for search.java 
// this function control directly in search object :D 

static boolean get_book_data(Search page , String[] data , Connection connect , Label stat){
    ResultSet result_author , result_book , result_publisher ;
    ResultSet Author_id = null;
    System.out.println("im here pro");
            try {
                
                 result_book = connect.createStatement().executeQuery(String.format("select * from BOOK where ISBN = LOWER(REPLACE('%s' , ' ' , ''))  OR  REPLACE(LOWER(Title) ,' ','') = LOWER(REPLACE('%s' , ' ' , '')) ",data[1] , data[0]));
//                if return true this mean the book was found :D 
                if(result_book.next()){
                  Author_id = connect.createStatement().executeQuery(String.format("select Author_ID from BOOK_Author where REPLACE(LOWER(ISBN),' ', '') = REPLACE(LOWER('%s'), ' ' , '' ) ", result_book.getString("ISBN")));
                  Author_id.next();
                
                  System.out.println(String.format("ISBN",Author_id.getString("Author_ID")));
                  
                  
                  result_author = connect.createStatement().executeQuery(String.format("select * from Author where REPLACE(LOWER(Author_ID),' ' , '' ) = REPLACE(LOWER('%s'),' ' , '' )",Author_id.getString("Author_ID")));
                 
                 result_author.next();
                  
                 result_publisher = connect.createStatement().executeQuery(String.format("select * from Publisher where REPLACE(LOWER(Publisher_ID),' ', '') = REPLACE(LOWER('%s'), ' ' , '' ) ", result_book.getString("Publisher_ID")));
                        result_publisher.next();
//                وزع الداتا بقي 
                 
//                 author
                page.authorId.setText("Author ID: "+result_author.getString("Author_ID"));
                page.fname.setText("Fname: "+result_author.getString("Fname"));
                page.lname.setText("Lname: "+result_author.getString("Lname"));
//                publisher 
                page.publisherId.setText("Publisher-ID: "+result_publisher.getString("Publisher_ID"));
                page.publisherName.setText("Publisher: "+result_publisher.getString("Name"));
                page.city.setText("City: "+result_publisher.getString("City"));
                page.phone.setText("Phone: "+result_publisher.getString("Phone"));
//                BOOK
                page.isbn.setText("ISBN: "+result_book.getString("ISBN"));
                page.title.setText("Title: "+result_book.getString("Title"));
                page.type.setText("Type: "+result_book.getString("Type"));
                page.pageCount.setText("Page_Count: "+result_book.getString("Page_Count"));
                page.price.setText("Price: "+result_book.getString("Price"));
                
                return true ; 
                }else{ 
                stat.setText(String.format("NOT FOUND %s  %s" ,data[0] , data[1] ));
                stat.setVisible(true);
                return false ; 
                }
            
            
            } catch (SQLException ex) {
                Logger.getLogger(SmallTools.class.getName()).log(Level.SEVERE, null, ex);
                return false ; 
            }
        
    
    } 
        
        
//          change with the fields and labels in search :D 

    static void visibilty(Label[] stat,   boolean  set){ 
             for(int i =0 ; i <  stat.length ; i++)stat[i].setVisible(set);

    }
     static void visibilty(TextField[] stat , boolean set){ 
         for(int i =0 ; i <  stat.length ; i++)stat[i].setVisible(set);
    }
   
        
        
        
 public  static boolean update_fun (Connection connect ,String ispn, String title , String type , String price , String page_count , String ispn_real )     {
       Statement stat;
       String g = String.format("update BOOK set ISBN = replace('%s' , ' ' , '') , title = replace('%s' , ' ' , '')   , type = replace('%s' , ' ' , '') , price  = replace('%s' , ' ' , '') , page_count = replace('%s' , ' ' , '') where Replace(ISBN , ' ', '')=  Replace('%s' , ' ', '') ",ispn, title , type , price , page_count , ispn_real );
          String b = String.format("update BOOK_Author set ISBN = replace('%s',' ' ,'') where replace(ISBN ,' ', '') = replace('%s' , ' ' , '')",ispn,ispn_real);
            System.out.println(b);
           try {
                stat = connect.createStatement();
// close the securty        
       stat.executeUpdate(String.format("ALTER TABLE BOOK_Author NOCHECK CONSTRAINT FK__BOOK_Autho__ISBN__0DAF0CB0"));
       System.out.println(g);
       stat.executeUpdate(g);
       stat.executeUpdate(b);
       
       System.out.println(String.format(" :>>  %s",b));
// open the securty        
       stat.executeUpdate(String.format("ALTER TABLE BOOK_Author CHECK CONSTRAINT FK__BOOK_Autho__ISBN__0DAF0CB0"));
           return true ; 
            } catch (SQLException ex) {
                Logger.getLogger(SmallTools.class.getName()).log(Level.SEVERE, null, ex);
           return false ; 
            }
//           return true ; 
       
   }
 public static boolean delete_data(Connection conn,String ispn , Search defult){
            try {
                Statement stat =  conn.createStatement();
//                close  the secure
                      stat.executeUpdate(String.format("ALTER TABLE BOOK_Author NOCHECK CONSTRAINT FK__BOOK_Autho__ISBN__0DAF0CB0"));

                stat.executeUpdate(String.format("DELETE from BOOK where REPLACE(ISBN, ' ' ,'') = REPLACE('%s', ' ' ,'') ", ispn));
                stat.executeUpdate(String.format("DELETE from BOOK_Author where REPLACE(ISBN, ' ' ,'')  = REPLACE('%s', ' ' ,'') ", ispn));
//                open secure
                                      stat.executeUpdate(String.format("ALTER TABLE BOOK_Author NOCHECK CONSTRAINT FK__BOOK_Autho__ISBN__0DAF0CB0"));
                                      defult.cancel_fun_redesign();
                                      SmallTools.clear(new Label[] {defult.authorId,defult.city,defult.error_connect,defult.fname,defult.isbn,defult.lname, defult.pageCount , defult.phone ,defult.price , defult.publisherId ,defult.publisherName ,defult.title , defult.type });
                                      defult.update.setVisible(false);
                                      defult.delete.setVisible(false);
                                      return true ; 
            } catch (SQLException ex) {
                Logger.getLogger(SmallTools.class.getName()).log(Level.SEVERE, null, ex);
                return false ; 
            }
            
 }
        
    
}
