package app.routes;

import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class RoutesTwo {

    private final PlaceholderRoute placeholderRoute = new PlaceholderRoute(); // TODO: Ret (fx hotelRoute)
    private final PlaceholderChildRoute placeholderChildRoute = new PlaceholderChildRoute(); // TODO: Ret (fx roomRoute)

    public EndpointGroup getRoutes() {
        return () -> {
            path("/placeholders", placeholderRoute.getRoutes()); // TODO: Ret path (fx /hotels)
            path("/children", placeholderChildRoute.getRoutes()); // TODO: Ret path (fx /rooms)
        };
    }
}
