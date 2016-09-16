package com.garryiv.air_tickets.core.notification.template;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.io.internal.ByteArrayOutputStream;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PdfTemplateInterpolator implements TemplateInterpolator {

    private final ResourceLoader resourceLoader;

    private final ConcurrentHashMap<String, IXDocReport> reportsCache = new ConcurrentHashMap<>();

    public PdfTemplateInterpolator(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public byte[] interpolate(String template, Map<String, Object> contextMap) {
        try {
            IXDocReport report = loadReport(template);
            IContext context = report.createContext(contextMap);

            Options options = Options.getTo(ConverterTypeTo.PDF);

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                report.convert(context, options, baos);
                return baos.toByteArray();
            }
        } catch (XDocReportException | IOException e) {
            throw new InterpolationException("Failed to instantiate template: " + template, e);
        }
    }

    private IXDocReport loadReport(String template) throws IOException, XDocReportException {
        IXDocReport report = reportsCache.get(template);
        if (report != null) {
            return report;
        }
        Resource resource = resourceLoader.getResource("classpath:/templates/" + template);

        if (!resource.exists()) {
            throw new IllegalArgumentException("Template does not exits " + template);
        }

        try (InputStream is = resource.getInputStream()) {
            report = new XDocReportRegistry().loadReport(is, TemplateEngineKind.Velocity);
            reportsCache.putIfAbsent(template, report);
            return report;
        }
    }
}
