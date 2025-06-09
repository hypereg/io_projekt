import io.javalin.http.Context;
import org.example.controllers.EdokumentController;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

class EdokumentControllerTest {

    @Test
    void testGetAll() throws SQLException {
        Context ctx = mock(Context.class);
        EdokumentController.getAll(ctx);
        verify(ctx).json(any());
    }

    @Test
    void testGetByIdNotFound() throws SQLException {
        Context ctx = mock(Context.class);
        when(ctx.pathParam("id")).thenReturn("9999");
        when(ctx.status(anyInt())).thenReturn(ctx);
        when(ctx.result(anyString())).thenReturn(ctx);

        EdokumentController.getById(ctx);
        verify(ctx).status(404);
        verify(ctx).result(anyString());
    }
}