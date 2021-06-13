package beSen.test.bsSimpleServer;

import beSen.bsSimpleServer.BsSimpleServer;

import java.io.File;

public class TestBsSimpleServer {
    public static void main(String[] args) throws Exception{
        BsSimpleServer bsSimpleServer = new BsSimpleServer(8989);
        bsSimpleServer.addAction("/",((httpServerRequest, httpServerResponse) -> {
            try {
                File file = new File("C:\\Users\\panpan\\gitclone\\hutool-site\\index.html");
                httpServerResponse.write(file);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        })).start();
    }
}
