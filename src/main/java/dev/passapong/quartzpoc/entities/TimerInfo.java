package dev.passapong.quartzpoc.entities;

public class TimerInfo {
    public int totalFireCount;
    public boolean runForever;
    public long repeatIntervalMs;
    public long initialOffsetMs;
    public String callbackData;
}
