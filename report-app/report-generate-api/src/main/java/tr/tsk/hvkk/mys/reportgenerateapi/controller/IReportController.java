package tr.tsk.hvkk.mys.reportgenerateapi.controller;

import tr.tsk.hvkk.mys.reportgenerateapi.dto.Personnel;

import java.util.List;

public interface IReportController {
    byte[] generateReport(List<Personnel> personnelList) throws Exception;
}
