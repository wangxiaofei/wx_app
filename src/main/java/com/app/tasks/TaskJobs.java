package com.app.tasks;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@SuppressWarnings("restriction")
@Service
public class TaskJobs {

    private Logger logger = Logger.getLogger(TaskJobs.class);
    
    public void testJob(){
    	System.out.println("test Job!!!!");
    }

}
