package name.svetov;

import io.micronaut.http.annotation.*;

@Controller("/space")
public class SpaceController {

    @Get(uri = "/", produces = "text/plain")
    public String index() {
        return "Example Response";
    }
}