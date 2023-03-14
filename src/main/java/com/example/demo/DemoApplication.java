package com.example.demo;

import com.azure.core.util.BinaryData;
import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/")
	String sayHello() {
		DefaultAzureCredential defaultCredential = new DefaultAzureCredentialBuilder().build();
		BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
				.endpoint("https://stvadfbdummyvaddev.storage.windows.net")
				.credential(defaultCredential)
				.buildClient();
		BlobContainerClient feedback = blobServiceClient.getBlobContainerClient("feedback");
		BlobClient blobClient = feedback.getBlobClient("0421cd5c-803e-11ed-85c0-e295c391af89.pb");
		BinaryData binaryData = blobClient.downloadContent();

		//0421cd5c-803e-11ed-85c0-e295c391af89.pb

		return "Hello World!"+binaryData;
	}
}
