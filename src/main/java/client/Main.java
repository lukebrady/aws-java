package client;

import client.EC2Reader;
import client.Persistence;
import org.bson.Document;
import software.amazon.awssdk.services.ec2.model.Instance;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;
import software.amazon.awssdk.services.ec2.model.Reservation;

public class Main {
    public static void main(String[] args) {
        /*
        EC2Reader e = new EC2Reader();
        DescribeInstancesResponse resp = e.getAllInstances();
        for (Reservation res : resp.reservations()) {
            for (Instance instance: res.instances()) {
                e.terminateInstance(instance);
            }
        }
        // e.createNewInstance();
        e.getAllInstances();
        e.close();
         */
        Persistence p = new Persistence("localhost", 27107);
        p.setDatabaseCollection("test", "test_col");
        p.writeData(new Document("name", "test").append("version", 1));
    }
}
