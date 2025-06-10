package com.sao.report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 10 Jun 2025
 * <p>
 * @description:
 */
public class FirstReport {
    public static void main(String[] args) {
        try {
            String filePath = String.valueOf(FirstReport.class.getClassLoader().getResource("FirstReport.jrxml").getPath());
            String projectDir = System.getProperty("user.dir");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("studentName", "Ahmet");

            Student student1 = new Student(1L, "Ahmet", "Özdemir", "1231 Main St", "Ankara1");
            Student student2 = new Student(2L, "Esra", "Özdemir", "1232 Main St", "Ankara2");
            Student student3 = new Student(3L, "Emir", "Özdemir", "1232 Main St", "Ankara3");
            Student student4 = new Student(4L, "Miray", "Özdemir", "1233 Main St", "Ankara4");

            List<Student> studentList = new ArrayList<>();
            studentList.add(student1);
            studentList.add(student2);
            studentList.add(student3);
            studentList.add(student4);

            /** Öğrenci listesini rapor içine göndermek için kullanılıyor.*/
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(studentList);

            /** JRXML dosyasını derleyerek JasperReport nesnesi oluşturuyor. */
            JasperReport report = JasperCompileManager.compileReport(filePath);

            /** JasperReport nesnesini doldurarak raporu oluşturuyor. */
            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

            /** Raporu PDF formatında dışa aktarıyor. */
            JasperExportManager.exportReportToPdfFile(print, projectDir + "/report/src/main/resources/FirstReport.pdf");
            System.out.println("Rapor başarıyla oluşturuldu: FirstReport.pdf");

            /** Raporu PDF formatında dışa aktarıyor. */
            JRXlsxExporter exporter = new JRXlsxExporter ();
            exporter.setExporterInput(new SimpleExporterInput(print));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(projectDir + "/report/src/main/resources/FirstReport.xlsx"));
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setDetectCellType(true);           // Hücre türünü otomatik algıla
            configuration.setCollapseRowSpan(true);          // Satırları birleştir
            configuration.setOnePagePerSheet(false);         // Tek sayfaya yaz
            configuration.setWhitePageBackground(false);     // Boş arka planı kaldır
            configuration.setRemoveEmptySpaceBetweenRows(true); // Gereksiz boşlukları kaldır
            configuration.setIgnoreGraphics(false);          // Grafik (çizgi vs) kaybı yaşanmasın

            exporter.setConfiguration(configuration);
            exporter.exportReport();

            System.out.println("Rapor başarıyla oluşturuldu: FirstReport.xlsx");


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Rapor oluşturulurken bir hata oluştu: " + e.getMessage());
        }
    }
}
