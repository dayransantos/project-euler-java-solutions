package opener;

import com.googlecode.javacv.cpp.opencv_core;
import com.googlecode.javacv.cpp.opencv_highgui;
import com.googlecode.javacv.cpp.opencv_objdetect;
import sun.misc.IOUtils;
import tasks.ITask;
import tasks.Tester;
import utils.FileUtils;
import utils.log.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_core.IplImage.*;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_objdetect.CvHaarClassifierCascade;
import static com.googlecode.javacv.cpp.opencv_objdetect.cvHaarDetectObjects;

//Answer :
public class Task_9 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_9());
        Logger.close();
    }

    private static final String CASCADE_FILE = "C:\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml";

    public void solving() throws Exception {
//        BufferedImage image = ImageIO.read(new File("d:\\Downloads\\task.png"));
//        int width = image.getWidth();
//        int height = image.getHeight();

        int width = 8000;
        int height = 8000;

        int cnt = 0;
        for (int x = 0; x < width; x += 100) {
            System.out.println(x);
            for (int y = 0; y < height; y += 100) {
//                BufferedImage sub = image.getSubimage(x, y, 100, 100);
//                ImageIO.write(sub, "PNG", new File("D:\\1\\" + x + "_" + y + ".png"));
                if (isFace(x, y)) {
                    System.out.println(x + " " + y);
                    FileUtils.move(
                            new File("D:\\1\\" + x + "_" + y + ".png"),
                            new File("D:\\2\\" + x + "_" + y + ".png")
                    );
                    ++cnt;
                } else {
                    FileUtils.move(
                            new File("D:\\1\\" + x + "_" + y + ".png"),
                            new File("D:\\3\\" + x + "_" + y + ".png")
                    );
                }
            }
        }
        System.out.println(cnt);
    }

    private boolean isFace(int x, int y) {
        // Load the original image.
        IplImage originalImage = cvLoadImage("D:\\1\\" + x + "_" + y + ".png", 1);

        // We need a grayscale image in order to do the recognition, so we
        // create a new image of the same size as the original one.
        IplImage grayImage = create(
                originalImage.width(),
                originalImage.height(),
                opencv_core.IPL_DEPTH_8U, 1);

        // We convert the original image to grayscale.
        cvCvtColor(originalImage, grayImage, CV_BGR2GRAY);

        CvMemStorage storage = CvMemStorage.create();

        // We instantiate a classifier cascade to be used for detection,
        // using the cascade definition.
        CvHaarClassifierCascade cascade = new CvHaarClassifierCascade(cvLoad(CASCADE_FILE));

        // We detect the faces.
        opencv_core.CvSeq faces = cvHaarDetectObjects(grayImage, cascade, storage, 1.1, 1, 0);

//        return faces.total() != 0;
        // We iterate over the discovered faces and draw yellow rectangles
        // around them.
//        for (int i = 0; i < faces.total(); i++) {
//            CvRect r = new CvRect(cvGetSeqElem(faces, i));
//            System.out.println("     " + r.x() + " " + r.y()  + " " +  r.width()  + " " +  r.height());
//        }

        return faces.total() != 0;
    }
}
