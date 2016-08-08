package com.garryiv.air_tickets.core.services.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class CheckInNotificationJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Date from = context.getPreviousFireTime();
        Date to = context.getFireTime();
    }
}
