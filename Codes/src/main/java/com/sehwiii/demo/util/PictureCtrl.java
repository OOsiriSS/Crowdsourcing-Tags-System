package com.sehwiii.demo.util;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * 复制图片
 *
 * @author 161250078
 */
public class PictureCtrl {

    public static void copyImage(String sourcePath, String targetPath) {
        try {
            File input = new File(sourcePath);
            BufferedImage bim = ImageIO.read(input);
            File output = new File(targetPath);
            ImageIO.write(bim, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getImgData(String path) {
        BASE64Encoder encoder = new BASE64Encoder();
        File file = new File(path);
        String imgData = "";
        try {
            BufferedImage bi = ImageIO.read(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            imgData = encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgData;
    }

}

