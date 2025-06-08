
import io.javalin.http.Context;
import org.example.controllers.SalaController;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;

import static org.mockito.Mockito.*;

class SaleControllerTest {

    @Test
    void testGetAll() throws SQLException {
        Context ctx = mock(Context.class);
        SalaController.getAll(ctx);
        verify(ctx).json(any());
    }

    @Test
    void testCreate() throws SQLException {
        Context ctx = mock(Context.class);
        when(ctx.bodyAsClass(HashMap.class)).thenReturn(new HashMap<>());
        SalaController.create(ctx);
        verify(ctx).status(201);
        verify(ctx).json(any());
    }

    @Test
    void testGetByIdNotFound() throws SQLException {
        Context ctx = mock(Context.class);
        when(ctx.pathParam("id")).thenReturn("9999");
        SalaController.getById(ctx);
        verify(ctx).status(404);
    }
}