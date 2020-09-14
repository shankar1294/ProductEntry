package com.example.userregistration.lambda;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Component;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.example.productentry.ProductEntryApplication;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CreateProductLambda implements RequestStreamHandler {
	
    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    
    static {
        try {
        	log.trace("inside handler ....");
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(ProductEntryApplication.class);
            // If you are using HTTP APIs with the version 2.0 of the proxy model, use the getHttpApiV2ProxyHandler
            // method: handler = SpringBootLambdaContainerHandler.getHttpApiV2ProxyHandler(Application.class);
        } catch (ContainerInitializationException e) {
            // if we fail here. We re-throw the exception to force another cold start
            e.printStackTrace();
            throw new RuntimeException("Could not initialize Spring Boot application", e);
        }
    }
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
            throws IOException {
    	//logger.info("handleRequest");
        handler.proxyStream(inputStream, outputStream, context);
    }
}