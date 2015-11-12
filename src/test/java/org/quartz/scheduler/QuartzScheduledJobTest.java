package org.quartz.scheduler;

import org.junit.Test;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import static org.junit.Assert.*;

public class QuartzScheduledJobTest {

	@Test
	public void testQuartzJobScheduler() {
		try {
			JobKey jobKey = new JobKey("ScheduledJob", "ScheduledJobGroup");
			JobDetail job = JobBuilder.newJob(QuartzScheduledJob.class)
					.withIdentity(jobKey).build();

			// Trigger the job to run on the next round minute
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("ScheduledTrigger", "ScheduledTriggerGroup")
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
					.build();

			// schedule it
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
	    	
	    	//Listener attached to jobKey
	    	scheduler.getListenerManager().addJobListener(
	    		new QuartzJobListner(), KeyMatcher.keyEquals(jobKey)
	    	);
	    	
			scheduler.start();	scheduler.scheduleJob(job, trigger);
			Thread.sleep(6 * 1000);
			scheduler.shutdown();
			
		} catch (SchedulerException | InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(true);
	}

}
