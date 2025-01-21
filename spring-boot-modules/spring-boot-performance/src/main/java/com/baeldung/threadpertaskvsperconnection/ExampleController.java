package com.baeldung.threadpertaskvsperconnection;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ExampleController {

    @GetMapping("/per-connection")
    public String threadPerConnection() throws InterruptedException {
        System.out.println("Handling connection on thread: " + Thread.currentThread()
            .getName());
        TimeUnit.SECONDS.sleep(15); // Simulate a long-running connection
        return "Response of long running task";
    }

    @GetMapping("/per-request")
    @Async("threadPerRequestExecutor")
    public Future<String> threadPerRequest() throws InterruptedException {
        System.out.println("Handling connection on thread: " + Thread.currentThread()
            .getName());
        TimeUnit.SECONDS.sleep(15); // Simulate a long-running connection
        return CompletableFuture.supplyAsync(() -> "Response of long running task");
    }
}



