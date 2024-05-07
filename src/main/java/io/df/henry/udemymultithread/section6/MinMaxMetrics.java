package io.df.henry.udemymultithread.section6;

public class MinMaxMetrics {
  // Add all necessary member variables
  private volatile long max = 0;
  private volatile long min = 0;

  /**
   * Initializes all member variables
   */
  public MinMaxMetrics() {
    // Add code here
    this.max = Long.MIN_VALUE;
    this.min = Long.MAX_VALUE;

  }

  /**
   * Adds a new sample to our metrics.
   */
  public void addSample(long newSample) {
    synchronized (this) {
      this.max = Math.max(this.max, newSample);
      this.min = Math.min(this.min, newSample);
    }
  }

  /**
   * Returns the smallest sample we've seen so far.
   */
  public long getMin() {
    // Add code here
    return this.min;
  }

  /**
   * Returns the biggest sample we've seen so far.
   */
  public long getMax() {
    // Add code here
    return this.max;
  }
}
