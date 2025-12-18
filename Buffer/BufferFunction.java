import java.nio.ByteBuffer;
import java.nio.CharBuffer;
public class BufferFunction {
    public static void main(String[] args) {
        CharBuffer charBuffer = CharBuffer.allocate(10);
        String text="BufferDemo";
        System.out.println("Putting text into CharBuffer: " + text);
        for (int i = 0; i < text.length(); i++) {
            char c= text.charAt(i);
            charBuffer.put(c);
    }
    int position = charBuffer.position();
    System.out.println("Current position after put: " + position);
    charBuffer.flip();
    System.out.println("Reading characters from CharBuffer:");
    while (charBuffer.hasRemaining()){
        System.out.println(charBuffer.get());
    }
    charBuffer.position(5);
    charBuffer.mark();
    charBuffer.position(6);
    charBuffer.reset();
    System.out.println("\nPosition after reset: " + charBuffer.position());
}
}