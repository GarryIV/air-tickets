package com.garryiv.air_tickets.core.services.notification;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import org.junit.Test;

import java.io.*;

public class UserNotificationServiceTest {
    private static String TEMPLATE = "C:\\Users\\Default.NADYA-PC\\Downloads\\ODTHelloWordWithVelocity.odt";

    @Test
    public void test() throws Exception {
        // 1) Load ODT file and set Velocity template engine and cache it to the registry
        InputStream in= new FileInputStream(new File(TEMPLATE));
        IXDocReport report = new XDocReportRegistry().loadReport(in, TemplateEngineKind.Velocity, true);

// 2) Create Java model context
        IContext context = report.createContext();
        context.put("name", "world");
        context.put("zzz", "zzz");
        context.put("yyy", "yyy");

// 3) Set PDF as format converter
        Options options = Options.getTo(ConverterTypeTo.PDF);

// 3) Generate report by merging Java model with the ODT and convert it to PDF
        OutputStream out = new FileOutputStream(new File("ODTHelloWordWithVelocity_Out.pdf"));
        report.convert(context, options, out);

        OutputStream out1 = new FileOutputStream(new File("ODTHelloWordWithVelocity_Out1.pdf"));
        report.convert(context, options, out1);

    }

}