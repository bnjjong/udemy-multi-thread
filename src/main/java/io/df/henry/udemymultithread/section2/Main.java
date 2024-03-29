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

package io.df.henry.udemymultithread.section2;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    Thread thread =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                System.out.println(
                    "We are now in thread \"" + Thread.currentThread().getName() + "\"");
                System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
              }
            });
    thread.setName("New worker thread");
    thread.setPriority(Thread.MAX_PRIORITY);
    System.out.println(
        "We are in thread \""
            + Thread.currentThread().getName()
            + "\" before starting a new thread.");
    thread.start();
    System.out.println(
        "We are in thread \""
            + Thread.currentThread().getName()
            + "\" after starting a new thread.");

    Thread.sleep(10000);
  }
}
