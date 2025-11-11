package tr.tsk.hvkk.mys.reportgenerateapi.service.impl;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import tr.tsk.hvkk.mys.reportgenerateapi.dto.Department;
import tr.tsk.hvkk.mys.reportgenerateapi.dto.Personnel;
import tr.tsk.hvkk.mys.reportgenerateapi.service.IReportService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportServiceImpl implements IReportService {
    @Override
    public byte[] generateReport(List<Personnel> personnelList) throws Exception {
        String filePath = String.valueOf(ReportServiceImpl.class.getClassLoader().getResource("report/personnel_list.jrxml").getPath());
        String projectDir = System.getProperty("user.dir");

        /** Personnel listesini rapor içine göndermek için kullanılıyor.*/
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(personnelList);

        /** JRXML dosyasını derleyerek JasperReport nesnesi oluşturuyor. */
        JasperReport report = JasperCompileManager.compileReport(filePath);

        /** JasperReport nesnesini doldurarak raporu oluşturuyor. */
        JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), dataSource);

        /** Raporu PDF formatında dışa byte dizisi olarak aktarıyor. */
        JasperExportManager.exportReportToPdfFile(print, projectDir + "/generated-report-server.pdf");
        byte[] reportPdfByte = JasperExportManager.exportReportToPdf(print);
        return reportPdfByte;
    }
}
