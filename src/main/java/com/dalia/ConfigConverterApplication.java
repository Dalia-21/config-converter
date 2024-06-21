package com.dalia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ConfigConverterApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConfigConverterApplication.class, args);
	}

  @Override
  public void run(String... args) {
    log.info("Starting Config Converter Application");

    if (args.length < 2) {
      log.error("Usage: Config Converter Application <input-file> <output-format> [output-file]");
      System.exit(1);
    }

    String inputFile = args[0];
    String outputFormat = args[1].toLowerCase();

    ConfigFileReader.checkExtension(inputFile, outputFormat);
    
    String fileContents = ConfigFileReader.read(inputFile);
  

  }

}
