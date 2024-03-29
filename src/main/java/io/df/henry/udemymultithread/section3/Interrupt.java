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

package io.df.henry.udemymultithread.section3;

import java.math.BigInteger;

public class Interrupt {
  public static void main(String[] args) {
    Thread thread = new Thread(new LongComputationTask(new BigInteger("200000"), new BigInteger("100000000")));

    thread.start();
    // 연산이 크다면 인터럽트 할 수 있다. 하지만 별도 처리 로직이 필요.
    thread.interrupt();
  }

  private static class LongComputationTask implements Runnable {
    private BigInteger base;
    private BigInteger power;

    public LongComputationTask(BigInteger base, BigInteger power) {
      this.base = base;
      this.power = power;
    }

    @Override
    public void run() {
      System.out.println(base + "^" + power + " = " + pow(base, power));
    }

    private BigInteger pow(BigInteger base, BigInteger power) {
      BigInteger result = BigInteger.ONE;

      for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
        // 인터럽트를 처리할 로직
        if (Thread.currentThread().isInterrupted()) {
          System.out.println("Prematurely interrupted computation");
          return BigInteger.ZERO;
        }
        result = result.multiply(base);
      }

      return result;
    }
  }
}
