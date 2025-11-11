package tr.tsk.hvkk.mys.reportgenerateapi.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.tsk.hvkk.mys.reportgenerateapi.controller.IReportController;
import tr.tsk.hvkk.mys.reportgenerateapi.dto.Personnel;
import tr.tsk.hvkk.mys.reportgenerateapi.service.IReportService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/generate-report")
public class ReportControllerImpl implements IReportController {
    @Autowired
    private IReportService reportService;

    @PostMapping(path = "/personnel")
    @Override
    public byte[] generateReport(@RequestBody List<Personnel> personnelList) throws Exception {
        return reportService.generateReport(personnelList);
    }
}
