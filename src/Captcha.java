import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Captcha {
    private  String captcha;
    private  final Random random = new Random();
    private  final int width = 70 , height = 20;
    Captcha(){
        this.captcha=makeCaptchaString();
    }
    public  String showCaptcha() {
        //captcha = makeCaptchaString();
        BufferedImage image = makeCaptchaImage(this.captcha);
        return makeFinalCaptcha(image);
    }

    private  String makeCaptchaString() {
        int length = 5 + (Math.abs(random.nextInt()) % 3);

        StringBuffer captchaStringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int baseCharNumber = Math.abs(random.nextInt()) % 62;
            int charNumber = 0;
            if (baseCharNumber < 26) {
                charNumber = 65 + baseCharNumber;
            }
            else if (baseCharNumber < 52){
                charNumber = 97 + (baseCharNumber - 26);
            }
            else {
                charNumber = 48 + (baseCharNumber - 52);
            }
            captchaStringBuffer.append((char)charNumber);
        }

        return captchaStringBuffer.toString();
       // int length = random.nextInt(4,8);
       // char[] captchaArray = new char[length];
       // for (int i = 0; i < length; i++) {
       //     captchaArray[i] = (char) random.nextInt(48,58);
       // }
       // return new String(captchaArray);
    }

    public  BufferedImage makeCaptchaImage(String myCaptcha) {
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0,0,width,height);
        graphics2D.setFont(new Font("Arial",Font.BOLD,20));
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString(myCaptcha,5,17);
        graphics2D.dispose();
        return addNoiseToCaptcha(image);
    }

    private  BufferedImage addNoiseToCaptcha(BufferedImage image) {
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            image.setRGB(x, y, -16777216);
        }
        return image;
    }

    private String makeFinalCaptcha(BufferedImage image) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] shapes = {"+","*","*","#","@","$","0"};
        int index = random.nextInt(7);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                stringBuilder.append(image.getRGB(j, i) == -16777216 ? shapes[index] : " ");
            }
            stringBuilder.append("\n");
        }
        return new String(stringBuilder);
    }


    public  boolean inputEqualsCaptcha(String input) {
        return input.equals(captcha);
    }
}
