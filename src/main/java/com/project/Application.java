package com.project;

public class Application {
    public static void main(String[] args) {
        System.out.println("Application running successfully on port 8080...");
        // Keep the application running inside the container
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
