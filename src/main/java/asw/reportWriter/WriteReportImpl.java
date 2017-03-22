package asw.reportWriter;

import org.springframework.beans.factory.annotation.Autowired;

import asw.reportWriter.kafka.KafkaProducer;

public class WriteReportImpl implements WriteReport {
	@Autowired
	private KafkaProducer kafkaProducer;

	@Override
	public void log(String event, String msg) {
		kafkaProducer.send(event, msg);
	}
}
