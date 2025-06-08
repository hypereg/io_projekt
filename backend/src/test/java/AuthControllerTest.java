
import io.javalin.http.Context;
import org.example.controllers.AuthController;
import org.example.models.User;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Test
    void testRegisterUserExists() throws Exception {
        Context ctx = mock(Context.class);
        User user = new User();
        user.setEmail("test@test.com");
        when(ctx.bodyAsClass(User.class)).thenReturn(user);
        AuthController.register(ctx);
        verify(ctx, atLeastOnce()).status(anyInt());
    }

    @Test
    void testLoginInvalid() throws Exception {
        Context ctx = mock(Context.class);
        User user = new User();
        user.email = "notfound@test.com";
        user.haslo = "wrong";
        when(ctx.bodyAsClass(User.class)).thenReturn(user);
        AuthController.login(ctx);
        verify(ctx).status(401);
    }

    @Test
    void testVerifyTokenInvalid() {
        Context ctx = mock(Context.class);
        when(ctx.header("Authorization")).thenReturn("badtoken");
        AuthController.verifyToken(ctx);
        verify(ctx).status(401);
    }
}