import io.javalin.http.Context;
import org.example.controllers.SalaController;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
        Map<String, Object> sala = new HashMap<>();
        sala.put("nazwa", "Aula");
        sala.put("budynek", "Główny");
        sala.put("pojemnosc", 100);
        when(ctx.bodyAsClass(Map.class)).thenReturn(sala);
        when(ctx.status(anyInt())).thenReturn(ctx);
        when(ctx.json(any())).thenReturn(ctx);

        SalaController.create(ctx);
        verify(ctx).status(201);
        verify(ctx).json(any());
    }

    @Test
    void testGetByIdNotFound() throws SQLException {
        Context ctx = mock(Context.class);
        when(ctx.pathParam("id")).thenReturn("9999");
        when(ctx.status(anyInt())).thenReturn(ctx);
        when(ctx.result(anyString())).thenReturn(ctx);

        SalaController.getById(ctx);
        verify(ctx).status(404);
        verify(ctx).result(anyString());
    }
}