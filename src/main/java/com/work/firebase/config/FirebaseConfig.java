package com.work.firebase.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Service
public class FirebaseConfig {
	
	@PostConstruct
	public void configureFirebaseConnection() throws IOException{
		
	File file=ResourceUtils.getFile("classpath:config/spring-boot-crud-api-firebase-adminsdk-wofkm-3ce7f4a7df.json");
		
	FileInputStream serviceAccount =
			new FileInputStream(file);

			FirebaseOptions options = new FirebaseOptions.Builder()
			  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
			  .build();

			FirebaseApp.initializeApp(options);

	}

}
