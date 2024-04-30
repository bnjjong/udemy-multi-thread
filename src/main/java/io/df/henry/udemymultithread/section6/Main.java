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
package io.df.henry.udemymultithread.section6;

/**
 * Resource Sharing & Introduction to Critical Sections
 * https://www.udemy.com/java-multithreading-concurrency-performance-optimization
 */
public class Main {
  public static void main(String[] args) throws InterruptedException {
    InventoryCounter inventoryCounter = new InventoryCounter();
    IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
    DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

    incrementingThread.start();
    decrementingThread.start();

    incrementingThread.join();
    decrementingThread.join();

    System.out.println("We currently have " + inventoryCounter.getItems() + " items");
  }

  public static class DecrementingThread extends Thread {

    private InventoryCounter inventoryCounter;

    public DecrementingThread(InventoryCounter inventoryCounter) {
      this.inventoryCounter = inventoryCounter;
    }

    @Override
    public void run() {
      for (int i = 0; i < 10000; i++) {
        inventoryCounter.decrement();
      }
    }
  }

  public static class IncrementingThread extends Thread {

    private InventoryCounter inventoryCounter;

    public IncrementingThread(InventoryCounter inventoryCounter) {
      this.inventoryCounter = inventoryCounter;
    }

    @Override
    public void run() {
      for (int i = 0; i < 10000; i++) {
        inventoryCounter.increment();
      }
    }
  }

  // 전체 클래스안의 메소드에 락을 거는 방법. 모니터 방식
//  private static class InventoryCounter {
//    private int items = 0;
//
//    public synchronized void increment() {
//      items++;
//    }
//
//    public synchronized void decrement() {
//      items--;
//    }
//
//    public synchronized int getItems() {
//      return items;
//    }
//  }

  // 특정 범위만 락을 걸때. 유연성과 세분성을 높여주지만 코드가 장황해 질 수 있음.
  private static class InventoryCounter {
    private int items = 0;
    Object lock = new Object();
    public void increment() {
      synchronized (lock) {
        items++;
      }
    }

    public void decrement() {
      synchronized (lock) {
        items--;
      }
    }

    public int getItems() {
      synchronized (lock) {
        return items;
      }
    }
  }
}
