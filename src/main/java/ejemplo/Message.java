package ejemplo;

/**
 * Created by herminio on 27/2/17.
 */
public class Message {
	//Clase mensaje, no se si hay que mantenerla y enviar objetos de este tipo a kafka habria que mirarlo
    private String message;

    public Message() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
