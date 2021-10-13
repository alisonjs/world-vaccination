import com.alisonjs.business.domain.User;
import com.alisonjs.business.exceptions.AuthorizationException;
import com.alisonjs.business.exceptions.BusinessException;
import com.alisonjs.business.exceptions.NotFoundException;
import com.alisonjs.business.repository.UserRepository;
import com.alisonjs.business.service.UserService;
import com.alisonjs.business.service.impl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Alison Sousa (alison.sousa@ufrn.br)
 * @since 13/10/2021
 */
public class UserServiceImplTest {


    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp(){
        this.autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.autoCloseable.close();
    }

    @Nested
    class curdUser{

        @Test
        void save(){
            var user = getUserInstance();
            userService.save(user);
            Mockito.verify(userRepository, Mockito.times(1)).save(ArgumentMatchers.any(User.class));
        }

        @Test
        void getAll(){
            userService.getAll();
            Mockito.verify(userRepository, Mockito.times(1)).getAll();
        }

        @Test
        void getOne(){
            Mockito.lenient().when(userRepository.getOne(ArgumentMatchers.anyLong())).thenReturn(getUserInstance());
            userService.getOne(1L);
            Mockito.verify(userRepository, Mockito.times(1)).getOne(ArgumentMatchers.anyLong());
        }

        @Test
        void getByUsername(){
            Mockito.lenient().when(userRepository.getByUsername(ArgumentMatchers.anyString())).thenReturn(getUserInstance());
            userService.getByUsername("user");
            Mockito.verify(userRepository, Mockito.times(1)).getByUsername(ArgumentMatchers.anyString());
        }

        @Test
        void login(){
            Mockito.lenient().when(userRepository.getByUsernameAndPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(getUserInstance());
            userService.login("user", "pass");
            Mockito.verify(userRepository, Mockito.times(1)).getByUsernameAndPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        }
    }

    @Nested
    class throwExceptionWhen{

        @Test
        void userIdNotFound(){
            Mockito.lenient().when(userRepository.getOne(ArgumentMatchers.anyLong())).thenReturn(null);
            var exception = Assertions.assertThrows(NotFoundException.class, () -> userService.getOne(1L), "User not found with the given id");
            Assertions.assertNotNull(exception);
            Assertions.assertEquals("User not found with the given id 1", exception.getMessage());
            Mockito.verify(userRepository, Mockito.times(1)).getOne(ArgumentMatchers.anyLong());
        }

        @Test
        void usernameAndPasswordNotFound(){
            Mockito.lenient().when(userRepository.getByUsernameAndPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(null);
            var exception = Assertions.assertThrows(AuthorizationException.class, () -> userService.login("user", "pass"), "User or password incorrect.");
            Assertions.assertNotNull(exception);
            Assertions.assertEquals("User or password incorrect.", exception.getMessage());
            Mockito.verify(userRepository, Mockito.times(1)).getByUsernameAndPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        }

        @ParameterizedTest
        @NullAndEmptySource
        void usernameRequiredFieldNotFound(String username){
            var user = getUserInstance();
            user.setUsername(username);
            var exception = Assertions.assertThrows(BusinessException.class, () -> userService.save(user));
            Assertions.assertNotNull(exception);
            Assertions.assertEquals("Username required", exception.getMessage());
            Mockito.verify(userRepository, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
        }

        @ParameterizedTest
        @NullAndEmptySource
        void passwordRequiredFieldNotFound(String password){
            var user = getUserInstance();
            user.setPassword(password);
            var exception = Assertions.assertThrows(BusinessException.class, () -> userService.save(user));
            Assertions.assertNotNull(exception);
            Assertions.assertEquals("Password required", exception.getMessage());
            Mockito.verify(userRepository, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
        }

        @ParameterizedTest
        @NullAndEmptySource
        void emailRequiredFieldNotFound(String email){
            var user = getUserInstance();
            user.setEmail(email);
            var exception = Assertions.assertThrows(BusinessException.class, () -> userService.save(user));
            Assertions.assertNotNull(exception);
            Assertions.assertEquals("Email required", exception.getMessage());
            Mockito.verify(userRepository, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
        }

    }

    public User getUserInstance(){
        return User.builder()
                .username("user")
                .password("pass")
                .email("email")
                .build();
    }
}
