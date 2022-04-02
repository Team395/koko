package frc.robot;

public final class SolenoidConfiguration {
    public int pcmId;
    public int forwardChannel;
    public int reverseChannel;

    public SolenoidConfiguration(int pcmId, int forwardChannel, int reverseChannel) {
        this.pcmId = pcmId;
        this.forwardChannel = forwardChannel;
        this.reverseChannel = reverseChannel;
    }
}
