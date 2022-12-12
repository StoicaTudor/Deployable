package ro.tuc.ds2020.service.rabbitmq;

import java.time.LocalDateTime;
import java.util.UUID;

public class Message {

    private final LocalDateTime timestamp;
    private final UUID deviceId;
    private final double measurementValue;

    public Message(LocalDateTime timestamp, UUID deviceId, double measurementValue) {
        this.timestamp = timestamp;
        this.deviceId = deviceId;
        this.measurementValue = measurementValue;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public UUID getDeviceId() {
        return deviceId;
    }

    public double getMeasurementValue() {
        return measurementValue;
    }
}
