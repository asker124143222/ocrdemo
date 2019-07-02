import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @Author: xu.dm
 * @Date: 2019/6/27 10:41
 * @Version: 1.0
 * @Description: TODO
 **/
public class OCRDemo {
    public static void main(String args[]) throws Exception {
        ITesseract instance = new Tesseract();
        instance.setDatapath("tessdata"); //相对目录，这个时候tessdata目录和src目录平级

//        instance.setDatapath("E:\\myProgram\\java\\ocrdemo\\tessdata");//支持绝对目录
        instance.setLanguage("chi_sim");//选择字库文件（只需要文件名，不需要后缀名）
        try {
            File imageFile = new File("d:\\temp\\3.png");
//            File imageFile = new File("d:\\temp\\1.jpg");
            BufferedImage bufferedImage = ImageIO.read(imageFile);
//            String result = instance.doOCR(imageFile);//开始识别
//            Rectangle rect = new Rectangle(2581, 510, 249, 196);

//            String result2 = instance.doOCR(bufferedImage, rect);
            String result2 = instance.doOCR(bufferedImage);


            System.out.println(result2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
