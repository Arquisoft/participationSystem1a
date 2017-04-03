package asw.reportWriter.kafka;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.ManagedBean;

/**
 * Created by herminio on 26/12/16.
 */
@ManagedBean
public class KafkaProducer {

    private static final Logger logger = Logger.getLogger(KafkaProducer.class);

    // TOPICS
    public static final String NEW_SUGGESTION = "newSuggestion";
    public static final String NEW_COMMENT = "newComment";
    public static final String POSITIVE_COMMENT = "positiveComment";
    public static final String NEGATIVE_COMMENT = "negativeComment";
    public static final String POSITIVE_SUGGESTION = "positiveSuggestion";
    public static final String MIN_VOTES_REACHED = "minVotesReached";
    
    // SENDS
 	public void sendNewSuggestion(long suggestionId) {
 		send(NEW_SUGGESTION, "Creada la propuesta -> " + suggestionId);
 	}
 	
 	public void sendNewComment(long commentId) {
 		send(NEW_COMMENT, "Creado el comentario -> " + commentId);
 	}
 	
 	public void sendPositiveComment(long commentId) {
 		send(POSITIVE_COMMENT, "Voto positivo en el comentario -> " + commentId);
 	}
 	
 	public void sendNegativeComment(long commentId) {
 		send(NEGATIVE_COMMENT, "Voto negativo en el comentario -> " + commentId);
 	}
 	
 	public void sendPositiveSuggestion(long suggestionId) {
 		send(POSITIVE_SUGGESTION, "Apoyo a la propuesta -> " + suggestionId);
 	}
 	
 	public void sendMinVotesReached(long suggestionId) {
 		send(MIN_VOTES_REACHED, "Se ha alcanzado el mínimo de votos -> " + suggestionId);
 	}
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate = new KafkaProducerFactory().kafkaTemplate();

    public void send(String topic, String data) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Success on sending message \"" + data + "\" to topic " + topic);
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("Error on sending message \"" + data + "\", stacktrace " + ex.getMessage());
            }
        });
    }

}
