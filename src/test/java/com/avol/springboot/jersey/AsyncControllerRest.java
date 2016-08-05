package com.avol.springboot.jersey;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by Durga on 8/5/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JerseyApplication.class)
@WebIntegrationTest(value = "server.address=localhost", randomPort = true)
public class AsyncControllerRest {

    @Value("${local.server.port}")  //boot injects the port used for bootstart the application.
    private int port;


    @Test
    public void testAsync() throws ExecutionException, InterruptedException {
        String baseURL = "http://localhost:" + port + "/avol";
        final AsyncInvoker asyncInvoker = ClientBuilder.newClient().target(baseURL).path("/async").request().async();
        final Future<Response> responseFuture = asyncInvoker.get(Response.class);
        System.out.println(">>>>>>>>>>>>Request is being processed asynchronously.");
        final Response response = responseFuture.get();
        // get() waits for the response to be ready
        System.out.println("Response Status code: " + response.getStatus());
        System.out.println("Response Entity: " + response.getEntity());
        System.out.println("Response received.");
    }

    @Test
    public void testAsychWithCallBack() throws InterruptedException {
        String baseURL = "http://localhost:" + port + "/avol";
        final Future<Response> responseFuture = ClientBuilder.newClient()
                .target(baseURL).path("/async").request().async()
                .get(new InvocationCallback<Response>() {
                    @Override
                    public void completed(Response response) {
                        System.out.println("Response status code "
                                + response.getStatus() + " received.");
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        System.out.println("Invocation failed.");
                        throwable.printStackTrace();
                    }
                });

        System.out.println("Method execution completed.");
        for(int i = 0 ; i < 25; i++) {
            System.out.println("client running : " + i);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
