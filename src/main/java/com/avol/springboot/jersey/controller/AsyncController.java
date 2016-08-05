package com.avol.springboot.jersey.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Response;
import java.util.concurrent.TimeUnit;

/**
 * Created by Durga on 8/5/2016.
 */
@Path("/async")
public class AsyncController {

    @GET
    public void processAsync(@Suspended AsyncResponse response) {
        response.setTimeoutHandler(new TimeoutHandler() {

            @Override
            public void handleTimeout(AsyncResponse asyncResponse) {
                asyncResponse.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE)
                        .entity("Operation time out.").build());
            }
        });
        response.setTimeout(200, TimeUnit.SECONDS);

        new Thread(new Runnable() {

            @Override
            public void run() {
                String result = null;
                try {
                    result = veryExpensiveOperation();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                response.resume(Response.status(Response.Status.ACCEPTED)
                        .entity(result).build());
            }

            private String veryExpensiveOperation() throws InterruptedException {
                // ... very expensive operation that typically finishes within 20 seconds
                for (int i = 0; i < 20; i++ ) {
                    System.out.println("Server Running: " + i);
                    TimeUnit.SECONDS.sleep(1);
                }
                return "Response from async method.";
            }
        }).start();
    }
}
