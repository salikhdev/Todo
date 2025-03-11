package uz.salikhdev.todo;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;

class SimpleContainerTest {
    @Test
    void testContainerStart() {
        try (GenericContainer<?> container = new GenericContainer<>("alpine").withCommand("sleep 10")) {
            container.start();
            System.out.println("Container started successfully");
        }
    }
}
