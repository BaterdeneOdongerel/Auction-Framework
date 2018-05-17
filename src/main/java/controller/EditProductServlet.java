package controller;


import model.user.Category;
import model.user.Product;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.CategoryServiceImp;
import service.ProductServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@WebServlet("/edit_product")
public class EditProductServlet extends BaseServlet {

    protected void post(HttpServletRequest request, HttpServletResponse response) throws Exception {

        FileItemFactory factory = new DiskFileItemFactory();

        ServletFileUpload upload = new ServletFileUpload( factory );

        List<FileItem> uploadItems = upload.parseRequest( request );
        File uploadedFile;
        String dirPath = new File(getServletContext().getRealPath("/")).getAbsolutePath() + "\\public\\images";

        Calendar calendar = Calendar.getInstance();

        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        String preFileName = "" + hours + "" + minutes + "" + seconds;
        String latestFileName = "";


        // update or create
        String sid = request.getParameter("id");
        String name = request.getParameter("pname");
        String desc = request.getParameter("desc");
        String catid = request.getParameter("catid");


        for( FileItem uploadItem : uploadItems )
        {

            if( uploadItem.isFormField() )
            {
                String fieldName = uploadItem.getFieldName();
                String value = uploadItem.getString();
                System.out.println("---->" + fieldName + " " + value);
                if ( fieldName.equals("id") ) {
                    sid = value;
                }
                if ( fieldName.equals("pname") ) {
                    name = value;
                }
                if ( fieldName.equals("desc") ) {
                    desc = value;
                }
                if ( fieldName.equals("catid") ) {
                    catid = value;
                }


            } else {

                String fileNameWithExt = uploadItem.getName();

                File filePath = new File(dirPath);

                if ( !filePath.exists() ) {
                    filePath.mkdirs();
                }
                latestFileName = dirPath + "\\" + preFileName + fileNameWithExt;
                uploadedFile = new File(latestFileName);
                uploadItem.write(uploadedFile);
                latestFileName = "public\\images" + "\\" + preFileName + fileNameWithExt;
            }
        }



        System.out.println("=========>" + latestFileName + "===");

        ProductServiceImp productServiceImp = new ProductServiceImp();




        Product product = new Product();
        product.setName(name);
        product.setDesc(desc);
        product.setCatid(Integer.parseInt(catid));

        if ( sid == null || "-1".equals(sid) ) { // create
            System.out.println("HERHEHRHERHERHERHER");
            product.setImagePath(latestFileName);
            productServiceImp.create(product);
            System.out.println("CCCCCCCCCCCCCCCCCCc");
        } else { // update
            System.out.println("WWWWWWWWWWWWWWWWWWW");
            if ( latestFileName.length() > 1 ) {
                product.setImagePath(latestFileName);
            } else {
                Product p = productServiceImp.selectById(Integer.parseInt(sid));
                product.setImagePath(p.getImagePath());
            }

            productServiceImp.update(product, Integer.parseInt(sid));
        }

        response.sendRedirect("/list_product?catid=" + catid);


    }

    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductServiceImp productServiceImp = new ProductServiceImp();
        String delete_id = request.getParameter("delete_id");
        String catid = request.getParameter("catid");

        if ( delete_id != null ) { // delete
            Product p = productServiceImp.selectById(Integer.parseInt(delete_id));
            productServiceImp.delete( Integer.parseInt(delete_id) );
            if ( catid == null )
                catid = "" + p.getCatid();
            response.sendRedirect("/list_product?catid=" + catid);

        } else {

            String sid = request.getParameter("id");

            int id = -1;
            Product product = new Product();

            if ( sid != null ) {
                id = Integer.parseInt(sid);
                product = productServiceImp.selectById(id);
            } else {
                product.setCatid(Integer.parseInt(catid));
            }

            product.setId(id);
            request.setAttribute("product", product);

            RequestDispatcher view = request.getRequestDispatcher("edit_product.jsp");
            view.forward(request, response);
        }

    }

}

