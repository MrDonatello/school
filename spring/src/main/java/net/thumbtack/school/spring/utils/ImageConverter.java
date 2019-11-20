package net.thumbtack.school.spring.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageConverter {

    public Image convert(Image image) {
        return image.getScaledInstance(100, 100, BufferedImage.SCALE_SMOOTH);
    }

}
