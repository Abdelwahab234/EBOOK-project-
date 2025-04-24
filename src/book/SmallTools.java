/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book;

import java.awt.TextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    

static  void clear(TextField[] inputs){
    
    for(int i = 0 ; i < inputs.length ; i++)inputs[i].setText("");
    
}
        
        

//search tool for search.java 
// this function control directly in search object :D 

static void get_book_data(Search page , String[] data , Connection connect){
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
                
                }
            
            
            } catch (SQLException ex) {
                Logger.getLogger(SmallTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    
    } 
        
        
        
        
        
        
        
        
    
}
