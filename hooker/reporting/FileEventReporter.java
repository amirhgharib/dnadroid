package com.dnadroid.hooker.reporting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.dnadroid.hooker.SubstrateMain;
import com.dnadroid.hooker.common.InterceptEvent;

import android.os.Environment;
public class FileEventReporter extends AbstractReporter {

  private static final String EVENT_DELIMITER = "\r\n\r\n";
  String OUTPUT_PATH = "hooker";
  private String outputFile;

  private FileOutputStream currentFileOutputStream = null;

  /**
   * Default constructor
   */
  public FileEventReporter(String outputFile) {
    this.outputFile = outputFile;
  }


  @Override
  protected void report(InterceptEvent event) {
    SubstrateMain.log("File reporter write to file an event.");
    this.writeToFile("{IDXP:"+event.getIDXP()+",payload:"+event.toJson()+"}");
    //this.writeToFile(event.toJson());
  }

  /**
   * @param jsonEvent
   */
  private void writeToFile(String jsonEvent) {

    FileOutputStream fos = this.getCurrentFileOutputStream();
    if (fos != null) {
      byte[] data = new String(jsonEvent + EVENT_DELIMITER).getBytes();
      try {
        fos.write(data);
        fos.flush();
      } catch (IOException e) {
        SubstrateMain.log(
            new StringBuilder("Error, while writing (or flushing) file ").append(this.outputFile)
                .append(" on the sdcard").toString(), e);
      }
    }
  }

  /**
   * @return
   */
  private FileOutputStream getCurrentFileOutputStream() {
    if (currentFileOutputStream == null) {
      File sdCardPath = Environment.getExternalStorageDirectory();

      // create directory if it doesn't exist
      File outputDir = new File(sdCardPath.getAbsolutePath() + File.separator + OUTPUT_PATH);
      if (!outputDir.exists()) {
        SubstrateMain.log("Creating the output directtory for event reporting");
        outputDir.mkdirs();
      }

      // delete old log files if exist
      String outputPath = outputDir.getAbsolutePath() + File.separator + this.outputFile;
      File outputFile = new File(outputPath);
      if (outputFile.exists()) {
        outputFile.delete();
      }

      // create the file
      try {
        outputFile.createNewFile();
      } catch (IOException e) {
        SubstrateMain.log(
            new StringBuilder("Error, impossible to create the file: ").append(
                outputFile.getAbsolutePath()).toString(), e);
        return null;
      }

      try {
        currentFileOutputStream = new FileOutputStream(outputFile);
      } catch (FileNotFoundException e) {
        SubstrateMain.log(
            new StringBuilder("Error, impossible to open ").append(outputFile.getAbsolutePath())
                .append(" on the sdcard").toString(), e);
      }
    }
    return currentFileOutputStream;
  }

}
