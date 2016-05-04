package edu.cqu.no1.zl.service;

import edu.cqu.no1.zl.dao.ImageDao;
import edu.cqu.no1.zl.domain.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by zl on 2016/4/30.
 */
@Service
public class ImageService {
    @Resource
    private ImageDao imageDao;

    public ImageDao getImageDao() {
        return imageDao;
    }

    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }


    public Image addImage(MultipartFile file, String uploadPath) throws IOException {
        String fileName = UUID.randomUUID().toString();
        FileOutputStream fos = new FileOutputStream(uploadPath + "/" + fileName);
        fos.write(file.getBytes());
        fos.flush();
        fos.close();
        Image image = new Image(fileName);

        imageDao.save(image);
        return image;
    }

    public BufferedImage getImage(String id, String imagesPath) {
        Image image = imageDao.get(Image.class, id);
        if (image == null) {
            return null;
        }
        String path = imagesPath + "/" + image.getFileName();
        Iterator iterator = null;
        try {
            iterator = ImageIO.getImageReaders(new FileImageInputStream(new File(path)));
            ImageReader reader = (ImageReader) iterator.next();
            InputStream in = new FileInputStream(path);
            ImageInputStream iis = ImageIO.createImageInputStream(in);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            return reader.read(0, param);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public BufferedImage getPositionedImage(String id, Integer centerX, Integer centerY,
                                            Integer offX, Integer offY, String imagesPath) {
        Image image = imageDao.get(Image.class, id);
        if (image == null) {
            return null;
        }
        String path = imagesPath + "/" + image.getFileName();

        Iterator iterator = null;
        try {
            iterator = ImageIO.getImageReaders(new FileImageInputStream(new File(path)));
            ImageReader reader = (ImageReader) iterator.next();
            InputStream in = new FileInputStream(path);
            ImageInputStream iis = ImageIO.createImageInputStream(in);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rect = new Rectangle(centerX - offX > 0 ? centerX - offX : 0,
                    centerY - offY > 0 ? centerY - offY : 0, 2 * offX, 2 * offY);
            param.setSourceRegion(rect);
            return reader.read(0, param);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public BufferedImage resizeImage(BufferedImage srcImg, Integer maxWidth, Integer maxHeight) {

        Integer tagWidth, tagHeight;
        Integer srcImgWidth = srcImg.getWidth();
        Integer srcImgHeight = srcImg.getHeight();

        float srcImgRatio = srcImgWidth / (srcImgHeight + 0.0f);

        float maxRatio = maxWidth / (maxHeight + 0.0f);


        if (srcImgRatio > maxRatio) {
            tagWidth = maxWidth;
            tagHeight = (int) (tagWidth / srcImgRatio);
        } else {
            tagHeight = maxHeight;
            tagWidth = (int) (tagHeight * srcImgRatio);
        }

        BufferedImage buffImg = new BufferedImage(tagWidth, tagHeight, BufferedImage.TYPE_INT_ARGB);
        buffImg.getGraphics().drawImage(
                srcImg.getScaledInstance(tagWidth, tagHeight, BufferedImage.SCALE_SMOOTH), 0,
                0, null);
        return buffImg;

    }

    public void writeImageToOutputStream(BufferedImage input, OutputStream out) throws IOException {
        ImageIO.write(input, "png", out);
    }

    public List<Image> getAllImage() {

        List<Image> images = imageDao.findAll(Image.class);

        Collections.sort(images, new Comparator<Image>() {
            public int compare(Image arg0, Image arg1) {
                return arg0.getSubjectsSize().compareTo(arg1.getSubjectsSize());
            }
        });

        return images;

    }

    public void remove(String id) {
        imageDao.delete(Image.class, id);
    }
}
