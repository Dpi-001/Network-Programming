import java.nio.ByteBuffer;

public class BufferDemo {
    public static void main(String[] args) {

        // 1️ Creating & Allocating Buffer
        ByteBuffer buffer = ByteBuffer.allocate(10); // heap allocation
        System.out.println("Capacity: " + buffer.capacity());

        // 2️ Filling buffer (Writing data)
        buffer.put((byte) 10);
        buffer.put((byte) 20);
        buffer.put((byte) 30);

        System.out.println("Position after put: " + buffer.position());

        // 3️ flip() → prepare for reading (network send)
        buffer.flip();
        System.out.println("Limit after flip: " + buffer.limit());

        // 4️Draining buffer (Reading data)
        while (buffer.hasRemaining()) {
            System.out.println("Read: " + buffer.get());
        }

        // 5️ rewind() → read again
        buffer.rewind();
        System.out.println("Position after rewind: " + buffer.position());

        // 6️ mark() and reset()
        buffer.get();     // read one byte
        buffer.mark();    // mark position
        buffer.get();     // read next byte
        buffer.reset();   // back to mark
        System.out.println("After reset: " + buffer.get());

        // 7️remaining()
        System.out.println("Remaining: " + buffer.remaining());

        // 8 clear() → ready for new network data
        buffer.clear();
        System.out.println("Position after clear: " + buffer.position());
        System.out.println("Limit after clear: " + buffer.limit());

        // 9️ Direct buffer (used in high-speed networking)
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(10);

        //  Wrapping array into buffer
        byte[] data = {1, 2, 3, 4};
        ByteBuffer wrappedBuffer = ByteBuffer.wrap(data);
    }
}
