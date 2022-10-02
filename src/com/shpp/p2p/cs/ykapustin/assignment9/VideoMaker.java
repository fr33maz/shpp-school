package com.shpp.p2p.cs.ykapustin.assignment9;


import org.jcodec.api.awt.AWTSequenceEncoder;
import org.jcodec.common.io.FileChannelWrapper;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Rational;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VideoMaker {

    public static void main(String[] args) throws IOException {
        FileChannelWrapper
                out = NIOUtils.writableFileChannel("C:\\Users\\monte\\shpp\\folderForVideo\\video.mp4");
        ArrayListOfBufferedImages images = fillArrayList();
        AWTSequenceEncoder encoder = new AWTSequenceEncoder(out, Rational.R(50, 1));
        for (Image image : images) {
            BufferedImage scaledImage = scaledImage(image);
            encoder.encodeImage(scaledImage);
        }
        encoder.finish();
    }

    private static BufferedImage scaledImage(Image image) {
        Image scaled = image.getScaledInstance(760, 480, Image.SCALE_SMOOTH);
        BufferedImage bf = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bf.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return bf;
    }

    private static ArrayListOfBufferedImages fillArrayList(){
        ArrayListOfBufferedImages images = new ArrayListOfBufferedImages();
        int number = 0;
        while (true) {
            String path = "C:\\Users\\monte\\shpp\\snapshots\\";
            number++;
            path += number + ".jpeg";
            try {
                BufferedImage image = ImageIO.read(new File(path));
                images.add(image);
            } catch (IOException e) {
                break;
            }
        }
        return images;
    }
}
