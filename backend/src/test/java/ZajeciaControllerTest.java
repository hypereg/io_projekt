import io.javalin.http.Context;
import org.example.controllers.ZajeciaController;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

class ZajeciaControllerTest {

    @Test
    void testGetAll() throws SQLException {
        Context ctx = mock(Context.class);
        ZajeciaController.getAll(ctx);
        verify(ctx).json(any());
    }
}