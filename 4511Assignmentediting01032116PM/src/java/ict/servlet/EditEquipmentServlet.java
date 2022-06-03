/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.EquipmentBean;
import ict.bean.EquipmentTypeBean;
import ict.db.EquipmentDB;
import ict.tag.ListEquipmentSelectionTag;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JKc
 */
@WebServlet(name = "EditEquipmentServlet", urlPatterns = {"/EditEquipmentServlet"})
public class EditEquipmentServlet extends HttpServlet {
    EquipmentDB db;
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new EquipmentDB(dbUrl, dbUser, dbPassword);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String id = request.getParameter("equipmentID");
        EquipmentBean editingEquipment = db.getEquipment(id);
        
        out.print("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "    <head>\n" +
                    "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                    "        <title>Add Equipment Result</title>\n" +
                    "        <script src=\"jquery/jquery-1.6.js\"></script>\n"+
                    "        <script>\n"+
                    "           $(document).ready(function(){\n"+
                    "           var states = \""+editingEquipment.getStates()+"\";\n"+
                    "           if(states == \"A\"){$(\"#available\").attr('checked', true);}\n"+
                    "           else if(states == \"N\"){$(\"#disable\").attr('checked', true);}\n"+
                    "               $('input[type=\"submit\"]').click(function(){\n" +
                    "                    $(\"#movement\").val($(this).attr('name'));\n" +
                    "                    if($(\"#movement\").val() == 'Edit'){"+
                    "                       var errorMessage = \"\";\n"+
                    "                       if($(\"#newName\").val() == ''){\n" +
                    "                           errorMessage += (\"Name can't be null\"); \n"+
                    "                       }\n"+
                    "                       if($(\"#newType\").val() == ''){\n" +
                    "                           errorMessage += (\"\\nType can't be null\"); \n"+
                    "                       }\n"+
                    "                       if($(\"#newRantDateLimit\").val() == ''){\n" +
                    "                           errorMessage += (\"\\nRant day Limit can't be null\"); \n"+
                    "                       }\n"+
                    "                       if(!$(\"input:radio[name='states']:checked\").val()) { errorMessage+= \"\\nStates can't be null\"; }"+
                    "                       if(errorMessage == \"\"){\n" +
                    "                           var answer = window.confirm(\"Save data?\\n"+
                                                "ID: \"+$(\"#equipmentID\").val() +\"\\nName: \"+$(\"#oldName\").val()"+   
                                                "+\"->\"+$(\"#newName\").val()+\"\\nType: \" +$(\"#newType option[value='"+editingEquipment.getType()+"']\").text()+\"->\"+$(\"#newType option:selected\").text()+ "+
                                                "\"\\nDetail: \"+$(\"#oldDetail\").val()+\"->\"+$(\"#newDetail\").val()"+
                                                "+\"\\nRant day limit: \"+$(\"#oldRantDateLimit\").val()+\"->\"+$(\"#newRantDateLimit\").val()"+
                    "                           );" +
                    "                           if (answer) {\n" +
                    "                               $(\"#editForm\").submit(); "+
                    "                           }\n"+
                    "                           else if(!answer){\n" +
                    "                               return false;\n" +
                    "                           }\n"+

                    
                    "                       }else{\n"+
                    "                           alert(errorMessage);\n"+
                    "                           return false;\n"+
                    "                       }\n"+
                    "                    }else if($(\"#movement\").val() == 'Delete'){\n"+
                    "                       var answer = window.confirm(\"Comfilm delete?\");"+
                    "                       if(answer){"+
                    "                           $(\"#editForm\").submit();"+
                    "                       }else if(!answer){"+
                    "                           return false;"+
                    "                       }"+
                    "                    }\n"+
                    "                });\n"+
                    "           });\n"+
                    "        </script>\n"+
                    "    </head>\n" +
                    "    <body>\n");
        
        //old data
        out.print("<form id='editForm' action='PrintEditedEquipmentResult' method='POST'>\n");
        out.print("<input type='hidden' name='equipmentID' id='equipmentID' value='"+editingEquipment.getId()+"'>");
        out.print("<input type='hidden' name='oldName' id='oldName' value='"+editingEquipment.getName()+"'></input>\n");
        out.print("<input type='hidden' name='oldDetail' id='oldDetail'value='"+editingEquipment.getDetail()+"'></input>\n");
        out.print("<input type='hidden' name='oldRantDayLimit' id='oldRantDateLimit' value='"+editingEquipment.getRantDateLimit()+"'></input>\n");
        out.print("<input type='hidden' name='movement' id='movement' value='NULL'>\n");
        
        //new data
        out.print("<label >Equipment ID: "+editingEquipment.getId()+"</label><br/>\n");
        out.print("<label for='newName'>Name: </label><input name='newName' id='newName' value='"+editingEquipment.getName()+"'><br/>\n");
        out.print("<label for='newType'>Type: </label>"+getTypeSelection(editingEquipment.getType())+"<br/>\n");
        out.print("<label for='newDetail' >Detail: </label><textarea name='newDetail' id='newDetail' value='"+editingEquipment.getDetail()+"'></textarea><br/>\n");
        out.print("<label>States: <label><input type='radio' name='states'id='available' value='A'><label for='available'>Available</label><input type='radio' name='states' id='disable' value='N'><label for='disable'>Disable</label><br/>");
        out.print("<label for='newRantDateLimit'>Rant day limit: </label><input type='number' min='1' id='newRantDateLimit' name='newRantDateLimit' value='"+editingEquipment.getRantDateLimit()+"'><br/>\n");
        out.print("<input type='submit' name='Edit' value='Edit'>\n");
        out.print("<input type='submit' name='Delete' value='Delete'>\n");
        out.print("</form></body></html>");
    }

    public String getTypeSelection(String defaultID){
        init();
        ArrayList<EquipmentTypeBean> list = db.getType();
        String htmlCode = "";
        htmlCode += ("<select name='newType' id='newType'>");
        htmlCode += ("<option value=''>Select Type</option>");
        EquipmentTypeBean currentType ;
        for(int count = 0; count <list.size();count ++){
            currentType = list.get(count);
            String name = currentType.getName();
            String id = currentType.getId();
            htmlCode += ("<option value='"+id+"'");
            if(id.equals(defaultID)){
                htmlCode += ("selected='selected'");
            }
            htmlCode += (">"+name+"</option>");
        }
        htmlCode += ("</select>");
        return htmlCode;
    }
    @Override
    public String getServletInfo() {
        return "Edit Equipment Servlet";
    }// </editor-fold>
}
