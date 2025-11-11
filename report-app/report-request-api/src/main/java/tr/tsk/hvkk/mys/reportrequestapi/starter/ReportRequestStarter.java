package tr.tsk.hvkk.mys.reportrequestapi.starter;

import tr.tsk.hvkk.mys.reportrequestapi.dto.Department;
import tr.tsk.hvkk.mys.reportrequestapi.dto.Personnel;
import tr.tsk.hvkk.mys.reportrequestapi.service.ReportRequestService;

import java.util.ArrayList;
import java.util.List;

public class ReportRequestStarter {
    private static ReportRequestService service = new ReportRequestService();

    public static void main(String[] args) {
        List<Personnel> personnelList = new ArrayList<>();
        personnelList.add(new Personnel(1000L, "Ahmet", "ÖZDEMİR", new Department("MYS")));
        personnelList.add(new Personnel(1001L, "Emir", "ÖZDEMİR", new Department("ASD")));

        try {
            byte[] pdfByte = service.sendReportRequest(personnelList);
            service.saveReportToFile(pdfByte);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
