package com.epam.cinema.service.implementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ResourceBundle;


public class ImageUploadingService {
    public static String uploadImage(HttpServletRequest request, String elementName) {
        Part filePart = null;
        String fileName = ResourceBundle.getBundle("locale_storage_img").getString("movieImg");
        try {
            filePart = request.getPart(elementName);
            fileName += filePart.getSubmittedFileName();
            for (Part part : request.getParts()) {
                part.write(fileName);
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
        return ResourceBundle.getBundle("locale_storage_img").getString("movieImgRelative") + filePart.getSubmittedFileName();
    }
}
