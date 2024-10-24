package com.example.dynamicpdfgenerator.service;

import com.example.dynamicpdfgenerator.Entities.Item;
import com.example.dynamicpdfgenerator.Entities.TransactionDone;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static com.itextpdf.io.font.constants.StandardFonts.COURIER;
import static com.itextpdf.io.font.constants.StandardFonts.COURIER_BOLD;

@Service
public class PdfService {
    public byte[] generatePdf(TransactionDone transaction) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            DeviceGray greyColor = new DeviceGray(0.2f);

            PdfFont font = PdfFontFactory.createFont(COURIER);
            PdfFont font1 = PdfFontFactory.createFont(COURIER_BOLD);
            SolidBorder border = new SolidBorder(greyColor,1.0f);

            Div mainDiv = new Div();
            mainDiv.setPaddings(0.0f,0.0f,50.0f,0.0f);
            mainDiv.setBorder(border);

            // Add content to the PDF
            float[] columnWidths = {3, 1, 1, 1};  // You can customize the column widths
            Table table = new Table(columnWidths);
            table.setBorderBottom(border);

            List<Item> items = transaction.getItems().stream().toList();

            Div div1 = new Div();
            div1.add(new Paragraph("Seller : ").setFontColor(greyColor).setFontSize(13.0f).setFont(font1).setPadding(-5.0f));
            div1.add(new Paragraph(transaction.getSeller()).setFontColor(greyColor).setFontSize(13.0f).setFont(font1).setPadding(-5.0f));
            div1.add(new Paragraph(transaction.getSellerGSTIN()).setFontColor(greyColor).setFontSize(13.0f).setFont(font1).setPadding(-5.0f));
            div1.add(new Paragraph(transaction.getSellerAddress()).setFontColor(greyColor).setFontSize(13.0f).setFont(font1).setPadding(-5.0f));
            div1.setPadding(20.0f);
            div1.setTextAlignment(TextAlignment.LEFT);

            Div div2 = new Div();
            div2.add(new Paragraph("Buyer : ").setFontColor(greyColor).setFontSize(13.0f).setFont(font1).setPadding(-5.0f));
            div2.add(new Paragraph(transaction.getBuyer()).setFontColor(greyColor).setFontSize(13.0f).setFont(font1).setPadding(-5.0f));
            div2.add(new Paragraph(transaction.getBuyerGSTIN()).setFontColor(greyColor).setFontSize(13.0f).setFont(font1).setPadding(-5.0f));
            div2.add(new Paragraph(transaction.getBuyerAddress()).setFontColor(greyColor).setFontSize(13.0f).setFont(font1).setPadding(-5.0f));
            div2.setPadding(20.0f);
            div2.setTextAlignment(TextAlignment.LEFT);


            table.setWidth(UnitValue.createPercentValue(100)); // Set table width to 50% of page width
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);

            // Add table header
            Cell header1 = new Cell(1, 2)
                    .add(div1);
            table.addHeaderCell(header1).setBorder(border);
            Cell header2 = new Cell(1, 2)
                    .add(div2);
            table.addHeaderCell(header2).setBorder(border);

            table.addCell("Item").setFontColor(greyColor).setFont(font).setBorder(border).setTextAlignment(TextAlignment.CENTER);
            table.addCell("Quantity").setFontColor(greyColor).setFont(font).setBorder(border).setTextAlignment(TextAlignment.CENTER);
            table.addCell("Rate").setFontColor(greyColor).setFont(font).setBorder(border).setTextAlignment(TextAlignment.CENTER);
            table.addCell("Amount").setFontColor(greyColor).setFont(font).setBorder(border).setTextAlignment(TextAlignment.CENTER);


            if(items.size() >=1 && items!=null) {
                for (Item i : items) {
                    table.addCell(i.getName()).setFontColor(greyColor).setFont(font).setBorder(border).setTextAlignment(TextAlignment.CENTER);
                    table.addCell(i.getQuantity()).setFontColor(greyColor).setFont(font).setBorder(border).setTextAlignment(TextAlignment.CENTER);
                    table.addCell(i.getRate().toString()).setFontColor(greyColor).setFont(font).setBorder(border).setTextAlignment(TextAlignment.CENTER);
                    table.addCell(i.getAmount().toString()).setFontColor(greyColor).setFont(font).setBorder(border).setTextAlignment(TextAlignment.CENTER);
                }
            }

            mainDiv.add(table);

            document.add(mainDiv);

            document.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
