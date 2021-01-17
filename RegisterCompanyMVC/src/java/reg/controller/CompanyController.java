/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reg.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reg.model.CompanyModel;

/**
 *
 * @author duncan
 */
@WebServlet(name = "CompanyController", urlPatterns = {"/Company.do"})
public class CompanyController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
          RequestDispatcher formPage =  request.getRequestDispatcher("/CompanyReq.jsp");
          RequestDispatcher listPage = request.getRequestDispatcher("/ListCompany.jsp");
          CompanyModel comModel = (CompanyModel) request.getAttribute("cm");
          if(comModel == null){
              comModel = new CompanyModel();
              request.setAttribute("cm", comModel);
          }
          if(comModel.applyRequestValues(request)){
              formPage.forward(request, response);
          }
          else if(comModel.validation()){
              formPage.forward(request, response);
          }
          else if(comModel.updateModelValues()){
              formPage.forward(request, response);
          }
          else if(comModel.invokeApplication()){
              formPage.forward(request, response);
          }
          else{
             listPage.forward(request, response);
          }
        }catch(Exception e){
                e.printStackTrace();
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}