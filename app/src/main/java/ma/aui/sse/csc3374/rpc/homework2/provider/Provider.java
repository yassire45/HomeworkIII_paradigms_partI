package ma.aui.sse.csc3374.rpc.homework2.provider;

import javax.xml.ws.Endpoint;
public class Provider {
    private static final String URL = "http://localhost:9000/ServiceImpl";
   
    public static void main(String[] args) {
     
        ServiceImpl serviceimpl = new ServiceImpl();
        System.out.println("Publishing The Service");
        Endpoint.publish(URL, serviceimpl);
        System.out.println("The Service Published");
    }
}
