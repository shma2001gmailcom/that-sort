package org.misha.luxoft;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.atomic.AtomicReference;


public class Pipe {
    // using the two connected pipe streams,
    // pass one stream to ONE THREAD, and the other stream to ANOTHER THREAD.
    // THE READ() AND WRITE() CALLS ON THE STREAMS ARE BLOCKING,
    // meaning if you try to use the same thread to both read and write,
    // this may result in the thread deadlocking itself.
    public static void main(String[] args) throws IOException {
        AtomicReference<StringBuilder> sb = new AtomicReference<>(new StringBuilder());
        try (PipedOutputStream output = new PipedOutputStream() {};
             PipedInputStream input = new PipedInputStream(output)
        ) {
            Thread writer = new Thread(() -> {
                for (int i = 0; i < "hi there in Haifa!".length(); i++) {
                    try {
                        output.write("hi there in Haifa!".charAt(i));
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
            });
            Thread reader = new Thread(() -> {
                for (int i = 0; i < 8; i++) {
                    try {
                        sb.get().append((char) input.read());
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
            });
            writer.start();
            reader.start();
            try {
                writer.join();
                reader.join();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println(sb.get().toString());
    }
}



