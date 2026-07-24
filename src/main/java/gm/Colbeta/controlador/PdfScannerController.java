package gm.Colbeta.controlador;

import gm.Colbeta.servicio.PdfScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pdf")
public class PdfScannerController {

    @Autowired
    private PdfScannerService pdfScannerService;

    @PostMapping(value = "/firmar-y-escanear", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> firmarYEscanearPdf(
            @RequestParam("plantilla") MultipartFile plantillaFile,
            @RequestParam("firma") MultipartFile firmaFile) {

        try {
            // Validar que los archivos no vengan vacíos
            if (plantillaFile.isEmpty() || firmaFile.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            // Llamar al servicio para procesar los bytes
            byte[] pdfResultante = pdfScannerService.generarPdfEfectoEscaneado(
                    plantillaFile.getInputStream(),
                    firmaFile.getBytes()
            );

            // Configurar los encabezados de respuesta para devolver un archivo PDF
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);

            // "inline" permite verlo en el navegador; usa "attachment" si prefieres descarga automática
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=documento_escaneado.pdf");

            return new ResponseEntity<>(pdfResultante, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}