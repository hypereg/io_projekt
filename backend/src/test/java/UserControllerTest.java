import io.javalin.http.Context;
import org.example.controllers.UserController;
import org.example.models.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;

import static org.mockito.Mockito.*;

class UserControllerTest {

    @Test
    void testGetAll() throws SQLException {
        Context ctx = mock(Context.class);
        UserController.getAll(ctx);
        verify(ctx).json(any());
    }

    @Test
    void testCreate() throws SQLException {
        Context ctx = mock(Context.class);
        User user = new User();
        user.imie = "Jan";
        user.nazwisko = "Kowalski";
        user.email = "jann@kowalski.pl";
        user.haslo = "haslo";
        user.rola = "student";
        user.numerBetterIndex = "12345";
        when(ctx.bodyAsClass(User.class)).thenReturn(user);
        when(ctx.status(anyInt())).thenReturn(ctx);
        when(ctx.json(any())).thenReturn(ctx);

        UserController.create(ctx);
        verify(ctx).status(201);
        verify(ctx).json(any());
    }

    @Test
    void testGetByIdNotFound() throws SQLException {
        Context ctx = mock(Context.class);
        when(ctx.pathParam("id")).thenReturn("9999");
        when(ctx.status(anyInt())).thenReturn(ctx);
        when(ctx.result(anyString())).thenReturn(ctx);

        UserController.getById(ctx);
        verify(ctx).status(404);
        verify(ctx).result(anyString());
    }
}