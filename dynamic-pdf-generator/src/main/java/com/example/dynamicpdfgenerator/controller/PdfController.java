package com.example.dynamicpdfgenerator.controller;

import com.example.dynamicpdfgenerator.Entities.ProductUser;
import com.example.dynamicpdfgenerator.Entities.TransactionDone;
import com.example.dynamicpdfgenerator.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/download")
public class PdfController {
    @Autowired
    private PdfService pdfService;

    @GetMapping(value="/download-pdf",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> downloadPdf(@RequestBody TransactionDone transaction) {
        byte[] pdfContent = pdfService.generatePdf(transaction);

        if (pdfContent != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=generated.pdf");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

            return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
