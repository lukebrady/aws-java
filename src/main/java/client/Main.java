package client;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.model.Volume;

import java.net.ServerSocket;

public class Main
{
    public static void main(String[] args)
    {
        EBSVolumeClient client = new EBSVolumeClient("default", Region.US_EAST_1);
        Volume volume = client.describeVolume("vol-0d9c469ea0e19754b");
        System.out.println(volume.encrypted());
    }
}
