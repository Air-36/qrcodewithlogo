package com.example.qrcodewithlogo;

import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.*;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;

import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@DesignerComponent(
        version = 1,
        description = "QR Code Generator with Logo (returns Base64 string).",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "")
@SimpleObject(external = true)
public class QRCodeWithLogo extends AndroidNonvisibleComponent {

    public QRCodeWithLogo(ComponentContainer container) {
        super(container.$form());
    }

    @SimpleFunction(description = "Generate a QR code with a logo. Returns Base64-encoded PNG image string.")
    public String Generate(String content, String logoUrl, int size) {
        try {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, size, size);

            Bitmap qrBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    qrBitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            Bitmap logo = getBitmapFromURL(logoUrl);
            if (logo != null) {
                int overlaySize = size / 5;
                Bitmap scaledLogo = Bitmap.createScaledBitmap(logo, overlaySize, overlaySize, false);
                Canvas canvas = new Canvas(qrBitmap);
                int left = (qrBitmap.getWidth() - overlaySize) / 2;
                int top = (qrBitmap.getHeight() - overlaySize) / 2;
                canvas.drawBitmap(scaledLogo, left, top, null);
            }

            // Convert bitmap to Base64
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            qrBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            byte[] byteArray = outputStream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);

        } catch (WriterException e) {
            e.printStackTrace();
            return "";
        }
    }

    private Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            return BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
