package com.example.qrcodewithlogo;

import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.common.ComponentCategory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Base64;
import android.view.View;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@DesignerComponent(
        version = 1,
        description = "QR Code Generator with Logo and Image Display",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "")
@SimpleObject(external = true)
public class QRCodeWithLogo extends AndroidNonvisibleComponent {

    private final Form form;

    public QRCodeWithLogo(ComponentContainer container) {
        super(container.$form());
        this.form = container.$form();
    }

    @SimpleFunction(description = "Generate a QR code with logo from file path or image component, display it on another Image component, and return a Base64 string.")
    public String Generate(String content, AndroidViewComponent logoComponent, AndroidViewComponent displayComponent) {
        try {
            int size = 512;
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, size, size);

            Bitmap qrBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    qrBitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            Bitmap logo = extractBitmapFromComponent(logoComponent);
            if (logo != null) {
                int overlaySize = size / 5;
                Bitmap scaledLogo = Bitmap.createScaledBitmap(logo, overlaySize, overlaySize, false);
                Canvas canvas = new Canvas(qrBitmap);
                int left = (qrBitmap.getWidth() - overlaySize) / 2;
                int top = (qrBitmap.getHeight() - overlaySize) / 2;
                canvas.drawBitmap(scaledLogo, left, top, null);
            }

            // Set image to UI component
            if (displayComponent.getView() instanceof android.widget.ImageView) {
                android.widget.ImageView imageView = (android.widget.ImageView) displayComponent.getView();
                imageView.setImageBitmap(qrBitmap);
            }

            // Return base64
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            qrBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            byte[] byteArray = outputStream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private Bitmap extractBitmapFromComponent(AndroidViewComponent component) {
        try {
            View view = component.getView();
            if (view instanceof android.widget.ImageView) {
                android.widget.ImageView imgView = (android.widget.ImageView) view;
                imgView.setDrawingCacheEnabled(true);
                Bitmap bitmap = Bitmap.createBitmap(imgView.getDrawingCache());
                imgView.setDrawingCacheEnabled(false);
                return bitmap;
            } else if (component instanceof com.google.appinventor.components.runtime.Image) {
                Image img = (Image) component;
                String path = img.Picture().startsWith("file://") ? img.Picture().substring(7) : img.Picture();
                File file = new File(path);
                InputStream is = new FileInputStream(file);
                return BitmapFactory.decodeStream(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
