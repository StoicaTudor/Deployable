package ro.tuc.ds2020.service.rabbitmq;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dto.device.DeviceUpdateDto;
import ro.tuc.ds2020.entity.Device;
import ro.tuc.ds2020.entity.EnergyConsumption;
import ro.tuc.ds2020.mapper.DeviceMapper;
import ro.tuc.ds2020.repository.DeviceRepository;
import ro.tuc.ds2020.service.DeviceService;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

@Service
public class Consumer {

    private final DeviceService deviceService;
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    private static final String FILE_PATH = "/home/citadin/Documents/Facultate/A4S1/DS/Lab/sensor.csv";

    private String uri;
    private final ConnectionFactory factory = new ConnectionFactory();
    private Connection connection;
    private Channel channel;
    private final boolean durable = false;    // durable - RabbitMQ will never lose the queue if a crash occurs
    private final boolean exclusive = false;  // exclusive - if queue only will be used by one connection
    private final boolean autoDelete = false; // autodelete - queue is deleted when last consumer unsubscribes
    private final String queueName = "hello";     //queue name
    private final String routingKey = "hello";
    private final String exchangeName = "";

    private ActionListener actionListener = e -> {
        try {
            consume();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    };

    private Timer timer = new Timer(11000, actionListener);

    private Consumer(
            DeviceService deviceService,
            DeviceRepository deviceRepository,
            DeviceMapper deviceMapper) throws Exception {

        this.deviceService = deviceService;
        this.deviceRepository = deviceRepository;
        this.deviceMapper = deviceMapper;
        init();
    }

    private void init() throws Exception {
        uri = System.getenv("CLOUDAMQP_URL");
        if (uri == null) uri = "amqps://gjsbwpsk:F-dLqNlV4pJpEeHLKIPG1INn4Xl_SUmP@goose.rmq2.cloudamqp.com/gjsbwpsk";

        factory.setUri(uri);

        //Recommended settings
        factory.setConnectionTimeout(30000);

        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.queueDeclare(queueName, durable, exclusive, autoDelete, null);
    }

    public void consume() throws IOException {
//        String message = readMessageToJson("1.2");
//
//        channel.basicPublish(exchangeName, routingKey, null, message.getBytes());
//        System.out.println(" [x] Sent '" + message + "'");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String receivedMessage = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + receivedMessage + "'");
            Message message = parseJson(receivedMessage);

            Device device = deviceRepository.findById(message.getDeviceId()).get();
            DeviceUpdateDto deviceUpdateDto = deviceMapper.deviceToDeviceUpdateDto(device);
            deviceUpdateDto.getEnergyConsumption().add(
                    new EnergyConsumption()
                            .setConsumption((int) message.getMeasurementValue())
                            .setDate(message.getTimestamp())
            );
            deviceUpdateDto.setEnergyConsumption(deviceUpdateDto.getEnergyConsumption());
            deviceService.updateDevice(deviceUpdateDto, deviceUpdateDto.getId());
         };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }

    private Message parseJson(String json) {

        JSONObject jsonObject = new JSONObject(json);
        String timestamp = jsonObject.getString("timestamp");
        String deviceId = jsonObject.getString("device_id");
        String measurementValue = jsonObject.getString("measurement_value");

        return new Message(
                LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("yyyy-MM-d'T'HH:mm")),
                UUID.fromString(deviceId),
                Double.parseDouble(measurementValue)
        );
    }
}
