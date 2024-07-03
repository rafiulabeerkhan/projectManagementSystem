package com.cns.webapp.projectManagement.repository.service;

import com.cns.webapp.projectManagement.model.ProjectDetails;
import com.cns.webapp.projectManagement.model.Report;
import com.cns.webapp.projectManagement.repository.ProjectRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JasperReportService {

    public String exportReport(String reportFormat, List<Report> projects) throws FileNotFoundException, JRException {
        String path="C:\\Users\\CNS\\Documents";

        File file= ResourceUtils.getFile("classpath:projectReport.jrxml");

        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(projects);
        Map<String, Object> parameters=new HashMap<>();
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\projects.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, path+"\\projects.pdf");
        }

        return "redirect:/user/projectList";
}

}
