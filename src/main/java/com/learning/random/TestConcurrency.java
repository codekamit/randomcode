package com.learning.random;

public class TestConcurrency{
//    private static final Object lock = new Object(); // Shared lock object

    public void print() {
        synchronized (this) { // Synchronize on the shared lock object
            for(int idx = 0; idx < 100; idx++) {
                System.out.println("Aye tatti tera naam kya : " + Thread.currentThread().getName());
            }
        }
    }
}
