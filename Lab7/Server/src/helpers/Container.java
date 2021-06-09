package helpers;
import java.net.SocketAddress;

public class Container {
    private final Object object;
    private final SocketAddress address;

    public Container(Object object, SocketAddress address) {
        this.object = object;
        this.address = address;
    }

    public SocketAddress getAddress() {
        return address;
    }

    public Object getObject() {
        return object;
    }
}
