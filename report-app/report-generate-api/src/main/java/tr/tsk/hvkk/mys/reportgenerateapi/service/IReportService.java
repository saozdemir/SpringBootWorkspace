package tr.tsk.hvkk.mys.reportgenerateapi.service;

import tr.tsk.hvkk.mys.reportgenerateapi.dto.Personnel;

import java.util.List;

public interface IReportService {
    byte[] generateReport(List<Personnel> personnelList) throws Exception;
}
