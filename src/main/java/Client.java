import lombok.Data;


@Data
public class Client {
    private final Long id;
    private String name;

    public Client(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
