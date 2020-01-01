package client;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;
import software.amazon.awssdk.services.ec2.model.Instance;
import software.amazon.awssdk.services.ec2.model.RunInstancesRequest;
import software.amazon.awssdk.services.ec2.model.RunInstancesResponse;

public class EC2Reader {
    private Ec2Client client = Ec2Client.builder().region(Region.US_EAST_1)
            .credentialsProvider(ProfileCredentialsProvider.builder()
                    .profileName("test_sdk")
                    .build()).build();


    EC2Reader() { }

    public DescribeInstancesResponse getAllInstances() {
        DescribeInstancesResponse resp = client.describeInstances();
        return resp;
    }

    public RunInstancesResponse createNewInstance() {
        try {
            RunInstancesRequest r = RunInstancesRequest.builder()
                    .imageId("ami-00068cd7555f543d5")
                    .instanceType("t2.micro")
                    .maxCount(1)
                    .minCount(1)
                    .build();
            RunInstancesResponse resp = client.runInstances(r);
            return resp;
        } catch (AwsServiceException e) {
            System.out.println(e);
            return null;
        } catch (Error e) {
            System.out.println("An error occurred creating the new instance.");
            return null;
        }
    }

    void close() {
        client.close();
    }
}
