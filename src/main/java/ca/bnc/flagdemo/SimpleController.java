package ca.bnc.flagdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class SimpleController {

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping(
            value = "/image",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public @ResponseBody byte[] flagImage(
            @RequestParam(name = "color", required = false, defaultValue = "00E41C23") String colorString) throws IOException {
        InputStream in = getClass().getResourceAsStream("/images/logo-bnc-flag.png");
        BufferedImage img = ImageIO.read(in);
        int color = Integer.parseInt(colorString, 16) & 0x00FFFFFF; // drop alpha

        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int pix = img.getRGB(x, y);
                int alpha = (pix >> 24) & 0xff;
                int rgba = (alpha << 24) | color;
                img.setRGB(x, y, rgba);
            }
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);
        baos.flush();
        return baos.toByteArray();
    }

}
