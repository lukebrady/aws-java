package client;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeVolumesRequest;
import software.amazon.awssdk.services.ec2.model.DescribeVolumesResponse;
import software.amazon.awssdk.services.ec2.model.Volume;

import java.util.Collection;

public class EBSVolumeClient
{
    protected Ec2Client ec2Client;

    EBSVolumeClient(String profileName, Region region)
    {
        ec2Client = Ec2Client.builder().region(region).credentialsProvider(ProfileCredentialsProvider.builder()
                .profileName(profileName)
                .build()).build();
    }

    public Volume describeVolume(String volumeId)
    {
        DescribeVolumesRequest volumeRequest = DescribeVolumesRequest.builder().volumeIds(volumeId).build();
        DescribeVolumesResponse volumesResponse = ec2Client.describeVolumes(volumeRequest);
        return volumesResponse.volumes().get(0);
    }

    public Collection<Volume> describeVolumes()
    {
        DescribeVolumesResponse volumesResponse = ec2Client.describeVolumes();
        return  volumesResponse.volumes();
    }

    protected Ec2Client getEc2Client()
    {
        return ec2Client;
    }
}
