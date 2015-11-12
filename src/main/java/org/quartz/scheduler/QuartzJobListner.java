package org.quartz.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class QuartzJobListner implements JobListener {

	@Override
	public String getName() {
		System.out.println("Inside ScheduledJobListner:getName ");
		return "ScheduledJobListner";
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		System.out.println("Inside ScheduledJobListner:jobToBeExecuted " + context.getJobDetail().getKey().toString());
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		System.out.println("Inside ScheduledJobListner:jobExecutionVetoed " + context.getJobDetail().getKey().toString());
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		System.out.println("Inside ScheduledJobListner:jobWasExecuted " + context.getJobDetail().getKey().toString());
	}

}
