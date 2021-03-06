/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domain.Books;
import domain.GenericDao;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author duncan
 */
public class BookModel {
    private String isbnInput="";
    private String titleInp = "";
    private String titleMsg = "";
    private String isbnMsg="";
    private String authorsInput="";
    private String authorsMsg = "";
    private String pubYearInp = "";
    private LocalDate pubYearValue;
    private String pubYearMsg = "";
    private String cateInp ="";
    private String purPriceInp ="";
    private int purPriceValue;
    private String purPriceMsg ="";
    private boolean error = false;
    
    private Books book = new Books();
    
    public boolean applyRequestValue(HttpServletRequest request){
        System.err.println("here");
        isbnInput =  request.getParameter("regNo");
        titleInp = request.getParameter("title");
        authorsInput = request.getParameter("author");
        System.err.println("here1");
        pubYearInp = request.getParameter("pubDate");
          try{
              System.err.println("here1"+ pubYearInp);
            pubYearValue = LocalDate.parse(pubYearInp);
             System.err.println("here2"+ pubYearValue);
        }catch(Exception e){
            System.err.println("con date");
            pubYearMsg = "it must be a date";
            error = true;
        }
          System.err.println("here2");
        purPriceInp =  request.getParameter("purchaseDate");
        try{
            purPriceValue = Integer.parseInt(purPriceInp);
        }catch(Exception e){
            pubYearInp = "it must be a number purchase value";
            error = true;
        }
         System.err.println("end conversion");
        return error;
    }
    
    public boolean processValidations(){
//         System.err.println("Begin validation");
//        String regex = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(isbnInput);
//        if(matcher.matches()){
//            isbnMsg= "The format of isbn must be 978-1-395434-43-2"+ matcher;
//            error = true;
//        } 
        if(titleInp.length()< 5){
            titleMsg = "the title is very short";
            error =  true;
        }
        if(authorsInput.length()<4){
            authorsMsg = "the author name must be atleast 4 characters";
            error = true;
        }
        int year = pubYearValue.getYear();
        int dif = LocalDate.now().getYear() - year;
        if(dif > 10){
            pubYearMsg = "Recorded books must not exceed 10 years of publication";
            error =  true;
        }
        if(purPriceValue >= 1000 || purPriceValue <= 100000){
            purPriceMsg = "You must be between 1000  and 100,000";
        error = true;
    }
        return error;
    }
    
    public boolean updateModelValues(){
       try{
           System.out.println("update");
        book.setISBN(isbnInput);
        book.setTitle(titleInp);
        book.setAuthors(authorsInput);
        book.setCategory(cateInp);
        book.setPublicationYears(pubYearValue);
        book.setRecordingDate(LocalDate.now());
        book.setPurchasingPrice(purPriceValue);
        if(cateInp == "Education"){
            int sel = (int) (purPriceValue * 0.5);
           book.setSellingPrice(sel);
        }
                }
       catch(Exception e){
       error = true;
       }
       return error;
    }
    public boolean invoke(){
         try {
            GenericDao<Books> compDao = new GenericDao<Books>(Books.class);
            compDao.create(book);
        } catch (Exception e) {
            error = true;
        }
        return error;
    }

    public String getIsbnInput() {
        return isbnInput;
    }

    public void setIsbnInput(String isbnInput) {
        this.isbnInput = isbnInput;
    }


    public String getTitleInp() {
        return titleInp;
    }

    public void setTitleInp(String titleInp) {
        this.titleInp = titleInp;
    }

    public String getTitleMsg() {
        return titleMsg;
    }

    public void setTitleMsg(String titleMsg) {
        this.titleMsg = titleMsg;
    }

    public String getIsbnMsg() {
        return isbnMsg;
    }

    public void setIsbnMsg(String isbnMsg) {
        this.isbnMsg = isbnMsg;
    }

    public String getAuthorsInput() {
        return authorsInput;
    }

    public void setAuthorsInput(String authorsInput) {
        this.authorsInput = authorsInput;
    }

    public String getAuthorsMsg() {
        return authorsMsg;
    }

    public void setAuthorsMsg(String authorsMsg) {
        this.authorsMsg = authorsMsg;
    }

    public String getPubYearInp() {
        return pubYearInp;
    }

    public void setPubYearInp(String pubYearInp) {
        this.pubYearInp = pubYearInp;
    }

    public LocalDate getPubYearValue() {
        return pubYearValue;
    }

    public void setPubYearValue(LocalDate pubYearValue) {
        this.pubYearValue = pubYearValue;
    }

    public String getPubYearMsg() {
        return pubYearMsg;
    }

    public void setPubYearMsg(String pubYearMsg) {
        this.pubYearMsg = pubYearMsg;
    }

    public String getCateInp() {
        return cateInp;
    }

    public void setCateInp(String cateInp) {
        this.cateInp = cateInp;
    }

    public String getPurPriceInp() {
        return purPriceInp;
    }

    public void setPurPriceInp(String purPriceInp) {
        this.purPriceInp = purPriceInp;
    }

    public int getPurPriceValue() {
        return purPriceValue;
    }

    public void setPurPriceValue(int purPriceValue) {
        this.purPriceValue = purPriceValue;
    }

    public String getPurPriceMsg() {
        return purPriceMsg;
    }

    public void setPurPriceMsg(String purPriceMsg) {
        this.purPriceMsg = purPriceMsg;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }
    
}
