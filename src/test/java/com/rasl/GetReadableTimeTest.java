package com.rasl;


import com.rasl.pojo.Task;
import org.junit.Assert;
import org.junit.Test;


public class GetReadableTimeTest {


    @Test
    public void test() {
        System.out.println(String.format("%d:%d:%d", 1, 15, 20));

    }

    @Test
    public  void getTimeTest() {
        Task task = new Task();

        task.setSpentTime(3600);
        Assert.assertEquals("1 hour. 0 min. 0 sec.", task.getReadableTime());
        System.out.println("0:00:00");

        task.setSpentTime(60);
        Assert.assertEquals("1 hour. 0 min. 0 sec.", task.getReadableTime());

        task.setSpentTime(60);
        Assert.assertEquals("1 hour. 0 min. 0 sec.", task.getReadableTime());

        task.setSpentTime(60);
        Assert.assertEquals("1 hour. 0 min. 0 sec.", task.getReadableTime());

        task.setSpentTime(60);
        Assert.assertEquals("1 hour. 0 min. 0 sec.", task.getReadableTime());

        task.setSpentTime(60);
        Assert.assertEquals("1 hour. 0 min. 0 sec.", task.getReadableTime());

    }
}
