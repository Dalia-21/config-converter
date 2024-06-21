package com.dalia;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigFileReader {

  public static void checkExtension(final String inputFileName, final String outputFormat) {
    List<String> supportedExtensions = Arrays.asList(new String[] {"xml", "json", "yaml"});

    String[] splitFileName = inputFileName.split("\\.");
    String fileExtension = splitFileName[splitFileName.length-1];

    String errorMessage = "";
    
    if (!supportedExtensions.contains(outputFormat)) {
      errorMessage = "Output format " + outputFormat + " is not supported.";
    }

    if (fileExtension.equals(outputFormat)) { // Overrides previous error message
      errorMessage = "File is already in desired format.";
    }

    if (!errorMessage.isBlank()) {
      log.error(errorMessage);
      System.exit(1);
    }
  }

  public static String read(final String fileName) {
  
    Path pathToFile = Paths.get(fileName);
    if (!Files.isReadable(pathToFile)) {
      log.error("Could not read file {fileName} - Please provide absolute path or path from current directory.");
      System.exit(1);
    }

    String fileContents = null;
    try {
      fileContents = String.join("\n", Files.readAllLines(pathToFile));
    } catch (IOException e) {
      log.error("Could not read contents of file {fileName}");
      System.exit(1);
    }

    return fileContents;
  }
  
}
