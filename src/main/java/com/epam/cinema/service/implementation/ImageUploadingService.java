package com.epam.cinema.service.implementation;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ResourceBundle;


public class ImageUploadingService {
    private static final ResourceBundle localeStorageImgBundle = ResourceBundle.getBundle("locale_storage_img");

    public static String uploadImage(Part filePart, Part[] parts) {
        String fileName = filePart.getSubmittedFileName();
        String file = localeStorageImgBundle.getString("movieImg").concat(fileName);

        try {
            for (Part part : parts) {
                part.write(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return localeStorageImgBundle.getString("movieImgRelative").concat(fileName);
    }
}
