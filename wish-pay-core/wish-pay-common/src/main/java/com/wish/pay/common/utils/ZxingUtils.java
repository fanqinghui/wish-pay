package com.wish.pay.common.utils;

import com.beust.jcommander.internal.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ZxingUtils {

    static org.slf4j.Logger log = LoggerFactory.getLogger(ZxingUtils.class);

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    private static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    /**
     * 将内容contents生成长宽均为width的图片，图片路径由imgPath指定
     */
    public static File getQRCodeImge(String contents, int width, String imgPath) {
        return getQRCodeImge(contents, width, width, imgPath);
    }

    /**
     * 将内容contents生成长为width，宽为width的图片，图片路径由imgPath指定
     */
    public static File getQRCodeImge(String contents, int width, int height, String imgPath) {
        try {
            Map<EncodeHintType, Object> hints = Maps.newHashMap();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF8");

            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);

            File imageFile = new File(imgPath);
            writeToFile(bitMatrix, "png", imageFile);

            return imageFile;

        } catch (Exception e) {
            log.error("create QR code error!", e);
            return null;
        }
    }

    /**
     * 将内容contents生成长为width，宽为width的图片,返回刘文静
     */
    public static ServletOutputStream getQRCodeImge(String contents, int width, int height) throws IOException {
        ServletOutputStream stream = null;
        try {
            Map<EncodeHintType, Object> hints = Maps.newHashMap();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", stream);
            return stream;
        } catch (Exception e) {
            log.error("create QR code error!", e);
            return null;
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    /**
     * 二维码信息写成JPG BufferedImage
     *
     * @param content
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage writeInfoToJpgBuffImg(String content, int width, int height) {
        if (width < 250) {
            width = 250;
        }
        if (height < 250) {
            height = 250;
        }
        BufferedImage re = null;

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(content,
                    BarcodeFormat.QR_CODE, width, height, hints);
            re = MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return re;
    }
}