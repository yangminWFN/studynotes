package ImageToArrayTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

public class ImageToArrayTest {
    public static BufferedImage ReadImgToArray(String path) {
        try {
            File file = new File(path);
            BufferedImage bim = ImageIO.read(file);
            return bim;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

//    //将带透明度的图片转化为不带透明度的RGB图片数据
////    public static BufferedImage ConvertToRGB(BufferedImage src) {
////        int width = src.getWidth();
////        int height = src.getHeight();
////        BufferedImage bim=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
////        System.out.println("图片宽为：" + width + "px," + "高为：" + height + "px");
////        for (int i = 0; i < width; i++) {
////            for (int j = 0; j < height; j++) {
////                int pixel = src.getRGB(i, j);
////                bim.setRGB(i,j,pixel-0xFF0000);
////            }
////        }
////        return bim;
////    }

    public static Color[][] ImageToColorArray(BufferedImage bim) {
        int width = bim.getWidth();
        int height = bim.getHeight();
        System.out.println("图片宽为：" + width + "px," + "高为：" + height + "px");
        Color[][] byteArr = new Color[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixel = bim.getRGB(i, j);
                //得到的pixel值为一个负数，转化为16进制后为ffRRGGBB
                if (i == 0 && j < 10) {
                    System.out.print(Integer.toHexString(pixel) + ",");
                }
                byteArr[i][j] = new Color(pixel, true);
            }
        }
        return byteArr;
    }

    //反转图片
    public static void RevereImage(int i, int j, BufferedImage bim, int red, int green, int blue) {
        red = 255 - red;
        green = 255 - green;
        blue = 255 - blue;
        bim.setRGB(i, j, new Color(red, green, blue, 255).getRGB());
    }

    public static void ChangeToGrey(int i, int j, BufferedImage bim, int red, int green, int blue)
    {
        int newrgb=(red+green+blue)/3;
        bim.setRGB(i, j,new Color(newrgb,newrgb,newrgb,255).getRGB());
    }

    public static void OutPutToImage(BufferedImage bim,String outPath)
    {
        File outFile = new File(outPath);
        try {
            ImageIO.write(bim, "png", outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\YANGMIN\\IdeaProjects\\JavaDataStructureStudy\\src\\ImageToArrayTest\\bizhi.png";
        BufferedImage bim = ReadImgToArray(path);
        if (bim == null) {
            System.out.println("读取文件失败");
        } else {
            Color[][] colorArr = ImageToColorArray(bim);
            int width = bim.getWidth();
            int height = bim.getHeight();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int red = colorArr[i][j].getRed();
                    int green = colorArr[i][j].getGreen();
                    int blue = colorArr[i][j].getBlue();
                    //反转图片
                    RevereImage(i, j, bim, red, green, blue);
//                    //彩色变灰度
//                    ChangeToGrey(i, j, bim, red, green, blue);
                }
            }
            String reverseOutPath = "C:\\Users\\YANGMIN\\IdeaProjects\\JavaDataStructureStudy\\src\\ImageToArrayTest\\bizhi_2.png";
            OutPutToImage(bim,reverseOutPath);
        }
    }
}
