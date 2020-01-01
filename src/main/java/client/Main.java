package client;

import client.EC2Reader;
import software.amazon.awssdk.services.ec2.model.Instance;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;
import software.amazon.awssdk.services.ec2.model.Reservation;

public class Main {
    public static void main(String[] args) {
        EC2Reader e = new EC2Reader();
        DescribeInstancesResponse resp = e.getAllInstances();
        for (Reservation res : resp.reservations()) {
            for (Instance instance: res.instances()) {
                System.out.println(instance);
            }
        }
        // e.createNewInstance();
        e.getAllInstances();
        e.close();
    }
}
