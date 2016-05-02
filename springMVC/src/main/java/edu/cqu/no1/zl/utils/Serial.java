package edu.cqu.no1.zl.utils;

import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by zl on 2016/5/1.
 */
@Component
public class Serial<T> {
    public byte[] objectToByte(T object) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(object);
        byte[] bytes = bo.toByteArray();
        bo.close();
        oo.close();
        return bytes;
    }

    public T ByteToObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = new ObjectInputStream(bi);
        Object obj = oi.readObject();
        bi.close();
        oi.close();
        return (T) obj;
    }
}
