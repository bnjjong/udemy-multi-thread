/*
 * MIT License
 *
 * Copyright (c) 2023-present, Henry<dogfootmaster@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.df.henry.udemymultithread.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;

public class FileHelper {

  public static File getFileFromResource(final String path) throws IOException {
    try {
      URL res = FileHelper.class.getClassLoader().getResource(path);

      if (res == null) {
        throw new IllegalArgumentException("path is not valid!>>>" + path);
      }
      System.out.println(res.getPath());
      return Paths.get(res.toURI()).toFile();
    } catch (Exception e) {
      InputStream in = FileHelper.class.getClassLoader().getResourceAsStream(path);
      try {
        if (in != null) {
          return convertInputStreamToFile(in);
        }
        throw new IOException("input stream is null");
      } catch (IOException ioException) {
        ioException.printStackTrace();
        throw ioException;
      }
    }
  }

  public static File createFileInResources(final String fileName) throws IOException {
    // 빌드에 생성됨.
//    URL res = FileHelper.class.getClassLoader().getResource("");
//    if (res == null) {
//      throw new IOException("Unable to locate resources directory.");
//    }
//
//    File resourcesFolder = new File(res.getFile());
//    File newFile = new File(resourcesFolder.getAbsolutePath() + File.separator + fileName);
//
//    if (newFile.createNewFile()) {
//      System.out.println("File created successfully: " + newFile.getAbsolutePath());
//      return newFile;
//    } else {
//      throw new IOException("Failed to create file: " + newFile.getAbsolutePath());
//    }

    // 여기는 파일만 생성
//    File resourcesFolder = new File("src/main/resources");
//    File newFile = new File(resourcesFolder.getAbsolutePath() + File.separator + fileName);
//
//    if (newFile.createNewFile()) {
//      System.out.println("File created successfully: " + newFile.getAbsolutePath());
//      return newFile;
//    } else {
//      throw new IOException("Failed to create file: " + newFile.getAbsolutePath());
//    }
    File resourcesFolder = new File("src/main/resources");

    // Splitting file name into directory and file name
    String[] pathComponents = fileName.split("/");
    String dirPath = resourcesFolder.getAbsolutePath();
    for (int i = 0; i < pathComponents.length - 1; i++) {
      dirPath += File.separator + pathComponents[i];
    }

    // Creating directories if they don't exist
    File dir = new File(dirPath);
    if (!dir.exists()) {
      if (!dir.mkdirs()) {
        throw new IOException("Failed to create directory: " + dir.getAbsolutePath());
      }
    }

    // Creating file
    File newFile = new File(dir.getAbsolutePath() + File.separator + pathComponents[pathComponents.length - 1]); //length -1을 파일명

    if (newFile.createNewFile()) {
      System.out.println("File created successfully: " + newFile.getAbsolutePath());
      return newFile;
    } else {
      throw new IOException("Failed to create file: " + newFile.getAbsolutePath());
    }
  }



  public static File convertInputStreamToFile(final InputStream in) throws IOException {

    // https://dev.umejintan.com/9
    File tempFile = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
    // when exit JVM, this temp file is deleted itself.
    tempFile.deleteOnExit();

    copyInputStreamToFile(in, tempFile);

    return tempFile;
  }

  private static void copyInputStreamToFile(final InputStream inputStream, final File file)
      throws IOException {

    try (FileOutputStream outputStream = new FileOutputStream(file)) {
      int read;
      byte[] bytes = new byte[1024];

      while ((read = inputStream.read(bytes)) != -1) {
        outputStream.write(bytes, 0, read);
      }
    }
  }


}
