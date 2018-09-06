```java
@Getter
@Setter
@Accessors(chain = true,fluent = true)
@RequiredArgsConstructor(staticName = "of")
public class Carport {

    @NotNull
    private String model;

    @NotBlank(message = "Engine code cannot be blank")
    private String engine;

    @Equals(value = "Deutschland",message = "Wrong country")
    private String country;

}
```
xxx

```java
@Override
public T doTransformation() {

    log.info("point 1");

    log.info("point 2");

    log.info("point 3");

    return elements.stream()
			.peek(e->log.info(e.toString()))
			.filter(t -> t.toString().equals(filterParam))
			.peek(e->log.info(e.toString()))
			.map(t -> combineFunctions(operations).apply(t))
			.findAny().orElseThrow(() -> new NullPointerException("it's me"));
}
```
