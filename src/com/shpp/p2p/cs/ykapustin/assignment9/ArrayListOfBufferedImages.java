package com.shpp.p2p.cs.ykapustin.assignment9;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayListOfBufferedImages implements Iterable<BufferedImage>, Iterator<BufferedImage> {
    private BufferedImage[] images;
    private int count = 0;

    public ArrayListOfBufferedImages() {
        images = new BufferedImage[0];
    }

    public BufferedImage get(int element) {
        return images[element];
    }

    public void add(BufferedImage image) {
        BufferedImage[] temp = new BufferedImage[images.length + 1];
        for (int i = 0; i < images.length; i++) {
            temp[i] = images[i];
        }
        temp[temp.length - 1] = image;
        images = temp;
    }

    public int size() {
        return images.length;
    }

    @Override
    public Iterator<BufferedImage> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return count < images.length - 1;
    }

    @Override
    public BufferedImage next() {
        if (count < images.length) {
            ++count;
            return images[count];
        } else {
            throw new NoSuchElementException();
        }
    }
}
