package edu.cqu.no1.zl.controller;

import edu.cqu.no1.zl.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zl on 2016/5/1.
 */
@Controller
@RequestMapping("/rest")
public class Image {

    @Resource
    private ImageService imageService;

    public ImageService getImageService() {
        return imageService;
    }

    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }


    @RequestMapping(value = "/image/cut/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void getCut(@PathVariable String id, Integer centerX, Integer centerY,
                       Integer offX, Integer offY,
                       HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("image/*");
        String uploadPath = req.getRealPath("/") + "images/";
        try {
            OutputStream os = res.getOutputStream();
            BufferedImage cutImage = imageService.getPositionedImage(id, centerX, centerY, offX, offY, uploadPath);
            BufferedImage resizedImage = imageService.resizeImage(cutImage, 160, 160);
            imageService.writeImageToOutputStream(resizedImage, os);

            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/image/resize/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void getResize(@PathVariable String id, HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("image/*");
        String uploadPath = req.getRealPath("/") + "images/";
        try {
            OutputStream os = res.getOutputStream();
            BufferedImage image = imageService.getImage(id, uploadPath);
            BufferedImage resizedImage = imageService.resizeImage(image, 160, 160);
            imageService.writeImageToOutputStream(resizedImage, os);

            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void get(@PathVariable String id, HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("image/*");
        String uploadPath = req.getRealPath("/") + "images/";
        try {
            OutputStream os = res.getOutputStream();
            BufferedImage image = imageService.getImage(id, uploadPath);
            imageService.writeImageToOutputStream(image, os);

            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping(value = "/image/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable String id){
        imageService.remove(id);
    }

}
