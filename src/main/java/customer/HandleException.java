package customer;

public class HandleException extends RuntimeException {

    public  HandleException(){
        super("Person not found...");
    }

}
