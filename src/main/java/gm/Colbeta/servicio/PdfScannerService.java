package gm.Colbeta.servicio;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Random;

@Service
public class PdfScannerService {

    public byte[] generarPdfEfectoEscaneado(InputStream plantillaInputStream, byte[] firmaBytes) throws Exception {
        // 1. Cargar el PDF base
        try (PDDocument document = Loader.loadPDF(plantillaInputStream.readAllBytes())) {

            // 2. Insertar la firma en la primera página (página index 0)
            PDPage paginaFirma = document.getPage(1);

            PDImageXObject firmaImage = PDImageXObject.createFromByteArray(document, firmaBytes, "firma");

            // Coordenadas y dimensiones de la firma (ajustar según tu PDF)
            float x = 330;
            float y = 380;
            float ancho = 150;
            float alto = 20;

            try (PDPageContentStream contentStream = new PDPageContentStream(
                    document, paginaFirma, PDPageContentStream.AppendMode.APPEND, true, true)) {
                contentStream.drawImage(firmaImage, x, y, ancho, alto);
            }

            // 3. Renderizar y aplicar el efecto de impreso/escaneado
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            try (PDDocument scannedDocument = new PDDocument()) {

                Random random = new Random();

                for (int pageIndex = 0; pageIndex < document.getNumberOfPages(); pageIndex++) {
                    // Renderizar cada página como imagen a 150 DPI
                    BufferedImage pageImage = pdfRenderer.renderImageWithDPI(pageIndex, 150);

                    // Aplicar inclinación, ruido suave y escala de grises
                    BufferedImage scannedImage = aplicarEfectoEscaner(pageImage, random);

                    // Convertir la imagen resultante a formato JPG
                    ByteArrayOutputStream imageBaos = new ByteArrayOutputStream();
                    ImageIO.write(scannedImage, "jpg", imageBaos);
                    byte[] imageBytes = imageBaos.toByteArray();

                    // Reconstruir la página dentro del nuevo PDF
                    PDImageXObject pdImage = PDImageXObject.createFromByteArray(scannedDocument, imageBytes, "page_" + pageIndex);
                    PDPage newPage = new PDPage(new PDRectangle(pdImage.getWidth(), pdImage.getHeight()));
                    scannedDocument.addPage(newPage);

                    try (PDPageContentStream newContentStream = new PDPageContentStream(scannedDocument, newPage)) {
                        newContentStream.drawImage(pdImage, 0, 0, pdImage.getWidth(), pdImage.getHeight());
                    }
                }

                // 4. Retornar el PDF en arreglo de bytes
                ByteArrayOutputStream pdfOut = new ByteArrayOutputStream();
                scannedDocument.save(pdfOut);
                return pdfOut.toByteArray();
            }
        }
    }

    /**
     * Aplica distorsiones de escáner: rotación leve, ruido y ajuste de color/contraste.
     */
    private BufferedImage aplicarEfectoEscaner(BufferedImage src, Random random) {
        int width = src.getWidth();
        int height = src.getHeight();

        BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = dest.createGraphics();

        // Fondo blanco
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Ajustes de calidad para la transformación
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Rotación aleatoria muy leve (-0.7° a +0.7°)
        double angleRad = Math.toRadians((random.nextDouble() - 0.5) * 1.4);
        g2d.rotate(angleRad, width / 2.0, height / 2.0);

        // Dibujar la imagen sobre el canvas
        g2d.drawImage(src, 0, 0, null);
        g2d.dispose();

        // Aplicar escala de grises y grano (ruido estático)
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = dest.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                // Desaturación
                int gray = (int) (0.299 * r + 0.587 * g + 0.114 * b);

                // Variación aleatoria de brillo por píxel (ruido)
                int noise = random.nextInt(9) - 4;
                gray = Math.min(255, Math.max(0, gray + noise));

                int newRgb = (gray << 16) | (gray << 8) | gray;
                dest.getRGB(x, y);
                dest.setRGB(x, y, newRgb);
            }
        }

        return dest;
    }
}