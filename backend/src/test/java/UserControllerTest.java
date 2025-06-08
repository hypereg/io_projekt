
import io.javalin.http.Context;
import org.example.controllers.UserController;
import org.example.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

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
        when(ctx.bodyAsClass(User.class)).thenReturn(new User());
        UserController.create(ctx);
        verify(ctx).status(201);
        verify(ctx).json(any());
    }

    @Test
    void testGetByIdNotFound() throws SQLException {
        Context ctx = mock(Context.class);
        when(ctx.pathParam("id")).thenReturn("9999");
        UserController.getById(ctx);
        verify(ctx).status(404);
    }
}